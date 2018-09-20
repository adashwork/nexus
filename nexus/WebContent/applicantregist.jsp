<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<script src="build/kalendae.standalone.js"type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" href="css/kalendae.css" type="text/css" />
<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet" type="text/css" />
<link href="https://fonts.googleapis.com/css?family=M+PLUS+1p" rel="stylesheet" type="text/css" />
<link href="../css/bootstrap-reboot.css" rel="stylesheet" type="text/css" />
<link href="../css/common.css" rel="stylesheet" type="text/css" />
<link href="../css/header.css" rel="stylesheet" type="text/css" />
<link href="../css/footer.css" rel="stylesheet" type="text/css" />
<link href="../css/job_seeker.css" rel="stylesheet" type="text/css" />
<title>求職者情報登録</title>
</head>
<body><!-- ヘッダー　-->
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
<h2>求職者情報登録</h2>
<div id="job_seeker">
	<ul>
	<c:forEach var="message" items="${ messages }">
		<li><c:out value="${ message }" /></li>
	</c:forEach>
	</ul>

	<form method="post" action="./regist">
		<table width="" border="0">
		<!-- <tr>
			<th width="20%">ID</th>
			<td>
			  <input type="text" name="id" value="<c:out value="${ seeker.id }" />">
				<input type="hidden" name="hiddenid" value="<c:out value="${ seeker.id }" />">
			</td>
		</tr> -->
		<tr>
			<th width="20%">名前</th>
			<td><input type="text" name="name" value="<c:out value="${ seeker.name }" />"></td>
		</tr>
		<tr>
			<th>ふりがな</th>
			<td><input type="text" name="kana" value="<c:out value="${ seeker.kana }" />"></td>
		</tr>
		<tr>
			<th>生年月日</th>
			<td><input type="date" name="birthdt" value="<c:out value="${ seeker.birthdt }" />"></td>
		</tr>
		<tr>
			<th>性別</th>
			<td><input type="radio" name="sex" value="男" /> 男
				<input type="radio" name="sex" value="女" /> 女</td>
<!--  			<input type="text" name="sex" value="<c:out value="${ seeker.sex }" />">-->
		</tr>
		<tr>
			<th>年齢</th>
			<td><select name="age">
