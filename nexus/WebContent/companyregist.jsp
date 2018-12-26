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
<body>
	<%@ include file="/header.jsp"%>
	<main>
	<h2>企業情報</h2>
	<ul>
		<c:forEach var="message" items="${ messages }">
			<li><c:out value="${ message }" /></li>
		</c:forEach>
	</ul>
	<div id="company">
		<form id="form" action="/nexus/web/company-regist" method="post">
			<table width="" border="0">



				<tr>
					<th>事業所番号(ハイフンあり)</th>
					<td><c:if test="${ status == 'regist' }">
							<input type="text" name="companyno"
								value="${ company.companyNo }" />
						</c:if> <c:if test="${ status != 'regist' }">

							<c:out value="${ company.companyNo }" />
							<input type="hidden" name="companyno"
								value="${ company.companyNo }" />
						</c:if></td>
				</tr>

				<tr>
					<th>法人番号</th>
					<td><input type="text" name="corporatenumber"
						value="${ company.corporateNumber }" /></td>
				</tr>

				<tr>
					<th>事業所名 [必須]</th>
					<td><input type="text" name="companyname"
						value="${ company.companyName }" /></td>
				</tr>
				<tr>
					<th>事業所名(カナ) [必須]</th>
					<td><input type="text" name="companykana"
						value="${ company.companyKana }" /></td>
				</tr>
				<tr>
					<th>事業所郵便番号 (123-4567)</th>
					<td><input id="postal" type="text" name="companypostal"
						value="${ company.companyPostal }" /></td>
				</tr>
				<tr>
					<th>事業所所在地</th>
					<td><input id="address" type="text" name="companyplace"
						value="${ company.companyPlace }" /></td>
				</tr>
				<tr>
					<th>最寄駅</th>
					<td><input type="text" name="nearstation"
						value="${ company.nearStation }" /></td>
				</tr>
				<tr>
					<th>事業所URL</th>
					<td><input type="text" name="companyurl"
						value="${ company.companyUrl }" /></td>
				</tr>

				<tr>
					<th>産業大分類コード</th>
					<td><select id="largecd" name="jobcategorylargecd">
							<option value=""></option>
							<c:forEach var="JCL" items="${ JCLargeList }">

								<option value="${ JCL.largecd }"
									<c:if test="${company.jobCategoryLargeCd == JCL.largecd }">
									selected
									</c:if>>${ JCL.name }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>産業中分類コード</th>
					<td><select id="middlecd" name="jobcategorymiddlecd">
							<option value=""></option>
							<c:forEach var="JCM" items="${ JCMiddleList }">
								<option value="${ JCM.middlecd }"
									<c:if test="${company.jobCategoryMiddleCd == JCM.middlecd }">
									selected
									</c:if>>${ JCM.name }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>産業小分類コード</th>
					<td><select id="smallcd" name="jobcategorysmallcd">

							<option value=""></option>
							<c:forEach var="JCS" items="${ JCSmallList }">
								<option value="${ JCS.smallcd }"
									<c:if test="${company.jobCategorySmallCd == JCS.smallcd }">
									selected
									</c:if>>${ JCS.name }</option>
							</c:forEach>
					</select></td>
				</tr>


				<tr>
					<th>資本金</th>
					<td><input type="text" name="capital"
						value="${ company.capital }" />万円</td>
				</tr>
				<tr>
					<th>従業員数</th>
					<td><select name="employees">
							<option value="10"
								<c:if test="${company.employees == '10' }">selected</c:if>>10名以下</option>
							<option value="100"
								<c:if test="${company.employees == '100' }">selected</c:if>>100名以下</option>
							<option value="1000"
								<c:if test="${company.employees == '1000' }">selected</c:if>>1000名以下</option>
							<option value="10000"
								<c:if test="${company.employees == '10000' }">selected</c:if>>10000名以下</option>
							<option value="100000"
								<c:if test="${company.employees == '100000' }">selected</c:if>>10000名以上</option>
					</select></td>
				</tr>
				<tr>
					<th>創業設立年</th>
					<td><input type="text" name="establishdt"
						value="${ company.establishDt }" />年</td>
				</tr>
				<tr>
					<th>担当者課係名/役職名</th>
					<td><input type="text" name="tantouyakushoku"
						value="${ company.tantouYakushoku }" /></td>
				</tr>
				<tr>
					<th>担当者名</th>
					<td><input type="text" name="tantou"
						value="${ company.tantou }" /></td>
				</tr>
				<tr>
					<th>担当者名(かな)</th>
					<td><input type="text" name="tantoukana"
						value="${ company.tantouKana }" /></td>
				</tr>
				<tr>
					<th>担当者TEL</th>
					<td><input type="text" name="tantoutel"
						value="${ company.tantouTel }" /></td>
				</tr>
				<tr>
					<th>担当者FAX</th>
					<td><input type="text" name="tantoufax"
						value="${ company.tantouFax }" /></td>
				</tr>
				<tr>
					<th>担当者email</th>
					<td><input type="text" name="tantouemail"
						value="${ company.tantouMail }" /></td>
				</tr>
				<tr>
					<th>担当者備考</th>
					<td><textarea rows="1" cols="40" name="tantounote"><c:out
								value="${ company.tantouNote }" /></textarea></td>
				</tr>
				<tr>
					<th>担当開拓者ID</th>
					<td><input type="text" name="tantoustaff_id"
						value="${ company.tantouStaffId }" /></td>
				</tr>
				<tr>
					<th>営業評価ランクABC</th>
					<td><select name="salesrank">
							<option value="A"
								<c:if test="${company.salesRank == 'A' }">selected</c:if>>A</option>
							<option value="B"
								<c:if test="${company.salesRank == 'B' }">selected</c:if>>B</option>
							<option value="C"
								<c:if test="${company.salesRank == 'C' }">selected</c:if>>C</option>
					</select></td>
				</tr>
				<tr>
					<th>営業備考</th>
					<td><textarea rows="4" cols="40" name="salesnote"><c:out
								value="${ company.salesNote }" /></textarea></td>
				</tr>

			</table>


			<button type="button" class="main-b"
				onClick="location.href='./company-search'" tabindex="62">企業検索に戻る</button>

			<c:if test="${ status == 'regist' }">
				<button type="submit" id="company-regist" class="main-b"
					onclick="MovePages(this)" tabindex="61">登録</button>
			</c:if>
			<c:if test="${ status != 'regist' }">
				<button type="submit" id="company-edit" class="main-b"
					onclick="MovePages(this)" tabindex="61">更新</button>
				<button type="submit" id="company-delete" class="main-b2"
					onclick="MovePages(this)" tabindex="63">削除</button>
			</c:if>
		</form>

	</div>

	<c:if test="${ status != 'regist' }">
	<%@ include file="/commentsearch_flame.jsp"%> </main>
	</c:if>
	<!-- フッター　-->
	<footer>
		<small>Copyright(C) 2009有限責任事業組合 大阪職業教育協働機構(A'ワーク創造館) All
			Rights Reserved.</small>
	</footer>
	<script src="js/kalendae.standalone.js" type="text/javascript"
		charset="utf-8"></script>
	<script src="../js/jobcategorypulldown.js" type="text/javascript"
		charset="utf-8"></script>
	<script src="../js/postcode.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>