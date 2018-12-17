package jp.or.adash.nexus.entity;
/**
 * 企業情報を検索する。検索項目のエンティティクラス
 * @author mosco
 *
 */
public class CompanySearch {


	// 企業名：フリーワード
	private String companyName;
	// 担当開拓者ID：プルダウン表示
	private String staffId;
	// 業種：プルダウン
	private String jobCategory;
	// 所在地・最寄り駅：フリーワード
	private String companyPlace;


	/**
	 * for mosco
	 * ServletでCompanySearchEntityオブジェクトを作成、引数に４つのパラメータをわたし、CSE内でコンストラクタに値を入力
	 *
	 */

	/**
	 * コンストラクタ
	 *
	 * @param companyName 企業名
	 * @param staffId 担当開拓者ID
	 * @param jobCategory 業種
	 * @param companyPlace 所在地・最寄り駅
	 */

	public CompanySearch(String companyName, String staffId, String jobCategory, String companyPlace) {
		this.companyName = companyName;
		this.staffId = staffId;
		this.jobCategory = jobCategory;
		this.companyPlace = companyPlace;
	}

	/**
	 * for mosco
	 * 以下は、Daoにて、SQLで検索する際に、検索欄に入力されたワード、値を取得するためのメソッド
	 */



	/**
	 * 検索欄に入力された企業名を取得する
	 * @return companyName
	 */

	public String getCompanyName() {
		return companyName;
	}


	/**
	 * 選択された開拓員のIDを取得する
	 * @return staffId
	 */

	public String getStaffId() {
		return staffId;
	}

	/**
	 * 選択された産業分類を取得する
	 * @return jobCategory
	 */

	public String getJobCategory() {
		return jobCategory;
	}

	/**
	 * 検索欄に入力された所在地・最寄り駅を取得する
	 * @return companyPlace
	 */

	public String getCompanyPlace() {
		return companyPlace;
	}



}
