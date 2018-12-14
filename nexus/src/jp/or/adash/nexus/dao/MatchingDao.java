package jp.or.adash.nexus.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.or.adash.nexus.entity.MatchingCase;
import jp.or.adash.nexus.utils.common.DataCommons;
import jp.or.adash.nexus.utils.dao.Transaction;
/**
 * マッチングデータアクセスクラス
 * @author ji
 * @author pgjavaAT
 *
 */
public class MatchingDao {

	/**
	 * データベース接続オブジェクト
	 */
	private Connection conn;

	/**
	 * コンストラクタ
	 * @param transaction トランザクションオブジェクト
	 */
	public MatchingDao(Transaction transaction) {
		this.conn = transaction.getConnection();
	}

	/**
	 * マッチング情報を登録する
	 * @param matching
	 * @return 登録件数
	 * @throws IOException
	 */
	public int insert(MatchingCase matching) throws IOException {
		int count = 0;

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("insert into matchingcase ");
		sql.append("(id,companyNo,kyujinno,jobseekerid,staffid,interviewdt,enterdt,"		// 追加・修正 2018/12/11.12 T.Ikeda
				+ "assessment,note,createuserid,updateuserid");
		sql.append(") values (");
		sql.append("?,?,?,?,?,?,?,?,?,?,?");
		sql.append(")");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setInt(1,matching.getId());
			ps.setString(2,matching.getCompanyNo());
			ps.setString(3,matching.getKyujinno());
			ps.setString(4,matching.getJobseekerid());
			ps.setString(5,matching.getStaffid());
			ps.setDate(6,DataCommons.convertToSqlDate(matching.getInterviewdt()));
			ps.setDate(7,DataCommons.convertToSqlDate(matching.getEnterdt()));
			ps.setString(8,matching.getAssessment());
			ps.setString(9,matching.getNote());
			ps.setString(10,matching.getCreateuserid());
			ps.setString(11,matching.getUpdateuserid());


			// SQL文を実行する
			count = ps.executeUpdate();
		} catch (SQLException e) {
			throw new IOException(e);
		}

		return count;
	}

	/**
	 * idを元にマッチング情報のデータを取得する
	 * @param id
	 * @return 取得したデータ
	 * @throws IOException
	 */
	public MatchingCase select(int id) throws IOException {
		MatchingCase matching = null;

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("select * "
				+ "from matchingcase "
				+ "where id = ?; ");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setInt(1, id);

			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				//0取得結果をリストに格納する
				while(rs.next()) {
					matching = new MatchingCase(
							rs.getInt("id"),
							rs.getString("companyNo"),		// 追加・修正 2018/12/11.12 T.Ikeda
							rs.getString("kyujinno"),
							rs.getString("jobseekerid"),
							rs.getString("staffid"),
							rs.getDate("interviewdt"),
							rs.getDate("enterdt"),
							rs.getString("assessment"),
							rs.getString("note"),
							rs.getDate("createdt"),
							rs.getString("createuserid"),
							rs.getDate("upDatedt"),
							rs.getString("updateuserid")
							);
				}
			} catch(SQLException e) {
				throw new IOException(e);
			}
		} catch(SQLException e) {
			throw new IOException(e);
		}

		return matching;
	}

	/**
	 * マッチング情報の更新
	 * @param matching 更新対象のデータ
	 * @return 更新行数 0じゃなければ更新成功
	 * @throws IOException
	 */
	public int update(MatchingCase matching) throws IOException {
		int count = 0;

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("update matchingcase set");
		sql.append(" companyNo = ?,");				// 追加・修正 2018/12/11.12 T.Ikeda
		sql.append(" kyujinno = ?,");
		sql.append(" jobseekerid = ?,");
		sql.append(" staffid = ?,");
		sql.append(" interviewdt = ?,");
		sql.append(" enterdt = ?,");
		sql.append(" assessment = ?,");
		sql.append(" note = ?,");
		sql.append(" updateuserid = ?");
		sql.append(" where");
		sql.append(" id = ?");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {

			ps.setString(1, matching.getKyujinno());
			ps.setString(2, matching.getCompanyNo());
			ps.setString(3, matching.getJobseekerid());
			ps.setString(4, matching.getStaffid());
			ps.setDate(5, DataCommons.convertToSqlDate(matching.getInterviewdt()));
			ps.setDate(6, DataCommons.convertToSqlDate(matching.getEnterdt()));
			ps.setString(7, matching.getAssessment());
			ps.setString(8, matching.getNote());
			ps.setString(9, matching.getUpdateuserid());
			ps.setInt(10, matching.getId());

			// SQL文を実行する
			count = ps.executeUpdate();
		} catch(SQLException e) {
			throw new IOException(e);
		}

		return count;
	}

}



