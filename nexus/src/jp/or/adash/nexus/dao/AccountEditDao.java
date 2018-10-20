package jp.or.adash.nexus.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.or.adash.nexus.entity.Staff;
import jp.or.adash.nexus.utils.dao.Transaction;


public class AccountEditDao {
	/**
	 * データベース接続オブジェクト
	 */
	private Connection conn;

	/**
	 * コンストラクタ
	 * @param transaction トランザクションオブジェクト
	 */
	public AccountEditDao(Transaction transaction) {
		this.conn = transaction.getConnection();
	}
	/**
	 * アカウントを更新する
	 * @param staff アカウント情報
	 * @return 更新件数
	 * @author H.Tomonari
	 */
	public int update(Staff staff) throws IOException {
		int count = 0;

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("update staff set");
		sql.append(" name = ?,");
		sql.append(" kana = ?,");
		sql.append(" authority = ?,");
		sql.append(" password = ?");
		sql.append(" where");
		sql.append(" id = ?");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {

			ps.setString(1, staff.getName());
			ps.setString(2, staff.getKana());
			ps.setString(3, staff.getAuthority());
			ps.setString(4, staff.getPassword());
			ps.setString(5, staff.getId());

			// SQL文を実行する
			count = ps.executeUpdate();
		} catch(SQLException e) {
			throw new IOException(e);
		}

		return count;
	}
	/**
	 * アカウントIDを元に、アカウント情報（1件）を取得する
	 * @param id アカウントID
	 * @return スタッフオブジェクト
	 * @author H.Tomonari
	 */
	public Staff selectStaff(String id) throws IOException {
		Staff staff = null;

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("select id,name,kana,authority,password");
		sql.append(" from staff");
		sql.append(" where id = ?");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setString(1, id);

			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				//0取得結果をリストに格納する
				while(rs.next()) {
					staff= new Staff(
							rs.getString("id"),
							rs.getString("name"),
							rs.getString("kana"),
							rs.getString("authority"),
							rs.getString("password"),
							null,
							null,
							null,
							null,
							null
							);

				}
			} catch(SQLException e) {
				throw new IOException(e);
			}
		} catch(SQLException e) {
			throw new IOException(e);
		}

		return staff;
	}
	/**
	 * アカウントIDを元にアカウントに削除フラグを立てる
	 * @param staff アカウント情報
	 * @return 更新件数
	 * @author H.Tomonari
	 */
	public int delete(String id) throws IOException {
		int count = 0;

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("update staff set");
		sql.append(" deleteflag = 1");
		sql.append(" where");
		sql.append(" id = ?");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setString(1, id);

			// SQL文を実行する
			count = ps.executeUpdate();
		} catch (SQLException e) {
			throw new IOException(e);
		}

		return count;
	}
}
