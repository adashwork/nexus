
// 入力→検索で入力した値を保持する
// その上で、リセットボタンクリック時に入力した値をリセットする
// ロード時の処理
$(function(){
    // イベントハンドらの設定
	// atainihenkougaattatoki
    $(".reset_button").on('click',function(event){
    	$("input[name=companyname]").val("");
    	$("input[name=companyplace]").val("");
    	$("select[name=jobcategory]").val("");
    	$("select[name=staffid]").val("");
    });
});

