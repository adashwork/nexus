package jp.or.adash.nexus.entity;

import java.util.Date;

public class Comment {

	/**
	 * id	備考ID
	* companyNo	事業所番号
	* kyujinNo	求人NO
	* jobSeekerId	求職者ID
	* staffId	職業紹介者ID
	* matchId	マッチング事例ID
	* genre	内容分類
	* important	重要アラート
	* title	件名
	* note	備考
	* createDt	新規登録日
	* createUserId	新規登録ユーザー
	* updatedt	最終更新日
	* updateUserId	最終更新ユーザー
	 */

	private Integer id;
	private String companyNo;
	private String kyujinNo;
	private String jobSeekerId;
	private String staffId;
	private Integer matchId;
	private String genre;
	private String important;
	private String title;
	private String note;
	private Date createDt;
	private String createUserId;
	private Date updateDt;
	private String updateUserId;



	/**
	 * コメントテーブルのフィールドをすべて引数にとる
	 * @param id
	 * @param companyNo
	 * @param kyujinNo
	 * @param jobSeekerId
	 * @param staffId
	 * @param matchId
	 * @param genre
	 * @param important
	 * @param title
	 * @param note
	 * @param createDt
	 * @param createUserId
	 * @param updateDt
	 * @param updateUserId
	 */
	public Comment(Integer id, String companyNo, String kyujinNo, String jobSeekerId, String staffId, Integer matchId,
			String genre, String important, String title, String note, Date createDt, String createUserId,
			Date updateDt, String updateUserId) {
		this.id = id;
		this.companyNo = companyNo;
		this.kyujinNo = kyujinNo;
		this.jobSeekerId = jobSeekerId;
		this.staffId = staffId;
		this.matchId = matchId;
		this.genre = genre;
		this.important = important;
		this.title = title;
		this.note = note;
		this.createDt = createDt;
		this.createUserId = createUserId;
		this.updateDt = updateDt;
		this.updateUserId = updateUserId;
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
	 * @return kyujinNo
	 */
	public String getKyujinNo() {
		return kyujinNo;
	}

	/**
	 * @return jobSeekerId
	 */
	public String getJobSeekerId() {
		return jobSeekerId;
	}

	/**
	 * @return staffId
	 */
	public String getStaffId() {
		return staffId;
	}

	/**
	 * @return matchId
	 */
	public Integer getMatchId() {
		return matchId;
	}

	/**
	 * @param matchId セットする matchId
	 * @author T.Ikeda 2018/12/18
	 */
	public void setMatchId(Integer matchId) {
		this.matchId = matchId;
	}

	/**
	 * @return genre
	 */
	public String getGenre() {
		return genre;
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
	 * @return createDt
	 */
	public Date getCreateDt() {
		return createDt;
	}

	/**
	 * @return createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @return updateDt
	 */
	public Date getUpdateDt() {
		return updateDt;
	}

	/**
	 * @return updateUserId
	 */
	public String getUpdateUserId() {
		return updateUserId;
	}

}
