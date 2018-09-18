<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="job_search.css" rel="stylesheet" type="text/css">
	<title>求人検索項目</title>
</head>
<body>
	<!--　求人検索項目 -->
	<div id="job_search">
		<h3>求人検索項目</h3>
		<form action="./jobsearch" method="post">
			<div class="listbox1">
				<h4>職種中分類</h4>
				<select name="jobsmallcd2" tabindex="1">
					<option value=""></option>
					<option value="012">研究者</option>
					<option value="000">農林水産技術者</option>
					<option value="07">開発技術者</option>
					<option value="08">製造技術者</option>
					<option value="09">建築・土木・測量技術者</option>
					<option value="10">情報処理・通信技術者</option>
				</select>
			</div>
			<div class="listbox1">
				<h4>職種小分類</h4>
				<select name="jobsmallcd3" tabindex="2">
					<option value=""></option>
					<option value="1">システムコンサルタント</option>
					<option value="2">システム設計技術者</option>
					<option value="103">情報処理プロジェクトマネージャ</option>
					<option value="104">ソフトウェア開発技術者</option>
					<option value="105">システム運用管理者</option>
					<option value="106">通信ネットワーク技術</option>
					<option value="109">その他の情報処理・通信技術者</option>
				</select>
			</div>
			<div class="listbox1">
				<h4>業種中分類</h4>
				<select name="joblargecd2" tabindex="3">
					<option value=""></option>
					<option value="0">情報・広告業</option>
					<option value="1">他事業サービス</option>
				</select>
			</div>
			<div class="listbox1">
				<h4>業種小分類</h4>
				<select name="joblargecd3" tabindex="4">
					<option value="0"></option>
					<option value="1">情報サービス業</option>
					<option value="2">ニュース供給業</option>
					<option value="3">興信業</option>
					<option value="4">広告業</option>
				</select>
			</div>
			<div class="listbox1">
				<h4>給料</h4>
				<input name="salarymin" type="number" maxlength="2" tabindex="5"
					style="width: 90px;" /> 万円～ <input name="salarymax" type="number"
					maxlength="2" tabindex="6" style="width: 90px;" /> 万円
			</div>
			<div class="listbox1">
				<h4>勤務地</h4>
				<select name="addresscd" tabindex="7">
					<option value=""></option>
					<option value="0">test1</option>
					<option value="1">test2</option>
				</select>
			</div>
			<div class="listbox1">
				<h4>雇用形態</h4>
				<select name="koyoukeitaicd" tabindex="7">
					<option value="0"></option>
					<option value="1">正社員</option>
					<option value="2">正社員以外</option>
					<option value="3">有期雇用派遣</option>
					<option value="4">無期雇用派遣</option>
					<option value="5">パート労働者</option>
					<option value="6">有期派遣パート</option>
					<option value="7">無期派遣パート</option>
				</select>
			</div>
			<!--隠し項目（年齢など他）-->
			<input name="" type="hidden" value="" /> <input name=""
				type="hidden" value="" />
			<!--ここまで隠し項目-->
			<div class="listbox2">
				<h4 class="word">求人詳細内をフリーワード検索</h4>
				<textarea class="word" name="textarea" cols="60" rows="5"
					placeholder="求人詳細項目より検索します。"></textarea>
			</div>
			<div class="listbox3">
				<input class="submit_button" type="submit" value="検索" /> <input
					class="reset_button" type="reset" value="選択項目をすべてクリア" />
			</div>
		</form>
	</div>
	<div id="job_search_list">
		<h3>求人一覧表示</h3>

		<table width="" border="0">
			<tbody id="">
				<tr>
					<th scope="col">&nbsp;</th>
					<th scope="col">求人No.</th>
					<th scope="col">事業署名</th>
					<th scope="col">就業場所</th>
					<th scope="col">最寄り駅</th>
					<th scope="col">職種</th>
					<th scope="col">雇用形態</th>
					<th scope="col">年齢制限</th>
					<th scope="col">基本給</th>
				</tr>
				<c:forEach var="SimpleKyujin" items="${ kyujin }">
					<tr>
						<td><button type="button"
								onclick="location.href='job_info.html'">詳細</button></td>
						<td><c:out value="${ SimpleKyujin.no }" /></td>
						<td><c:out value="${ SimpleKyujin.companyname }" /></td>
						<td><c:out value="${ SimpleKyujin.address }" /></td>
						<td><c:out value="${ SimpleKyujin.nearstation }" /></td>
						<td><c:out value="${ SimpleKyujin.job }" /></td>
						<td><c:out value="${ SimpleKyujin.koyoukeitaicd }" /></td>
						<td><c:out value="${ SimpleKyujin.agemin }" />～<c:out value="${ SimpleKyujin.agemax }" /></td>
						<td><c:out value="${ SimpleKyujin.salarymin }" />～<c:out
								value="${ SimpleKyujin.salarymax }" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>