package jp.or.adash.nexus.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.or.adash.nexus.entity.CompanySearchResult;
import jp.or.adash.nexus.utils.dao.Transaction;

/**
 * 企業テーブルにアクセスするDAOクラス
 * @author
 *
 */

public class CompanyDao {

	/**
	 * データベース接続オブジェクト
	 */
	private Connection conn;

	/**
	 * コンストラクタ
	 * @param transaction トランザクションオブジェクト
	 */
	public CompanyDao(Transaction transaction) {
		this.conn = transaction.getConnection();
	}



	/**
	 * 検索条件に該当した企業情報を、簡易版で取得し、
	 * 結果をリストにまとめる
	 * @param companySearch 検索条件を格納したオブェクト
	 * @return companyList 商品検索結果のリスト
	 * @throws IOException
	 * @author mosco
	 */



	public List<CompanySearchResult> selectCompanyList(
			String staffId,String jobCategory,String[] companyName,String[] companyPlace
			) throws IOException {

		List<CompanySearchResult> companyList = new ArrayList<CompanySearchResult>();		// 検索結果の企業を格納するリスト
		int setFlag = 0;											// ？の番号を決めるための値
		int setFlagStaffId = 0;
		int setFlagJobCategory = 0;
		int setCompanyName = 0;
		int setCompanyPlace = 0;

		// SQLのWHERE句に条件を追加するためのリスト
		List<String> whereStr = new ArrayList<String>();

		// 企業名・単語を入れるためのリスト
		List<String> setWordList = new ArrayList<String>();		// 条件格納用
		List<Integer> setFlagList = new ArrayList<Integer>();		// フラグ番号用


		// 検索・選択項目ごとの入力があるかどうか。あれば"？"に番号を振ってWHERE句に条件追加

		// 担当者の選択
		if(staffId != null && !"".equals(staffId)) {
			whereStr.add("tantoustaff_id = ?");
			setFlagStaffId = ++setFlag;
		}

		// 産業大分類の選択
		if(jobCategory != null && !"".equals(jobCategory)) {
			whereStr.add("jobcategorylargecd = ?");
			setFlagJobCategory = ++setFlag;
		}

		// フラグ番号管理用のリストの初期値を↑までの値と同じにする


		// 企業名・カナの検索
		if(companyName != null && !companyName[0].equals("") ) {// 配列の中身が空の判定
			for(int i = 0; i < companyName.length; i++) {
				// SQLでは(cmp.companyname like '%単語%' or cmp.companykana like '%単語%')
				// 1単語につき+2フラグ、対応する（?=単語）が加算される
				whereStr.add("(cmp.companyname like concat('%',?,'%') or cmp.companykana like concat('%',?,'%'))");
				setWordList.add(companyName[i]); 				// 前半の？に入力する単語用
				setWordList.add(companyName[i]); 				// 後半（カナ検索）用
				setCompanyName = ++setFlag;						// 一個目の？の番号
				setFlagList.add(setCompanyName);
				setCompanyName = ++setFlag;						// 二個目の？の番号
				setFlagList.add(setCompanyName);
			}
		}

		// 所在地・最寄駅の検索
		if(companyPlace != null && !companyPlace[0].equals("") ){	// 配列の中身が空の判定
			for(int i = 0; i < companyPlace.length; i++) {
			 // SQLでは(cmp.companyplace like '%単語%' or cmp.nearstation like '%単語%')
				whereStr.add("(cmp.companyplace like concat('%',?,'%') or cmp.nearstation like concat('%',?,'%'))");
				setWordList.add(companyPlace[i]); 				// 前半の？に入力する単語用
				setWordList.add(companyPlace[i]); 				// 後半用
				setCompanyPlace = ++setFlag;					// 一個目の？の番号
				setFlagList.add(setCompanyPlace);
				setCompanyPlace = ++setFlag;					// 二個目の？の番号
				setFlagList.add(setCompanyPlace);
			}
		}


		// SQL文の作成
		StringBuilder sqlSearchCompany = new StringBuilder();
		sqlSearchCompany.append("SELECT cmp.companyno, cmp.companyname,jbc.name,cmp.companyplace,cmp.tantou");
		sqlSearchCompany.append(" from company cmp left join jobcategory jbc");
		sqlSearchCompany.append(" on cmp.jobcategorylargecd = jbc.largecd");
		sqlSearchCompany.append(" WHERE  deleteflag = 0 and jbc.middlecd = '0' and jbc.smallcd = '0'");
		if(setFlag != 0 ) {
			sqlSearchCompany.append(" and ");
			sqlSearchCompany.append(String.join(" and ", whereStr));
		}

		// TODO 18/12/17改ページ用にSQLを書き換える


		try(PreparedStatement ps = conn.prepareStatement(sqlSearchCompany.toString())){

			// 入力された値の？の番号と内容を入力
			// 担当ID
			if (setFlagStaffId != 0) {
				ps.setString(setFlagStaffId, staffId);
			}
			// 産業大分類コード
			if (setFlagJobCategory != 0) {
				ps.setString(setFlagJobCategory, jobCategory);
			}
			// 企業名／所在地・最寄り駅は同時に処理
			if(setCompanyName != 0 || setCompanyPlace != 0) {
				for(int i = 0; i<setWordList.size() ;i++) {
					ps.setString(setFlagList.get(i), setWordList.get(i));
				}
			}


			// SQLを実行する
			try(ResultSet rs = ps.executeQuery()) {
			// ResultSetから1行読み込む
			while(rs.next()) {
				// ResultSetからCompanyオブジェクトにデータを詰め直し、リストに格納
				companyList.add(
						new CompanySearchResult(	rs.getString("companyno"),
													rs.getString("companyname"),
													rs.getString("companyplace"),
													rs.getString("tantou"),
													rs.getString("name")
												));
			}
		} catch(SQLException e) {
			throw new IOException(e);
		}
	} catch(SQLException e) {
		throw new IOException(e);
	}

		return companyList;

	}


}
