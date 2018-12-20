<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>求職者情報一覧表示</title>
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
			<nav>
				<ul class="mainnavi">
					<li><a href="/nexus/web/job-search"><i class="fas fa-home"></i>検索</a></li>
					<li><a href="/nexus/web/jobseeker-list"><i
							class="fas fa-search"></i>登録&amp;閲覧</a>
						<ul class="drop-menu">
							<li><a href="/nexus/web/company-registdisp">企業登録<i
									class="fas fa-angle-right"></i></a></li>
							<li><a href="/nexus/web/kyujin-disp">求人登録<i
									class="fas fa-angle-right"></i></a></li>
							<li><a href="/nexus/web/jobseeker-list">求職者登録<i
									class="fas fa-angle-right"></i></a></li>
							<li><a href="/nexus/web/matching-regist">マッチング登録<i
									class="fas fa-angle-right"></i></a></li>
						</ul></li>
					<c:if test="${Staff.authority == 1}"><li><a href="/nexus/web/account-list"><i
							class="far fa-bookmark"></i>管理</a></li></c:if>
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
	<h2>求職者情報検索</h2>
	<div id="box">
		<form action="/nexus/web/jobseeker-list" method="post">
			<table>
				<tr>
					<td><p>求職者ID:</p> <input type="text" name="js_id" /></td>
					<td><p>求職者かな名:</p> <input type="text" name="js_kana" /></td>
					<td><p>担当職業紹介者:</p> <select name="st_name">
							<option></option>
							<c:forEach var="staff" items="${ st_name }">
								<option value="${ staff.name }"><c:out
										value="${ staff.name }" /></option>
							</c:forEach>
					</select></td>
					<td><input type="submit" class="main-b" name="send" value="検索"></td>
				</tr>
			</table>
		</form>
	</div>

	<form action="/nexus/web/jobseeker-disp" method="get">
		<input type="submit" class="main-b" name="send" value="新規登録">
	</form>


	<table class="list_table">
		<tr>
			<th></th>
			<th>求職者ID</th>
			<th>氏名</th>
			<th>性別</th>
			<th>年齢</th>
			<th>希望業種</th>
			<th>希望職種</th>
			<th>希望勤務地</th>
			<th>担当職業紹介者</th>
		</tr>
		<c:forEach var="jobseeker" items="${ list }">
			<tr>
				<td>
					<form action="/nexus/web/jobseeker-info" method="post">
						<button class="mini_b mini_b_applilist" name="js_id"
							value="<c:out value="${ jobseeker.id }" />">詳細</button>
					</form>
				</td>
				<td><fmt:formatNumber value="${ jobseeker.id }"/></td>
				<td><c:out value="${ jobseeker.js_name }" /></td>
				<td><c:if test="${ jobseeker.sex == 1 }">男</c:if> <c:if
						test="${ jobseeker.sex == 2 }">女</c:if></td>
				<td><c:out value="${ jobseeker.age }" /></td>
				<td><c:out value="${ jobseeker.hopejobcategory }" /></td>
				<td><c:out value="${ jobseeker.hopejob1 }" /></td>
				<td><c:out value="${ jobseeker.hopeworkplace }" /></td>
				<td><c:out value="${ jobseeker.st_name }" /></td>
			</tr>
		</c:forEach>
	</table>
	</main>

	<footer>
		<small>Copyright(C) 2009有限責任事業組合 大阪職業教育協働機構(A'ワーク創造館) All
			Rights Reserved.</small>
	</footer>

</body>
</html>
