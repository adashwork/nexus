<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">

<head>

<meta charset="UTF-8">

<title>ログイン</title>
<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css"rel="stylesheet" >
<link href="./css?family=M+PLUS+1p" rel="stylesheet">
<link href="./css/bootstrap-reboot.css" rel="stylesheet">
<link href="./css/common.css" rel="stylesheet">
<link href="./css/header.css" rel="stylesheet">
<link href="./css/footer.css" rel="stylesheet">
<link href="./css/stafflogin.css" rel="stylesheet">

</head>

<body>
<header>
  <section>
			<h1 class="logo">
				<a href="/nexus/top"><img src="./css/TryNexus-Logo.png"
					width="97" height="70" alt="TryNexus" /></a>
			</h1>
			<div class="user">
      <div class="user__wrapper">
        <!-- <div class="user__image"></div> -->
        <div class="user__name">
          <a href="#"><i class="fas fa-ellipsis-v"></i></a>
          <ul class="drop-menu">
          </ul>
        </div>
      </div>
    </div>
  </section>
</header>

<div><!--　コンテナー-->
	<div>
		<h1>ログイン</h1>
	</div>
    <!--ID&PW入力フォーム-->
    <div>
    <p style="color:red">
    ${message}
    </p>
    <form action="/nexus/login" method="POST">
    <table>
    	<tr>
			<th rowspan="2">ID</th>
		</tr>
        <tr>
			<td><input type="text" id="id" name="id" required></td>
		</tr>
        <tr>
			<th rowspan="2">パスワード</th>
		</tr>
			<tr>
				<td><input type="password" id="pass" name="pass" required></td>
			</tr>
	</table>
	<div>
    	<input class="main-b" type=submit value="ログイン">
    	<input class="main-b" type="reset"value="リセット">
	</div>
    </form>

    </div>


    <!--ログインできないとき -->
	<div>
		<a href="/nexus/login-message">ログインできない／アカウントをお持ちでない場合</a>
	</div>
</div><!--　コンテナ終了-->
<body>
<!--フッター-->
<footer>
  <small>Copyright(C) 2009有限責任事業組合 大阪職業教育協働機構(A'ワーク創造館)　All Rights Reserved.</small>
</footer>

<script>
history.forward();
</script>
</body>
</html>
