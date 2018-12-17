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
		// 受け取ったstr内に含まれる全角スペースを半角に置換
		String str2 = str.replace("　"," ");
		// 半角スペースごとに単語を区切り、配列に格納
		String[] str3 = str2.split(" ",0);

		return str3;

	}
}