<option value="">-</option>
<option value="0">0</option>
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>
<option value="6">6</option>
<option value="7">7</option>
<option value="8">8</option>
<option value="9">9</option>
<option value="10">10</option>
<option value="11">11</option>
<option value="12">12</option>
<option value="13">13</option>
<option value="14">14</option>
<option value="15">15</option>
<option value="16">16</option>
<option value="17">17</option>
<option value="18">18</option>
<option value="19">19</option>
<option value="20">20</option>
<option value="21">21</option>
<option value="22">22</option>
<option value="23">23</option>
<option value="24">24</option>
<option value="25">25</option>
<option value="26">26</option>
<option value="27">27</option>
<option value="28">28</option>
<option value="29">29</option>
<option value="30">30</option>
<option value="31">31</option>
<option value="32">32</option>
<option value="33">33</option>
<option value="34">34</option>
<option value="35">35</option>
<option value="36">36</option>
<option value="37">37</option>
<option value="38">38</option>
<option value="39">39</option>
<option value="40">40</option>
<option value="41">41</option>
<option value="42">42</option>
<option value="43">43</option>
<option value="44">44</option>
<option value="45">45</option>
<option value="46">46</option>
<option value="47">47</option>
<option value="48">48</option>
<option value="49">49</option>
<option value="50">50</option>
<option value="51">51</option>
<option value="52">52</option>
<option value="53">53</option>
<option value="54">54</option>
<option value="55">55</option>
<option value="56">56</option>
<option value="57">57</option>
<option value="58">58</option>
<option value="59">59</option>
<option value="60">60</option>
<option value="61">61</option>
<option value="62">62</option>
<option value="63">63</option>
<option value="64">64</option>
<option value="65">65</option>
<option value="66">66</option>
<option value="67">67</option>
<option value="68">68</option>
<option value="69">69</option>
<option value="70">70</option>
<option value="71">71</option>
<option value="72">72</option>
<option value="73">73</option>
<option value="74">74</option>
<option value="75">75</option>
<option value="76">76</option>
<option value="77">77</option>
<option value="78">78</option>
<option value="79">79</option>
<option value="80">80</option>
<option value="81">81</option>
<option value="82">82</option>
<option value="83">83</option>
<option value="84">84</option>
<option value="85">85</option>
<option value="86">86</option>
<option value="87">87</option>
<option value="88">88</option>
<option value="89">89</option>
<option value="90">90</option>
<option value="91">91</option>
<option value="92">92</option>
<option value="93">93</option>
<option value="94">94</option>
<option value="95">95</option>
<option value="96">96</option>
<option value="97">97</option>
<option value="98">98</option>
<option value="99">99</option>
</select>歳</td>
<!-- <input type="text" name="age" value="<c:out value="${ seeker.age }" />"> -->
		</tr>
		<tr>
			<th>郵便番号</th>
			<td><input type="text" name="postal" value="<c:out value="${ seeker.postal }" />"></td>
		</tr>
		<tr>
			<th>住所</th>
			<td><input type="text" name="address" value="<c:out value="${ seeker.address }" />"></td>
		</tr>
		<tr>
			<th>最寄り駅</th>
			<td><input type="text" name="nearstation" value="<c:out value="${ seeker.nearstation }" />"></td>
		</tr>
		<tr>
			<th>自宅電話番号</th>
			<td><input type="text" name="phone" value="<c:out value="${ seeker.phone }" />"></td>
		</tr>
		<tr>
			<th>携帯電話番号</th>
			<td><input type="text" name="mobile" value="<c:out value="${ seeker.mobile }" />"></td>
		</tr>
		<tr>
			<th>配偶者</th>
			<td><input type="radio" name="partner" value="0" checked/> 無
				<input type="radio" name="partner" value="1" /> 有</td>
			<!--  <input type="text" name="partner" value="<c:out value="${ seeker.partner }" />">-->
		</tr>
		<tr>
			<th>扶養人数</th>
			<td><select name="huyou">
