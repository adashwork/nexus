<%@ page pageEncoding="UTF-8"%>
<header>
	<section>
		<h1 class="logo">
			<a href="/nexus/web/staff-top"><img
				src="../css/TryNexus-Logo.png" width="97" height="70" alt="TryNexus" /></a>
		</h1>
		<nav>
			<ul class="mainnavi">
				<li><a href="#"><i
						class="fas fa-search"></i>検索</a>
					<ul class="drop-menu">
						<li><a href="/nexus/web/company-search">企業検索 <i
								class="fas fa-angle-right"></i></a></li>
						<li><a href="/nexus/web/job-search">求人検索<i
								class="fas fa-angle-right"></i></a></li>
						<li><a href="/nexus/web/jobseeker-list">求職者検索<i
								class="fas fa-angle-right"></i></a></li>
						<li><a href="/nexus/web/matching-search">マッチング検索 <i
								class="fas fa-angle-right"></i></a></li>
					</ul></li>


				<li><a href="#"><i class="fas fa-save"></i>登録</a>
					<ul class="drop-menu">
						<li><a href="/nexus/web/company-registdisp">企業登録<i
								class="fas fa-angle-right"></i></a></li>
						<li><a href="/nexus/web/kyujin-disp">求人登録<i
								class="fas fa-angle-right"></i></a></li>
						<li><a href="/nexus/web/jobseeker-disp#tab1">求職者登録<i
								class="fas fa-angle-right"></i></a></li>
						<li><a href="/nexus/web/matching-registdisp">マッチング登録<i
								class="fas fa-angle-right"></i></a></li>
					</ul></li>
				<c:if test="${Staff.authority == '1'}">
					<li><a href="#"><i
							class="far fa-bookmark"></i>管理</a>
					<ul class="drop-menu">
						<li><a href="/nexus/web/account-list">アカウント<i
								class="fas fa-angle-right"></i></a></li>
					</ul></li>
				</c:if>
			</ul>
		</nav>
		<div class="user">
			<div class="user__wrapper">
				<div class="user__name">
					<a href="#"> <c:out value="${ Staff.name }" /> <i
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