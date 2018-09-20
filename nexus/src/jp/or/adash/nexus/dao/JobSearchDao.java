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

public class JobSearchDao {
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
	 * 商品コードを元に、商品情報（1件）を取得する
	 * @param itemNo 商品番号
	 * @return 商品オブジェクト
	 * @throws IOException
	 */
	public List<SimpleKyujin> selectKyujin(String job, String jobcategory,String addresscd,
			int salarymin, int salarymax, String koyoukeitaicd)
			throws IOException {
		List<SimpleKyujin> kyujinlist = new ArrayList<SimpleKyujin>();

		//where句を付け足す処理
		int setFlag = 0;
		int setFlagJob = 0;
		int setFlagjobcategory = 0;
		int setFlagSalarymin = 0;
		int setFlagSalarymax = 0;
		int setFlagKoyoukeitaicd = 0;

		List<String> whereStr = new ArrayList<String>();
		try (PreparedStatement ps = this.conn.prepareStatement(whereStr.toString())) {


			if (job != null && !"".equals(job)) {
				whereStr.add("job = %?%");
				setFlagJob = ++setFlag;
			}

			if (job != null && !"".equals(jobcategory)) {
				whereStr.add("jobcategory = %?%");
				setFlagjobcategory = ++setFlag;
			}

			if (salarymin != 0 && !"".equals(salarymin)) {
				whereStr.add("salarymin => ?");
				setFlagSalarymin = ++setFlag;
			}
			if (salarymin != 0 && !"".equals(salarymax)) {
				whereStr.add("salarymax =< ?");
				setFlagSalarymax = ++setFlag;
			}
			if (koyoukeitaicd != null && !"".equals(koyoukeitaicd)) {
				whereStr.add("koyoukeitaicd = ?");
				setFlagKoyoukeitaicd = ++setFlag;
			}

			if (setFlagJob != 0) {
				ps.setString(setFlagJob, job);
			}
			if (setFlagJob != 0) {
				ps.setString(setFlagjobcategory, jobcategory);
			}

			if (setFlagSalarymin != 0) {
				ps.setInt(setFlagSalarymin, salarymin);
			}

			if (setFlagSalarymax != 0) {
				ps.setInt(setFlagSalarymax, salarymax);
			}

			if (setFlagKoyoukeitaicd != 0) {
				ps.setString(setFlagKoyoukeitaicd, koyoukeitaicd);
			}

		} catch (SQLException e) {
			throw new IOException(e);
		}

		StringBuilder sqlSearchJob = new StringBuilder();

		sqlSearchJob.append("select no,job,jobcategory, ");
		sqlSearchJob.append("companyname,address,addresscd,nearstation,");
		sqlSearchJob.append("agemin,agemax,salarymin,salarymax,koyoukeitaicd  ");
		sqlSearchJob.append(" from kyujin");
		sqlSearchJob.append(" order by no");
		sqlSearchJob.append(" where ");
		sqlSearchJob.append(String.join(" and ", whereStr));
		sqlSearchJob.append(" ;");

		// SQL文を生成する

		try (PreparedStatement ps = this.conn.prepareStatement(sqlSearchJob.toString())) {
			// SQL文を実行する
			try (ResultSet rs = ps.executeQuery()) {
				// 1.取得結果をリストに格納する
				while (rs.next()) {
					kyujinlist.add(
							new SimpleKyujin(rs.getString("no"),rs.getString("job"), rs.getString("jobcategory"),
									rs.getString("companyname"), rs.getString("address"), rs.getString("addresscd"),
									rs.getString("nearstation"),
									rs.getInt("agemin"), rs.getInt("agemax"), rs.getInt("salarymin"),
									rs.getInt("salarymax"),rs.getString("koyoukeitaicd")));
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
