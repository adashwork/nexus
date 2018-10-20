package jp.or.adash.nexus.services;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.or.adash.nexus.dao.KyujinDao;
import jp.or.adash.nexus.dao.SaibanDao;
import jp.or.adash.nexus.entity.Kyujin;
import jp.or.adash.nexus.utils.common.DataCommons;
import jp.or.adash.nexus.utils.common.MessageCommons;
import jp.or.adash.nexus.utils.dao.Transaction;

/**
 * 求人情報に関する処理を定義するクラス
 * @author m.kashiwagi
 * @author pgjavaAT 時間チェック部分をリファクタリング 18/09/29
 */
public class KyujinService {

	/**
	 * トランザクションオブジェクト
	 */
	private Transaction transaction;

	/**
	 * 処理結果メッセージを格納するリスト
	 */
	private List<String> messages;

	/**
	 * コンストラクタ
	 */
	public KyujinService() {
		transaction = new Transaction();
		messages = new ArrayList<String>();
	}

	/**
	 * 処理結果メッセージを取得する
	 * @return 処理結果メッセージ
	 */
	public List<String> getMessages() {
		return messages;
	}

	/**
	 * 求人番号を元に、商品情報を取得する
	 * @param code 商品コード
	 * @return 商品情報
	 */
	public Kyujin getKyujin(String no) {
		Kyujin kyujin = null;

		try {
			// データベース接続を開始する
			transaction.open();

			// 商品単価を取得する
			KyujinDao dao = new KyujinDao(transaction);
			kyujin = dao.selectKyujin(no);

		} catch (IOException e) {
			// エラーメッセージをセットする
		} finally {
			// データベース接続を終了する
			transaction.close();
		}

		return kyujin;
	}

	/**
	 * 求人票リストを取得する
	 * @return 求人票リスト
	 */
	public List<Kyujin> getKyujinList() {
		List<Kyujin> kyujinList = new ArrayList<Kyujin>();

		try {
			// データベース接続を開始する
			transaction.open();

			// 求人リストを取得する
			KyujinDao dao = new KyujinDao(transaction);
			kyujinList = dao.selectKyujinList();

		} catch (IOException e) {
			// エラーメッセージをセットする
			messages.add(MessageCommons.ERR_DB_CONNECT);
		} finally {
			// データベース接続を終了する
			transaction.close();
		}

		return kyujinList;
	}

