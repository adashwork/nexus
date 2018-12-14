package jp.or.adash.nexus.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.events.Comment;

import jp.or.adash.nexus.dao.CompanyDao;
import jp.or.adash.nexus.dao.SaibanDao;
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
	 * コンストラクタ
	 */
	public CompanyService() {
		transaction = new Transaction();
		messages = new ArrayList<String>();
	}

	/**
	 * Companyデータのバリデーション
	 * @param company
	 * @return true:数値に異常がなければ  false:異常が１つでもあれば
	 */
	public boolean check(Company company) {
		boolean checkResult = true;

		//共通データチェッククラス
		CommonsService commonsService = new CommonsService();

		//項目ごとにバリデーションを行う
		//事業者番号(PK)
		//からっぽの場合はエラーを出さない。基本的にその場合は独自の事業所番号を割り振るため。
		if ("A".equals(String.valueOf(company.getCompanyNo().charAt(0)))) {
			if (!company.getCompanyNo().matches("^A[0-9]{3}-[0-9]{6}-[0]{1}$")) {
				checkResult = false;
				messages.add("Aから始まる事業所番号のフォーマットが間違っています");
			}
		} else if (!"".equals(company.getCompanyNo())) {
			if (!company.getCompanyNo().matches("^[0-9]{4}-[0-9]{6}-[0]{1}$")) {
				checkResult = false;
				messages.add("事業所番号は0000-000000-0のフォーマットで記入してください");
			}
		}

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
	 * 独自のユニークなCompanyNoを取得する
	 * 頭に「A」を付け加えた13文字の独自companyNoを生成
	 *
	 * @return CompanyNo
	 * @throws IOException
	 */
	public String createUniqueCompanyNo() throws IOException {
		//采番マスタよりデータ取得
		try {
			transaction.open();
			SaibanDao saidao = new SaibanDao(transaction);
			int saiban = saidao.getCompanyInt();
			String saibanString = String.valueOf(saiban);

			//独自の事業所番号を生成
			StringBuffer uniqueCompanyNo = new StringBuffer();
			uniqueCompanyNo.append("A");
			for (int i = saibanString.length(); i < 11; i++) {
				uniqueCompanyNo.append("0");
			}
			uniqueCompanyNo.append(saibanString);
			uniqueCompanyNo.insert(4, "-");
			uniqueCompanyNo.insert(11, "-");

			return uniqueCompanyNo.toString();
		} catch (IOException e) {
			throw new IOException(e);
		} finally {
			// データベース接続をを終了する
			transaction.close();
		}
	}

}
