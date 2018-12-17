<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>求職者情報登録</title>
</head>
<body>
	<!-- ヘッダー　-->
	<header>
		<section>
			<h1 class="logo">
				<a href="/nexus/web/staff-top"><img
					src="../css/TryNexus-Logo.png" width="97" height="70" alt="TryNexus" /></a>
			</h1>
			</h1>
			<nav>
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
			<div class="user">
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
	<h2>求職者情報登録</h2>
	<div id="job_seeker">
		<ul>
			<c:forEach var="message" items="${ messages }">
				<li><c:out value="${ message }" /></li>
			</c:forEach>
		</ul>

		<form method="post" action="/nexus/web/jobseeker-regist">
			<table width="" border="0">
				<!-- <tr>
			<th width="20%">ID</th>
			<td>
			  <input type="text" name="id" value="<c:out value="${ seeker.id }" />">
				<input type="hidden" name="hiddenid" value="<c:out value="${ seeker.id }" />">
			</td>
		</tr> -->
				<tr>
					<th width="20%">名前</th>
					<td><input type="text" name="name"
						value="<c:out value="${ seeker.name }" />"></td>
				</tr>
				<tr>
					<th>ふりがな</th>
					<td><input type="text" name="kana"
						value="<c:out value="${ seeker.kana }" />"></td>
				</tr>
				<tr>
					<th>生年月日</th>
					<td><input type="text" class="datepicker" name="birthdt"
						value="<c:out value="${ seeker.birthdt }" />"></td>
				</tr>
				<tr>
					<th>性別</th>
					<td><input type="radio" name="sex" value="1" /> 男 <input
						type="radio" name="sex" value="2" /> 女</td>
				</tr>
				<!--<tr>
					<th>年齢</th>
					<td><select name="age">
							<%
								for (int i = 0; i <= 120; i++) {
							%>
							<option value="<%=i%>">
								<%=i%>
							</option>
							<%
								}
							%>
					</select>歳</td>
				</tr>-->
				<tr>
					<th>郵便番号</th>
					<td><input type="text" name="postal"
						value="<c:out value="${ seeker.postal }" />"></td>
				</tr>
				<tr>
					<th>住所</th>
					<td><input type="text" name="address"
						value="<c:out value="${ seeker.address }" />"></td>
				</tr>
				<tr>
					<th>最寄り駅</th>
					<td><input type="text" name="nearstation"
						value="<c:out value="${ seeker.nearstation }" />"></td>
				</tr>
				<tr>
					<th>自宅電話番号</th>
					<td><input type="text" name="phone"
						value="<c:out value="${ seeker.phone }" />"></td>
				</tr>
				<tr>
					<th>携帯電話番号</th>
					<td><input type="text" name="mobile"
						value="<c:out value="${ seeker.mobile }" />"></td>
				</tr>
				<tr>
					<th>配偶者</th>
					<td><input type="radio" name="partner" value="0" checked /> 無
						<input type="radio" name="partner" value="1" /> 有</td>
				</tr>
				<tr>
					<th>扶養人数</th>
					<td><select name="huyou">
							<%
								for (int i = 0; i <= 120; i++) {
							%>
							<option value="<%=i%>">
								<%=i%>
							</option>
							<%
								}
							%>
					</select>人</td>
				</tr>
				<tr>
					<th>学歴</th>
					<td><input type="text" name="education"
						value="<c:out value="${ seeker.education }" />"></td>
				</tr>
				<tr>
					<th>希望職種1</th>
					<td><select name="hopejob1">
							<c:forEach var="job" items="${ Largelist }">
								<option value="${ job.largecd }">${ job.name }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>希望職種2</th>
					<td><select name="hopejob2">
							<c:forEach var="job" items="${ Largelist }">
								<option value="${ job.largecd }">${ job.name }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>希望職種3</th>
					<td><select name="hopejob3">
							<c:forEach var="job" items="${ Largelist }">
								<option value="${ job.largecd }">${ job.name }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>希望業種</th>
					<td><select name="hopejobcategory">
							<c:forEach var="jobcategory" items="${ JCLargelist }">
								<option value="${ jobcategory.largecd }">${ jobcategory.name }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>希望勤務地</th>
					<td><select name="hopeworkplace">
							<c:forEach var="todouhuken" items="${ Todouhukenlist }">
								<option value="${ todouhuken.cd }">${ todouhuken.name }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>希望雇用形態</th>
					<td><input type="radio" name="hopekoyoukeitai" value="1" />
						正社員 <input type="radio" name="hopekoyoukeitai" value="2" /> 正社員以外
						<input type="radio" name="hopekoyoukeitai" value="3" /> 有期雇用派遣 <input
						type="radio" name="hopekoyoukeitai" value="4" /> 無期雇用派遣</td>
				</tr>
				<tr>
					<th>希望勤務日時</th>
					<td><input type="text" name="hopeworkingDate"
						value="<c:out value="${ seeker.hopeworkingDate }" />"></td>
				</tr>
				<tr>
					<th>希望勤務時間(開始)</th>
					<td><input type="text" name="hopebegintime"
						value="<c:out value="${ seeker.hopebegintime }" />"></td>
				</tr>
				<tr>
					<th>希望勤務時間(終了)</th>
					<td><input type="text" name="hopeendtime"
						value="<c:out value="${ seeker.hopeendtime }" />"></td>
				</tr>
				<tr>
					<th>希望月給</th>
					<td><input type="text" name="hopesalary"
						value="<c:out value="${ seeker.hopesalary }" />"></td>
				</tr>
				<tr>
					<th>希望時間給</th>
					<td><input type="text" name="hopejikyu"
						value="<c:out value="${ seeker.hopejikyu }" />"></td>
				</tr>
				<tr>
					<th>その他希望</th>
					<td><textarea rows="4" cols="40" name="hopeetc"
							<c:out value="${ seeker.hopeetc }" />></textarea></td>
				</tr>
				<tr>
					<th>自動車免許</th>
					<td><input type="radio" name="driverlicense" value="0" checked />
						無し <input type="radio" name="driverlicense" value="1" /> 普通 <input
						type="radio" name="driverlicense" value="2" /> AT限定 <input
						type="radio" name="driverlicense" value="3" /> 中型 <input
						type="radio" name="driverlicense" value="4" /> 大型 <input
						type="radio" name="driverlicense" value="5" /> 原付 <input
						type="radio" name="driverlicense" value="6" /> 2種</td>
				</tr>
				<tr>
					<th>その他免許</th>
					<td><textarea rows="4" cols="40" name="licenseetc"
							<c:out value="${ seeker.licenseetc }" />>
			</textarea></td>
				</tr>
				<tr>
					<th>パソコンスキル</th>
					<td><textarea rows="4" cols="40" name="pasokonskill"
							<c:out value="${ seeker.pasokonskill }" />></textarea></td>
				</tr>
				<tr>
					<th>留意点</th>
					<td><textarea rows="4" cols="40" name="caution"
							placeholder="身体上に注意することを書いてください。"
							<c:out value="${ seeker.caution }" />></textarea></td>
				</tr>
				<tr>
					<th>担当職業者紹介者ID</th>
					<td><input type="text" name="tantoustaffid"
						value="<c:out value="${ Staff.id }" />"></td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><input type="text" name="password"
						value="<c:out value="${ seeker.password }" />"></td>
				</tr>
			</table>
			<input class="main-b" type="submit" value="登録">
		</form>
		<form action="/nexus/web/jobseeker-list" method="get">
			<input type="submit" class="main-b" name="send" value="一覧に戻る">
		</form>
	</div>
	</main>
	<!-- フッター　-->
	<footer>
		<small>Copyright(C) 2009有限責任事業組合 大阪職業教育協働機構(A'ワーク創造館) All
			Rights Reserved.</small>
	</footer>

</body>
</html>