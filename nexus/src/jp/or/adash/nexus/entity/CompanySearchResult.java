package jp.or.adash.nexus.entity;

/**
 *
 *
 * コメントのちに追加
 * @author AWU305
 *
 */

public class CompanySearchResult {

	private String companyNo;
	private String companyName;
	private String companyPlace;
	private String tantou;
	private String jobCategoryLargeName;



	public CompanySearchResult(String companyNo, String companyName, String companyPlace, String tantou,
			String jobCategoryLargeName) {
		super();
		this.companyNo = companyNo;
		this.companyName = companyName;
		this.companyPlace = companyPlace;
		this.tantou = tantou;
		this.jobCategoryLargeName = jobCategoryLargeName;
	}




	public String getCompanyNo() {
		return companyNo;
	}


	public String getCompanyName() {
		return companyName;
	}


	public String getCompanyPlace() {
		return companyPlace;
	}


	public String getTantou() {
		return tantou;
	}


	public String getJobCategoryLargeName() {
		return jobCategoryLargeName;
	}

}
