package jp.or.adash.nexus.entity;

import java.util.Date;

/**
 * 求人票マスタのEntityクラス
 * @author pgjavaAT
 *
 */
public class Kyujin {

	/**
	 * no 求人Ｎo.
	* receptiondt 受付年月日（西暦）
	* perioddt 求人有効年月日
	* companyno 事業所番号
	* addresscd 就業場所コード
	* jobsmallcd１ 職種小分類コード１
	* jobsmallcd２ 職種小分類コード２
	* jobsmallcd３ 職種小分類コード３
	* joblargecd１ 職業大分類コード１
	* joblargecd２ 職業大分類コード２
	* joblargecd３ 職業大分類コード３
	* jobcategorysmallcd 産業小分類コード
	* jobcategorylargecd 産業大分類コード
	* jobcategory 産業分類名
	* companykana 事業所名（かな）
	* companyname 事業所名
	* companypostal 事業所郵便番号
	* companyplace 事業所所在地
	* companyurl 事業所URL
	* postal 就業場所郵便番号
	* address 就業場所
	* nearstation 最寄り駅
	* job 職種
	* hakencd 派遣／請負コード
	* detail 仕事の内容
	* koyoukeitaicd 雇用形態コード
	* koyoukikan 雇用期間の定め
	* koyoukikankaishi 雇用期間開始年月日
	* koyoukikanowari 雇用期間終了年月日
	* education 学歴
	* experience 必要な経験等
	* license 必要な免許・資格等
	* agemin 年齢制限・下限
	* agemax 年齢制限・上限
	* salarymin 基本給下限
	* salarymax 基本給上限
	* salaryformcd 賃金形態コード
	* begintime 就業時間・始業
	* endtime 就業時間・終業
	* establishdt 創業設立年
	* capital 資本金
	* companyfeature 会社の特長
	* tantouyakushoku 選考担当者課係名/役職名
	* tantoukana 選考担当者名（カナ）
	* tantou 選考担当者名
	* tantoustaff_id 担当職業紹介者ID
	* applicationform 応募書類
	* background 募集背景
	* hiddensex 性別（求職者に非公開）
	* hiddenagemin 年齢下限（求職者に非公開）
	* hiddenagemax 年齢上限（求職者に非公開）
	* hiddenetc その他非公開情報
	* createdt 新規登録日
	* createuserid 新規登録ユーザー
	* updatedt 最終更新日
	* updateuserid 最終更新ユーザー
	* deleteflag 削除フラグ
	 */
	private String no;
	private Date receptiondt;
	private Date perioddt;
	private String companyno;
	private String addresscd;
	private String jobsmallcd1;
	private String jobsmallcd2;
	private String jobsmallcd3;
	private String joblargecd1;
	private String joblargecd2;
	private String joblargecd3;
	private String jobcategorysmallcd;
	private String jobcategorylargecd;
	private String companykana;
	private String companyname;
	private String companypostal;
	private String companyplace;
	private String companyurl;
	private String postal;
	private String address;
	private String nearstation;
	private String job;
	private String hakencd;
	private String detail;
	private String koyoukeitaicd;
	private String koyoukikan;
	private Date koyoukikankaishi;
	private Date koyoukikanowari;
	private String education;
	private String experience;
	private String license;
	private int agemin;
	private int agemax;
	private int salarymin;
	private int salarymax;
	private String salaryformcd;
	private int begintime;
	private int endtime;
	private int establishdt;
	private long capital;
	private String companyfeature;
	private String tantouyakushoku;
	private String tantoukana;
	private String tantou;
	private String tantoustaff_id;
	private String applicationform;
	private String background;
	private String hiddensex;
	private int hiddenagemin;
	private int hiddenagemax;
	private String hiddenetc;
	private Date createdt;
	private String createuserid;
	private Date upDatedt;
	private String upDateuserid;
	private String deleteflag;

