<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>アカウント一覧</title>
<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=M+PLUS+1p"
	rel="stylesheet">
<link href="../css/bootstrap-reboot.css" rel="stylesheet">
<link href="../css/common.css" rel="stylesheet">
<link href="../css/header.css" rel="stylesheet">
<link href="../css/footer.css" rel="stylesheet">
<link href="../css/account.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
</head>
<body>
	<%@ include file="/header.jsp" %>
	<main>
	<h2>アカウント一覧</h2>
	<input class="main-b newbutton" type="button" value="新規登録"
		onclick="location.href='/nexus/web/account-disp'">
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
					<form method="get" action="/nexus/web/account-edit">
						<input type="hidden" name="id" value="${ account.id }"> <input
							class="main-b" type="submit" value="編集">
					</form>
				</td>
				<td><fmt:formatNumber value="${ account.id }" pattern="0000"/></td>
				<td><c:out value="${ account.name }" /></td>
				<td><c:out value="${ account.kana }" /></td>
				<td><c:if test="${account.authority == 1}">管理者</c:if> <c:if
						test="${account.authority == 2}">その他</c:if></td>
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
