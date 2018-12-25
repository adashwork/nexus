<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<script src="build/kalendae.standalone.js" type="text/javascript"
	charset="utf-8"></script>
<link rel="stylesheet" href="css/kalendae.css" type="text/css" />
<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css"
	rel="stylesheet" type="text/css" />
<link href="https://fonts.googleapis.com/css?family=M+PLUS+1p"
	rel="stylesheet" type="text/css" />
<link href="../css/bootstrap-reboot.css" rel="stylesheet"
	type="text/css" />
<link href="../css/common.css" rel="stylesheet" type="text/css" />
<link href="../css/header.css" rel="stylesheet" type="text/css" />
<link href="../css/footer.css" rel="stylesheet" type="text/css" />
<link href="../css/job_seeker.css" rel="stylesheet" type="text/css" />
<link href="../css/applicant_regist.css" rel="stylesheet" type="text/css" />
<link href="../css/commentserch.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/themes/base/jquery-ui.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/i18n/jquery.ui.datepicker-ja.min.js"></script>
<link rel="stylesheet"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/Base/jquery-ui.css">

<script src="js/kalendae.standalone.js" type="text/javascript" charset="utf-8"></script>
<script src="https://ajaxzip3.github.io/ajaxzip3.js" charset="UTF-8"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1/i18n/jquery.ui.datepicker-ja.min.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/applicantregist_tab.js"></script>

<title>求職者詳細情報</title>
</head>
<body>
<form id="form" method="get" action="">
<c:forEach var="comment" items="${ commentlist }">
<div id="wrap">
  <div class="comment">
		<h3>フリーコメント1</h3>
		<p>タイトル<p>
		<input class="comment-title" type="text" name="title" value="<c:out value="${ comment.title }"/>" >
		<p>内容<p>
		<textarea rows="4" cols="50" wrap="hard"><c:out value="${ comment.note }"/></textarea>
	</div>
  <div class="down">
		<div class="box">
			<p>登録ID：<c:out value="${ comment.createUserId }" /></p>
			<p>更新ID：<c:out value="${ comment.updateUserId }" /></p>
	  	</div>
 		<div class="box">
			<p>登録日:<c:out value="${ comment.createDt }" /></p>
		  	<p>更新日:<c:out value="${ comment.updateDt }" /></p>
	    </div>
  </div>

	<input type="hidden" name="commentid" value="<c:out value="${ comment.id }" />">
		<input class="main-b" type="submit" id="comment-disp"
							onclick="MovePages(this)" value="編集">

</c:forEach>
</form>
	<footer>
		<small>Copyright(C) 2009有限責任事業組合 大阪職業教育協働機構(A'ワーク創造館) All
			Rights Reserved.</small>
	</footer>
</body>
</html>