<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>求職者情報一覧表示</title>
<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css"rel="stylesheet" >
<link href="https://fonts.googleapis.com/css?family=M+PLUS+1p" rel="stylesheet">
<link href="css/bootstrap-reboot.css" rel="stylesheet">
<link href="css/common.css" rel="stylesheet">
<link href="css/header.css" rel="stylesheet">
<link href="css/footer.css" rel="stylesheet">

</head>
<body>

<header>
  <section>
    <h1 class="logo"><a href="#">LOGO</a></h1>
    <nav>
      <ul class="mainnavi">
        <li><a href="#"><i class="fas fa-home"></i>検索</a></li>
        <li>
          <a href="#"><i class="fas fa-search"></i>登録&amp;閲覧</a>
          <ul class="drop-menu">
            <li><a href="#">求人情報<i class="fas fa-angle-right"></i></a></li>
            <li><a href="#">求職者情報<i class="fas fa-angle-right"></i></a></li>
            <li><a href="#">企業マスタ<i class="fas fa-angle-right"></i></a></li>
            <li><a href="#">マッチング履歴<i class="fas fa-angle-right"></i></a></li>
          </ul>
        </li>
        <li><a href="#"><i class="far fa-bookmark"></i>管理</a></li>
      </ul>
    </nav>
    <div class="user">
      <div class="user__wrapper">
        <!-- <div class="user__image"></div> -->
        <div class="user__name">
          <a href="#">山田 太郎<i class="fas fa-ellipsis-v"></i></a>
          <ul class="drop-menu">
            <li><a href="#">サインアウト<i class="fas fa-angle-right"></i></a></li>
          </ul>
        </div>
      </div>
    </div>
  </section>
</header>

<main>


  <h2>求職者情報一覧</h2>
  <div id="box">
            <form action="./reserve.html" method="post">


                            <input type="text" name="name1" required />
                            <input type="text" name="name2" required />

                            <select  name="tantoustaffname" required>
                                        <option value="">田中さん</option>
                                        <option value="25">てるてる</option>
                                        <option value="26">あっち兄さん</option>
                                        <option value="27" selected></option>
                                        <option value="28"></option>
                                        <option value="29"></option>
                                        <option value="30"></option>
                            </select>


                <input type="submit" class="main-b" name="send" value="検索">
	        </form>
 </div>

        <form action="./jobseeker/display" method="get">
            <input type="submit" class="main-b" name="send" value="新規登録">
        </form>


        <table class="list_table">
		<tr>
			<th></th>
			<th>求職者ID</th>
			<th>氏名</th>
            <th>年齢</th>
			<th>性別</th>
            <th>希望業種</th>
			<th>希望職種</th>
            <th>希望勤務地</th>
            <th>担当職業紹介者</th>
		</tr>
		<c:forEach var="jobseeker" items="${ list }">
        <tr>
        <form action="./jobseekermaininfoservlet" method="post">
			<td><button class="mini_b mini_b_applilist"name="js_id" value="<c:out value="${ jobseeker.id }" />">詳細</button>
		</form>
<!--		<c:out value="${ applicant_list.jsp }" /></td>  -->
			<td><c:out value="${ jobseeker.id }" /></td>
			<td><c:out value="${ jobseeker.js_name }" /></td>
            <td><c:out value="${ jobseeker.age }" /></td>
			<td><c:out value="${ jobseeker.sex }" /></td>
			<td><c:out value="${ jobseeker.hopejobcategory }" /></td>
            <td><c:out value="${ jobseeker.hopejob1 }" /></td>
			<td><c:out value="${ jobseeker.hopeworkplace }" /></td>
			<td><c:out value="${ jobseeker.st_name }" /></td>
		</tr>
		</c:forEach>
	</table>
</main>

<footer>
  <small>Copyright(C) 2009有限責任事業組合 大阪職業教育協働機構(A'ワーク創造館)　All Rights Reserved.</small>
</footer>

</body>
</html>
