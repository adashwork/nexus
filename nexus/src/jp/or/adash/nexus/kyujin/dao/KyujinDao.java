package jp.or.adash.nexus.kyujin.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jp.or.adash.nexus.entity.Kyujin;
import jp.or.adash.nexus.utils.dao.Transaction;


/**
 * 求人データアクセスクラス
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

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("insert into item(");
		sql.append(
				"no, receptiondt, perioddt, companyno, addresscd, jobsmallcd１, jobsmallcd２, jobsmallcd３, joblargecd１, joblargecd２, joblargecd３, jobcategorysmallcd, jobcategorylargecd, companykana, companyname, companypostal, companyplace, companyurl, postal, address, nearstation, job, hakencd, detail, koyoukeitaicd, koyoukikan, koyoukikankaishi, koyoukikanowari, education, experience, license, agemin, agemax, salarymin, salarymax, salaryformcd, begintime, endtime, establishdt, capital, companyfeature, tantouyakushoku, tantoukana, tantou, tantoustaff_id, applicationform, background, hiddensex, hiddenagemin, hiddenagemax, hiddenetc, createdt, createuserid, updatedt, updateuserid, deleteflag");
		sql.append(") values (");
		sql.append(
				"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, current_timestamp, ?, current_timestamp, ?, ?");
		sql.append(")");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setString(1, kyujin.getNo());
			ps.setDate(2, (Date) kyujin.getReceptiondt());
			ps.setDate(3, (Date) kyujin.getPerioddt());
			ps.setString(4, kyujin.getCompanyno());
			ps.setString(5, kyujin.getAddresscd());
			ps.setString(6, kyujin.getJobsmallcd1());
			ps.setString(7, kyujin.getJobsmallcd2());
			ps.setString(8, kyujin.getJobsmallcd3());
			ps.setString(9, kyujin.getJoblargecd1());
			ps.setString(10, kyujin.getJoblargecd2());
			ps.setString(11, kyujin.getJoblargecd3());
			ps.setString(12, kyujin.getJobcategorysmallcd());
			ps.setString(13, kyujin.getJobcategorylargecd());
			ps.setString(14, kyujin.getCompanykana());
			ps.setString(15, kyujin.getCompanyname());
			ps.setString(16, kyujin.getCompanypostal());
			ps.setString(17, kyujin.getCompanyplace());
			ps.setString(18, kyujin.getCompanyurl());
			ps.setString(19, kyujin.getPostal());
			ps.setString(20, kyujin.getAddress());
			ps.setString(21, kyujin.getNearstation());
			ps.setString(22, kyujin.getJob());
			ps.setString(23, kyujin.getHakencd());
			ps.setString(24, kyujin.getDetail());
			ps.setString(25, kyujin.getKoyoukeitaicd());
			ps.setString(26, kyujin.getKoyoukikan());
			ps.setDate(27, (Date) kyujin.getKoyoukikankaishi());
			ps.setDate(28, (Date) kyujin.getKoyoukikanowari());
			ps.setString(29, kyujin.getEducation());
			ps.setString(30, kyujin.getExperience());
			ps.setString(31, kyujin.getLicense());
			ps.setInt(32, kyujin.getAgemin());
			ps.setInt(33, kyujin.getAgemax());
			ps.setInt(34, kyujin.getSalarymin());
			ps.setInt(35, kyujin.getSalarymax());
			ps.setString(36, kyujin.getSalaryformcd());
			ps.setInt(37, kyujin.getBegintime());
			ps.setInt(38, kyujin.getEndtime());
			ps.setInt(39, kyujin.getEstablishdt());
			ps.setLong(40, kyujin.getCapital());
			ps.setString(41, kyujin.getCompanyfeature());
			ps.setString(42, kyujin.getTantouyakushoku());
			ps.setString(43, kyujin.getTantoukana());
			ps.setString(44, kyujin.getTantou());
			ps.setString(45, kyujin.getTantoustaff_id());
			ps.setString(46, kyujin.getApplicationform());
			ps.setString(47, kyujin.getBackground());
			ps.setString(48, kyujin.getHiddensex());
			ps.setInt(49, kyujin.getHiddenagemin());
			ps.setInt(50, kyujin.getHiddenagemax());
			ps.setString(51, kyujin.getHiddenetc());
			//ps.setTimestamp(52, (Timestamp) kyujin.getCreatedt());
			ps.setString(52, kyujin.getCreateuserid());
			//ps.setTimestamp(54, (Timestamp) kyujin.getUpDatedt());
			ps.setString(53, kyujin.getUpDateuserid());
			ps.setString(54, kyujin.getDeleteflag());

			// SQL文を実行する
			count = ps.executeUpdate();
		} catch (SQLException e) {
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

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select no, receptiondt, perioddt, companyno, addresscd, jobsmallcd1, jobsmallcd2, jobsmallcd3, joblargecd1, joblargecd2, joblargecd3, jobcategorysmallcd, jobcategorylargecd, companykana, companyname, companypostal, companyplace, companyurl, postal, adress, nearstation, job, hakencd, detail, koyoukeitaicd, koyoukikan, koyoukikankaishi, koyoukikanowari, education, experience, license, agemin, agemax, salarymin, salarymax, salaryformcd, begintime, endtime, establishdt, capital, companyfeature, tantouyakushoku, tantoukana, tantou, tantoustaff_id, applicationform, background, hiddensex, hiddenagemin, hiddenagemax, hiddenetc, createdt, createuserid, updatedt, updateuserid, deleteflag");
		sql.append(" from kyujin");
		sql.append(" where no = ?");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setString(1, no);

			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				// 取得結果をリストに格納する
				while (rs.next()) {
					return new Kyujin(rs.getString("no"),
							rs.getDate("receptiondt"),
							rs.getDate("perioddt"),
							rs.getString("companyno"),
							rs.getString("addresscd"),
							rs.getString("jobsmallcd1"),
							rs.getString("jobsmallcd2"),
							rs.getString("jobsmallcd3"),
							rs.getString("joblargecd1"),
							rs.getString("joblargecd2"),
							rs.getString("joblargecd3"),
							rs.getString("jobcategorysmallcd"),
							rs.getString("jobcategorylargecd"),
							rs.getString("companykana"),
							rs.getString("companyname"),
							rs.getString("companypostal"),
							rs.getString("companyplace"),
							rs.getString("companyurl"),
							rs.getString("postal"),
							rs.getString("adress"),
							rs.getString("nearstation"),
							rs.getString("job"),
							rs.getString("hakencd"),
							rs.getString("detail"),
							rs.getString("koyoukeitaicd"),
							rs.getString("koyoukikan"),
							rs.getDate("koyoukikankaishi"),
							rs.getDate("koyoukikanowari"),
							rs.getString("education"),
							rs.getString("experience"),
							rs.getString("license"),
							rs.getInt("agemin"),
							rs.getInt("agemax"),
							rs.getInt("salarymin"),
							rs.getInt("salarymax"),
							rs.getString("salaryformcd"),
							rs.getInt("begintime"),
							rs.getInt("endtime"),
							rs.getInt("establishdt"),
							rs.getLong("capital"),
							rs.getString("companyfeature"),
							rs.getString("tantouyakushoku"),
							rs.getString("tantoukana"),
							rs.getString("tantou"),
							rs.getString("tantoustaff_id"),
							rs.getString("applicationform"),
							rs.getString("background"),
							rs.getString("hiddensex"),
							rs.getInt("hiddenagemin"),
							rs.getInt("hiddenagemax"),
							rs.getString("hiddenetc"),
							rs.getTimestamp("createdt"),
							rs.getString("createuserid"),
							rs.getTimestamp("upDatedt"),
							rs.getString("upDateuserid"),
							rs.getString("deleteflag"));
				}
			} catch (SQLException e) {
				throw new IOException(e);
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}

		return kyujin;
	}

	/**
	 * 求人票の一覧を取得する
	 * @return 求人票リスト
	 * @throws IOException
	 */
	public List<Kyujin> selectKyujinList() throws IOException {
		List<Kyujin> kyujins = new ArrayList<Kyujin>();

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select no, receptiondt, perioddt, companyno, addresscd, jobsmallcd1, jobsmallcd2, jobsmallcd3, joblargecd1, joblargecd2, joblargecd3, jobcategorysmallcd, jobcategorylargecd, companykana, companyname, companypostal, companyplace, companyurl, postal, adress, nearstation, job, hakencd, detail, koyoukeitaicd, koyoukikan, koyoukikankaishi, koyoukikanowari, education, experience, license, agemin, agemax, salarymin, salarymax, salaryformcd, begintime, endtime, establishdt, capital, companyfeature, tantouyakushoku, tantoukana, tantou, tantoustaff_id, applicationform, background, hiddensex, hiddenagemin, hiddenagemax, hiddenetc, createdt, createuserid, updatedt, updateuserid, deleteflag");
		sql.append(" from kyujin");
		sql.append(" order by no");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				// 取得結果をリストに格納する
				while (rs.next()) {
					kyujins.add(new Kyujin(rs.getString("no"),
							rs.getDate("receptiondt"),
							rs.getDate("perioddt"),
							rs.getString("companyno"),
							rs.getString("addresscd"),
							rs.getString("jobsmallcd1"),
							rs.getString("jobsmallcd2"),
							rs.getString("jobsmallcd3"),
							rs.getString("joblargecd1"),
							rs.getString("joblargecd2"),
							rs.getString("joblargecd3"),
							rs.getString("jobcategorysmallcd"),
							rs.getString("jobcategorylargecd"),
							rs.getString("companykana"),
							rs.getString("companyname"),
							rs.getString("companypostal"),
							rs.getString("companyplace"),
							rs.getString("companyurl"),
							rs.getString("postal"),
							rs.getString("adress"),
							rs.getString("nearstation"),
							rs.getString("job"),
							rs.getString("hakencd"),
							rs.getString("detail"),
							rs.getString("koyoukeitaicd"),
							rs.getString("koyoukikan"),
							rs.getDate("koyoukikankaishi"),
							rs.getDate("koyoukikanowari"),
							rs.getString("education"),
							rs.getString("experience"),
							rs.getString("license"),
							rs.getInt("agemin"),
							rs.getInt("agemax"),
							rs.getInt("salarymin"),
							rs.getInt("salarymax"),
							rs.getString("salaryformcd"),
							rs.getInt("begintime"),
							rs.getInt("endtime"),
							rs.getInt("establishdt"),
							rs.getLong("capital"),
							rs.getString("companyfeature"),
							rs.getString("tantouyakushoku"),
							rs.getString("tantoukana"),
							rs.getString("tantou"),
							rs.getString("tantoustaff_id"),
							rs.getString("applicationform"),
							rs.getString("background"),
							rs.getString("hiddensex"),
							rs.getInt("hiddenagemin"),
							rs.getInt("hiddenagemax"),
							rs.getString("hiddenetc"),
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
	 * 求人データを更新する
	 * @param kyujin 求人データ
	 * @return 更新件数
	 * @throws IOException
	 */
	public int update(Kyujin kyujin) throws IOException {
		int count = 0;

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("update item set");
		sql.append(" receptiondt = ?");
		sql.append(" perioddt = ?");
		sql.append(" companyno = ?");
		sql.append(" addresscd = ?");
		sql.append(" jobsmallcd1 = ?");
		sql.append(" jobsmallcd2 = ?");
		sql.append(" jobsmallcd3 = ?");
		sql.append(" joblargecd1 = ?");
		sql.append(" joblargecd2 = ?");
		sql.append(" joblargecd3 = ?");
		sql.append(" jobcategorysmallcd = ?");
		sql.append(" jobcategorylargecd = ?");
		sql.append(" companykana = ?");
		sql.append(" companyname = ?");
		sql.append(" companypostal = ?");
		sql.append(" companyplace = ?");
		sql.append(" companyurl = ?");
		sql.append(" postal = ?");
		sql.append(" adress = ?");
		sql.append(" nearstation = ?");
		sql.append(" job = ?");
		sql.append(" hakencd = ?");
		sql.append(" detail = ?");
		sql.append(" koyoukeitaicd = ?");
		sql.append(" koyoukikan = ?");
		sql.append(" koyoukikankaishi = ?");
		sql.append(" koyoukikanowari = ?");
		sql.append(" education = ?");
		sql.append(" experience = ?");
		sql.append(" license = ?");
		sql.append(" agemin = ?");
		sql.append(" agemax = ?");
		sql.append(" salarymin = ?");
		sql.append(" salarymax = ?");
		sql.append(" salaryformcd = ?");
		sql.append(" begintime = ?");
		sql.append(" endtime = ?");
		sql.append(" establishdt = ?");
		sql.append(" capital = ?");
		sql.append(" companyfeature = ?");
		sql.append(" tantouyakushoku = ?");
		sql.append(" tantoukana = ?");
		sql.append(" tantou = ?");
		sql.append(" tantoustaff_id = ?");
		sql.append(" applicationform = ?");
		sql.append(" background = ?");
		sql.append(" hiddensex = ?");
		sql.append(" hiddenagemin = ?");
		sql.append(" hiddenagemax = ?");
		sql.append(" hiddenetc = ?");
		sql.append(" current_timestamp");
		// sql.append(" createuserid = ?");
		sql.append(" current_timestamp");
		sql.append(" updatedt = ?");
		sql.append(" updateuserid = ?");
		// sql.append(" deleteflag = ?");
		sql.append(" where");
		sql.append(" no = ?");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setString(1, kyujin.getNo());
			ps.setDate(2, (Date) kyujin.getReceptiondt());
			ps.setDate(3, (Date) kyujin.getPerioddt());
			ps.setString(4, kyujin.getCompanyno());
			ps.setString(5, kyujin.getAddresscd());
			ps.setString(6, kyujin.getJobsmallcd1());
			ps.setString(7, kyujin.getJobsmallcd2());
			ps.setString(8, kyujin.getJobsmallcd3());
			ps.setString(9, kyujin.getJoblargecd1());
			ps.setString(10, kyujin.getJoblargecd2());
			ps.setString(11, kyujin.getJoblargecd3());
			ps.setString(12, kyujin.getJobcategorysmallcd());
			ps.setString(13, kyujin.getJobcategorylargecd());
			ps.setString(14, kyujin.getCompanykana());
			ps.setString(15, kyujin.getCompanyname());
			ps.setString(16, kyujin.getCompanypostal());
			ps.setString(17, kyujin.getCompanyplace());
			ps.setString(18, kyujin.getCompanyurl());
			ps.setString(19, kyujin.getPostal());
			ps.setString(20, kyujin.getAddress());
			ps.setString(21, kyujin.getNearstation());
			ps.setString(22, kyujin.getJob());
			ps.setString(23, kyujin.getHakencd());
			ps.setString(24, kyujin.getDetail());
			ps.setString(25, kyujin.getKoyoukeitaicd());
			ps.setString(26, kyujin.getKoyoukikan());
			ps.setDate(27, (Date) kyujin.getKoyoukikankaishi());
			ps.setDate(28, (Date) kyujin.getKoyoukikanowari());
			ps.setString(29, kyujin.getEducation());
			ps.setString(30, kyujin.getExperience());
			ps.setString(31, kyujin.getLicense());
			ps.setInt(32, kyujin.getAgemin());
			ps.setInt(33, kyujin.getAgemax());
			ps.setInt(34, kyujin.getSalarymin());
			ps.setInt(35, kyujin.getSalarymax());
			ps.setString(36, kyujin.getSalaryformcd());
			ps.setInt(37, kyujin.getBegintime());
			ps.setInt(38, kyujin.getEndtime());
			ps.setInt(39, kyujin.getEstablishdt());
			ps.setLong(40, kyujin.getCapital());
			ps.setString(41, kyujin.getCompanyfeature());
			ps.setString(42, kyujin.getTantouyakushoku());
			ps.setString(43, kyujin.getTantoukana());
			ps.setString(44, kyujin.getTantou());
			ps.setString(45, kyujin.getTantoustaff_id());
			ps.setString(46, kyujin.getApplicationform());
			ps.setString(47, kyujin.getBackground());
			ps.setString(48, kyujin.getHiddensex());
			ps.setInt(49, kyujin.getHiddenagemin());
			ps.setInt(50, kyujin.getHiddenagemax());
			ps.setString(51, kyujin.getHiddenetc());
			//ps.setTimestamp(52, (Timestamp) kyujin.getCreatedt());
			//ps.setString(52, kyujin.getCreateuserid());
			ps.setTimestamp(52, (Timestamp) kyujin.getUpDatedt());
			ps.setString(53, kyujin.getUpDateuserid());
			//ps.setString(54, kyujin.getDeleteflag());

			// SQL文を実行する
			count = ps.executeUpdate();
		} catch (SQLException e) {
			throw new IOException(e);
		}

		return count;
	}

	/**
	 * 求人データ二削除フラグを立てる
	 * @param kyujin 求人データ
	 * @return 更新件数
	 * @throws IOException
	 */
	public int delete(String no, String delflag) throws IOException {
		int count = 0;

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("update item set");
		sql.append(" deleteflag = ?");
		sql.append(" where");
		sql.append(" no = ?");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setString(2, no);
			ps.setString(1, delflag);

			// SQL文を実行する
			count = ps.executeUpdate();
		} catch (SQLException e) {
			throw new IOException(e);
		}

		return count;
	}
}
