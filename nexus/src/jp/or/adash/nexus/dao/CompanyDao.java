package jp.or.adash.nexus.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import jp.or.adash.nexus.entity.Company;
import jp.or.adash.nexus.utils.common.DataCommons;
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
		sql.append("insert into company ( ");

		sql.append("companyno,         ");
		sql.append("corporatenumber,   ");
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
		sql.append("tantoumail,       ");
		sql.append("tantounote,        ");
		sql.append("tantoustaff_id,    ");
		sql.append("salesrank,         ");
		sql.append("salesnote,         ");
		//sql.append("createdt,          ");
		sql.append("createuserid,      ");
		//sql.append("updatedt,          ");
		sql.append("updateuserid,      ");
		sql.append("deleteflag         ");

		sql.append("  ) values ( ");

		sql.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
		sql.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
		sql.append("?, ?, ?, ?, ?, 0");

		sql.append(" )");
		String test = sql.toString();

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
			if (company.getCapital() == null) {
				ps.setNull(11, java.sql.Types.INTEGER);
			} else {
				ps.setInt(11, company.getCapital());
			}

			ps.setString(12, company.getEmployees());

			if (company.getEstablishDt() == null) {
				ps.setNull(13, java.sql.Types.INTEGER);
			} else {
				ps.setInt(13, company.getEstablishDt());
			}

			ps.setString(14, company.getTantouYakushoku());
			ps.setString(15, company.getTantou());
			ps.setString(16, company.getTantouKana());
			ps.setString(17, company.getTantouTel());
			ps.setString(18, company.getTantouFax());
			ps.setString(19, company.getTantouMail());
			ps.setString(20, company.getTantouNote());
			ps.setString(21, company.getTantouStaffId());
			ps.setString(22, company.getSalesRank());
			ps.setString(23, company.getSalesNote());
			//ps.setDate(1, company.getCreateDt()               );
			ps.setString(24, company.getCreateuserId());
			//ps.setDate(1, company.getUpdateDt()               );
			ps.setString(25, company.getUpdateUserId());
			//ps.setDate(1, company.getDeletefFag()             );

			// SQL文を実行する
			count = ps.executeUpdate();
		} catch (SQLException e) {
			throw new IOException(e);
		}

		return count;
	}

	/**
	 * 企業の詳細を1件取得する
	 * companyNoに対応したデータが無い場合はnullを返す
	 * @param companyNo
	 * @return Company 企業オブジェクト。該当件数がなければnullを返す
	 * @throws IOException
	 */
	public Company selectCompanyInfo(String companyNo) throws IOException {
		//TODO 項目のあたりはかなりまだ適当です。
		Company companyInfo = null;

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("select * ");
		sql.append("from company  ");
		sql.append("where companyno = ? ");

		String test = sql.toString();

		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setString(1, companyNo);

			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				// 取得結果をリストに格納する
				if (rs.next()) {
					companyInfo = new Company(
							rs.getString("companyno"),
							rs.getString("corporatenumber"),
							rs.getString("companyname"),
							rs.getString("companykana"),
							rs.getString("companypostal"),
							rs.getString("companyplace"),
							rs.getString("nearstation"),
							rs.getString("companyurl"),
							rs.getString("jobcategorysmallcd"),
							rs.getString("jobcategorylargecd"),
							rs.getInt("capital"),
							rs.getString("employees"),
							rs.getInt("establishdt"),
							rs.getString("tantouyakushoku"),
							rs.getString("tantou"),
							rs.getString("tantoukana"),
							rs.getString("tantoutel"),
							rs.getString("tantoufax"),
							rs.getString("tantoumail"),
							rs.getString("tantounote"),
							rs.getString("tantoustaff_id"),
							rs.getString("salesrank"),
							rs.getString("salesnote"),
							rs.getDate("createdt"),
							rs.getString("createuserid"),
							rs.getDate("updatedt"),
							rs.getString("updateuserid"),
							rs.getString("deleteflag"));

				}
			} catch (SQLException e) {
				throw new IOException(e);
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}

		return companyInfo;
	}

	/**
	 * 企業情報を更新する
	 * @param company 企業エンティティオブジェクト
	 * @return count 更新件数
	 * @throws IOException
	 */

	public int updateCompany(Company company) throws IOException {
		//TODO 項目のあたりはかなりまだ適当です。
		int count = 0;

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("update  company  ");
		sql.append(" set  ");

		//sql.append("companyno = ?          "); 事業所番号は変更不可とするため除外
		sql.append("corporatenumber = ?,    ");
		sql.append("companyname = ?,        ");
		sql.append("companykana = ?,        ");
		sql.append("companypostal = ?,      ");
		sql.append("companyplace = ?,       ");
		sql.append("nearstation = ?,        ");
		sql.append("companyurl = ?,         ");
		sql.append("jobcategorysmallcd = ?,  ");
		sql.append("jobcategorylargecd = ?, ");
		sql.append("capital = ?,            ");
		sql.append("employees = ?,          ");
		sql.append("establishdt = ?,        ");
		sql.append("tantouyakushoku = ?,    ");
		sql.append("tantou = ?,             ");
		sql.append("tantoukana = ?,         ");
		sql.append("tantoutel = ?,          ");
		sql.append("tantoufax = ?,          ");
		sql.append("tantoumail = ?,        ");
		sql.append("tantounote = ?,         ");
		sql.append("tantoustaff_id = ?,     ");
		sql.append("salesrank = ?,          ");
		sql.append("salesnote = ?,          ");
		sql.append("updatedt = ?,           ");
		sql.append("updateuserid = ?       ");

		sql.append(" where companyno = ? ");

		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			//引数なしDateオブジェクトを生成すると現在時刻が入る
			Date date = new Date();

			ps.setString(1, company.getCorporateNumber());
			ps.setString(2, company.getCompanyName());
			ps.setString(3, company.getCompanyKana());
			ps.setString(4, company.getCompanyPostal());
			ps.setString(5, company.getCompanyPlace());
			ps.setString(6, company.getNearStation());
			ps.setString(7, company.getCompanyUrl());
			ps.setString(8, company.getJobCategorySmallCd());
			ps.setString(9, company.getJobCategoryLargeCd());
			ps.setInt(10, company.getCapital());
			ps.setString(11, company.getEmployees());
			ps.setInt(12, company.getEstablishDt());
			ps.setString(13, company.getTantouYakushoku());
			ps.setString(14, company.getTantou());
			ps.setString(15, company.getTantouKana());
			ps.setString(16, company.getTantouTel());
			ps.setString(17, company.getTantouFax());
			ps.setString(18, company.getTantouMail());
			ps.setString(19, company.getTantouNote());
			ps.setString(20, company.getTantouStaffId());
			ps.setString(21, company.getSalesRank());
			ps.setString(22, company.getSalesNote());
			ps.setDate(23, DataCommons.convertToSqlDate(date));
			ps.setString(24, company.getUpdateUserId());

			ps.setString(25, company.getCompanyNo());

			// SQL文を実行する
			count = ps.executeUpdate();
		} catch (SQLException e) {
			throw new IOException(e);
		}

		return count;
	}

	/**
	 * 企業のdeleteflagを1にする
	 * @param companyNo
	 * @return 削除件数
	 * @throws IOException
	 */
	public int deleteCompany(String companyNo) throws IOException {
		//TODO 項目のあたりはかなりまだ適当です。
				int count = 0;

				// SQL文を生成する
				StringBuffer sql = new StringBuffer();
				sql.append("update  company  ");
				sql.append(" set  ");
				sql.append("deleteflag  = 1        ");
				sql.append(" where companyno = ? ");

				try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
					ps.setString(1, companyNo);
					// SQL文を実行する
					count = ps.executeUpdate();
				} catch (SQLException e) {
					throw new IOException(e);
				}

				return count;
	}

}
