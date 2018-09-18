package jp.or.adash.nexus.kyujin.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.or.adash.nexus.utils.dao.Transaction;


/**
 *　采番データアクセスクラス
 */
public class SaibanDao {

	/**
	 * データベース接続オブジェクト
	 */
	private Connection conn;

	/**
	 * コンストラクタ
	 * @param transaction トランザクションオブジェクト
	 */
	public SaibanDao(Transaction transaction) {
		this.conn = transaction.getConnection();
	}

	/**
	 * 采番マスタをの情報を取得
	 * @param saiban 現在の番号取得
	 * @throws IOException
	 */
	public int getsaiban() throws IOException {

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("select kyujinsaiban");
		sql.append(" from saiban");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {

			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				// 番号を返す
				int getsaiban = rs.getInt("kyujinsaiban") + 1;

				// インクリメントした値で　采番マスタ更新
				update(getsaiban);

				return getsaiban;

			} catch (SQLException e) {
				throw new IOException(e);
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	/**
	 * 采番マスタを更新する
	 * @throws IOException
	 */
	public void update(int saiban) throws IOException {

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("update saiban set");
		sql.append(" kyujinsaiban = saiban");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {

			// SQL文を実行する
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new IOException(e);
		}

	}
}
