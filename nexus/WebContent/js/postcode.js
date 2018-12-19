/**
 *郵便番号から住所を入力するJava script
 * @author akiba
 */

// 住所を検索する
function searchAddress(){
    // 入力チェック
    if ($('[name=postal]').val().length != 7) {
        // 7桁でない場合、処理終了
        return false;
    }

    // YahooDevelopers郵便番号検索APIから住所取得
    $.ajax({
        url: 'https://map.yahooapis.jp/search/zip/V1/zipCodeSearch',    // URL
        type: 'GET',                                                    // HTTPメソッド
        dataType: 'jsonp',                                              // データの種類
        data: {                                                                 // パラメータ
            appid: 'dj00aiZpPUhnOVlkSmJ4NmQwVSZzPWNvbnN1bWVyc2VjcmV0Jng9MGU-',  // アプリケーションID
            query: $('[name=postal]').val(),                                                         // 検索条件
            output: 'json',                                                     // データの種類
            callback: 'callback'                                                // コールバック関数名
        },
        jsonp: false,                                                           // JSONPのコールバック関数名を自動指定するか
        jsonpCallback: 'callback'                                               // JSONPのコールバック関数名
        }).done(
            function(data, status, xhr){    // API呼び出しに成功した場合
                if (data != null && data.Feature) {
                    // データがあれば住所をセットする
                    $('#address').val(data.Feature[0].Property.Address);
                }
        }).fail(
            function(xhr, status, error){   // API呼び出しに失敗した場合
                alert("APIにアクセス失敗");
    });
}

// ロード時の処理
$(function(){
    // イベントハンドらの設定
	// atainihenkougaattatoki
    $('[name=postal]').on('blur',function(event){
        searchAddress();
    });
});