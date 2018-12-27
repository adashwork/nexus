package jp.or.adash.nexus.entity;

import java.util.Date;

/**
/* * 求人票マスタのEntityクラス
 * @author pgjavaAT
 * @author kmiyamoto
 *
 */

public class Kyujin {
	/*
	**
	* 求人情報kyujinテーブル
	*
	* no 求人Ｎo.
	* companyno 事業所番号
	* postal 就業場所郵便番号
	* address 就業場所
	* nearline 沿線
	* nearstationkj 最寄り駅
	* addresscd 就業場所コード
	* jobsmallcd１ 職種小分類コード１
	* jobsmallcd２ 職種小分類コード２
	* jobsmallcd３ 職種小分類コード３
	* joblargecd１ 職業大分類コード１
	* joblargecd２ 職業大分類コード２
	* joblargecd３ 職業大分類コード３
	* job 職種
	* detail 仕事の内容
	* koyoukeitaicd 雇用形態コード
	* hakencd 派遣／請負コード
	* koyoukikan 雇用期間の定め
	* koyoukikankaishi 雇用期間開始年月日
	* koyoukikanowari 雇用期間終了年月日
	* education 学歴
	* experience 必要な経験等
	* license 必要な免許・資格等
	* agemin 年齢制限・下限
	* agemax 年齢制限・上限
	* salaryformcd 賃金形態コード
	* salarymin 基本給下限
	* salarymax 基本給上限
	* bonus 賞与
	* koutuhi 通勤手当
	* teate	他諸手当
	* begintime 就業時間・始業
	* endtime 就業時間・終業
	* shift シフト制
	* flex フレックスタイム
	* jitan 時短勤務
	* jikangai 時間外平均
	* siyoukikan 試用期間
	* workdays 週所定労働日数
	* nenkanholiday	年間休日
	* applicationform 応募書類
	* background 募集背景
	* bosyunumbers 募集人数
	* hiddensex 性別（求職者に非公開）
	* hiddenagemin 年齢下限（求職者に非公開）
	* hiddenagemax 年齢上限（求職者に非公開）
	* hiddenetc その他非公開情報
	* receptiondt 受付年月日（西暦）
	* perioddt 紹介期限日
	* createdt 新規登録日
	* createuserid 新規登録ユーザー
	* updatedt 最終更新日
	* updateuserid 最終更新ユーザー
	* deleteflag 削除フラグ
	 */

	private String no;
	private String companyno;
	private String postal;
	private String address;
	private String nearline;
	private String nearstationkj;
	private String addresscd;
	private String jobsmallcd1;
	private String jobsmallcd2;
	private String jobsmallcd3;
	private String joblargecd1;
	private String joblargecd2;
	private String joblargecd3;
	private String job;
	private String detail;
	private String koyoukeitaicd;
	private String hakencd;
	private String koyoukikan;
	private Date koyoukikankaishi;
	private Date koyoukikanowari;
	private String education;
	private String experience;
	private String license;
	private Integer agemin;
	private Integer agemax;
	private String salaryformcd;
	private Integer salarymin;
	private Integer salarymax;
	private String bonus;
	private String koutuhi;
	private String teate;
	private Integer begintime;
	private Integer endtime;
	private String shift;
	private String flex;
	private String jitan;
	private Integer jikangai;
	private Integer siyoukikan;
	private Integer workdays;
	private String nenkanholiday;
	private String applicationform;
	private String background;
	private String bosyunumbers;
	private String hiddensex;
	private Integer hiddenagemin;
	private Integer hiddenagemax;
	private String hiddenetc;
	private Date receptiondt;
	private Date perioddt;
	private Date createdt;
	private String createuserid;
	private Date updatedt;
	private String updateuserid;
	private String deleteflag;

	public Kyujin() {

	}

