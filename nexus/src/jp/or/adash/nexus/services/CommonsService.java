package jp.or.adash.nexus.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.or.adash.nexus.dao.CommonsDao;
import jp.or.adash.nexus.utils.common.MessageCommons;
import jp.or.adash.nexus.utils.dao.Transaction;

/**
 * 共通で使用するデータベースへの処理を定義するクラス
 * @author pgjavaAT
 */
public class CommonsService {

	String errMsg = null;

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
	public CommonsService() {
		transaction = new Transaction();
		messages = new ArrayList<String>();
	}

	/**
	 * noから該当する求人があるか確認する
	 * @param no
	 * @return
	 */
	public boolean checkKyujin(String no) {
		boolean result = false;

		try {
			// データベース接続を開始する
			transaction.open();

			// トランザクションを開始する
			transaction.beginTrans();

			// 商品単価を取得する
			CommonsDao dao = new CommonsDao(transaction);
			result = dao.selectKyujinno(no);

			if(!result) {
				messages.add(MessageCommons.MSG_KYUJIN_FAILURE);
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
	 * idから該当する求職者があることを確認する
	 * @param id
	 * @return
	 */
	public boolean checkJobseeker(String id) {
		boolean result = false;

		try {
			// データベース接続を開始する
			transaction.open();

			// トランザクションを開始する
			transaction.beginTrans();

			// 商品単価を取得する
			CommonsDao dao = new CommonsDao(transaction);
			result = dao.selectJobseeker(id);

			if(result == false){
				messages.add(MessageCommons.MSG_JOBSEEKER_FAILURE);
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

	public String checkStaffName(String id) {
		String staffName = null;

		try {
			// データベース接続を開始する
			transaction.open();

			// トランザクションを開始する
			transaction.beginTrans();

			// 商品単価を取得する
			CommonsDao dao = new CommonsDao(transaction);
			staffName = dao.getStaffName(id);

			if(staffName == null){
				messages.add(MessageCommons.MSG_STAFFNAME_FAILURE);
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

		return staffName;
	}
	/**
	 * 処理結果メッセージを取得する
	 * @return 処理結果メッセージ
	 */
	public List<String> getMessages() {
		return messages;
	}
}
