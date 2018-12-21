package jp.or.adash.nexus.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.or.adash.nexus.entity.Comment;
import jp.or.adash.nexus.entity.CommentSearchParameter;
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

	/**
	 * companyNoを元に企業コメント情報のデータを取得する
	 * @param companyNo
	 * @return List<Comment> コメントの入ったリストを取得する
	 * @throws IOException
	 */
	public List<Comment> selectCompanyCommentList(String companyNo)throws IOException {
		List<Comment> companyCommentList = new ArrayList<Comment>();

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		sql.append(" select *  ");
		sql.append(" from comment  ");
		sql.append(" where companyno = ? ");
		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setString(1, companyNo);

			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				// 取得結果をリストに格納する
				while(rs.next()) {
					companyCommentList.add(new Comment(
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
							));
				}
			} catch(SQLException e) {
				throw new IOException(e);
			}
		} catch(SQLException e) {
			throw new IOException(e);
		}

		return companyCommentList;
	}



	/**
	 * 2018/12/20 kitayama 新規作成
	 * コメントを登録する
	 * @param comment コメントオブジェクト
	 * @return	count 登録行数
	 * @throws IOException
	 */
	public int insertV2(Comment comment) throws IOException {
		int count = 0;

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		// INSERT句
		sql.append(" INSERT INTO");
		sql.append(" comment");

		sql.append(" ( ");

		sql.append(" id ");
		sql.append(",companyno");
		sql.append(",kyujinno");
		sql.append(",jobseekerid");
		sql.append(",staffid");
		sql.append(",matchid");
		sql.append(",genre");
		sql.append(",important");
		sql.append(",title");
		sql.append(",note");
		sql.append(",createuserid");
		sql.append(",updateuserid");

		sql.append(")");

		// VALUE句
		sql.append(" VALUES (");

		sql.append(" ?");				// id
		sql.append(",NULLIF(?, '')");	// companyno
		sql.append(",NULLIF(?, '')");	// kyujinno
		sql.append(",NULLIF(?, '')");	// jobseekerid
		sql.append(",NULLIF(?, '')");	// staffid
		sql.append(",NULLIF(?, -1)");	// matchid
		sql.append(",NULLIF(?, '')");	// genre
		sql.append(",NULLIF(?, '')");	// important
		sql.append(",NULLIF(?, '')");	// title
		sql.append(",NULLIF(?, '')");	// note
		sql.append(",NULLIF(?, '')");	// createuserid
		sql.append(",NULLIF(?, '')");	// updateuserid

		sql.append(")");

		try (PreparedStatement ps = this.conn.prepareStatement(sql.toString())) {
			ps.setInt(1, comment.getId());
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
	 * 2018/12/20 kitayama 新規作成
	 * コメントを更新する
	 * @param comment コメントオブジェクト
	 * @return count 更新した行数
	 * @throws IOException
	 */
	public int updateV2(Comment comment) throws IOException {
		int count = 0;

		// SQL文を生成する
		StringBuffer sql = new StringBuffer();
		// UPDATE句
		sql.append(" UPDATE");
		sql.append(" comment");
		sql.append(" SET");

		// VALUES
		sql.append(" companyno = 	NULLIF(?, '')");
		sql.append(",kyujinno = 	NULLIF(?, '')");
		sql.append(",jobseekerid = 	NULLIF(?, '')");
		sql.append(",staffid = 		NULLIF(?, '')");
		sql.append(",matchid = 		NULLIF(?, -1)");
		sql.append(",genre = 		?");
		sql.append(",important = 	?");
		sql.append(",title = 		?");
		sql.append(",note = 		?");
		sql.append(",updateuserid = ?");
		sql.append(",updatedt = current_timestamp()");

		// WHERE句
		sql.append(" WHERE");
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
			ps.setString(10, comment.getUpdateUserId());
			ps.setInt(11, comment.getId());

			// SQL文を実行する
			count = ps.executeUpdate();
		} catch(SQLException e) {
			throw new IOException(e);
		}

		return count;
	}

	/**
	 * 備考（コメント）の一覧取得のためのメソッド
	 * 与えられたパラメータから該当するものを検索する
	 * @param CommentSearchParameter
	 * @return commentList
	 * @throws IOException
	 * @author mosco(2018/12/21完了)
	 */
	public List<Comment> selectCommentList(CommentSearchParameter csp) throws IOException{
		// 検索結果のコメント格納リスト
		List<Comment> commentList = new ArrayList<>();
		// SQLのWHERE句に条件を追加するためのリスト
		List<String> whereStr = new ArrayList<String>();
		// 各項目の？の番号を振るための変数
		int setFlag = 0;
		int setFlagCompanyNo = 0;
		int setFlagKyujinNo = 0;
		int setFlagStaffId = 0;
		int setFlagJobSeekerId = 0;
		int setFlagMatchId = 0;

		// 事業所番号の有無
		if(csp.getCompanyNo() != null && !(csp.getCompanyNo().equals(""))) {
			whereStr.add("companyno = ?");
			setFlagCompanyNo = ++setFlag;
		}

		// 求人NOの有無
		if(csp.getKyujinNo() != null && !(csp.getKyujinNo().equals(""))) {
			whereStr.add("kyujinno = ?");
			setFlagKyujinNo = ++setFlag;
		}

		// 職業紹介者IDの有無
		if(csp.getStaffId() != null && !(csp.getStaffId().equals(""))) {
			whereStr.add("staffid = ?");
			setFlagStaffId = ++setFlag;
		}

		// 求職者IDの有無
		if(csp.getJobSeekerId() != null && !(csp.getJobSeekerId().equals(""))) {
			whereStr.add("jobseekerid = ?");
			setFlagJobSeekerId = ++setFlag;
		}

		// マッチング事例IDの有無 : ない場合は呼び出し元サーブレット側で(-1)を代入させている
		if(csp.getMatchId() != null && csp.getMatchId() != -1) {
			whereStr.add("matchid = ?");
			setFlagMatchId = ++setFlag;
		}


		// SQL文の作成
		StringBuilder sqlSearchComment = new StringBuilder();
		sqlSearchComment.append("SELECT *");
		// sqlSearchComment.append(" id, companyno, kyujinno,");
		// sqlSearchComment.append(" jobseekerid, staffid, matchid, genre,");
		// sqlSearchComment.append(" important, title, note, createdt, createuserid,");
		// sqlSearchComment.append(" updatedt, updateuserid");
		sqlSearchComment.append(" from comment");
		if(setFlag != 0 ) {
			sqlSearchComment.append(" where ");
			sqlSearchComment.append(whereStr.get(0));
			if(setFlag >= 1) {
				for(int i = 1; i <= setFlag-1; i++) {
					sqlSearchComment.append(" and ");
					sqlSearchComment.append(whereStr.get(i));
				}
			}
		}

		try (PreparedStatement ps = conn.prepareStatement(sqlSearchComment.toString())) {

			if(setFlagCompanyNo != 0) {
				ps.setString(setFlagCompanyNo, csp.getCompanyNo());
			}
			if(setFlagKyujinNo != 0) {
				ps.setString(setFlagKyujinNo, csp.getKyujinNo());
			}
			if(setFlagStaffId != 0) {
				ps.setString(setFlagStaffId, csp.getStaffId());
			}
			if(setFlagJobSeekerId != 0) {
				ps.setString(setFlagJobSeekerId, csp.getJobSeekerId());
			}
			if(setFlagMatchId != 0) {
				ps.setInt(setFlagMatchId, csp.getMatchId());
			}

			// SQLを実行する
			try (ResultSet rs = ps.executeQuery()) {
				// ResultSetから1行読み込む
				while (rs.next()) {
					// ResultSetからCommentオブジェクトにデータを詰め直し、リストに格納
					commentList.add(
							new Comment(rs.getInt("id"),				// 備考ID
										rs.getString("companyno"),		// 事業所番号
										rs.getString("kyujinno"),		// 求人NO
										rs.getString("jobseekerid"),	// 求職者ID
										rs.getString("staffid"),		// 職業紹介者ID
										rs.getInt("matchid"),			// マッチング事例ID
										rs.getString("genre"),			// 内容分類
										rs.getString("important"),		// 重要アラート
										rs.getString("title"),			// 件名
										rs.getString("note"),			// 備考
										rs.getDate("createdt"),			// 新規登録日
										rs.getString("createuserid"),	// 新規登録ユーザー
										rs.getDate("updatedt"),			// 最終更新日
										rs.getString("updateuserid")	// 最終更新ユーザー
										));
				}
			} catch (SQLException e) {
				throw new IOException(e);
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}
		return commentList;
	}

	/**
	 * IDから該当するコメントを一件取得
	 * @param id
	 * @return Comment
	 * @throws IOException
	 * @author mosco(2018/12/21完了)
	 */
	public Comment selectComment(int id) throws IOException{

		Comment comment = null;
		// SQL文の作成
		StringBuilder sqlSearchComment = new StringBuilder();
		sqlSearchComment.append("SELECT *");
		sqlSearchComment.append(" from comment");
		sqlSearchComment.append(" where id = ?");

		try (PreparedStatement ps = conn.prepareStatement(sqlSearchComment.toString())) {
			ps.setInt(1, id);

			// SQLを実行する
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
				comment =
						new Comment(1,		// rs.getInt("id"),				// 備考ID
								rs.getString("companyno"),		// 事業所番号
								rs.getString("kyujinno"),		// 求人NO
								rs.getString("jobseekerid"),	// 求職者ID
								rs.getString("staffid"),		// 職業紹介者ID
								rs.getInt("matchid"),			// マッチング事例ID
								rs.getString("genre"),			// 内容分類
								rs.getString("important"),		// 重要アラート
								rs.getString("title"),			// 件名
								rs.getString("note"),			// 備考
								rs.getDate("createdt"),			// 新規登録日
								rs.getString("createuserid"),	// 新規登録ユーザー
								rs.getDate("updatedt"),			// 最終更新日
								rs.getString("updateuserid")	// 最終更新ユーザー
								);
				}

				} catch (SQLException e) {
						throw new IOException(e);
				}
			} catch (SQLException e) {
					throw new IOException(e);
			}
			return comment;
		}


}
