package jp.or.adash.nexus.services;

import java.util.List;

import javax.xml.stream.events.Comment;

import jp.or.adash.nexus.entity.Company;
import jp.or.adash.nexus.utils.dao.Transaction;

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




}
