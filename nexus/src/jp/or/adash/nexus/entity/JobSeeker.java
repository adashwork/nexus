package jp.or.adash.nexus.entity;

import java.util.Date;

/**
 * 求職者マスタのEntityクラス
 * @author pgjavaAT
 *
 */
public class JobSeeker {
	/**
	 * id 求職者ID
	* name 求職者氏名
	* kana 氏名（カナ）
	* birthdt 生年月日
	* sex 性別
	//* age 年齢
	* zip21 自宅郵便番号
	* addr21 自宅住所
	* seekermail メールアドレス
	* nearstation 最寄り駅
	* phone 自宅TEL
	* mobile 携帯TEL
	* partner 配偶者
	* huyou 扶養家族
	* education 学歴
	* HOPEJOB1 希望職種１
	* HOPEJOB2 希望職種２
	* HOPEJOB3 希望職種３
	* HOPEJOBCATEGORY 希望業種
	* HOPEJOBCATEGORY2 希望業種2
	* HOPEJOBCATEGORY3 希望業種3
	* hopeworkplace 希望勤務地
	* hopekoyoukeitai 希望雇用形態
	* hopeweekday 希望勤務曜日
	* hopeworkingdate 希望勤務日時
	* hopebegintime 希望勤務時間（開始）
	* hopeendtime 希望勤務時間（終了）
	* hopesalary 希望月給
	* hopejikyu 希望時間給
	* hopeetc その他希望
	* driverlicense 自動車免許
	* licenseetc その他免許
	* pasokonskill パソコンスキル
	* caution 留意点
	* tantoustaffname 担当職業紹介者名
	* tantoustaffid 担当職業紹介者ID
	* password パスワード
	* createdt 新規登録日
	* createuserid 新規登録ユーザー
	* updatedt 最終更新日
	* updateuserid 最終更新ユーザー
	* deleteflag 削除フラグ
	 */
	private String id;
	private String name;
	private String kana;
	private Date birthdt;
	private String sex;
//	private Integer age;
	private String zip21;
	private String addr21;
	private String seekermail; //最新更新箇所
	private String nearstation;
	private String phone;
	private String mobile;
	private String partner;
	private Integer huyou;
	private String education;
	private String career;//最新更新箇所
	private String HOPEJOB1;//最新更新箇所
	private String HOPEJOB2;//最新更新箇所
	private String HOPEJOB3;//最新更新箇所
	private String HOPEJOBCATEGORY;
	private String HOPEJOBCATEGORY2;//最新更新箇所
	private String HOPEJOBCATEGORY3;//最新更新箇所
	private String hopeworkplace;
	private String hopekoyoukeitai;
	private String hopeweekday;//最新更新箇所
	private Integer hopeworkingdate;//最新更新箇所
	private Integer hopebegintime;
	private Integer hopeendtime;
	private Integer hopesalary;
	private Integer hopejikyu;
	private String hopeetc;
	private String driverlicense;
	private String licenseetc;
	private String pasokonskill;
	private String caution;
//	private String tantoustaffname;
	private String tantoustaffid;
	private String password;
	private String note;//最新更新箇所
	private Date createdt;
	private String createuserid;
	private Date updatedt;//最新更新箇所
	private String updateuserid;//最新更新箇所
	private String deleteflag;

	public JobSeeker(String id, String name, String kana, Date birthdt, String sex,  String zip21,
			String addr21, String seekermail,String nearstation, String phone, String mobile, String partner, Integer huyou,
			String education,String career, String HOPEJOB1, String HOPEJOB2, String HOPEJOB3, String HOPEJOBCATEGORY, String HOPEJOBCATEGORY2, String HOPEJOBCATEGORY3,
			String hopeworkplace, String hopekoyoukeitai, String hopeweekday, Integer hopeworkingdate, Integer hopebegintime, Integer hopeendtime,
			Integer hopesalary, Integer hopejikyu, String hopeetc, String driverlicense, String licenseetc, String pasokonskill,
			String caution,  String tantoustaffid, String password, String note, Date createdt, String createuserid,
			Date updatedt, String updateuserid, String deleteflag) {

		this.id = id;
		this.name = name;
		this.kana = kana;
		this.birthdt = birthdt;
		this.sex = sex;
//		this.age = age;
		this.zip21 = zip21;
		this.addr21 = addr21;
		this.seekermail = seekermail;
		this.nearstation = nearstation;
		this.phone = phone;
		this.mobile = mobile;
		this.partner = partner;
		this.huyou = huyou;
		this.education = education;
		this.career = career;
		this.HOPEJOB1 = HOPEJOB1;
		this.HOPEJOB2 = HOPEJOB2;
		this.HOPEJOB3 = HOPEJOB3;
		this.HOPEJOBCATEGORY = HOPEJOBCATEGORY;
		this.HOPEJOBCATEGORY2 = HOPEJOBCATEGORY2;
		this.HOPEJOBCATEGORY3 = HOPEJOBCATEGORY3;
		this.hopeweekday = hopeweekday;
		this.hopeworkplace = hopeworkplace;
		this.hopekoyoukeitai = hopekoyoukeitai;
		this.hopeworkingdate = hopeworkingdate;
		this.hopebegintime = hopebegintime;
		this.hopeendtime = hopeendtime;
		this.hopesalary = hopesalary;
		this.hopejikyu = hopejikyu;
		this.hopeetc = hopeetc;
		this.driverlicense = driverlicense;
		this.licenseetc = licenseetc;
		this.pasokonskill = pasokonskill;
		this.caution = caution;
//		this.tantoustaffname = tantoustaffname;
		this.tantoustaffid = tantoustaffid;
		this.password = password;
		this.note = note;
		this.createdt = createdt;
		this.createuserid = createuserid;
		this.updatedt = updatedt;
		this.updateuserid = updateuserid;
		this.deleteflag = deleteflag;
	}

