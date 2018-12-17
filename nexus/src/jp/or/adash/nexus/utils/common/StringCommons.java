package jp.or.adash.nexus.utils.common;

/**
 *
 * @author AWU305
 *
 */


public class StringCommons {

	/**
	 * 検索のテキスト欄に入力された文字列に対するメソッド
	 * （説明）
	 * [単語 単語２　単語３]のように入力された文字列に対し
	 * 全角スペースを半角スペースに置換・統一
	 * 文字列を半角スペースごとに区切り、単語を配列に格納する
	 * @author mosco
	 *
	 */
	public static String[]  splitWords(String str){

		String[] str2 = {""};

		if(str == null) {
			return str2;
		}


		// 受け取ったstr内に含まれる全角スペースを半角に置換
		str = str.replace("　"," ");
		 //
		str = str.trim();
		// 間の半角スペースがいくつでもひとつにする
		// スペースしかないときに""で返す
		// 正規表現を使用しましたが書き手が理解していないので要注意　18/12/17 mosco
		str = str.replaceAll("  +|   +|\t|\r|\n"," ");

		if(str.equals(" ")) {
			return str2;
		}

		// 半角スペースごとに単語を区切り、配列に格納
		str2 = str.split(" ",0);


		return str2;

	}
}
