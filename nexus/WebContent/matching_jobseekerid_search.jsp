<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">

<head>

<meta charset="UTF-8">

<title>マッチング　求職者ID検索</title>
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

		<h2>求職者ID検索 &darr;</h2>

			<form action="/nexus/web/matching-jobseekerid-search" method="post">
			<div class="m_div">
				<p class="m_p">
					<input type="hidden" name="js_id" value="<c:out value="${ param.js_id }" />">
				</p>
				<p class="m_p">
					求職者名かな<br/>
					<input type="text" name="js_kana" value="<c:out value="${ param.js_kana }" />">
				</p>
				<p class="m_p">
					担当紹介者<br/>
					<select name="st_name">
							<option value=""></option>
							<c:forEach var="staff" items="${ st_name }">
							<option value="<c:out value="${ staff.name}" />" <c:if test="${staff.name  == param.st_name }">
									selected
									</c:if>><c:out
								value="${ staff.name }" /></option>
					</c:forEach>
					</select>
				</p>
				<p class="m_right"><input type="submit" class="main-b" name="send" value="検索"></p>
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
					<input name="id_name_input" type="button" value="入力" />
					<input type="hidden" value="<c:out value="${ jobseeker.id }" />" />
					<input type="hidden" value="<c:out value="${ jobseeker.js_name }" />" />
				</td>
				<td><c:out value="${ jobseeker.id }" /></td>
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
