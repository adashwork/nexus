package jp.or.adash.nexus.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.or.adash.nexus.entity.JobSeeker;
import jp.or.adash.nexus.entity.JobSeekerMain;
import jp.or.adash.nexus.utils.common.DataCommons;
import jp.or.adash.nexus.utils.dao.Transaction;

/**
 * 求職者情報を取り扱うDAO
 * @author Y.Okamura & T.Uchi
 */
public class JobSeekerDao {

	/**
	 * データベース接続オブジェクト
	 */
	private Connection conn;

	/**
	 * コンストラクタ
	 * @param transaction トランザクションオブジェクト
	 */
	public JobSeekerDao(Transaction transaction) {
		this.conn = transaction.getConnection();
	}

	/**
	 * 求職者情報を登録する
	 * @param seeker 登録する求職者の情報
	 * @return 登録件数
	 * @throws IOException
	 * @author Y.Okamura & T.Uchi
	 */
	public int insert(JobSeeker seeker) throws IOException {
		int count = 0;

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("insert into jobseeker(");
		sql.append("id, name, kana, birthdt, sex, age, postal,");
		sql.append("address, nearstation, phone, mobile, partner, huyou, education,");
		sql.append("hopejob1, hopejob2, hopejob3, hopejobcategory, hopeworkplace,");
		sql.append("hopekoyoukeitai, hopeworkingdate, hopebegintime, hopeendtime,");
		sql.append("hopesalary, hopejikyu, hopeetc, driverlicense,licenseetc,");
		sql.append("pasokonskill,caution, tantoustaffid, password,");
		sql.append("createuserid, updateuserid, deleteflag");
		sql.append(") values (");
		sql.append("?, ?, ?, ?, ?, ?, ?,");
		sql.append("?, ?, ?, ?, ?, ?, ?,");
		sql.append("?, ?, ?, ?, ?,");
		sql.append("?, ?, ?, ?,");
		sql.append("?, ?, ?, ?, ?,");
		sql.append("?, ?, ?, ?,");
		sql.append("? ,?, 0");
		sql.append(")");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setString(1, seeker.getId());
			ps.setString(2, seeker.getName());
			ps.setString(3, seeker.getKana());
			ps.setDate(4, DataCommons.convertToSqlDate(seeker.getBirthdt()));
			ps.setString(5, seeker.getSex());
			ps.setInt(6, seeker.getAge());
			ps.setString(7, seeker.getPostal());
			ps.setString(8, seeker.getAddress());
			ps.setString(9, seeker.getNearstation());
			ps.setString(10, seeker.getPhone());
			ps.setString(11, seeker.getMobile());
			ps.setString(12, seeker.getPartner());
			ps.setInt(13, seeker.getHuyou());
			ps.setString(14, seeker.getEducation());
			ps.setString(15, seeker.getHopejob1());
			ps.setString(16, seeker.getHopejob2());
			ps.setString(17, seeker.getHopejob3());
			ps.setString(18, seeker.getHopejobcategory());
			ps.setString(19, seeker.getHopeworkplace());
			ps.setString(20, seeker.getHopekoyoukeitai());
			if (seeker.getHopeworkingDate() != null) {
                ps.setInt(21, seeker.getHopeworkingDate());
            } else {
                ps.setNull(21, java.sql.Types.INTEGER);
            }
			if (seeker.getHopebegintime() != null) {
                ps.setInt(22, seeker.getHopebegintime());
            } else {
                ps.setNull(22, java.sql.Types.INTEGER);
            }
			if (seeker.getHopeendtime() != null) {
                ps.setInt(23, seeker.getHopeendtime());
            } else {
                ps.setNull(23, java.sql.Types.INTEGER);
            }
			if (seeker.getHopesalary() != null) {
                ps.setInt(24, seeker.getHopesalary());
            } else {
                ps.setNull(24, java.sql.Types.INTEGER);
            }
			if (seeker.getHopejikyu() != null) {
                ps.setInt(25, seeker.getHopejikyu());
            } else {
                ps.setNull(25, java.sql.Types.INTEGER);
            }
			//ps.setInt(21, seeker.getHopeworkingDate());
//			ps.setInt(22, seeker.getHopebegintime());
//			ps.setInt(23, seeker.getHopeendtime());
//			ps.setInt(24, seeker.getHopesalary());
//			ps.setInt(25, seeker.getHopejikyu());
			ps.setString(26, seeker.getHopeetc());
			ps.setString(27, seeker.getDriverlicense());
			ps.setString(28, seeker.getLicenseetc());
			ps.setString(29, seeker.getPasokonskill());
			ps.setString(30, seeker.getCaution());
			ps.setString(31, seeker.getTantoustaffid());
			ps.setString(32, seeker.getPassword());
			//			ps.setDate(32, (Date) seeker.getCreatedt());
			ps.setString(33, seeker.getCreateuserid());
			//			ps.setDate(34, (Date) seeker.getUpDatedt());
			ps.setString(34, seeker.getUpDateuserid());
			//			ps.setString(34, seeker.getDeleteflag());

			// SQL文を実行する
			count = ps.executeUpdate();
		} catch (SQLException e) {
			throw new IOException(e);
		}