<option value="">-</option>
<option value="0">0</option>
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>
<option value="6">6</option>
<option value="7">7</option>
<option value="8">8</option>
<option value="9">9</option>
<option value="10">10</option>
<option value="11">11</option>
<option value="12">12</option>
<option value="13">13</option>
<option value="14">14</option>
<option value="15">15</option>
<option value="16">16</option>
<option value="17">17</option>
<option value="18">18</option>
<option value="19">19</option>
<option value="20">20</option>
<option value="21">21</option>
<option value="22">22</option>
<option value="23">23</option>
<option value="24">24</option>
<option value="25">25</option>
<option value="26">26</option>
<option value="27">27</option>
<option value="28">28</option>
<option value="29">29</option>
<option value="30">30</option>
<option value="31">31</option>
<option value="32">32</option>
<option value="33">33</option>
<option value="34">34</option>
<option value="35">35</option>
<option value="36">36</option>
<option value="37">37</option>
<option value="38">38</option>
<option value="39">39</option>
<option value="40">40</option>
<option value="41">41</option>
<option value="42">42</option>
<option value="43">43</option>
<option value="44">44</option>
<option value="45">45</option>
<option value="46">46</option>
<option value="47">47</option>
<option value="48">48</option>
<option value="49">49</option>
<option value="50">50</option>
<option value="51">51</option>
<option value="52">52</option>
<option value="53">53</option>
<option value="54">54</option>
<option value="55">55</option>
<option value="56">56</option>
<option value="57">57</option>
<option value="58">58</option>
<option value="59">59</option>
<option value="60">60</option>
<option value="61">61</option>
<option value="62">62</option>
<option value="63">63</option>
<option value="64">64</option>
<option value="65">65</option>
<option value="66">66</option>
<option value="67">67</option>
<option value="68">68</option>
<option value="69">69</option>
<option value="70">70</option>
<option value="71">71</option>
<option value="72">72</option>
<option value="73">73</option>
<option value="74">74</option>
<option value="75">75</option>
<option value="76">76</option>
<option value="77">77</option>
<option value="78">78</option>
<option value="79">79</option>
<option value="80">80</option>
<option value="81">81</option>
<option value="82">82</option>
<option value="83">83</option>
<option value="84">84</option>
<option value="85">85</option>
<option value="86">86</option>
<option value="87">87</option>
<option value="88">88</option>
<option value="89">89</option>
<option value="90">90</option>
<option value="91">91</option>
<option value="92">92</option>
<option value="93">93</option>
<option value="94">94</option>
<option value="95">95</option>
<option value="96">96</option>
<option value="97">97</option>
<option value="98">98</option>
<option value="99">99</option>
</select>人</td>
<!-- <input type="text" name="huyou" value="<c:out value="${ seeker.huyou }" />"></td> -->
		</tr>
		<tr>
			<th>学歴</th>
			<td><input type="text" name="education" value="<c:out value="${ seeker.education }" />"></td>
		</tr>
		<tr>
			<th>希望職種1</th>
			<td><input type="text" name="hopejob1" value="<c:out value="${ seeker.hopejob1 }" />"></td>
		</tr>
		<tr>
			<th>希望職種2</th>
			<td><input type="text" name="hopejob2" value="<c:out value="${ seeker.hopejob2 }" />"></td>
		</tr>
		<tr>
			<th>希望職種3</th>
			<td><input type="text" name="hopejob3" value="<c:out value="${ seeker.hopejob3 }" />"></td>
		</tr>
		<tr>
			<th>希望業種</th>
			<td><input type="text" name="hopejobcategory" value="<c:out value="${ seeker.hopejobcategory }" />"></td>
		</tr>
		<tr>
			<th>希望勤務地</th>
			<td><select name="hopeworkplace">
