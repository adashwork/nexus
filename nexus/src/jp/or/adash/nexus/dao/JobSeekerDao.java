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
		sql.append("id, name, kana, birthdt, sex, zip21,");
		sql.append("addr21, seekermail, nearstation, phone, mobile, partner, huyou, education, career");
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
			ps.setString(6, seeker.getzip21());
			ps.setString(7, seeker.getAddr21());
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
			ps.setString(4, seeker.getAddr21());
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
	 * TODO セットパラメーターのあたりはまだ完了していない。mmiyamoto
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
		sql.append(" hopeendtime, ");
		sql.append(" hopesalary, ");
		sql.append(" hopejikyu, ");
		sql.append(" hopeetc, ");
		sql.append(" driverlicense, ");
		sql.append(" licenseetc, ");
		sql.append(" pasokonskill, ");
		sql.append(" caution, ");
		sql.append(" tantoustaffid, ");
		sql.append(" note, ");
		sql.append(" createuserid, ");
		sql.append(" updateuserid, ");
		sql.append(" deleteflag, ");

		sql.append(") values (");

		sql.append("?, ?, ?, ?, ?, ");
		sql.append("?, ?, ?, ?, ?, ");
		sql.append("?, ?, ?, ?, ?, ");
		sql.append("?, ?, ?, ?, ?, ");
		sql.append("?, ?, ?, ?, ?, ");
		sql.append("?, ?, ?, ?, ?, ");
		sql.append("?, ?, 0");
		sql.append(")");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setString(1, seeker.getId());
			ps.setDate(  2, DataCommons.convertToSqlDate(seeker.getBirthdt()));
			ps.setString(3, seeker.getSex());
			ps.setString(4, seeker.getzip21());
			ps.setString(5, seeker.getNearstation());
			ps.setString(6, seeker.getPartner());
			ps.setInt(   7, seeker.getHuyou());
			ps.setString(8, seeker.getEducation());
			ps.setString(9, seeker.getCareer());
			ps.setString(10, seeker.getHOPEJOB1());
			ps.setString(11, seeker.getHOPEJOB2());
			ps.setString(12, seeker.getHOPEJOB3());
			ps.setString(13, seeker.getHOPEJOBCATEGORY());
			ps.setString(14, seeker.getHOPEJOBCATEGORY2());
			ps.setString(15, seeker.getHOPEJOBCATEGORY3());
			ps.setString(16, seeker.getHopeworkplace());
			ps.setString(17, seeker.getHopekoyoukeitai());
			ps.setString(18, seeker.getHopeweekday());
			if (seeker.getHopeworkingdate() != null) {
                ps.setInt(19, seeker.getHopeworkingdate());
            } else {
                ps.setNull(19, java.sql.Types.INTEGER);
            }
			if (seeker.getHopebegintime() != null) {
                ps.setInt(20, seeker.getHopebegintime());
            } else {
                ps.setNull(20, java.sql.Types.INTEGER);
            }
			if (seeker.getHopeendtime() != null) {
                ps.setInt(21, seeker.getHopeendtime());
            } else {
                ps.setNull(21, java.sql.Types.INTEGER);
            }
			if (seeker.getHopesalary() != null) {
                ps.setInt(22, seeker.getHopesalary());
            } else {
                ps.setNull(22, java.sql.Types.INTEGER);
            }
			if (seeker.getHopejikyu() != null) {
                ps.setInt(23, seeker.getHopejikyu());
            } else {
                ps.setNull(23, java.sql.Types.INTEGER);
            }

			ps.setString(24, seeker.getHopeetc());
			ps.setString(25, seeker.getDriverlicense());
			ps.setString(26, seeker.getLicenseetc());
			ps.setString(27, seeker.getPasokonskill());
			ps.setString(28, seeker.getCaution());
			ps.setString(29, seeker.getTantoustaffid());
		    ps.setString(30, seeker.getNote());
		    //ps.setDate( , (Date) seeker.getCreatedt());
			ps.setString(31, seeker.getCreateuserid());
			//ps.setDate( , (Date) seeker.getUpdatedt());
			ps.setString(32, seeker.getUpdateuserid());


			// SQL文を実行する
			count = ps.executeUpdate();
		} catch (SQLException e) {
			throw new IOException(e);
		}

		return count;
	}

	/**
	 * 求職者IDを元に、求人情報（1件）を取得する
	 * データは個人テーブルと、属性テーブルの二つを結合して取得する
	 * @param id 求職者ID
	 * @return 求職者オブジェクト
	 * @throws IOException
	 * @author Y.Okamura & T.Uchi & mmiyamoto
	 */
	public JobSeekerMain selectJobSeeker(String id) throws IOException {
		JobSeekerMain seeker = null;

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
   sql.append("  select                                                   ");
   sql.append(" p.id                 as  id                           ,   ");
   sql.append(" p.kana               as  kana                         ,   ");
   sql.append(" z.birthdt            as  birthdt                      ,   ");
   sql.append(" z.sex                as  sex                          ,   ");
   sql.append(" z.age                as  age                          ,   ");
   sql.append(" z.postal             as  postal                       ,   ");
   sql.append(" p.address            as  address                      ,   ");
   sql.append(" p.seekermail         as  seekermail                   ,   ");
   sql.append(" z.nearstation        as  nearstation                  ,   ");
   sql.append(" p.phone              as  phone                        ,   ");
   sql.append(" p.mobile             as  mobile                       ,   ");
   sql.append(" z.partner            as  partner                      ,   ");
   sql.append(" z.huyou              as  huyou                        ,   ");
   sql.append(" z.education          as  education                    ,   ");
   sql.append(" z.career             as  career                       ,   ");
   sql.append(" z.HOPEJOB1           as  HOPEJOB1                     ,   ");
   sql.append(" z.HOPEJOB2           as  HOPEJOB2                     ,   ");
   sql.append(" z.HOPEJOB3           as  HOPEJOB3                     ,   ");
   sql.append(" z.HOPEJOBCATEGORY    as  HOPEJOBCATEGORY              ,   ");
   sql.append(" z.HOPEJOBCATEGORY2   as  HOPEJOBCATEGORY2             ,   ");
   sql.append(" z.HOPEJOBCATEGORY3   as  HOPEJOBCATEGORY3             ,   ");
   sql.append(" z.hopeworkplace      as  hopeworkplace                ,   ");
   sql.append(" z.hopekoyoukeitai    as  hopekoyoukeitai              ,   ");
   sql.append(" z.hopeweekday        as  hopeweekday                  ,   ");
   sql.append(" z.hopeworkingdate    as  hopeworkingdate              ,   ");
   sql.append(" z.hopebegintime      as  hopebegintime                ,   ");
   sql.append(" z.hopeendtime        as  hopeendtime                  ,   ");
   sql.append(" z.hopesalary         as  hopesalary                   ,   ");
   sql.append(" z.hopejikyu          as  hopejikyu                    ,   ");
   sql.append(" z.hopeetc            as  hopeetc                      ,   ");
   sql.append(" z.driverlicense      as  driverlicense                ,   ");
   sql.append(" z.licenseetc         as  licenseetc                   ,   ");
   sql.append(" z.pasokonskill       as  pasokonskill                 ,   ");
   sql.append(" z.caution            as  caution                      ,   ");
   sql.append(" z.tantoustaffid      as  tantoustaffid                ,   ");
   sql.append(" z.password           as  password                     ,   ");
   sql.append(" z.note               as  note                         ,   ");
   sql.append(" z.createdt           as  createdt                     ,   ");
   sql.append(" z.createuserid       as  createuserid                 ,   ");
   sql.append(" z.updatedt           as  updatedt                     ,   ");
   sql.append(" z.updateuserid       as  updateuserid                 ,   ");
   sql.append(" z.deleteflag         as  deleteflag                       ");

   sql.append(" from jobseeker p                       ");
   sql.append(" left join zokuseijobseeker z on p.id = z.id                       ");
   sql.append(" where z.id = ?;                     ");

		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setString(1, id);

			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				// 取得結果をリストに格納する
				while (rs.next()) {
					return new JobSeekerMain(
							rs.getString("id"),
							rs.getString("name"),
							rs.getString("kana"),
							rs.getString("sex"),
							rs.getDate("birthdt"),
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
			ps.setString(6, seeker.getzip21());
			ps.setString(7, seeker.getAddr21());
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