		return count;
	}

	/**
	 * 求職者IDを元に、求人情報（1件）を取得する
	 * @param id 求職者ID
	 * @return 求職者オブジェクト
	 * @throws IOException
	 * @author Y.Okamura & T.Uchi
	 */
	public JobSeeker selectJobSeeker(String id) throws IOException {
		JobSeeker seeker = null;

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("select *");
		sql.append(" from jobseeker");
		sql.append(" where id = ?");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setString(1, id);

			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				// 取得結果をリストに格納する
				while (rs.next()) {
					return new JobSeeker(
							rs.getString("id"),
							rs.getString("name"),
							rs.getString("kana"),
							rs.getDate("birthdt"),
							rs.getString("sex"),
							rs.getInt("age"),
							rs.getString("postal"),
							rs.getString("address"),
							rs.getString("nearstation"),
							rs.getString("phone"),
							rs.getString("mobile"),
							rs.getString("partner"),
							rs.getInt("huyou"),
							rs.getString("education"),
							rs.getString("hopejob1"),
							rs.getString("hopejob2"),
							rs.getString("hopejob3"),
							rs.getString("hopejobcategory"),
							rs.getString("hopeworkplace"),
							rs.getString("hopekoyoukeitai"),
							rs.getInt("hopeworkingdate"),
							rs.getInt("hopebegintime"),
							rs.getInt("hopeendtime"),
							rs.getInt("hopesalary"),
							rs.getInt("hopejikyu"),
							rs.getString("hopeetc"),
							rs.getString("driverlicense"),
							rs.getString("licenseetc"),
							rs.getString("pasokonskill"),
							rs.getString("caution"),
							null,
							rs.getString("tantoustaffid"),
							rs.getString("password"),
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

		return seeker;
	}

	/**
	 * 求職者の一覧を取得する
	 * @return 求職者リスト
	 * @throws IOException
	 * @author Y.Okamura & T.Uchi
	 */
	public List<JobSeeker> selectJobSeekerList() throws IOException {
		List<JobSeeker> jobseekers = new ArrayList<JobSeeker>();

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("select *");
		sql.append(" from jobseeker");
		sql.append(" order by id");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				// 取得結果をリストに格納する
				while (rs.next()) {
					jobseekers.add(new JobSeeker(
							rs.getString("id"),
							rs.getString("name"),
							rs.getString("kana"),
							rs.getDate("birthdt"),
							rs.getString("sex"),
							rs.getInt("age"),
							rs.getString("postal"),
							rs.getString("address"),
							rs.getString("nearstation"),
							rs.getString("phone"),
							rs.getString("mobile"),
							rs.getString("partner"),
							rs.getInt("huyou"),
							rs.getString("education"),
							rs.getString("hopejob1"),
							rs.getString("hopejob2"),
							rs.getString("hopejob3"),
							rs.getString("hopejobcategory"),
							rs.getString("hopeworkplace"),
							rs.getString("hopekoyoukeitai"),
							rs.getInt("hopeworkingdate"),
							rs.getInt("hopebegintime"),
							rs.getInt("hopeendtime"),
							rs.getInt("hopesalary"),
							rs.getInt("hopejikyu"),
							rs.getString("hopeetc"),
							rs.getString("driverlicense"),
							rs.getString("licenseetc"),
							rs.getString("pasokonskill"),
							rs.getString("caution"),
							null,
							rs.getString("tantoustaffid"),
							rs.getString("password"),
							rs.getTimestamp("createdt"),
							rs.getString("createuserid"),
							rs.getTimestamp("updatedt"),
							rs.getString("updateuserid"),
							rs.getString("deleteflag")));
				}
			} catch (SQLException e) {
				throw new IOException(e);
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}

		return jobseekers;
	}

	/**
	 * 求職者を更新する
	 * @param seeker 求職者
	 * @return 更新件数
	 * @throws IOException
	 * @author Y.Okamura & T.Uchi
	 */
	public int update(JobSeekerMain seeker) throws IOException {
		int count = 0;

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("update jobseeker set");
		sql.append(" name = ?,");
		sql.append(" kana = ?,");
		sql.append(" birthdt = ?,");
		sql.append(" sex = ?,");
		sql.append(" age = ?,");
		sql.append(" postal = ?,");
		sql.append(" address = ?,");
		sql.append(" nearstation = ?,");
		sql.append(" phone = ?,");
		sql.append(" mobile = ?,");
		sql.append(" partner = ?,");
		sql.append(" huyou = ?,");
		sql.append(" education = ?,");
		sql.append(" hopejob1 = ?,");
		sql.append(" hopejob2 = ?,");
		sql.append(" hopejob3 = ?,");
		sql.append(" hopejobcategory = ?,");
		sql.append(" hopeworkplace = ?,");
		sql.append(" hopekoyoukeitai = ?,");
		sql.append(" hopeworkingDate = ?,");
		sql.append(" hopebegintime = ?,");
		sql.append(" hopeendtime = ?,");
		sql.append(" hopesalary = ?,");
		sql.append(" hopejikyu = ?,");
		sql.append(" hopeetc = ?,");
		sql.append(" driverlicense = ?,");
		sql.append(" licenseetc = ?,");
		sql.append(" pasokonskill = ?,");
		sql.append(" caution = ?,");
		sql.append(" tantoustaffid = ?,");
		sql.append(" password = ?");
		sql.append(" where");
		sql.append(" id = ?");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setString(1, seeker.getName());
			ps.setString(2, seeker.getKana());
			ps.setDate(3, DataCommons.convertToSqlDate(seeker.getBirthdt()));
			ps.setString(4, seeker.getSex());
			ps.setInt(5, seeker.getAge());
			ps.setString(6, seeker.getPostal());
			ps.setString(7, seeker.getAddress());
			ps.setString(8, seeker.getNearstation());
			ps.setString(9, seeker.getPhone());
			ps.setString(10, seeker.getMobile());
			ps.setString(11, seeker.getPartner());
			ps.setInt(12, seeker.getHuyou());
			ps.setString(13, seeker.getEducation());
			ps.setString(14, seeker.getHopejob1());
			ps.setString(15, seeker.getHopejob2());
			ps.setString(16, seeker.getHopejob3());
			ps.setString(17, seeker.getHopejobcategory());
			ps.setString(18, seeker.getHopeworkplace());
			ps.setString(19, seeker.getHopekoyoukeitai());
			ps.setInt(20, seeker.getHopeworkingDate());
			ps.setInt(21, seeker.getHopebegintime());
			ps.setInt(22, seeker.getHopeendtime());
			ps.setInt(23, seeker.getHopesalary());
			ps.setInt(24, seeker.getHopejikyu());
			ps.setString(25, seeker.getHopeetc());
			ps.setString(26, seeker.getDriverlicense());
			ps.setString(27, seeker.getLicenseetc());
			ps.setString(28, seeker.getPasokonskill());
			ps.setString(29, seeker.getCaution());
			ps.setString(30, seeker.getTantoustaffid());
			ps.setString(31, seeker.getPassword());
			ps.setString(32, seeker.getId());

			// SQL文を実行する
			count = ps.executeUpdate();
		} catch (SQLException e) {
			throw new IOException(e);
		}

		return count;
	}
}
