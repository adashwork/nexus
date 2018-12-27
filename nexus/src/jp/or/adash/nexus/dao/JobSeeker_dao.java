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
	 * 追記 jobseekerテーブルの分裂に対応。
	 * 		コメントの更新日時順に表示されるように変更。
	 * 		年齢を生年月日から計算して表示されるように修正。
	 * 		希望業種、希望職種、希望勤務地が番号表示になっていたため、正しい表示に修正
	 *
	 * @auther aihara tanaka
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
	 * 	 * 追記 jobseekerテーブルの分裂に対応。
	 * 		    コメントの更新日時順に表示されるように変更。
	 * 		    年齢を生年月日から計算して表示されるように修正。
	 * 		    希望業種、希望職種、希望勤務地が番号表示になっていたため、正しい表示に修正
	 * @auther aihara,tanaka
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
	 * 	 * 追記 jobseekerテーブルの分裂に対応。
	 * 		    コメントの更新日時順に表示されるように変更。
	 * 		    年齢を生年月日から計算して表示されるように修正。
	 * 		    希望業種、希望職種、希望勤務地が番号表示になっていたため、正しい表示に修正
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
		sql.append(" select js.id, js.name, js.kana, zjs.sex, zjs.birthdt, zjs.age,");
		sql.append(" zjs.postal, js.address, zjs.nearstation, js.phone, js.mobile, zjs.partner, zjs.huyou, zjs.education,");
		sql.append(" zjs.hopejob1, zjs.hopejob2, zjs.hopejob3, zjs.hopejobcategory, zjs.hopeworkplace,");
		sql.append(" zjs.hopekoyoukeitai, zjs.hopeworkingdate, zjs.hopebegintime, zjs.hopeendtime,");
		sql.append(" zjs.hopesalary, zjs.hopejikyu, zjs.hopeetc, zjs.driverlicense, zjs.licenseetc,");
		sql.append(" zjs.pasokonskill, zjs.caution, st.name, zjs.tantoustaffid, zjs.password,");
		sql.append(" zjs.createdt, zjs.createuserid, zjs.updatedt, zjs.updateuserid, zjs.deleteflag");
		sql.append(" from jobseeker js");
		sql.append(" left join zokuseijobseeker zjs on js.id = zjs.id");
		sql.append(" left join staff st on zjs.tantoustaffid = st.id");
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
							rs.getString("zjs.sex"),
							rs.getDate("zjs.birthdt"),
							rs.getInt("zjs.age"),
							rs.getString("zjs.postal"),
							rs.getString("js.address"),
							rs.getString("zjs.nearstation"),
							rs.getString("js.phone"),
							rs.getString("js.mobile"),
							rs.getString("zjs.partner"),
							rs.getInt("zjs.huyou"),
							rs.getString("zjs.education"),
							rs.getString("zjs.hopejob1"),
							rs.getString("zjs.hopejob2"),
							rs.getString("zjs.hopejob3"),
							rs.getString("zjs.hopejobcategory"),
							rs.getString("zjs.hopeworkplace"),
							rs.getString("zjs.hopekoyoukeitai"),
							rs.getInt("zjs.hopeworkingdate"),
							rs.getInt("zjs.hopebegintime"),
							rs.getInt("zjs.hopeendtime"),
							rs.getInt("zjs.hopesalary"),
							rs.getInt("zjs.hopejikyu"),
							rs.getString("zjs.hopeetc"),
							rs.getString("zjs.driverlicense"),
							rs.getString("zjs.licenseetc"),
							rs.getString("zjs.pasokonskill"),
							rs.getString("zjs.caution"),
							rs.getString("st.name"),
							rs.getString("zjs.tantoustaffid"),
							rs.getString("zjs.password"),
							rs.getDate("zjs.createdt"),
							rs.getString("zjs.createuserid"),
							rs.getDate("zjs.updatedt"),
							rs.getString("zjs.updateuserid"),
							rs.getString("zjs.deleteflag")
							);
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

