package jp.or.adash.nexus.services;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
 * @author kemiyan 18/12/14～
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
	 * 求人番号を元に、求人情報を取得する
	 * @param no 求人コード
	 * @return 求人情報
	 */
	public Kyujin getKyujin(String no) {
		Kyujin kyujin = null;

		try {
			// データベース接続を開始する
			transaction.open();

			// 求人情報を取得する
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

		// 受付年月日（西暦）の日付が妥当かチェック
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

		// getBytesでlength <= 0にしているところは入力必須
		// 必須でなくすには別の書式か length < 0

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

		// この項目は企業登録に移行
		/*		// 事業所名（全角カナ）の長さが適切か
						length = DataCommons.getBytes(kyujin.getCompanykana());
						if (length < 0 || length > 54) {
							messages.add("事業所名（全角カナ）は54字以内にしてください。");
							result = false;
						}

						// 事業所名（全角カナ）が全角か
						msg = DataCommons.chklgKana(kyujin.getCompanykana());
						if (msg != null) {
							messages.add("事業所名に"
									+ msg);
							result = false;
						}

						// 創業設立年チェック
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
							// 578 は日本最古の創業年
							if (kyujin.getEstablishdt() > year || kyujin.getEstablishdt() < 578) {
								messages.add("創業設立年が不当な値です。");
								result = false;
							}
						}
				*/


		// TODO 郵便番号から就業場所住所・都道府県の引き込みと、正誤判定を行うようにする

		// 就業場所郵便番号の長さが適切か
		length = DataCommons.getBytes(kyujin.getPostal());
		if (length < 0 || length > 8) {
			messages.add("就業場所郵便番号が不適切です。");
			result = false;
		}
		if (!kyujin.getPostal().equals("")) {
			msg = DataCommons.chkZipcode(kyujin.getPostal());
			if (msg != null) {
				messages.add(msg);
				result = false;
			}
		}

		// 都道府県選択必須
		if ("".equals(kyujin.getAddresscd())) {
			messages.add("就業場所の都道府県を入力してください");
			result = false;
		}

		// 就業場所の長さが適切か
		length = DataCommons.getBytes(kyujin.getAddress());
		if (length <= 0 || length > 270) {
			messages.add("就業場所を90字以内で入力してください。");
			result = false;
		}

		// 沿線の長さが適切か
		length = DataCommons.getBytes(kyujin.getNearline());
		if (length < 0 || length > 90) {
			messages.add("沿線を30字以内で入力してください。");
			result = false;
		}

		// 最寄り駅の長さが適切か
		length = DataCommons.getBytes(kyujin.getNearstationKyujin());
		if (length <= 0 || length > 90) {
			messages.add("最寄り駅を30字以内で入力してください。");
			result = false;
		}

		// 職種名の長さが適切か
		length = DataCommons.getBytes(kyujin.getJob());
		if (length <= 0 || length > 84) {
			messages.add("職種名を28字以内で入力してください。");
			result = false;
		}

		// 仕事の内容の長さが適切か
		length = DataCommons.getBytes(kyujin.getDetail());
		if (length <= 0 || length > 1200) {
			messages.add("仕事の内容を400字以内で入力してください。");
			result = false;
		}

		// 雇用期間の定め
		msg = DataCommons.chksDigits(kyujin.getKoyoukikan(), 30);
		if (msg != null) {
			messages.add("雇用期間の定めは30字以内にしてください。");
			result = false;
		}

		// 雇用期間が下記if条件以外なら雇用期限入力
		if (!kyujin.getKoyoukikan().equals("") && !kyujin.getKoyoukikan().equals("なし")
				&& !kyujin.getKoyoukikan().equals("無し") && !kyujin.getKoyoukikan().equals("無")) {

			if (kyujin.getKoyoukikankaishi() == null || kyujin.getKoyoukikanowari() == null) {
				messages.add("雇用期間の期限を入れてください。");
				result = false;

			} else {
				// 雇用期間の日付が妥当かチェック
				String start = new SimpleDateFormat("yyyy/MM/dd").format(kyujin.getKoyoukikankaishi());
				msg = DataCommons.chkDate(start);
				if (msg != null) {
					messages.add("雇用期間に" + msg);
					result = false;
				}
				String end = new SimpleDateFormat("yyyy/MM/dd").format(kyujin.getKoyoukikanowari());
				msg = DataCommons.chkDate(end);
				if (msg != null) {
					messages.add("雇用期間に" + msg);
					result = false;
				}
				msg = DataCommons.chkdRange(start, end);
				if (msg != null) {
					messages.add("雇用期間の" + msg);
					result = false;
				}
			}
		}
		//		else if (kyujin.getKoyoukikan().equals("2")) {
		//
		//			if (kyujin.getKoyoukikankaishi() != null || kyujin.getKoyoukikanowari() != null) {
		//				messages.add("雇用期間の日付入れないでください。");
		//				result = false;
		//
		//			}
		//		}

		// 学歴の内容の長さが適切か
		msg = DataCommons.chksDigits(kyujin.getEducation(), 64);
		if (msg != null) {
			messages.add("学歴は64字以内にしてください。");
			result = false;
		}

		// 必要な経験等の長さが適切か
		msg = DataCommons.chksDigits(kyujin.getExperience(), 84);
		if (msg != null) {
			messages.add("必要な経験等は84字以内にしてください。");
			result = false;
		}

		// 必要な免許・資格等の内容の長さが適切か
		msg = DataCommons.chksDigits(kyujin.getLicense(), 84);
		if (msg != null) {
			messages.add("必要な免許・資格等は84字以内にしてください。");
			result = false;
		}

		// TODO 以下、年齢など数字入力のところがマイナス入力可能なのでそれをチェック

		// 年齢の下限・上限の値が適切か
		msg = DataCommons.chkInt(String.valueOf(kyujin.getAgemin().toString()));
		if (msg != null) {
			messages.add("年齢に" + msg);
			result = false;
		}
		msg = DataCommons.chkiDigits(kyujin.getAgemin(), 2);
		if (msg != null) {
			messages.add("年齢が" + msg);
			result = false;
		}
		msg = DataCommons.chkInt(String.valueOf(kyujin.getAgemax().toString()));
		if (msg != null) {
			messages.add("年齢に" + msg);
			result = false;
		}
		msg = DataCommons.chkiDigits(kyujin.getAgemax(), 2);
		if (msg != null) {
			messages.add("年齢が" + msg);
			result = false;
		}
		if (kyujin.getAgemin() > kyujin.getAgemax()) {
			messages.add("年齢の範囲が間違っています。");
			result = false;
		}

		// 基本給の下限・上限の値が適切か
		msg = DataCommons.chkInt(String.valueOf(kyujin.getSalarymin().toString()));
		if (msg != null) {
			messages.add("基本給に" + msg);
			result = false;
		}
		msg = DataCommons.chkiDigits(kyujin.getSalarymin(), 7);
		if (msg != null) {
			messages.add("基本給が" + msg);
			result = false;
		}
		msg = DataCommons.chkInt(String.valueOf(kyujin.getSalarymax().toString()));
		if (msg != null) {
			messages.add("基本給に" + msg);
			result = false;
		}
		msg = DataCommons.chkiDigits(kyujin.getSalarymax(), 7);
		if (msg != null) {
			messages.add("基本給が" + msg);
			result = false;
		}
		if (kyujin.getSalarymin() > kyujin.getSalarymax()) {
			messages.add("基本給の範囲が間違っています。");
			result = false;
		}

		// 賞与の内容の長さが適切か
		msg = DataCommons.chksDigits(kyujin.getBonus(), 50);
		if (msg != null) {
			messages.add("賞与は50字以内にしてください。");
			result = false;
		}

		// 通勤手当の長さが適切か
		msg = DataCommons.chksDigits(kyujin.getKoutuhi(), 30);
		if (msg != null) {
			messages.add("通勤手当は30字以内にしてください。");
			result = false;
		}

		// 諸手当の内容の長さが適切か
		msg = DataCommons.chksDigits(kyujin.getTeate(), 30);
		if (msg != null) {
			messages.add("諸手当は30字以内にしてください。");
			result = false;
		}

		// 就業時間の下限・上限の値が適切か
		msg = DataCommons.chkiDigits(kyujin.getBegintime(), 4);
		if (msg != null) {
			messages.add("就業時間が" + msg);
			result = false;
		}
		msg = DataCommons.chkTime(kyujin.getBegintime());
		if (msg != null) {
			messages.add(msg);
			result = false;
		}
		// chkTime()メソッドを追加したためコメントアウト、後ほど削除予定－1807期生記述
		//			String sttime = String.format("%04d", kyujin.getBegintime());
		//			Pattern p = Pattern.compile("^([0-1][0-9]|[2][0-3])[0-5][0-9]$");
		//			Matcher m = p.matcher(sttime);
		//			if ( !m.find() ) {
		//				messages.add("就業時間・始業を時間で入れてください。");
		//				result = false;
		//			}
		msg = DataCommons.chkiDigits(kyujin.getEndtime(), 4);
		if (msg != null) {
			messages.add("就業時間が" + msg);
			result = false;
		}
		msg = DataCommons.chkTime(kyujin.getEndtime());
		if (msg != null) {
			messages.add(msg);
			result = false;
		}

		// 夜勤で日付またいだ時間を入力することも有り得るのでコメントアウト－1810期生コメ
		//		if (kyujin.getBegintime() > kyujin.getEndtime()) {
		//			messages.add("就業時間の範囲が間違っています。");
		//			result = false;
		//		}

		//会社の特徴　長さチェック－項目不要
		//		msg = DataCommons.chksDigits(kyujin.getCompanyfeature(), 90);
		//		if (msg != null) {
		//			messages.add(msg);
		//			result = false;
		//		}

		// シフト制の長さチェック
		msg = DataCommons.chksDigits(kyujin.getShift(), 60);
		if (msg != null) {
			messages.add("シフト制は60字以内にしてください");
			result = false;
		}

		// フレックスタイム制の長さチェック
		msg = DataCommons.chksDigits(kyujin.getFlex(), 60);
		if (msg != null) {
			messages.add("フレックスタイム制は60字以内にしてください");
			result = false;
		}

		// 時間外労働の値が適切か
		msg = DataCommons.chkiDigits(kyujin.getJikangai(), 2);
		if (msg != null) {
			messages.add("時間外労働は2桁以内にしてください");
			result = false;
		}

		// 試用期間の値が適切か
		msg = DataCommons.chkiDigits(kyujin.getSiyoukikan(), 1);
		if (msg != null) {
			messages.add("試用期間は1桁にしてください");
			result = false;
		}

		// 週所定労働日数の値が適切か
		msg = DataCommons.chkiDigits(kyujin.getWorkdays(), 1);
		if (msg != null) {
			messages.add("週所定労働日数は1桁にしてください");
			result = false;
		}

		// 年間休日日数の値が適切か
		msg = DataCommons.chksDigits(kyujin.getNenkanholiday(), 30);
		if (msg != null) {
			messages.add("年間休日日数は30字以内にしてください");
			result = false;
		}

		// 応募書類の長さチェック
		msg = DataCommons.chksDigits(kyujin.getApplicationform(), 500);
		if (msg != null) {
			messages.add("応募書類は500字以内にしてください");
			result = false;
		}

		// 募集背景の長さチェック
		msg = DataCommons.chksDigits(kyujin.getBackground(), 1000);
		if (msg != null) {
			messages.add("募集背景は1000字以内にしてください");
			result = false;
		}

		// 募集人数の長さチェック
		msg = DataCommons.chksDigits(kyujin.getBosyunumbers(), 4);
		if (msg != null) {
			messages.add("募集人員は4桁以内にしてください");
			result = false;
		}

		// （求職者非公開）年齢の下限・上限の値が適切か
		msg = DataCommons.chkInt(String.valueOf(kyujin.getHiddenagemin().toString()));
		if (msg != null) {
			messages.add("非公開年齢に" + msg);
			result = false;
		}
		msg = DataCommons.chkiDigits(kyujin.getHiddenagemin(), 2);
		if (msg != null) {
			messages.add("非公開年齢の" + msg);
			result = false;
		}
		msg = DataCommons.chkInt(String.valueOf(kyujin.getHiddenagemax().toString()));
		if (msg != null) {
			messages.add("非公開年齢に" + msg);
			result = false;
		}
		msg = DataCommons.chkiDigits(kyujin.getHiddenagemax(), 2);
		if (msg != null) {
			messages.add("非公開年齢の" + msg);
			result = false;
		}
		if (kyujin.getHiddenagemin() > kyujin.getHiddenagemax()) {
			messages.add("年齢の範囲が間違ってます。");
			result = false;
		}

		// その他非公開情報の長さチェック
		msg = DataCommons.chksDigits(kyujin.getHiddenetc(), 1000);
		if (msg != null) {
			messages.add("その他非公開情報は1000字以内にしてください");
			result = false;
		}
		return result;
	}

	/**
	 * 登録完了メッセージ
	 */
	private static final String MSG_ITEM_REGIST_COMPLETE = "求人データの登録が完了しました。";

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

			// 求人情報を取得する
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
			// データベース接続を終了する
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

			// 採番マスタよりデータ取得
			SaibanDao saidao = new SaibanDao(transaction);
			int saiban = saidao.getsaiban();

			// とってきた番号を加工し、Kyujin.noにデータ格納
			String str = String.format("A" + "%013d", saiban);
			kyujin.setNo(str);

			// 求人マスタよりデータ取得
			KyujinDao dao = new KyujinDao(transaction);
			int count = dao.insertKyujin(kyujin);

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
			kyujin.setNo(null);
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

	/**
	 * 登録完了メッセージ
	 */
	private static final String MSG_ITEM_REGIST_DLTCOMPLETE = "求人データの削除フラグ登録が完了しました。";

	/**
	 * 登録失敗メッセージ
	 */
	private static final String MSG_ITEM_REGIST_DLTFAILURE = "求人データの削除フラグ登録に失敗しました。";

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
				messages.add(MSG_ITEM_REGIST_DLTCOMPLETE);
				result = true;
			} else {
				// エラーメッセージをセットする
				messages.add(MSG_ITEM_REGIST_DLTFAILURE);
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

}
