<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">

<head>

<meta charset="UTF-8">

<title>マッチング結果・編集・登録</title>
<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=M+PLUS+1p" rel="stylesheet">
<link href="../css/bootstrap-reboot.css" rel="stylesheet">
<link href="../css/common.css" rel="stylesheet">
<link href="../css/header.css" rel="stylesheet">
<link href="../css/footer.css" rel="stylesheet">
<link rel="stylesheet" href="../css/default.css">
<link rel="stylesheet" href="../css/default.date.css">
<!-- <link href="css/ootuka.css" rel="stylesheet"> -->
<link href="../css/matchingidsearch.css" rel="stylesheet">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/themes/base/jquery-ui.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/i18n/jquery.ui.datepicker-ja.min.js"></script>
<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/Base/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1/i18n/jquery.ui.datepicker-ja.min.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
</head>

<body>

	<header>
		<section>
			<h1 class="logo">
				<a href="/nexus/web/staff-top"><img src="../css/TryNexus-Logo.png" width="97" height="70" alt="TryNexus" /></a>
			</h1>
			<nav>
				<ul class="mainnavi">
					<li><a href="/nexus/web/job-search"><i class="fas fa-home"></i>検索</a>
					<li><a href="/nexus/web/jobseeker-list"><i class="fas fa-search"></i>登録&amp;閲覧</a>
						<ul class="drop-menu">
							<li><a href="/nexus/web/kyujin-disp">求人情報<i class="fas fa-angle-right"></i></a></li>
							<li><a href="/nexus/web/jobseeker-list">求職者情報<i class="fas fa-angle-right"></i></a></li>
							<li><a href="/nexus/web/match-disp">マッチング登録<i class="fas fa-angle-right"></i></a></li>
						</ul></li>
					<c:if test="${Staff.authority == 1}"><li><a href="/nexus/web/account-list"><i class="far fa-bookmark"></i>管理</a></li></c:if>
				</ul>
			</nav>
			<div class="user">
				<div class="user__wrapper">
					<div class="user__name">
						<a href="#"><c:out value="${ Staff.name }" /><i class="fas fa-ellipsis-v"></i></a>
						<ul class="drop-menu">
							<li><a href="/nexus/web/logout">ログアウト<i class="fas fa-angle-right"></i></a></li>
						</ul>
					</div>
				</div>
			</div>
		</section>
	</header>
	<main>
	<h2>企業ID情報検索 &darr;</h2>

		<form action="/nexus/web/jobseeker-list" method="post">
			<div class="m_div">
				<p class="m_p">
					企業名かな<br/>
					<input type="text" name="conpany_kana" size=13  value="">
				</p>
				<p class="m_p">
					担当紹介者<br/>
					<input type="text" size=13 name="kyujinno">
					<select name="st_name">
							<option></option>
							<c:forEach var="staff" items="${ st_name }">
								<option value="${ staff.name }"><c:out
										value="${ staff.name }" /></option>
							</c:forEach>
					</select>
				</p>
				<p><input type="submit" class="main-b" name="send" value="検索"></p>
			</div>
		</form>

	<table class="list_table">
		<tr>
			<th></th>
			<th>企業ID</th>
			<th>企業名</th>
			<th>担当紹介者</th>
		</tr>
		<c:forEach var="jobseeker" items="${ list }">
			<tr>
				<td>
					<form action="/nexus/web/jobseeker-info" method="post">		<!-- TODO: ■要変更 -->
						<button class="mini_b mini_b_applilist" name="js_id"
							value="<c:out value="${ jobseeker.id }" />">入力</button>
					</form>
				</td>
				<td><fmt:formatNumber value="${ jobseeker.id }" pattern="00000000"/></td>	<!-- TODO: ■要変更 -->
				<td><c:out value="${ jobseeker.js_name }" /></td>
				<td><c:out value="${ jobseeker.st_name }" /></td>
			</tr>
		</c:forEach>
	</table>

		<h2>求人ID検索 &darr;</h2>
				<form action="/nexus/web/jobseeker-list" method="post">
			<div class="m_div">
				<p class="m_p">
					企業名かな<br/>
					<input type="text" name="conpany_kana" size=13  value="">
				</p>
				<p class="m_p">
					担当紹介者<br/>
					<input type="text" size=13 name="kyujinno">
					<select name="st_name">
							<option></option>
							<c:forEach var="staff" items="${ st_name }">
								<option value="${ staff.name }"><c:out
										value="${ staff.name }" /></option>
							</c:forEach>
					</select>
				</p>
				<p><input type="submit" class="main-b" name="send" value="検索"></p>
			</div>
		</form>

	<table class="list_table">
		<tr>
			<th></th>
			<th>求人ID</th>
			<th>企業名</th>
			<th>担当紹介者</th>
		</tr>
		<c:forEach var="jobseeker" items="${ list }">
			<tr>
				<td>
					<form action="/nexus/web/jobseeker-info" method="post">		<!-- TODO: ■要変更 -->
						<button class="mini_b mini_b_applilist" name="js_id"
							value="<c:out value="${ jobseeker.id }" />">入力</button>
					</form>
				</td>
				<td><fmt:formatNumber value="${ jobseeker.id }" pattern="00000000"/></td>	<!-- TODO: ■要変更 -->
				<td><c:out value="${ jobseeker.js_name }" /></td>
				<td><c:out value="${ jobseeker.st_name }" /></td>
			</tr>
		</c:forEach>
	</table>
		<h2>求職者ID検索 &darr;</h2>

			<form action="/nexus/web/jobseeker-list" method="post">
			<div class="m_div">
				<p class="m_p">
					求職者かな名<br/>
					<input type="text" name="js_kana" size=13  value="">
				</p>
				<p class="m_p">
					担当紹介者<br/>
					<input type="text" size=13 name="kyujinno">
					<select name="st_name">
							<option></option>
							<c:forEach var="staff" items="${ st_name }">
								<option value="${ staff.name }"><c:out
										value="${ staff.name }" /></option>
							</c:forEach>
					</select>
				</p>
				<p><input type="submit" class="main-b" name="send" value="検索"></p>
			</div>
		</form>

	<table class="list_table">
		<tr>
			<th></th>
			<th>求職者ID</th>
			<th>氏名</th>
			<th>担当紹介者</th>
		</tr>
		<c:forEach var="jobseeker" items="${ list }">
			<tr>
				<td>
					<form action="/nexus/web/jobseeker-info" method="post">		<!-- TODO: ■要変更 -->
						<button class="mini_b mini_b_applilist" name="js_id"
							value="<c:out value="${ jobseeker.id }" />">入力</button>
					</form>
				</td>
				<td><fmt:formatNumber value="${ jobseeker.id }" pattern="00000000"/></td>	<!-- TODO: ■要変更 -->
				<td><c:out value="${ jobseeker.js_name }" /></td>
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
