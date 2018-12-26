package jp.or.adash.nexus.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.or.adash.nexus.dao.JobSearchDao;
import jp.or.adash.nexus.entity.Company;
import jp.or.adash.nexus.entity.SimpleKyujin;
import jp.or.adash.nexus.utils.dao.Transaction;

public class JobSearchService {

	/**
	 * トランザクションオブジェクト
	 */
	private Transaction transaction;

	/**
	 * 処理結果メッセージを格納するリスト
	 */
	private List<String> messages;

	/**
	 * コンストラクタ
	 */
	public JobSearchService() {
		transaction = new Transaction();
		messages = new ArrayList<String>();
	}

	/**
	 * 処理結果メッセージを取得する
	 * @return 処理結果メッセージ
	 */
	public List<String> getMessages() {
		return messages;
	}

	/**
	 * 求人リストを取得する。
	 *
	 *  職種、職種小分類コード1、職種小分類コード２、職種小分類コード３、職業大分類コード１、職業大分類コード２、職業大分類コード３、
	 *   就業場所コード、 基本給下限、 基本給上限、雇用形態コード	を元に、求人情報を取得する
	 * @param  	job＝職種　
	 * 			jobsmallcd1 = 職種小分類コード１
				jobsmallcd2	= 職種小分類コード２
				jobsmallcd3	= 職種小分類コード３
				joblargecd1	= 職業大分類コード１
				joblargecd2 = 職業大分類コード２
				joblargecd3	= 職業大分類コード３
				salarymin= 基本給下限
				salarymax = 基本給上限
				addresscd = 就業場所コード
				koyoukeitaicd=雇用形態コード
	 *
	 * @return 求人リスト
	 */
	public List<SimpleKyujin> getKyujin(String job, String addresscd, String jobsmallcd1, String jobsmallcd2,
			String jobsmallcd3, String joblargecd1,
			String joblargecd2, String joblargecd3,
			String jobcategorysmallcd, String jobcategorylargecd,
			String koyoukeitaicd, int salarymin, int salarymax) {
		List<SimpleKyujin> kyujinlist = new ArrayList<SimpleKyujin>();

		try {
			// 1.データベース接続を開始する
			transaction.open();

			// 1.求人票を取得する
			JobSearchDao dao = new JobSearchDao(transaction);

			kyujinlist = dao.selectKyujin(job, addresscd, jobsmallcd1, jobsmallcd2, jobsmallcd3,
					joblargecd1, joblargecd2, joblargecd3,
					jobcategorysmallcd, jobcategorylargecd,
					koyoukeitaicd, salarymin, salarymax);

		} catch (IOException e) {
			// 1.エラーメッセージをセットする
		} finally {
			// 1.データベース接続をを終了する
			transaction.close();
		}

		return kyujinlist;
	}

	/**
	 * 事業所番号をKeyとした企業情報のマップを返す
	 * @param simpleKyujinList
	 * @return 企業情報のマップ
	 */
	public Map<String, Company> getCompanyMap(List<SimpleKyujin> simpleKyujinList) {
		Map<String, Company> companyMap = new HashMap<String, Company>();

		for (SimpleKyujin simpleKyujin : simpleKyujinList) {
			String companyNo = simpleKyujin.getCompanyno();

			//事業所番号が空っぽならcontinue
			if ("".equals(companyNo) || companyNo == null) {
				continue;
			}

			//企業情報の取得
			CompanyService companyService = new CompanyService();
			Company company = companyService.getCompanyInfo(companyNo);
			//マップに格納
			companyMap.put(companyNo, company);
		}

		return companyMap;
	}
}
