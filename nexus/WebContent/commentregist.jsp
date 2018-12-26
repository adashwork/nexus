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
<script type="text/javascript" src="../js/comment.js"></script>

<style>


</style>
<title>求職者詳細情報</title>
</head>

<body>
	<form id="form" method="post" action="">
		<div id="container">
			<!-- 必要なパラメータをhiddenで持たせる -->
			<input type="hidden" name="commentid" value="<c:out value="${ comment.id }" />">
			<h3>フリーコメント1</h3>
			<ul>
				<c:forEach var="message" items="${ messages }">
					<li><c:out value="${ message }" /></li>
				</c:forEach>
			</ul>
			<div class="comment">
				<table class="c1">
					<tr>
						<th>企業番号</th>
						<th>求職者ID</th>
					</tr>
					<tr>
						<td><input type="text" name="companyno"
							value="<c:out value="${ comment.companyNo }" />"></td>
						<td><input type="text" name="jobseekerid"
							value="<c:out value="${ comment.jobSeekerId }" />"></td>
					</tr>
					<tr>
						<th>求人番号</th>
						<th>マッチングID</th>
					</tr>
					<tr>
						<td><input type="text" name="kyujinno"
							value="<c:out value="${ comment.kyujinNo }" />"></td>
						<td><input type="text" name="matchid"
							value="<c:out value="${ comment.matchId }" />"></td>
					</tr>
					</thead>
					<tbody>
						<tr>
							<th>カテゴリー表示</th>
							<th>タイトル表示</th>
						</tr>
						<tr>
							<td>
							<!-- genreの値でselectedを分岐させる -->
								<select name="genre">
									<c:choose>
										<c:when test="${ comment.genre == 1 }">
											<option value="0">えらんでください</option>
											<option value="1" selected>求職者</option>
											<option value="2">企業</option>
											<option value="3">求人</option>
											<option value="4">マッチング</option>
											<option value="9">その他</option>
										</c:when>
										<c:when test="${ comment.genre == 2 }">
											<option value="0">えらんでください</option>
											<option value="1">求職者</option>
											<option value="2" selected>企業</option>
											<option value="3">求人</option>
											<option value="4">マッチング</option>
											<option value="9">その他</option>
										</c:when>
										<c:when test="${ comment.genre == 3 }">
											<option value="0">えらんでください</option>
											<option value="1">求職者</option>
											<option value="2">企業</option>
											<option value="3" selected>求人</option>
											<option value="4">マッチング</option>
											<option value="9">その他</option>
										</c:when>
										<c:when test="${ comment.genre == 4 }">
											<option value="0">えらんでください</option>
											<option value="1">求職者</option>
											<option value="2">企業</option>
											<option value="3">求人</option>
											<option value="4" selected>マッチング</option>
											<option value="9">その他</option>
										</c:when>
										<c:when test="${ comment.genre == 9 }">
											<option value="0">えらんでください</option>
											<option value="1">求職者</option>
											<option value="2">企業</option>
											<option value="3">求人</option>
											<option value="4">マッチング</option>
											<option value="9" selected>その他</option>
										</c:when>
										<c:otherwise>
											<option value="0">えらんでください</option>
											<option value="1" selected>求職者</option>
											<option value="2">企業</option>
											<option value="3">求人</option>
											<option value="4">マッチング</option>
											<option value="9">その他</option>
										</c:otherwise>
									</c:choose>
								</select>
							</td>
							<td><input type="text" name="title"
								value="<c:out value="${ comment.title }" />"></td>
							<td>
								<c:choose>
									<c:when test="${ comment.important == 1 }">
										<input type="checkbox" name="important" value="1" chekced>重要
									</c:when>
									<c:otherwise>
										<input type="checkbox" name="important" value="1">重要
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<th>内容</th>
						</tr>
						<tr>
							<td>
								<textarea rows="3" cols="40" name="note"><c:out value="${ comment.note }" /></textarea>
							</td>
						</tr>
					</tfoot>
				</table>
				<div class="down">
					<div class="box">
						<p>
							登録ID：
							<c:out value="${ comment.createUserId }" />
						</p>
						<p>
							更新ID：
							<c:out value="${ comment.updateUserId }" />
						</p>
					</div>
					<div class="box">
						<p>
							登録日:
							<c:out value="${ comment.createDt }" />
						</p>
						<p>
							更新日:
							<c:out value="${ comment.createDt }" />
						</p>
					</div>
				</div>
			</div>

			<c:if test="${ !empty comment.id }">
				<input class="main-b"type="submit" id="comment-update" onclick="MovePages(this)"value="更新">
				<input class="main-b" type="submit" id="comment-delete"onclick="MovePages(this)" value="削除">
			</c:if>
			<c:if test="${ empty comment.id }">
				<input class="main-b" type="submit" id="comment-regist" onclick="MovePages(this)" value="登録">
			</c:if>
			<input class="main-b" type="button" id="comment-search" onclick="CloseWindow()" value="戻る">

		</form>

	<!-- フッター　-->
	<footer>
		<small>Copyright(C) 2009有限責任事業組合 大阪職業教育協働機構(A'ワーク創造館) All
			Rights Reserved.</small>
	</footer>


</body>
</html>