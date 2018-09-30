<!-- @author ??? & pgjavaAT
	18/09/30 閉じるボタンをトップへ戻るに変更
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>求人登録</title>
<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=M+PLUS+1p"
	rel="stylesheet">
<link href="../css/bootstrap-reboot.css" rel="stylesheet">
<link href="../css/common.css" rel="stylesheet">
<link href="../css/header.css" rel="stylesheet">
<link href="../css/footer.css" rel="stylesheet">
<link href="../css/job_regist.css" rel="stylesheet" type="text/css" />

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
</head>
<body>

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
					<li><a href="/nexus/web/account-list"><i
							class="far fa-bookmark"></i>管理</a></li>
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
	<form id="form" method="post" action="">
		<div id="job_regist">
			<h2>求人登録</h2>


			<div id="message">

				<ul>
					<c:forEach var="message" items="${ messages }">
						<li><c:out value="${ message }" /></li>
					</c:forEach>
				</ul>

			</div>


			<table>
				<tr>
					<th>求人No.</th>
					<td><input type="hidden" name="no"
						value="<c:out value="${ kyujin.no }" />"><c:out value="${ kyujin.no }" /></td>
				</tr>
				<tr>
					<th>受付年月日</th>
					<td><input type="text" class="datepicker" name="receptiondt"
						value="<fmt:formatDate value="${ kyujin.receptiondt }" pattern="yyyy-MM-dd"/>"
						size="10" maxlength="10" tabindex="2"></td>
				</tr>
				<tr>
					<th>求人有効年月日</th>
					<td><input type="text" class="datepicker" name="perioddt"
						value="<fmt:formatDate value="${ kyujin.perioddt }" pattern="yyyy-MM-dd"/>"
						size="10" maxlength="10" tabindex="3"></td>
				</tr>
				<tr>
					<th>事業所番号</th>
					<td><input type="text" name="companyno"
						value="<c:out value="${ kyujin.companyno }" />" size="20"
						maxlength="13" tabindex="4"></td>
				</tr>

				<!-- 1やりたかったメモ：一行テキストボックスはenter押すとすぐに登録なので
			submit変更かjsとかで制御、日本語入力オン-に-->

				<tr>
					<th>産業大分類コード</th>
					<td><select name="jobcategorylargecd" tabindex="5">
							<option></option>
							<c:forEach var="jobcategory" items="${ JCLargelist }">
								<option value="${ jobcategory.largecd }">${ jobcategory.name }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>産業細分類コード</th>
					<td><select name="jobcategorysmallcd" tabindex="6">
							<option></option>
							<c:forEach var="jobcategory" items="${ JCSmalllist }">
								<option value="${ jobcategory.smallcd }">${ jobcategory.name }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>事業所名（半角ｶﾅ）</th>
					<td><input type="text" name="companykana"
						value="<c:out value="${ kyujin.companykana }" />" size="40"
						maxlength="54" tabindex="7"></td>
				</tr>
				<tr>
					<th>事業所名</th>
					<td><input type="text" name="companyname"
						value="<c:out value="${ kyujin.companyname }" />" size="40"
						maxlength="60" tabindex="8"></td>
				</tr>
				<tr>
					<th>事業所郵便番号</th>
					<td><input type="text" name="companypostal"
						value="<c:out value="${ kyujin.companypostal }" />" size="8"
						maxlength="8" tabindex="9"></td>
				</tr>
				<tr>
					<th>事業所所在地</th>
					<td><input type="text" name="companyplace"
						value="<c:out value="${ kyujin.companyplace }" />" size="40"
						maxlength="75" tabindex="10"></td>
				</tr>
				<tr>
					<th>事業所URL</th>
					<td><input type="text" name="companyurl"
						value="<c:out value="${ kyujin.companyurl }" />" size="40"
						maxlength="100" tabindex="11"></td>
				</tr>
				<tr>
					<th>創業設立年</th>
					<td><input type="number" name="establishdt"
						value="<c:out value="${ kyujin.establishdt }" />" size="8"
						maxlength="4" tabindex="12">年</td>
				</tr>
				<tr>
					<th>資本金</th>
					<td><input type="number" name="capital"
						value="<c:out value="${ kyujin.capital }" />" maxlength="16"
						tabindex="13">円</td>
				</tr>
				<tr>
					<th>会社の特徴</th>
					<td><textarea name="companyfeature" rows="3" cols="40"
							tabindex="14">
							<c:out value="${ kyujin.companyfeature }" /></textarea></td>
				</tr>
				<tr>
					<th>職種大分類コード１</th>
					<td><select name="joblargecd1" tabindex="15">
							<option></option>
							<c:forEach var="job" items="${ Largelist }">
								<option value="${ job.largecd }">${ job.name }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>職種細分類コード１</th>
					<td><select name="jobsmallcd1" tabindex="16">
							<option></option>
							<c:forEach var="job" items="${ Smalllist }">
								<option value="${ job.smallcd }">${ job.name }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>職種大分類コード2</th>
					<td><select name="joblargecd2" tabindex="17">
							<option></option>
							<c:forEach var="job" items="${ Largelist }">
								<option value="${ job.largecd }">${ job.name }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>職種細分類コード2</th>
					<td><select name="jobsmallcd2" tabindex="18">
							<option></option>
							<c:forEach var="job" items="${ Smalllist }">
								<option value="${ job.smallcd }">${ job.name }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>職種大分類コード3</th>
					<td><select name="joblargecd3" tabindex="19">
							<option></option>
							<c:forEach var="job" items="${ Largelist }">
								<option value="${ job.largecd }">${ job.name }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>職種細分類コード3</th>
					<td><select name="jobsmallcd3" tabindex="20">
							<option></option>
							<c:forEach var="job" items="${ Smalllist }">
								<option value="${ job.smallcd }">${ job.name }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>就業場所郵便番号</th>
					<td><input type="text" name="postal"
						value="<c:out value="${ kyujin.postal }" />" size="10"
						maxlength="8" tabindex="21"></td>
				</tr>

				<tr>
					<th>就業場所都道府県</th>
					<td><select name="adresscd" tabindex="22">
							<option></option>
							<c:forEach var="todouhuken" items="${ list }">
								<option value="${ todouhuken.cd }">${ todouhuken.name }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>就業場所住所</th>
					<td><input type="text" name="address"
						value="<c:out value="${ kyujin.address }" />" size="40"
						maxlength="90" tabindex="23"></td>
				</tr>
				<tr>
					<th>就業場所最寄り駅</th>
					<td><input type="text" name="nearstation"
						value="<c:out value="${ kyujin.nearstation }" />" size="40"
						maxlength="30" tabindex="24"></td>
				</tr>
				<tr>
					<th>職種</th>
					<td><input type="text" name="job"
						value="<c:out value="${ kyujin.job }" />" size="40" maxlength="28"
						tabindex="25"></td>
				</tr>
				<tr>
					<th>派遣/請負</th>
					<td><input type="radio" name="hakencd" value="1"
						<c:if test="${1 == kyujin.hakencd}" >checked="checked"</c:if>
						tabindex="26"> 派遣 <input type="radio" name="hakencd"
						value="2"
						<c:if test="${2 == kyujin.hakencd}" >checked="checked"</c:if>
						tabindex="27"> 請負 <input type="radio" name="hakencd"
						value="3"
						<c:if test="${1 != kyujin.hakencd && 2 != kyujin.hakencd }" >checked="checked"</c:if>
						tabindex="28"> その他</td>

				</tr>
				<tr>
					<th>仕事の内容</th>
					<td><textarea name="detail" rows="3" cols="40" tabindex="29"><c:out
								value="${ kyujin.detail }" /></textarea></td>
				</tr>
				<tr>
					<th>雇用形態</th>
					<td><select name="koyoukeitaicd" tabindex="30">
							<option></option>
							<option value="1"
								<c:if test="${1 == kyujin.koyoukeitaicd}">selected="selected"</c:if>>正社員</option>
							<option value="2"
								<c:if test="${2 == kyujin.koyoukeitaicd}">selected="selected"</c:if>>正社員以外</option>
							<option value="3"
								<c:if test="${3 == kyujin.koyoukeitaicd}">selected="selected"</c:if>>有期雇用派遣</option>
							<option value="4"
								<c:if test="${4 == kyujin.koyoukeitaicd}">selected="selected"</c:if>>無期雇用派遣</option>
							<option value="5"
								<c:if test="${5 == kyujin.koyoukeitaicd}">selected="selected"</c:if>>パート労働者</option>
							<option value="6"
								<c:if test="${6 == kyujin.koyoukeitaicd}">selected="selected"</c:if>>有期派遣パート</option>
							<option value="7"
								<c:if test="${7 == kyujin.koyoukeitaicd}">selected="selected"</c:if>>無期派遣パート</option>
					</select></td>
				</tr>
				<tr>
					<th>雇用期間の定め</th>
					<td><input type="radio" name="koyoukikan" value="1"
						<c:if test="${1 == kyujin.koyoukikan}">checked="checked"</c:if>
						tabindex="31"> 有り <input type="radio" name="koyoukikan"
						value="2"
						<c:if test="${1 != kyujin.koyoukikan}">checked="checked"</c:if>
						tabindex="32"> 無し</td>
				</tr>
				<tr>
					<th>雇用期間開始年月日</th>
					<td><input type="text" class="datepicker"
						name="koyoukikankaishi"
						value="<fmt:formatDate value="${ kyujin.koyoukikankaishi }" pattern="yyyy-MM-dd"/>"
						size="10" maxlength="10" tabindex="33"></td>
				</tr>
				<tr>
					<th>雇用期間終了年月日</th>
					<td><input type="text" class="datepicker"
						name="koyoukikanowari"
						value="<fmt:formatDate value="${ kyujin.koyoukikanowari }" pattern="yyyy-MM-dd"/>"
						size="10" maxlength="10" tabindex="34"></td>
				</tr>
				<tr>
					<th>学歴</th>
					<td><input type="text" name="education"
						value="<c:out value="${ kyujin.education }" />" size="40"
						maxlength="64" tabindex="35"></td>
				</tr>
				<tr>
					<th>必要な経験等</th>
					<td><input type="text" name="experience"
						value="<c:out value="${ kyujin.experience }" />" size="40"
						maxlength="84" tabindex="36"></td>
				</tr>
				<tr>
					<th>必要な免許・資格等</th>
					<td><input type="text" name="license"
						value="<c:out value="${ kyujin.license }" />" size="40"
						maxlength="84" tabindex="37"></td>
				</tr>
				<tr>
					<th>年齢制限・下限</th>
					<td><input type="number" name="agemin"
						value="<c:out value="${ kyujin.agemin }" />" size="8"
						maxlength="3" tabindex="38">歳</td>
				</tr>
				<tr>
					<th>年齢制限・上限</th>
					<td><input type="number" name="agemax"
						value="<c:out value="${ kyujin.agemax }" />" size="8"
						maxlength="3" tabindex="39">歳</td>
				</tr>
				<tr>
					<th>基本給下限</th>
					<td><input type="number" name="salarymin"
						value="<c:out value="${ kyujin.salarymin }" />" size="10"
						maxlength="7" tabindex="40">円</td>
				</tr>
				<tr>
					<th>基本給上限</th>
					<td><input type="number" name="salarymax"
						value="<c:out value="${ kyujin.salarymax }" />" size="10"
						maxlength="7" tabindex="41">円</td>
				</tr>
				<tr>
					<th>賃金形態</th>
					<td><input type="radio" name="salaryformcd" value="1"
						<c:if test="${1 == kyujin.salaryformcd}">checked="checked"</c:if>
						tabindex="42"> 月給 <input type="radio" name="salaryformcd"
						value="3"
						<c:if test="${3 == kyujin.salaryformcd}">checked="checked"</c:if>
						tabindex="43"> 日給 <input type="radio" name="salaryformcd"
						value="4"
						<c:if test="${4 == kyujin.salaryformcd}">checked="checked"</c:if>
						tabindex="44"> 時間給 <input type="radio" name="salaryformcd"
						value="5"
						<c:if test="${5 == kyujin.salaryformcd}">checked="checked"</c:if>
						tabindex="45"> 年棒 <input type="radio" name="salaryformcd"
						value="6"
						<c:if test="${1 != kyujin.salaryformcd && 3 != kyujin.salaryformcd &&
						 4 != kyujin.salaryformcd && 5 != kyujin.salaryformcd}">checked="checked"</c:if>
						tabindex="46"> その他</td>
				</tr>
				<tr>
					<th>就業時間・始業</th>
					<td><input type="number" name="begintime"
						value="<c:out value="${ kyujin.begintime }" />" size="8"
						maxlength="4" tabindex="47"></td>
				</tr>
				<tr>
					<th>就業時間・終業</th>
					<td><input type="number" name="endtime"
						value="<c:out value="${ kyujin.endtime }" />" size="8"
						maxlength="4" tabindex="48"></td>
				</tr>
				<tr>
					<th>選考担当者課係名/役職名</th>
					<td><input type="text" name="tantouyakushoku"
						value="<c:out value="${ kyujin.tantouyakushoku }" />" size="30"
						maxlength="28" tabindex="49"></td>
				</tr>
				<tr>
					<th>選考担当者名（ひらがな）</th>
					<td><input type="text" name="tantoukana"
						value="<c:out value="${ kyujin.tantoukana }" />" size="30"
						maxlength="28" tabindex="50"></td>
				</tr>
				<tr>
					<th>選考担当者名</th>
					<td><input type="text" name="tantou"
						value="<c:out value="${ kyujin.tantou }" />" size="30"
						maxlength="14" tabindex="51"></td>
				</tr>
				<tr>
					<th>担当職業紹介者ID</th>
					<td><input type="text" name="tantoustaff_id"
						value="<c:out value="${ kyujin.tantoustaff_id }" />" size="8"
						maxlength="4" tabindex="52"></td>
				</tr>
				<tr>
					<th>応募書類</th>
					<td><textarea name="applicationform" rows="3" cols="40"
							tabindex="53"><c:out
								value="${ kyujin.applicationform }" /></textarea></td>
				</tr>
				<tr>
					<th>募集背景</th>
					<td><textarea name="background" rows="3" cols="40"
							tabindex="54"><c:out value="${ kyujin.background }" /></textarea></td>
				</tr>
				<tr>
					<th>性別（求職者に非公開）</th>
					<td><input type="radio" name="hiddensex" value="1"
						<c:if test="${1 == kyujin.hiddensex}">checked="checked"</c:if>
						tabindex="55"> 男 <input type="radio" name="hiddensex"
						value="2"
						<c:if test="${2 == kyujin.hiddensex}">checked="checked"</c:if>
						tabindex="56"> 女 <input type="radio" name="hiddensex"
						value="3"
						<c:if test="${1 != kyujin.hiddensex && 2 != kyujin.hiddensex}">checked="checked"</c:if>
						tabindex="57"> その他</td>
				</tr>
				<tr>
					<th>年齢下限（求職者に非公開）</th>
					<td><input type="number" name="hiddenagemin"
						value="<c:out value="${ kyujin.hiddenagemin }" />" size="8"
						maxlength="3" tabindex="58">歳</td>
				</tr>
				<tr>
					<th>年齢上限（求職者に非公開）</th>
					<td><input type="number" name="hiddenagemax"
						value="<c:out value="${ kyujin.hiddenagemax }" />" size="8"
						maxlength="3" tabindex="59">歳</td>
				</tr>
				<tr>
					<th>その他非公開情報</th>
					<td><textarea name="hiddenetc" rows="3" cols="40"
							tabindex="60"><c:out value="${ kyujin.hiddenetc }" /></textarea></td>
				</tr>
				<tr>
					<th>新規登録日</th>
					<td><input type="hidden" name="createdt"
						value="<fmt:formatDate value="${ kyujin.createdt }"
					pattern="yyyy-MM-dd"/>">
						<fmt:formatDate value="${ kyujin.createdt }"
					pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<th>新規登録ユーザ</th>
					<td><input type="hidden" name="createuserid"
						value="<c:out value="${ kyujin.createuserid }" />">
						<c:out value="${ kyujin.createuserid }" /></td>
				<tr>
					<th>最終更新日</th>
					<td><input type="hidden" name="updatedt"
						value="<fmt:formatDate value="${ kyujin.updatedt }"
					pattern="yyyy-MM-dd"/>">
						<fmt:formatDate value="${ kyujin.updatedt }"
					pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<th>最終更新ユーザ</th>
					<td><input type="hidden" name="updateuserid"
						value="<c:out value="${ kyujin.updateuserid }" />">
						<c:out value="${ kyujin.updateuserid }" /></td>
				</tr>

			</table>


			<button type="button" class="main-b"
				onClick="location.href='./job-search'" tabindex="62">求人一覧に戻る</button>

			<c:if test="${ kyujin.no == null }">
				<button type="submit" id="kyujin-insert" class="main-b"
					onclick="MovePages(this)" tabindex="61">登録</button>
			</c:if>
			<c:if test="${ kyujin.no != null }">
				<button type="submit" id="kyujin-update" class="main-b"
					onclick="MovePages(this)" tabindex="61">更新</button>
				<button type="submit" id="kyujin-delete" class="main-b2"
					onclick="MovePages(this)" tabindex="63">削除</button>
			</c:if>
		</div>
	</form>
	</main>
	<footer>
		<small>Copyright(C) 2009有限責任事業組合 大阪職業教育協働機構(A'ワーク創造館) All
			Rights Reserved.</small>
	</footer>
</body>
</html>
