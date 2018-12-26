/**
 *企業の分類プルダウンのJava script
 * @author akiba
 */

// Middlecdを取得する
function getMiddleCategory() {
	// jobcategory APIから分類を取得する
	$.ajax({
		url : "http://localhost:8080/trynexusapi/webapi/jobcategory/getjobcategory", // リクエストURL
		type : "GET", // HTTPメソッド
		dataType : "json", // データ形式
		data : {
			"largecode" : $('#largecd').val()
		}
	}).done(function(data) {
		callbackMiddle(data);
	} // 成功時の処理
	).fail(function(xhr, status, error) {
		alert('APIアクセスに失敗しました。');
	} // 失敗時の処理
	);
}

// コールバック時に呼び出されるメソッド
// jobcategoryをセットする
function callbackMiddle(data) {
	$('select#middlecd option').remove();
	$('select#smallcd option').remove();
	$('#middlecd').append("<option value=\"\"></option>");
	for (var category in data){
		$('#middlecd').append("<option value="+ data[category].middlecd +">" + data[category].name + "</option>");
	}
}

// ロード時の処理
$(function() {
	// イベントハンドラの定義
	$("#largecd").on("change", function() {
		getMiddleCategory();
	});
});


// Smallcdを取得する
function getSmallCategory() {
	// jobcategory APIから分類を取得する
	$.ajax({
		url : "http://localhost:8080/trynexusapi/webapi/jobcategory/getjobcatagory2", // リクエストURL
		type : "GET", // HTTPメソッド
		dataType : "json", // データ形式
		data : {
			"middlecode" : $('#middlecd').val()
		}
	}).done(function(data) {
		callbackSmall(data);
	} // 成功時の処理
	).fail(function(xhr, status, error) {
		alert('APIアクセスに失敗しました。');
	} // 失敗時の処理
	);
}

//コールバック時に呼び出されるメソッド
//jobcategoryをセットする
function callbackSmall(data) {
	$('select#smallcd option').remove();
	$('#smallcd').append("<option value=\"\"></option>");
	for (var category in data){
		$('#smallcd').append("<option value="+ data[category].smallcd +">" + data[category].name + "</option>");
	}

}

//ロード時の処理
$(function() {
	// イベントハンドラの定義
	$("#middlecd").on("change", function() {
		getSmallCategory();
	});
});
