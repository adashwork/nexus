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
<link href="../css/commentregist.css" rel="stylesheet" type="text/css" />

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
</head>
<body>
<div class="wrap1">
		<div class="comment1">
		  	<h3>フリーコメント1</h3>
			   <div class="first">
			  	 <div class="comment-name1">
					<p>企業番号</p>
					<label class="label"></label>
					<input type="text" size="10">
				  </div>
				  <div class="comment-name1">
					<p>求職者ID</p>
					<label class="label"></label>
					<input type="text" size="10">
				  </div>
				  <div class="comment-name1">
					<p>求人番号</p>
					<label class="label"></label>
					<input type="text" size="10">
				  </div>
					<div class="comment-name1">
					<p>マッチングID</p>
					<label class="label"></label>
					<input type="text" size="10">
				  </div>
			  <div class="second">
				  <div class="comment-name2">
					<p>カテゴリー</p>
					<select name="choice">
					<option value="0" selected="selected">選択</option>
					<option value="1">あ</option>
					<option value="2">い</option>
					<option value="3">う</option>
				　 　<option value="3">え</option>
					</select>
				  </div>
				  <div class="comment-name2">
					<p>タイトル</p>
					<label class="label"></label>
					<input type="text">
				  </div>
					<div class="comment-name2">
					<p class="important">重要</p>
					<label class="label"></label>
					<input type="checkbox" >
					</div>
			  <div class="textbox">
				<p>内容</p>
				<label class="label"></label>
				<textarea rows="10" cols="60"></textarea>
			  </div>
	<div class="down">
	 <div class="box">
	  <p>登録ID：</p>
	  <p>更新ID：</p>
	 </div>
	<div class="box">
	  <p>登録日:</p>
	  <p>更新日：</p>
	</div>
  </div>
</div>
<form action="" method="post">
  <input class="main-d" type="button"
							onclick="location.href=''" value="削除">
  <input class="main-d" type="button"
						onclick="location.href=''" value="更新">
  <input class="main-d" type="button"
						onclick="location.href=''" value="戻る">
</form>
</body>
</html>
