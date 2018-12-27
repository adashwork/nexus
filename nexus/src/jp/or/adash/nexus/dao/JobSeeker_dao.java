package jp.or.adash.nexus.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.or.adash.nexus.entity.JobSeekerMain;
import jp.or.adash.nexus.entity.Jobseeker_simple_entity;
import jp.or.adash.nexus.entity.StaffName;
import jp.or.adash.nexus.utils.dao.Transaction;

/**
 * 求職者データアクセスクラス
 * @author s.aihara
 *
 */

public class JobSeeker_dao {

	/**
	 * データベース接続オブジェクト
	 */
	private Connection conn;

	/**
	 * コンストラクタ
	 * @param transaction トランザクションオブジェクト
	 */
	public JobSeeker_dao(Transaction transaction) {
		this.conn = transaction.getConnection();
	}

	/**
	 * 求職者の情報一覧を取得する
	 * @return 求職者情報リスト
	 * @auther aihara
	 * @throws IOException
	 */
/*	public List<Jobseeker_simple_entity> selectJobseekerList() throws IOException {
		List<Jobseeker_simple_entity> jobseeker = new ArrayList<Jobseeker_simple_entity>();

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("select js.id, js.name, js.age, js.sex, js.hopejobcategory, js.hopejob1, js.hopeworkplace, st.name");
		sql.append(" from jobseeker js");
		sql.append(" left join staff st on js.tantoustaffid = st.id");
		sql.append(" order by js.id");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				// 取得結果をリストに格納する
				while(rs.next()) {
					jobseeker.add(new Jobseeker_simple_entity(rs.getString("js.id"),
							rs.getString("js.name"),
							rs.getInt("js.age"),
							rs.getString("js.sex"),
							rs.getString("js.hopejobcategory"),
							rs.getString("js.hopejob1"),
							rs.getString("js.hopeworkplace"),
							rs.getString("st.name")));
				}
			} catch(SQLException e) {
				throw new IOException(e);
			}
		} catch(SQLException e) {
			throw new IOException(e);
		}

		return jobseeker;
	}
	*/
	/**
	 * 求職者IDを元に、求職者情報（1件）を取得する
	 * @auther aihara
	 * @throws IOException
	 */

