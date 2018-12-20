package jp.or.adash.nexus.entity;

import java.util.Date;

/**
 * マッチング検索結果のエンティティ
 * @author kitayama
 *
 */

public class MatchingSearchResult {

	/**
	* id マッチング事例ID
	* companyNo 事業所番号
	* kyujinno 求人NO
	* jobseekerid 求職者ID
	* staffid 職業紹介者ID
	* interviewdt 面接日
	* enterdt 入社日
	* assessment 評価
	* important 重要度（commentテーブルのimportant）
	* title タイトル（commentテーブルのtitle）
	* note 備考（commentテーブルのnote）
	* createdt 新規登録日
	* createuserid 新規登録ユーザー
	* updatedt 最終更新日
	* updateuserid 最終更新ユーザー
	*/
	private Integer id;
	private String companyNo;
	private String kyujinno;
	private String jobseekerid;
	private String staffid;
	private Date interviewdt;
	private Date enterdt;
	private String assessment;
	private String important;
	private String title;
	private String note;
	private Date createdt;
	private String createuserid;
	private Date updatedt;
	private String updateuserid;
	public MatchingSearchResult(Integer id, String companyNo, String kyujinno, String jobseekerid, String staffid,
			Date interviewdt, Date enterdt, String assessment, String important, String title, String note,
			Date createdt, String createuserid, Date updatedt, String updateuserid) {
		super();
		this.id = id;
		this.companyNo = companyNo;
		this.kyujinno = kyujinno;
		this.jobseekerid = jobseekerid;
		this.staffid = staffid;
		this.interviewdt = interviewdt;
		this.enterdt = enterdt;
		this.assessment = assessment;
		this.important = important;
		this.title = title;
		this.note = note;
		this.createdt = createdt;
		this.createuserid = createuserid;
		this.updatedt = updatedt;
		this.updateuserid = updateuserid;
	}

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @return companyNo
	 */
	public String getCompanyNo() {
		return companyNo;
	}
	/**
	 * @return kyujinno
	 */
	public String getKyujinno() {
		return kyujinno;
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
	 * @return interviewdt
	 */
	public Date getInterviewdt() {
		return interviewdt;
	}
	/**
	 * @return enterdt
	 */
	public Date getEnterdt() {
		return enterdt;
	}
	/**
	 * @return assessment
	 */
	public String getAssessment() {
		return assessment;
	}
	/**
	 * @return important
	 */
	public String getImportant() {
		return important;
	}
	/**
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @return note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @return createdt
	 */
	public Date getCreatedt() {
		return createdt;
	}
	/**
	 * @return createuserid
	 */
	public String getCreateuserid() {
		return createuserid;
	}
	/**
	 * @return updatedt
	 */
	public Date getUpdatedt() {
		return updatedt;
	}
	/**
	 * @return updateuserid
	 */
	public String getUpdateuserid() {
		return updateuserid;
	}



}