<option value="" selected>都道府県</option>
<option value="01">北海道</option>
<option value="02">青森県</option>
<option value="03">岩手県</option>
<option value="04">宮城県</option>
<option value="05">秋田県</option>
<option value="06">山形県</option>
<option value="07">福島県</option>
<option value="08">茨城県</option>
<option value="09">栃木県</option>
<option value="10">群馬県</option>
<option value="11">埼玉県</option>
<option value="12">千葉県</option>
<option value="13">東京都</option>
<option value="14">神奈川県</option>
<option value="15">新潟県</option>
<option value="16">富山県</option>
<option value="17">石川県</option>
<option value="18">福井県</option>
<option value="19">山梨県</option>
<option value="20">長野県</option>
<option value="21">岐阜県</option>
<option value="22">静岡県</option>
<option value="23">愛知県</option>
<option value="24">三重県</option>
<option value="25">滋賀県</option>
<option value="26">京都府</option>
<option value="27">大阪府</option>
<option value="28">兵庫県</option>
<option value="29">奈良県</option>
<option value="30">和歌山県</option>
<option value="31">鳥取県</option>
<option value="32">島根県</option>
<option value="33">岡山県</option>
<option value="34">広島県</option>
<option value="35">山口県</option>
<option value="36">徳島県</option>
<option value="37">香川県</option>
<option value="38">愛媛県</option>
<option value="39">高知県</option>
<option value="40">福岡県</option>
<option value="41">佐賀県</option>
<option value="42">長崎県</option>
<option value="43">熊本県</option>
<option value="44">大分県</option>
<option value="45">宮崎県</option>
<option value="46">鹿児島県</option>
<option value="47">沖縄県</option>
</select></td>
			<!-- <input type="text" name="hopeworkplace" value="<c:out value="${ seeker.hopeworkplace }" />"> -->
		</tr>
		<tr>
			<th>希望雇用形態</th>
			<td><input type="radio" name="hopekoyoukeitai" value="1" /> 正社員
				<input type="radio" name="hopekoyoukeitai" value="2" /> 正社員以外
				<input type="radio" name="hopekoyoukeitai" value="3" /> 有期雇用派遣
				<input type="radio" name="hopekoyoukeitai" value="4" /> 無期雇用派遣</td>
			<!-- <input type="text" name="hopekoyoukeitai" value="<c:out value="${ seeker.hopekoyoukeitai }" />"> -->
		</tr>
		<tr>
			<th>希望勤務日時</th>
			<td>
			<input type="text" name="hopeworkingDate" value="<c:out value="${ seeker.hopeworkingDate }" />"></td>
		</tr>
		<tr>
			<th>希望勤務時間(開始)</th>
			<td><input type="text" name="hopebegintime" value="<c:out value="${ seeker.hopebegintime }" />"></td>
		</tr>
		<tr>
			<th>希望勤務時間(終了)</th>
			<td><input type="text" name="hopeendtime" value="<c:out value="${ seeker.hopeendtime }" />"></td>
		</tr>
		<tr>
			<th>希望月給</th>
			<td><input type="text" name="hopesalary" value="<c:out value="${ seeker.hopesalary }" />"></td>
		</tr>
		<tr>
			<th>希望時間給</th>
			<td><input type="text" name="hopejikyu" value="<c:out value="${ seeker.hopejikyu }" />"></td>
		</tr>
		<tr>
			<th>その他希望</th>
			<td><input type="text" name="hopeetc" value="<c:out value="${ seeker.hopeetc }" />"></td>
		</tr>
		<tr>
			<th>自動車免許</th>
			<td><input type="radio" name="driverlicense" value="0" checked/> 無し
				<input type="radio" name="driverlicense" value="1" /> 普通
				<input type="radio" name="driverlicense" value="2" /> AT限定
				<input type="radio" name="driverlicense" value="3" /> 中型
				<input type="radio" name="driverlicense" value="4" /> 大型
				<input type="radio" name="driverlicense" value="5" /> 原付
				<input type="radio" name="driverlicense" value="6" /> 2種</td>
			<!-- <input type="text" name="driverlicense" value="<c:out value="${ seeker.driverlicense }" />"> -->
		</tr>
		<tr>
			<th>その他免許</th>
			<td><input type="text" name="licenseetc" value="<c:out value="${ seeker.licenseetc }" />"></td>
		</tr>
		<tr>
			<th>パソコンスキル</th>
			<td><input type="text" name="pasokonskill" value="<c:out value="${ seeker.pasokonskill }" />"></td>
		</tr>
		<tr>
			<th>留意点</th>
			<td><input type="textarea" rows="4" cols="40" name="caution" value="<c:out value="${ seeker.caution }" />"></td>
		</tr>
		<tr>
			<th>担当職業者紹介者ID</th>
			<td><input type="text" name="tantoustaffid" value="<c:out value="${ seeker.tantoustaffid }" />"></td>
		</tr>
		<tr>
			<th>パスワード</th>
			<td><input type="text" name="password" value="<c:out value="${ seeker.password }" />"></td>
		</tr>
	</table>
		<input class="main-b" type="submit" value="登録">
	</form>
	<form action="../jobseekerservlet" method="get">
            <input type="submit" class="main-b" name="send" value="一覧に戻る">
        </form>
    </div>
    </main>
	<!-- フッター　-->
<footer> <small>Copyright(C) 2009有限責任事業組合 大阪職業教育協働機構(A'ワーク創造館)　All Rights Reserved.</small> </footer>
</body>
</html>