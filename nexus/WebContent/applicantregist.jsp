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
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/i18n/jquery.ui.datepicker-ja.min.js"></script>
<link rel="stsylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/Base/jquery-ui.css">

<script src="/js/kalendae.standalone.js" type="text/javascript" charset="utf-8"></script>
<script src="https://ajaxzip3.github.io/ajaxzip3.js" charset="UTF-8"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1/i18n/jquery.ui.datepicker-ja.min.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/applicantregist_tab.js"></script>
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
<title>求職者詳細情報</title>
</head>
<body>
	<!-- ヘッダー　-->
	<%@ include file ="/header.jsp" %>

	<main>
	<h2>求職者詳細情報</h2>
	<ul>
		<c:forEach var="message" items="${ messages }">
			<li><c:out value="${ message }" /></li>
		</c:forEach>
	</ul>
	<div id="job_seeker">
		<form action="/nexus/web/jobseeker-regist" method="post">

		  <div id="tab-controll" class="tab-menu">
		<ul>
           <li id="btn1"><a href="#tab1">個人情報</a></li>
           <li id="btn2"><a href="#tab2">希望業種</a></li>
           <li id="btn3"><a href="#tab3">その他</a></li>
        </ul>
        </div>

	    <div id="controll" class="menu">
			<table id="tab1" class="page">
				<tr>
				<th width="20%">ID</th>
				<td><input type="hidden" name="id"
						value="<c:out value="${ info.id }" />">
				<fmt:formatNumber value="${ info.id }" pattern="00000000"/></td>
				</tr>
				<tr>
					<th>求職者氏名</th>
						<td><input type="text" name="name" value="${ info.name }" /></td>
				</tr>
				<tr>
					<th>氏名(かな)</th>
						<td><input type="text" name="kana" value="${ info.kana }" /></td>
				</tr>
				<tr>
					<th>生年月日</th>
					<td><input type="text" class="datepicker" name="birthdt"
							value="<fmt:formatDate value="${ info.birthdt }" pattern="yyyy-MM-dd"/>"></td>
				</tr>
				<tr>
					<th>性別</th>
						<td>
							<input type="radio" name="sex" value="1"
							<c:if test="${ info.sex == 1 }">checked</c:if> />男
							<input type="radio" name="sex" value="2"
							<c:if test="${ info.sex == 2 }">checked</c:if> />女
						</td>
					</tr>
					<tr>
						<th>郵便番号</th>
						<td><input type="text" name="zip21" class="form_text-small"
							onKeyUp="AjaxZip3.zip2addr('zip21','zip22','addr21','addr21');"
							size="3" maxlength="3" value="${ info.zip21 }" /> -
							<input type="text" name="zip22" class="form_text-small"
							onKeyUp="AjaxZip3.zip2addr('zip21','zip22','addr21','addr21');"
							size="4" maxlength="4" value="${ info.zip22 }" /></td>
					</tr>
					<tr>
						<th>住所</th>
						<td><input type="text" name="addr21" class="form_text"
							value="${ info.addr21 }" size="50"></td>
					</tr>
					<tr>
						<th>メールアドレス</th>
						<td><input type="text" name="seekermail"
							value="${ info.seekermail }" /></td>
					</tr>
					<tr>
						<th>最寄り駅</th>
						<td><input type="text" name="nearstation"
							value="${ info.nearstation }" size="10" />駅</td>
					</tr>
					<tr>
						<th>自宅電話番号</th>
						<td><input type="text" name="phone" value="${ info.phone }" /></td>
				</tr>
				<tr>
						<th>携帯電話番号</th>
						<td><input type="text" name="mobile" value="${ info.mobile }" /></td>
				</tr>
				<tr>
					<th>配偶者</th>
					<td><input type="radio" name="partner" value="0"
							<c:if test="${ info.partner == 0 }"> checked </c:if> /> 無 <input
							type="radio" name="partner" value="1"
							<c:if test="${ info.partner == 1 }"> checked </c:if> /> 有</td>
				</tr>
				<tr>
					<th>扶養家族人数</th>
					<td><select name="huyou">
							<option value=""></option>
							<option value="0"
								<c:if test="${ info.huyou == 0 }">selected</c:if>>0</option>
							<option value="1"
								<c:if test="${ info.huyou == 1 }">selected</c:if>>1</option>
							<option value="2"
								<c:if test="${ info.huyou == 2 }">selected</c:if>>2</option>
							<option value="3"
								<c:if test="${ info.huyou == 3 }">selected</c:if>>3</option>
							<option value="4"
								<c:if test="${ info.huyou == 4 }">selected</c:if>>4</option>
							<option value="5"
								<c:if test="${ info.huyou == 5 }">selected</c:if>>5</option>
							<option value="6"
								<c:if test="${ info.huyou == 6 }">selected</c:if>>6</option>
							<option value="7"
								<c:if test="${ info.huyou == 7 }">selected</c:if>>7</option>
							<option value="8"
								<c:if test="${ info.huyou == 8 }">selected</c:if>>8</option>
							<option value="9"
								<c:if test="${ info.huyou == 9 }">selected</c:if>>9</option>
							<option value="10"
								<c:if test="${ info.huyou == 10 }">selected</c:if>>10</option>
							<option value="11"
								<c:if test="${ info.huyou == 11 }">selected</c:if>>11</option>
							<option value="12"
								<c:if test="${ info.huyou == 12 }">selected</c:if>>12</option>
							<option value="13"
								<c:if test="${ info.huyou == 13 }">selected</c:if>>13</option>
							<option value="14"
								<c:if test="${ info.huyou == 14 }">selected</c:if>>14</option>
							<option value="15"
								<c:if test="${ info.huyou == 15 }">selected</c:if>>15</option>
							<option value="16"
								<c:if test="${ info.huyou == 16 }">selected</c:if>>16</option>
							<option value="17"
								<c:if test="${ info.huyou == 17 }">selected</c:if>>17</option>
							<option value="18"
								<c:if test="${ info.huyou == 18 }">selected</c:if>>18</option>
							<option value="19"
								<c:if test="${ info.huyou == 19 }">selected</c:if>>19</option>
							<option value="20"
								<c:if test="${ info.huyou == 20 }">selected</c:if>>20</option>
							<option value="21"
								<c:if test="${ info.huyou == 21 }">selected</c:if>>21</option>
							<option value="22"
								<c:if test="${ info.huyou == 22 }">selected</c:if>>22</option>
							<option value="23"
								<c:if test="${ info.huyou == 23 }">selected</c:if>>23</option>
							<option value="24"
								<c:if test="${ info.huyou == 24 }">selected</c:if>>24</option>
							<option value="25"
								<c:if test="${ info.huyou == 25 }">selected</c:if>>25</option>
							<option value="26"
								<c:if test="${ info.huyou == 26 }">selected</c:if>>26</option>
							<option value="27"
								<c:if test="${ info.huyou == 27 }">selected</c:if>>27</option>
							<option value="28"
								<c:if test="${ info.huyou == 28 }">selected</c:if>>28</option>
							<option value="29"
								<c:if test="${ info.huyou == 29 }">selected</c:if>>29</option>
							<option value="30"
								<c:if test="${ info.huyou == 30 }">selected</c:if>>30</option>
							<option value="31"
								<c:if test="${ info.huyou == 31 }">selected</c:if>>31</option>
							<option value="32"
								<c:if test="${ info.huyou == 32 }">selected</c:if>>32</option>
							<option value="33"
								<c:if test="${ info.huyou == 33 }">selected</c:if>>33</option>
							<option value="34"
								<c:if test="${ info.huyou == 34 }">selected</c:if>>34</option>
							<option value="35"
								<c:if test="${ info.huyou == 35 }">selected</c:if>>35</option>
							<option value="36"
								<c:if test="${ info.huyou == 36 }">selected</c:if>>36</option>
							<option value="37"
								<c:if test="${ info.huyou == 37 }">selected</c:if>>37</option>
							<option value="38"
								<c:if test="${ info.huyou == 38 }">selected</c:if>>38</option>
							<option value="39"
								<c:if test="${ info.huyou == 39 }">selected</c:if>>39</option>
							<option value="40"
								<c:if test="${ info.huyou == 40 }">selected</c:if>>40</option>
							<option value="41"
								<c:if test="${ info.huyou == 41 }">selected</c:if>>41</option>
							<option value="42"
								<c:if test="${ info.huyou == 42 }">selected</c:if>>42</option>
							<option value="43"
								<c:if test="${ info.huyou == 43 }">selected</c:if>>43</option>
							<option value="44"
								<c:if test="${ info.huyou == 44 }">selected</c:if>>44</option>
							<option value="45"
								<c:if test="${ info.huyou == 45 }">selected</c:if>>45</option>
							<option value="46"
								<c:if test="${ info.huyou == 46 }">selected</c:if>>46</option>
							<option value="47"
								<c:if test="${ info.huyou == 47 }">selected</c:if>>47</option>
							<option value="48"
								<c:if test="${ info.huyou == 48 }">selected</c:if>>48</option>
							<option value="49"
								<c:if test="${ info.huyou == 49 }">selected</c:if>>49</option>
					</select>人</td>
				</tr>
				<tr>
						<th>最終学歴</th>

						<td>
							<input type="radio" name="education" value="1"
							<c:if test="${ info.education == 1 }">checked</c:if> />中学
							<input type="radio" name="education" value="2"
							<c:if test="${ info.education == 2 }">checked</c:if> />高校
							<input type="radio" name="education" value="3"
							<c:if test="${ info.education == 3 }">checked</c:if> />短大
							<input type="radio" name="education" value="4"
							<c:if test="${ info.education == 4 }">checked</c:if> />大学
							<input type="radio" name="education" value="5"
							<c:if test="${ info.education == 5 }">checked</c:if> />専門学校
						</td>
				</tr>
				<tr>
					<th>職歴・経歴</th>
					<td><textarea rows="20" cols="80" name="career" ><c:out value="${ info.career }" /></textarea></td>
				</tr>
				</table>
				<table id="tab2" class="page">
				<tr>
					<th>希望職種1</th>
					<td><select name="hopeJob1">
						<option value=""></option>
						<c:forEach var="job" items="${ Largelist }">
							<option value="<c:out value="${ job.largecd }" />"
							<c:if test="${ job.largecd == info.hopeJob1 }">selected</c:if> >
							<c:out value="${ job.name }" />
							</option>
						</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>希望職種2</th>
					<td><select name="hopeJob2">
					<option value=""></option>
						<c:forEach var="job" items="${ Largelist }">
							<option value="<c:out value="${ job.largecd }" />"
							<c:if test="${ job.largecd == info.hopeJob2 }">selected</c:if> >
							<c:out value="${ job.name }" />
							</option>
						</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>希望職種3</th>
					<td><select name="hopeJob3">
					<option value=""></option>
						<c:forEach var="job" items="${ Largelist }">
							<option value="<c:out value="${ job.largecd }" />"
							<c:if test="${ job.largecd == info.hopeJob3 }">selected</c:if> >
							<c:out value="${ job.name }" />
							</option>
						</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>希望業種1</th>
					<td><select name="hopeJobCategory1">
						<option value=""></option>
						<c:forEach var="jobcategory" items="${ JCLargelist }">
							<option value="<c:out value="${ jobcategory.largecd }" />"
							<c:if test="${ jobcategory.largecd == info.hopeJobCategory1 }">selected</c:if> >
							<c:out value="${ jobcategory.name }" />
							</option>
						</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>希望業種2</th>
					<td><select name="hopeJobCategory2">
						<option value=""></option>
						<c:forEach var="jobcategory" items="${ JCLargelist }">
							<option value="<c:out value="${ jobcategory.largecd }" />"
							<c:if test="${ jobcategory.largecd == info.hopeJobCategory2 }">selected</c:if> >
							<c:out value="${ jobcategory.name }" />
							</option>
						</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>希望業種3</th>
					<td><select name="hopeJobCategory3">
						<option value=""></option>
						<c:forEach var="jobcategory" items="${ JCLargelist }">
							<option value="<c:out value="${ jobcategory.largecd }" />"
							<c:if test="${ jobcategory.largecd == info.hopeJobCategory3 }">selected</c:if> >
							<c:out value="${ jobcategory.name }" />
							</option>
						</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>希望勤務地</th>
					<td><select name="hopeworkplace">
						<option value=""></option>
							<c:forEach var="todouhuken" items="${ Todouhukenlist }">
								<option value="<c:out value="${  todouhuken.cd }" />"
								<c:if test="${ todouhuken.cd == info.hopeworkplace }">selected</c:if>>
								<c:out value="${ todouhuken.name }" />
								</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>希望雇用形態</th>
				<td>
					<label><input type="checkbox" name="hopekoyoukeitai" value="1">正社員</label>
					<label><input type="checkbox" name="hopekoyoukeitai" value="2">契約社員</label>
					<label><input type="checkbox" name="hopekoyoukeitai" value="3">パート</label>
				</td>
				</tr>
				<tr>
					<th>希望勤務曜日</th>
				<td>
					<label><input type="checkbox" name="hopekoyoukeitai" value="1">月</label>
					<label><input type="checkbox" name="hopekoyoukeitai" value="2">火</label>
					<label><input type="checkbox" name="hopekoyoukeitai" value="3">水</label>
					<label><input type="checkbox" name="hopekoyoukeitai" value="4">木</label>
					<label><input type="checkbox" name="hopekoyoukeitai" value="5">金</label>
					<label><input type="checkbox" name="hopekoyoukeitai" value="6">土</label>
					<label><input type="checkbox" name="hopekoyoukeitai" value="7">日</label>
				</td>
				</tr>
