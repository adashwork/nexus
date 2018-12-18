package jp.or.adash.nexus.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.or.adash.nexus.dao.CommentDao;
import jp.or.adash.nexus.dao.MatchingDao;
import jp.or.adash.nexus.dao.SaibanDao;
import jp.or.adash.nexus.entity.Comment;
import jp.or.adash.nexus.entity.JobSeekerMain;
import jp.or.adash.nexus.entity.Kyujin;
import jp.or.adash.nexus.entity.MatchingCase;
import jp.or.adash.nexus.entity.MatchingSearchParameter;
import jp.or.adash.nexus.utils.common.DataCommons;
import jp.or.adash.nexus.utils.common.MessageCommons;
import jp.or.adash.nexus.utils.dao.Transaction;

/**
 * マッチング登録に関する処理を定義するクラス
 * @author ji
 * @author pgjavaAT
 *
 */
public class MatchingService {

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
	 * 求人ＮＯ.／求職者ＩＤのオブジェクト作成
	 * @param kyujinno 求人ＮＯ/jobseekerid 求職者ＮＯ
	 *
	 */
	KyujinService kyujinservice = new KyujinService();
	private String kyujinno;
	Kyujin kyujin = kyujinservice.getKyujin(kyujinno);

	JobSeekerService jobseekerService = new JobSeekerService();
	private String jobseekerid;
	JobSeekerMain jobseekermain = jobseekerService.getJobseekermaininfo(jobseekerid);

	/**
	* マッチング情報の入力内容をチェックする（エラーチェック）
	* @param matching マッチング情報
	* @return 処理結果（true:成功、false:失敗）
	*/
	public boolean check(MatchingCase matching) {
		boolean result = true; // チェック結果

		// 企業IDの値が入力されているか　　　　　　　　　　　追加 2018/12/12 T.IKead
		if (matching.getCompanyNo().equals("")) {
			messages.add("企業IDが入力されていません。");
			result = false;
		}

		// 求人IDの値が入力されているか
		if (matching.getKyujinno().equals("")) {
			messages.add("求人IDが入力されていません。");    // 修正 No→ID 2018/12/12 T.Ikeda
			result = false;
		}

		errMsg = DataCommons.chksDigits(matching.getKyujinno(), 14);
		messages.add(errMsg);

		// 求職者IDの値が入力されているか
		if (matching.getJobseekerid().equals("")) {
			messages.add("求職者IDが入力されていません。");
			result = false;
		}

		errMsg = DataCommons.chksDigits(matching.getJobseekerid(), 8);
		messages.add(errMsg);

		// 職業紹介者IDの値が入力されているか
		if (matching.getStaffid().equals("")) {
			messages.add("職業紹介者IDが入力されていません。");
			result = false;
		}

		errMsg = DataCommons.chksDigits(matching.getStaffid(), 4);
		messages.add(errMsg);

		//面接日の値が入力されているか
		if (matching.getInterviewdt() == null) {
			messages.add("面接日が入力されていません。");
			result = false;
		}

		//入社日の値が入力されているか
		if (matching.getEnterdt() == null) {
			messages.add("入社日が入力されていません。");
			result = false;
		}
		// 評価の値が入力されているか
		if (matching.getAssessment().equals("")) {
			messages.add("評価が入力されていません。");
			result = false;
		}

		// 備考の値が入力されているか
		errMsg = DataCommons.chksDigits(matching.getNote(), 200);
		messages.add(errMsg);

		return result;
	}

