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
		sql.append("id, name, kana, birthdt, sex, postal,");
		sql.append("address, seekermail, nearstation, phone, mobile, partner, huyou, education, career");
		sql.append("HOPEJOB1, HOPEJOB2, HOPEJOB3, HOPEJOBCATEGORY, HOPEJOBCATEGORY2, HOPEJOBCATEGORY3, hopeworkplace,");
		sql.append("hopekoyoukeitai, hopeweekday, hopeworkingdate, hopebegintime, hopeendtime,");
		sql.append("hopesalary, hopejikyu, hopeetc, driverlicense,licenseetc,");
		sql.append("pasokonskill,caution, tantoustaffid, password, note");
		sql.append("createdt, createuserid, updatedt, updateuserid, deleteflag");
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
//			ps.setInt(6, seeker.getAge());
			ps.setString(6, seeker.getPostal());
			ps.setString(7, seeker.getAddress());
			ps.setString(8, seeker.getSeekermail());
			ps.setString(9, seeker.getNearstation());
			ps.setString(10, seeker.getPhone());
			ps.setString(11, seeker.getMobile());
			ps.setString(12, seeker.getPartner());
			ps.setInt(13, seeker.getHuyou());
			ps.setString(14, seeker.getEducation());
			ps.setString(15, seeker.getCareer());
			ps.setString(16, seeker.getHOPEJOB1());
			ps.setString(17, seeker.getHOPEJOB2());
			ps.setString(18, seeker.getHOPEJOB3());
			ps.setString(19, seeker.getHOPEJOBCATEGORY());
			ps.setString(20, seeker.getHOPEJOBCATEGORY2());
			ps.setString(21, seeker.getHOPEJOBCATEGORY3());
			ps.setString(22, seeker.getHopeworkplace());
			ps.setString(23, seeker.getHopekoyoukeitai());
			if (seeker.getHopeworkingdate() != null) {
                ps.setInt(24, seeker.getHopeworkingdate());
            } else {
                ps.setNull(25, java.sql.Types.INTEGER);
            }
			if (seeker.getHopebegintime() != null) {
                ps.setInt(26, seeker.getHopebegintime());
            } else {
                ps.setNull(27, java.sql.Types.INTEGER);
            }
			if (seeker.getHopeendtime() != null) {
                ps.setInt(28, seeker.getHopeendtime());
            } else {
                ps.setNull(29, java.sql.Types.INTEGER);
            }
			if (seeker.getHopesalary() != null) {
                ps.setInt(30, seeker.getHopesalary());
            } else {
                ps.setNull(31, java.sql.Types.INTEGER);
            }
			if (seeker.getHopejikyu() != null) {
                ps.setInt(32, seeker.getHopejikyu());
            } else {
                ps.setNull(33, java.sql.Types.INTEGER);
            }
			//ps.setInt(21, seeker.getHopeworkingDate());
