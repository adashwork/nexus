package jp.or.adash.nexus.entity;

public class Company {

	/***
	 * corporateNumber	法人番号
	* companyNo	事業所番号
	* companyName	事業所名
	* companyKana	事業所名（カナ）
	* companyPostal	事業所郵便番号
	* companyPlace	事業所所在地
	* nearStation	最寄駅
	* companyUrl	事業所URL
	* jobCategorySmallCd	産業小分類コード
	* jobCategoryLargeCd	産業大分類コード
	* capital	資本金
	* employees	従業員数
	* establishDt	創業設立年
	* tantouYakushoku	担当者課係名/役職名
	* tantou	担当者名
	* tantouKana	担当者名（かな）
	* tantouTel	担当者TEL
	* tantouFax	担当者FAX
	* tantouEmail	担当者email
	* tantouNote	担当者備考
	* tantouStaffId	担当開拓者ID
	* salesRank	営業評価ランクABC
	* salesNote	営業備考
	 */

	private String companyNo;
	private String corporateNumber;
	private String companyName;
	private String companyKana;
	private String companyPostal;
	private String companyPlace;
	private String nearStation;
	private String companyUrl;
	private String jobCategorySmallCd;
	private String jobCategoryLargeCd;
	private Integer capital;
	private String employees;
	private Integer establishDt;
	private String tantouYakushoku;
	private String tantou;
	private String tantouKana;
	private String tantouTel;
	private String tantouFax;
	private String tantouEmail;
	private String tantouNote;
	private String tantouStaffId;
	private String salesRank;
	private String salesNote;
	public Company(String companyNo, String corporateNumber, String companyName, String companyKana,
			String companyPostal, String companyPlace, String nearStation, String companyUrl, String jobCategorySmallCd,
			String jobCategoryLargeCd, Integer capital, String employees, Integer establishDt, String tantouYakushoku,
			String tantou, String tantouKana, String tantouTel, String tantouFax, String tantouEmail, String tantouNote,
			String tantouStaffId, String salesRank, String salesNote) {

		this.companyNo = companyNo;
		this.corporateNumber = corporateNumber;
		this.companyName = companyName;
		this.companyKana = companyKana;
		this.companyPostal = companyPostal;
		this.companyPlace = companyPlace;
		this.nearStation = nearStation;
		this.companyUrl = companyUrl;
		this.jobCategorySmallCd = jobCategorySmallCd;
		this.jobCategoryLargeCd = jobCategoryLargeCd;
		this.capital = capital;
		this.employees = employees;
		this.establishDt = establishDt;
		this.tantouYakushoku = tantouYakushoku;
		this.tantou = tantou;
		this.tantouKana = tantouKana;
		this.tantouTel = tantouTel;
		this.tantouFax = tantouFax;
		this.tantouEmail = tantouEmail;
		this.tantouNote = tantouNote;
		this.tantouStaffId = tantouStaffId;
		this.salesRank = salesRank;
		this.salesNote = salesNote;
	}


	/**
	 * 事業所番号を取得する
	 * @return companyNo
	 */
	public String getCompanyNo() {
		return companyNo;
	}

	/**
	 * 法人番号を取得する
	 * @return corporateNumber
	 */
	public String getCorporateNumber() {
		return corporateNumber;
	}

	/**
	 * 事業所名を取得する
	 * @return companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * 事業所名（カナ）を取得する
	 * @return companyKana
	 */
	public String getCompanyKana() {
		return companyKana;
	}

	/**
	 * 事業所郵便番号を取得する
	 * @return companyPostal
	 */
	public String getCompanyPostal() {
		return companyPostal;
	}

	/**
	 * 事業所所在地を取得する
	 * @return companyPlace
	 */
	public String getCompanyPlace() {
		return companyPlace;
	}

	/**
	 * 最寄駅を取得する
	 * @return nearStation
	 */
	public String getNearStation() {
		return nearStation;
	}

	/**
	 * 事業所URLを取得する
	 * @return companyUrl
	 */
	public String getCompanyUrl() {
		return companyUrl;
	}

	/**
	 * 産業小分類コードを取得する
	 * @return jobCategorySmallCd
	 */
	public String getJobCategorySmallCd() {
		return jobCategorySmallCd;
	}

	/**
	 * 産業大分類コードを取得する
	 * @return jobCategoryLargeCd
	 */
	public String getJobCategoryLargeCd() {
		return jobCategoryLargeCd;
	}

	/**
	 * 資本金を取得する
	 * @return capital
	 */
	public Integer getCapital() {
		return capital;
	}

	/**
	 * 従業員数を取得する
	 * @return employees
	 */
	public String getEmployees() {
		return employees;
	}

	/**
	 * 創業設立年を取得する
	 * @return establishDt
	 */
	public Integer getEstablishDt() {
		return establishDt;
	}

	/**
	 * 担当者課係名/役職名を取得する
	 * @return tantouYakushoku
	 */
	public String getTantouYakushoku() {
		return tantouYakushoku;
	}

	/**
	 * 担当者名を取得する
	 * @return tantou
	 */
	public String getTantou() {
		return tantou;
	}

	/**
	 * 担当者名（かな）を取得する
	 * @return tantouKana
	 */
	public String getTantouKana() {
		return tantouKana;
	}

	/**
	 * 担当者TELを取得する
	 * @return tantouTel
	 */
	public String getTantouTel() {
		return tantouTel;
	}

	/**
	 * 担当者FAXを取得する
	 * @return tantouFax
	 */
	public String getTantouFax() {
		return tantouFax;
	}

	/**
	 * 担当者emailを取得する
	 * @return tantouEmail
	 */
	public String getTantouEmail() {
		return tantouEmail;
	}

	/**
	 * 担当者備考を取得する
	 * @return tantouNote
	 */
	public String getTantouNote() {
		return tantouNote;
	}

	/**
	 * 担当開拓者IDを取得する
	 * @return tantouStaffId
	 */
	public String getTantouStaffId() {
		return tantouStaffId;
	}

	/**
	 * 営業評価ランクABCを取得する
	 * @return salesRank
	 */
	public String getSalesRank() {
		return salesRank;
	}

	/**
	 * 営業備考を取得する
	 * @return salesNote
	 */
	public String getSalesNote() {
		return salesNote;
	}

}
