package jp.or.adash.nexus.entity;

public class CommentSearchParameter {

	/**
	 * id			備考ID
	 * companyNo	事業所番号
	 * kyujinNo		求人NO
	 * staffId		職業紹介者ID
	 * jobSeekerId	求職者ID
	 * matchId		マッチング事例ID
	 */
	private Integer id;
	private String companyNo;
	private String kyujinNo;
	private String staffId;
	private String jobSeekerId;
	private Integer matchId;

	public CommentSearchParameter(Integer id, String companyNo, String kyujinNo, String staffId, String jobSeekerId,
			Integer matchId) {
		super();
		this.id = id;
		this.companyNo = companyNo;
		this.kyujinNo = kyujinNo;
		this.staffId = staffId;
		this.jobSeekerId = jobSeekerId;
		this.matchId = matchId;
	}

	/**
	 * 備考IDを取得する
	 * @return id
	 */
	public Integer getId() {
		return id;
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
	 * 職業紹介者IDを取得する
	 * @return staffId
	 */
	public String getStaffId() {
		return staffId;
	}

	/**
	 * 求職者IDを取得する
	 * @return jobSeekerId
	 */
	public String getJobSeekerId() {
		return jobSeekerId;
	}

	/**
	 * マッチング事例IDを取得する
	 * @return matchId
	 */
	public Integer getMatchId() {
		return matchId;
	}




}