	/**
	 * 求職者IDを返す
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 求職者氏名を返す
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 氏名（カナ）を返す
	 * @return kana
	 */
	public String getKana() {
		return kana;
	}

	/**
	 * 生年月日を返す
	 * @return birthdt
	 */
	public Date getBirthdt() {
		return birthdt;
	}

	/**
	 * 性別を返す
	 * @return sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * 年齢を返す
	 * @return age

	public Integer getAge() {
		return age;
	}
	 */
	/**
	 * 自宅郵便番号を返す
	 * @return postal
	 */
	public String getzip21() {
		return zip21;
	}

	/**
	 * 自宅住所を返す
	 * @return address
	 */
	public String getAddr21() {
		return addr21;
	}

	/**
	 * メールアドレスを返す
	 * @return seekermail
	 */
	public String getSeekermail() {
		return seekermail;
	}

	/**
	 * 最寄り駅を返す
	 * @return nearstation
	 */
	public String getNearstation() {
		return nearstation;
	}

	/**
	 * 自宅TELを返す
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 携帯TELを返す
	 * @return mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * 配偶者を返す
	 * @return partner
	 */
	public String getPartner() {
		return partner;
	}

	/**
	 * 扶養家族を返す
	 * @return huyou
	 */
	public Integer getHuyou() {
		return huyou;
	}

	/**
	 * 学歴を返す
	 * @return education
	 */
	public String getEducation() {
		return education;
	}

	/**
	 * 職歴・経歴を返す
	 * @return career
	 */
	public String getCareer() {
		return career;
	}

	/**
	 * 希望職種１を返す
	 * @return HOPEJOB1
	 */
	public String getHOPEJOB1() {
		return HOPEJOB1;
	}

	/**
	 * 希望職種２を返す
	 * @return HOPEJOB2
	 */
	public String getHOPEJOB2() {
		return HOPEJOB2;
	}

	/**
	 * 希望職種３を返す
	 * @return HOPEJOB3
	 */
	public String getHOPEJOB3() {
		return HOPEJOB3;
	}

	/**
	 * 希望業種1を返す
	 * @return HOPEJOBCATEGORY
	 */
	public String getHOPEJOBCATEGORY() {
		return HOPEJOBCATEGORY;
	}

	/**
	 * 希望業種2を返す
	 * @return HOPEJOBCATEGORY2
	 */
	public String getHOPEJOBCATEGORY2() {
		return HOPEJOBCATEGORY2;
	}

	/**
	 * 希望業種3を返す
	 * @return HOPEJOBCATEGORY3
	 */
	public String getHOPEJOBCATEGORY3() {
		return HOPEJOBCATEGORY3;
	}


	/**
	 * 希望勤務地を返す
	 * @return hopeworkplace
	 */
	public String getHopeworkplace() {
		return hopeworkplace;
	}

	/**
	 * 希望雇用形態を返す
	 * @return hopekoyoukeitai
	 */
	public String getHopekoyoukeitai() {
		return hopekoyoukeitai;
	}

	/**
	 * 希望勤務曜日を返す
	 * @return hopeweekday
	 */
	public String getHopeweekday() {
		return hopeweekday;
	}

	/**
	 * 希望勤務日数を返す
	 * @return hopeworkingdate
	 */
	public Integer getHopeworkingdate() {
		return hopeworkingdate;
	}

	/**
	 * 希望勤務時間（開始）を返す
	 * @return hopebegintime
	 */
	public Integer getHopebegintime() {
		return hopebegintime;
	}

	/**
	 * 希望勤務時間（終了）を返す
	 * @return hopeendtime
	 */
	public Integer getHopeendtime() {
		return hopeendtime;
	}

	/**
	 * 希望月給を返す
	 * @return hopesalary
	 */
	public Integer getHopesalary() {
		return hopesalary;
	}

	/**
	 * 希望時間給を返す
	 * @return hopejikyu
	 */
	public Integer getHopejikyu() {
		return hopejikyu;
	}

	/**
	 * その他希望を返す
	 * @return hopeetc
	 */
	public String getHopeetc() {
		return hopeetc;
	}

	/**
	 * 自動車免許を返す
	 * @return driverlicense
	 */
	public String getDriverlicense() {
		return driverlicense;
	}

	/**
	 * その他免許を返す
	 * @return licenseetc
	 */
	public String getLicenseetc() {
		return licenseetc;
	}

	/**
	 * パソコンスキルを返す
	 * @return pasokonskill
	 */
	public String getPasokonskill() {
		return pasokonskill;
	}

	/**
	 * 留意点を返す
	 * @return caution
	 */
	public String getCaution() {
		return caution;
	}

	/**
	 * 担当職業紹介者名を返す
	 * @return tantoustaffname
	 */
	/*public String getTantoustaffname() {
		return tantoustaffname;
	}*/

	/**
	 * 担当職業紹介者IDを返す
	 * @return tantoustaffid
	 */
	public String getTantoustaffid() {
		return tantoustaffid;
	}

	/**
	 * パスワードを返す
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 補足を返す
	 * @return note
	 */
	public String getNote() {
		return note;
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
	public String getdeleteflag() {
		return deleteflag;
	}

	/**
	 * 求職者IDをセットする
	 * @param id id
	 */
	public void setId(String id) {
		this.id = id;
	}
}
