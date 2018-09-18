/**
 *
 */
//ウィンドウをクローズする
function CloseWindow(){
	window.open('about:blank','_self').close();
}

//IDを取得してそれを元にURL遷移
function MovePages(ele){
	var page_id = ele.id;
	var loc = location.pathname;
	var url = loc.substring(0, loc.lastIndexOf('/')) + '/';
	console.log(url);
	$("#form").attr("action",url + page_id );
}