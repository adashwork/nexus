package jp.or.adash.nexus.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.or.adash.nexus.dao.JobSeekerDao;
import jp.or.adash.nexus.dao.JobSeeker_dao;
import jp.or.adash.nexus.dao.SaibanDao;
import jp.or.adash.nexus.entity.JobSeeker;
import jp.or.adash.nexus.entity.Jobseeker_simple_entity;
import jp.or.adash.nexus.utils.common.MessageCommons;
import jp.or.adash.nexus.utils.dao.Transaction;




public class JobSeekerService {
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
	public JobSeekerService() {
		transaction = new Transaction();
		messages = new ArrayList<String>();
	}

	/**
	 * 求職者情報一覧を取得する
	 * @return 求職者情報一覧
     *@aihara
	 */
public List<Jobseeker_simple_entity> getJobSeekerList(){
	List<Jobseeker_simple_entity> jobseekerList = new ArrayList<Jobseeker_simple_entity>();
	try {
		// データベース接続を開始する
		transaction.open();

		// 商品リストを取得する
		JobSeeker_dao dao = new JobSeeker_dao(transaction);
		jobseekerList = dao.selectJobseekerList();

	} catch(IOException e) {
		// エラーメッセージをセットする
		messages.add(MessageCommons.ERR_DB_CONNECT);
	} finally {
		// データベース接続を終了する
		transaction.close();
	}

	return jobseekerList;
	}
	/**
	 * 求職者idを元に絞込み、求職者情報を取得する
	 * @param id 求職者id
	 * @return idで絞り込んだ求職者情報
     *@aihara
	 */
public Jobseeker_simple_entity getJobseeker(int js_id) {
	Jobseeker_simple_entity jobseeker = null;

	try {
		// データベース接続を開始する
		transaction.open();

		// 商品単価を取得する
		JobSeeker_dao dao = new JobSeeker_dao(transaction);
		jobseeker = dao.selectItem(js_id);

	} catch(IOException e) {
		// エラーメッセージをセットする
	} finally {
		// データベース接続をを終了する
		transaction.close();
	}

	return jobseeker;
}

	/*
	 * 求職者情報の詳細情報を取得する
	 * @return 求職者情報の詳細情報
     *@aihara
	 */
public JobSeeker getJobseekermaininfo(String js_id) {
	JobSeeker jobseeker = null;

	try {
		// データベース接続を開始する
		transaction.open();

		// 商品単価を取得する
		JobSeeker_dao dao = new JobSeeker_dao(transaction);
		jobseeker = dao.selectItem(js_id);

	} catch(IOException e) {
		// エラーメッセージをセットする
	} finally {
		// データベース接続をを終了する
		transaction.close();
	}

	return jobseeker;
}
	/**
	 * 求職者IDを元に、求職者情報を取得する
	 * @param id 求職者ID
	 * @return 求職者情報
	 */
	public JobSeeker getJobSeeker(String id) {
		JobSeeker seeker = null;

		try {
			// データベース接続を開始する
			transaction.open();

			// 商品単価を取得する
			JobSeekerDao dao = new JobSeekerDao(transaction);
			seeker = dao.selectJobSeeker(id);

		} catch(IOException e) {
			// エラーメッセージをセットする
		} finally {
			// データベース接続をを終了する
			transaction.close();
		}

		return seeker;
	}



