/**
 *
 */
package jp.or.adash.nexus.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.or.adash.nexus.dao.JobCategoryDao;
import jp.or.adash.nexus.entity.JobCategory;
import jp.or.adash.nexus.utils.common.MessageCommons;
import jp.or.adash.nexus.utils.dao.Transaction;

/**
 * 業種に関する処理を定義するクラス
 * @author aizawa
 *
 */
public class JobCategoryService {

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
	public JobCategoryService() {
		transaction = new Transaction();
		messages = new ArrayList<String>();
	}

	/**
	 * すべての業種大分類リストを取得する
	 * @return 業種リスト
	 */
	public List<JobCategory> getLargeJobCategoryList() {
		List<JobCategory> LargeJobCategoryList = new ArrayList<JobCategory>();

		try {
			// データベース接続を開始する
			transaction.open();

			// 商品リストを取得する
			JobCategoryDao dao = new JobCategoryDao(transaction);
			LargeJobCategoryList = dao.selectLargeJobCategoryList();

		} catch (IOException e) {
			// エラーメッセージをセットする
			messages.add(MessageCommons.ERR_DB_CONNECT);
		} finally {
			// データベース接続を終了する
			transaction.close();
		}

		return LargeJobCategoryList;
	}

	/**
	 * 指定した業種大分類コードに属する
	 * 業種小分類リストを取得する
	 * @param 大分類コード
	 * @return 業種リスト(中分類コード、名前)
	 * @author mmiyamoto
	 */
	public List<JobCategory> getMiddleJobCategoryList(String largecd) {
		List<JobCategory> middleJobCategoryList = new ArrayList<JobCategory>();

		try {
			// データベース接続を開始する
			transaction.open();

			// 商品リストを取得する
			JobCategoryDao dao = new JobCategoryDao(transaction);
			middleJobCategoryList = dao.selectMiddleJobCategoryList(largecd);

		} catch (IOException e) {
			// エラーメッセージをセットする
			messages.add(MessageCommons.ERR_DB_CONNECT);
		} finally {
			// データベース接続を終了する
			transaction.close();
		}

		return middleJobCategoryList;
	}
	/**
	 * すべての業種小分類リストを取得する
	 * @return 業種リスト
	 */
	public List<JobCategory> getSmallJobCategoryList() {
		List<JobCategory> SmallJobCategoryList = new ArrayList<JobCategory>();

		try {
			// データベース接続を開始する
			transaction.open();

			// 商品リストを取得する
			JobCategoryDao dao = new JobCategoryDao(transaction);
			SmallJobCategoryList = dao.selectSmallJobCategoryList();

		} catch (IOException e) {
			// エラーメッセージをセットする
			messages.add(MessageCommons.ERR_DB_CONNECT);
		} finally {
			// データベース接続を終了する
			transaction.close();
		}

		return SmallJobCategoryList;
	}

	/**
	 * 指定した業種中分類コードに属する
	 * 業種小分類リストを取得する
	 * @param middleCode 中分類コード
	 * @return 業種リスト(小分類コード、名前)
	 * @author mmiyamoto
	 */
	public List<JobCategory> getSmallJobCategoryList(String middlecd) {
		List<JobCategory> SmallJobCategoryList = new ArrayList<JobCategory>();

		try {
			// データベース接続を開始する
			transaction.open();

			// 商品リストを取得する
			JobCategoryDao dao = new JobCategoryDao(transaction);
			SmallJobCategoryList = dao.selectSmallJobCategoryList(middlecd);

		} catch (IOException e) {
			// エラーメッセージをセットする
			messages.add(MessageCommons.ERR_DB_CONNECT);
		} finally {
			// データベース接続を終了する
			transaction.close();
		}

		return SmallJobCategoryList;
	}



}
