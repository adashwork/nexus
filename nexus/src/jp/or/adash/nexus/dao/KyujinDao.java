package jp.or.adash.nexus.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.or.adash.nexus.entity.Company;
import jp.or.adash.nexus.entity.Kyujin;
import jp.or.adash.nexus.utils.common.DataCommons;
import jp.or.adash.nexus.utils.dao.Transaction;

/**
 * 求人データアクセスクラス
 * @author ??
 * @author pgjavaAT
 *
 */
public class KyujinDao {
	/**
	 * データベース接続オブジェクト
	 */
	private Connection conn;
	/**
	 * コンストラクタ
	 * @param transaction トランザクションオブジェクト
	 */
	public KyujinDao(Transaction transaction) {
		this.conn = transaction.getConnection();
	}
	/**
	 * 求人票を登録する
	 * @param kyujin 登録する求人の情報
	 * @return 登録件数
	 * @throws IOException
	 */
	public int insert(Kyujin kyujin) throws IOException {
		int count = 0;

		// SQL文を生成する(求人票①)
		StringBuffer sql = new StringBuffer();
		 sql.append("insert into kyujin(");
			sql.append("no, companyno, companykana, postal, address, nearline, nearstation, addresscd,");
	        sql.append("jobsmallcd1, jobsmallcd2, jobsmallcd3, joblargecd1, joblargecd2, joblargecd3,");
	        sql.append("job, detail, koyoukeitaicd, hakencd, koyoukikan, koyoukikankaishi, koyoukikanowari,");
	        sql.append("education, experience, license, agemin, agemax, salaryformcd, salarymin, salarymax,	bouns, koutuhi,	teate,");
	        sql.append("begintime, endtime,	shift, flex, jitan,	jikangai, siyoukikan, workdays,	nenkanholiday,");
	        sql.append("applicationform, background, bosyunumbers,hiddensex, hiddenagemin, hiddenagemax, hiddenetc,");
	        sql.append("receptiondt, perioddt, createuserid, updateuserid, deleteflag,");
	        sql.append(") values (");
	        sql.append("?, ?, ?, ?, ?, ?, ?, ?,");
	        sql.append("?, ?, ?, ?, ?, ?,");
	        sql.append("?, ?, ?, ?, ?, ?, ?,");
	        sql.append("?, ?, ?, ?, ?, ?, ?, ?,	?, ?, ?,");
	        sql.append("?, ?, ?, ?, ?, ?, ?, ?,	?,");
	        sql.append("?, ?, ?, ?, ?, ?, ?,");
	        sql.append("?, ?, ?, ?, ?,");
	        sql.append(")");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setString(1, kyujin.getNo());
			ps.setString(2, kyujin.getCompanyno());
			ps.setString(3, kyujin.getCompanykana());
			ps.setString(4, kyujin.getPostal());
			ps.setString(5, kyujin.getAddress());
			ps.setString(6, kyujin.getNearline());//沿線
			ps.setString(7, kyujin.getNearstation());
			ps.setString(8, kyujin.getAddresscd());
			ps.setString(9, kyujin.getJobsmallcd1());
			ps.setString(10, kyujin.getJobsmallcd2());
			ps.setString(11, kyujin.getJobsmallcd3());
			ps.setString(12, kyujin.getJoblargecd1());
			ps.setString(13, kyujin.getJoblargecd2());
			ps.setString(14, kyujin.getJoblargecd3());
			ps.setString(15, kyujin.getJob());
			ps.setString(16, kyujin.getDetail());
			ps.setString(17, kyujin.getKoyoukeitaicd());
			ps.setString(18, kyujin.getHakencd());
			ps.setString(19, kyujin.getKoyoukikan());
			ps.setDate(20, DataCommons.convertToSqlDate(kyujin.getKoyoukikankaishi()));
			ps.setDate(21, DataCommons.convertToSqlDate(kyujin.getKoyoukikanowari()));
			ps.setString(22, kyujin.getEducation());
			ps.setString(23, kyujin.getExperience());
			ps.setString(24, kyujin.getLicense());
			ps.setInt(25, kyujin.getAgemin());
			ps.setInt(26, kyujin.getAgemax());
			ps.setString(27, kyujin.getSalaryformcd());
			ps.setInt(28, kyujin.getSalarymin());
			ps.setInt(29, kyujin.getSalarymax());
			ps.setString(30, kyujin.getBonus());//賞与
			ps.setString(31, kyujin.getKoutuhi());//通勤手当
			ps.setString(32, kyujin.getTeate());//他諸手当
			ps.setInt(33, kyujin.getBegintime());
			ps.setInt(34, kyujin.getEndtime());
			ps.setString(35, kyujin.getShift());//シフト制
			ps.setString(36, kyujin.getFlex());//フレックスタイム
			ps.setString(37, kyujin.getJitan());//時短勤務
			ps.setInt(38, kyujin.getJikangai());//時間外平均
			ps.setInt(39, kyujin.getSiyoukikan());//試用期間
			ps.setInt(40, kyujin.getWorkdays());//週所定労働日数
			ps.setString(41, kyujin.getNenkanholiday());//年間休日
			ps.setString(42, kyujin.getApplicationform());
			ps.setString(43, kyujin.getBackground());
			ps.setString(44, kyujin.getBosyunumbers());
			ps.setString(45, kyujin.getHiddensex());
			ps.setInt(46, kyujin.getHiddenagemin());
			ps.setInt(47, kyujin.getHiddenagemax());
			ps.setString(48, kyujin.getHiddenetc());
			ps.setDate(49, DataCommons.convertToSqlDate(kyujin.getReceptiondt()));
			ps.setDate(50, DataCommons.convertToSqlDate(kyujin.getPerioddt()));
          //ps.setString(51, "now()");//createdt???
			ps.setString(51, kyujin.getCreateuserid());
		  //ps.setString(52, "now()");//updatedt???
			ps.setString(52,  kyujin.getUpdateuserid());
			ps.setString(53, "0");//deleteflag???

			// SQL文を実行する(求人票)
			count = ps.executeUpdate();
		} catch (SQLException e) {
			throw new IOException(e);
		}

