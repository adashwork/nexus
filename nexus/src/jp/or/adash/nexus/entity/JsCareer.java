package jp.or.adash.nexus.entity;

/**
 * 求職者職歴マスタのEntityクラス
 * @author pgjavaAT
 *
 */
public class JsCareer {

	/**
	 * jobseekerid 求職者ID
	* career 職歴
	* nyusyokudt 入職年月
	* taisyokudt 退職年月
	 */
	private String jobseekerid;
	private String career;
	private Integer nyusyokudt;
	private Integer taisyokudt;

	public JsCareer(String jobseekerid, String career, Integer nyusyokudt, Integer taisyokudt) {
		this.jobseekerid = jobseekerid;
		this.career = career;
		this.nyusyokudt = nyusyokudt;
		this.taisyokudt = taisyokudt;
	}

	/**
	 * 求職者IDを返す
	 * @return id
	 */
	public String getJobseekerid() {
		return jobseekerid;
	}

	/**
	 * 職歴を返す
	 * @return career
	 */
	public String getCareer() {
		return career;
	}

	/**
	 * 入職年月を返す
	 * @return nyusyokudt
	 */
	public Integer getNyusyokudt() {
		return nyusyokudt;
	}

	/**
	 * 退職年月を返す
	 * @return taisyokudt
	 */
	public Integer getTaisyokudt() {
		return taisyokudt;
	}

}
