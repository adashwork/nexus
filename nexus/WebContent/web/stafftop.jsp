<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>実習</title>
<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css"rel="stylesheet" >
<link href="https://fonts.googleapis.com/css?family=M+PLUS+1p" rel="stylesheet">
<link href="css/bootstrap-reboot.css" rel="stylesheet">
<link href="css/applicantlist.css" rel="stylesheet">
<link href="css/common.css" rel="stylesheet">
<link href="css/header.css" rel="stylesheet">
<link href="css/footer.css" rel="stylesheet">
<link href="css/stafftop.css" rel="stylesheet">
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

<div><!--　コンテナー-->
	<div>
		<h2>トップページ</h2>
	</div>
	<!-- 検索メニュー-->
	<div>
    <table class="staff-table">
    	<tr>
    	<th>
			<label>検索</label>
		</th>
        </tr>
        <tr>
        <td><a href="./jobsearch">■求人検索</a></td>
        </tr>
    </table>
	</div>

    <!-- 登録＆閲覧メニュー-->
	<div>
    <table class="staff-table">
    	<tr>
    	<th>
			<label>登録＆閲覧</label>
		</th>
        </tr>
        <tr>
        <td><a href="./job/detail">■求人情報</a></td>
        </tr>
        <tr>
        <td><a href="./jobseekerservlet">■求職者情報</a></td>
        </tr>
        <tr>
        <td>■企業マスター</td>
        </tr>
        <tr>
        <td><a href="./matchingservlet">■マッチング登録</a></td>
        </tr>
    </table>
	</div>

    <!-- 管理メニュー-->
	<div>
    <table class="staff-table">
    	<tr>
    	<th>
			<label>管理</label>
		</th>
        </tr>
        <tr>
        <td>■アカウント</td>
        </tr>
    </table>
	</div>

	<div class="clear">
	<form name= "logout" method="GET" action="logoutservlet">
    <input class="main-b" type="submit" name="close" value="ログアウト">
    </form>
	</div>



</div><!--　コンテナ終了-->

<footer>
  <small>Copyright(C) 2009有限責任事業組合 大阪職業教育協働機構(A'ワーク創造館)　All Rights Reserved.</small>
</footer>

</body>
</html>
