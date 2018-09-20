package jp.or.adash.nexus.entity;


/*
 * 簡易型求人表示用求人データオブジェクト
 */
public class SimpleKyujin {

	private String no;
	private String job;
	private String jobcategory;
	private String companyname;
	private String address;
	private String addresscd;
	private String nearstation;
	private String koyoukeitaicd;
	private int agemin;
	private int agemax;
	private int salarymin;
	private int salarymax;

	public SimpleKyujin(String no,String job, String jobcategory,
			String companyname,String address,String addresscd,String nearstation,
			int agemin,int agemax,int salarymin,int salarymax,String koyoukeitaicd
			) {
		this.no=no;
		this.job=job;
		this.jobcategory=jobcategory;
		this.companyname=companyname;
		this.address=address;
		this.addresscd=addresscd;
		this.nearstation=nearstation;
		this.agemin=agemin;
		this.agemax=agemax;
		this.salarymin=salarymin;
		this.salarymax=salarymax;
		this.koyoukeitaicd=koyoukeitaicd;
	}

	public String getNo() {
		return no;
	}
	public String getJob() {
		return job;
	}
	public String getJobcategory() {
		return jobcategory;
	}
	public String getCompanyname() {
		return companyname;
	}
	public String getAddress() {
		return address;
	}
	public String getAddresscd() {
		return addresscd;
	}
	public String getNearstation() {
		return nearstation;
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
