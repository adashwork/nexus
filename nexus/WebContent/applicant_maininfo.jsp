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
<title>求職者詳細情報</title>
</head>
<body>
	<!-- ヘッダー　-->
	<header>
		<section>
			<h1 class="logo">
				<a href="/nexus/web/staff-top"><img
					src="../css/TryNexus-Logo.png" width="97" height="70" alt="TryNexus" /></a>
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
					<c:if test="${Staff.authority == 1}"><li><a href="/nexus/web/account-list"><i
							class="far fa-bookmark"></i>管理</a></li></c:if>
				</ul>
			</nav>
			<div class="user">
				<div class="user__wrapper">
					<div class="user__name">
						<a href="#"><c:out value="${ Staff.name }" /><i
							class="fas fa-ellipsis-v"></i></a>
						<ul class="drop-menu">
							<li><a href="/nexus/web/logout">ログアウト<i
									class="fas fa-angle-right"></i></a></li>
						</ul>
					</div>
				</div>
			</div>
		</section>
	</header>
	<main>
	<h2>求職者詳細情報</h2>
	<ul>
		<c:forEach var="message" items="${ messages }">
			<li><c:out value="${ message }" /></li>
		</c:forEach>
	</ul>
	<div id="job_seeker">
		<form action="/nexus/web/jobseeker-edit" method="post">
			<table border="0">

				<tr>
					<th width="20%">ID</th>
					<td><input type="hidden" name="id"
						value="<c:out value="${ info.id }" />">
				<fmt:formatNumber value="${ info.id }" pattern="00000000"/></td>
				</tr>
				<tr>
					<th>氏名</th>
					<td><input type="text" name="name" value="${ info.name }" /></td>
				</tr>
				<tr>
					<th>氏名(かな)</th>
					<td><input type="text" name="kana" value="${ info.kana }" /></td>
				</tr>
				<tr>
					<th>性別</th>
					<td><input type="radio" name="sex" value="${ info.sex  }"
						<c:if test="${ info.sex == 1 }">checked</c:if> />男 <input
						type="radio" name="sex" value="${ info.sex }"
						<c:if test="${ info.sex == 2 }">checked</c:if> />女</td>
				</tr>
				<tr>
					<th>生年月日</th>
					<td><input type="text" class="datepicker" name="birthdt"
						value="<fmt:formatDate value="${ info.birthdt }" pattern="yyyy-MM-dd"/>"></td>
				</tr>
				<!--<tr>
					<th>年齢</th>
					<td><select name="age">
							<option value="">-</option>
							<option value="0" <c:if test="${ info.age == 0 }">selected</c:if>>0</option>
							<option value="1" <c:if test="${ info.age == 1 }">selected</c:if>>1</option>
							<option value="2" <c:if test="${ info.age == 2 }">selected</c:if>>2</option>
							<option value="3" <c:if test="${ info.age == 3 }">selected</c:if>>3</option>
							<option value="4" <c:if test="${ info.age == 4 }">selected</c:if>>4</option>
							<option value="5" <c:if test="${ info.age == 5 }">selected</c:if>>5</option>
							<option value="6" <c:if test="${ info.age == 6 }">selected</c:if>>6</option>
							<option value="7" <c:if test="${ info.age == 7 }">selected</c:if>>7</option>
							<option value="8" <c:if test="${ info.age == 8 }">selected</c:if>>8</option>
							<option value="9" <c:if test="${ info.age == 9 }">selected</c:if>>9</option>
							<option value="10"
								<c:if test="${ info.age == 10 }">selected</c:if>>10</option>
							<option value="11"
								<c:if test="${ info.age == 11 }">selected</c:if>>11</option>
							<option value="12"
								<c:if test="${ info.age == 12 }">selected</c:if>>12</option>
							<option value="13"
								<c:if test="${ info.age == 13 }">selected</c:if>>13</option>
							<option value="14"
								<c:if test="${ info.age == 14 }">selected</c:if>>14</option>
							<option value="15"
								<c:if test="${ info.age == 15 }">selected</c:if>>15</option>
							<option value="16"
								<c:if test="${ info.age == 16 }">selected</c:if>>16</option>
							<option value="17"
								<c:if test="${ info.age == 17 }">selected</c:if>>17</option>
							<option value="18"
								<c:if test="${ info.age == 18 }">selected</c:if>>18</option>
							<option value="19"
								<c:if test="${ info.age == 19 }">selected</c:if>>19</option>
							<option value="20"
								<c:if test="${ info.age == 20 }">selected</c:if>>20</option>
							<option value="21"
								<c:if test="${ info.age == 21 }">selected</c:if>>21</option>
							<option value="22"
								<c:if test="${ info.age == 22 }">selected</c:if>>22</option>
							<option value="23"
								<c:if test="${ info.age == 23 }">selected</c:if>>23</option>
							<option value="24"
								<c:if test="${ info.age == 24 }">selected</c:if>>24</option>
							<option value="25"
								<c:if test="${ info.age == 25 }">selected</c:if>>25</option>
							<option value="26"
								<c:if test="${ info.age == 26 }">selected</c:if>>26</option>
							<option value="27"
								<c:if test="${ info.age == 27 }">selected</c:if>>27</option>
							<option value="28"
								<c:if test="${ info.age == 28 }">selected</c:if>>28</option>
							<option value="29"
								<c:if test="${ info.age == 29 }">selected</c:if>>29</option>
							<option value="30"
								<c:if test="${ info.age == 30 }">selected</c:if>>30</option>
							<option value="31"
								<c:if test="${ info.age == 31 }">selected</c:if>>31</option>
							<option value="32"
								<c:if test="${ info.age == 32 }">selected</c:if>>32</option>
							<option value="33"
								<c:if test="${ info.age == 33 }">selected</c:if>>33</option>
							<option value="34"
								<c:if test="${ info.age == 34 }">selected</c:if>>34</option>
							<option value="35"
								<c:if test="${ info.age == 35 }">selected</c:if>>35</option>
							<option value="36"
								<c:if test="${ info.age == 36 }">selected</c:if>>36</option>
							<option value="37"
								<c:if test="${ info.age == 37 }">selected</c:if>>37</option>
							<option value="38"
								<c:if test="${ info.age == 38 }">selected</c:if>>38</option>
							<option value="39"
								<c:if test="${ info.age == 39 }">selected</c:if>>39</option>
							<option value="40"
								<c:if test="${ info.age == 40 }">selected</c:if>>40</option>
							<option value="41"
								<c:if test="${ info.age == 41 }">selected</c:if>>41</option>
							<option value="42"
								<c:if test="${ info.age == 42 }">selected</c:if>>42</option>
							<option value="43"
								<c:if test="${ info.age == 43 }">selected</c:if>>43</option>
							<option value="44"
								<c:if test="${ info.age == 44 }">selected</c:if>>44</option>
							<option value="45"
								<c:if test="${ info.age == 45 }">selected</c:if>>45</option>
							<option value="46"
								<c:if test="${ info.age == 46 }">selected</c:if>>46</option>
							<option value="47"
								<c:if test="${ info.age == 47 }">selected</c:if>>47</option>
							<option value="48"
								<c:if test="${ info.age == 48 }">selected</c:if>>48</option>
							<option value="49"
								<c:if test="${ info.age == 49 }">selected</c:if>>49</option>
							<option value="50"
								<c:if test="${ info.age == 50 }">selected</c:if>>50</option>
							<option value="51"
								<c:if test="${ info.age == 51 }">selected</c:if>>51</option>
							<option value="52"
								<c:if test="${ info.age == 52 }">selected</c:if>>52</option>
							<option value="53"
								<c:if test="${ info.age == 53 }">selected</c:if>>53</option>
							<option value="54"
								<c:if test="${ info.age == 54 }">selected</c:if>>54</option>
							<option value="55"
								<c:if test="${ info.age == 55 }">selected</c:if>>55</option>
							<option value="56"
								<c:if test="${ info.age == 56 }">selected</c:if>>56</option>
							<option value="57"
								<c:if test="${ info.age == 57 }">selected</c:if>>57</option>
							<option value="58"
								<c:if test="${ info.age == 58 }">selected</c:if>>58</option>
							<option value="59"
								<c:if test="${ info.age == 59 }">selected</c:if>>59</option>
							<option value="60"
								<c:if test="${ info.age == 60 }">selected</c:if>>60</option>
							<option value="61"
								<c:if test="${ info.age == 61 }">selected</c:if>>61</option>
							<option value="62"
								<c:if test="${ info.age == 62 }">selected</c:if>>62</option>
							<option value="63"
								<c:if test="${ info.age == 63 }">selected</c:if>>63</option>
							<option value="64"
								<c:if test="${ info.age == 64 }">selected</c:if>>64</option>
							<option value="65"
								<c:if test="${ info.age == 65 }">selected</c:if>>65</option>
							<option value="66"
								<c:if test="${ info.age == 66 }">selected</c:if>>66</option>
							<option value="67"
								<c:if test="${ info.age == 67 }">selected</c:if>>67</option>
							<option value="68"
								<c:if test="${ info.age == 68 }">selected</c:if>>68</option>
							<option value="69"
								<c:if test="${ info.age == 69 }">selected</c:if>>69</option>
							<option value="70"
								<c:if test="${ info.age == 70 }">selected</c:if>>70</option>
							<option value="71"
								<c:if test="${ info.age == 71 }">selected</c:if>>71</option>
							<option value="72"
								<c:if test="${ info.age == 72 }">selected</c:if>>72</option>
							<option value="73"
								<c:if test="${ info.age == 73 }">selected</c:if>>73</option>
							<option value="74"
								<c:if test="${ info.age == 74 }">selected</c:if>>74</option>
							<option value="75"
								<c:if test="${ info.age == 75 }">selected</c:if>>75</option>
							<option value="76"
								<c:if test="${ info.age == 76 }">selected</c:if>>76</option>
							<option value="77"
								<c:if test="${ info.age == 77 }">selected</c:if>>77</option>
							<option value="78"
								<c:if test="${ info.age == 78 }">selected</c:if>>78</option>
							<option value="79"
								<c:if test="${ info.age == 79 }">selected</c:if>>79</option>
							<option value="80"
								<c:if test="${ info.age == 80 }">selected</c:if>>80</option>
							<option value="81"
								<c:if test="${ info.age == 81 }">selected</c:if>>81</option>
							<option value="82"
								<c:if test="${ info.age == 82 }">selected</c:if>>82</option>
							<option value="83"
								<c:if test="${ info.age == 83 }">selected</c:if>>83</option>
							<option value="84"
								<c:if test="${ info.age == 84 }">selected</c:if>>84</option>
							<option value="85"
								<c:if test="${ info.age == 85 }">selected</c:if>>85</option>
							<option value="86"
								<c:if test="${ info.age == 86 }">selected</c:if>>86</option>
							<option value="87"
								<c:if test="${ info.age == 87 }">selected</c:if>>87</option>
							<option value="88"
								<c:if test="${ info.age == 88 }">selected</c:if>>88</option>
							<option value="89"
								<c:if test="${ info.age == 89 }">selected</c:if>>89</option>
							<option value="90"
								<c:if test="${ info.age == 90 }">selected</c:if>>90</option>
							<option value="91"
								<c:if test="${ info.age == 91 }">selected</c:if>>91</option>
							<option value="92"
								<c:if test="${ info.age == 92 }">selected</c:if>>92</option>
							<option value="93"
								<c:if test="${ info.age == 93 }">selected</c:if>>93</option>
							<option value="94"
								<c:if test="${ info.age == 94 }">selected</c:if>>94</option>
							<option value="95"
								<c:if test="${ info.age == 95 }">selected</c:if>>95</option>
							<option value="96"
								<c:if test="${ info.age == 96 }">selected</c:if>>96</option>
							<option value="97"
								<c:if test="${ info.age == 97 }">selected</c:if>>97</option>
							<option value="98"
								<c:if test="${ info.age == 98 }">selected</c:if>>98</option>
							<option value="99"
								<c:if test="${ info.age == 99 }">selected</c:if>>99</option>
					</select>歳</td>
				</tr>
				<tr>
					<th>郵便番号</th>
					<td><input type="text" name="postal" value="${ info.postal }" /></td>
				</tr>
				<tr>
					<th>住所</th>
					<td><input type="text" name="address"
						value="${ info.address }" /></td>
				</tr>
				<tr>
					<th>最寄り駅</th>
					<td><input type="text" name="nearstation"
						value="${ info.nearstation }" /></td>
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
					<th>扶養人数</th>
					<td><select name="huyou">
							<option value="">-</option>
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
							<option value="50"
								<c:if test="${ info.huyou == 50 }">selected</c:if>>50</option>
							<option value="51"
								<c:if test="${ info.huyou == 51 }">selected</c:if>>51</option>
							<option value="52"
								<c:if test="${ info.huyou == 52 }">selected</c:if>>52</option>
							<option value="53"
								<c:if test="${ info.huyou == 53 }">selected</c:if>>53</option>
							<option value="54"
								<c:if test="${ info.huyou == 54 }">selected</c:if>>54</option>
							<option value="55"
								<c:if test="${ info.huyou == 55 }">selected</c:if>>55</option>
							<option value="56"
								<c:if test="${ info.huyou == 56 }">selected</c:if>>56</option>
							<option value="57"
								<c:if test="${ info.huyou == 57 }">selected</c:if>>57</option>
							<option value="58"
								<c:if test="${ info.huyou == 58 }">selected</c:if>>58</option>
							<option value="59"
								<c:if test="${ info.huyou == 59 }">selected</c:if>>59</option>
							<option value="60"
								<c:if test="${ info.huyou == 60 }">selected</c:if>>60</option>
							<option value="61"
								<c:if test="${ info.huyou == 61 }">selected</c:if>>61</option>
							<option value="62"
								<c:if test="${ info.huyou == 62 }">selected</c:if>>62</option>
							<option value="63"
								<c:if test="${ info.huyou == 63 }">selected</c:if>>63</option>
							<option value="64"
								<c:if test="${ info.huyou == 64 }">selected</c:if>>64</option>
							<option value="65"
								<c:if test="${ info.huyou == 65 }">selected</c:if>>65</option>
							<option value="66"
								<c:if test="${ info.huyou == 66 }">selected</c:if>>66</option>
							<option value="67"
								<c:if test="${ info.huyou == 67 }">selected</c:if>>67</option>
							<option value="68"
								<c:if test="${ info.huyou == 68 }">selected</c:if>>68</option>
							<option value="69"
								<c:if test="${ info.huyou == 69 }">selected</c:if>>69</option>
							<option value="70"
								<c:if test="${ info.huyou == 70 }">selected</c:if>>70</option>
							<option value="71"
								<c:if test="${ info.huyou == 71 }">selected</c:if>>71</option>
							<option value="72"
								<c:if test="${ info.huyou == 72 }">selected</c:if>>72</option>
							<option value="73"
								<c:if test="${ info.huyou == 73 }">selected</c:if>>73</option>
							<option value="74"
								<c:if test="${ info.huyou == 74 }">selected</c:if>>74</option>
							<option value="75"
								<c:if test="${ info.huyou == 75 }">selected</c:if>>75</option>
							<option value="76"
								<c:if test="${ info.huyou == 76 }">selected</c:if>>76</option>
							<option value="77"
								<c:if test="${ info.huyou == 77 }">selected</c:if>>77</option>
							<option value="78"
								<c:if test="${ info.huyou == 78 }">selected</c:if>>78</option>
							<option value="79"
								<c:if test="${ info.huyou == 79 }">selected</c:if>>79</option>
							<option value="80"
								<c:if test="${ info.huyou == 80 }">selected</c:if>>80</option>
							<option value="81"
								<c:if test="${ info.huyou == 81 }">selected</c:if>>81</option>
							<option value="82"
								<c:if test="${ info.huyou == 82 }">selected</c:if>>82</option>
							<option value="83"
								<c:if test="${ info.huyou == 83 }">selected</c:if>>83</option>
							<option value="84"
								<c:if test="${ info.huyou == 84 }">selected</c:if>>84</option>
							<option value="85"
								<c:if test="${ info.huyou == 85 }">selected</c:if>>85</option>
							<option value="86"
								<c:if test="${ info.huyou == 86 }">selected</c:if>>86</option>
							<option value="87"
								<c:if test="${ info.huyou == 87 }">selected</c:if>>87</option>
							<option value="88"
								<c:if test="${ info.huyou == 88 }">selected</c:if>>88</option>
							<option value="89"
								<c:if test="${ info.huyou == 89 }">selected</c:if>>89</option>
							<option value="90"
								<c:if test="${ info.huyou == 90 }">selected</c:if>>90</option>
							<option value="91"
								<c:if test="${ info.huyou == 91 }">selected</c:if>>91</option>
							<option value="92"
								<c:if test="${ info.huyou == 92 }">selected</c:if>>92</option>
							<option value="93"
								<c:if test="${ info.huyou == 93 }">selected</c:if>>93</option>
							<option value="94"
								<c:if test="${ info.huyou == 94 }">selected</c:if>>94</option>
							<option value="95"
								<c:if test="${ info.huyou == 95 }">selected</c:if>>95</option>
							<option value="96"
								<c:if test="${ info.huyou == 96 }">selected</c:if>>96</option>
							<option value="97"
								<c:if test="${ info.huyou == 97 }">selected</c:if>>97</option>
							<option value="98"
								<c:if test="${ info.huyou == 98 }">selected</c:if>>98</option>
							<option value="99"
								<c:if test="${ info.huyou == 99 }">selected</c:if>>99</option>
					</select>人</td>
				<tr>
					<th>学歴</th>
					<td><input type="text" name="education"
						value="${ info.education }" /></td>
				</tr>
				<tr>
					<th>希望職種1</th>
					<td><select name="hopejob1">
							<c:forEach var="job" items="${ Largelist }">
								<option value="${ job.largecd }" ${ job.name }
									<c:if test="${job.largecd == info.hopejob1 }">selected</c:if>>${ job.name }
								</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>希望職種2</th>
					<td><select name="hopejob2">
							<c:forEach var="job" items="${ Largelist }">
								<option value="${ job.largecd }" ${ job.name }
									<c:if test="${job.largecd == info.hopejob2 }">selected</c:if>>${ job.name }
								</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>希望職種3</th>
					<td><select name="hopejob3">
							<c:forEach var="job" items="${ Largelist }">
								<option value="${ job.largecd }" ${ job.name }
									<c:if test="${job.largecd == info.hopejob3 }">selected</c:if>>${ job.name }
								</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>希望業種</th>
					<td><select name="hopejobcategory">
							<c:forEach var="jobcategory" items="${ JCLargelist }">
								<option value="${ jobcategory.largecd }" ${ jobcategory.name }
									<c:if test="${jobcategory.largecd == info.hopejobcategory }">selected</c:if>>${ jobcategory.name }
								</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<th>希望勤務地</th>
					<td><select name="hopeworkplace">
							<c:forEach var="todouhuken" items="${ Todouhukenlist }">
								<option value="${ todouhuken.cd }"
									<c:if test="${todouhuken.cd == info.hopeworkplace }">selected</c:if>>${ todouhuken.name }
								</option>
							</c:forEach>
					</select></td>
				<tr>
					<th>希望雇用形態</th>
					<td><input type="radio" name="hopekoyoukeitai" value="1"
						<c:if test="${ info.hopekoyoukeitai == 1 }">checked</c:if> /> 正社員
						<input type="radio" name="hopekoyoukeitai" value="2"
						<c:if test="${ info.hopekoyoukeitai == 2 }">checked</c:if> />
						正社員以外 <input type="radio" name="hopekoyoukeitai" value="3"
						<c:if test="${ info.hopekoyoukeitai == 3 }">checked</c:if> />
						有期雇用派遣 <input type="radio" name="hopekoyoukeitai" value="4"
						<c:if test="${ info.hopekoyoukeitai == 4 }">checked</c:if> />
						無期雇用派遣</td>
				</tr>
				<tr>
					<th>希望勤務日時</th>
					<td><input type="text" name="hopeworkingDate"
						value="${ info.hopeworkingDate }" /></td>
				</tr>
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
						value="${ info.hopesalary }" /></td>
				</tr>
				<tr>
					<th>希望時間給</th>
					<td><input type="text" name="hopejikyu"
						value="${ info.hopejikyu }" /></td>
				</tr>
				<tr>
					<th>その他希望</th>
					<td><textarea rows="4" cols="40" name="hopeetc"><c:out
								value="${ info.hopeetc }" /></textarea></td>
				</tr>
				<tr>
					<th>自動車免許</th>
					<td><input type="radio" name="driverlicense" value="0"
						<c:if test="${ info.driverlicense == 0 }">checked</c:if> /> 無し <input
						type="radio" name="driverlicense" value="1"
						<c:if test="${ info.driverlicense == 1 }">checked</c:if> /> 普通 <input
						type="radio" name="driverlicense" value="2"
						<c:if test="${ info.driverlicense == 2 }">checked</c:if> /> AT限定
						<input type="radio" name="driverlicense" value="3"
						<c:if test="${ info.driverlicense == 3 }">checked</c:if> /> 中型 <input
						type="radio" name="driverlicense" value="4"
						<c:if test="${ info.driverlicense == 4 }">checked</c:if> /> 大型 <input
						type="radio" name="driverlicense" value="5"
						<c:if test="${ info.driverlicense == 5 }">checked</c:if> /> 原付 <input
						type="radio" name="driverlicense" value="6"
						<c:if test="${ info.driverlicense == 6 }">checked</c:if> /> 2種</td>
				</tr>
				<tr>
					<th>その他免許</th>
					<td><textarea rows="4" cols="40" name="licenseetc"><c:out
								value="${ info.licenseetc }" /></textarea></td>
				</tr>
				<tr>
					<th>パソコンスキル</th>
					<td><textarea rows="4" cols="40" name="pasokonskill"><c:out
								value="${ info.pasokonskill }" /></textarea></td>
				</tr>
				<tr>
					<th>留意点</th>
					<td><textarea rows="4" cols="40" name="caution"><c:out
								value="${ info.caution }" /></textarea></td>
				</tr>
				<tr>
					<th>担当職業者紹介者氏名</th>
					<td><input type="hidden" name="tantoustaffname"
						value="<c:out value="${ info.tantoustaffname }" />"> <c:out
							value="${ info.tantoustaffname }" /></td>
				</tr>
				<tr>
					<th>担当職業者紹介者ID</th>
					<td><input type="hidden" name="tantoustaffid"
						value="<c:out value="${ info.tantoustaffid }" />"> <c:out
							value="${ info.tantoustaffid }" /></td>
				</tr>
			</table>
			<input type="submit" value="更新" class="main-b">
		</form>
		<input class="main-b" type="button"
			onclick="location.href='/nexus/web/jobseeker-list'" value="一覧に戻る">
	</div>
	</main>
	<!-- フッター　-->
	<footer>
		<small>Copyright(C) 2009有限責任事業組合 大阪職業教育協働機構(A'ワーク創造館) All
			Rights Reserved.</small>
	</footer>

	<script src="js/kalendae.standalone.js" type="text/javascript"
		charset="utf-8"></script>
</body>
</html>