<%-- 				<tr>
					<th>希望勤務日時</th>
					<td><input type="text" name="hopeworkingdate"
						value="${ info.hopeworkingdate }" /></td>
				</tr> --%>
				<tr>
					<th>希望勤務時間(開始)</th>
					<td><input type="text" name="hopebegintime"
						value="${ info.hopebegintime }" /></td>
				</tr>
				<tr>
					<th>希望勤務時間(終了)</th>
					<td><input type="text" name="hopeendtime"
							value="${ info.hopeendtime }" /></td>
					</tr>
					<tr>
						<th>希望月給</th>
						<td><input type="text" name="hopesalary"
							value="${ info.hopesalary }" size="4" />万円</td>
					</tr>
					<tr>
						<th>希望時間給</th>
						<td><input type="text" name="hopejikyu"
							value="${ info.hopejikyu }" size="4" />円</td>
					</tr>
					<tr>
						<th>その他希望</th>
						<td><textarea rows="3" cols="80" name="hopeetc"  ><c:out
									value="${ info.hopeetc }" /></textarea></td>
					</tr>
					<tr>
						<th>自動車免許</th>
						<td><input type="text" name="driverlicense"
							value="${ info.driverlicense }" /></td>
				</tr>
				</table>

				<table id="tab3" class="page">
				<tr>
						<th>その他資格</th>
					<td><textarea rows="3" cols="80" name="licenseetc"><c:out
								value="${ info.licenseetc }" /></textarea></td>
				</tr>
				<tr>
						<th>パソコンスキル</th>
						<td><textarea rows="5" cols="80" name="pasokonskill" ><c:out
									value="${ info.pasokonskill }" /></textarea></td>
				</tr>
				<tr>
						<th>留意点</th>
						<td><textarea rows="5" cols="80" name="caution" ><c:out
									value="${ info.caution }" /></textarea></td>
				</tr>
				<tr>
					<th>担当職業者紹介者ID</th>
				<%--<td>
						<input type="hidden" name="tantoustaffid"
						value="<c:out value="${ info.tantoustaffid }" />"><c:out value="${ info.tantoustaffid }" />

					</td> --%>
 					<td><select name="tantoustaffid">
						<option value=""></option>
						<c:forEach var="staff" items="${ staffList }">
							<option value="<c:out value="${ staff.id }" />"
								<c:if test="${ staff.id  == info.tantoustaffid }"> selected</c:if> >
								<c:out value="${ staff.name }" />
							</option>
						</c:forEach>
					</select></td>

				</tr>
					<tr>
						<th>補足</th>
						<td><textarea rows="3" cols="80" name="note" ><c:out
									value="${ info.note }" /></textarea></td>
				</tr>
				</table>

				<input class="main-b" type="submit" value="登録"> <input
					class="main-b" type="button"
			onclick="location.href='/nexus/web/jobseeker-list'" value="一覧に戻る">

					<!-- <button type="button" class="main-b"
				onClick="location.href='./companysearch'" tabindex="62">企業検索に戻る</button>

			<c:if test="${ status == 'regist' }">
				<button type="submit" id="company-regist" class="main-b"
					onclick="MovePages(this)" tabindex="61">登録</button>
			</c:if>
			<c:if test="${ status != 'regist' }">
				<button type="submit" id="company-edit" class="main-b"
					onclick="MovePages(this)" tabindex="61">更新</button>
				<button type="submit" id="company-delete" class="main-b2"
					onclick="MovePages(this)" tabindex="63">削除</button>
			</c:if> -->
		</div>
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