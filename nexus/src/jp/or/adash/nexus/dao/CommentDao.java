package jp.or.adash.nexus.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.or.adash.nexus.entity.Comment;
import jp.or.adash.nexus.utils.dao.Transaction;

/***
 * コメントデータアクセスクラス
 * @author T.Ikeda,
 *
 */
public class CommentDao {

	/**
	 * データベース接続オブジェクト
	 */
	private Connection conn;

	/**
	 * コンストラクタ
	 * @param transaction トランザクションオブジェクト
	 */

	public CommentDao(Transaction transaction) {
		this.conn = transaction.getConnection();
	}

	/**
	 * マッチングコメント情報を登録する
	 * @param comment
	 * @return 登録件数
	 * @throws IOException
	 */
	public int insert(Comment comment) throws IOException {
		int count = 0;

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("insert into comment ");
		sql.append("(id,companyno,kyujinno,jobseekerid,staffid,matchid,genre,"
				+ "important,title,note,createuserid,updateuserid");
		sql.append(") values (");
		sql.append("?,?,?,?,?,?,?,?,?,?,?,?");
		sql.append(")");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setInt(1,comment.getId());
			ps.setString(2,comment.getCompanyNo());
			ps.setString(3,comment.getKyujinNo());
			ps.setString(4,comment.getJobSeekerId());
			ps.setString(5,comment.getStaffId());
			ps.setInt(6,comment.getMatchId());
			ps.setString(7,comment.getGenre());
			ps.setString(8,comment.getImportant());
			ps.setString(9,comment.getTitle());
			ps.setString(10,comment.getNote());
			ps.setString(11,comment.getCreateUserId());
			ps.setString(12,comment.getUpdateUserId());

			// SQL文を実行する
			count = ps.executeUpdate();
		} catch (SQLException e) {
			throw new IOException(e);
		}

		return count;
	}

	/**
	 * マッチングコメント情報の更新
	 * @param comment 更新対象のデータ
	 * @return 更新行数 0じゃなければ更新成功
	 * @throws IOException
	 */
	public int update(Comment comment) throws IOException {
		int count = 0;

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("update comment set");
		sql.append(" companyno = ?,");
		sql.append(" kyujinno = ?,");
		sql.append(" jobseekerid = ?,");
		sql.append(" staffid = ?,");
		sql.append(" matchid = ?,");
		sql.append(" genre = ?,");
		sql.append(" important = ?,");
		sql.append(" title = ?,");
		sql.append(" note = ?,");
		sql.append(" updateuserid = ?");
		sql.append(" where");
		sql.append(" id = ?");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {

			ps.setString(1, comment.getCompanyNo());
			ps.setString(2, comment.getKyujinNo());
			ps.setString(3, comment.getJobSeekerId());
			ps.setString(4, comment.getStaffId());
			ps.setInt(5, comment.getMatchId());
			ps.setString(6, comment.getGenre());
			ps.setString(7, comment.getImportant());
			ps.setString(8, comment.getTitle());
			ps.setString(9, comment.getNote());
			ps.setInt(10, comment.getId());

			// SQL文を実行する
			count = ps.executeUpdate();
		} catch(SQLException e) {
			throw new IOException(e);
		}

		return count;
	}

	/**
	 * idを元にマッチングコメント情報のデータを取得する
	 * @param id
	 * @return 取得したデータ
	 * @throws IOException
	 */
	public Comment selectMatching(int id) throws IOException {
		Comment matchingComment = null;

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("select * "
				+ "from comment "
				+ "where id = ?; ");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setInt(1, id);

			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				// 取得結果をリストに格納する
				while(rs.next()) {
					matchingComment = new Comment(
							rs.getInt("id"),
							rs.getString("companyNo"),
							rs.getString("kyujinNo"),
							rs.getString("jobSeekerId"),
							rs.getString("staffId"),
							rs.getInt("matchId"),
							rs.getString("genre"),
							rs.getString("important"),
							rs.getString("title"),
							rs.getString("note"),
							rs.getDate("createDt"),
							rs.getString("createUserId"),
							rs.getDate("updateDt"),
							rs.getString("updateUserId")
							);
				}
			} catch(SQLException e) {
				throw new IOException(e);
			}
		} catch(SQLException e) {
			throw new IOException(e);
		}

		return matchingComment;
	}



}
