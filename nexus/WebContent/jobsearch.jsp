<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css"
	rel="stylesheet" type="text/css" />
<link href="https://fonts.googleapis.com/css?family=M+PLUS+1p"
	rel="stylesheet" type="text/css" />
<link href="../css/bootstrap-reboot.css" rel="stylesheet" type="text/css" />
<link href="../css/common.css" rel="stylesheet" type="text/css" />

<link href="../css/footer.css"  type="text/css" />
<link href="../css/job_search.css" rel="stylesheet" type="text/css" />
<title>求人検索項目</title>
</head>
<body>
	<%@ include file="/header.jsp"%>
	<main>
	<!-- 求人検索項目 -->
	<div id="job_search">
		<h3 style="float: none;">求人検索項目</h3>
		<form action="/nexus/web/job-search" method="post">
			<div class="listbox2">
				<h4 class="word">職種をフリーワード検索</h4>
				<textarea class="word" name="job" cols="60" rows="3"
					placeholder="職種名より検索します。" tabindex="1"></textarea>
			</div>

			<div class="listbox2">
				<h4 class="word">業種をフリーワード検索</h4>
				<textarea class="word" name="jobcategory" cols="60" rows="3"
					placeholder="業種名より検索します。" tabindex="1"></textarea>
			</div>
			<div class="listbox1">
				<h4>勤務地</h4>
				<select name="addresscd">
					<option value=""></option>
					<c:forEach var="todouhuken" items="${ todouhukenlist }">
						<option value="${ todouhuken.cd }">${ todouhuken.name }</option>
					</c:forEach>
				</select>
			</div>
			<div class="listbox1">
				<h4>給料</h4>
				<input name="salarymin" type="number" maxlength="2" tabindex="3"
					min="0" style="width: 70px;" /> 万円～ <input name="salarymax"
					type="number" maxlength="2" tabindex="4" min="0"
					style="width: 70px;" /> 万円
			</div>
			<div class="listbox1">
				<h4>雇用形態</h4>
				<select name="koyoukeitaicd" tabindex="5">
					<option value=""></option>
					<option value="1">正社員</option>
					<option value="2">正社員以外</option>
					<option value="3">有期雇用派遣</option>
					<option value="4">無期雇用派遣</option>
					<option value="5">パート労働者</option>
					<option value="6">有期派遣パート</option>
					<option value="7">無期派遣パート</option>
				</select>
			</div>
			<div class="listbox1">
				<h4>路線</h4>
				<select name="rosen" tabindex="5">
					<option value=""></option>
					<option value="0">大阪地下鉄</option>
					<option value="1">御堂筋線</option>
					<option value="2">中央線</option>
					<option value="3">JR大阪環状線</option>
					<option value="4">近鉄線</option>
					<option value="5">阪急線</option>
					<option value="6">阪神線</option>
				</select>
			</div>
			<!--隠し項目（年齢など他）-->
			<input name="" type="hidden" value="" /> <input name=""
				type="hidden" value="" />
			<!--ここまで隠し項目-->
			<div class="listbox2">
				<h4 class="word">求人詳細内をフリーワード検索</h4>
				<textarea class="word" name="job" cols="60" rows="5"
					placeholder="求人詳細項目より検索します。"></textarea>
			</div>
			<div class="listbox3">
				<input class="submit_button" type="submit" value="検索" /> <input
					class="reset_button" type="reset" value="選択項目をすべてクリア" />
			</div>
		</form>
	</div>
	<!-- 求人一覧表示 -->
	<div id="job_search_list">
		<h3>求人一覧表示</h3>
		<table width="" border="0">
			<tbody id="">
				<tr>
					<th scope="col">&nbsp;</th>
					<th scope="col">求人No.</th>
					<th scope="col">事業所名</th>
					<th scope="col">就業場所</th>
					<th scope="col">最寄り駅</th>
					<th scope="col">職種</th>
					<th scope="col">雇用形態</th>
					<th scope="col">年齢制限</th>
					<th scope="col">基本給</th>
				</tr>
				<c:forEach var="kyujin" items="${ kyujinlist }">

					<tr>
						<td>
							<form method="get" action="/nexus/web/kyujin-disp">
								<input type="hidden" name="no" value="${ kyujin.no }">
									<input type="submit" value="詳細">
							</form>
						</td>
						<td><c:out value="${ kyujin.no }" /></td>
						<td><c:out value="${ companyMap[kyujin.companyno].companyName }" /></td>
						<td><c:out value="${ kyujin.address }" /></td>
						<td><c:out value="${ kyujin.nearstation }" /></td>
						<td><c:out value="${ kyujin.job }" /></td>
						<td><c:out value="${ kyujin.koyoukeitaicd }" /></td>
						<td><c:out value="${ kyujin.agemin }" />歳 ～ <c:out
								value="${ kyujin.agemax }" />歳</td>
						<td><c:out value="${ kyujin.salarymin }" />円 ～ <c:out
								value="${ kyujin.salarymax }" />円</td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	</main>
	<!-- フッター　-->
	<footer> <small>Copyright(C) 2009有限責任事業組合
		大阪職業教育協働機構(A'ワーク創造館) All Rights Reserved.</small> </footer>
</body>
</html>