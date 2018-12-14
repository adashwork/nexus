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
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1/i18n/jquery.ui.datepicker-ja.min.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<style>
#job_seeker {
	width: 980px;
	margin-top: 0;
	margin-bottom: 50px;
	margin-right: auto;
	margin-left: auto;
	clear: both;
}

#job_seeker table th {
	letter-spacing: 0.3em;
}

#job_seeker table td {
	text-align: left;
}
</style>
<title>企業情報</title>
</head>
<body>
	<!-- ヘッダー　-->
	<header>
		<section>
			<h1 class="logo">
				<a href="/nexus/web/staff-top"><img
					src="../css/TryNexus-Logo.png" width="97" height="70" alt="TryNexus" /></a>
			</h1>
/*			<nav>
				<ul class="mainnavi">
					<li><a href="/nexus/web/job-search"><i class="fas fa-home"></i>検索</a></li>
					<li><a href="/nexus/web/jobseeker-list"><i
							class="fas fa-search"></i>登録&amp;閲覧</a>
						<ul class="drop-menu">
						<li><a href="/nexus/web/kyujin-disp">求人情報<i
									class="fas fa-angle-right"></i></a></li>
							<li><a href="/nexus/web/jobseeker-list">求職者情報<i
									class="fas fa-angle-right"></i></a></li>
							<li><a href="/nexus/web/match-disp">マッチング登録<i
									class="fas fa-angle-right"></i></a></li>
						</ul></li>
					<c:if test="${Staff.authority == 1}"><li><a href="/nexus/web/account-list"><i
							class="far fa-bookmark"></i>管理</a></li></c:if>
				</ul>
			</nav>
*/			<div class="user">
				<div class="user__wrapper">
					<div class="user__name">
						<a href="#"><c:out value="${ Staff.name }" /><i
							class="fas fa-ellipsis-v"></i></a>
						<ul class="drop-menu">
							<li><a href="/nexus/web/logout">ログアウト<i
									class="fas fa-angle-right"></i></a></li>
						</ul>
					</div>
				</div>
			</div>
		</section>
	</header>
	<main>
	<h2>企業情報</h2>
	<ul>
		<c:forEach var="message" items="${ messages }">
			<li><c:out value="${ message }" /></li>
		</c:forEach>
	</ul>
	<div id="job_seeker">
		<form action="/nexus/web/jobseeker-edit" method="post">
			<table border="0">

				<tr>
					<th width="20%">ID</th>
					<td><input type="hidden" name="id"
						value="<c:out value="${ info.id }" />">
				<fmt:formatNumber value="${ info.id }" pattern="00000000"/></td>
				</tr>
				<tr>
					<th>法人番号</th>
					<td><input type="text" name="corporatenumber" value="${ info.corporatenumber }" /></td>
				</tr>
				<tr>
					<th>事業所番号</th>
					<td><input type="text" name="companyno" value="${ info.companyno }" /></td>
				</tr>
                <tr>
					<th>事業所名</th>
					<td><input type="text" name="companyname" value="${ info.companyname }" /></td>
				</tr>
				<tr>
					<th>事業所名(カナ)</th>
					<td><input type="text" name="companykana" value="${ info.companykana }" /></td>
				</tr>
				<tr>
					<th>事業所郵便番号</th>
					<td><input type="text" name="companypostal" value="${ info.companypostal }" /></td>
				</tr>
                <tr>
					<th>事業所所在地</th>
					<td><input type="text" name="companyplace"
						value="${ info.companyplace }" /></td>
				</tr>
                <tr>
					<th>最寄駅</th>
					<td><input type="text" name="nearstation"
						value="${ info.nearstation }" /></td>
				</tr>
                <tr>
					<th>事業所URL</th>
					<td><input type="text" name="companyurl"
						value="${ info.companyurl }" /></td>
				</tr>
                <tr>
					<th>産業小分類コード</th>
					<td><input type="text" name="jobcategorysmallcd" value="${ info.jobcategorysmallcd }" /></td>
				</tr>
				<tr>
					<th>産業大分類コード</th>
					<td><input type="text" name="jobcategorylargecd" value="${ info.jobcategorylargecd }" /></td>
				</tr>
                <tr>
					<th>資本金</th>
					<td><input type="text" name="capital" value="${ info.capital }" /></td>
				</tr>
				<tr>
				<th>従業員数</th>
				<td>
				<select name=employees>
				<opition value="1">10名以下</opition>
				<opition value="2">100名以下</opition>
				<opition value="3">1000名以下</opition>
				<opition value="4">10000名以下</opition>
				<opition value="5">10000名以上</opition>
				</select>
				</td>
				</tr>
				<tr>
					<th>創業設立年</th>
					<td><input type="text" name="establishdt" value="${ info.establishdt }" /></td>
				</tr>
				<tr>
					<th>担当者課係名/役職名</th>
					<td><input type="text" name="tantouyakushoku" value="${ info.tantouyakushoku }" /></td>
				</tr>
				<tr>
					<th>担当者名</th>
					<td><input type="text" name="tantou" value="${ info.tantou }" /></td>
				</tr>
				<tr>
					<th>担当者名(かな)</th>
					<td><input type="text" name="tantoukana" value="${ info.tantoukana }" /></td>
				</tr>
				<tr>
					<th>担当者TEL</th>
					<td><input type="text" name="tantoutel" value="${ info.tantoutel }" /></td>
				</tr>
				<tr>
					<th>担当者FAX</th>
					<td><input type="text" name="tantoufax" value="${ info.tantoufax }" /></td>
				</tr>
				<tr>
					<th>担当者email</th>
					<td><input type="text" name="tantouemail" value="${ info.tantouemail }" /></td>
				</tr>
				<tr>
					<th>担当者備考</th>
					<td><textarea rows="1" cols="40" name="tantounote"><c:out
								value="${ info.tantounote }" /></textarea></td>
				</tr>
				<tr>
					<th>担当開拓者ID</th>
					<td><input type="text" name="tantoustaff_id" value="${ info.tantoustaff_id }" /></td>
				</tr>
				<tr>
				<th>営業評価ランクABC</th>
				<td>
				<select name="salesrank">
				<opition value="1">A</opition>
				<opition value="2">B</opition>
				<opition value="3">C</opition>
				</select>
				</td>
				</tr>
				<tr>
					<th>営業備考</th>
					<td><textarea rows="4" cols="40" name="salesnote"><c:out
								value="${ info.salesnote }" /></textarea></td>
				</tr>

				<tr>
					<th>フリーコメント</th>
				</tr>

				<tr>
					<td><textarea rows="1" cols="20" name="title"><c:out
								value="${ info.title }" /></textarea></td>


				</tr>









			</table>
			<input type="submit" value="更新" class="main-b">
		</form>
		<input class="main-b" type="button"
			onclick="location.href='/nexus/web/jobseeker-list'" value="一覧に戻る">
	</div>
	</main>
	<!-- フッター　-->
	<footer>
		<small>Copyright(C) 2009有限責任事業組合 大阪職業教育協働機構(A'ワーク創造館) All
			Rights Reserved.</small>
	</footer>

	<script src="js/kalendae.standalone.js" type="text/javascript"
		charset="utf-8"></script>
</body>
</html>