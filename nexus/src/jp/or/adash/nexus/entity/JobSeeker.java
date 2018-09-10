package jp.or.adash.nexus.entity;
import java.sql.Date;
import java.sql.Timestamp;

public class JobSeeker {
	private String id;
	private String name;
	private String kana;
	private Date birthdt;
	private String sex;
	private int age;
	private String postal;
	private String address;
	private String nearstation;
	private String phone;
	private String mobile;
	private String partner;
	private int huyou;
	private String education;
	private String hopejob1;
	private String hopejob2;
	private String hopejob3;
	private String hopejobcategory;
	private String hopeworkplace;
	private String hopekoyoukeitai;
	private int hopeworkingDate;
	private int hopebegintime;
	private int hopeendtime;
	private int hopesalary;
	private int hopejikyu;
	private String hopeetc;
	private String driverlicense;
	private String pasokonskill;
	private String caution;
	private String tantoustaffid;
	private String password;
	private Timestamp createdt;
	private String createuserid;
	private Timestamp upDatedt;
	private String upDateuserid;
	private String deleteflag;

	public JobSeeker(String id, String name, String kana, Date birthdt, String sex, int age, String postal,
			String address, String nearstation, String phone, String mobile, String partner, int huyou,
			String education, String hopejob1, String hopejob2, String hopejob3, String hopejobcategory,
			String hopeworkplace, String hopekoyoukeitai, int hopeworkingDate, int hopebegintime, int hopeendtime,
			int hopesalary, int hopejikyu, String hopeetc, String driverlicense, String pasokonskill, String caution,
			String tantoustaffid, String password, Timestamp createdt, String createuserid, Timestamp upDatedt,
			String upDateuserid, String deleteflag) {
		this.id = id;
		this.name = name;
		this.kana = kana;
		this.birthdt = birthdt;
		this.sex = sex;
		this.age = age;
		this.postal = postal;
		this.address = address;
		this.nearstation = nearstation;
		this.phone = phone;
		this.mobile = mobile;
		this.partner = partner;
		this.huyou = huyou;
		this.education = education;
		this.hopejob1 = hopejob1;
		this.hopejob2 = hopejob2;
		this.hopejob3 = hopejob3;
		this.hopejobcategory = hopejobcategory;
		this.hopeworkplace = hopeworkplace;
		this.hopekoyoukeitai = hopekoyoukeitai;
		this.hopeworkingDate = hopeworkingDate;
		this.hopebegintime = hopebegintime;
		this.hopeendtime = hopeendtime;
		this.hopesalary = hopesalary;
		this.hopejikyu = hopejikyu;
		this.hopeetc = hopeetc;
		this.driverlicense = driverlicense;
		this.pasokonskill = pasokonskill;
		this.caution = caution;
		this.tantoustaffid = tantoustaffid;
		this.password = password;
		this.createdt = createdt;
		this.createuserid = createuserid;
		this.upDatedt = upDatedt;
		this.upDateuserid = upDateuserid;
		this.deleteflag = deleteflag;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getKana() {
		return kana;
	}

	public Date getBirthdt() {
		return birthdt;
	}

	public String getSex() {
		return sex;
	}

	public int getAge() {
		return age;
	}

	public String getPostal() {
		return postal;
	}

	public String getAddress() {
		return address;
	}

	public String getNearstation() {
		return nearstation;
	}

	public String getPhone() {
		return phone;
	}

	public String getMobile() {
		return mobile;
	}

	public String getPartner() {
		return partner;
	}

	public int getHuyou() {
		return huyou;
	}

	public String getEducation() {
		return education;
	}

	public String getHopejob1() {
		return hopejob1;
	}

	public String getHopejob2() {
		return hopejob2;
	}

	public String getHopejob3() {
		return hopejob3;
	}

	public String getHopejobcategory() {
		return hopejobcategory;
	}

	public String getHopeworkplace() {
		return hopeworkplace;
	}

	public String getHopekoyoukeitai() {
		return hopekoyoukeitai;
	}

	public int getHopeworkingDate() {
		return hopeworkingDate;
	}

	public int getHopebegintime() {
		return hopebegintime;
	}

	public int getHopeendtime() {
		return hopeendtime;
	}

	public int getHopesalary() {
		return hopesalary;
	}

	public int getHopejikyu() {
		return hopejikyu;
	}

	public String getHopeetc() {
		return hopeetc;
	}

	public String getDriverlicense() {
		return driverlicense;
	}

	public String getPasokonskill() {
		return pasokonskill;
	}

	public String getCaution() {
		return caution;
	}

	public String getTantoustaffid() {
		return tantoustaffid;
	}

	public String getPassword() {
		return password;
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