	/**
	 * 求職者情報の内容をチェックする
	 * @param seeker 求職者情報
	 * @return 処理結果（true:成功、false:失敗）
	 */
	public boolean check(JobSeeker seeker) {
		boolean result = true;		// チェック結果
/**

		// 求職者IDの値が入力されているか
				if (seeker.getName() == "") {
					messages.add("名前が入力されていません。");
					result = false;
				}

				// ふりがなの値が入力されているか
				if (seeker.getKana() == "") {
					messages.add("ふりがなが入力されていません。");
					result = false;
				}

				// 生年月日の値が入力されているか
				if (seeker.getBirthdt() == null) {
					messages.add("生年月日が入力されていません。");
					result = false;
				}

				// 性別の値が入力されているか
				if (seeker.getSex() == "") {
					messages.add("性別が入力されていません。");
					result = false;
				}

				// 年齢の値が入力されているか
				if (seeker.getAge() == -1) {
					messages.add("年齢が入力されていません。");
					result = false;
				}

				// 郵便番号の値が入力されているか
				if (seeker.getPostal() == "") {
					messages.add("郵便番号が入力されていません。");
					result = false;
				}

				// 住所の値が入力されているか
				if (seeker.getAddress() == "") {
					messages.add("住所が入力されていません。");
					result = false;
				}

				// 最寄り駅の値が入力されているか
				if (seeker.getNearstation() == "") {
					messages.add("最寄り駅が入力されていません。");
					result = false;
				}

				// 配偶者の値が入力されているか
				if (seeker.getPartner() == "") {
					messages.add("配偶者の有無が入力されていません。");
					result = false;
				}

				// 扶養家族の値が入力されているか
				if (seeker.getHuyou() == -1) {
					messages.add("扶養家族が入力されていません。");
					result = false;
				}

				// 学歴の値が入力されているか
				if (seeker.getEducation() == "") {
					messages.add("学歴が入力されていません。");
					result = false;
				}

				// 希望職種1の値が入力されているか
				if (seeker.getHopejob1() == "") {
					messages.add("希望職種1が入力されていません。");
					result = false;
				}

				// 希望職種2の値が入力されているか
				if (seeker.getHopejob2() == "") {
					messages.add("希望職種2が入力されていません。");
					result = false;
				}

				// 希望職種3の値が入力されているか
				if (seeker.getHopejob3() == "") {
					messages.add("希望職種3が入力されていません。");
					result = false;
				}

				// 希望業種の値が入力されているか
				if (seeker.getHopejobcategory() == "") {
					messages.add("希望業種が入力されていません。");
					result = false;
				}

				// 希望勤務地の値が入力されているか
				if (seeker.getHopeworkplace() == "") {
					messages.add("希望勤務地が入力されていません。");
					result = false;
				}

				// 雇用形態の値が入力されているか
				if (seeker.getHopekoyoukeitai() == "") {
					messages.add("雇用形態が入力されていません。");
					result = false;
				}

				// 希望勤務日時の値が入力されているか
				if (seeker.getHopeworkingDate() == -1) {
					messages.add("希望勤務日時が入力されていません。");
					result = false;
				}

				// 希望勤務時間（開始）の値が入力されているか
				if (seeker.getHopebegintime() == -1) {
					messages.add("希望勤務時間（開始）が入力されていません。");
					result = false;
				}

				// 希望勤務時間（終了）の値が入力されているか
				if (seeker.getHopeendtime() == -1) {
					messages.add("希望勤務時間（終了）が入力されていません。");
					result = false;
				}

				// 希望月給の値が入力されているか
				if (seeker.getHopesalary() == -1) {
					messages.add("希望月給が入力されていません。");
					result = false;
				}

				// 希望時間給の値が入力されているか
				if (seeker.getHopejikyu() == -1) {
					messages.add("希望時間給が入力されていません。");
					result = false;
				}

				// 自動車免許の値が入力されているか
				if (seeker.getDriverlicense() == "") {
					messages.add("自動車免許が入力されていません。");
					result = false;
				}

				// 担当職業紹介者IDの値が入力されているか
				if (seeker.getTantoustaffid() == "") {
					messages.add("担当職業紹介者IDが入力されていません。");
					result = false;
				}

				// パスワードの値が入力されているか
				if (seeker.getPassword() == "") {
					messages.add("パスワードが入力されていません。");
					result = false;
				}
*/
/*
		// 商品名の長さが適切か
		DataCommons commons = new DataCommons();
		int length = commons.getBytes(item.getItemName());
		if (length <= 0 || length >= 100) {
			messages.add("商品名の文字数が多すぎます。");
			result = false;
		}

		// 単価の値が正しいか
		if (item.getUnitPrice() <= 0) {
			messages.add("単価は1円以上で入力してください。");
			result = false;
		}
*/
		return result;

	}
	/**
	 * 求職者情報を登録する
	 * @param item 商品データ
	 * @return 処理結果（true:成功、false:失敗）
	 */
	public boolean registItem(JobSeeker seeker) {
		boolean result = false;
		result = this.insertJobSeeker(seeker);

/*
		// データベースに商品が既に存在するかどうか確認する
		if (exists(seeker.getId())) {
			// 存在する場合は、商品データを更新する
			result = this.updateItem(seeker);
		} else {
			// 存在しなければ、商品データを登録する
			result = this.insertItem(seeker);
		}
*/
		return result;
	}