	/**
	 * マッチング情報を登録する。
	 * @param matching マッチング情報
	 * @return マッシング情報を登録する。
	 */
	public boolean insertMatchingCase(MatchingCase matching, Comment comment) {	// comment追加 2018/12/14 T.Ikeda
		boolean result = false; //1処理結果

		try {
			// 1データベース接続を開始する
			transaction.open();

			// 1トランザクションを開始する
			transaction.beginTrans();

			SaibanDao sdao = new SaibanDao(transaction);
			int id = sdao.getMatching();
			matching.setId(id);
			comment.setMatchId(id);									// 追加 2018/12/18 T.Ikeda

			// マッチング事例をDBに登録する
			MatchingDao dao = new MatchingDao(transaction);
			int count = dao.insert(matching);

			if (count > 0) {
				// 1完了メッセージをセットする
				messages.add(MessageCommons.MSG_REGIST_COMPLETE);
				result = true;
			} else {
				// 1エラーメッセージをセットする
				messages.add(MessageCommons.MSG_REGIST_FAILURE);
				result = false;
			}

			// コメントをDBに登録する　　　　　　　　　　　　　追加 2018/12/14T.Ikeda
			CommentDao cdao = new CommentDao(transaction);
			int countC = cdao.insert(comment);

			if (countC > 0) {
				// 1完了メッセージをセットする
				messages.add(MessageCommons.MSG_REGIST_COMPLETE);
				result = true;
			} else {
				// 1エラーメッセージをセットする
				messages.add(MessageCommons.MSG_REGIST_FAILURE);
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

	/**
	 * マッチング情報の更新
	 * @param matching
	 * @return
	 */
	public boolean updateMatchingCase(MatchingCase matching, Comment comment) {	// comment追加 2018/12/14 T.Ikeda
		boolean result = false;

		try {
			// 1データベース接続を開始する
			transaction.open();

			// 1トランザクションを開始する
			transaction.beginTrans();

			// 1マッチング情報を取得する
			MatchingDao dao = new MatchingDao(transaction);
			int count = dao.update(matching);

			if (count > 0) {
				// 1完了メッセージをセットする
				messages.add(MessageCommons.MSG_UPDATE_COMPLETE);
				result = true;
			} else {
				// 1エラーメッセージをセットする
				messages.add(MessageCommons.MSG_UPDATE_FAILURE);
				result = false;
			}

			// 1コメント情報を取得する　　　　　　　　　　　　　追加 2018/12/14T.Ikeda
			CommentDao cdao = new CommentDao(transaction);
			int countC = cdao.update(comment);

			if (countC > 0) {
				// 1完了メッセージをセットする
				messages.add(MessageCommons.MSG_UPDATE_COMPLETE);
				result = true;
			} else {
				// 1エラーメッセージをセットする
				messages.add(MessageCommons.MSG_UPDATE_FAILURE);
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

	/**
	 * idを元にマッチング事例を取得
	 * @param id
	 * @return
	 */
	public MatchingCase getMatching(int id) {
		MatchingCase matching = null;
//		Comment matchingComment = null;

		try {
			// データベース接続を開始する
			transaction.open();

			// idを元にマッチング事例を取得
			MatchingDao dao = new MatchingDao(transaction);
			matching = dao.select(id);

//			// マッチングidを元にマッチングコメントを取得
//			CommentDao cdao = new CommentDao(transaction);
//			matchingComment = cdao.selectMatching(matchid);


		} catch (IOException e) {
			// エラーメッセージをセットする
		} finally {
			// データベース接続を終了する
			transaction.close();
		}

		return matching;
	}

	// 2018/12/17 kitayama 新規作成
	/**
	 * マッチング事例を取得する
	 * @param msp 検索条件オブジェクト
	 * @return マッチング事例のListオブジェクト
	 */
	public List<MatchingCase> getMatchingV2(MatchingSearchParameter msp) {
		List<MatchingCase> matching = new ArrayList<MatchingCase>();

		try {
			// データベース接続を開始する
			transaction.open();

			// idを元にマッチング事例を取得
			MatchingDao dao = new MatchingDao(transaction);
			matching = dao.selectV2(msp);

		} catch (IOException e) {
			// エラーメッセージをセットする
		} finally {
			// データベース接続を終了する
			transaction.close();
		}

		return matching;
	}


}
