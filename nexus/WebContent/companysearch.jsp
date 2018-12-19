<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css"
	rel="stylesheet" type="text/css" />
<link href="https://fonts.googleapis.com/css?family=M+PLUS+1p"
	rel="stylesheet" type="text/css" />
<link href="../css/bootstrap-reboot.css" rel="stylesheet"
	type="text/css" />
<link href="../css/common.css" rel="stylesheet" type="text/css" />
<link href="../css/header.css" rel="stylesheet" type="text/css" />
<link href="../css/footer.css" rel="stylesheet" type="text/css" />
<link href="../css/company_search.css" rel="stylesheet" type="text/css" />
<title>企業ページ</title>
</head>
<body>
	<!-- ヘッダー　-->
	<header> <section>
	<h1 class="logo">
		<a href="/nexus/web/staff-top"><img src="../css/TryNexus-Logo.png"
			width="97" height="70" alt="TryNexus" /></a>
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
		<!-- <c:if test="${Staff.authority == 1}"><li><a href="/nexus/web/account-list"><i
				class="far fa-bookmark"></i>管理</a></li></c:if> -->
	</ul>
	</nav>
	<div class="user">
		<div class="user__wrapper">
			<div class="user__name">
				<a href="#"> <!-- <c:out value="${ Staff.name }" /> --> <i
					class="fas fa-ellipsis-v"></i>
				</a>
				<ul class="drop-menu">
					<li><a href="/nexus/web/logout">ログアウト<i
							class="fas fa-angle-right"></i></a></li>
				</ul>
			</div>
		</div>
	</div>
	</section> </header>
	<main> <!--　企業検索項目 -->
	<div id="company_search">

		<ul class="errormessage">
			<c:forEach var="message" items="${ messages }">
				<li><c:out value="${ message }" /></li>
			</c:forEach>
		</ul>
		<h3 style="float: none;">企業検索項目</h3>



		<p>※２単語以上入力する際は間にスペースを入れてください。</p>
		<form action="/nexus/web/companysearch" method="get">

			<div class="listbox2">
				<!--  CSSファイルなので跡で設定する -->
				<h4 class="word">企業名</h4>
				<input class="word" name="companyname" type="text"
					placeholder="企業名を入れてください（カナ対応）" tabindex="1" />
			</div>

			<div class="listbox2">
				<!--  CSSファイルなので跡で設定する -->
				<h4 class="word">事業所所在地・最寄り駅</h4>
				<input class="word" name="companyplace" type="text"
					placeholder="事業所所在地・最寄り駅を入れてください。" tabindex="1" />
			</div>

			<div class="listbox1">
				<h4>業種</h4>
				<select name="jobcategory">
					<option value=""></option>
					<c:forEach var="jobcategory" items="${ jobcategorylist }">
						<option value="<c:out value="${ jobcategory.largecd }" />"><c:out
								value="${ jobcategory.name }" /></option>
					</c:forEach>
				</select>
			</div>

			<div class="listbox1">
				<h4>A'担当者名</h4>
				<select name="staffid">
					<option value=""></option>
					<c:forEach var="staff" items="${ stafflist }">
						<option value="<c:out value="${ staff.id }" />"><c:out
								value="${ staff.name }" /></option>
					</c:forEach>
				</select> </select>
			</div>
			<div class="listbox3">
				<input class="submit_button" type="submit" value="検索" /> <input
					class="reset_button" type="reset" value="選択項目をすべてクリア" />
			</div>
		</form>

	</div>

	<div id="company_search_list">

		<h3>企業一覧表示</h3>

		<form action="/nexus/web/company-registdisp" method="get">
			<input type="submit" class="regist_button1" name="send" value="新規登録">
		</form>



		<c:if test="${companylist.size() >=0 }">
			<p class="searchresult">
				検索結果
				<c:out value="${ companylist.size() }" />
				件
			</p>
		</c:if>

		<table width="" border="0">
			<tbody id="">
				<tr>
					<th scope="col">&nbsp;</th>
					<th scope="col">事業所番号</th>
					<th scope="col">事業所名</th>
					<th scope="col">業種</th>
					<th scope="col">住所</th>
					<th scope="col">選考担当者</th>
				</tr>
				<c:forEach var="company" items="${ companylist }">
					<tr>
						<td>
							<form method="get" action="/nexus/web.company-info">
								<input type="hidden" name="companyno"
									value="<c:out value="${ company.companyNo }" />"> <input
									type="submit" value="詳細">
							</form>
						</td>
						<td><c:out value="${ company.companyNo }" /></td>
						<td><c:out value="${ company.companyName }" /></td>
						<td><c:out value="${ company.jobCategoryLargeName }" /></td>
						<td><c:out value="${ company.companyPlace }" /></td>
						<td><c:out value="${ company.tantou }" /></td>

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