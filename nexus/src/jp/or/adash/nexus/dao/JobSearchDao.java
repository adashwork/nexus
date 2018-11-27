package jp.or.adash.nexus.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.or.adash.nexus.entity.SimpleKyujin;
import jp.or.adash.nexus.utils.dao.Transaction;

/**
 * 求人データアクセスクラス
 * @author ???
 * @author pgjavaAT
 *
 */
public class JobSearchDao {
/*
 * TODO 検索サーブレットの作成（今は初期表示サーブレットと兼用している）
 * TODO　検索項目のデータベース利用（業種マスタでjobnameを検索してコード取得、kyujinマスタを検索）
 * TODO　路線の選択
 *
 */
	/**
	 * データベース接続オブジェクト
	 */
	private Connection conn;

	/**
	 * コンストラクタ
	 * @param transaction トランザクションオブジェクト
	 */
	public JobSearchDao(Transaction transaction) {
		this.conn = transaction.getConnection();
	}

	/**
	 * 求人票の一覧を簡易版で取得する
	 * @throws IOException
	 */
	public List<SimpleKyujin> selectKyujin(String job, String addresscd,
			String jobsmallcd1, String jobsmallcd2,String jobsmallcd3,
			String joblargecd1, String joblargecd2, String joblargecd3,
			String jobcategorysmallcd,String jobcategorylargecd,
			String koyoukeitaicd,int salarymin, int salarymax)
			throws IOException {
		List<SimpleKyujin> kyujinlist = new ArrayList<SimpleKyujin>();

		//where句を付け足す処理
		int setFlag = 0;
		int setFlagJobsmallcd1 = 0;
		int setFlagJobsmallcd2 = 0;
		int setFlagJobsmallcd3 = 0;
		int setFlagJoblargecd1 = 0;
		int setFlagJoblargecd2 = 0;
		int setFlagJoblargecd3 = 0;
		int setFlagJob = 0;
		int setFlagAddresscd = 0;
		int setFlagKoyoukeitaicd = 0;
		int setFlagSalarymin = 0;
		int setFlagSalarymax = 0;

		List<String> whereStr = new ArrayList<String>();

		if (jobsmallcd1 != null && !"".equals(jobsmallcd1)) {
			whereStr.add("jobsmallcd1 = ?");
			setFlagJobsmallcd1 = ++setFlag;
		}

		if (jobsmallcd2 != null && !"".equals(jobsmallcd2)) {
			whereStr.add("jobsmallcd2 = ?");
			setFlagJobsmallcd2 = ++setFlag;
		}

		if (jobsmallcd3 != null && !"".equals(jobsmallcd3)) {
			whereStr.add("jobsmallcd3 = ?");
			setFlagJobsmallcd3 = ++setFlag;
		}

		if (joblargecd1 != null && !"".equals(joblargecd1)) {
			whereStr.add("joblargecd1 = ?");
			setFlagJoblargecd1 = ++setFlag;
		}

		if (joblargecd2 != null && !"".equals(joblargecd2)) {
			whereStr.add("joblargecd2 = ?");
			setFlagJoblargecd2 = ++setFlag;
		}

		if (joblargecd3 != null && !"".equals(joblargecd3)) {
			whereStr.add("joblargecd3 = ?");
			setFlagJoblargecd3 = ++setFlag;
		}

		if (job != null && !"".equals(job)) {
			whereStr.add("job = ?");
			setFlagJob = ++setFlag;
		}
		if (addresscd != null && !"".equals(addresscd)) {
			whereStr.add("addresscd = ?");
			setFlagAddresscd = ++setFlag;
		}
		if (koyoukeitaicd != null && !"".equals(koyoukeitaicd)) {
			whereStr.add("koyoukeitaicd = ?");
			setFlagKoyoukeitaicd = ++setFlag;
		}
		if (salarymin != 0) {
			whereStr.add("salarymin >= ?");
			setFlagSalarymin = ++setFlag;
		}
		if (salarymax != 0) {
			whereStr.add("salarymax <= ?");
			setFlagSalarymax = ++setFlag;
		}


		// SQL文の作成
		StringBuilder sqlSearchJob = new StringBuilder();
		sqlSearchJob.append("select no, companyno,addresscd, jobsmallcd1,jobsmallcd2,jobsmallcd3,");
		sqlSearchJob.append("joblargecd1,joblargecd2,joblargecd3,");
		sqlSearchJob.append("jobcategorysmallcd,jobcategorylargecd,");
		sqlSearchJob.append("companyname,address,addresscd,nearstation,job,koyoukeitaicd, ");
		sqlSearchJob.append("agemin,agemax,salarymin,salarymax,deleteflag ");
		sqlSearchJob.append(" from kyujin");
		sqlSearchJob.append(" where deleteflag like '0'");
		if(setFlag != 0 ) {
			sqlSearchJob.append(" and ");
		sqlSearchJob.append(String.join(" and ", whereStr));
		}
		try (PreparedStatement ps = conn.prepareStatement(sqlSearchJob.toString())) {

			if (setFlagJobsmallcd1 != 0) {
				ps.setString(setFlagJobsmallcd1, jobsmallcd1);
			}

			if (setFlagJobsmallcd2 != 0) {
				ps.setString(setFlagJobsmallcd2, jobsmallcd2);
			}

			if (setFlagJobsmallcd3 != 0) {
				ps.setString(setFlagJobsmallcd3, jobsmallcd3);
			}

			if (setFlagJoblargecd1 != 0) {
				ps.setString(setFlagJoblargecd1, joblargecd1);
			}

			if (setFlagJoblargecd2 != 0) {
				ps.setString(setFlagJoblargecd2, joblargecd2);
			}

			if (setFlagJoblargecd3 != 0) {
				ps.setString(setFlagJoblargecd3, joblargecd3);
			}

			if (setFlagJob != 0) {
				ps.setString(setFlagJob, job);
			}
			if (setFlagAddresscd != 0) {
				ps.setString(setFlagAddresscd, addresscd);
			}
			if (setFlagKoyoukeitaicd != 0) {
				ps.setString(setFlagKoyoukeitaicd, koyoukeitaicd);
			}
			if (setFlagSalarymin != 0) {
				ps.setInt(setFlagSalarymin, salarymin);
			}
			if (setFlagSalarymax != 0) {
				ps.setInt(setFlagSalarymax, salarymax);
			}

			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				// 1.取得結果をリストに格納する
				while (rs.next()) {
					kyujinlist.add(
							new SimpleKyujin(rs.getString("no"), rs.getString("companyno"),
									rs.getString("jobsmallcd1"), rs.getString("jobsmallcd2"),
									rs.getString("jobsmallcd3"),
									rs.getString("joblargecd1"), rs.getString("joblargecd2"),
									rs.getString("joblargecd3"),
									rs.getString("jobcategorysmallcd"),rs.getString("jobcategorylargecd"),
									rs.getString("companyname"), rs.getString("address"), rs.getString("addresscd"),
									rs.getString("nearstation"), rs.getString("job"), rs.getString("koyoukeitaicd"),
									rs.getInt("agemin"), rs.getInt("agemax"), rs.getInt("salarymin"),
									rs.getInt("salarymax"), rs.getString("deleteflag")));
				}
			} catch (SQLException e) {
				throw new IOException(e);
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}

		return kyujinlist;
	}
}
