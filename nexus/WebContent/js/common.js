/**
 * Javascriptの機能を一つにまとめたファイル
 * カレンダーの表示、アカウント削除の確認、
 * ウィンドウのクローズ、IDごとにURL遷移先の変更
 * @author pgjavaAT
 */



// カレンダー classタグに「datepiker」を設定すればカレンダーが表示される
$(function() {
	  $('.datepicker').datepicker({
	      dateFormat: 'yy-mm-dd',
	  });
  });

// 削除確認
function kakunin(){
	 if(!confirm("アカウントを削除します。本当によろしいですか？")){
		 return false;
	 }

	  /*if (ret == true){
	    location.href = "/nexus/web/account-delete?id='1234'";
	  }*/
	}
/**
*
*/
//ウィンドウをクローズする
function CloseWindow(){
	window.open('about:blank','_self').close();
}

//ele.idを元に、formタグのactionのURLを再設定する
//type="submit"のみ使用可 formにid="form"を設定して使用
function MovePages(ele){
	var page_id = ele.id;
	var loc = location.pathname;
	var url = loc.substring(0, loc.lastIndexOf('/')) + '/';
	console.log(url);
	$("#form").attr("action",url + page_id );
}

// ブラウザの戻るボタンを無効化する
$(function(){
window.location.hash="no-back";
window.location.hash="no-back-button";
window.onhashchange=function(){
    window.location.hash="no-back";
}
});