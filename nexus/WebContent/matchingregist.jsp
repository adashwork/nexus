<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">

<head>

<meta charset="UTF-8">

<title>マッチング結果登録</title>
<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css"rel="stylesheet" >
<link href="https://fonts.googleapis.com/css?family=M+PLUS+1p" rel="stylesheet">
<link href="css/bootstrap-reboot.css" rel="stylesheet">
<link href="css/common.css" rel="stylesheet">
<link href="css/header.css" rel="stylesheet">
<link href="css/footer.css" rel="stylesheet">
<!-- <link href="css/ootuka.css" rel="stylesheet"> -->
<link href="css/matchingregist.css" rel="stylesheet">

</head>

<body>

<header>
  <section>
    <h1 class="logo"><a href="#">LOGO</a></h1>
    <nav>
      <ul class="mainnavi">
        <li><a href="#"><i class="fas fa-home"></i>検索</a></li>
        <li>
          <a href="#"><i class="fas fa-search"></i>登録&amp;閲覧</a>
          <ul class="drop-menu">
            <li><a href="#">求人情報<i class="fas fa-angle-right"></i></a></li>
            <li><a href="#">求職者情報<i class="fas fa-angle-right"></i></a></li>
            <li><a href="#">企業マスタ<i class="fas fa-angle-right"></i></a></li>
            <li><a href="#">マッチング履歴<i class="fas fa-angle-right"></i></a></li>
          </ul>
        </li>
        <li><a href="#"><i class="far fa-bookmark"></i>管理</a></li>
      </ul>
    </nav>
    <div class="user">
      <div class="user__wrapper">
        <!-- <div class="user__image"></div> -->
        <div class="user__name">
          <a href="#">山田 太郎<i class="fas fa-ellipsis-v"></i></a>
          <ul class="drop-menu">
            <li><a href="#">サインアウト<i class="fas fa-angle-right"></i></a></li>
          </ul>
        </div>
      </div>
    </div>
  </section>
</header>
<main>

<!-- body部分　-->

<h2>マッチング結果登録</h2>
<form method="post" action="./matchingservlet">



<!-- テーブル部分　-->


<table border="0">
<caption>マッチング結果登録</caption>
		<tr>
			<th>項目名</th>
			<th>結果</th>

			</tr>
			<tr>
				<td>求人No</td>
				<td>
				<input type="text" name="kyujinno" size="14"><input
					type="submit" value="求人No一覧"></td>

			</tr>
			<tr>
				<td>求職者No</td>
				<td><input type="text" name="jobseekerid" size="8"><input
					type="submit" value="求職者No一覧"></td>

			</tr>
			<tr>
				<td>入社日</td>
				<td><input type="date" name="enterdt">
					＜＜<script src="build/kalendae.standalone.js"
						type="text/javascript" charset="utf-8"></script></td>

			</tr>
			<tr>
				<td>評価</td>
				<td>
						<input name="assessment" type="radio" value="1"> 成功 <input
							name="assessment" type="radio" value="2"> 失敗 <input name="assessment"
							type="radio" value="3" checked="checked"> 確認中
					</td>

			</tr>
			<tr>
				<td>面接日</td>
				<td><input type="date" name="interviewdt">
					 <script src="build/kalendae.standalone.js"
						type="text/javascript" charset="utf-8"></script></td>

			</tr>
			<tr>
				<td>職業紹介者ID</td>
				<td><input type="text" name="staffid" size="4"></td>

			</tr>
			<tr>
				<td>備考欄</td>
				<td><textarea name="note" rows="5" cols="50"></textarea></td>

			</tr>

		</table>
		</form>
		<input type="submit" value="登録">




	</main>


<!-- フッター　-->
<footer>
  <small>Copyright(C) 2009有限責任事業組合 大阪職業教育協働機構(A'ワーク創造館)　All Rights Reserved.</small>
</footer>

</body>
</html>


