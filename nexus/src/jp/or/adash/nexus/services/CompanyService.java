package jp.or.adash.nexus.services;

import java.util.List;

import jp.or.adash.nexus.entity.Company;
import jp.or.adash.nexus.utils.dao.Transaction;

public class CompanyService {

	String errMsg = null;
	/**
	 * トランザクションオブジェクト
	 */
	private Transaction transaction;

	/**
	 * 処理結果メッセージを格納するリスト
	 */
	private List<String> messages;


	public List<String> getMessages() {
		return messages;
	}


	public boolean check(Company company) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}


	public boolean insertCompany(Company company) {
		return false;
		// TODO 自動生成されたメソッド・スタブ

	}



/*
	*//**
	 * データをチェックしてCompanyエンティティオブジェクトを生成する
	 * エンティティの型に合わない場合は、初期値を代入する
	 * @param companyNo
	 * @param corporateNumber
	 * @param companyName
	 * @param companyKana
	 * @param companyPostal
	 * @param companyPlace
	 * @param nearStation
	 * @param companyUrl
	 * @param jobCategorySmallCd
	 * @param jobCategoryLargeCd
	 * @param capital
	 * @param employees
	 * @param establishDt
	 * @param tantouYakushoku
	 * @param tantou
	 * @param tantouKana
	 * @param tantouTel
	 * @param tantouFax
	 * @param tantouEmail
	 * @param tantouNote
	 * @param tantouStaffId
	 * @param salesRank
	 * @param salesNote
	 * @param createDt
	 * @param createuserId
	 * @param upStringDt
	 * @param updateUserId
	 * @param deletefFag
	 * @return
	 *//*
	public Company newCompany(String companyNoP, String corporateNumberP, String companyNameP, String companyKanaP,
			String companyPostalP, String companyPlaceP, String nearStationP, String companyUrlP, String jobCategorySmallCdP,
			String jobCategoryLargeCdP, String capitalP, String employeesP, String establishDtP, String tantouYakushokuP,
			String tantouP, String tantouKanaP, String tantouTelP, String tantouFaxP, String tantouEmailP, String tantouNoteP,
			String tantouStaffIdP, String salesRankP, String salesNoteP, String createDtP, String createuserIdP, String updateDtP,
			String updateUserIdP, String deletefFagP) {

		 String companyNo;
		 String corporateNumber;
		 String companyName;
		 String companyKana;
		 String companyPostal;
		 String companyPlace;
		 String nearStation;
		 String companyUrl;
		 String jobCategorySmallCd;
		 String jobCategoryLargeCd;
		 Integer capital;
		 String employees;
		 Integer establishDt;
		 String tantouYakushoku;
		 String tantou;
		 String tantouKana;
		 String tantouTel;
		 String tantouFax;
		 String tantouEmail;
		 String tantouNote;
		 String tantouStaffId;
		 String salesRank;
		 String salesNote;
		 Date	 createDt;
		 String createuserId;
		 Date 	updateDt;
		 String updateUserId;
		 String deletefFag;

		 Company company = new Company(companyNo, corporateNumber, companyName, companyKana, companyPostal, companyPlace, nearStation, companyUrl, jobCategorySmallCd, jobCategoryLargeCd, capital, employees, establishDt, tantouYakushoku, tantou, tantouKana, tantouTel, tantouFax, tantouEmail, tantouNote, tantouStaffId, salesRank, salesNote, createDt, createuserId, updateDt, updateUserId, deletefFag);


		return null;
	}
*/



}
