package jp.or.adash.nexus.entity;
import java.sql.Timestamp;

public class Staff {
	private String id;
	private String name;
	private String kana;
	private String authority;
	private String password;
	private Timestamp createdt;
	private String createuserid;
	private Timestamp updatedt;
	private String updateuserid;
	private String deleteflag;

	public Staff(String id, String name, String kana, String authority, String password, Timestamp createdt,
			String createuserid, Timestamp updatedt, String updateuserid, String deleteflag) {
		this.id = id;
		this.name = name;
		this.kana = kana;
		this.authority = authority;
		this.password = password;
		this.createdt = createdt;
		this.createuserid = createuserid;
		this.updatedt = updatedt;
		this.updateuserid = updateuserid;
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

	public String getAuthority() {
		return authority;
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

	public Timestamp getUpdatedt() {
		return updatedt;
	}

	public String getUpdateuserid() {
		return updateuserid;
	}

	public String getDeleteflag() {
		return deleteflag;
	}


	}
