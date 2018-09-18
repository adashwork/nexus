package jp.or.adash.nexus.entity;


/*
 * 簡易型求人表示用求人データオブジェクト
 */
public class SimpleKyujin {

	private String no;
	private String companyno;
	private String jobsmallcd1;
	private String jobsmallcd2;
	private String jobsmallcd3;
	private String joblargecd1;
	private String joblargecd2;
	private String joblargecd3;
	private String companyname;
	private String adress;
	private String adresscd;
	private String nearstation;
	private String job;
	private String koyoukeitaicd;
	private int agemin;
	private int agemax;
	private int salarymin;
	private int salarymax;

	public SimpleKyujin(String no,String companyno,String jobsmallcd1,String jobsmallcd2,String jobsmallcd3,
			String joblargecd1,String joblargecd2,String joblargecd3,
			String companyname,String adress,String adresscd,String nearstation,
			String job,String koyoukeitaicd,
			int agemin,int agemax,int salarymin,int salarymax
			) {
		this.no=no;
		this.companyno=companyno;
		this.jobsmallcd1=jobsmallcd1;
		this.jobsmallcd2=jobsmallcd2;
		this.jobsmallcd3=jobsmallcd3;
		this.joblargecd1=joblargecd1;
		this.joblargecd2=joblargecd2;
		this.joblargecd3=joblargecd3;
		this.companyname=companyname;
		this.adress=adress;
		this.adresscd=adresscd;
		this.nearstation=nearstation;
		this.job=job;
		this.koyoukeitaicd=koyoukeitaicd;
		this.agemin=agemin;
		this.agemax=agemax;
		this.salarymin=salarymin;
		this.salarymax=salarymax;
	}

	public String getNo() {
		return no;
	}

	public String getAdresscd() {
		return adresscd;
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
	public String getCompanyname() {
		return companyname;
	}
	public String getAdress() {
		return adress;
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
	public int getAgemin() {
		return agemin;
	}
	public int getAgemax() {
		return agemax;
	}
	public int getSalarymin() {
		return salarymin;
	}
	public int getSalarymax() {
		return salarymax;
	}

}
