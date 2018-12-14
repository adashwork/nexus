package jp.or.adash.nexus.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.or.adash.nexus.entity.Company;
import jp.or.adash.nexus.utils.dao.Transaction;

public class CompanyDao {

	/**
	 * データベース接続オブジェクト
	 */
	private Connection conn;

	/**
	 * コンストラクタ
	 * @param transaction トランザクションオブジェクト
	 */
	public CompanyDao(Transaction transaction) {
		this.conn = transaction.getConnection();
	}

	/**
	 * 企業情報を新規登録する
	 * @param company
	 * @return count 登録が完了した件数
	 */
	public int insertCompany(Company company) throws IOException {
		int count = 0;

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("insert into company( ");

		sql.append("corporatenumber,   ");
		sql.append("companyno,         ");
		sql.append("companyname,       ");
		sql.append("companykana,       ");
		sql.append("companypostal,     ");
		sql.append("companyplace,      ");
		sql.append("nearstation,       ");
		sql.append("companyurl,        ");
		sql.append("jobcategorysmallcd,");
		sql.append("jobcategorylargecd,");
		sql.append("capital,           ");
		sql.append("employees,         ");
		sql.append("establishdt,       ");
		sql.append("tantouyakushoku,   ");
		sql.append("tantou,            ");
		sql.append("tantoukana,        ");
		sql.append("tantoutel,         ");
		sql.append("tantoufax,         ");
		sql.append("tantouemail,       ");
		sql.append("tantounote,        ");
		sql.append("tantoustaff_id,    ");
		sql.append("salesrank,         ");
		sql.append("salesnote,         ");
		//sql.append("createdt,          ");
		sql.append("createuserid,      ");
		//sql.append("updatedt,          ");
		sql.append("updateuserid,      ");
		//sql.append("deleteflag         ");

		sql.append(") values (");

		sql.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?");
		sql.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?");
		sql.append("?, ?, ?, ?, ?, 0");

		sql.append(")");

		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setString(1, company.getCompanyNo());
			ps.setString(2, company.getCorporateNumber());
			ps.setString(3, company.getCompanyName());
			ps.setString(4, company.getCompanyKana());
			ps.setString(5, company.getCompanyPostal());
			ps.setString(6, company.getCompanyPlace());
			ps.setString(7, company.getNearStation());
			ps.setString(8, company.getCompanyUrl());
			ps.setString(9, company.getJobCategorySmallCd());
			ps.setString(10, company.getJobCategoryLargeCd());
			ps.setInt(   11, company.getCapital());
			ps.setString(12, company.getEmployees());
			ps.setInt(   13, company.getEstablishDt());
			ps.setString(14, company.getTantouYakushoku());
			ps.setString(15, company.getTantou());
			ps.setString(16, company.getTantouKana());
			ps.setString(17, company.getTantouTel());
			ps.setString(18, company.getTantouFax());
			ps.setString(19, company.getTantouEmail());
			ps.setString(20, company.getTantouNote());
			ps.setString(21, company.getTantouStaffId());
			ps.setString(22, company.getSalesRank());
			ps.setString(23, company.getSalesNote());
			//ps.setString(1, company.getCreateDt()               );
			ps.setString(24, company.getCreateuserId());
			//ps.setString(1, company.getUpdateDt()               );
			ps.setString(25, company.getUpdateUserId());
			//ps.setString(1, company.getDeletefFag()             );

			// SQL文を実行する
			count = ps.executeUpdate();
		} catch (SQLException e) {
			throw new IOException(e);
		}

		return count;
	}

}
