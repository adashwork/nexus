package jp.or.adash.nexus.entity;
import java.sql.Date;
import java.sql.Timestamp;

public class Kyujin {
	private String no;
	private Date receptiondt;
	private Date perioddt;
	private String companyno;
	private String jobsmallcd1;
	private String jobsmallcd2;
	private String jobsmallcd3;
	private String joblargecd1;
	private String joblargecd2;
	private String joblargecd3;
	private String jobcategorysmallcd;
	private String jobcategorylargecd;
	private String jobcategory;
	private String companykana;
	private String companyname;
	private String companypostal;
	private String companyplace;
	private String companyurl;
	private String postal;
	private String adress;
	private String nearstation;
	private String job;
	private int hakencd;
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
	private int  begintime;
	private int  endtime;
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
	private Timestamp createdt;
	private String createuserid;
	private Timestamp upDatedt;
	private String upDateuserid;
	private String deleteflag;

	public Kyujin(String no, Date receptiondt, Date perioddt, String companyno, String jobsmallcd1, String jobsmallcd2,
			String jobsmallcd3, String joblargecd1, String joblargecd2, String joblargecd3, String jobcategorysmallcd,
			String jobcategorylargecd, String jobcategory, String companykana, String companyname, String companypostal,
			String companyplace, String companyurl, String postal, String adress, String nearstation, String job,
			int hakencd, String detail, String koyoukeitaicd, String koyoukikan, Date koyoukikankaishi,
			Date koyoukikanowari, String education, String experience, String license, int agemin, int agemax,
			int salarymin, int salarymax, String salaryformcd, int begintime, int endtime, int establishdt,
			long capital, String companyfeature, String tantouyakushoku, String tantoukana, String tantou,
			String tantoustaff_id, String applicationform, String background, String hiddensex, int hiddenagemin,
			int hiddenagemax, String hiddenetc, Timestamp createdt, String createuserid, Timestamp upDatedt,
			String upDateuserid, String deleteflag) {
		this.no = no;
		this.receptiondt = receptiondt;
		this.perioddt = perioddt;
		this.companyno = companyno;
		this.jobsmallcd1 = jobsmallcd1;
		this.jobsmallcd2 = jobsmallcd2;
		this.jobsmallcd3 = jobsmallcd3;
		this.joblargecd1 = joblargecd1;
		this.joblargecd2 = joblargecd2;
		this.joblargecd3 = joblargecd3;
		this.jobcategorysmallcd = jobcategorysmallcd;
		this.jobcategorylargecd = jobcategorylargecd;
		this.jobcategory = jobcategory;
		this.companykana = companykana;
		this.companyname = companyname;
		this.companypostal = companypostal;
		this.companyplace = companyplace;
		this.companyurl = companyurl;
		this.postal = postal;
		this.adress = adress;
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

	public String getNo() {
		return no;
	}

	public Date getReceptiondt() {
		return receptiondt;
	}

	public Date getPerioddt() {
		return perioddt;
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

	public String getJobcategory() {
		return jobcategory;
	}

	public String getCompanykana() {
		return companykana;
	}

	public String getCompanyname() {
		return companyname;
	}

	public String getCompanypostal() {
		return companypostal;
	}

	public String getCompanyplace() {
		return companyplace;
	}

	public String getCompanyurl() {
		return companyurl;
	}

	public String getPostal() {
		return postal;
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

	public int getHakencd() {
		return hakencd;
	}

	public String getDetail() {
		return detail;
	}

	public String getKoyoukeitaicd() {
		return koyoukeitaicd;
	}

	public String getKoyoukikan() {
		return koyoukikan;
	}

	public Date getKoyoukikankaishi() {
		return koyoukikankaishi;
	}

	public Date getKoyoukikanowari() {
		return koyoukikanowari;
	}

	public String getEducation() {
		return education;
	}

	public String getExperience() {
		return experience;
	}

	public String getLicense() {
		return license;
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

	public String getSalaryformcd() {
		return salaryformcd;
	}

	public int getBegintime() {
		return begintime;
	}

	public int getEndtime() {
		return endtime;
	}

	public int getEstablishdt() {
		return establishdt;
	}

	public long getCapital() {
		return capital;
	}

	public String getCompanyfeature() {
		return companyfeature;
	}

	public String getTantouyakushoku() {
		return tantouyakushoku;
	}

	public String getTantoukana() {
		return tantoukana;
	}

	public String getTantou() {
		return tantou;
	}

	public String getTantoustaff_id() {
		return tantoustaff_id;
	}

	public String getApplicationform() {
		return applicationform;
	}

	public String getBackground() {
		return background;
	}

	public String getHiddensex() {
		return hiddensex;
	}

	public int getHiddenagemin() {
		return hiddenagemin;
	}

	public int getHiddenagemax() {
		return hiddenagemax;
	}

	public String getHiddenetc() {
		return hiddenetc;
	}

	public Timestamp getCreatedt() {
		return createdt;
	}

	public String getCreateuserid() {
		return createuserid;
	}

	public Timestamp getUpDatedt() {
		return upDatedt;
	}

	public String getUpDateuserid() {
		return upDateuserid;
	}

	public String getDeleteflag() {
		return deleteflag;
	}



}
