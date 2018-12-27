package jp.or.adash.nexus.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.or.adash.nexus.dao.CommentDao;
import jp.or.adash.nexus.dao.CompanyDao;
import jp.or.adash.nexus.dao.SaibanDao;
import jp.or.adash.nexus.entity.Comment;
import jp.or.adash.nexus.entity.Company;
import jp.or.adash.nexus.entity.CompanySearch;
import jp.or.adash.nexus.entity.CompanySearchResult;
import jp.or.adash.nexus.utils.common.MessageCommons;
import jp.or.adash.nexus.utils.common.StringCommons;
import jp.or.adash.nexus.utils.dao.Transaction;

/**
 * 企業に関する処理のクラス
 * @author mmiyamoto
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
	 * コンストラクタ
	 */
	public CompanyService() {
		transaction = new Transaction();
		messages = new ArrayList<String>();
	}

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
	 * @param 新規登録の場合true, 更新の場合はfalse
	 * @return true:数値に異常がなければ  false:異常が１つでもあれば
	 */
	public boolean check(Company company, boolean newRegist) {
		boolean checkResult = true;

		//共通データチェッククラス
		CommonsService commonsService = new CommonsService();

		//項目ごとにバリデーションを行う
		//事業者番号(PK)
		//からっぽの場合はエラーを出さない。基本的にその場合は独自の事業所番号を割り振るため。
		if (!"".equals(company.getCompanyNo()) && (newRegist == true)) {

			//既に登録済みかチェック
			if (isRegistCompany(company.getCompanyNo())) {
				checkResult = false;
				messages.add("その事業所番号は既に登録済みです");
			}

			//フォーマットチェック
			if (!company.getCompanyNo().matches("^[0-9]{4}-[0-9]{6}-[0-9]{1}$")) {
				checkResult = false;
				messages.add("事業所番号は0000-000000-0のフォーマットで記入してください");
			}

		}

		//法人番号
		if (!company.getCorporateNumber().matches("^[0-9]{13}$|^$")) {
			messages.add("法人番号は半角数字13桁で書いてください");
			checkResult = false;
		}

		//事業所名
		if ("".equals(company.getCompanyName())) {
			messages.add("事業名を入力してください");
			checkResult = false;
		}

		//事業所名カナ
		if ("".equals(company.getCompanyKana())) {
			messages.add("事業名(カナ)を入力してください");
			checkResult = false;
		} else {
			if (!company.getCompanyKana().matches("^[ァ-ヶー]*$")) {
				messages.add("事業名(カナ)は全角カタカナで入力してください");
				checkResult = false;
			}
		}

		//事業所郵便番号
		if ("".equals(company.getCompanyPostal())) {

		} else {
			if (!company.getCompanyPostal().matches("^[0-9]{3}-[0-9]{4}$")) {
				messages.add("郵便番号は半角数字とハイフンを使って次のように入力してください。「123-4567」");
				checkResult = false;
			}
		}

		//事業所所在地
		if (company.getCompanyPlace().length() > 75) {
			messages.add("事業所所在地は75文字以内でお願いします");
			checkResult = false;
		}

		//最寄駅
		if (company.getNearStation().length() > 35) {
			messages.add("事業所所在地は35文字以内でお願いします");
			checkResult = false;
		}

		//事業所URL
		if (company.getCompanyUrl().length() > 100) {
			messages.add("事業所所在地は100文字以内でお願いします");
			checkResult = false;
		}

		//産業小分類コード
		//産業中分類コード
		//産業大分類コード

		//資本金

		//オーバーフローしている場合を考慮して、上限値も含む
		if (company.getCapital() != null) {
			if (company.getCapital() >= 2147483647 ) {
				messages.add("資本金は2147483647万までしか入力できません");
				checkResult = false;
			} else if(company.getCapital() < 0) {
				messages.add("資本金にマイナスの値は入力できません");
				checkResult = false;
			}
		}

		//従業員数

		//創業設立年
		if (company.getEstablishDt() != null) {
			if (company.getEstablishDt() > 9999) {
				messages.add("創業設立年は9999年までしか入力できません");
				checkResult = false;
			}
		}

		//担当者課係名/役職名
		if (company.getTantouYakushoku().length() > 28) {
			messages.add("担当役職は28文字以内でお願いします");
			checkResult = false;
		}

		//担当者名
		if (company.getTantou().length() > 14) {
			messages.add("担当者名は14文字以内でお願いします");
			checkResult = false;
		}

		//担当者名(かな)

		if (!company.getTantouKana().matches("^[ぁ-ん]{0,28}$")) {
			messages.add("担当者名(かな)は全角ひらがな28文字以内で入力してください");
			checkResult = false;
		}
		//担当者TEL
		if (company.getTantouTel().length() > 20) {
			messages.add("担当者TELは20文字以内でお願いします");
			checkResult = false;
		} else if(company.getTantouTel().matches("^-.*$")) {
			messages.add("担当者TELにマイナスの値は入力できません");
			checkResult = false;
		}
		//担当者FAX
		if (company.getTantouFax().length() > 20) {
			messages.add("担当者FAXは20文字以内でお願いします");
			checkResult = false;
		} else if(company.getTantouFax().matches("^-.*$") ) {
			messages.add("担当者FAXにマイナスの値は入力できません");
			checkResult = false;
		}
		//担当者email
		if (company.getTantouMail().length() > 50) {
			messages.add("担当者emailは50文字以内でお願いします");
			checkResult = false;
		}

		//担当開拓者ID

		//営業評価ランクABC

		return checkResult;
	}

	/**
	 * 企業情報を新規登録する
	 * @param company
	 * @return true:成功時  false:失敗時
	 */
	public boolean insertCompany(Company company) {
		boolean result = false; // 処理結果

		try {
			// データベース接続を開始する
			transaction.open();

			// トランザクションを開始する
			transaction.beginTrans();

			if ("".equals(company.getCompanyNo())) {
				//独自事業所番号を発行する
				company.setCompanyNo(createUniqueCompanyNo());
			}
			CompanyDao companyDao = new CompanyDao(transaction);
			int count = companyDao.insertCompany(company);

			if (count > 0) {
				// 完了メッセージをセットする
				messages.add("登録が完了しました");
				result = true;
			} else {
				// エラーメッセージをセットする
				messages.add("登録に失敗しました");
				result = false;
			}

			// トランザクションをコミットする
			transaction.commit();

		} catch (IOException e) {
			//独自事業所番号を破棄する
			company.setCompanyNo(null);
			// トランザクションをロールバックする
			transaction.rollback();

			// エラーメッセージをセットする
			messages.add("データベースアクセスに失敗しました。");
		} finally {
			// データベース接続をを終了する
			transaction.close();
		}

		return result;

	}

	/**
	 * 企業情報を更新する
	 * @param company
	 * @return true:成功時  false:失敗時
	 */
	public boolean updateCompany(Company company) {
		boolean result = false; // 処理結果
		try {
			// データベース接続を開始する
			transaction.open();

			// トランザクションを開始する
			transaction.beginTrans();

			CompanyDao companyDao = new CompanyDao(transaction);
			int count = companyDao.updateCompany(company);

			if (count > 0) {
				// 完了メッセージをセットする
				messages.add("更新が完了しました");
				result = true;
			} else {
				// エラーメッセージをセットする
				messages.add("更新に失敗しました");
				result = false;
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

		return result;

	}

	/**
	 * 企業情報を削除する
	 * @param companyNo
	 * @return true:成功時  false:失敗時
	 */
	public boolean delteCompany(String companyNo) {
		boolean result = false; // 処理結果
		try {
			// データベース接続を開始する
			transaction.open();

			// トランザクションを開始する
			transaction.beginTrans();

			CompanyDao companyDao = new CompanyDao(transaction);
			int count = companyDao.deleteCompany(companyNo);

			if (count > 0) {
				// 完了メッセージをセットする
				messages.add("企業情報が削除されました");
				result = true;
			} else {
				// エラーメッセージをセットする
				messages.add("企業を削除できませんでした");
				result = false;
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

		return result;

	}

	/**
	 * 企業の詳細情報を取得する
	 * @param companyNo
	 * @return Companyオブジェクト
	 */
	public Company getCompanyInfo(String companyNo) {
		Company company = null;

		try {
			// データベース接続を開始する
			transaction.open();

			// 企業情報を取得する
			CompanyDao dao = new CompanyDao(transaction);
			company = dao.selectCompanyInfo(companyNo);

		} catch (IOException e) {
			// エラーメッセージをセットする
		} finally {
			// データベース接続をを終了する
			transaction.close();
		}

		return company;
	}

	/**
	 *
	 * 企業のコメント一覧を取得する
	 * @deprecated コメントに関する処理はCommentサービスに統合しました。
	 * @param companyNo
	 * @return Commentオブジェクト
	 */
	public List<Comment> getCompanyCommentList(String companyNo) {
		List<Comment> commentList = null;

		try {
			// データベース接続を開始する
			transaction.open();

			// 企業情報を取得する
			CommentDao dao = new CommentDao(transaction);
			commentList = dao.selectCompanyCommentList(companyNo);

		} catch (IOException e) {
			// エラーメッセージをセットする
		} finally {
			// データベース接続をを終了する
			transaction.close();
		}

		return commentList;

	}

	/**
	 * 独自のユニークなCompanyNoを取得する
	 * 頭に「A」を付け加えた13文字の独自companyNoを生成
	 * Transactionがopenされていることが前提
	 *
	 * @return CompanyNo
	 * @throws IOException
	 */
	public String createUniqueCompanyNo() throws IOException {
		//采番マスタよりデータ取得

		if (transaction.isActive()) {
			try {
				SaibanDao saidao = new SaibanDao(transaction);
				int saiban = saidao.getCompanyInt();
				String saibanString = String.valueOf(saiban);

				//独自の事業所番号を生成
				StringBuffer uniqueCompanyNo = new StringBuffer();
				uniqueCompanyNo.append("A");
				for (int i = saibanString.length() + 1; i < 11; i++) {
					uniqueCompanyNo.append("0");
				}
				uniqueCompanyNo.append(saibanString);
				uniqueCompanyNo.insert(4, "-");
				uniqueCompanyNo.insert(11, "-");

				return uniqueCompanyNo.toString();
			} catch (IOException e) {
				messages.add("事業所番号の発行に失敗しました");
				throw new IOException(e);

			}
		} else {
			messages.add("事業所番号発行のための正しい処理が踏まれていません");
			throw new IOException();
		}

	}

	/**
	 * 事業番号を元に、その企業が登録済みか確認する
	 * @param CompanyNo
	 * @return ture:登録済み false:未登録
	 * @throws IOException
	 */
	public boolean isRegistCompany(String CompanyNo) {
		try {
			transaction.open();
			CompanyDao companyDao = new CompanyDao(transaction);
			if (companyDao.isRegistCompany(CompanyNo)) {
				return true;
			}
		} catch (IOException e) {

		} finally {
			// データベース接続を終了する
			transaction.close();
		}
		return false;
	}

	/**
	 * 企業情報の検索
	 * @param cse
	 * @return companyList
	 */

	public List<CompanySearchResult> getCompanyList(CompanySearch cse) {
		List<CompanySearchResult> companyList = new ArrayList<>();

		// CompanySearchオブジェクトから検索条件の値を取り出す
		String staffId = cse.getStaffId(); // A'担当者のID
		String jobCategory = cse.getJobCategory(); // 産業大分類のコード
		String companyNameSub = cse.getCompanyName(); // 企業名入力欄に入力された値
		String companyPlaceSub = cse.getCompanyPlace(); // 所在地・最寄り駅の欄に入力された値

		//  企業名入力欄に入力された値をスペースごとに単語に分割、配列に格納
		String[] companyName = StringCommons.splitWords(companyNameSub);
		// 所在地・最寄り駅の欄に入力された値も同様に
		String[] companyPlace = StringCommons.splitWords(companyPlaceSub);

		Transaction transaction = new Transaction();
		CompanyDao dao;
		try {
			// データベース接続を開く
			transaction.open();
			// DBから企業情報を取得し、Dao内のメソッドでListに詰め、そのListを返してもらう
			dao = new CompanyDao(transaction);
			companyList = dao.selectCompanyList(staffId, jobCategory, companyName, companyPlace);

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

		return companyList;
	}

}
