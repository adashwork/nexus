package jp.or.adash.nexus.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	public int insertKyujin(Kyujin kyujin) throws IOException {
		int count = 0;

		// SQL文を生成する(求人票①)
		StringBuffer sql = new StringBuffer();
		 sql.append("insert into kyujin(");
			sql.append("no, companyno, postal, address, nearline, nearstationkj, addresscd,");
	        sql.append("jobsmallcd1, jobsmallcd2, jobsmallcd3, joblargecd1, joblargecd2, joblargecd3,");
	        sql.append("job, detail, koyoukeitaicd, hakencd, koyoukikan, koyoukikankaishi, koyoukikanowari,");
	        sql.append("education, experience, license, agemin, agemax, salaryformcd, salarymin, salarymax, bouns, koutuhi, teate,");
	        sql.append("begintime, endtime, shift, flex, jitan, jikangai, siyoukikan, workdays, nenkanholiday,");
	        sql.append("applicationform, background, bosyunumbers,hiddensex, hiddenagemin, hiddenagemax, hiddenetc,");
	        sql.append("receptiondt, perioddt, createuserid, updateuserid, deleteflag,");
	        sql.append(") values (");
	        sql.append("?, ?, ?, ?, ?, ?, ?, ");
	        sql.append("?, ?, ?, ?, ?, ?, ");
	        sql.append("?, ?, ?, ?, ?, ?, ?, ");
	        sql.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
	        sql.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ");
	        sql.append("?, ?, ?, ?, ?, ?, ?, ");
	        sql.append("?, ?, ?, ?, ?, ");
	        sql.append(")");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setString(1, kyujin.getNo());
			ps.setString(2, kyujin.getCompanyno());
			ps.setString(3, kyujin.getPostal());
			ps.setString(4, kyujin.getAddress());
			ps.setString(5, kyujin.getNearline());//沿線
			ps.setString(6, kyujin.getNearstationKyujin());
			ps.setString(7, kyujin.getAddresscd());
			ps.setString(8, kyujin.getJobsmallcd1());
			ps.setString(9, kyujin.getJobsmallcd2());
			ps.setString(10, kyujin.getJobsmallcd3());
			ps.setString(11, kyujin.getJoblargecd1());
			ps.setString(12, kyujin.getJoblargecd2());
			ps.setString(13, kyujin.getJoblargecd3());
			ps.setString(14, kyujin.getJob());
			ps.setString(15, kyujin.getDetail());
			ps.setString(16, kyujin.getKoyoukeitaicd());
			ps.setString(17, kyujin.getHakencd());
			ps.setString(18, kyujin.getKoyoukikan());
			ps.setDate(19, DataCommons.convertToSqlDate(kyujin.getKoyoukikankaishi()));
			ps.setDate(20, DataCommons.convertToSqlDate(kyujin.getKoyoukikanowari()));
			ps.setString(21, kyujin.getEducation());
			ps.setString(22, kyujin.getExperience());
			ps.setString(23, kyujin.getLicense());
			ps.setInt(24, kyujin.getAgemin());
			ps.setInt(25, kyujin.getAgemax());
			ps.setString(26, kyujin.getSalaryformcd());
			ps.setInt(27, kyujin.getSalarymin());
			ps.setInt(28, kyujin.getSalarymax());
			ps.setString(29, kyujin.getBonus());//賞与
			ps.setString(30, kyujin.getKoutuhi());//通勤手当
			ps.setString(31, kyujin.getTeate());//他諸手当
			ps.setInt(32, kyujin.getBegintime());
			ps.setInt(33, kyujin.getEndtime());
			ps.setString(34, kyujin.getShift());//シフト制
			ps.setString(35, kyujin.getFlex());//フレックスタイム
			ps.setString(36, kyujin.getJitan());//時短勤務
			ps.setInt(37, kyujin.getJikangai());//時間外平均
			ps.setInt(38, kyujin.getSiyoukikan());//試用期間
			ps.setInt(39, kyujin.getWorkdays());//週所定労働日数
			ps.setString(40, kyujin.getNenkanholiday());//年間休日
			ps.setString(41, kyujin.getApplicationform());
			ps.setString(42, kyujin.getBackground());
			ps.setString(43, kyujin.getBosyunumbers());
			ps.setString(44, kyujin.getHiddensex());
			ps.setInt(45, kyujin.getHiddenagemin());
			ps.setInt(46, kyujin.getHiddenagemax());
			ps.setString(47, kyujin.getHiddenetc());
			ps.setDate(48, DataCommons.convertToSqlDate(kyujin.getReceptiondt()));
			ps.setDate(49, DataCommons.convertToSqlDate(kyujin.getPerioddt()));
          //ps.setString(51, "now()");//createdt???
			ps.setString(50, kyujin.getCreateuserid());
		  //ps.setString(52, "now()");//updatedt???
			ps.setString(51, kyujin.getUpdateuserid());
			ps.setString(52, "0");//deleteflag???

			// SQL文を実行する(求人票)
			count = ps.executeUpdate();
		} catch (SQLException e) {
			throw new IOException(e);
		}

		return count;
	}


    /**
	 * 求人票コードを元に、求人情報（1件）を取得する
	 * @param no 求人票番号
	 * @return 求人オブジェクト
	 * @throws IOException
	 */
	public Kyujin selectKyujin(String no) throws IOException {
		Kyujin kyujin = null;

		// SQL文を生成する(求人票②)
		StringBuffer sql = new StringBuffer();
		sql.append("insert into kyujin(");
		sql.append("no, companyno, postal, address, nearline, nearstationkj, addresscd,");
        sql.append("jobsmallcd1, jobsmallcd2, jobsmallcd3, joblargecd1, joblargecd2, joblargecd3,");
        sql.append("job, detail, koyoukeitaicd, hakencd, koyoukikan, koyoukikankaishi, koyoukikanowari,");
        sql.append("education, experience, license, agemin, agemax, salaryformcd, salarymin, salarymax, bouns, koutuhi, teate,");
        sql.append("begintime, endtime, shift, flex, jitan, jikangai, siyoukikan, workdays, nenkanholiday,");
        sql.append("applicationform, background, bosyunumbers,hiddensex, hiddenagemin, hiddenagemax, hiddenetc,");
        sql.append("receptiondt, perioddt, createuserid, updateuserid, deleteflag,");
        sql.append(") values (");
        sql.append("?, ?, ?, ?, ?, ?, ?, ");
        sql.append("?, ?, ?, ?, ?, ?, ");
        sql.append("?, ?, ?, ?, ?, ?, ?, ");
        sql.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
        sql.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ");
        sql.append("?, ?, ?, ?, ?, ?, ?, ");
        sql.append("?, ?, ?, ?, ?, ");
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
							rs.getString("postal"),
							rs.getString("address"),
							rs.getString("nearline"),
							rs.getString("nearstationkj"),
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
	 * 求人票の一覧を取得する
	 * @return 求人票リスト
	 * @throws IOException
	 */
	public List<Kyujin> selectKyujinList() throws IOException {
		List<Kyujin> kyujins = new ArrayList<Kyujin>();

		// SQL文を生成する(求人票③)
		StringBuffer sql = new StringBuffer();
		sql.append("insert into kyujin(");
		sql.append("no, companyno, postal, address, nearline, nearstationkj, addresscd,");
        sql.append("jobsmallcd1, jobsmallcd2, jobsmallcd3, joblargecd1, joblargecd2, joblargecd3,");
        sql.append("job, detail, koyoukeitaicd, hakencd, koyoukikan, koyoukikankaishi, koyoukikanowari,");
        sql.append("education, experience, license, agemin, agemax, salaryformcd, salarymin, salarymax, bouns, koutuhi, teate,");
        sql.append("begintime, endtime, shift, flex, jitan, jikangai, siyoukikan, workdays, nenkanholiday,");
        sql.append("applicationform, background, bosyunumbers,hiddensex, hiddenagemin, hiddenagemax, hiddenetc,");
        sql.append("receptiondt, perioddt, createuserid, updateuserid, deleteflag,");
        sql.append(") values (");
        sql.append("?, ?, ?, ?, ?, ?, ?, ");
        sql.append("?, ?, ?, ?, ?, ?, ");
        sql.append("?, ?, ?, ?, ?, ?, ?, ");
        sql.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
        sql.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ");
        sql.append("?, ?, ?, ?, ?, ?, ?, ");
        sql.append("?, ?, ?, ?, ?, ");
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
									rs.getString("postal"),
									rs.getString("address"),
									rs.getString("nearline"),
									rs.getString("nearstationkj"),
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
		sql.append(", postal = ?");
		sql.append(", address = ?");
		sql.append(", nearline = ?");
		sql.append(", nearstationkj = ?");
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
			ps.setString(3, kyujin.getPostal());
			ps.setString(4, kyujin.getAddress());
			ps.setString(5, kyujin.getNearline());//沿線
			ps.setString(6, kyujin.getNearstationKyujin());
			ps.setString(7, kyujin.getAddresscd());
			ps.setString(8, kyujin.getJobsmallcd1());
			ps.setString(9, kyujin.getJobsmallcd2());
			ps.setString(10, kyujin.getJobsmallcd3());
			ps.setString(11, kyujin.getJoblargecd1());
			ps.setString(12, kyujin.getJoblargecd2());
			ps.setString(13, kyujin.getJoblargecd3());
			ps.setString(14, kyujin.getJob());
			ps.setString(15, kyujin.getDetail());
			ps.setString(16, kyujin.getKoyoukeitaicd());
			ps.setString(17, kyujin.getHakencd());
			ps.setString(18, kyujin.getKoyoukikan());
			ps.setDate(19, DataCommons.convertToSqlDate(kyujin.getKoyoukikankaishi()));
			ps.setDate(20, DataCommons.convertToSqlDate(kyujin.getKoyoukikanowari()));
			ps.setString(21, kyujin.getEducation());
			ps.setString(22, kyujin.getExperience());
			ps.setString(23, kyujin.getLicense());
			ps.setInt(24, kyujin.getAgemin());
			ps.setInt(25, kyujin.getAgemax());
			ps.setString(26, kyujin.getSalaryformcd());
			ps.setInt(27, kyujin.getSalarymin());
			ps.setInt(28, kyujin.getSalarymax());
			ps.setString(29, kyujin.getBonus());//賞与
			ps.setString(30, kyujin.getKoutuhi());//通勤手当
			ps.setString(31, kyujin.getTeate());//他諸手当
			ps.setInt(32, kyujin.getBegintime());
			ps.setInt(33, kyujin.getEndtime());
			ps.setString(34, kyujin.getShift());//シフト制
			ps.setString(35, kyujin.getFlex());//フレックスタイム
			ps.setString(36, kyujin.getJitan());//時短勤務
			ps.setInt(37, kyujin.getJikangai());//時間外平均
			ps.setInt(38, kyujin.getSiyoukikan());//試用期間
			ps.setInt(39, kyujin.getWorkdays());//週所定労働日数
			ps.setString(40, kyujin.getNenkanholiday());//年間休日
			ps.setString(41, kyujin.getApplicationform());
			ps.setString(42, kyujin.getBackground());
			ps.setString(43, kyujin.getBosyunumbers());
			ps.setString(44, kyujin.getHiddensex());
			ps.setInt(45, kyujin.getHiddenagemin());
			ps.setInt(46, kyujin.getHiddenagemax());
			ps.setString(47, kyujin.getHiddenetc());
			ps.setDate(48, DataCommons.convertToSqlDate(kyujin.getReceptiondt()));
			ps.setDate(49, DataCommons.convertToSqlDate(kyujin.getPerioddt()));
          //ps.setString(51, "now()");//createdt???
			ps.setString(50, kyujin.getCreateuserid());
		  //ps.setString(52, "now()");//updatedt???
			ps.setString(51, kyujin.getUpdateuserid());
			ps.setString(52, "0");//deleteflag???
			// SQL文を実行する(求人票)
			count = ps.executeUpdate();
		} catch (SQLException e) {
			throw new IOException(e);
		}

		return count;
	}


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
}
