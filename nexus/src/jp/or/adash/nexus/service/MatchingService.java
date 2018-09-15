package jp.or.adash.nexus.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.or.adash.nexus.dao.MatchingDao;
import jp.or.adash.nexus.entity.MatchingCase;
import jp.or.adash.nexus.utils.common.MessageCommons;
import jp.or.adash.nexus.utils.dao.Transaction;


/**
 * 登録に関する処理を定義するクラス
 * @author J.Ibushi
 *
 */
public class MatchingService {

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
	public MatchingService() {
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
	 * 商品コードを元に、商品情報を取得する
	 * @param code 商品コード
	 * @return 商品情報
	 */
	/*public MatchingCases getItem(int id, String kyujinno, String jobseekerid, String staffid, Date interviewdt,
			Date enterdt,
			String assessment, String note, Timestamp createdt, String createuserid,
			Timestamp upDatedt, String upDateuserid) {
		MatchingCases matchingcases = null;

		try {
			// 1データベース接続を開始する
			transaction.open();

			// 1商品単価を取得する
			MadaoTransaction dao = new MadaoTransaction();
			MatchingCases matchingcases = dao.selectMatchingCases(id);

		} catch (IOException e) {
			// 1エラーメッセージをセットする
		} finally {
			// 1データベース接続をを終了する
			transaction.close();
		}

		return item;
	}

	/**
	 * 商品リストを取得する
	 * @return 商品リスト
	 */
	/*public List<MatchingCases> getList() {
		List<MatchingCases> List = new ArrayList<MatchingCases>();

		try {
			// 1データベース接続を開始する
			transaction.open();

			// 1商品リストを取得する
			MatchingDao dao = new MatchingDao(transaction);
			itemList = dao.selectItemList();

		} catch (IOException e) {
			// 1エラーメッセージをセットする
			messages.add(MessageCommons.ERR_DB_CONNECT);
		} finally {
			// 1データベース接続を終了する
			transaction.close();
		}

		return itemList;
	}

	/**
	 * 商品価格を取得する
	 * @param itemNo 商品番号
	 * @return 商品価格（エラーの場合、-1をセット）
	 */
	/*public int getItemPrice(int itemNo) {
		// 1商品単価（商品がなければ、0）
		int unitPrice = 0;

		try {
			// 1データベース接続を開始する
			transaction.open();

			// 1商品単価を取得する
			MatchingDao dao = new MatchingDao(transaction);
			Item item = dao.selectItem(itemNo);
			unitPrice = item.getUnitPrice();

		} catch (IOException e) {
			// 1エラーメッセージをセットする
			unitPrice = -1;
		} finally {
			// 1データベース接続をを終了する
			transaction.close();
		}

		return unitPrice;
	}
/*
	/**
	 * 商品データの内容をチェックする
	 * @param item 商品データ
	 * @return 処理結果（true:成功、false:失敗）
	 */
	/*
	public boolean check(Item item) {
		boolean result = true; // 1チェック結果

		// 1商品コードの値が正しいか
		if (item.getItemNo() <= 0) {
			messages.add("商品コードに0より小さい値は指定できません。");
			result = false;
		}

		// 1商品名の長さが適切か
		DataCommons commons = new DataCommons();
		int length = commons.getBytes(item.getItemName());
		if (length <= 0 || length >= 100) {
			messages.add("商品名の文字数が多すぎます。");
			result = false;
		}

		// 1単価の値が正しいか
		if (item.getUnitPrice() <= 0) {
			messages.add("単価は1円以上で入力してください。");
			result = false;
		}

		return result;
	}
/*
	/**
	 * 登録完了メッセージ
	 */
	private static final String MSG_ITEM_REGIST_COMPLETE = "商品登録が完了しました。";

	/**
	 * 登録失敗メッセージ
	 */
	private static final String MSG_ITEM_REGIST_FAILURE = "商品登録に失敗しました。";

	/**
	 * マッチング結果データを登録する
	 * @param maching 商品データ
	 * @return 処理結果（true:成功、false:失敗）
	 */
	//public boolean registItem(MatchingCases matching) {
		//boolean result = false;

		// 1データベースに商品が既に存在するかどうか確認する
		/*if (exists(item.getItemNo())) {
			// 存在する場合は、商品データを更新する
			result = this.updateItem(item);
		} else {
			// 1存在しなければ、商品データを登録する
			result = this.insertItem(item);
		}

		return result;
	}

	/**
	 * 商品コードがデータベースに既に存在するかどうかを確認する
	 * @param code 商品コード
	 * @return true:存在する、false:存在しない
	 */
	/*
	private boolean exists(int code) {
		boolean result = false; // 1確認結果

		// 1コードをキーにして、データベースを検索する
		int price = this.getItemPrice(code);

		// 1データが存在する場合、true	を返す
		if (price > 0) {
			result = true;
		}

		return result;
	}
/*
	/**
	 * 商品データを更新する
	 * @param item 商品データ
	 * @return 処理結果（true:成功、false:失敗）
	 */
	/*public boolean updateItem(Item item) {
		boolean result = false; // 1処理結果

		try {
			// 1データベース接続を開始する
			transaction.open();

			// 1トランザクションを開始する
			transaction.beginTrans();

			// 1商品単価を取得する
			MatchingDao dao = new MatchingDao(transaction);
			int count = dao.update(item);

			if (count > 0) {
				// 1完了メッセージをセットする
				messages.add(MSG_ITEM_REGIST_COMPLETE);
				result = true;
			} else {
				//1エラーメッセージをセットする
				messages.add(MSG_ITEM_REGIST_FAILURE);
			}

			// 1トランザクションをコミットする
			transaction.commit();

		} catch (IOException e) {
			// 1トランザクションをロールバックする
			transaction.rollback();

			// 1エラーメッセージをセットする
			messages.add(MessageCommons.ERR_DB_CONNECT);
		} finally {
			// 1データベース接続をを終了する
			transaction.close();
		}

		return result;
	}

	/**
	 * マッチング結果をデータベースに登録する
	 * @param  matching マッチング結果データ
	 * @return 処理結果（true:成功、false:失敗）
	 */
	public boolean insertMatchingCases(MatchingCase matching) {
		boolean result = false; //1処理結果

		try {
			// 1データベース接続を開始する
			transaction.open();

			// 1トランザクションを開始する
			transaction.beginTrans();

			// 1商品単価を取得する
			MatchingDao dao = new MatchingDao(transaction);
			int count = dao.insert(matching);

			if (count > 0) {
				// 1完了メッセージをセットする
				messages.add(MSG_ITEM_REGIST_COMPLETE);
				result = true;
			} else {
				// 1エラーメッセージをセットする
				messages.add(MSG_ITEM_REGIST_FAILURE);
				result = false;
			}

			//1 トランザクションをコミットする
			transaction.commit();

		} catch (IOException e) {
			// 1トランザクションをロールバックする
			transaction.rollback();

			// 1エラーメッセージをセットする
			messages.add(MessageCommons.ERR_DB_CONNECT);
		} finally {
			//1 データベース接続をを終了する
			transaction.close();
		}

		return result;
	}

}