	public Kyujin(String no, String companyno, String postal, String address,
			String nearline, String nearstationkj, String addresscd, String jobsmallcd1, String jobsmallcd2,
			String jobsmallcd3, String joblargecd1, String joblargecd2, String joblargecd3,
			String job, String detail, String koyoukeitaicd, String hakencd, String koyoukikan,
			Date koyoukikankaishi, Date koyoukikanowari, String education, String experience, String license,
			Integer agemin, Integer agemax, String salaryformcd, Integer salarymin, Integer salarymax,
			String bonus, String koutuhi, String teate, Integer begintime, Integer endtime, String shift,
			String flex, String jitan, Integer jikangai, Integer siyoukikan, Integer workdays, String nenkanholiday,
			String applicationform, String background, String bosyunumbers, String hiddensex,
			Integer hiddenagemin, Integer hiddenagemax, String hiddenetc, Date receptiondt, Date perioddt,
			Date createdt, String createuserid, Date updatedt, String updateuserid, String deleteflag) {
		super();
		this.no = no;
		this.companyno = companyno;
		this.postal = postal;
		this.address = address;
		this.nearline = nearline;
		this.nearstationkj = nearstationkj;
		this.addresscd = addresscd;
		this.jobsmallcd1 = jobsmallcd1;
		this.jobsmallcd2 = jobsmallcd2;
		this.jobsmallcd3 = jobsmallcd3;
		this.joblargecd1 = joblargecd1;
		this.joblargecd2 = joblargecd2;
		this.joblargecd3 = joblargecd3;
		this.job = job;
		this.detail = detail;
		this.koyoukeitaicd = koyoukeitaicd;
		this.hakencd = hakencd;
		this.koyoukikan = koyoukikan;
		this.koyoukikankaishi = koyoukikankaishi;
		this.koyoukikanowari = koyoukikanowari;
		this.education = education;
		this.experience = experience;
		this.license = license;
		this.agemin = agemin;
		this.agemax = agemax;
		this.salaryformcd = salaryformcd;
		this.salarymin = salarymin;
		this.salarymax = salarymax;
		this.bonus = bonus;
		this.koutuhi = koutuhi;
		this.teate = teate;
		this.begintime = begintime;
		this.endtime = endtime;
		this.shift = shift;
		this.flex = flex;
		this.jitan = jitan;
		this.jikangai = jikangai;
		this.siyoukikan = siyoukikan;
		this.workdays = workdays;
		this.nenkanholiday = nenkanholiday;
		this.applicationform = applicationform;
		this.background = background;
		this.bosyunumbers = bosyunumbers;
		this.hiddensex = hiddensex;
		this.hiddenagemin = hiddenagemin;
		this.hiddenagemax = hiddenagemax;
		this.hiddenetc = hiddenetc;
		this.receptiondt = receptiondt;
		this.perioddt = perioddt;
		this.createdt = createdt;
		this.createuserid = createuserid;
		this.updatedt = updatedt;
		this.updateuserid = updateuserid;
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
	 * 事業所番号を返す
	 * @return companyno
	 */
	public String getCompanyno() {
		return companyno;
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
	 * 沿線を返す
	 * @return nearline
	 */
	public String getNearline() {
		return nearline;
	}

	/**
	 * 最寄り駅を返す
	 * @return nearstationkj
	 */
	public String getNearstationKyujin() {
		return nearstationkj;
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
	 * 職種を返す
	 * @return job
	 */
	public String getJob() {
		return job;
	}

	/**
	 * 産業小分類コードを返す
	 * @return jobcategorysmallcd

	public String getJobcategorysmallcd() {
		return jobcategorysmallcd;
	} */
	/**
	 * 産業大分類コードを返す
	 * @return jobcategorylargecd

	public String getJobcategorylargecd() {
		return jobcategorylargecd;
	}*/

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
	 * 派遣／請負コードを返す
	 * @return hakencd
	 */
	public String getHakencd() {
		return hakencd;
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
	public Integer getAgemin() {
		return agemin;
	}

	/**
	 * 年齢制限・上限を返す
	 * @return agemax
	 */
	public Integer getAgemax() {
		return agemax;
	}

	/**
	 * 賃金形態コードを返す
	 * @return salaryformcd
	 */
	public String getSalaryformcd() {
		return salaryformcd;
	}

	/**
	 * 基本給下限を返す
	 * @return salarymin
	 */
	public Integer getSalarymin() {
		return salarymin;
	}

	/**
	 * 基本給上限を返す
	 * @return salarymax
	 */
	public Integer getSalarymax() {
		return salarymax;
	}

	/**
	 * 賞与に関することを返す
	 * @return bonus
	 */
	public String getBonus() {
		return bonus;
	}

	/**
	 * 通勤手当を返す
	 * @return koutuhi
	 */
	public String getKoutuhi() {
		return koutuhi;
	}

	/**
	 * 諸手当を返す
	 * @return teate
	 */
	public String getTeate() {
		return teate;
	}

	/**
	 * 就業時間・始業を返す
	 * @return begintime
	 */
	public Integer getBegintime() {
		return begintime;
	}

	/**
	 * 就業時間・終業を返す
	 * @return endtime
	 */
	public Integer getEndtime() {
		return endtime;
	}

	/**
	 * シフト制かどうかとその詳細を返す
	 * @return shift
	 */
	public String getShift() {
		return shift;
	}

	/**
	 * フレックス制かどうかとその詳細を返す
	 * @return flex
	 */
	public String getFlex() {
		return flex;
	}

	/**
	 * 時短勤務対応かどうかを返す
	 * @return jitan
	 */
	public String getJitan() {
		return jitan;
	}

	/**
	 * 時間外勤務平均を返す
	 * @return jikangai
	 */
	public Integer getJikangai() {
		return jikangai;
	}

	/**
	 * 試用期間0～6ヶ月を返す
	 * @return siyoukikan
	 */
	public Integer getSiyoukikan() {
		return siyoukikan;
	}

	/**
	 * 週所定労働日数を返す
	 * @return workdays
	 */
	public Integer getWorkdays() {
		return workdays;
	}

	/**
	 * 年間休日日数を返す
	 * @return nenkanholiday
	 */
	public String getNenkanholiday() {
		return nenkanholiday;
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
	 * 募集人数を返す
	 * @return bosyunumbers
	 */
	public String getBosyunumbers() {
		return bosyunumbers;
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
	public Integer getHiddenagemin() {
		return hiddenagemin;
	}

	/**
	 * 年齢上限（求職者に非公開）を返す
	 * @return hiddenagemax
	 */
	public Integer getHiddenagemax() {
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
	 * 受付年月日（西暦）を返す
	 * @return receptiondt
	 */
	public Date getReceptiondt() {
		return receptiondt;
	}

	/**
	 * 紹介期限日を返す
	 * @return perioddt
	 */
	public Date getPerioddt() {
		return perioddt;
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
	 * @return updatedt
	 */
	public Date getUpdatedt() {
		return updatedt;
	}

	/**
	 * 最終更新ユーザーを返す
	 * @return updateuserid
	 */
	public String getUpdateuserid() {
		return updateuserid;
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
		this.no = no;
	}

}
