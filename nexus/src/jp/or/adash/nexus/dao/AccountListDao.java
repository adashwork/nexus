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

public class AccountListDao {
	/**
	 * データベース接続オブジェクト
	 */
	private Connection conn;

	/**
	 * コンストラクタ
	 * @param transaction トランザクションオブジェクト
	 */
	public AccountListDao(Transaction transaction) {
		this.conn = transaction.getConnection();
	}
	/**
	 * アカウントの情報一覧を取得する
	 * @return アカウントリスト
	 * @author H.Tomonari
	 */
	public List<Staff> selectAccountList() throws IOException {
		List<Staff> account = new ArrayList<Staff>();


		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id,name,kana,authority,password");
		sql.append(" from staff");
		sql.append(" where deleteflag = 0");
		sql.append(" order by id");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				//0取得結果をリストに格納する
				while(rs.next()) {
					account.add(new Staff
							(rs.getString("id"),
							rs.getString("name"),
							rs.getString("kana"),
							rs.getString("authority"),
							rs.getString("password"),
							null,
							null,
							null,
							null,
							null));
				}
			} catch(SQLException e) {
				throw new IOException(e);
			}
		} catch(SQLException e) {
			throw new IOException(e);
		}
		return account;
	}











}
