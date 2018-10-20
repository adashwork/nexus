package jp.or.adash.nexus.entity;

/**
 * 求職者特性マスタのEntityクラス
 * @author pgjavaAT
 *
 */
public class JsCharacter {

	/**
	 * jobseekerid 求職者ID
	* characterid 特性ID
	 */
	private char jobseekerid;
	private Integer characterid;

	public JsCharacter(char jobseekerid, Integer characterid) {
		this.jobseekerid = jobseekerid;
		this.characterid = characterid;
	}

	/**
	 * 求職者IDを返す
	 * @return jobseekerid
	 */
	public char getJobseekerid() {
		return jobseekerid;
	}

	/**
	 * 特性IDを返す
	 * @return characterid
	 */
	public Integer getCharacterid() {
		return characterid;
	}

}
