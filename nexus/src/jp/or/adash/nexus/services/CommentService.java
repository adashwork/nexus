package jp.or.adash.nexus.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.or.adash.nexus.dao.CommentDao;
import jp.or.adash.nexus.dao.SaibanDao;
import jp.or.adash.nexus.entity.Comment;
import jp.or.adash.nexus.entity.CommentSearchParameter;
import jp.or.adash.nexus.utils.common.MessageCommons;
import jp.or.adash.nexus.utils.dao.Transaction;

public class CommentService {

	/**
	 * トランザクションオブジェクト
	 */
	private Transaction transaction;

	/**
	 * 処理結果メッセージを格納するリスト
	 */
	private List<String> messages;

	/**
	 * メッセージのリストを取得する
	 * @return  List<String>
	 */
	public List<String> getMessages() {
		return messages;
	}

	public CommentService() {
		transaction = new Transaction();
		messages = new ArrayList<String>();
	}

	/**
	 * 入力チェック
	 * @param comment
	 * @return checkResult true or false
	 * @author mosco(2018/12/21 完了確認)
	 */
	public boolean checkComment(Comment comment) {
		boolean checkResult = true;

		// フォーマットチェック
		// 事業所NO（13桁）
		if (comment.getCompanyNo() != null && !comment.getCompanyNo().equals("")) {
			if (comment.getCompanyNo().length() != 13) {
				messages.add("事業所NOは13桁で入力してください。");
				checkResult = false;
			}
		}

		// 求人NO（14桁）
		if (comment.getKyujinNo() != null && !comment.getKyujinNo().equals("")) {
			if (comment.getKyujinNo().length() != 14) {
				messages.add("求人NOは14桁で入力してください。");
				checkResult = false;
			}
		}

		// カテゴリ選択「0：選択なし」ならエラー
		if (comment.getGenre().equals("0")) {
			messages.add("内容分類を選択してください。");
			checkResult = false;
		}

		return checkResult;
	}

	/**
	 * コメントの登録
	 * @param Comment
	 * @return true:成功時  false:失敗時
	 */
	public Comment insertComment(Comment comment) {
		// TODO ここでIDを入れなおす
		boolean result = false; // 処理結果

		try {
			// データベース接続を開始する
			transaction.open();

			// トランザクションを開始する
			transaction.beginTrans();

			//采番マスタよりデータ取得
			SaibanDao saidao = new SaibanDao(transaction);
			int saiban = saidao.getCommentInt();

			// Commentオブジェクトに割り振られたIDをセット
			comment.setId(saiban);

			CommentDao commentDao = new CommentDao(transaction);
			int count = commentDao.insertV2(comment);

			if (count > 0) {
				// 完了メッセージをセットする
				messages.add(MessageCommons.MSG_REGIST_COMPLETE);

			} else {
				// エラーメッセージをセットする
				messages.add(MessageCommons.MSG_REGIST_FAILURE);
				// 登録に失敗した場合は備考IDを返さない
				comment.setId(null);

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

		return comment;
	}

	/**
	 * 求人・企業・求職者各画面からコメント（備考）一覧を取得する
	 * パラメータを受け取るのは各呼び出し元のServletにて指定
	 * @param CommentSearchParameter
	 * @return commentList
	 * @author mosco
	 */
	public List<Comment> commentSearch(CommentSearchParameter csp){
		List<Comment> commentList = new ArrayList<>();

		Transaction transaction = new Transaction();
		CommentDao dao;
		try {
			// データベース接続を開く
			transaction.open();
			// DBから企業情報を取得し、Dao内のメソッドでListに詰め、そのListを返してもらう
			dao = new CommentDao(transaction);
			commentList = dao.selectCommentList(csp);

		} catch (IOException e) {
			// DB接続が失敗した場合、例外をキャッチする
			messages.add(MessageCommons.ERR_DB_CONNECT);
		} finally {
			try {
				// DB接続の終了
				dao = null;
				transaction.close();
			} catch (Exception e) {
				transaction = null;
			}
		}

		return commentList;
	}

	/**
	 * コメントを更新する
	 * @param comment コメントオブジェクト
	 * @return result 更新に成功すればtrue、失敗すればfalseを返す
	 */
	public boolean updateComment(Comment comment) {
		boolean result = false;

		try {
			// データベース接続を開始する
			transaction.open();

			// トランザクションを開始する
			transaction.beginTrans();

			CommentDao commentDao = new CommentDao(transaction);
			int count = commentDao.updateV2(comment);

			if (count > 0) {
				// 完了メッセージをセットする
				messages.add(MessageCommons.MSG_UPDATE_COMPLETE);
				result = true;
			} else {
				// エラーメッセージをセットする
				messages.add(MessageCommons.MSG_UPDATE_FAILURE);
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
	 * コメントを削除する
	 * @param id コメントID
	 * @return true:成功時  false:失敗時
	 */
	public boolean commentDelete(int id) {
		boolean result = false; // 処理結果
		try {
			// データベース接続を開始する
			transaction.open();

			// トランザクションを開始する
			transaction.beginTrans();

			CommentDao commentDao = new CommentDao(transaction);
			int count = commentDao.deleteV2(id);

			if (count > 0) {
				// 完了メッセージをセットする
				messages.add("コメントが削除されました");
				result = true;
			} else {
				// エラーメッセージをセットする
				messages.add("コメントを削除できませんでした");
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
	 * 備考IDでコメントを一件取得する
	 * @param id
	 * @return comment
	 * @author mosco(2018/12/21完了)
	 */
	public Comment commentSearch2(int id){
		Comment comment = null;

		Transaction transaction = new Transaction();
		CommentDao dao;
		try {
			// データベース接続を開く
			transaction.open();
			// DBから企業情報を取得し、Dao内のメソッドでListに詰め、そのListを返してもらう
			dao = new CommentDao(transaction);
			comment = dao.selectComment(id);

		} catch (IOException e) {
			// DB接続が失敗した場合、例外をキャッチする
			messages.add(MessageCommons.ERR_DB_CONNECT);
		} finally {
			try {
				// DB接続の終了
				dao = null;
				transaction.close();
			} catch (Exception e) {
				transaction = null;
			}
		}

		return comment;
	}

}
