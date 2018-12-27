<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>トップページ</title>
<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=M+PLUS+1p"
	rel="stylesheet">
<link href="../css/bootstrap-reboot.css" rel="stylesheet">
<link href="../css/common.css" rel="stylesheet">
<link href="../css/header.css" rel="stylesheet">
<link href="../css/footer.css" rel="stylesheet">
<link href="../css/stafftop.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/stafftop.js"></script>
</head>
<body>
	<%@ include file="/header.jsp" %>
	<main>

	<div>
		<!--　コンテナー-->
		<div>
			<h2>トップページ</h2>
		</div>
		<!-- 検索メニュ　※URLはダミー　-->
		<div>
			<table class="staff-table">
				<tr>
					<th><label>検索</label></th>
				</tr>
				<tr>
					<td><a href="/nexus/web/company-search">■企業検索</a></td>

				</tr>
				<tr>
					<td><a href="/nexus/web/job-search">■求人検索</a></td>

				</tr>
				<tr>
					<td><a href="/nexus/web/jobseeker-list">■求職者検索</a></td>

				</tr>
				<tr>
					<td><a href="/nexus/web/matching-search">■マッチング検索</a></td>

				</tr>

			</table>
		</div>
		<!-- 登録＆閲覧メニュー -->
		<div>
			<table class="staff-table">
				<tr>
					<th><label>登録＆閲覧</label></th>
				</tr>
				<tr>
					<td><a href="/nexus/web/company-registdisp">■企業登録</a></td>
				</tr>
				<tr>
					<td><a href="/nexus/web/kyujin-disp">■求人登録</a></td>
				</tr>
				<tr>
					<td><a href="/nexus/web/jobseeker-disp">■求職者登録</a></td>
				</tr>
				<tr>
					<td><a href="/nexus/web/matching-registdisp">■マッチング登録</a></td>
				</tr>
			</table>
		</div>

		<!-- 管理メニュー　※管理者以外は表示しない-->
		<c:if test="${Staff.authority == 1}"><div>
			<table class="staff-table">
				<tr>
					<th><label>管理</label></th>
				</tr>
				<tr>
					<td><a href="/nexus/web/account-list">■アカウント</a></td>
				</tr>
			</table>
		</div></c:if>
		<div>
			<div class="clear">
				<form name="logout" method="GET" action="/nexus/web/logout">
					<input class="main-b" type="submit" name="close" value="ログアウト">
				</form>
			</div>
		</div>
		<!--　コンテナ終了-->
	</div>

	</main>
	<footer>
		<small>Copyright(C) 2009有限責任事業組合 大阪職業教育協働機構(A'ワーク創造館) All
			Rights Reserved.</small>
	</footer>
</body>
</html>