	/*
	 * 求人票データの内容をチェックする
	 * @param kyujin 求人票データ
	 * @return 処理結果（true:成功、false:失敗）
	 */
	public boolean check(Kyujin kyujin) {
		boolean result = true; // チェック結果
		String msg = null;
		String stdate = null;

		// 事業所番号の長さが適切か

		int length = DataCommons.getBytes(kyujin.getCompanyno());
		if (length <= 0 || length > 13) {
			messages.add("事業所番号が不当です。");
			result = false;
		}
		msg = DataCommons.chkCompanyno(kyujin.getCompanyno());
		if (msg != null) {
			messages.add(msg);
			result = false;
		}

		// 受付年月日（西暦）　日付が妥当かチェック
		if (kyujin.getReceptiondt() == null) {
			messages.add("受付年月日を入力してください。");
			result = false;
		} else {
			stdate = new SimpleDateFormat("yyyy/MM/dd").format(kyujin.getReceptiondt());
			msg = DataCommons.chkDate(stdate);
			if (msg != null) {
				messages.add(msg);
				result = false;
			}
		}

		// 求人有効年月日が妥当かチェック
		if (kyujin.getPerioddt() == null) {
			messages.add("求人有効年月日を入力してください。");
			result = false;
		} else {
			stdate = new SimpleDateFormat("yyyy/MM/dd").format(kyujin.getPerioddt());
			msg = DataCommons.chkDate(stdate);
			if (msg != null) {
				messages.add(msg);
				result = false;
			}
		}
		// 事業署名（半角ｶﾅ）の長さが適切か
		length = DataCommons.getBytes(kyujin.getCompanykana());
		if (length <= 0 || length > 54) {
			messages.add("事業所名（半角ｶﾅ）が不当です。");
			result = false;
		}

		// 事業所名（半角ｶﾅ）が半角ｶﾅか
		msg = DataCommons.chkKana(kyujin.getCompanykana());
		if (msg != null) {
			messages.add("事業所名（半角ｶﾅ）が"
					+ msg);
			result = false;
		}
		// 事業署名の長さが適切か
		length = DataCommons.getBytes(kyujin.getCompanyname());
		if (length <= 0 || length > 60) {
			messages.add("事業所名が不当です。");
			result = false;
		}

		// 事業所郵便番号の長さが適切か
		length = DataCommons.getBytes(kyujin.getCompanypostal());
		if (length > 8) {
			messages.add("郵便番号が長すぎます。");
			result = false;
		}
		if (!kyujin.getCompanypostal().equals(""))
			msg = DataCommons.chkZipcode(kyujin.getCompanypostal());
		if (msg != null) {
			messages.add(msg);
			result = false;
		}

		// 事業所所在地の長さが適切か
		length = DataCommons.getBytes(kyujin.getCompanyplace());
		if (length > 75) {
			messages.add("事業所所在地が長すぎます。");
			result = false;
		}

		// 事業所URLの長さが適切か
		length = DataCommons.getBytes(kyujin.getCompanyurl());
		if (length > 100) {
			messages.add("事業所URLが長すぎます。");
			result = false;
		}

		// 就業場所郵便番号の長さが適切か
		length = DataCommons.getBytes(kyujin.getPostal());
		if (length > 8) {
			messages.add("就業場所郵便番号が長すぎます。");
			result = false;
		}
		if (!kyujin.getPostal().equals("")) {
			msg = DataCommons.chkZipcode(kyujin.getPostal());
			if (msg != null) {
				messages.add(msg);
				result = false;
			}
		}

		// 就業場所の長さが適切か
		length = DataCommons.getBytes(kyujin.getAddress());
		if (length > 90) {
			messages.add("就業場所が長すぎます。");
			result = false;
		}

		// 最寄り駅の長さが適切か
		length = DataCommons.getBytes(kyujin.getNearstation());
		if (length > 30) {
			messages.add("最寄り駅が長すぎます。");
			result = false;
		}

		// 就業場所の長さが適切か
		length = DataCommons.getBytes(kyujin.getJob());
		if (length <= 0 || length > 28) {
			messages.add("職種が不適切です。");
			result = false;
		}

		// 仕事の内容の長さが適切か
		length = DataCommons.getBytes(kyujin.getDetail());
		if (length > 297) {
			messages.add("仕事の内容が長すぎます。");
			result = false;
		}

		//雇用期間の定め　１　OR　２
		if (kyujin.getKoyoukikan().equals("1")) {

			if (kyujin.getKoyoukikankaishi() == null || kyujin.getKoyoukikanowari() == null) {
				messages.add("雇用期間の日付を入れてください。");
				result = false;

			} else {
				// 雇用期間の　日付が妥当かチェック
				String start = new SimpleDateFormat("yyyy/MM/dd").format(kyujin.getKoyoukikankaishi());
				msg = DataCommons.chkDate(start);
				if (msg != null) {
					messages.add(msg);
					result = false;
				}
				String end = new SimpleDateFormat("yyyy/MM/dd").format(kyujin.getKoyoukikanowari());
				msg = DataCommons.chkDate(end);
				if (msg != null) {
					messages.add(msg);
					result = false;
				}
				msg = DataCommons.chkdRange(start, end);
				if (msg != null) {
					messages.add(msg);
					result = false;
				}
			}
		} else if (kyujin.getKoyoukikan().equals("2")) {

			if (kyujin.getKoyoukikankaishi() != null || kyujin.getKoyoukikanowari() != null) {
				messages.add("雇用期間の日付入れないでください。");
				result = false;

			}
		}

		// 学歴の内容の長さが適切か
		length = DataCommons.getBytes(kyujin.getEducation());
		if (length > 64) {
			messages.add("学歴が長すぎます。");
			result = false;
		}

		// 必要な経験等の長さが適切か
		length = DataCommons.getBytes(kyujin.getExperience());
		if (length > 84) {
			messages.add("必要な経験等が長すぎます。");
			result = false;
		}

		// 必要な免許・資格等の内容の長さが適切か
		length = DataCommons.getBytes(kyujin.getLicense());
		if (length > 84) {
			messages.add("必要な免許・資格等が長すぎます。");
			result = false;
		}

		// 年齢の下限・上限の値が適切か
		msg = DataCommons.chkInt(String.valueOf(kyujin.getAgemin().toString()));
		if (msg != null) {
			messages.add(msg);
			result = false;
		}
		msg = DataCommons.chkiDigits(kyujin.getAgemin(), 2);
		if (msg != null) {
			messages.add(msg);
			result = false;
		}
		msg = DataCommons.chkInt(String.valueOf(kyujin.getAgemax().toString()));
		if (msg != null) {
			messages.add(msg);
			result = false;
		}
		msg = DataCommons.chkiDigits(kyujin.getAgemax(), 2);
		if (msg != null) {
			messages.add(msg);
			result = false;
		}
		if (kyujin.getAgemin() > kyujin.getAgemax()) {
			messages.add("年齢の範囲が間違ってます。");
			result = false;
		}

		// 基本給の下限・上限の値が適切か
		msg = DataCommons.chkInt(String.valueOf(kyujin.getSalarymin().toString()));
		if (msg != null) {
			messages.add(msg);
			result = false;
		}
		msg = DataCommons.chkiDigits(kyujin.getSalarymin(), 7);
		if (msg != null) {
			messages.add(msg);
			result = false;
		}
		msg = DataCommons.chkInt(String.valueOf(kyujin.getSalarymax().toString()));
		if (msg != null) {
			messages.add(msg);
			result = false;
		}
		msg = DataCommons.chkiDigits(kyujin.getSalarymax(), 7);
		if (msg != null) {
			messages.add(msg);
			result = false;
		}
		if (kyujin.getSalarymin() > kyujin.getSalarymax()) {
			messages.add("基本給の範囲が間違ってます。");
			result = false;
		}

		// 就業時間の下限・上限の値が適切か
		msg = DataCommons.chkiDigits(kyujin.getBegintime(), 4);
		if (msg != null) {
			messages.add(msg);
			result = false;
		}
		msg = DataCommons.chkTime(kyujin.getBegintime());
		if (msg != null) {
			messages.add(msg);
			result = false;
		}
		// TODO chkTime()メソッドを追加したためコメントアウト、後ほど削除予定
		//			String sttime = String.format("%04d", kyujin.getBegintime());
		//			Pattern p = Pattern.compile("^([0-1][0-9]|[2][0-3])[0-5][0-9]$");
		//			Matcher m = p.matcher(sttime);
		//			if ( !m.find() ) {
		//				messages.add("就業時間・始業を時間で入れてください。");
		//				result = false;
		//			}
		msg = DataCommons.chkiDigits(kyujin.getEndtime(), 4);
		if (msg != null) {
			messages.add(msg);
			result = false;
		}
		msg = DataCommons.chkTime(kyujin.getEndtime());
		if (msg != null) {
			messages.add(msg);
			result = false;
		}
		// TODO chkTime()メソッドを追加したためコメントアウト、後ほど削除予定
		//			sttime = String.format("%04d", kyujin.getEndtime());
		//			p = Pattern.compile("^([0-1][0-9]|[2][0-3])[0-5][0-9]$");
		//			m = p.matcher(sttime);
		//			if ( !m.find() ) {
		//				messages.add("就業時間・就業を時間で入れてください。");
		//				result = false;
		//			}
		if (kyujin.getBegintime() > kyujin.getEndtime()) {
			messages.add("就業時間の範囲が間違っています。");
			result = false;
		}
		//創業設立年チェック
		if (kyujin.getEstablishdt() != 0) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			int year = Integer.parseInt(sdf.format(date));
			msg = DataCommons.chkiDigits(kyujin.getEstablishdt(), 4);
			if (msg != null) {
				messages.add(msg);
				result = false;
			}
			msg = DataCommons.chkInt(String.valueOf(kyujin.getEstablishdt().toString()));
			if (msg != null) {
				messages.add(msg);
				result = false;
			}
			//578 は日本最古の創業年
			if (kyujin.getEstablishdt() > year || kyujin.getEstablishdt() < 578) {
				messages.add("創業設立年が不当な値です。");
				result = false;
			}
		}

