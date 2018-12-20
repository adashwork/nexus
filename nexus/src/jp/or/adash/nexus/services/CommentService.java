package jp.or.adash.nexus.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.or.adash.nexus.dao.CommentDao;
import jp.or.adash.nexus.dao.SaibanDao;
import jp.or.adash.nexus.entity.Comment;
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


	public CommentService(){
		transaction = new Transaction();
		messages = new ArrayList<String>();
	}


	/**
	 * 入力チェック
	 * @param comment
	 * @return checkResult true or false
	 */
	public boolean checkComment(Comment comment) {
		boolean checkResult = true;

		// フォーマットチェック
		// 事業所NO（13桁）
		if(comment.getCompanyNo() != null && !comment.getCompanyNo().equals("")) {
			if(comment.getCompanyNo().length() != 13) {
				messages.add("事業所NOは13桁で入力してください。");
				checkResult = false;
			}
		}

		// 求人NO（14桁）
		if(comment.getKyujinNo() != null && !comment.getKyujinNo().equals("")) {
			if(comment.getKyujinNo().length() != 14) {
				messages.add("求人NOは14桁で入力してください。");
				checkResult = false;
			}
		}

		// カテゴリ選択「0：選択なし」ならエラー
		if(comment.getGenre().equals("0")) {
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
			int saiban = saidao.getsaiban();

			//とってきた番号を加工し、Kyujin.noにデータ格納
			String str = String.format("A" + "%013d", saiban);
			// comment.setId(str);

			CommentDao commentDao = new CommentDao(transaction);
			int count = commentDao.insert(comment);

			if (count > 0) {
				// 完了メッセージをセットする
				messages.add("登録が完了しました");


			} else {
				// エラーメッセージをセットする
				messages.add("登録に失敗しました");
				// 登録に失敗した場合は備考IDを返さない
				comment.setId(null);


			}

			// トランザクションをコミットする
			transaction.commit();

		} catch (IOException e) {
			// トランザクションをロールバックする
			transaction.rollback();

			// エラーメッセージをセットする
			messages.add("データベースアクセスに失敗しました。");
		} finally {
			// データベース接続をを終了する
			transaction.close();
		}

		return comment;

	}
}
