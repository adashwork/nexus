package jp.or.adash.nexus.entity;
import java.util.Date;

/**
 * 職業紹介者マスタのEntityクラス
 * @author pgjavaAT
 *
 */
public class Staff {

	/**
	 * id 職業紹介者ID
	 * name 職業紹介者氏名
	 * kana 氏名（カナ）
	 * authority 権限
	 * password パスワード
	 * createdt 新規登録日
	 * createuserid 新規登録ユーザー
	 * updatedt 最終更新日
	 * updateuserid 最終更新ユーザー
	 * deleteflag  削除フラグ
	 */
	private String id;
	private String name;
	private String kana;
	private String authority;
	private String password;
	private Date createdt;
	private String createuserid;
	private Date updatedt;
	private String updateuserid;
	private String deleteflag;

	public Staff(String id, String name, String kana, String authority, String password, Date createdt,
			String createuserid, Date updatedt, String updateuserid, String deleteflag) {
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

	/**
	 *探番idをセットする。
	 *
	 */
	public void setId(Integer id) {
		this.id = String.valueOf(id);
	}

	/**
	 * 職業紹介者IDを返す
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 職業紹介者氏名を返す
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
	 * 権限を返す
	 * @return authority
	 */
	public String getAuthority() {
		return authority;
	}

	/**
	 * パスワードを返す
	 * @return password
	 */
	public String getPassword() {
		return password;
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



	}