		//資本金チェック
		msg = DataCommons.chklDigits(kyujin.getCapital(), 16);
		if (msg != null) {
			messages.add(msg);
			result = false;
		}
		msg = DataCommons.chkLong(kyujin.getCapital());
		if (msg != null) {
			messages.add(msg);
			result = false;
		}
		//会社の特徴　長さチェック
		msg = DataCommons.chksDigits(kyujin.getCompanyfeature(), 90);
		if (msg != null) {
			messages.add(msg);
			result = false;
		}
		//選考担当者　役職名の長さチェック
		msg = DataCommons.chksDigits(kyujin.getTantouyakushoku(), 28);
		if (msg != null) {
			messages.add(msg);
			result = false;
		}
		//採用担当者名　長さ、ひらがなチェック
		msg = DataCommons.chksDigits(kyujin.getTantoukana(), 28);
		if (msg != null) {
			messages.add(msg);
			result = false;
		}
		if (!kyujin.getTantoukana().equals("")) {
			msg = DataCommons.chkHiragana(kyujin.getTantoukana());
			if (msg != null) {
				messages.add(msg);
				result = false;
			}
		}
		//選考担当者名　長さチェック
		msg = DataCommons.chksDigits(kyujin.getTantou(), 14);
		if (msg != null) {
			messages.add(msg);
			result = false;
		}
		//応募書類　長さチェック
		msg = DataCommons.chksDigits(kyujin.getApplicationform(), 500);
		if (msg != null) {
			messages.add(msg);
			result = false;
		}
		//募集背景　長さチェック
		msg = DataCommons.chksDigits(kyujin.getBackground(), 1000);
		if (msg != null) {
			messages.add(msg);
			result = false;
		}
		// （求職者非公開）年齢の下限・上限の値が適切か
		msg = DataCommons.chkInt(String.valueOf(kyujin.getHiddenagemin().toString()));
		if (msg != null) {
			messages.add(msg);
			result = false;
		}
		msg = DataCommons.chkiDigits(kyujin.getHiddenagemin(), 2);
		if (msg != null) {
			messages.add(msg);
			result = false;
		}
		msg = DataCommons.chkInt(String.valueOf(kyujin.getHiddenagemax().toString()));
		if (msg != null) {
			messages.add(msg);
			result = false;
		}
		msg = DataCommons.chkiDigits(kyujin.getHiddenagemax(), 2);
		if (msg != null) {
			messages.add(msg);
			result = false;
		}
		if (kyujin.getHiddenagemin() > kyujin.getHiddenagemax()) {
			messages.add("年齢の範囲が間違ってます。");
			result = false;
		}
		//その他非公開情報　長さチェック
		msg = DataCommons.chksDigits(kyujin.getHiddenetc(), 1000);
		if (msg != null) {
			messages.add(msg);
			result = false;
		}
		return result;
	}

	/**
	 * 登録完了メッセージ
	 */
	private static final String MSG_ITEM_REGIST_COMPLETE = "求人データの更新が完了しました。";

	/**
	 * 登録失敗メッセージ
	 */
	private static final String MSG_ITEM_REGIST_FAILURE = "求人データの登録に失敗しました。";

	/**
	 * 求人票データを更新する
	 * @param kyujin 求人票データ
	 * @return 処理結果（true:成功、false:失敗）
	 */
	public boolean updateKyujin(Kyujin kyujin) {
		boolean result = false; // 処理結果

		try {
			// データベース接続を開始する
			transaction.open();

			// トランザクションを開始する
			transaction.beginTrans();

			// 商品単価を取得する
			KyujinDao dao = new KyujinDao(transaction);
			int count = dao.update(kyujin);

			if (count > 0) {
				// 完了メッセージをセットする
				messages.add(MSG_ITEM_REGIST_COMPLETE);
				result = true;
			} else {
				// エラーメッセージをセットする
				messages.add(MSG_ITEM_REGIST_FAILURE);
			}

			// トランザクションをコミットする
			transaction.commit();

		} catch (IOException e) {
			// トランザクションをロールバックする
			transaction.rollback();

			// エラーメッセージをセットする
			messages.add(MessageCommons.ERR_DB_CONNECT);
		} finally {
			// データベース接続をを終了する
			transaction.close();
		}

		return result;
	}

	/**
	 * 求人票データをデータベースに登録する
	 * @param kyujin 求人票データ
	 * @return 処理結果（true:成功、false:失敗）
	 */
	public boolean insertKyujin(Kyujin kyujin) {
		boolean result = false; // 処理結果

		try {
			// データベース接続を開始する
			transaction.open();

			// トランザクションを開始する
			transaction.beginTrans();

			//采番マスタよりデータ取得
			SaibanDao saidao = new SaibanDao(transaction);
			int saiban = saidao.getsaiban();

			//とってきた番号を加工し、Kyujin.noにデータ格納
			String str = String.format("A" + "%013d", saiban);
			kyujin.setNo(str);

			//
			KyujinDao dao = new KyujinDao(transaction);
			int count = dao.insert(kyujin);

			if (count > 0) {
				// 完了メッセージをセットする
				messages.add(MSG_ITEM_REGIST_COMPLETE);
				result = true;
			} else {
				// エラーメッセージをセットする
				messages.add(MSG_ITEM_REGIST_FAILURE);
				result = false;
			}

			// トランザクションをコミットする
			transaction.commit();

		} catch (IOException e) {
			// トランザクションをロールバックする
			transaction.rollback();

			// エラーメッセージをセットする
			messages.add(MessageCommons.ERR_DB_CONNECT);
		} finally {
			// データベース接続を終了する
			transaction.close();
		}

		return result;
	}

	/**
	 * 求人票データに削除フラグを立てる
	 * @return 処理結果（true:成功、false:失敗）
	 */
	public boolean deleteKyujin(String no, String delflag) {
		boolean result = false; // 処理結果

		try {
			// データベース接続を開始する
			transaction.open();

			// トランザクションを開始する
			transaction.beginTrans();

			// 求人マスタに削除フラグを立てる
			KyujinDao dao = new KyujinDao(transaction);
			int count = dao.delete(no, delflag);

			if (count > 0) {
				// 完了メッセージをセットする
				messages.add(MSG_ITEM_REGIST_COMPLETE);
				result = true;
			} else {
				// エラーメッセージをセットする
				messages.add(MSG_ITEM_REGIST_FAILURE);
				result = false;
			}

			// トランザクションをコミットする
			transaction.commit();

		} catch (IOException e) {
			// トランザクションをロールバックする
			transaction.rollback();

			// エラーメッセージをセットする
			messages.add(MessageCommons.ERR_DB_CONNECT);
		} finally {
			// データベース接続をを終了する
			transaction.close();
		}

		return result;
	}

}
