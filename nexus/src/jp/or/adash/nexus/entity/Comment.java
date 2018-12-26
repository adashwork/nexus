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
	 * 備考IDを取得する
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 備考IDに値をセットする
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 事業所番号を取得する
	 * @return companyNo
	 */
	public String getCompanyNo() {
		return companyNo;
	}

	/**
	 * 求人NOを取得する
	 * @return kyujinNo
	 */
	public String getKyujinNo() {
		return kyujinNo;
	}

	/**
	 * 求職者IDを取得する
	 * @return jobSeekerId
	 */
	public String getJobSeekerId() {
		return jobSeekerId;
	}

	/**
	 * 職業紹介者IDを取得する
	 * @return staffId
	 */
	public String getStaffId() {
		return staffId;
	}

	/**
	 * マッチング事例IDを取得する
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
	 * 内容分類を取得する
	 * @return genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * 重要アラート
	 * @return important
	 */
	public String getImportant() {
		return important;
	}

	/**
	 * 件名を取得する
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 備考を取得する
	 * @return note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * 新規登録日を取得する
	 * @return createDt
	 */
	public Date getCreateDt() {
		return createDt;
	}

	/**
	 * 新規登録ユーザーを取得する
	 * @return createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * 最終更新日を取得する
	 * @return updateDt
	 */
	public Date getUpdateDt() {
		return updateDt;
	}

	/**
	 * 最終更新ユーザーを取得する
	 * @return updateUserId
	 */
	public String getUpdateUserId() {
		return updateUserId;
	}

}