	public Kyujin(String no, Date receptiondt, Date perioddt, String companyno, String addresscd, String jobsmallcd1,
			String jobsmallcd2, String jobsmallcd3, String joblargecd1, String joblargecd2, String joblargecd3,
			String jobcategorysmallcd, String jobcategorylargecd, String companykana,
			String companyname, String companypostal, String companyplace, String companyurl, String postal,
			String address, String nearstation, String job, String hakencd, String detail, String koyoukeitaicd,
			String koyoukikan, Date koyoukikankaishi, Date koyoukikanowari, String education, String experience,
			String license, int agemin, int agemax, int salarymin, int salarymax, String salaryformcd, int begintime,
			int endtime, int establishdt, long capital, String companyfeature, String tantouyakushoku,
			String tantoukana, String tantou, String tantoustaff_id, String applicationform, String background,
			String hiddensex, int hiddenagemin, int hiddenagemax, String hiddenetc, Date createdt,
			String createuserid, Date upDatedt, String upDateuserid, String deleteflag) {
		super();
		this.no = no;
		this.receptiondt = receptiondt;
		this.perioddt = perioddt;
		this.companyno = companyno;
		this.addresscd = addresscd;
		this.jobsmallcd1 = jobsmallcd1;
		this.jobsmallcd2 = jobsmallcd2;
		this.jobsmallcd3 = jobsmallcd3;
		this.joblargecd1 = joblargecd1;
		this.joblargecd2 = joblargecd2;
		this.joblargecd3 = joblargecd3;
		this.jobcategorysmallcd = jobcategorysmallcd;
		this.jobcategorylargecd = jobcategorylargecd;
		this.companykana = companykana;
		this.companyname = companyname;
		this.companypostal = companypostal;
		this.companyplace = companyplace;
		this.companyurl = companyurl;
		this.postal = postal;
		this.address = address;
		this.nearstation = nearstation;
		this.job = job;
		this.hakencd = hakencd;
		this.detail = detail;
		this.koyoukeitaicd = koyoukeitaicd;
		this.koyoukikan = koyoukikan;
		this.koyoukikankaishi = koyoukikankaishi;
		this.koyoukikanowari = koyoukikanowari;
		this.education = education;
		this.experience = experience;
		this.license = license;
		this.agemin = agemin;
		this.agemax = agemax;
		this.salarymin = salarymin;
		this.salarymax = salarymax;
		this.salaryformcd = salaryformcd;
		this.begintime = begintime;
		this.endtime = endtime;
		this.establishdt = establishdt;
		this.capital = capital;
		this.companyfeature = companyfeature;
		this.tantouyakushoku = tantouyakushoku;
		this.tantoukana = tantoukana;
		this.tantou = tantou;
		this.tantoustaff_id = tantoustaff_id;
		this.applicationform = applicationform;
		this.background = background;
		this.hiddensex = hiddensex;
		this.hiddenagemin = hiddenagemin;
		this.hiddenagemax = hiddenagemax;
		this.hiddenetc = hiddenetc;
		this.createdt = createdt;
		this.createuserid = createuserid;
		this.upDatedt = upDatedt;
		this.upDateuserid = upDateuserid;
		this.deleteflag = deleteflag;
	}

	/**
	 * 求人Ｎo.を返す
	 * @return no
	 */
	public String getNo() {
		return no;
	}

	/**
	 * 受付年月日（西暦）を返す
	 * @return receptiondt
	 */
	public Date getReceptiondt() {
		return receptiondt;
	}

	/**
	 * 求人有効年月日を返す
	 * @return perioddt
	 */
	public Date getPerioddt() {
		return perioddt;
	}

	/**
	 * 事業所番号を返す
	 * @return companyno
	 */
	public String getCompanyno() {
		return companyno;
	}

	/**
	 * 就業場所コードを返す
	 * @return addresscd
	 */
	public String getAddresscd() {
		return addresscd;
	}

	/**
	 * 職種小分類コード１を返す
	 * @return jobsmallcd1
	 */
	public String getJobsmallcd1() {
		return jobsmallcd1;
	}

	/**
	 * 職種小分類コード２を返す
	 * @return jobsmallcd2
	 */
	public String getJobsmallcd2() {
		return jobsmallcd2;
	}

	/**
	 * 職種小分類コード３を返す
	 * @return jobsmallcd3
	 */
	public String getJobsmallcd3() {
		return jobsmallcd3;
	}

	/**
	 * 職業大分類コード１を返す
	 * @return joblargecd1
	 */
	public String getJoblargecd1() {
		return joblargecd1;
	}

	/**
	 * 職業大分類コード２を返す
	 * @return joblargecd2
	 */
	public String getJoblargecd2() {
		return joblargecd2;
	}

	/**
	 * 職業大分類コード３を返す
	 * @return joblargecd3
	 */
	public String getJoblargecd3() {
		return joblargecd3;
	}

	/**
	 * 産業小分類コードを返す
	 * @return jobcategorysmallcd
	 */
	public String getJobcategorysmallcd() {
		return jobcategorysmallcd;
	}

	/**
	 * 産業大分類コードを返す
	 * @return jobcategorylargecd
	 */
	public String getJobcategorylargecd() {
		return jobcategorylargecd;
	}

	/**
	 * 事業所名（かな）を返す
	 * @return companykana
	 */
	public String getCompanykana() {
		return companykana;
	}

	/**
	 * 事業所名を返す
	 * @return companyname
	 */
	public String getCompanyname() {
		return companyname;
	}

	/**
	 * 事業所郵便番号を返す
	 * @return companypostal
	 */
	public String getCompanypostal() {
		return companypostal;
	}

	/**
	 * 事業所所在地を返す
	 * @return companyplace
	 */
	public String getCompanyplace() {
		return companyplace;
	}

	/**
	 * 事業所URLを返す
	 * @return companyurl
	 */
	public String getCompanyurl() {
		return companyurl;
	}

	/**
	 * 就業場所郵便番号を返す
	 * @return postal
	 */
	public String getPostal() {
		return postal;
	}

	/**
	 * 就業場所を返す
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 最寄り駅を返す
	 * @return nearstation
	 */
	public String getNearstation() {
		return nearstation;
	}

	/**
	 * 職種を返す
	 * @return job
	 */
	public String getJob() {
		return job;
	}

