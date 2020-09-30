<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>アカウント登録完了</title>
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
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
</head>
<body>
	<%@ include file="/header.jsp" %>
	<main>
	<h2>アカウント登録完了</h2>
	<p>アカウント登録が完了しました。</p>
	<table>
		<tr>
			<th>項目名</th>
			<th></th>
		</tr>
		<tr>
			<td>アカウントID</td>
			<td><fmt:formatNumber
					value="${ Staff.id }" pattern="0000"/></td>
		</tr>
		<tr>
			<td>氏名</td>
			<td><c:out value="${ staff.name }" /></td>
		</tr>
		<tr>
			<td>氏名（かな）</td>
			<td><c:out value="${ staff.kana }" /></td>
		</tr>
		<tr>
			<td>ユーザー権限</td>
			<td><c:if test="${staff.authority == 1}">管理者</c:if> <c:if
					test="${staff.authority == 2}">その他</c:if></td>
		</tr>
		<tr>
			<td>パスワード</td>
			<td>*****</td>
		</tr>
	</table>
	<input class="main-b" type="button" value="アカウント一覧へ戻る"
		onclick="location.href='/nexus/web/account-list'"> </main>
	<footer>
		<small>Copyright(C) 2009有限責任事業組合 大阪職業教育協働機構(A'ワーク創造館) All
			Rights Reserved.</small>
	</footer>
</body>
</html>