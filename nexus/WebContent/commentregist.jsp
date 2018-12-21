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

<style>


</style>
<title>求職者詳細情報</title>
</head>

<body>

<div id="container">
			<h3>フリーコメント1</h3>
				<div class="comment">
				<table class="c1">
					<tr>
					<th>企業番号</th>
					<th>求職者ID</th>
					</tr>
					<tr>
					<td><input type="text" name=""></td>
					<td><input type="text" name=""></td>
					</tr>
					<tr>
					<th>求人番号</th>
					<th>マッチングID</th>
					</tr>
					<tr>
					<td><input type="text" name=""></td>
					<td><input type="text" name=""></td>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th>カテゴリー表示</th>
					<th>タイトル表示</th>
				</tr>
				<tr>
					<td><select name="hopejobcategory">
					<option value="">-</option>
					<option value="">a</option>
					<option value="">b</option>
					<option value="">c</option>
					<option value="">d</option>
					</select></td>
				 	 <td><input type="text" name=""></td>
				 	 <td><input type="checkbox" name="">重要</td>
				</tr>
				</tbody>
				<tfoot>
				<tr>
					<th>カテゴリー表示</th>
				</tr>
				<tr>
					<td><textarea rows="3" cols="40" name="career"><c:out
								value="${ info.careea }" /></textarea></td>
				</tr>
				</tfoot>
		</table>
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
<form method="get" action="/nexus/web/account-edit">
		<input class="main-b" type="button"
							onclick="location.href=''" value="削除">
		<input class="main-b" type="button"
							onclick="location.href=''" value="更新">
		<input class="main-b" type="button"
							onclick="location.href=''" value="戻る">
		<input class="main-b" type="button"
							onclick="location.href=''" value="登録">
</form>

	<!-- フッター　-->
	<footer>
		<small>Copyright(C) 2009有限責任事業組合 大阪職業教育協働機構(A'ワーク創造館) All
			Rights Reserved.</small>
	</footer>


</body>
</html>