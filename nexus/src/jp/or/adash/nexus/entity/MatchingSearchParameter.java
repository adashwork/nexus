package jp.or.adash.nexus.entity;

/**
 * マッチング事例検索条件のエンティティ
 * @author kitayama
 *
 */
public class MatchingSearchParameter {

	// 2018/12/17 kitayama wordのデータ型をListからString配列に変更
	/**
	 * matchingid	マッチング事例ID
	 * companyNo 	事業所番号
	 * jobseekerid	求職者ID
	 * staffid		職業紹介者ID
	 * word			フリーワード（複数持つことがある）
	 */
	//TODO 変数名とデータ型は要確認
	private Integer matchingid;
	private String companyNo;
	private String jobseekerid;
	private String staffid;
	private String[] word;

	public MatchingSearchParameter(Integer matchingid
								 , String companyNo
								 , String jobseekerid
								 , String staffid
								 , String[] word) {
		this.matchingid = matchingid;
		this.companyNo = companyNo;
		this.jobseekerid = jobseekerid;
		this.staffid = staffid;
		this.word = word;
	}

	/**
	 * @return matchingid
	 */
	public Integer getMatchingid() {
		return matchingid;
	}

	/**
	 * @return companyId
	 */
	public String getCompanyId() {
		return companyNo;
	}

	/**
	 * @return jobseekerid
	 */
	public String getJobseekerid() {
		return jobseekerid;
	}

	/**
	 * @return staffid
	 */
	public String getStaffid() {
		return staffid;
	}

	/**
	 * @return word
	 */
	public String[] getWord() {
		return word;
	}



}
