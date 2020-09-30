package jp.or.adash.nexus.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	 * 求職者ID、求職者かな名、担当紹介者氏名を元に、求職者情報を取得する
	 * @param jobSeekerId 求職者ID
	 * @param jobSeekerKanaName 求職者かな名
	 * @param tantoStaffId 担当紹介者ID
	 * @return 検索結果の求職者情報リスト
	 * @auther aihara , tanaka
	 * 	 * 追記 jobseekerテーブルの分裂に対応。
	 * 		    コメントの更新日時順に表示されるように変更。
	 * 		    年齢を生年月日から計算して表示されるように修正。
	 * 		    希望業種、希望職種、希望勤務地が番号表示になっていたため、正しい表示に修正
	 * 	2020/06/14 T.Kawasaki 検索条件によるSQLの生成ロジックを簡略化
	 * @throws IOException
	 */
	public List<Jobseeker_simple_entity> selectJobSeeker(
			String jobSeekerId,
			String jobSeekerKanaName,
			String tantoStaffId
			) throws IOException {

		List<Jobseeker_simple_entity> jobseeker = new ArrayList<Jobseeker_simple_entity>();

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();

		sql.append(" select js.id, js.name, zjs.sex, st.name AS staffname,jc.name AS jobcategoryname,jb.name AS jobname,tk.name AS todouhukenname,TIMESTAMPDIFF(YEAR,zjs.birthdt,CURDATE()) AS age");
		sql.append(" from jobseeker js");
		sql.append(" left join comment cm on js.id = cm.jobseekerid  ");
		sql.append(" left join zokuseijobseeker zjs on js.id = zjs.id  ");
		sql.append(" left join staff st on zjs.tantoustaffid = st.id");
		sql.append(" left join jobcategory jc on zjs.HOPEJOBCATEGORY = jc.id");
		sql.append(" left join job jb on zjs.HOPEJOB1 = jb.id");
		sql.append(" left join todouhuken tk on zjs.hopeworkplace = tk.cd");
		sql.append(" where");
//		sql.append(" js.id = ? and js.kana like concat('%', ?, '%') and zjs.tantoustaffid = ?");

		boolean cond = false;					// 検索条件が入っているかどうか
		int count = 0;							// 検索条件数
		String[] conditions = new String[3];	// 検索条件

		// 検索条件（求職者ID）
		if (jobSeekerId != "") {
			cond = true;
			sql.append(" js.id = ?");
			conditions[count] = jobSeekerId;
			count++;
		}
		// 検索条件（求職者かな名）
		if (jobSeekerKanaName != "") {
			if (cond) {
				sql.append(" and ");
			}
			cond = true;
			sql.append(" js.kana like concat('%', ?, '%') ");
			conditions[count] = jobSeekerKanaName;
			count++;
		}
		// 検索条件（担当紹介者ID）
		if (tantoStaffId != "") {
			if (cond) {
				sql.append(" and ");
			}
			cond = true;
			sql.append(" zjs.tantoustaffid = ? ");
			conditions[count] = tantoStaffId;
			count++;
		}

		sql.append(" ORDER BY  cm.updatedt DESC ");

		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			// 検索条件を設定する
			for (int i = 0; i < count; i++) {
				ps.setString(i + 1, conditions[i]);
			}
//			ps.setString(1, jobSeekerId);
//			ps.setString(2, jobSeekerKanaName);
//			ps.setString(3, tantoStaffId);

			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				// 取得結果をリストに格納する
				while(rs.next()) {
					jobseeker.add(new Jobseeker_simple_entity(
							rs.getString("id"),
							rs.getString("name"),
							rs.getInt("age"),
							rs.getString("sex"),
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