		return count;
	}

/**
 * 企業データアクセスクラス
 *@author ??
 *@author pgjavaAT
 *
 */
public class CompanyDao{
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
	 *企業を登録する
	 *@param Company 登録する企業の情報
	 *@return 登録件数
	 *@throws IOException
	 */
	public int insert(Company company) throws IOException{
		int count = 0;

		// SQL文を生成する(企業１)
		StringBuffer sql = new StringBuffer();
		sql.append("insert into company(");
		sql.append("companyno, corporatenumber, companyname, companykana, companypostal, companyplace,");
        sql.append("nearstation, companyurl, jobcategorysmallcd, jobcategorymiddlecd, jobcategorylargecd, capital, employees,");
        sql.append("establishdt, tantouyakushoku, tantou, tantoukana, tantoutel, tantoufax, tantoumail, tantounote,");
        sql.append("tantoustaff_id, salesrank, salesnote,");
        sql.append("createuserid, updateuserid, deleteflag,");
        sql.append(") values (");
        sql.append("?, ?, ?, ?, ?, ?,");
        sql.append("?, ?, ?, ?, ?, ?, ?,");
        sql.append("?, ?, ?, ?, ?, ?, ?, ?,");
        sql.append("?, ?, ?,");
        sql.append("?, ?, ?,");
        sql.append(")");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setString(1, company.getCompanyNo());
			ps.setString(2, company.getCorporateNumber());//法人番号
			ps.setString(3, company.getCompanyName());
			ps.setString(4, company.getTantouKana());
			ps.setString(5, company.getCompanyPostal());
			ps.setString(6, company.getCompanyPlace());
			ps.setString(7, company.getNearStation());
			ps.setString(8, company.getCompanyUrl());
			ps.setString(9, company.getJobCategorySmallCd());
			ps.setString(10, company.getJobCategoryMiddleCd());
			ps.setString(11, company.getJobCategoryLargeCd());
			ps.setLong(12, company.getCapital());
			ps.setString(13, company.getEmployees());//従業員数
			ps.setInt(14, company.getEstablishDt());
			ps.setString(15, company.getTantouYakushoku());
			ps.setString(16, company.getTantou());
			ps.setString(17, company.getTantouKana());
			ps.setString(18, company.getTantouTel());//担当者tel
			ps.setString(19, company.getTantouFax());//担当者fax
			ps.setString(20, company.getTantouMail());//担当者emai
			ps.setString(21, company.getTantouNote());//担当者備考
			ps.setString(22, company.getTantouStaffId());
			ps.setString(23, company.getSalesRank());
			ps.setString(24, company.getSalesNote());
			//ps.setString(24, "now()");//createdt???
			ps.setString(25, company.getCreateuserId());
		    //ps.setString(25, "now()");//updatedt???
			ps.setString(26,  company.getUpdateUserId());
			ps.setString(27, "0");//deleteflag???

			// SQL文を実行する(企業)
			count = ps.executeUpdate();
		}catch(SQLException e) {
			throw new IOException(e);
		}
		return count;
		}

    /**
	 * 求人票コードを元に、商品情報（1件）を取得する
	 * @param no 求人票番号
	 * @return 求人オブジェクト
	 * @throws IOException
	 */
	public Kyujin selectKyujin(String no) throws IOException {
		Kyujin kyujin = null;

		// SQL文を生成する(求人票②)
		StringBuffer sql = new StringBuffer();
		sql.append("insert into kyujin(");
		sql.append("no, companyno, companykana, postal, address, nearline, nearstation, addresscd,");
        sql.append("jobsmallcd1, jobsmallcd2, jobsmallcd3, joblargecd1, joblargecd2, joblargecd3,");
        sql.append("job, detail, koyoukeitaicd, hakencd, koyoukikan, koyoukikankaishi, koyoukikanowari,");
        sql.append("education, experience, license, agemin, agemax, salaryformcd, salarymin, salarymax,	bouns, koutuhi,	teate,");
        sql.append("begintime, endtime,	shift, flex, jitan,	jikangai, siyoukikan, workdays,	nenkanholiday,");
        sql.append("applicationform, background, bosyunumbers,hiddensex, hiddenagemin, hiddenagemax, hiddenetc,");
        sql.append("receptiondt, perioddt, createuserid, updateuserid, deleteflag,");
        sql.append(") values (");
        sql.append("?, ?, ?, ?, ?, ?, ?, ?,");
        sql.append("?, ?, ?, ?, ?, ?,");
        sql.append("?, ?, ?, ?, ?, ?, ?,");
        sql.append("?, ?, ?, ?, ?, ?, ?, ?,	?, ?, ?,");
        sql.append("?, ?, ?, ?, ?, ?, ?, ?,	?,");
        sql.append("?, ?, ?, ?, ?, ?, ?,");
        sql.append("?, ?, ?, ?, ?,");
        sql.append(")");
		sql.append(" from kyujin");
		sql.append(" where no = ?");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setString(1, no);

			// SQL文を実行する(求人票②)
			try (ResultSet rs = ps.executeQuery()) {
				// 取得結果をリストに格納する
				while (rs.next()) {
					return new Kyujin(rs.getString("no"),
							rs.getString("companyno"),
							rs.getString("companykana"),
							rs.getString("postal"),
							rs.getString("address"),
							rs.getString("nearline"),
							rs.getString("nearstation"),
							rs.getString("addresscd"),
							rs.getString("jobsmallcd1"),
							rs.getString("jobsmallcd2"),
							rs.getString("jobsmallcd3"),
							rs.getString("joblargecd1"),
							rs.getString("joblargecd2"),
							rs.getString("joblargecd3"),
							rs.getString("job"),
							rs.getString("detail"),
							rs.getString("koyoukeitaicd"),
							rs.getString("hakencd"),
							rs.getString("koyoukikan"),
							rs.getDate("koyoukikankaishi"),
							rs.getDate("koyoukikanowari"),
							rs.getString("education"),
							rs.getString("experience"),
							rs.getString("license"),
							rs.getInt("agemin"),
							rs.getInt("ageman"),
							rs.getString("salaryformcd"),
							rs.getInt("salarymin"),
							rs.getInt("salarymax"),
							rs.getString("bonus"),
							rs.getString("koutuhi"),
							rs.getString("teate"),
							rs.getInt("begintime"),
							rs.getInt("endtime"),
							rs.getString("shift"),
							rs.getString("flex"),
							rs.getString("jitan"),
							rs.getInt("jikangai"),
							rs.getInt("siyoukika"),
							rs.getInt("workdays"),
							rs.getString("nenkanholiday"),
							rs.getString("applicationform"),
							rs.getString("background"),
							rs.getString("bosyunumbers"),
							rs.getString("hiddensex"),
							rs.getInt("hiddenagemin"),
							rs.getInt("hiddenagemax"),
							rs.getString("hiddenetc"),
							rs.getDate("receptiondt"),
							rs.getDate("perioddt"),
							rs.getTimestamp("createdt"),
							rs.getString("createuserid"),
							rs.getTimestamp("upDatedt"),
							rs.getString("upDateuserid"),
							rs.getString("deleteflag"));
				}
			}catch(SQLException e) {
				throw new IOException(e);
			}
		}catch(SQLException e) {
			throw new IOException(e);
		}

	     return kyujin;
	}


	/**
	 * 企業コードを元に、商品情報(1件)を取得する
	 * @param no 事業所番号
	 * @return 企業オブジェクト
	 * @throws IOException
	 */
	public Company selectCompany(String no)throws IOException{
		Company company = null;

		//SQL文を生成する(企業２)
		StringBuffer sql = new StringBuffer();
		sql.append("insert into company(");
		sql.append("companyno, corporatenumber, companyname, companykana, companypostal, companyplace,");
        sql.append("nearstation, companyurl, jobcategorysmallcd, jobcategorymiddlecd, jobcategorylargecd, capital, employees,");
        sql.append("establishdt, tantouyakushoku, tantou, tantoukana, tantoutel, tantoufax, tantoumail, tantounote,");
        sql.append("tantoustaff_id, salesrank, salesnote,");
        sql.append("createuserid, updateuserid, deleteflag,");
        sql.append(") values (");
        sql.append("?, ?, ?, ?, ?, ?,");
        sql.append("?, ?, ?, ?, ?, ?, ?,");
        sql.append("?, ?, ?, ?, ?, ?, ?, ?,");
        sql.append("?, ?, ?,");
        sql.append("?, ?, ?,");
        sql.append(")");
		sql.append("from company");
		sql.append("where no = ?");
		try(PreparedStatement ps = this.conn.prepareStatement(sql.toString())){
			ps.setString(1, no);

			//SQL文を実行する(企業)
			try(ResultSet rs = ps.executeQuery()){
				//取得結果をリストに格納する
				while(rs.next()) {
					return new Company(rs.getString("companyno"),//2PK
							rs.getString("corporatenumber"),//1
							rs.getString("companyname"),
							rs.getString("tantoukana"),
							rs.getString("companypostal"),
							rs.getString("companyplace"),
							rs.getString("nearstation"),
							rs.getString("companyurl"),
							rs.getString("jobcategorysmallcd"),
							rs.getString("jobcategorymiddlecd"),
							rs.getString("jobcategorylargecd"),
							rs.getLong("capital"),
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
//							rs.getTimestamp("createdt"),
							rs.getString("createuserid"),
//							rs.getTimestamp("upDatedt"),
							rs.getString("upDateuserid"),
							rs.getString("deleteflag"));
				}
			}catch(SQLException e) {
				throw new IOException(e);
			}

		}catch(SQLException e) {
			throw new IOException(e);
		}

		return company;

	}

	/**
	 * 求人票の一覧を取得する
	 * @return 求人票リスト
	 * @throws IOException
	 */
	public List<Kyujin> selectKyujinList() throws IOException {
		List<Kyujin> kyujins = new ArrayList<Kyujin>();

		// SQL文を生成する(求人票③)
		StringBuffer sql = new StringBuffer();
		sql.append("insert into kyujin(");
		sql.append("no, companyno, companykana, postal, address, nearline, nearstation, addresscd,");
        sql.append("jobsmallcd1, jobsmallcd2, jobsmallcd3, joblargecd1, joblargecd2, joblargecd3,");
        sql.append("job, detail, koyoukeitaicd, hakencd, koyoukikan, koyoukikankaishi, koyoukikanowari,");
        sql.append("education, experience, license, agemin, agemax, salaryformcd, salarymin, salarymax,	bouns, koutuhi,	teate,");
        sql.append("begintime, endtime,	shift, flex, jitan,	jikangai, siyoukikan, workdays,	nenkanholiday,");
        sql.append("applicationform, background, bosyunumbers,hiddensex, hiddenagemin, hiddenagemax, hiddenetc,");
        sql.append("receptiondt, perioddt, createuserid, updateuserid, deleteflag,");
        sql.append(") values (");
        sql.append("?, ?, ?, ?, ?, ?, ?, ?,");
        sql.append("?, ?, ?, ?, ?, ?,");
        sql.append("?, ?, ?, ?, ?, ?, ?,");
        sql.append("?, ?, ?, ?, ?, ?, ?, ?,	?, ?, ?,");
        sql.append("?, ?, ?, ?, ?, ?, ?, ?,	?,");
        sql.append("?, ?, ?, ?, ?, ?, ?,");
        sql.append("?, ?, ?, ?, ?,");
        sql.append(")");
		sql.append(" from kyujin");
		sql.append(" order by no");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			// SQL文を実行する(求人票)
			try (ResultSet rs = ps.executeQuery()) {
				// 取得結果をリストに格納する(求人票)
				while (rs.next()) {
					kyujins.add(new Kyujin(rs.getString("no"),
									rs.getString("companyno"),
									rs.getString("companykana"),
									rs.getString("postal"),
									rs.getString("address"),
									rs.getString("nearline"),
									rs.getString("nearstation"),
									rs.getString("addresscd"),
									rs.getString("jobsmallcd1"),
									rs.getString("jobsmallcd2"),
									rs.getString("jobsmallcd3"),
									rs.getString("joblargecd1"),
									rs.getString("joblargecd2"),
									rs.getString("joblargecd3"),
									rs.getString("job"),
									rs.getString("detail"),
									rs.getString("koyoukeitaicd"),
									rs.getString("hakencd"),
									rs.getString("koyoukikan"),
									rs.getDate("koyoukikankaishi"),
									rs.getDate("koyoukikanowari"),
									rs.getString("education"),
									rs.getString("experience"),
									rs.getString("license"),
									rs.getInt("agemin"),
									rs.getInt("ageman"),
									rs.getString("salaryformcd"),
									rs.getInt("salarymin"),
									rs.getInt("salarymax"),
									rs.getString("bonus"),
									rs.getString("koutuhi"),
									rs.getString("teate"),
									rs.getInt("begintime"),
									rs.getInt("endtime"),
									rs.getString("shift"),
									rs.getString("flex"),
									rs.getString("jitan"),
									rs.getInt("jikangai"),
									rs.getInt("siyoukika"),
									rs.getInt("workdays"),
									rs.getString("nenkanholiday"),
									rs.getString("applicationform"),
									rs.getString("background"),
									rs.getString("bosyunumbers"),
									rs.getString("hiddensex"),
									rs.getInt("hiddenagemin"),
									rs.getInt("hiddenagemax"),
									rs.getString("hiddenetc"),
									rs.getDate("receptiondt"),
									rs.getDate("perioddt"),
									rs.getTimestamp("createdt"),
									rs.getString("createuserid"),
									rs.getTimestamp("upDatedt"),
									rs.getString("upDateuserid"),
									rs.getString("deleteflag")));
				}
			} catch (SQLException e) {
				throw new IOException(e);
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}

		return kyujins;
	}

    /**
    *企業の一覧を取得する
    *@return 企業リスト
    *@throws IOException
    */
    public List<Company> selectCompany() throws IOException{
	List<Company> company = new ArrayList<Company>();

	//SQL文を生成する(企業３)
	StringBuffer sql = new StringBuffer();
	sql.append("insert into company(");
	sql.append("corporatenumber, companyno, companyname, companykana, companypostal, companyplace,");
    sql.append("nearstation, companyur, jobcategorysmallcd, jobcategorymiddlecd, jobcategorylargecd, capital, employees,");
    sql.append("establishdt, tantouyakushoku, tantou, tantoukana, tantoutel, tantoufax, tantoumail, tantounote,");
    sql.append("tantoustaff_id, salesrank, salesnote,");
    sql.append("createuserid, updateuserid, deleteflag,");
    sql.append(") values (");
    sql.append("?, ?, ?, ?, ?, ?,");
    sql.append("?, ?, ?, ?, ?, ?, ?,");
    sql.append("?, ?, ?, ?, ?, ?, ?, ?,");
    sql.append("?, ?, ?,");
    sql.append("?, ?, ?,");
    sql.append(")");
	sql.append("from company");
	sql.append("order by no");
	try(PreparedStatement ps = this.conn.prepareStatement(sql.toString())){
		//SQL文を実行する
		try(ResultSet rs = ps.executeQuery()){
			//取引結果をリストに格納する
			while(rs.next()) {
				company.add(new Company(rs.getString("corporatenumber"),
								rs.getString("companyno"),
								rs.getString("companyname"),
								rs.getString("tantoukana"),
								rs.getString("companypostal"),
								rs.getString("companyplace"),
								rs.getString("nearstation"),
								rs.getString("companyurl"),
								rs.getString("jobcategorysmallcd"),
								rs.getString("jobcategorylargecd"),
								rs.getLong("capital"),
								rs.getString("corporatenumber"),
								rs.getInt("establishdt"),
								rs.getString("tantouyakushoku"),
								rs.getString("tantou"),
								rs.getString("tantoutel"),
								rs.getString("tantoufax"),
								rs.getString("tantouemail"),
								rs.getString("tantounote"),
								rs.getString("tantoustaff_id"),
								rs.getString("salesrank"),
								rs.getString("salesnote"),
								rs.getTimestamp("createdt"),
								rs.getString("createuserid"),
								rs.getTimestamp("upDatedt"),
								rs.getString("upDateuserid"),
								rs.getString("deleteflag")));
			}
		}catch(SQLException e) {
			throw new IOException(e);
		}
	}catch(SQLException e) {
		throw new IOException(e);
	}
	return company;
   }


	/**
	 * 求人データを更新する
	 * @param kyujin 求人データ
	 * @return 更新件数
	 * @throws IOException
	 */
	public int update(Kyujin kyujin) throws IOException {
		int count = 0;

		// SQL文を生成する(求人票④)
		StringBuffer sql = new StringBuffer();
		sql.append("update kyujin set");
		sql.append(" no = ?");
		sql.append(", companyno = ?");
		sql.append(", companykana = ?");
		sql.append(", postal = ?");
		sql.append(", address = ?");
		sql.append(", nearline = ?");
		sql.append(", nearstation = ?");
		sql.append(", addresscd = ?");
		sql.append(", jobsmallcd１ = ?");
		sql.append(", jobsmallcd２ = ?");
		sql.append(", jobsmallcd３ = ?");
		sql.append(", joblargecd1 = ?");
		sql.append(", joblargecd2 = ?");
		sql.append(", joblargecd3 = ?");
		sql.append(", job = ?");
		sql.append(", detail = ?");
		sql.append(", koyoukeitaicd = ?");
		sql.append(", hakencd = ?");
		sql.append(", koyoukikan = ?");
		sql.append(", koyoukikankaishi = ?");
		sql.append(", koyoukikanowari = ?");
		sql.append(", education = ?");
		sql.append(", experience = ?");
		sql.append(", license = ?");
		sql.append(", agemin = ?");
		sql.append(", agemax = ?");
		sql.append(", salaryformcd = ?");
		sql.append(", salarymin = ?");
		sql.append(", salarymax = ?");
		sql.append(", bonus = ?");
		sql.append(", koutuhi = ?");
		sql.append(", teate = ?");
		sql.append(", begintime = ?");
		sql.append(", endtime = ?");
		sql.append(", shift = ?");
		sql.append(", flex = ?");
		sql.append(", jitan = ?");
		sql.append(", jikangai = ?");
		sql.append(", siyoukikan = ?");
		sql.append(", workdays = ?");
		sql.append(", nenkanholiday = ?");
		sql.append(", applicationform = ?");
		sql.append(", background = ?");
		sql.append(", bosyunumbers = ?");
		sql.append(", hiddensex = ?");
		sql.append(", hiddenagemin = ?");
		sql.append(", hiddenagemax = ?");
		sql.append(", hiddenetc = ?");
		sql.append(", receptiondt = ?");
		sql.append(", perioddt = ?");
		//sql.append(" createedt = ?");
		//sql.append(" createuserid = ?");
		//sql.append(" updatedt = ?");
		sql.append(", updateuserid = ?");
		// sql.append(" deleteflag = ?");
		sql.append(" where");
		sql.append(" no = ?");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setString(1, kyujin.getNo());
			ps.setString(2, kyujin.getCompanyno());
			ps.setString(3, kyujin.getCompanykana());
			ps.setString(4, kyujin.getPostal());
			ps.setString(5, kyujin.getAddress());
			ps.setString(6, kyujin.getNearline());//沿線
			ps.setString(7, kyujin.getNearstation());
			ps.setString(8, kyujin.getAddresscd());
			ps.setString(9, kyujin.getJobsmallcd1());
			ps.setString(10, kyujin.getJobsmallcd2());
			ps.setString(11, kyujin.getJobsmallcd3());
			ps.setString(12, kyujin.getJoblargecd1());
			ps.setString(13, kyujin.getJoblargecd2());
			ps.setString(14, kyujin.getJoblargecd3());
			ps.setString(15, kyujin.getJob());
			ps.setString(16, kyujin.getDetail());
			ps.setString(17, kyujin.getKoyoukeitaicd());
			ps.setString(18, kyujin.getHakencd());
			ps.setString(19, kyujin.getKoyoukikan());
			ps.setDate(20, DataCommons.convertToSqlDate(kyujin.getKoyoukikankaishi()));
			ps.setDate(21, DataCommons.convertToSqlDate(kyujin.getKoyoukikanowari()));
			ps.setString(22, kyujin.getEducation());
			ps.setString(23, kyujin.getExperience());
			ps.setString(24, kyujin.getLicense());
			ps.setInt(25, kyujin.getAgemin());
			ps.setInt(26, kyujin.getAgemax());
			ps.setString(27, kyujin.getSalaryformcd());
			ps.setInt(28, kyujin.getSalarymin());
			ps.setInt(29, kyujin.getSalarymax());
			ps.setString(30, kyujin.getBonus());//賞与
			ps.setString(31, kyujin.getKoutuhi());//通勤手当
			ps.setString(32, kyujin.getTeate());//他諸手当
			ps.setInt(33, kyujin.getBegintime());
			ps.setInt(34, kyujin.getEndtime());
			ps.setString(35, kyujin.getShift());//シフト制
			ps.setString(36, kyujin.getFlex());//フレックスタイム
			ps.setString(37, kyujin.getJitan());//時短勤務
			ps.setInt(38, kyujin.getJikangai());//時間外平均
			ps.setInt(39, kyujin.getSiyoukikan());//試用期間
			ps.setInt(40, kyujin.getWorkdays());//週所定労働日数
			ps.setString(41, kyujin.getNenkanholiday());//年間休日
			ps.setString(42, kyujin.getApplicationform());
			ps.setString(43, kyujin.getBackground());
			ps.setString(44, kyujin.getBosyunumbers());
			ps.setString(45, kyujin.getHiddensex());
			ps.setInt(46, kyujin.getHiddenagemin());
			ps.setInt(47, kyujin.getHiddenagemax());
			ps.setString(48, kyujin.getHiddenetc());
			ps.setDate(49, DataCommons.convertToSqlDate(kyujin.getReceptiondt()));
			ps.setDate(50, DataCommons.convertToSqlDate(kyujin.getPerioddt()));
          //ps.setString(51, "now()");//createdt???
			ps.setString(51, kyujin.getCreateuserid());
		  //ps.setString(52, "now()");//updatedt???
			ps.setString(52,  kyujin.getUpdateuserid());
			ps.setString(53, "0");//deleteflag???

			// SQL文を実行する(求人票)
			count = ps.executeUpdate();
		} catch (SQLException e) {
			throw new IOException(e);
		}

		return count;
	}

	/**
	 * 企業データを更新する
	 * @param company 企業データ
	 * @return 更新件数
	 * @throws IOException
	 */
	public int update(Company company) throws IOException{
		int count = 0;

		// SQL文を生成する(企業４)
		StringBuffer sql = new StringBuffer();
		sql.append("update compnay set");
		sql.append(" corporatenumber = ?");
		sql.append(", companyno = ?");
		sql.append(", companyname = ?");
		sql.append(", companykana = ?");
		sql.append(", companypostal = ?");
		sql.append(", companyplace = ?");
		sql.append(", nearstation = ?");
		sql.append(", companyur = ?");
		sql.append(", jobcategorysmallcd = ?");
		sql.append(", jobcatagorylargecd = ?");
		sql.append(", capital = ?");
		sql.append(", employees = ?");
		sql.append(", establishdt = ?");
		sql.append(", tantouyakusyoku = ?");
		sql.append(", tantou = ?");
		sql.append(", tantoukana = ?");
		sql.append(", tantoutel = ?");
		sql.append(", tantoufax = ?");
		sql.append(", tantoumail = ?");
		sql.append(", tantounote = ?");
		sql.append(", tantoustaff_id = ?");
		sql.append(", salesrank = ?");
		sql.append(", salesnote = ?");
		//sql.append(" createedt = ?");
		//sql.append(" createuserid = ?");
		//sql.append(" updatedt = ?");
		sql.append(", updateuserid = ?");
		// sql.append(" deleteflag = ?");
		sql.append("where");
		sql.append(" no = ?");
	/**
	 * 求人データに削除フラグを立てる
	 * @param kyujin 求人データ
	 * @return 更新件数
	 * @throws IOException
	 */
	public int delete(String no, String staffid) throws IOException {
		int count = 0;

		// SQL文を生成する(求人票⑤)
		StringBuffer sql = new StringBuffer();
		sql.append("update kyujin set");
		sql.append(" updateuserid = ?");
		sql.append(", deleteflag = ?");
		sql.append(" where");
		sql.append(" no = ?");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setString(1, staffid);
			ps.setString(2, "1");
			ps.setString(3, no);

			// SQL文を実行する
			count = ps.executeUpdate();
		} catch (SQLException e) {
			throw new IOException(e);
		}

		return count;
	}

	/**
	 * 企業データに削除フラグを立てる
	 * @param company 企業データ
	 * @return 更新件数
	 * @throws IOException
	 */
	public int delete(String companyno, String staffid) throws IOException {
		int count = 0;

		// SQL文を生成する(企業５)
		StringBuffer sql = new StringBuffer();
		sql.append("update company set");
		sql.append(" updateuserid = ?");
		sql.append(", deleteflag = ?");
		sql.append(" where");
		sql.append(" no = ?");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setString(1, staffid);
			ps.setString(2, "1");
			ps.setString(3, no);

			// SQL文を実行する(企業)
			count = ps.executeUpdate();
		} catch (SQLException e) {
			throw new IOException(e);
		}

		return count;

	}

}

