<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>アカウント一覧</title>
<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=M+PLUS+1p" rel="stylesheet">
<link href="css/bootstrap-reboot.css" rel="stylesheet">
<link href="css/common.css" rel="stylesheet">
<link href="css/header.css" rel="stylesheet">
<link href="css/footer.css" rel="stylesheet">
<link href="css/account.css" rel="stylesheet">
</head>
<body>
<header>
  <section>
    <h1 class="logo"><a href="./stafftop.jsp">LOGO</a></h1>
    <nav>
      <ul class="mainnavi">
        <li><a href="./jobsearch"><i class="fas fa-home"></i>検索</a></li>
        <li>
          <a href="./jobseekerservlet"><i class="fas fa-search"></i>登録&amp;閲覧</a>
          <ul class="drop-menu">
            <li><a href="./detail">求人情報<i class="fas fa-angle-right"></i></a></li>
            <li><a href="./jobseekerservlet">求職者情報<i class="fas fa-angle-right"></i></a></li>
            <li><a href="./matchingdisservlet">マッチング登録<i class="fas fa-angle-right"></i></a></li>
          </ul>
        </li>
        <li><a href="./AccountListServlet"><i class="far fa-bookmark"></i>管理</a></li>
      </ul>
    </nav>
    <div class="user">
      <div class="user__wrapper">
        <div class="user__name">
          <a href="#"><c:out value="${ Staff.name }" /><i class="fas fa-ellipsis-v"></i></a>
          <ul class="drop-menu">
            <li><a href="./logoutservlet">ログアウト<i class="fas fa-angle-right"></i></a></li>
          </ul>
        </div>
      </div>
    </div>
  </section>
</header>
<main>
	<h2>アカウント一覧</h2>
	<input class="main-b newbutton" type="button" value="新規登録" onclick="location.href='accountregistservlet'">
	<table>
		<tr>
			<th></th>
			<th>アカウントID</th>
			<th>氏名</th>
			<th>氏名（かな）</th>
			<th>ユーザー権限</th>
		</tr>
		<c:forEach var="account" items="${ accounts }">
		<tr>
			<td>
				<form method="get" action="AccountEditServlet">
					<input type="hidden" name="id" value="${ account.id }">
					<input class="main-b" type="submit" value="編集">
				</form>
			</td>
			<td><c:out value="${ account.id }" /></td>
			<td><c:out value="${ account.name }" /></td>
			<td><c:out value="${ account.kana }" /></td>
			<td>
				<c:if test="${account.authority == 1}">管理者</c:if>
				<c:if test="${account.authority == 2}">その他</c:if>
			</td>
		</tr>
		</c:forEach>
	</table>
</main>
<footer> <small>Copyright(C) 2009有限責任事業組合 大阪職業教育協働機構(A'ワーク創造館) All Rights Reserved.</small> </footer>
</body>
</html>
