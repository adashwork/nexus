<div>
<c:forEach var="comment" items="${ commentlist }">
<div id="wrap">
  <div class="comment">
		<h3>フリーコメント1</h3>
		<p>タイトル<p>
		<input class="comment-title" type="text" name="title" value="<c:out value="${ comment.title }"/>" >
		<p>内容<p>
		<textarea rows="4" cols="50" wrap="hard"><c:out value="${ comment.note }"/></textarea>
	</div>
  <div class="down">
		<div class="box">
			<p>登録ID：<c:out value="${ comment.createUserId }" /></p>
			<p>更新ID：<c:out value="${ comment.updateUserId }" /></p>
	  	</div>
 		<div class="box">
			<p>登録日:<c:out value="${ comment.createDt }" /></p>
		  	<p>更新日:<c:out value="${ comment.updateDt }" /></p>
	    </div>
  </div>
<form method="get" action="./comment-disp">
		<button class="main-b" type="button" name="commentid" value="<c:out value="${ comment.id }" />"
				onclick="WindowOpen()" >
		詳細
		</button>
</form>
</c:forEach>
</div>

<div style="clear:both;">
<form name="commentregist" action="./comment-disp">
	<button class="main-b" type="button" name="commentregist" onclick="WindowOpen()">
	コメント新規登録
	</button>
</form>
</div>

<!-- TODO headで読み込ませる -->
<script type="text/javascript" src="../js/comment.js"></script>
