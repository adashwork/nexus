<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">

<head>

<meta charset="UTF-8">

<title>マッチング事例検索</title>
<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=M+PLUS+1p" rel="stylesheet">
<link href="../css/bootstrap-reboot.css" rel="stylesheet">
<link href="../css/common.css" rel="stylesheet">
<link href="../css/header.css" rel="stylesheet">
<link href="../css/footer.css" rel="stylesheet">
<link rel="stylesheet" href="../css/default.css">
<link rel="stylesheet" href="../css/default.date.css">
<!-- <link href="css/ootuka.css" rel="stylesheet"> -->
<link href="../css/matching.css" rel="stylesheet">

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

	<main> 													<!-- body部分　-->

		<h2>マッチング事例検索</h2>

<!--   2018/12/18 kitayama コメントアウト -->
<%--
		<ul>
			<c:forEach var="message" items="${ messages }">
				<li><font color=#F00 size="7"><c:out value="${ message }" /></font></li>
			</c:forEach>
		</ul>
--%>
		<!--  kitayama 2018/12/14 form methodをgetに変更
										  actionを検索サーブレットに変更
										  各検索項目に検索値を受け取れるよう変更 -->
 		<form id="form" method="get" action="./matching-search">

			<!--  komukai　2018/12/13 検索欄テーブルタグからdivへ変更 -->
			<div class="m_div">

				<p class="m_p">
					マッチングID<br/>
				<!--  kitayama 2018/12/14 nameを変更 -->
				<input type="text" name="matchingid" size=13  value="<c:out value="${ matchingid }" />" >
				</p>

				<p class="m_p">
					企業ID<br/>
					<input type="text" size=13 name="companyno" value="<c:out value="${ companyno }" />" >
				</p>
				<p class="m_p">
					求職者ID<br/>
					<input type="text" size=13 name="jobseekerid" value="<c:out value="${ jobseekerid }" />" >
				</p>

				<p class="m_p">
				<!--  kitayama 2018/12/14 検索項目を職業紹介者IDに変更 -->
					職業紹介者ID<br/>
					<input type="text" size=13 name="staffid" value="<c:out value="${ staffid }" />" >
				</p>			</div>
			<div class="m_div">
				<p class="m_p">
					フリーワード検索<br/>
					<input type="text" name="note" size="50"></textarea>
				</p>
				<p class="m_p_right">
					<input type="submit" class="main-b" name="send" value="検索">
				</p>
		</div>
		</form>

		<p>
			検索結果：
			<c:out value="${ matching.size() }" />
			件
		</p>

		<table border="0">

			<tr>
				<th></th>
				<th>マッチングID</th>
				<th>企業ID</th>
				<th>求人者ID</th>
				<th>合否</th>
				<th>コメント</th>
			</tr>

			<!--  kitayama　2018/12/13 for文追加 -->

			<c:choose>
				<c:when test="${ empty matching }">
					<c:forEach var="errormessage" items="${ messages }">
						<tr>
						<!-- TODO CSSで幅を広げる -->
							<td colspan=6>
								<c:out value="${ errormessage }" />
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:when test="${ !empty matching }">
				<c:forEach var="matchingCase" items="${ matching }">
						<tr>
							<td>
								<form action="./matching-registdisp" method="get">
								<button class="mini_b mini_b_applilist" name="matchinginfo"
											value="<c:out value="${ matchingCase.id }" />">詳細</button>
								</form>
							</td>
							<td>
								<c:out value="${ matchingCase.id }" />
								<input type="hidden" name="matchingid" value="<c:out value="${ matchingCase.id }" />" >
							</td>
							<td><c:out value="${ matchingCase.companyNo }" /></td>
							<td><c:out value="${ matchingCase.jobseekerid }" /></td>
							<td><c:out value="${ matchingCase.assessment }" /></td>
							<td>
								<c:out value="${ matchingCase.title }" />
<!-- 2018/12/18 kitayama 文字数によって切り捨てる部分のコメントアウト -->
<%--
								<c:choose>
									<c:when test="${ fn:length(matchingCase.title)  <= 15 }">
										<c:out value="${ matchingCase.title }" />
									</c:when>
									<c:otherwise>
										<c:out value="${ fn:substring(matchingCase.title, 0, 15) }" />
									</c:otherwise>
								</c:choose>
 --%>
							</td>
						</tr>
				</c:forEach>
				</c:when>
			</c:choose>
		</table>
<%--
<div>
	<!-- TODO: 検索結果の件数表示 -->
	<p>(●●件の候補があります(●●/●●))</p>
</div>

<div>
	<!-- TODO: 検索結果の件数により、前のページ、次のページ -->
	<p>前のページ　/　次のページ</p>
</div>

		<button type="button" class="main-b" onClick="location.href='./staff-top'">戻る</button>
		<button type="button" class="main-b" onClick="location.href='./matching-registdisp'">登録</button>
 		<c:if test="${ matching.id == null }">
			<button type="submit" id="match-regist" class="main-b" onclick="MovePages(this)">登録</button>
		</c:if>
		<c:if test="${ matching.id != null && matching.id != 0 }">
			<button type="submit" id="match-update" class="main-b" onclick="MovePages(this)">更新</button>
		</c:if>
 --%>

	</main>
<!-- フッター　-->
	<footer>
		<small>Copyright(C) 2009有限責任事業組合 大阪職業教育協働機構(A'ワーク創造館) All Rights Reserved.</small>
	</footer>
</body>
</html>