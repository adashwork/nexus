package jp.or.adash.nexus.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.or.adash.nexus.entity.JobSeeker;
import jp.or.adash.nexus.entity.Jobseeker_simple_entity;
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
	 * @throws IOException
	 */
	public List<Jobseeker_simple_entity> selectJobseekerList() throws IOException {
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
	/**
	 * 求職者IDを元に、求職者情報（1件）を取得する
	 * @param itemNo 商品番号
	 * @return 商品オブジェクト
	 * @throws IOException
	 */
	public Jobseeker_simple_entity selectItem(int js_id) throws IOException {
		Jobseeker_simple_entity jobseeker = null;

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append(" select js.id, js.name, js.age, js.sex, js.hopejobcategory, js.hopejob1, js.hopeworkplace, st.name");
		sql.append(" from jobseeker js");
		sql.append(" left join staff st on js.tantoustaffid = st.id");
		sql.append(" where js.id = ?");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setInt(1, js_id);

			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				// 取得結果をリストに格納する
				while(rs.next()) {
					return new Jobseeker_simple_entity(rs.getString("js.id"),
							rs.getString("js.name"),
							rs.getInt("js.age"),
							rs.getString("js.sex"),
							rs.getString("js.hopejobcategory"),
							rs.getString("js.hopejob1"),
							rs.getString("js.hopeworkplace"),
							rs.getString("st.name"));
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
	 * 求職者IDを元に、求職者情報の詳細情報（1件）を取得する
	 * @param itemNo 商品番号
	 * @return 商品オブジェクト
	 * @throws IOException
	 */
	public JobSeeker selectItem(String js_id) throws IOException {
		JobSeeker jobseeker = null;

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append(" select js.id, js.name, js.age, js.sex, js.hopejobcategory, js.hopejob1, js.hopeworkplace, st.name");
		sql.append(" from jobseeker js");
		sql.append(" left join staff st on js.tantoustaffid = st.id");
		sql.append(" where js.id = ?");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setString(1, js_id);

			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				// 取得結果をリストに格納する
				while(rs.next()) {
/*				return new JobSeeker(rs.getString("js.id"),
							rs.getString("js.name"),
							rs.getInt("js.age"),
							rs.getString("js.sex"),
							rs.getString("js.hopejobcategory"),
							rs.getString("js.hopejob1"),
							rs.getString("js.hopeworkplace"),
							rs.getString("st.name"));*/
				}
			} catch(SQLException e) {
				throw new IOException(e);
			}
		} catch(SQLException e) {
			throw new IOException(e);
		}

		return jobseeker;
	}
}
