package jp.or.adash.nexus.entity;

import java.util.List;

/**
 * マッチング事例検索条件のエンティティ
 * @author kitayama
 *
 */
public class MatchingSearchParameter {

	/**
	 * matchingid	マッチング事例ID
	 * jobseekerid	求職者ID
	 * staffid		職業紹介者ID
	 * word			フリーワード（複数持つことがある）
	 */
	//TODO 変数名とデータ型は要確認
	private Integer matchingid;
	private String jobseekerid;
	private String staffid;
	private List<String> word;

	public MatchingSearchParameter(Integer matchingid
								 , String jobseekerid
								 , String staffid
								 , List<String> word) {
		this.matchingid = matchingid;
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
	public List<String> getWord() {
		return word;
	}



}
