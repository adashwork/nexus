/**
 *
 * ボタン押したらコメントのページのウィンドウが画面中央に開く
 */
function WindowOpen(commentid){

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
	   if(!isNaN(commentid) && commentid != ""){
		   subp += "?commentid=" + commentid;
	   }
	   window.open(subp,subn ,SubWinOpt);
	   return false;
}

/**
 *
 * ボタン押したらページを閉じる
 */
function CloseWindow(){
	window.open('about:blank','_self').close();
}



$(function() {
$('#genre').change(function() {
    var r = $('option:selected').val();
    	if(r ==1){
    		$('#jobseekerid').attr('required','1');
    		$('#companyno').removeAttr('required','1');
    		$('#kyujinno').removeAttr('required','1');
    		$('#matchid').removeAttr('required','1');
    	}else if(r == 2){
    			$('#companyno').attr('required','1');
    			$('#matchid').removeAttr('required','1');
    			$('#jobseekerid').removeAttr('required','1');
    			$('#kyujinno').removeAttr('required','1');
    		}else if(r == 3){
    			$('#kyujinno').attr('required','1');
    			$('#jobseekerid').removeAttr('required','1');
    			$('#companyno').removeAttr('required','1');
    			$('#matchid').removeAttr('required','1');
    			}else if(r == 4){
    				$('#matchid').attr('required','1');
    				$('#jobseekerid').removeAttr('required','1');
    				$('#companyno').removeAttr('required','1');
    				$('#kyujinno').removeAttr('required','1');
    			}else if(r == 9){
    				$('#matchid').removeAttr('required','1');
    				$('#jobseekerid').removeAttr('required','1');
    				$('#companyno').removeAttr('required','1');
    				$('#kyujinno').removeAttr('required','1');
    			}else if(r == 0){
    				$('#matchid').removeAttr('required','1');
    				$('#jobseekerid').removeAttr('required','1');
    				$('#companyno').removeAttr('required','1');
    				$('#kyujinno').removeAttr('required','1');

    	}
})
});


