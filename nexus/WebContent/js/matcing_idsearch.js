$(function() {
	$('[name=id_name_input]').on('click',function(){
        clickFire();
    });
  });
var parantExistanceFlag = true;

function clickFire(){

    //ウィンドウオブジェクトが存在していない時警告してフラグをfalseに
    if ( !window.opener || !Object.keys(window.opener).length ) {
        window.alert('親画面が存在しません')
        parantExistanceFlag = false
    }

    //親画面に値を挿入
    if(parantExistanceFlag){
        window.opener.$('#jobseeker_id').val($(this).next().val());
        // window.opener.$('#jobseeker_name').val($('#jovseeker_id').val());
    }
    window.close();
}
