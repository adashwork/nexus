package jp.or.adash.nexus.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.events.Comment;

import jp.or.adash.nexus.dao.CompanyDao;
import jp.or.adash.nexus.entity.Company;
import jp.or.adash.nexus.entity.CompanySearch;
import jp.or.adash.nexus.utils.dao.Transaction;

/**
 * 企業情報の登録・更新・削除／検索に関連するServiceクラス
 * @author
 *
 */

public class CompanyService {

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
	 * メッセージのリストを取得する
	 * @return  List<String>
	 */
	public List<String> getMessages() {
		return messages;
	}


	/**
	 * Companyデータのバリデーション
	 * @param company
	 * @return true:成功時  false:失敗時
	 */
	public boolean check(Company company) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}


	/**
	 * 企業情報を新規登録する
	 * @param company
	 * @return true:成功時  false:失敗時
	 */
	public boolean insertCompany(Company company) {
		return false;
		// TODO 自動生成されたメソッド・スタブ

	}

	/**
	 * 企業情報を更新する
	 * @param company
	 * @return true:成功時  false:失敗時
	 */
	public boolean updateCompany(Company company) {
		return false;
		// TODO 自動生成されたメソッド・スタブ

	}

	/**
	 * 企業情報を削除する
	 * @param company
	 * @return true:成功時  false:失敗時
	 */
	public boolean delteCompany(Company company) {
		return false;
		// TODO 自動生成されたメソッド・スタブ

	}

	/**
	 * 企業の詳細情報を取得する
	 * @param companyNo
	 * @return Companyオブジェクト
	 */
	public Company getCompanyInfo(String companyNo) {
		return null;
	}

	/**
	 * 企業のコメント一覧を取得する
	 * @param companyNo
	 * @return Commentオブジェクト
	 */
	public List<Comment> getCompanyCommentList(String companyNo) {
		return null;
	}





	/**
	 * 企業情報を取得する（検索）
	 * @return List<Company> companyList 該当した企業の一覧
	 * @author mosco
	 */
	public List<Company> getCompany(CompanySearch cse) {
		List<Company> companyList = new ArrayList<>();

		Transaction transaction = new Transaction();
		CompanyDao dao;
		try {
			// データベース接続を開く
			transaction.open();
			// DBから企業情報を取得し、Dao内のメソッドでListに詰め、そのListを返してもらう
			dao = new CompanyDao(transaction);
			companyList = dao.selectCompanyList(cse);

		} catch(IOException e) {
			// DB接続が失敗した場合、例外をキャッチする
			// TODO エラーメッセージの表示 リストorStringにエラーメッセージを格納し、検索画面で表示
		} finally {
			try {
				// DB接続の終了
				dao = null;
				transaction.close();
			} catch(Exception e) {
				transaction = null;
			}
		}

		return companyList;
	}



}
