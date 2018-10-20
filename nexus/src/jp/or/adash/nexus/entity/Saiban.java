package jp.or.adash.nexus.entity;

/**
 * 採番テーブルのEntityクラス
 * @author aizawa1807
 *
 */
public class Saiban {

	/**
	 * jobseekersaiban 求職者ID採番
     * kyujinsaiban　求人NO採番
     * companysaiban　事業所番号採番
     * staffsaiban 職業紹介者採番
	 *
	 */

	private Integer jobseekersaiban;
	private Integer kyujinsaiban;
	private Integer companysaiban;
	private Integer staffsaiban;
	private Integer matchingsaiban;

	public Saiban(Integer staffsaiban, Integer jobseekersaiban, Integer kyujinsaiban, Integer companysaiban, Integer matchingsaiban) {
		this.staffsaiban =staffsaiban;
		this.jobseekersaiban = jobseekersaiban;
		this.kyujinsaiban = kyujinsaiban;
		this.companysaiban = companysaiban;
		this.matchingsaiban = matchingsaiban;
	}



	/**
	 * 求職者ID採番を返す
	 * @return jobseekersaiban
	 */
	public Integer getJobseekersaiban() {
		return jobseekersaiban;
	}

	/**
	 * 求人NO採番を返す
	 * @return kyujinsaiban
	 */
	public Integer getKyujinsaiban() {
		return kyujinsaiban;
	}

	/**
	 * 事業所番号採番を返す
	 * @return companysaiban
	 */
	public Integer getCompanysaiban() {
		return companysaiban;
	}

	/**
	 *職業紹介者ID採番を返す
	 * @return staffsaiban
	 */
	public Integer getStaffsaiban() {
		return staffsaiban;
	}

	/**
	 * マッチング事例ID採番を返す
	 * @return
	 */
	public Integer getMatchingsaiban() {
		return matchingsaiban;
	}
}
