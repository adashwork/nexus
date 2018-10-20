package jp.or.adash.nexus.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.or.adash.nexus.dao.AccountListDao;
import jp.or.adash.nexus.entity.Staff;
import jp.or.adash.nexus.utils.common.MessageCommons;
import jp.or.adash.nexus.utils.dao.Transaction;

public class AccountListService {
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
	public AccountListService() {
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
	 * アカウント情報一覧を取得する
	 * @return アカウント一覧リスト
	 * @author H.Tomonari
	 */
	public List<Staff> getAccountList() {
		List<Staff> AccountList = new ArrayList<Staff>();
		try {
			//0データベース接続を開始する
			transaction.open();

			//0アカウントリストを取得する
			AccountListDao dao = new AccountListDao(transaction);
			AccountList = dao.selectAccountList();

		} catch (IOException e) {
			//0エラーメッセージをセットする
			messages.add(MessageCommons.ERR_DB_CONNECT);
		} finally {
			//0データベース接続を終了する
			transaction.close();
		}
		return AccountList;
	}

}
