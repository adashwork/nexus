package jp.or.adash.nexus.services;

import java.io.IOException;

import jp.or.adash.nexus.dao.StaffLoginDao;
import jp.or.adash.nexus.entity.Staff;
import jp.or.adash.nexus.utils.dao.Transaction;

/**
 * ログイン処理に関する処理を定義するクラス
 * @author H.Tomonari
 */

public class LoginService {

	//0トランザクションオブジェクト
	private Transaction transaction;

	//0IDを元にユーザー情報を取得する
	public Staff getAuthUser(String user,String pass) {
		Staff userData = null;
		try {
			transaction = new Transaction();
			//0データベース接続を開始する
			transaction.open();
			//0ユーザー情報を取得する
			StaffLoginDao dao = new StaffLoginDao(transaction);
			userData = dao.getLogin(user, pass);
		} catch (IOException e) {
			//0エラー処理を行う
		} finally {
			//0データベース接続をを終了する
			transaction.close();
		}
		return userData;
	}
}