	/**
	 * 派遣／請負コードを返す
	 * @return hakencd
	 */
	public String getHakencd() {
		return hakencd;
	}

	/**
	 * 仕事の内容を返す
	 * @return detail
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * 雇用形態コードを返す
	 * @return koyoukeitaicd
	 */
	public String getKoyoukeitaicd() {
		return koyoukeitaicd;
	}

	/**
	 * 雇用期間の定めを返す
	 * @return koyoukikan
	 */
	public String getKoyoukikan() {
		return koyoukikan;
	}

	/**
	 * 雇用期間開始年月日を返す
	 * @return koyoukikankaishi
	 */
	public Date getKoyoukikankaishi() {
		return koyoukikankaishi;
	}

	/**
	 * 雇用期間終了年月日を返す
	 * @return koyoukikanowari
	 */
	public Date getKoyoukikanowari() {
		return koyoukikanowari;
	}

	/**
	 * 学歴を返す
	 * @return education
	 */
	public String getEducation() {
		return education;
	}

	/**
	 * 必要な経験等を返す
	 * @return experience
	 */
	public String getExperience() {
		return experience;
	}

	/**
	 * 必要な免許・資格等を返す
	 * @return license
	 */
	public String getLicense() {
		return license;
	}

	/**
	 * 年齢制限・下限を返す
	 * @return agemin
	 */
	public int getAgemin() {
		return agemin;
	}

	/**
	 * 年齢制限・上限を返す
	 * @return agemax
	 */
	public int getAgemax() {
		return agemax;
	}

	/**
	 * 基本給下限を返す
	 * @return salarymin
	 */
	public int getSalarymin() {
		return salarymin;
	}

	/**
	 * 基本給上限を返す
	 * @return salarymax
	 */
	public int getSalarymax() {
		return salarymax;
	}

	/**
	 * 賃金形態コードを返す
	 * @return salaryformcd
	 */
	public String getSalaryformcd() {
		return salaryformcd;
	}

	/**
	 * 就業時間・始業を返す
	 * @return begintime
	 */
	public int getBegintime() {
		return begintime;
	}

	/**
	 * 就業時間・終業を返す
	 * @return endtime
	 */
	public int getEndtime() {
		return endtime;
	}

	/**
	 * 創業設立年を返す
	 * @return establishdt
	 */
	public int getEstablishdt() {
		return establishdt;
	}

	/**
	 * 資本金を返す
	 * @return capital
	 */
	public long getCapital() {
		return capital;
	}

	/**
	 * 会社の特長を返す
	 * @return companyfeature
	 */
	public String getCompanyfeature() {
		return companyfeature;
	}

	/**
	 * 選考担当者課係名/役職名を返す
	 * @return tantouyakushoku
	 */
	public String getTantouyakushoku() {
		return tantouyakushoku;
	}

	/**
	 * 選考担当者名（カナ）を返す
	 * @return tantoukana
	 */
	public String getTantoukana() {
		return tantoukana;
	}

	/**
	 * 選考担当者名を返す
	 * @return tantou
	 */
	public String getTantou() {
		return tantou;
	}

	/**
	 * 担当職業紹介者IDを返す
	 * @return tantoustaff_id
	 */
	public String getTantoustaff_id() {
		return tantoustaff_id;
	}

	/**
	 * 応募書類を返す
	 * @return applicationform
	 */
	public String getApplicationform() {
		return applicationform;
	}

	/**
	 * 募集背景を返す
	 * @return background
	 */
	public String getBackground() {
		return background;
	}

	/**
	 * 性別（求職者に非公開）を返す
	 * @return hiddensex
	 */
	public String getHiddensex() {
		return hiddensex;
	}

	/**
	 * 年齢下限（求職者に非公開）を返す
	 * @return hiddenagemin
	 */
	public int getHiddenagemin() {
		return hiddenagemin;
	}

	/**
	 * 年齢上限（求職者に非公開）を返す
	 * @return hiddenagemax
	 */
	public int getHiddenagemax() {
		return hiddenagemax;
	}

	/**
	 * その他非公開情報を返す
	 * @return hiddenetc
	 */
	public String getHiddenetc() {
		return hiddenetc;
	}

	/**
	 * 新規登録日を返す
	 * @return createdt
	 */
	public Date getCreatedt() {
		return createdt;
	}

	/**
	 * 新規登録ユーザーを返す
	 * @return createuserid
	 */
	public String getCreateuserid() {
		return createuserid;
	}

	/**
	 * 最終更新日を返す
	 * @return upDatedt
	 */
	public Date getUpDatedt() {
		return upDatedt;
	}

	/**
	 * 最終更新ユーザーを返す
	 * @return upDateuserid
	 */
	public String getUpDateuserid() {
		return upDateuserid;
	}

	/**
	 * 削除フラグを返す
	 * @return deleteflag
	 */
	public String getDeleteflag() {
		return deleteflag;
	}

	/**
	 * 求人Ｎo.をセットする
	 * @param no 求人Ｎo.
	 */
	public void setNo(String no) {
		// TODO 自動生成されたメソッド・スタブ
		this.no = no;
	}

}
