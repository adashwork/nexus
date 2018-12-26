package jp.or.adash.nexus.dao;

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
				int getsaiban = -1;
				if(rs.next()) {
					getsaiban = rs.getInt("kyujinsaiban") +1;

					// インクリメントした値で　采番マスタ更新
					updatesaiban(getsaiban);
				}
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
	public void updatesaiban(int getsaiban) throws IOException {

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("update saiban set");
		sql.append(" kyujinsaiban = ");
		sql.append("?");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setInt(1, getsaiban);
			// SQL文を実行する
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}


	/**
	 * 求職者采番マスタをの情報を取得
	 * @param saiban 現在の番号取得
	 * @throws IOException
	 */
	public int getseeker() throws IOException {

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("select jobseekersaiban");
		sql.append(" from saiban");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {

			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				// 番号を返す
				int getsaiban = -1;
				if(rs.next()) {
					getsaiban = rs.getInt("jobseekersaiban") +1;

					// インクリメントした値で　采番マスタ更新
					updateseeker(getsaiban);
				}
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
	public void updateseeker(int getsaiban) throws IOException {

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("update saiban set");
		sql.append(" jobseekersaiban = ");
		sql.append("?");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setInt(1, getsaiban);
			// SQL文を実行する
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}


	/**
	 * スタッフアカウントidを取得
	 * @throws IOException
	 */
	public int getAccountId() throws IOException {

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("select staffsaiban");
		sql.append(" from saiban");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {

			int getsaiban = -1;
			// SQL文を実行する  複数取れたレコードの中から
			try (ResultSet rs = ps.executeQuery()) {
				// 番号を返す　カーソルを１レコード目に移した
				if(rs.next()) {
					getsaiban = rs.getInt("staffsaiban") + 1;
					// インクリメントした値で　采番マスタ更新
					updateAccountId(getsaiban);
				}
				return getsaiban;

			} catch (SQLException e) {
				throw new IOException(e);
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	/**
	 * スタッフアカウントidを更新する
	 * @param accountId スタッフアカウントid
	 * @throws IOException
	 */
	public void updateAccountId(int accountId) throws IOException {

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("update saiban set staffsaiban = ?");
		//sql
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setInt(1, accountId);
		// SQL文を実行する
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new IOException(e);
		}

	}

	/**
	 * マッチングidを取得する
	 * @return getsaiban 現在の採番データに+1をして返す
	 * @throws IOException
	 */
	public int getMatching() throws IOException {

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("select matchingsaiban");
		sql.append(" from saiban");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {

			int getsaiban = -1;
			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				if(rs.next()) {
					getsaiban = rs.getInt("matchingsaiban") + 1;
					// 採番マスタの更新
					updateMatching(getsaiban);
				}
				return getsaiban;

			} catch (SQLException e) {
				throw new IOException(e);
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	/**
	 * マッチングidを更新する
	 * @param accountId スタッフアカウントid
	 * @throws IOException
	 */
	public void updateMatching(int matchingId) throws IOException {

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("update saiban set matchingsaiban = ?");
		//sql
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setInt(1, matchingId);
		// SQL文を実行する
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new IOException(e);
		}

	}

	/**
	 * 独自のcompanyNoを生成するための
	 * ユニークな整数を返す
	 * 値生成と同時に現在の採番データに+1を足す
	 * @return int saiban 整数
	 * @throws IOException
	 */
	public int getCompanyInt() throws IOException {

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("select companysaiban");
		sql.append(" from saiban");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {

			int getsaiban = -1;
			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				if(rs.next()) {
					getsaiban = rs.getInt("companysaiban") + 1;
					// 採番マスタの更新
					updateCompanySaiban(getsaiban);
				}
				return getsaiban;

			} catch (SQLException e) {
				throw new IOException(e);
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	/**
	 * companysaibanを更新する
	 * @param accountId companysaiban
	 * @throws IOException
	 */
	public void updateCompanySaiban(int currentCompanySaiban) throws IOException {

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("update saiban set companysaiban = ?");
		//sql
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setInt(1, currentCompanySaiban);
		// SQL文を実行する
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new IOException(e);
		}

	}


	/**
	 * 備考コメントにIDを振る
	 * 値生成と同時に現在の採番データに+1を足す
	 * @return int saiban 整数
	 * @throws IOException
	 */
	public int getCommentInt() throws IOException {

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("select commentsaiban");
		sql.append(" from saiban");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {

			int getsaiban = -1;
			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				if(rs.next()) {
					getsaiban = rs.getInt("commentsaiban") + 1;
					// 採番マスタの更新
					updateCommentSaiban(getsaiban);
				}
				return getsaiban;

			} catch (SQLException e) {
				throw new IOException(e);
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	/**
	 * commentsaibanを更新する
	 * @param accountId companysaiban
	 * @throws IOException
	 */
	public void updateCommentSaiban(int currentCommentSaiban) throws IOException {

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append("update saiban set commentsaiban = ?");
		//sql
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setInt(1, currentCommentSaiban);
		// SQL文を実行する
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new IOException(e);
		}

	}



}
