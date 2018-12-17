package jp.or.adash.nexus.dao;

import java.sql.Connection;

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
	 * --企業検索--
	 *
	 * 検索条件に入力された値を、CompanySearchオブジェクトから取り出す
	 * 1.A’担当者の入力があったかどうか
	 * →あれば？の値が１増える
	 * 2.産業大分類の入力があったかどうか
	 *  * →あれば？の値が１増える
	 * 3.企業名・カナの入力があったかどうか
	 * 4.住所・最寄り駅の入力があったかどうか
	 */

	/**
	 * 検索条件に該当した企業情報を、簡易版で取得し、
	 * 結果をリストにまとめる
	 * @param companySearch 検索条件を格納したオブェクト
	 * @return companyList 商品検索結果のリスト
	 * @throws IOException
	 */

/*
	public List<Company> selectCompanyList(
			String staffId,String jobCategory,String[] companyName,String[] companyPlace
			) throws IOException {

		List<Company> companyList = new ArrayList<Company>();		// 検索結果の企業を格納するリスト
		int setFlag = 0;											// ？の番号を決めるための値
		int setFlagStaffId = 0;
		int setFlagJobCategory = 0;
		int setCompanyName = 0;
		int setCompanyPlace = 0;

		// SQLのWHERE句に条件を追加するためのリスト
		List<String> whereStr = new ArrayList<String>();

		// 企業名・単語を入れるためのリスト
		List<String> aaa = new ArrayList<String>();		// 条件文一文格納用
		List<Integer> bbb = new ArrayList<Integer>();		// フラグ番号用


		// 検索・選択項目ごとの入力があるかどうか。あれば"？"に番号を振ってWHERE句に条件追加

		// 担当者の選択
		if(staffId != null && "".equals(staffId)) {
			whereStr.add("staffId = ?");
			setFlagStaffId = ++setFlag;
		}

		// 産業大分類の選択
		if(jobCategory != null && "".equals(jobCategory)) {
			whereStr.add("jobCategory = ?");
			setFlagJobCategory = ++setFlag;
		}

		// フラグ番号管理用のリストの初期値を↑までの値と同じにする


		// 企業名・カナの検索
		if(companyName != null && companyName.length != 0) {// 配列の中身が空の判定
			for(int i = 0; i < companyName.length-1; i++) {
				// SQLでは(cmp.companyname like '%単語%' or cmp.companykana like '%単語%')
				// 1単語につき+2フラグ、対応する（?=単語）が加算される
				whereStr.add("cmp.companyname like concat('%',?,'%') or cmp.companykana like concat('%',?,'%')");
				aaa.add(companyName[i]); 				// 前半の？用
				aaa.add(companyName[i]); 				// 後半（カナ検索）の？用
				setCompanyName = ++setFlag;



			}
		}

		// 所在地・最寄駅の検索
		if(companyPlace != null && companyPlace.length != 0) {// 配列の中身が空の判定
			for(int i = 0; i < companyPlace.length-1; i++) {
			 // SQLでは(cmp.companyplace like '%単語%' or cmp.nearstation like '%単語%')
				whereStr.add("cmp.companyplace like concat('%',?,'%') or cmp.nearstation like concat('%',?,'%')");
				aaa.add(companyPlace[i]); 				// 前半の？用
				aaa.add(companyPlace[i]); 				// 後半の？用
			}
		}


		// SQL文の作成
		StringBuilder sqlSearchCompany = new StringBuilder();
		sqlSearchCompany.append("SELECT cmp.companyno, cmp.companyname,jbc.name,cmp.companyplace,cmp.tantou");
		sqlSearchCompany.append("from company cmp left join jobcategory jbc");
		sqlSearchCompany.append("on cmp.jobcategorylargecd = jbc.largecd");
		sqlSearchCompany.append("WHERE  deleteflag = 0");
		if(setFlag != 0 ) {
			sqlSearchCompany.append(" and ");
			sqlSearchCompany.append(String.join(" and ", whereStr));
		}



		try(PreparedStatement ps = conn.prepareStatement(sql)) {

			// SQLを実行する
			try(ResultSet rs = ps.executeQuery()) {
			// ResultSetから1行読み込む
			while(rs.next()) {
				// ResultSetからCompanyオブジェクトにデータを詰め直し、リストに格納
				companyList.add(
						new Company(
								rs.getString("companyno"),
								rs.getString("companyname"),
								rs.getString("companyno"),
								rs.getString("name"),
								rs.getString("tantou"),
								));


			}
		} catch(SQLException e) {
			throw new IOException(e);
		}
	} catch(SQLException e) {
		throw new IOException(e);
	}

		return companyList;*/




}
