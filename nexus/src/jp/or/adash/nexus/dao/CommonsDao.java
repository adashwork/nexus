package jp.or.adash.nexus.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.or.adash.nexus.utils.dao.Transaction;

/**
 * 共通で使用するデータベースアクセスクラス
 * @author pgjavaAT
 *
 */
public class CommonsDao {

	/**
	 * データベース接続オブジェクト
	 */
	private Connection conn;

	/**
	 * コンストラクタ
	 * @param transaction トランザクションオブジェクト
	 */
	public CommonsDao(Transaction transaction) {
		this.conn = transaction.getConnection();
	}

	/**
	 * 求人が存在しているか確認する
	 * @param kyujinno
	 * @return
	 * @throws IOException
	 */
	public boolean selectKyujinno(String kyujinno) throws IOException {
		boolean result = false;
		int  count = 0;

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) as cnt "
				+ "from kyujin "
				+ "where no = ? ");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setString(1, kyujinno);

			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					count = rs.getInt("cnt");
				}
				if(count != 0) {
					result = true;
				}
			} catch (SQLException e) {
				throw new IOException(e);
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}
		return result;
	}

	/**
	 * 求職者IDがDBに存在しているか確認する
	 * @param jobseekerid
	 * @return
	 * @throws IOException
	 */
	public boolean selectJobseeker(String jobseekerid) throws IOException {
		boolean result = false;
		int count = 0;
		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) as cnt "
				+ "from jobseeker "
				+ "where  id = ?; ");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setString(1, jobseekerid);

			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					count = rs.getInt("cnt");
				}
				if(count != 0) {
					result = true;
				}
			} catch (SQLException e) {
				throw new IOException(e);
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}
		return result;
	}

	public String getStaffName(String id) throws IOException {

		StringBuffer sql = new StringBuffer();
		sql.append("select  name "
				+ "from staff "
				+ "where  id = ?; ");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setString(1, id);

			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					 return rs.getString("name");
				}
			} catch (SQLException e) {
				throw new IOException(e);
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}
		return null;
	}
}
