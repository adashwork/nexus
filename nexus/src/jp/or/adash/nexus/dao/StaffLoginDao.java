package jp.or.adash.nexus.dao;
/**
* Staffデータアクセスクラス
* @author S.Okuno
*
*/

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.or.adash.nexus.entity.Staff;
import jp.or.adash.nexus.utils.dao.Transaction;

public class StaffLoginDao {
	/**
	 * データベース接続オブジェクト
	 */
	private Connection conn;

	/**
	 * コンストラクタ
	 * @param transaction トランザクションオブジェクト
	 */
	public  StaffLoginDao(Transaction transaction) {
		this.conn = transaction.getConnection();
	}

	/**
	 *jsp入力情報を元に、IDとPASSWord情報（1件）を取得する
	 * @param id スタッフのID
	 * @return Loginオブジェクト
	 * @throws IOException
	 */
	public Staff getLogin(String user,String pass) throws IOException {
		Staff userData = null;

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("select id,name");
		sql.append(" from staff");
		sql.append(" where id = ? and passWord = ?");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			/*検索する要素*/
			ps.setString(1, user);
			ps.setString(2, pass);

			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				// 取得結果をリストに格納する
				while (rs.next()) {

					userData = new Staff(
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
							);

				}
			} catch (SQLException e) {
				throw new IOException(e);

			}
		} catch (SQLException e) {
			throw new IOException(e);
		}

		return userData;
	}

}
