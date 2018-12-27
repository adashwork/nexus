package jp.or.adash.nexus.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.or.adash.nexus.entity.MatchingCase;
import jp.or.adash.nexus.entity.MatchingSearchParameter;
import jp.or.adash.nexus.entity.MatchingSearchResult;
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
		sql.append("(id,companyno,kyujinno,jobseekerid,staffid,interviewdt,enterdt,"		// 追加・修正 2018/12/11,12,14 T.Ikeda
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
							rs.getString("companyno"),		// 追加・修正 2018/12/11,12,14 T.Ikeda
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
		sql.append(" companyno = ?,");				// 追加・修正 2018/12/11,12,14 T.Ikeda
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

			ps.setString(1, matching.getCompanyNo());
			ps.setString(2, matching.getKyujinno());
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

	// 2018/12/18 kitayama 新規追加
	/**
	 * マッチング事例の取得（単一行）
	 * @param id マッチングID
	 * @return MatchingSearchResult マッチング事例オブジェクト
	 * @throws IOException
	 */
	public MatchingSearchResult selectV1(int id) throws IOException {
		MatchingSearchResult matching = null;

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		// SELECT句
		sql.append(" SELECT");

		sql.append(" m.id");
		sql.append(",m.companyno");
		sql.append(",m.kyujinno");
		sql.append(",m.jobseekerid");
		sql.append(",m.staffid");
		sql.append(",m.interviewdt");
		sql.append(",m.enterdt");
		sql.append(",m.assessment");
		sql.append(",c.important");
		sql.append(",c.title");
		sql.append(",c.note");
		sql.append(",m.createdt");
		sql.append(",m.createuserid");
		sql.append(",m.updatedt");
		sql.append(",m.updateuserid");

		// FROM句
		// テーブル名に別名をつける
		// matchingcase = m
		// comment 		= c
		sql.append(" FROM");
		sql.append(" matchingcase m");
		sql.append(" LEFT JOIN");
		sql.append(" comment c");
		sql.append(" ON");
		sql.append(" m.id = c.matchid");

		// WHERE句
		sql.append(" WHERE");
		sql.append(" COALESCE(m.id, '') = COALESCE(NULLIF(?, -1), COALESCE(m.id, ''))");

		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			// 各項目に検索条件を置く
			ps.setInt(1, id);

			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				// 取得結果をオブジェクトに格納する
				while(rs.next()) {
					matching = new MatchingSearchResult(
						rs.getInt("id"),
						rs.getString("companyno"),
						rs.getString("kyujinno"),
						rs.getString("jobseekerid"),
						rs.getString("staffid"),
						rs.getDate("interviewdt"),
						rs.getDate("enterdt"),
						rs.getString("assessment"),
						rs.getString("important"),
						rs.getString("title"),
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



	// 2018/12/17 kitayama 新規追加
	/**
	 * マッチング事例の取得（複数行）
	 * @param msp 検索条件オブジェクト
	 * @return MatchingCase マッチング事例オブジェクト
	 * @throws IOException
	 */
	public List<MatchingSearchResult> selectV2(MatchingSearchParameter msp) throws IOException {
		List<MatchingSearchResult> matching = new ArrayList<MatchingSearchResult>();

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		// SELECT句
		sql.append(" SELECT");

		sql.append(" m.id");
		sql.append(",m.companyno");
		sql.append(",m.kyujinno");
		sql.append(",m.jobseekerid");
		sql.append(",m.staffid");
		sql.append(",m.interviewdt");
		sql.append(",m.enterdt");
		sql.append(",m.assessment");
		sql.append(",c.important");
		sql.append(",c.title");
		sql.append(",c.note");
		sql.append(",m.createdt");
		sql.append(",m.createuserid");
		sql.append(",m.updatedt");
		sql.append(",m.updateuserid");

		// FROM句
		// テーブル名に別名をつける
		// matchingcase = m
		// comment 		= c
		sql.append(" FROM");
		sql.append(" matchingcase m");
		sql.append(" LEFT JOIN");
		sql.append(" comment c");
		sql.append(" ON");
		sql.append(" m.id = c.matchid");

		// WHERE句
		// フィールドは必ずNULL以外が入っているようにする。NULLの場合は空文字で置換する
		// 検索条件が空欄の場合は全行一致するようにする（空文字と空文字で比較する）
		sql.append(" WHERE");
		sql.append("     COALESCE(m.id, '')             = COALESCE(NULLIF(?, -1), COALESCE(m.id, ''))");
		sql.append(" AND COALESCE(m.companyno, '')   LIKE COALESCE(NULLIF(?, ''), COALESCE(m.companyno, ''))");
		sql.append(" AND COALESCE(m.jobseekerid, '') LIKE COALESCE(NULLIF(?, ''), COALESCE(m.jobseekerid, ''))");
		sql.append(" AND COALESCE(m.staffid, '')     LIKE COALESCE(NULLIF(?, ''), COALESCE(m.staffid, ''))");
		// フリーワード検索部分は検索条件の数（splitした数）分検索する
		sql.append(" AND ( ");
		for(int i = 0; msp.getWord().length > i; i++){
			if(1 > i) {
				sql.append("    COALESCE(c.note, '') LIKE COALESCE(NULLIF(?, ''), COALESCE(c.note, ''))");
			}else {
				sql.append(" OR COALESCE(c.note, '') LIKE COALESCE(NULLIF(?, ''), COALESCE(c.note, ''))");
			}
		}
		sql.append(" ) ");

		// ORDERBY句
		sql.append(" ORDER BY ");
		sql.append(" c.important DESC");
		sql.append(",m.updatedt DESC");
//		sql.append("");

		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			// 各項目に検索条件を置く
			ps.setInt(1, msp.getMatchingid());
			ps.setString(2, "%" + msp.getCompanyId() + "%");
			ps.setString(3, "%" + msp.getJobseekerid() + "%");
			ps.setString(4, "%" + msp.getStaffid() + "%");
			for(int i = 0; msp.getWord().length > i; i++) {
				ps.setString((5 + i), "%" + msp.getWord()[i] + "%");
			}

			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				// 取得結果をオブジェクトに格納する
				while(rs.next()) {
					matching.add(
						new MatchingSearchResult(
							rs.getInt("id"),
							rs.getString("companyno"),
							rs.getString("kyujinno"),
							rs.getString("jobseekerid"),
							rs.getString("staffid"),
							rs.getDate("interviewdt"),
							rs.getDate("enterdt"),
							rs.getString("assessment"),
							rs.getString("important"),
							rs.getString("title"),
							rs.getString("note"),
							rs.getDate("createdt"),
							rs.getString("createuserid"),
							rs.getDate("upDatedt"),
							rs.getString("updateuserid")
						)
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
}



