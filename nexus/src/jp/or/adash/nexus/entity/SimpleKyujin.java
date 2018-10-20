package jp.or.adash.nexus.entity;

/*
 * 簡易型求人表示用求人データオブジェクト
 */
public class SimpleKyujin {

	/**
	 * no 求人Ｎo.

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

	* companyname 事業所名

	* address 就業場所
	* nearstation 最寄り駅
	* job 職種

	* detail 仕事の内容
	* agemin 年齢制限・下限
	* agemax 年齢制限・上限
	* salarymin 基本給下限
	* salarymax 基本給上限
	* deleteflag 削除フラグ
	 */
	private String no;

	private String companyno;
	private String jobsmallcd1;
	private String jobsmallcd2;
	private String jobsmallcd3;
	private String joblargecd1;
	private String joblargecd2;
	private String joblargecd3;
	private String jobcategorysmallcd;
	private String jobcategorylargecd;
	private String companyname;
	private String address;
	private String addresscd;
	private String nearstation;
	private String job;
	private String koyoukeitaicd;
	private Integer agemin;
	private Integer agemax;
	private Integer salarymin;
	private Integer salarymax;
	private String deleteflag;

	public SimpleKyujin() {
	}

	public SimpleKyujin(String no, String companyno, String jobsmallcd1, String jobsmallcd2, String jobsmallcd3,
			String joblargecd1, String joblargecd2, String joblargecd3,
			String jobcategorysmallcd, String jobcategorylargecd,
			String companyname, String address, String addresscd, String nearstation,
			String job, String koyoukeitaicd,
			Integer agemin, Integer agemax, Integer salarymin, Integer salarymax, String deleteflag) {
		this.no = no;
		this.companyno = companyno;
		this.jobsmallcd1 = jobsmallcd1;
		this.jobsmallcd2 = jobsmallcd2;
		this.jobsmallcd3 = jobsmallcd3;
		this.joblargecd1 = joblargecd1;
		this.joblargecd2 = joblargecd2;
		this.joblargecd3 = joblargecd3;
		this.jobcategorysmallcd = jobcategorysmallcd;
		this.jobcategorylargecd = jobcategorylargecd;
		this.companyname = companyname;
		this.address = address;
		this.addresscd = addresscd;
		this.nearstation = nearstation;
		this.job = job;
		this.koyoukeitaicd = koyoukeitaicd;
		this.agemin = agemin;
		this.agemax = agemax;
		this.salarymin = salarymin;
		this.salarymax = salarymax;
	}

	public String getDeleteflag() {
		return deleteflag;
	}

	public String getNo() {
		return no;
	}

	public String getAddresscd() {
		return addresscd;
	}

	public String getCompanyno() {
		return companyno;
	}

	public String getJobsmallcd1() {
		return jobsmallcd1;
	}

	public String getJobsmallcd2() {
		return jobsmallcd2;
	}

	public String getJobsmallcd3() {
		return jobsmallcd3;
	}

	public String getJoblargecd1() {
		return joblargecd1;
	}

	public String getJoblargecd2() {
		return joblargecd2;
	}

	public String getJoblargecd3() {
		return joblargecd3;
	}

	public String getJobcategorysmallcd() {
		return jobcategorysmallcd;
	}

	public String getJobcategorylargecd() {
		return jobcategorylargecd;
	}

	public String getCompanyname() {
		return companyname;
	}

	public String getAddress() {
		return address;
	}

	public String getNearstation() {
		return nearstation;
	}

	public String getJob() {
		return job;
	}

	public String getKoyoukeitaicd() {
		return koyoukeitaicd;
	}

	public Integer getAgemin() {
		return agemin;
	}

	public Integer getAgemax() {
		return agemax;
	}

	public Integer getSalarymin() {
		return salarymin;
	}

	public Integer getSalarymax() {
		return salarymax;
	}

}
