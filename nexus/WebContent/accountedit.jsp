<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>アカウント編集</title>
<script type="text/javascript" src="../js/common.js"></script>
<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=M+PLUS+1p"
	rel="stylesheet">
<link href="../css/bootstrap-reboot.css" rel="stylesheet">
<link href="../css/common.css" rel="stylesheet">
<link href="../css/header.css" rel="stylesheet">
<link href="../css/footer.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
</head>
<body>
	<header>
		<section>
			<h1 class="logo">
				<a href="/nexus/web/staff-top"><img
					src="../css/TryNexus-Logo.png" width="97" height="70" alt="TryNexus" /></a>
			</h1>
			</h1>
			<nav>
				<ul class="mainnavi">
					<li><a href="/nexus/web/job-search"><i class="fas fa-home"></i>検索</a></li>
					<li><a href="/nexus/web/jobseeker-list"><i
							class="fas fa-search"></i>登録&amp;閲覧</a>
						<ul class="drop-menu">
							<li><a href="/nexus/web/kyujin-disp">求人情報<i
									class="fas fa-angle-right"></i></a></li>
							<li><a href="/nexus/web/jobseeker-list">求職者情報<i
									class="fas fa-angle-right"></i></a></li>
							<li><a href="/nexus/web/match-disp">マッチング登録<i
									class="fas fa-angle-right"></i></a></li>
						</ul></li>
					<li><a href="/nexus/web/account-list"><i
							class="far fa-bookmark"></i>管理</a></li>
				</ul>
			</nav>
			<div class="user">
				<div class="user__wrapper">
					<div class="user__name">
						<a href="#"><c:out value="${ Staff.name }" /><i
							class="fas fa-ellipsis-v"></i></a>
						<ul class="drop-menu">
							<li><a href="/nexus/web/logout">ログアウト<i
									class="fas fa-angle-right"></i></a></li>
						</ul>
					</div>
				</div>
			</div>
		</section>
	</header>
	<main>
	<h2>アカウント編集</h2>
	<ul>
		<c:forEach var="message" items="${ messages }">
			<li><c:out value="${ message }" /></li>
		</c:forEach>
	</ul>
	<form action="/nexus/web/account-editcomp" method="post">
		<table>
			<tr>
				<th>項目名</th>
				<th></th>
			</tr>
			<tr>
				<td>アカウントID</td>

				<td><input type="hidden" name="id"
					value="<c:out value="${ Staff.id }" />"> <fmt:formatNumber
					value="${ Staff.id }" pattern="0000"/></td>

			</tr>
			<tr>
				<td>氏名</td>
				<td><input type="text" name="name"
					value="<c:out value="${ Staff.name }" />"></td>
			</tr>
			<tr>
				<td>氏名（かな）</td>
				<td><input type="text" name="kana"
					value="<c:out value="${ Staff.kana }" />"></td>
			</tr>
			<tr>
				<td>ユーザー権限</td>
				<td><input type="hidden" name="authority"
					value="<c:out value="${ Staff.authority }" />"> <c:if
						test="${Staff.authority == 1}">管理者</c:if> <c:if
						test="${Staff.authority == 2}">その他</c:if></td>
			</tr>

			<tr>
				<td>新しいパスワード</td>
				<td><input type="password" name="password"
					value="<c:out value="${ Staff.password }"/>"></td>
			</tr>
		</table>
		<input class="main-b" type="submit" value="更新">
	</form>

	<form name="delete" action="/nexus/web/account-delete" method="GET"
		onsubmit="return kakunin()">
		<input type="hidden" name="id" value="${ Staff.id }"> <input
			class="main-b" type="submit" value="削除">
	</form>
	<input class="main-b" type="button" value="戻る"
		onclick="location.href='/nexus/web/account-list'"> <!--   <input class="main-b" type="button"
		onclick="location.href='/nexus/web/account-list'" value="戻る">
	--> <!--  <form method="post" action="AccountEditCompletionServlet">
					<input type="hidden" name="id" value="<c:out value="${ Staff.id }" />">
					<input type="hidden" name="name" value="<c:out value="${ Staff.name }" />">
					<input type="hidden" name="kana" value="<c:out value="${ Staff.kana }" />">
					<input type="hidden" name="authority"value="<c:out value="${ Staff.authority }" />" >
					<input type="hidden" name="password" value="<c:out value="${ Staff.password }" />">
					<input class="main-b" type="submit" value="更新">
				</form>--> </main>
	<footer>
		<small>Copyright(C) 2009有限責任事業組合 大阪職業教育協働機構(A'ワーク創造館) All
			Rights Reserved.</small>
	</footer>
</body>
</html>