	/**
	 * 求職者情報をデータベースに登録する
	 * @param seeker 求職者情報
	 * @return 処理結果（true:成功、false:失敗）
	 */
	public boolean insertJobSeeker(JobSeeker seeker) {
		boolean result = false;		// 処理結果

		try {
			// データベース接続を開始する
			transaction.open();

			// トランザクションを開始する
			transaction.beginTrans();

			//采番マスタよりデータ取得
			SaibanDao saidao = new SaibanDao(transaction);
			int saiban = saidao.getseeker();

			//とってきた番号を加工し、Kyujin.noにデータ格納
			String str = String.format("%08d", saiban);
			seeker.setId(str);

			// DBに求職者情報を取得する
			JobSeekerDao dao = new JobSeekerDao(transaction);
			int count = dao.insert(seeker);

			if (count > 0) {
				// 完了メッセージをセットする
				messages.add("登録完了");
				result = true;
			} else {
				// エラーメッセージをセットする
				messages.add("登録失敗");
				result = false;
			}

			// トランザクションをコミットする
			transaction.commit();

		} catch(IOException e) {
			// トランザクションをロールバックする
			transaction.rollback();

			// エラーメッセージをセットする
			messages.add("データベースアクセスに失敗しました。");
		} finally {
			// データベース接続をを終了する
			transaction.close();
		}

		return result;
	}


	/**
	 * 求職者情報を更新する
	 * @param seeker 求職者情報
	 * @return 処理結果（true:成功、false:失敗）
	 */
	public boolean updateJobSeeker(JobSeeker seeker) {
		boolean result = false;	// 処理結果

		try {
			// データベース接続を開始する
			transaction.open();

			// トランザクションを開始する
			transaction.beginTrans();

			// 商品単価を取得する
			JobSeekerDao dao = new JobSeekerDao(transaction);
			int count = dao.update(seeker);

			if (count > 0) {
				// 完了メッセージをセットする
				messages.add("編集が完了しました。");
				result = true;
			} else {
				// エラーメッセージをセットする
				messages.add("編集に失敗しました。");
			}

			// トランザクションをコミットする
			transaction.commit();

		} catch(IOException e) {
			// トランザクションをロールバックする
			transaction.rollback();

			// エラーメッセージをセットする
			messages.add("データベースアクセスに失敗しました。");
		} finally {
			// データベース接続をを終了する
			transaction.close();
		}

		return result;
	}
	/**
	 * 求職者情報がデータベースに既に存在するかどうかを確認する
	 * @param code 商品コード
	 * @return true:存在する、false:存在しない
	 */
	/*
	private boolean exists(int id) {
		boolean result = false;		// 確認結果

		// コードをキーにして、データベースを検索する
		int price = this.getId(id);

		// データが存在する場合、true	を返す
		if (price > 0) {
			result = true;
		}

		return result;
	}*/

	/**
	 * 処理結果メッセージを取得する
	 * @return 処理結果メッセージ
	 */

	public List<String> getMessages() {
		return messages;
	}
/*
	public JobSeeker getJobSeeker(int id) {
		JobSeeker seeker = null;

		try {
			// データベース接続を開始する
			transaction.open();

			// 商品単価を取得する
			JobSeekerDao dao = new JobSeeker(transaction);
			seeker = dao.selectJobSeeker(id);

		} catch(IOException e) {
			// エラーメッセージをセットする
		} finally {
			// データベース接続をを終了する
			transaction.close();
		}

		return seeker;

	}*/
}
