package jp.or.adash.nexus.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.or.adash.nexus.entity.Staff;
import jp.or.adash.nexus.utils.dao.Transaction;


/**
 * スタッフデータアクセスクラス
 * @author mmiyamoto
 *
 */
public class StaffDao {

	/**
	 * データベース接続オブジェクト
	 */
	private Connection conn;

	/**
	 * コンストラクタ
	 * @param transaction トランザクションオブジェクト
	 */
	public StaffDao(Transaction transaction) {
		this.conn = transaction.getConnection();
	}


	public Staff selectStaffName(String staffId) throws IOException {
		Staff staff = null;

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("select * ");
		sql.append(" from staff ");
		sql.append(" where id = ? ");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			/*検索する要素*/
			ps.setString(1, staffId);


			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				// 取得結果をリストに格納する
				while (rs.next()) {

					staff = new Staff(
							rs.getString("id"),
							rs.getString("name"),
							rs.getString("kana"),
							rs.getString("authority"),
							rs.getString("password"),
							rs.getDate("createdt"),
							rs.getString("createuserid"),
							rs.getDate("updatedt"),
							rs.getString("updateuserid"),
							rs.getString("deleteflag")
							);
				}
			} catch (SQLException e) {
				throw new IOException(e);

			}
		} catch (SQLException e) {
			throw new IOException(e);
		}

		return staff;
	}

	/**
	 *  スタッフ一覧を取得する
	 * @return スタッフのリスト
	 * @throws IOException
	 */
	public List<Staff> selectStaffList() throws IOException {
		List<Staff> staffList = new ArrayList<Staff>();

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("select id, name ");
		sql.append(" from staff ");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			/*検索する要素*/

			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				// 取得結果をリストに格納する
				while (rs.next()) {

					staffList.add(new Staff(
							rs.getString("id"),
							rs.getString("name"),
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null
							));
				}
			} catch (SQLException e) {
				throw new IOException(e);

			}
		} catch (SQLException e) {
			throw new IOException(e);
		}

		return staffList;
	}


}
