<%@ page pageEncoding="UTF-8"%>
<header>
	<section>
		<h1 class="logo">
			<a href="/nexus/web/staff-top"><img
				src="../css/TryNexus-Logo.png" width="97" height="70" alt="TryNexus" /></a>
		</h1>
		<nav>
			<ul class="mainnavi">
				<li><a href="/nexus/web/job-search"><i class="fas fa-home"></i>検索</a>
					<ul class="drop-menu">
						<li><a href="/nexus/web/companysearch">企業検索 <i
								class="fas fa-angle-right"></i></a></li>
						<li><a href="/nexus/web/job-search">求人検索<i
								class="fas fa-angle-right"></i></a></li>
						<li><a href="/nexus/web/jobseeker-list">求職者検索<i
								class="fas fa-angle-right"></i></a></li>
						<li><a href="/nexus/web/matching-regist">マッチング検索 <i
								class="fas fa-angle-right"></i></a></li>
					</ul></li>


				<li><a href="/nexus/web/jobseeker-list"><i
						class="fas fa-search"></i>登録&amp;閲覧</a>
					<ul class="drop-menu">
						<li><a href="/nexus/web/company-registdisp">企業登録<i
								class="fas fa-angle-right"></i></a></li>
						<li><a href="/nexus/web/kyujin-disp">求人登録<i
								class="fas fa-angle-right"></i></a></li>
						<li><a href="/nexus/web/jobseeker-disp">求職者登録<i
								class="fas fa-angle-right"></i></a></li>
						<li><a href="/nexus/web/match-disp">マッチング登録<i
								class="fas fa-angle-right"></i></a></li>
					</ul></li>
				<c:if test="${Staff.authority == '1'}">
					<li><a href="/nexus/web/account-list"><i
							class="far fa-bookmark"></i>管理</a></li>
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