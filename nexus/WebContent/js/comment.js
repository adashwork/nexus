/**
 *
 * ボタン押したらコメントのページのウィンドウが画面中央に開く
 */
function WindowOpen(){

	   var subw = 1500;   // サブウインドウの横幅
	   var subh = 700;   // サブウインドウの高さ
	   var subp = "/nexus/web/comment-disp";   // 表示するページ(URL)
	   var subn = "commentregist";   // サブウインドウの名称
	   // 表示座標の計算
	   var subx = ( screen.availWidth  - subw ) / 2;   // X座標
	   var suby = ( screen.availHeight - subh ) / 2;   // Y座標
	   // サブウインドウのオプション文字列を作る
	   var SubWinOpt = "width=" + subw + ",height=" + subh + ",top=" + suby + ",left=" + subx;
	   // サブウインドウを表示
	   window.open(subp,subn ,SubWinOpt);
}

/**
 *
 * ボタン押したらページを閉じる
 */
function CloseWindow(){
	window.open('about:blank','_self').close();
}
