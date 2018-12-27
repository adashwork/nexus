<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">

<head>

<meta charset="UTF-8">

<title>マッチング　求人ID検索</title>
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
<script type="text/javascript" src="../js/matching_idsearch.js"></script>
</head>

<body>

	<header>
		<section>
			<h1 class="logo">
				<a href="#" onClick="window.close(); return false;">
					<img src="../css/TryNexus-Logo.png" width="97" height="70" alt="TryNexus" /></a>
			</h1>
			<nav>
				<ul class="mainnavi">
					<li><a href="#" onClick="window.close(); return false;">検索ウィンドウを閉じる</a></li>
				</ul>
			</nav>
		</section>
	</header>
	<main>

	<h2>求人No検索 &darr;</h2>
		<form action="/nexus/web/matching-kyujinid-search" method="get">
			<div class="m_div">
				<p class="m_p">
					職種をフリーワード検索<br/>
					<textarea class="word" name="job" cols="15" rows="1"
					placeholder="職種名より検索します。" tabindex="1"></textarea>
				</p>
				<p class="m_p">
					業種をフリーワード検索<br/>
				<textarea class="word" name="jobcategory" cols="15" rows="1"
					placeholder="業種名より検索します。" tabindex="1"></textarea>
				</p>
				<p class="m_right"><input class="submit_button" type="submit" value="検索" /></p>
			</div>
		</form>

	<table class="list_table">
		<tr>
			<th></th>
			<th>求人No</th>
			<th>職種</th>
		</tr>
				<c:forEach var="kyujin" items="${ kyujinlist }">
					<tr>
						<td>
							<input name="id_name_kyujin" type="button" value="入力" />
							<input type="hidden" value="<c:out value="${ kyujin.no }" />" />
							<input type="hidden" value="<c:out value="${kyujin.job }" />" />
						</td>
						<td><c:out value="${ kyujin.no }" /></td>
						<td><c:out value="${ kyujin.job }" /></td>
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
