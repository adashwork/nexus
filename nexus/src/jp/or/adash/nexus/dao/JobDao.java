package jp.or.adash.nexus.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.or.adash.nexus.entity.Job;
import jp.or.adash.nexus.utils.dao.Transaction;

/**
 *  職種データアクセスクラス
 * @author aizawa
 *
 */
public class JobDao {

	/**
	 * データベース接続オブジェクト
	 */
	private Connection conn;

	/**
	 * コンストラクタ
	 * @param transaction トランザクションオブジェクト
	 */
	public JobDao(Transaction transaction) {
		this.conn = transaction.getConnection();
	}


	/**
	 * 職種の大分類一覧を取得する
	 * @return 職種大分類リスト
	 * @throws IOException
	 */
	public List<Job> selectLargeJobList() throws IOException {
		List<Job> LargeJobList = new ArrayList<Job>();

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append(" select largecd, name ");
		sql.append(" from job ");
		sql.append(" where middlecd = '0' and smallcd ='0' ");
		sql.append(" order by largecd; ");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				// 取得結果をリストに格納する
				while (rs.next()) {
					LargeJobList.add(new Job(rs.getString("largecd"),
							rs.getString("name")));
				}
			} catch (SQLException e) {
				throw new IOException(e);
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}

		return LargeJobList;
	}





	/**
	 * 職種小分類の一覧を取得する
	 * @return 職種小分類リスト
	 * @throws IOException
	 */
	public List<Job> selectSmallJobList() throws IOException {
		List<Job> SmallJobList = new ArrayList<Job>();

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("select smallcd, name ");
		sql.append(" from job ");
		sql.append(" where  middlecd != '0' and smallcd !='0' ");
		sql.append(" order by cast(smallcd as signed); ");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				// 取得結果をリストに格納する
				while (rs.next()) {
					SmallJobList.add(new Job(null, null, null, rs.getString("smallcd"),
							rs.getString("name")));
				}
			} catch (SQLException e) {
				throw new IOException(e);
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}

		return SmallJobList;
	}

}