	public List<Jobseeker_simple_entity> selectJobSeekerId(String js_id) throws IOException {
		List<Jobseeker_simple_entity> jobseeker = new ArrayList<Jobseeker_simple_entity>();

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append(" select js.id, js.name, zjs.sex,st.name AS staffname,jc.name AS jobcategoryname,jb.name AS jobname,tk.name AS todouhukenname,TIMESTAMPDIFF(YEAR,zjs.birthdt,CURDATE()) AS age");
		sql.append(" from jobseeker js");
		sql.append(" left join  comment cm on js.id = cm.jobseekerid  ");
		sql.append(" left join zokuseijobseeker zjs on js.id = zjs.id  ");
		sql.append(" left join staff st on zjs.tantoustaffid = st.id");
		sql.append(" left join jobcategory jc on zjs.HOPEJOBCATEGORY = jc.id");
		sql.append(" left join job jb on zjs.HOPEJOB1 = jb.id");
		sql.append(" left join todouhuken tk on zjs.hopeworkplace = tk.cd");
		sql.append(" where js.id = ?");
		sql.append(" ORDER BY  cm.updatedt DESC ");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setString(1, js_id);

			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				// 取得結果をリストに格納する
				while(rs.next()) {
					jobseeker.add(new Jobseeker_simple_entity(
							rs.getString("js.id"),
							rs.getString("js.name"),
							rs.getInt("age"),
							rs.getString("zjs.sex"),
							rs.getString("jobcategoryname"),
							rs.getString("jobname"),
							rs.getString("todouhukenname"),
							rs.getString("staffname")));
				}
			} catch(SQLException e) {
				throw new IOException(e);
			}
		} catch(SQLException e) {
			throw new IOException(e);
		}
		return jobseeker;
	}

	/**
	 * 担当紹介者を元に、求職者情報（1件）を取得する
	 * @auther aihara
	 * @throws IOException
	 */

	public List<Jobseeker_simple_entity> selectJobSeekerStName(String st_name) throws IOException {
		List<Jobseeker_simple_entity> jobseeker = new ArrayList<Jobseeker_simple_entity>();

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append(" select js.id, js.name, zjs.sex, st.name AS staffname,jc.name AS jobcategoryname,jb.name AS jobname,tk.name AS todouhukenname,TIMESTAMPDIFF(YEAR,zjs.birthdt,CURDATE()) AS age");
		sql.append(" from jobseeker js");
		sql.append(" left join  comment cm on js.id = cm.jobseekerid  ");
		sql.append(" left join zokuseijobseeker zjs on js.id = zjs.id  ");
		sql.append(" left join staff st on zjs.tantoustaffid = st.id");
		sql.append(" left join jobcategory jc on zjs.HOPEJOBCATEGORY = jc.id");
		sql.append(" left join job jb on zjs.HOPEJOB1 = jb.id");
		sql.append(" left join todouhuken tk on zjs.hopeworkplace = tk.cd");
		sql.append(" where st.name = ?");
		sql.append(" ORDER BY  cm.updatedt DESC ");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setString(1, st_name);

			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				// 取得結果をリストに格納する
				while(rs.next()) {
					jobseeker.add(new Jobseeker_simple_entity(
							rs.getString("js.id"),
							rs.getString("js.name"),
							rs.getInt("age"),
							rs.getString("zjs.sex"),
							rs.getString("jobcategoryname"),
							rs.getString("jobname"),
							rs.getString("todouhukenname"),
							rs.getString("staffname")));
				}
			} catch(SQLException e) {
				throw new IOException(e);
			}
		} catch(SQLException e) {
			throw new IOException(e);
		}
		return jobseeker;
	}

	/**
	 * 求職者ID、求職者かな名、担当紹介者氏名を元に、求職者情報（1件）を取得する
	 * @auther aihara , tanaka
	 * @throws IOException
	 */
	public List<Jobseeker_simple_entity> selectJobSeeker(String js_id,String js_kana,String st_name) throws IOException {
		List<Jobseeker_simple_entity> jobseeker = new ArrayList<Jobseeker_simple_entity>();
		// SQL文を生成する
		StringBuffer sql = new StringBuffer();

		// 1.求職者ID、求職者かな名、担当紹介者氏名が入っている場合
		if(!("".equals(js_id)) && !("".equals(js_kana)) && !("".equals(st_name))) {

			sql.append(" select js.id, js.name, zjs.sex, st.name AS staffname,jc.name AS jobcategoryname,jb.name AS jobname,tk.name AS todouhukenname,TIMESTAMPDIFF(YEAR,zjs.birthdt,CURDATE()) AS age");
			sql.append(" from jobseeker js");
			sql.append(" left join  comment cm on js.id = cm.jobseekerid  ");
			sql.append(" left join zokuseijobseeker zjs on js.id = zjs.id  ");
			sql.append(" left join staff st on zjs.tantoustaffid = st.id");
			sql.append(" left join jobcategory jc on zjs.HOPEJOBCATEGORY = jc.id");
			sql.append(" left join job jb on zjs.HOPEJOB1 = jb.id");
			sql.append(" left join todouhuken tk on zjs.hopeworkplace = tk.cd");
			sql.append(" where js.id = ? and js.kana like concat('%', ?, '%') and st.name = ?");
			sql.append(" ORDER BY  cm.updatedt DESC ");
			try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
				ps.setString(1, js_id);
				ps.setString(2, js_kana);
				ps.setString(3, st_name);
				// SQL文を実行する
				try (ResultSet rs = ps.executeQuery()) {
					// 取得結果をリストに格納する
					while(rs.next()) {
						jobseeker.add(new Jobseeker_simple_entity(
								rs.getString("js.id"),
								rs.getString("js.name"),
								rs.getInt("age"),
								rs.getString("zjs.sex"),
								rs.getString("jobcategoryname"),
								rs.getString("jobname"),
								rs.getString("todouhukenname"),
								rs.getString("staffname")));
					}
				} catch(SQLException e) {
					throw new IOException(e);
				}
			} catch(SQLException e) {
				throw new IOException(e);
			}
			return jobseeker;
		}
		// 2.求職者ID、求職者かな名が入っている場合
		else if(!("".equals(js_id)) && !("".equals(js_kana)) && "".equals(st_name)) {
			sql.append(" select js.id, js.name, zjs.sex, st.name AS staffname,jc.name AS jobcategoryname,jb.name AS jobname,tk.name AS todouhukenname,TIMESTAMPDIFF(YEAR,zjs.birthdt,CURDATE()) AS age");
			sql.append(" from jobseeker js");
			sql.append(" left join  comment cm on js.id = cm.jobseekerid ");
			sql.append(" left join zokuseijobseeker zjs on js.id = zjs.id  ");
			sql.append(" left join staff st on zjs.tantoustaffid = st.id");
			sql.append(" left join jobcategory jc on zjs.HOPEJOBCATEGORY = jc.id");
			sql.append(" left join job jb on zjs.HOPEJOB1 = jb.id");
			sql.append(" left join todouhuken tk on zjs.hopeworkplace = tk.cd");
			sql.append(" where js.id = ? and js.kana like concat('%', ?, '%')");
			sql.append(" ORDER BY  cm.updatedt DESC");
			try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
				ps.setString(1, js_id);
				ps.setString(2, js_kana);
				// SQL文を実行する
				try (ResultSet rs = ps.executeQuery()) {
					// 取得結果をリストに格納する
					while(rs.next()) {
						jobseeker.add(new Jobseeker_simple_entity(
								rs.getString("js.id"),
								rs.getString("js.name"),
								rs.getInt("age"),
								rs.getString("zjs.sex"),
								rs.getString("jobcategoryname"),
								rs.getString("jobname"),
								rs.getString("todouhukenname"),
								rs.getString("staffname")));
					}
				} catch(SQLException e) {
					throw new IOException(e);
				}
			} catch(SQLException e) {
				throw new IOException(e);
			}
			return jobseeker;
		}
		// 3.求職者IDが入っている場合
		else if(!("".equals(js_id)) && "".equals(js_kana) && "".equals(st_name)) {
			sql.append(" select js.id, js.name, zjs.sex, st.name AS staffname,jc.name AS jobcategoryname,jb.name AS jobname,tk.name AS todouhukenname,TIMESTAMPDIFF(YEAR,zjs.birthdt,CURDATE()) AS age");
			sql.append(" from jobseeker js");
			sql.append(" left join  comment cm on js.id = cm.jobseekerid  ");
			sql.append(" left join zokuseijobseeker zjs on js.id = zjs.id  ");
			sql.append(" left join staff st on zjs.tantoustaffid = st.id");
			sql.append(" left join jobcategory jc on zjs.HOPEJOBCATEGORY = jc.id");
			sql.append(" left join job jb on zjs.HOPEJOB1 = jb.id");
			sql.append(" left join todouhuken tk on zjs.hopeworkplace = tk.cd");
			sql.append(" where js.id = ?");
			sql.append(" ORDER BY  cm.updatedt DESC");
			try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
				ps.setString(1, js_id);
				// SQL文を実行する
				try (ResultSet rs = ps.executeQuery()) {
					// 取得結果をリストに格納する
					while(rs.next()) {
						jobseeker.add(new Jobseeker_simple_entity(
								rs.getString("js.id"),
								rs.getString("js.name"),
								rs.getInt("age"),
								rs.getString("zjs.sex"),
								rs.getString("jobcategoryname"),
								rs.getString("jobname"),
								rs.getString("todouhukenname"),
								rs.getString("staffname")));
					}
				} catch(SQLException e) {
					throw new IOException(e);
				}
			} catch(SQLException e) {
				throw new IOException(e);
			}
			return jobseeker;
		}

		// 4.求職者かな名、担当紹介者氏名が入っている場合
		else if("".equals(js_id) && !("".equals(js_kana)) && !("".equals(st_name))) {
			sql.append(" select js.id, js.name, zjs.sex, st.name AS staffname,jc.name AS jobcategoryname,jb.name AS jobname,tk.name AS todouhukenname,TIMESTAMPDIFF(YEAR,zjs.birthdt,CURDATE()) AS age");
			sql.append(" from jobseeker js");
			sql.append(" left join  comment cm on js.id = cm.jobseekerid ");
			sql.append(" left join zokuseijobseeker zjs on js.id = zjs.id  ");
			sql.append(" left join staff st on zjs.tantoustaffid = st.id");
			sql.append(" left join jobcategory jc on zjs.HOPEJOBCATEGORY = jc.id");
			sql.append(" left join job jb on zjs.HOPEJOB1 = jb.id");
			sql.append(" left join todouhuken tk on zjs.hopeworkplace = tk.cd");
			sql.append(" where js.kana like concat('%', ?, '%') and st.name = ?");
			sql.append(" ORDER BY  cm.updatedt DESC");
			try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
				ps.setString(1, js_kana);
				ps.setString(2, st_name);
				// SQL文を実行する
				try (ResultSet rs = ps.executeQuery()) {
					// 取得結果をリストに格納する
					while(rs.next()) {
						jobseeker.add(new Jobseeker_simple_entity(
								rs.getString("js.id"),
								rs.getString("js.name"),
								rs.getInt("age"),
								rs.getString("zjs.sex"),
								rs.getString("jobcategoryname"),
								rs.getString("jobname"),
								rs.getString("todouhukenname"),
								rs.getString("staffname")));
					}
				} catch(SQLException e) {
					throw new IOException(e);
				}
			} catch(SQLException e) {
				throw new IOException(e);
			}
			return jobseeker;
		}
		// 5.求職者かな名が入っている場合
		else if("".equals(js_id) && !("".equals(js_kana)) && "".equals(st_name)){
			sql.append(" select js.id, js.name, zjs.sex, st.name AS staffname,jc.name AS jobcategoryname,jb.name AS jobname,tk.name AS todouhukenname,TIMESTAMPDIFF(YEAR,zjs.birthdt,CURDATE()) AS age");
			sql.append(" from jobseeker js");
			sql.append(" left join  comment cm on js.id = cm.jobseekerid ");
			sql.append(" left join zokuseijobseeker zjs on js.id = zjs.id  ");
			sql.append(" left join staff st on zjs.tantoustaffid = st.id");
			sql.append(" left join jobcategory jc on zjs.HOPEJOBCATEGORY = jc.id");
			sql.append(" left join job jb on zjs.HOPEJOB1 = jb.id");
			sql.append(" left join todouhuken tk on zjs.hopeworkplace = tk.cd");
			sql.append(" where js.kana like concat('%', ?, '%')");
			sql.append(" ORDER BY  cm.updatedt DESC");
			try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
				ps.setString(1, js_kana);
				// SQL文を実行する
				try (ResultSet rs = ps.executeQuery()) {
					// 取得結果をリストに格納する
					while(rs.next()) {
						jobseeker.add(new Jobseeker_simple_entity(
								rs.getString("js.id"),
								rs.getString("js.name"),
								rs.getInt("age"),
								rs.getString("zjs.sex"),
								rs.getString("jobcategoryname"),
								rs.getString("jobname"),
								rs.getString("todouhukenname"),
								rs.getString("staffname")));
					}
				} catch(SQLException e) {
					throw new IOException(e);
				}
			} catch(SQLException e) {
				throw new IOException(e);
			}
			return jobseeker;
		}
		// 6.求職者ID、担当紹介者氏名が入っている場合
		else if(!("".equals(js_id)) && "".equals(js_kana) && !("".equals(st_name))){
			sql.append(" select js.id, js.name, zjs.sex, st.name AS staffname,jc.name AS jobcategoryname,jb.name AS jobname,tk.name AS todouhukenname,TIMESTAMPDIFF(YEAR,zjs.birthdt,CURDATE()) AS age");
			sql.append(" from jobseeker js");
			sql.append(" left join  comment cm on js.id = cm.jobseekerid");
			sql.append(" left join zokuseijobseeker zjs on js.id = zjs.id  ");
			sql.append(" left join staff st on zjs.tantoustaffid = st.id");
			sql.append(" left join jobcategory jc on zjs.HOPEJOBCATEGORY = jc.id");
			sql.append(" left join job jb on zjs.HOPEJOB1 = jb.id");
			sql.append(" left join todouhuken tk on zjs.hopeworkplace = tk.cd");
			sql.append(" where js.id = ? and st.name = ?");
			sql.append(" ORDER BY  cm.updatedt DESC");
			try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
				ps.setString(1, js_id);
				ps.setString(2, st_name);
				// SQL文を実行する
				try (ResultSet rs = ps.executeQuery()) {
					// 取得結果をリストに格納する
					while(rs.next()) {
						jobseeker.add(new Jobseeker_simple_entity(
								rs.getString("js.id"),
								rs.getString("js.name"),
								rs.getInt("age"),
								rs.getString("zjs.sex"),
								rs.getString("jobcategoryname"),
								rs.getString("jobname"),
								rs.getString("todouhukenname"),
								rs.getString("staffname")));
					}
				} catch(SQLException e) {
					throw new IOException(e);
				}
			} catch(SQLException e) {
				throw new IOException(e);
			}
			return jobseeker;
		}
		// 7.担当紹介者氏名が入っている場合
		else if("".equals(js_id) && "".equals(js_kana) && !("".equals(st_name))){
			sql.append(" select js.id, js.name, zjs.sex, st.name AS staffname,jc.name AS jobcategoryname,jb.name AS jobname,tk.name AS todouhukenname,TIMESTAMPDIFF(YEAR,zjs.birthdt,CURDATE()) AS age");
			sql.append(" from jobseeker js");
			sql.append(" left join  comment cm on js.id = cm.jobseekerid ");
			sql.append(" left join zokuseijobseeker zjs on js.id = zjs.id  ");
			sql.append(" left join staff st on zjs.tantoustaffid = st.id");
			sql.append(" left join jobcategory jc on zjs.HOPEJOBCATEGORY = jc.id");
			sql.append(" left join job jb on zjs.HOPEJOB1 = jb.id");
			sql.append(" left join todouhuken tk on zjs.hopeworkplace = tk.cd");
			sql.append(" where st.name = ?");
			sql.append(" ORDER BY  cm.updatedt DESC");
			try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
				ps.setString(1, st_name);
				// SQL文を実行する
				try (ResultSet rs = ps.executeQuery()) {
					// 取得結果をリストに格納する
					while(rs.next()) {
						jobseeker.add(new Jobseeker_simple_entity(
								rs.getString("js.id"),
								rs.getString("js.name"),
								rs.getInt("age"),
								rs.getString("zjs.sex"),
								rs.getString("jobcategoryname"),
								rs.getString("jobname"),
								rs.getString("todouhukenname"),
								rs.getString("staffname")));
					}
				} catch(SQLException e) {
					throw new IOException(e);
				}
			} catch(SQLException e) {
				throw new IOException(e);
			}
			return jobseeker;
		}

		// 8.何も入っていない場合
		else {
			return jobseeker;
		}
}

	/**
	 * 求職者IDを元に、求職者情報の詳細情報（1件）を取得する
	 * @return 求職者情報の詳細情報
	 * @throws IOException
	 */
	public JobSeekerMain selectJobseekermaininfo(String js_id) throws IOException {
		JobSeekerMain jobseeker = null;
		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append(" select js.id, js.name, js.kana, js.sex, js.birthdt, ");
		sql.append(" js.postal, js.address,is seekermail, js.nearstation, js.phone, js.mobile, js.partner, js.huyou, js.education, js.career");
		sql.append(" js.HOPEJOB1, js.HOPEJOB2, js.HOPEJOB3, js.HOPEJOBCATEGORY, js.HOPEJOBCATEGORY2, js.HOPEJOBCATEGORY3, js.hopeworkplace,");
		sql.append(" js.hopekoyoukeitai, js.hopeweekday ,js.hopeworkingdate, js.hopebegintime, js.hopeendtime,");
		sql.append(" js.hopesalary, js.hopejikyu, js.hopeetc, js.driverlicense, js.licenseetc,");
		sql.append(" js.pasokonskill, js.caution, st.name, js.tantoustaffid, js.password, js.note");
		sql.append(" js.createdt, js.createuserid, js.updatedt, js.updateuserid, js.deleteflag");
		sql.append(" from jobseeker js");
		sql.append(" left join staff st on js.tantoustaffid = st.id");
		sql.append(" where js.id = ?");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setString(1, js_id);
			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				// 取得結果をリストに格納する
				while(rs.next()) {
				jobseeker = new JobSeekerMain(
							rs.getString("js.id"),
							rs.getString("js.name"),
							rs.getString("js.kana"),
							rs.getString("js.sex"),
							rs.getDate("js.birthdt"),
						//	rs.getInt("js.age"),
							rs.getString("js.postal"),
							rs.getString("js.address"),
							rs.getString("js.seekermail"),
							rs.getString("js.nearstation"),
							rs.getString("js.phone"),
							rs.getString("js.mobile"),
							rs.getString("js.partner"),
							rs.getInt("js.huyou"),
							rs.getString("js.education"),
							rs.getString("js.career"),
							rs.getString("js.HOPEJOB1"),
							rs.getString("js.HOPEJOB2"),
							rs.getString("js.HOPEJOB3"),
							rs.getString("js.HOPEJOBCATEGORY"),
							rs.getString("js.HOPEJOBCATEGORY2"),
							rs.getString("js.HOPEJOBCATEGORY3"),
							rs.getString("js.hopeworkplace"),
							rs.getString("js.hopekoyoukeitai"),
							rs.getString("js.hopeweekday"),
							rs.getInt("js.hopeworkingdate"),
							rs.getInt("js.hopebegintime"),
							rs.getInt("js.hopeendtime"),
							rs.getInt("js.hopesalary"),
							rs.getInt("js.hopejikyu"),
							rs.getString("js.hopeetc"),
							rs.getString("js.driverlicense"),
							rs.getString("js.licenseetc"),
							rs.getString("js.pasokonskill"),
							rs.getString("js.caution"),
							rs.getString("st.name"),
							rs.getString("js.tantoustaffid"),
							rs.getString("js.password"),
							rs.getDate("js.createdt"),
							rs.getString("js.createuserid"),
							rs.getDate("js.updatedt"),
							rs.getString("js.updateuserid"),
							rs.getString("js.deleteflag"));
				}
			} catch(SQLException e) {
				throw new IOException(e);
			}
		} catch(SQLException e) {
			throw new IOException(e);
		}
		return jobseeker;
	}

	/**
	 * 担当紹介者氏名を取得する
	 * @auther aihara
	 * @throws IOException
	 */

	public List<StaffName> selectTantoStaff() throws IOException {
		List<StaffName> name = new ArrayList<StaffName>();

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append(" select name");
		sql.append(" from staff");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {

			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				// 取得結果をリストに格納する
				while(rs.next()) {
					name.add(new StaffName(
							rs.getString("name")));
				}
			} catch(SQLException e) {
				throw new IOException(e);
			}
		} catch(SQLException e) {
			throw new IOException(e);
		}
		return name;
	}
}

