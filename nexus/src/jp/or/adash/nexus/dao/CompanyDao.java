package jp.or.adash.nexus.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.or.adash.nexus.entity.Company;
import jp.or.adash.nexus.entity.CompanySearchResult;
import jp.or.adash.nexus.utils.common.DataCommons;
import jp.or.adash.nexus.utils.dao.Transaction;

/**
 * 企業データアクセスクラス
 * @author mmiyamoto
 * @author msc
 *
 */
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
		sql.append("jobcategorymiddlecd,");
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
		sql.append("?, ?, ?, ?, ?, ?, 0");

		sql.append(" )");

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
			ps.setString(10, company.getJobCategoryMiddleCd());
			ps.setString(11, company.getJobCategoryLargeCd());
			if (company.getCapital() == null) {
				ps.setNull(12, java.sql.Types.INTEGER);
			} else {
				ps.setInt(12, company.getCapital());
			}

			ps.setString(13, company.getEmployees());

			if (company.getEstablishDt() == null) {
				ps.setNull(14, java.sql.Types.INTEGER);
			} else {
				ps.setInt(14, company.getEstablishDt());
			}

			ps.setString(15, company.getTantouYakushoku());
			ps.setString(16, company.getTantou());
			ps.setString(17, company.getTantouKana());
			ps.setString(18, company.getTantouTel());
			ps.setString(19, company.getTantouFax());
			ps.setString(20, company.getTantouMail());
			ps.setString(21, company.getTantouNote());
			ps.setString(22, company.getTantouStaffId());
			ps.setString(23, company.getSalesRank());
			ps.setString(24, company.getSalesNote());
			//ps.setDate(1, company.getCreateDt()               );
			ps.setString(25, company.getCreateuserId());
			//ps.setDate(1, company.getUpdateDt()               );
			ps.setString(26, company.getUpdateUserId());
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
							rs.getString("jobcategorymiddlecd"),
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
	 * 企業が登録済みならtrueを返す
	 * 削除フラグが1の企業も含む
	 * @param companyNo
	 * @return true:登録済み false:未登録
	 * @throws IOException
	 */
	public boolean isRegistCompany(String companyNo) throws IOException {
		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("select * ");
		sql.append("from company  ");
		sql.append("where companyno = ?  ");

		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setString(1, companyNo);
			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return true;
				}
			} catch (SQLException e) {
				throw new IOException(e);
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}
		return false;
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
		sql.append("jobcategorymiddlecd = ?,  ");
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
			ps.setString(9, company.getJobCategoryMiddleCd());
			ps.setString(10, company.getJobCategoryLargeCd());
			ps.setInt(   11, company.getCapital());
			ps.setString(12, company.getEmployees());
			ps.setInt(   13, company.getEstablishDt());
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
			ps.setDate(  24, DataCommons.convertToSqlDate(date));
			ps.setString(25, company.getUpdateUserId());

			ps.setString(26, company.getCompanyNo());

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

	/**
	 * 企業情報を検索する
	 * @param staffId
	 * @param jobCategory
	 * @param companyName
	 * @param companyPlace
	 * @return companyList
	 * @throws IOException
	 */

	public List<CompanySearchResult> selectCompanyList(
			String staffId,String jobCategory,String[] companyName,String[] companyPlace
			) throws IOException {

		List<CompanySearchResult> companyList = new ArrayList<CompanySearchResult>();		// 検索結果の企業を格納するリスト
		int setFlag = 0;											// ？の番号を決めるための値
		int setFlagStaffId = 0;
		int setFlagJobCategory = 0;
		int setCompanyName = 0;
		int setCompanyPlace = 0;

		// SQLのWHERE句に条件を追加するためのリスト
		List<String> whereStr = new ArrayList<String>();

		// 企業名・単語を入れるためのリスト
		List<String> setWordList = new ArrayList<String>();		// 条件格納用
		List<Integer> setFlagList = new ArrayList<Integer>();		// フラグ番号用


		// 検索・選択項目ごとの入力があるかどうか。あれば"？"に番号を振ってWHERE句に条件追加

		// 担当者の選択
		if(staffId != null && !"".equals(staffId)) {
			whereStr.add("tantoustaff_id = ?");
			setFlagStaffId = ++setFlag;
		}

		// 産業大分類の選択
		if(jobCategory != null && !"".equals(jobCategory)) {
			whereStr.add("jobcategorylargecd = ?");
			setFlagJobCategory = ++setFlag;
		}

		// フラグ番号管理用のリストの初期値を↑までの値と同じにする


		// 企業名・カナの検索
		if(companyName != null && !companyName[0].equals("") ) {// 配列の中身が空の判定
			for(int i = 0; i < companyName.length; i++) {
				// SQLでは(cmp.companyname like '%単語%' or cmp.companykana like '%単語%')
				// 1単語につき+2フラグ、対応する（?=単語）が加算される
				whereStr.add("(cmp.companyname like concat('%',?,'%') or cmp.companykana like concat('%',?,'%'))");
				setWordList.add(companyName[i]); 				// 前半の？に入力する単語用
				setWordList.add(companyName[i]); 				// 後半（カナ検索）用
				setCompanyName = ++setFlag;						// 一個目の？の番号
				setFlagList.add(setCompanyName);
				setCompanyName = ++setFlag;						// 二個目の？の番号
				setFlagList.add(setCompanyName);
			}
		}

		// 所在地・最寄駅の検索
		if(companyPlace != null && !companyPlace[0].equals("") ){	// 配列の中身が空の判定
			for(int i = 0; i < companyPlace.length; i++) {
			 // SQLでは(cmp.companyplace like '%単語%' or cmp.nearstation like '%単語%')
				whereStr.add("(cmp.companyplace like concat('%',?,'%') or cmp.nearstation like concat('%',?,'%'))");
				setWordList.add(companyPlace[i]); 				// 前半の？に入力する単語用
				setWordList.add(companyPlace[i]); 				// 後半用
				setCompanyPlace = ++setFlag;					// 一個目の？の番号
				setFlagList.add(setCompanyPlace);
				setCompanyPlace = ++setFlag;					// 二個目の？の番号
				setFlagList.add(setCompanyPlace);
			}
		}


		// SQL文の作成
		StringBuilder sqlSearchCompany = new StringBuilder();
		sqlSearchCompany.append("SELECT cmp.companyno, cmp.companyname,jbc.name,cmp.companyplace,cmp.tantou");
		sqlSearchCompany.append(" from company cmp left join jobcategory jbc");
		sqlSearchCompany.append(" on cmp.jobcategorylargecd = jbc.largecd");
		sqlSearchCompany.append(" WHERE  deleteflag = 0 and jbc.middlecd = '0' and jbc.smallcd = '0'");
		if(setFlag != 0 ) {
			sqlSearchCompany.append(" and ");
			sqlSearchCompany.append(String.join(" and ", whereStr));
		}

		// TODO 18/12/17改ページ用にSQLを書き換える


		try(PreparedStatement ps = conn.prepareStatement(sqlSearchCompany.toString())){

			// 入力された値の？の番号と内容を入力
			// 担当ID
			if (setFlagStaffId != 0) {
				ps.setString(setFlagStaffId, staffId);
			}
			// 産業大分類コード
			if (setFlagJobCategory != 0) {
				ps.setString(setFlagJobCategory, jobCategory);
			}
			// 企業名／所在地・最寄り駅は同時に処理
			if(setCompanyName != 0 || setCompanyPlace != 0) {
				for(int i = 0; i<setWordList.size() ;i++) {
					ps.setString(setFlagList.get(i), setWordList.get(i));
				}
			}


			// SQLを実行する
			try(ResultSet rs = ps.executeQuery()) {
			// ResultSetから1行読み込む
			while(rs.next()) {
				// ResultSetからCompanyオブジェクトにデータを詰め直し、リストに格納
				companyList.add(
						new CompanySearchResult(	rs.getString("companyno"),
													rs.getString("companyname"),
													rs.getString("companyplace"),
													rs.getString("tantou"),
													rs.getString("name")
												));
			}
		} catch(SQLException e) {
			throw new IOException(e);
		}
	} catch(SQLException e) {
		throw new IOException(e);
	}

		return companyList;

	}


}