//			ps.setInt(22, seeker.getHopebegintime());
//			ps.setInt(23, seeker.getHopeendtime());
//			ps.setInt(24, seeker.getHopesalary());
//			ps.setInt(25, seeker.getHopejikyu());
			ps.setString(34, seeker.getHopeetc());
			ps.setString(35, seeker.getDriverlicense());
			ps.setString(36, seeker.getLicenseetc());
			ps.setString(37, seeker.getPasokonskill());
			ps.setString(38, seeker.getCaution());
			ps.setString(39, seeker.getTantoustaffid());
			ps.setString(40, seeker.getPassword());
		    ps.setString(41, seeker.getNote());
		    //ps.setDate(42, (Date) seeker.getCreatedt());
			ps.setString(42, seeker.getCreateuserid());
			//ps.setDate(44, (Date) seeker.getUpdatedt());
			ps.setString(43, seeker.getUpdateuserid());
			//ps.setString(34, seeker.getDeleteflag());

			// SQL文を実行する
			count = ps.executeUpdate();
		} catch (SQLException e) {
			throw new IOException(e);
		}

		return count;
	}


	/**
	 * 求職者の個人情報を登録する
	 * @param seeker 登録する求職者の情報
	 * @return 登録件数
	 * @throws IOException
	 * @author mmiyamoto
	 */
	public int insertPrivateData(JobSeeker seeker) throws IOException {
		int count = 0;

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("insert into jobseeker(");
		sql.append(" id, ");
		sql.append(" name, ");
		sql.append(" kana, ");
		sql.append(" address, ");
		sql.append(" seekermail, ");
		sql.append(" phone, ");
		sql.append(" mobile, ");
		sql.append(" createuserid ");
		sql.append(" updateuserid ");
		sql.append(" deleteflag ");

		sql.append(") values (");

		sql.append("?, ?, ?, ?, ?, ");
		sql.append("?, ?, ?, ?, ?, ");
		sql.append("0 ");
		sql.append(")");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setString(1, seeker.getId());
			ps.setString(2, seeker.getName());
			ps.setString(3, seeker.getKana());
			ps.setString(4, seeker.getAddress());
			ps.setString(5, seeker.getSeekermail());
			ps.setString(6, seeker.getPhone());
			ps.setString(7, seeker.getMobile());
			ps.setString(8, seeker.getCreateuserid());
			ps.setString(9, seeker.getUpdateuserid());
			// SQL文を実行する
			count = ps.executeUpdate();
		} catch (SQLException e) {
			throw new IOException(e);
		}
		return count;
	}

	/**
	 * 求職者の属性情報を登録する
	 * @param seeker 登録する求職者の情報
	 * @return 登録件数
	 * @throws IOException
	 * @author mmiyamoto
	 */
	public int insertZokuseiData(JobSeeker seeker) throws IOException {
		int count = 0;

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("insert into zokuseijobseeker(");
		sql.append(" id, ");
		sql.append(" birthdt, ");
		sql.append(" sex, ");
		sql.append(" postal, ");
		sql.append(" nearstation, ");
		sql.append(" partner, ");
		sql.append(" huyou, ");
		sql.append(" education, ");
		sql.append(" career, ");
		sql.append(" HOPEJOB1, ");
		sql.append(" HOPEJOB2, ");
		sql.append(" HOPEJOB3, ");
		sql.append(" HOPEJOBCATEGORY, ");
		sql.append(" HOPEJOBCATEGORY2, ");
		sql.append(" HOPEJOBCATEGORY3, ");
		sql.append(" hopeworkplace, ");
		sql.append(" hopekoyoukeitai, ");
		sql.append(" hopeweekday, ");
		sql.append(" hopeworkingdate, ");
		sql.append(" hopebegintime, ");

		sql.append(" hopesalary, ");
		sql.append(" hopejikyu, ");
		sql.append(" hopeetc, ");
		sql.append(" driverlicense, ");
		sql.append(" licenseetc, ");
		sql.append(" pasokonskill, ");
		sql.append(" caution, ");
		sql.append(" tantoustaffid, ");
		sql.append(" id, ");
		sql.append(" id, ");
		sql.append(" id, ");
		sql.append(" id, ");
		sql.append(" id, ");
		sql.append(" id, ");
		sql.append(" id, ");
		sql.append(" id, ");
		sql.append(" id, ");
		sql.append(" id, ");
		sql.append(" id, ");
		sql.append(" id, ");
		sql.append(" id, ");
		sql.append(" id, ");
		sql.append(" id, ");
		sql.append(" id, ");
		sql.append(" id, ");

		sql.append("id, name, kana, birthdt, sex, postal,");
		sql.append("address, seekermail, nearstation, phone, mobile, partner, huyou, education, career");
		sql.append("HOPEJOB1, HOPEJOB2, HOPEJOB3, HOPEJOBCATEGORY, HOPEJOBCATEGORY2, HOPEJOBCATEGORY3, hopeworkplace,");
		sql.append("hopekoyoukeitai, hopeweekday, hopeworkingdate, hopebegintime, hopeendtime,");
		sql.append("hopesalary, hopejikyu, hopeetc, driverlicense,licenseetc,");
		sql.append("pasokonskill,caution, tantoustaffid, password, note");
		sql.append("createdt, createuserid, updatedt, updateuserid, deleteflag");
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
//			ps.setInt(6, seeker.getAge());
			ps.setString(6, seeker.getPostal());
			ps.setString(7, seeker.getAddress());
			ps.setString(8, seeker.getSeekermail());
			ps.setString(9, seeker.getNearstation());
			ps.setString(10, seeker.getPhone());
			ps.setString(11, seeker.getMobile());
			ps.setString(12, seeker.getPartner());
			ps.setInt(13, seeker.getHuyou());
			ps.setString(14, seeker.getEducation());
			ps.setString(15, seeker.getCareer());
			ps.setString(16, seeker.getHOPEJOB1());
			ps.setString(17, seeker.getHOPEJOB2());
			ps.setString(18, seeker.getHOPEJOB3());
			ps.setString(19, seeker.getHOPEJOBCATEGORY());
			ps.setString(20, seeker.getHOPEJOBCATEGORY2());
			ps.setString(21, seeker.getHOPEJOBCATEGORY3());
			ps.setString(22, seeker.getHopeworkplace());
			ps.setString(23, seeker.getHopekoyoukeitai());
			if (seeker.getHopeworkingdate() != null) {
                ps.setInt(24, seeker.getHopeworkingdate());
            } else {
                ps.setNull(25, java.sql.Types.INTEGER);
            }
			if (seeker.getHopebegintime() != null) {
                ps.setInt(26, seeker.getHopebegintime());
            } else {
                ps.setNull(27, java.sql.Types.INTEGER);
            }
			if (seeker.getHopeendtime() != null) {
                ps.setInt(28, seeker.getHopeendtime());
            } else {
                ps.setNull(29, java.sql.Types.INTEGER);
            }
			if (seeker.getHopesalary() != null) {
                ps.setInt(30, seeker.getHopesalary());
            } else {
                ps.setNull(31, java.sql.Types.INTEGER);
            }
			if (seeker.getHopejikyu() != null) {
                ps.setInt(32, seeker.getHopejikyu());
            } else {
                ps.setNull(33, java.sql.Types.INTEGER);
            }
			//ps.setInt(21, seeker.getHopeworkingDate());
//			ps.setInt(22, seeker.getHopebegintime());
//			ps.setInt(23, seeker.getHopeendtime());
//			ps.setInt(24, seeker.getHopesalary());
//			ps.setInt(25, seeker.getHopejikyu());
			ps.setString(34, seeker.getHopeetc());
			ps.setString(35, seeker.getDriverlicense());
			ps.setString(36, seeker.getLicenseetc());
			ps.setString(37, seeker.getPasokonskill());
			ps.setString(38, seeker.getCaution());
			ps.setString(39, seeker.getTantoustaffid());
			ps.setString(40, seeker.getPassword());
		    ps.setString(41, seeker.getNote());
		    //ps.setDate(42, (Date) seeker.getCreatedt());
			ps.setString(42, seeker.getCreateuserid());
			//ps.setDate(44, (Date) seeker.getUpdatedt());
			ps.setString(43, seeker.getUpdateuserid());
			//ps.setString(34, seeker.getDeleteflag());

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
						//	rs.getInt("age"),
							rs.getString("postal"),
							rs.getString("address"),
							rs.getString("seekermail"),
							rs.getString("nearstation"),
							rs.getString("phone"),
							rs.getString("mobile"),
							rs.getString("partner"),
							rs.getInt("huyou"),
							rs.getString("education"),
							rs.getString("career"),
							rs.getString("HOPEJOB1"),
							rs.getString("HOPEJOB2"),
							rs.getString("HOPEJOB3"),
							rs.getString("HOPEJOBCATEGORY"),
							rs.getString("HOPEJOBCATEGORY2"),
							rs.getString("HOPEJOBCATEGORY3"),
							rs.getString("hopeworkplace"),
							rs.getString("hopekoyoukeitai"),
							rs.getString("hopekeekday"),
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
							//null,
							rs.getString("tantoustaffid"),
							rs.getString("password"),
							rs.getString("note"),
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
						//	rs.getInt("age"),
							rs.getString("postal"),
							rs.getString("address"),
							rs.getString("seekermail"),
							rs.getString("nearstation"),
							rs.getString("phone"),
							rs.getString("mobile"),
							rs.getString("partner"),
							rs.getInt("huyou"),
							rs.getString("education"),
							rs.getString("career"),
							rs.getString("HOPEJOB1"),
							rs.getString("HOPEJOB2"),
							rs.getString("HOPEJOB3"),
							rs.getString("HOPEJOBCATEGORY"),
							rs.getString("HOPEJOBCATEGORY2"),
							rs.getString("HOPEJOBCATEGORY3"),
							rs.getString("hopeworkplace"),
							rs.getString("hopekoyoukeitai"),
							rs.getString("hopekeekday"),
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
							//null,
							rs.getString("tantoustaffid"),
							rs.getString("password"),
							rs.getString("note"),
							rs.getDate("createdt"),
							rs.getString("createuserid"),
							rs.getDate("updatedt"),
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
	//	sql.append(" age = ?,");
		sql.append(" postal = ?,");
		sql.append(" address = ?,");
		sql.append(" seekermail = ?,");
		sql.append(" nearstation = ?,");
		sql.append(" phone = ?,");
		sql.append(" mobile = ?,");
		sql.append(" partner = ?,");
		sql.append(" huyou = ?,");
		sql.append(" education = ?,");
		sql.append(" career = ?,");
		sql.append(" HOPEJOB1 = ?,");
		sql.append(" HOPEJOB2 = ?,");
		sql.append(" HOPEJOB3 = ?,");
		sql.append(" HOPEJOBCATEGORY = ?,");
		sql.append(" HOPEJOBCATEGORY2 = ?,");
		sql.append(" HOPEJOBCATEGORY3 = ?,");
		sql.append(" hopeworkplace = ?,");
		sql.append(" hopekoyoukeitai = ?,");
		sql.append(" hopekweekday = ?,");
		sql.append(" hopeworkingdate = ?,");
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
		sql.append(" note = ?");
		sql.append(" createdt = ?");
		sql.append(" updatedt = ?");
		sql.append(" updateuserid = ?");
		sql.append(" createuserid = ?");
		sql.append(" deleteflag = ?");
		sql.append(" where");
		sql.append(" id = ?");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setString(1, seeker.getName());
			ps.setString(2, seeker.getKana());
			ps.setDate(3, DataCommons.convertToSqlDate(seeker.getBirthdt()));
			ps.setString(4, seeker.getSex());
		//	ps.setInt(5, seeker.getAge());
			ps.setString(6, seeker.getPostal());
			ps.setString(7, seeker.getAddress());
			ps.setString(8, seeker.getSeekermail());
			ps.setString(9, seeker.getNearstation());
			ps.setString(10, seeker.getPhone());
			ps.setString(11, seeker.getMobile());
			ps.setString(12, seeker.getPartner());
			ps.setInt(13, seeker.getHuyou());
			ps.setString(14, seeker.getEducation());
			ps.setString(15, seeker.getCareer());
			ps.setString(16, seeker.getHOPEJOB1());
			ps.setString(17, seeker.getHOPEJOB2());
			ps.setString(18, seeker.getHOPEJOB3());
			ps.setString(19, seeker.getHOPEJOBCATEGORY());
			ps.setString(19, seeker.getHOPEJOBCATEGORY2());
			ps.setString(19, seeker.getHOPEJOBCATEGORY3());
			ps.setString(20, seeker.getHopeworkplace());
			ps.setString(21, seeker.getHopekoyoukeitai());
			ps.setString(22, seeker.getHopeweekday());
			ps.setInt(23, seeker.getHopeworkingdate());
			ps.setInt(24, seeker.getHopebegintime());
			ps.setInt(25, seeker.getHopeendtime());
			ps.setInt(26, seeker.getHopesalary());
			ps.setInt(27, seeker.getHopejikyu());
			ps.setString(28, seeker.getHopeetc());
			ps.setString(29, seeker.getDriverlicense());
			ps.setString(30, seeker.getLicenseetc());
			ps.setString(31, seeker.getPasokonskill());
			ps.setString(32, seeker.getCaution());
			ps.setString(33, seeker.getTantoustaffid());
			ps.setString(34, seeker.getPassword());
			ps.setString(35, seeker.getId());
			ps.setString(36, seeker.getNote());



			// SQL文を実行する
			count = ps.executeUpdate();
		} catch (SQLException e) {
			throw new IOException(e);
		}

		return count;
	}
}
