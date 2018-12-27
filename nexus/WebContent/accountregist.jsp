<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>アカウント登録</title>
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
	<%@ include file="/header.jsp" %>
	<main>
	<h2>アカウント登録</h2>
	<ul>
		<p style="color: red">
			<c:forEach var="message" items="${ messages }">
				<li><c:out value="${ message }" /></li>
			</c:forEach>
		</p>
	</ul>
	<form action="/nexus/web/account-regist" method="post">
		<table>
			<tr>
				<th>項目名</th>
				<th></th>
			</tr>
			<tr>
				<td>アカウントID</td>
				<td><input type="hidden" name="hiddenid"
					value="<fmt:formatNumber
					value="${ staff.id }" pattern="0000"/>" required></td>
			</tr>
			<tr>
				<td>氏名</td>
				<td><input type="text" name="name"
					value="<c:out value="${ staff.name }"/>" required></td>
			</tr>
			<tr>
				<td>氏名（かな）</td>
				<td><input type="text" name="kana"
					value="<c:out value="${ staff.kana }"/>" required></td>
			</tr>
			<tr>
				<td>ユーザー権限</td>
				<td><input type="radio" name="kengen" value="1">管理者 <input
					type="radio" name="kengen" value="2">その他</td>
			</tr>
			<tr>
				<td>パスワード</td>
				<td><input type="password" name="password" required></td>
			</tr>
		</table>
		<input class="main-b" type="button"
			onclick="location.href='/nexus/web/account-list'" value="戻る">
		<input class="main-b" type="submit" value="登録">
	</form>
	</main>
	<footer>
		<small>Copyright(C) 2009有限責任事業組合 大阪職業教育協働機構(A'ワーク創造館) All
			Rights Reserved.</small>
	</footer>
</body>
</html>
