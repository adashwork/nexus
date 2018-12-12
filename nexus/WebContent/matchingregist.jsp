<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">

<head>

<meta charset="UTF-8">

<title>マッチング結果・編集・登録</title>
<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=M+PLUS+1p" rel="stylesheet">
<link href="../css/bootstrap-reboot.css" rel="stylesheet">
<link href="../css/common.css" rel="stylesheet">
<link href="../css/header.css" rel="stylesheet">
<link href="../css/footer.css" rel="stylesheet">
<link rel="stylesheet" href="../css/default.css">
<link rel="stylesheet" href="../css/default.date.css">
<!-- <link href="css/ootuka.css" rel="stylesheet"> -->
<link href="../css/matchingregist.css" rel="stylesheet">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/themes/base/jquery-ui.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/i18n/jquery.ui.datepicker-ja.min.js"></script>
<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/Base/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1/i18n/jquery.ui.datepicker-ja.min.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
</head>

<body>

	<header>
 		<section>
  			<h1 class="logo">
				<a href="/nexus/web/staff-top"><img src="../css/TryNexus-Logo.png" width="97" height="70" alt="TryNexus" /></a>
			</h1>

 			<nav>
				<ul class="mainnavi">
					<li><a href="/nexus/web/job-search"><i class="fas fa-home"></i>検索</a></li>
					<li><a href="/nexus/web/jobseeker-list"><i class="fas fa-search"></i>登録&amp;閲覧</a>
						<ul class="drop-menu">
							<li><a href="/nexus/web/kyujin-disp">求人情報<i class="fas fa-angle-right"></i></a></li>
							<li><a href="/nexus/web/jobseeker-list">求職者情報<i class="fas fa-angle-right"></i></a></li>
							<li><a href="/nexus/web/match-disp">マッチング登録<i class="fas fa-angle-right"></i></a></li>
						</ul>
					</li>
					<c:if test="${Staff.authority == 1}">
					<li><a href="/nexus/web/account-list"><i class="far fa-bookmark"></i>管理</a></li></c:if>
				</ul>
			</nav>
			<div class="user">
				<div class="user__wrapper">
					<div class="user__name">
						<a href="#"><c:out value="${ Staff.name }" /><i class="fas fa-ellipsis-v"></i></a>
						<ul class="drop-menu">
							<li><a href="/nexus/web/logout">ログアウト<i class="fas fa-angle-right"></i></a></li>
						</ul>
					</div>
				</div>
			</div>
		</section>
	</header>

	<main> <!-- body部分　-->

		<h2>マッチング結果・編集・登録</h2>		<!-- TODO：結果・編集・登録に表示を変える -->

		<ul>
			<c:forEach var="message" items="${ messages }">
				<li><font color=#F00 size="7"><c:out value="${ message }" /></font></li>
			</c:forEach>
		</ul>

 		<form id="form" method="post" action="">	<!-- TODO:「action」未設定 -->

<!-- テーブル部分　-->


		<table border="0">

			<tr>
				<th>
					マッチングID
				</th>
				<td>
					<input type="hidden" name="no" value="<c:out value="${ matching.id }" />">
						<fmt:formatNumber value="${ matching.id }" pattern="00000000"/>
				</td>
			</tr>
			<tr>
				<th>
					企業ID
				</th>
				<td>
					<!-- TODO: 処理未 -->
				</td>
			</tr>
			<tr>
				<th>
					求人ID
				</th>
				<td>
					<input type="text" name="kyujinno"  value="<c:out value="${ matching.kyujinno }" />">
					<input type="button" value="求人No一覧" onclick="window.open('/nexus/web/job-search')">
				</td>
			</tr>
			<tr>
				<th>
					求職者ID
				</th>
				<td>
					<input type="text" name="jobseekerid"  value="<c:out value="${ matching.jobseekerid }" />">
					<input type="button" value="求職者一覧" onclick="window.open('/nexus/web/jobseeker-list')">
				</td>
			</tr>
			<tr>
				<th>
					職業紹介者ID
				</th>
				<td>
					<input type="text" name="staffid" size="4"
						<c:if test="${ matching.staffid == null }">value="<fmt:formatNumber value="${ Staff.id }" pattern="0000" />"</c:if>
						<c:if test='${ matching.staffid != null }'>value="<fmt:formatNumber value='${ matchinig.staffid }' pattern='0000' />"</c:if>
					>
				</td>
			</tr>
			<tr>
				<th>
					面接日
				</th>
				<td>
					<input type="text" class="datepicker" name="interviewdt"
					value="<fmt:formatDate value="${ matching.interviewdt }" pattern="yyyy-MM-dd"/>">
				</td>
			</tr>
			<tr>
				<th>
					評価						<!-- TODO：採用不採用に変換してOKか -->
				</th>
				<td>
					<input name="assessment" type="radio" value="1"
					<c:if test="${ 1 == matching.assessment }">checked="checked"</c:if>> 採用
					<input name="assessment" type="radio" value="2"
					<c:if test="${ 2 == matching.assessment }">checked="checked"</c:if>> 不採用
					<input name="assessment" type="radio" value="3"
					<c:if test="${ 1 != matching.assessment && 2 != matching.assessment }">checked="checked"</c:if>> 確認中
				</td>
			</tr>
			<tr>
				<th colspan="2">
					フリーワード				<!-- TODO：入力後の処理、未 -->
				</th>
			</tr>
			<tr>
				<td colspan="2">
					<textarea name="note" style="width:100%"></textarea>
				</td>
			</tr>

		</table>

		<button type="button" class="main-b" onClick="location.href='./staff-top'">トップに戻る</button>
		<c:if test="${ matching.id == null }">
				<button type="submit" id="match-regist" class="main-b" onclick="MovePages(this)">登録</button>
		</c:if>
		<c:if test="${ matching.id != null && matching.id != 0 }">
			<button type="submit" id="match-update" class="main-b" onclick="MovePages(this)">更新</button>
		</c:if>

	</form>

	</main>

	<!-- フッター　-->
	<footer>
		<small>Copyright(C) 2009有限責任事業組合 大阪職業教育協働機構(A'ワーク創造館) All Rights Reserved.</small>
	</footer>

</body>
</html>
