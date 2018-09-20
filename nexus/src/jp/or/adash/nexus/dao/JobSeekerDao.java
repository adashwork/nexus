package jp.or.adash.nexus.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.or.adash.nexus.entity.JobSeeker;
import jp.or.adash.nexus.utils.dao.Transaction;



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
		sql.append("'1','1', 0");
		sql.append(")");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setString(1, seeker.getId());
			ps.setString(2, seeker.getName());
			ps.setString(3, seeker.getKana());
			ps.setDate(4, new java.sql.Date(seeker.getBirthdt().getTime()));
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
			ps.setInt(21, seeker.getHopeworkingDate());
			ps.setInt(22, seeker.getHopebegintime());
			ps.setInt(23, seeker.getHopeendtime());
			ps.setInt(24, seeker.getHopesalary());
			ps.setInt(25, seeker.getHopejikyu());
			ps.setString(26, seeker.getHopeetc());
			ps.setString(27, seeker.getDriverlicense());
			ps.setString(28, seeker.getLicenseetc());
			ps.setString(29, seeker.getPasokonskill());
			ps.setString(30, seeker.getCaution());
			ps.setString(31, seeker.getTantoustaffid());
			ps.setString(32, seeker.getPassword());
//			ps.setDate(32, (Date) seeker.getCreatedt());
//			ps.setString(33, seeker.getCreateuserid());
//			ps.setDate(34, (Date) seeker.getUpDatedt());
//			ps.setString(34, seeker.getUpDateuserid());
//			ps.setString(34, seeker.getDeleteflag());




			// SQL文を実行する
			count = ps.executeUpdate();
		} catch(SQLException e) {
			throw new IOException(e);
		}

		return count;
	}

	/**
	 * 求職者IDを元に、求人情報（1件）を取得する
	 * @param id 求職者ID
	 * @return 求職者オブジェクト
	 * @throws IOException
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
				while(rs.next()) {
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
							rs.getString("tantoustaffid"),
							rs.getString("password"),
							rs.getDate("createdt"),
							rs.getString("createuserid"),
							rs.getDate("updatedt"),
							rs.getString("updateuserid"),
							rs.getString("deleteflag")
							);

				}
			} catch(SQLException e) {
				throw new IOException(e);
			}
		} catch(SQLException e) {
			throw new IOException(e);
		}

		return seeker;
	}

	/**
	 * 求職者の一覧を取得する
	 * @return 求職者リスト
	 * @throws IOException
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
				while(rs.next()) {
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
							rs.getString("tantoustaffid"),
							rs.getString("password"),
							rs.getTimestamp("createdt"),
							rs.getString("createuserid"),
							rs.getTimestamp("updatedt"),
							rs.getString("updateuserid"),
							rs.getString("deleteflag")));
				}
			} catch(SQLException e) {
				throw new IOException(e);
			}
		} catch(SQLException e) {
			throw new IOException(e);
		}

		return jobseekers;
	}

	/**
	 * 求職者を更新する
	 * @param seeker 求職者
	 * @return 更新件数
	 * @throws IOException
	 */
	public int update(JobSeeker seeker) throws IOException {
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
		sql.append(" password = ?,");
		sql.append(" where");
		sql.append(" id = ?");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setString(2, seeker.getName());
			ps.setString(3, seeker.getKana());
			ps.setDate(4, new java.sql.Date(seeker.getBirthdt().getTime()));
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
			ps.setInt(21, seeker.getHopeworkingDate());
			ps.setInt(22, seeker.getHopebegintime());
			ps.setInt(23, seeker.getHopeendtime());
			ps.setInt(24, seeker.getHopesalary());
			ps.setInt(25, seeker.getHopejikyu());
			ps.setString(26, seeker.getHopeetc());
			ps.setString(27, seeker.getDriverlicense());
			ps.setString(28, seeker.getLicenseetc());
			ps.setString(29, seeker.getPasokonskill());
			ps.setString(30, seeker.getCaution());
			ps.setString(31, seeker.getTantoustaffid());
			ps.setString(32, seeker.getPassword());
			ps.setString(1, seeker.getId());

			// SQL文を実行する
			count = ps.executeUpdate();
		} catch(SQLException e) {
			throw new IOException(e);
		}

		return count;
	}
}
