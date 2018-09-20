<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet" type="text/css" />
<link href="https://fonts.googleapis.com/css?family=M+PLUS+1p" rel="stylesheet" type="text/css" />
<link href="css/bootstrap-reboot.css" rel="stylesheet" type="text/css" />
<link href="css/common.css" rel="stylesheet" type="text/css" />
<link href="css/header.css" rel="stylesheet" type="text/css" />
<link href="css/footer.css" rel="stylesheet" type="text/css" />
<link href="css/job_search.css" rel="stylesheet" type="text/css" />
<title>求人検索項目</title>
</head>
<body>
<!-- ヘッダー　-->
<header>
  <section>
    <h1 class="logo"><a href="#">LOGO</a></h1>
    <nav>
      <ul class="mainnavi">
        <li><a href="#"><i class="fas fa-home"></i>検索</a></li>
        <li> <a href="#"><i class="fas fa-search"></i>登録&amp;閲覧</a>
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
        <div class="user__name"> <a href="#">山田 太郎<i class="fas fa-ellipsis-v"></i></a>
          <ul class="drop-menu">
            <li><a href="#">サインアウト<i class="fas fa-angle-right"></i></a></li>
          </ul>
        </div>
      </div>
    </div>
  </section>
</header>
<main>
<div id="kyujinyoko">
<h3>求人票閲覧</h3>
<table width="100%" border="0">
  <tr>
    <th width="100" scope="col">求人要項</th>
    <th scope="col">&nbsp;</th>
  </tr>
  <tr>
    <td>求人No.</td>
    <td>4434209</td>
  </tr>
   <tr>
    <td>事業所名</td>
    <td>ABC株式会社</td>
  </tr>
  <tr>
    <td>就業場所</td>
    <td>奈良県大和高田市永和町2-14</td>
  </tr>
  <tr>
    <td>職種</td>
    <td>議員</td>
  </tr>
  <tr>
    <td>雇用形態</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>年齢制限</td>
    <td>20歳 ～ 70歳</td>
  </tr>
  <tr>
    <td>基本給</td>
    <td>100000円 ～ 200000円</td>
  </tr>
 </table>
  <button type="button" onclick="location.href='javascript:history.back()'">戻る</button>
</div>
	</main>
<!-- フッター　-->
<footer> <small>Copyright(C) 2009有限責任事業組合 大阪職業教育協働機構(A'ワーク創造館)　All Rights Reserved.</small> </footer>
</body>
</html>