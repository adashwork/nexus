package jp.or.adash.nexus.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.or.adash.nexus.dao.JobSeekerDao;
import jp.or.adash.nexus.dao.JobSeeker_dao;
import jp.or.adash.nexus.dao.SaibanDao;
import jp.or.adash.nexus.entity.JobSeeker;
import jp.or.adash.nexus.entity.JobSeekerMain;
import jp.or.adash.nexus.entity.Jobseeker_simple_entity;
import jp.or.adash.nexus.entity.StaffName;
import jp.or.adash.nexus.utils.common.DataCommons;
import jp.or.adash.nexus.utils.dao.Transaction;

/**
 * 求職者サービス
 * @author S.aihara & Y.Okamura & T.Uchi & pgjavaAT
 */

public class JobSeekerService {

	String errMsg = null;
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
	/*
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
	*/
	/**
	 * 求職者idを元に絞込み、求職者情報を取得する
	 * @param id 求職者id
	 * @return idで絞り込んだ求職者情報
	 * @author aihara
	 */
	/*public List<Jobseeker_simple_entity> getJobseeker(String js_id) {
		List<Jobseeker_simple_entity> jobseeker = null;

		try {
			// データベース接続を開始する
			transaction.open();

			// 求職者情報を取得する
			JobSeeker_dao dao = new JobSeeker_dao(transaction);
			jobseeker = dao.selectJobSeekerId(js_id);

		} catch(IOException e) {
			// エラーメッセージをセットする
		} finally {
			// データベース接続をを終了する
			transaction.close();
		}

		return jobseeker;
	}
	*/
	/*
	 * 求職者情報の詳細情報を取得する
	 * @return 求職者情報の詳細情報
	 * @author aihara
	 */
	public JobSeekerMain getJobseekermaininfo(String js_id) {
		JobSeekerMain jobseeker = null;

		try {
			// データベース接続を開始する
			transaction.open();

			// 商品単価を取得する
			/*			JobSeeker_dao dao = new JobSeeker_dao(transaction);
						jobseeker = dao.selectJobseekermaininfo(js_id);*/
			JobSeekerDao jobSeekerDao = new JobSeekerDao(transaction);
			jobseeker = jobSeekerDao.selectJobSeeker(js_id);

		} catch (IOException e) {
			// エラーメッセージをセットする
		} finally {
			// データベース接続をを終了する
			transaction.close();
		}

		return jobseeker;
	}

	/**
	 * 求職者情報を検索する
	 * @param 求職者ID js_id,求職者かな名 js_kana,担当紹介者氏名 st_name
	 * @return 求職者情報
	 * @author aihara
	 */
	public List<Jobseeker_simple_entity> getJobSeeker(String js_id, String js_kana, String st_name) {
		List<Jobseeker_simple_entity> seeker = null;

		try {
			// データベース接続を開始する
			transaction.open();

			// 商品単価を取得する
			JobSeeker_dao dao = new JobSeeker_dao(transaction);
			seeker = dao.selectJobSeeker(js_id, js_kana, st_name);

		} catch (IOException e) {
			// エラーメッセージをセットする
		} finally {
			// データベース接続をを終了する
			transaction.close();
		}

		return seeker;
	}

	/**
	 * 担当紹介者氏名を取得する
	 * @return 担当紹介者氏名情報
	 * @author aihara
	 */
	public List<StaffName> getTantoStaff() {
		List<StaffName> staff = null;

		try {
			// データベース接続を開始する
			transaction.open();

			// 商品単価を取得する
			JobSeeker_dao dao = new JobSeeker_dao(transaction);
			staff = dao.selectTantoStaff();

		} catch (IOException e) {
			// エラーメッセージをセットする
		} finally {
			// データベース接続をを終了する
			transaction.close();
		}

		return staff;
	}

	/**
	 * 求職者情報の内容をチェックする
	 * @param seeker 求職者情報
	 * @return 処理結果（true:成功、false:失敗）
	 * @author Y.Okamura & T.Uchi
	 */
	public boolean check(JobSeeker seeker) {
		//テストとして強制的にエラーを出力
		if(true) {
			messages.add("強制的にデータチェックエラーを発生させています");
			return  false;
		}




		boolean result = true; // チェック結果

		// 求職者IDの値が入力されているか
		if (seeker.getName().equals("")) {
			messages.add("名前が入力されていません。");
			result = false;
		}

		errMsg = DataCommons.chksDigits(seeker.getName(), 100);
		messages.add(errMsg);

		// ふりがなの値が入力されているか
		if (seeker.getKana().equals("")) {
			messages.add("ふりがなが入力されていません。");
			result = false;
		}

		errMsg = DataCommons.chkHiragana(seeker.getKana());
		messages.add(errMsg);

		errMsg = DataCommons.chksDigits(seeker.getKana(), 100);
		messages.add(errMsg);

		// 生年月日の値が入力されているか
		if (seeker.getBirthdt() == null) {
			messages.add("生年月日が入力されていません。");
			result = false;
		}

		// 性別の値が入力されているか
		if (seeker.getSex() == null) {
			messages.add("性別が入力されていません。");
			result = false;
		}

		// 年齢の値が入力されているか
		/*if (seeker.getAge() == -1) {
			messages.add("年齢が入力されていません。");
			result = false;
		}
		errMsg = DataCommons.chkInt(String.valueOf(seeker.getAge().toString()));
		messages.add(errMsg);

		errMsg = DataCommons.chkiDigits(seeker.getAge(), 3);
		messages.add(errMsg);
		 */
		// 郵便番号の値が入力されているか
		if (seeker.getzip21().equals("")) {
			messages.add("郵便番号が入力されていません。");
			result = false;
		}

		errMsg = DataCommons.chkZipcode(seeker.getzip21());
		messages.add(errMsg);

		errMsg = DataCommons.chksDigits(seeker.getzip21(), 8);
		messages.add(errMsg);

		// 住所の値が入力されているか
		if (seeker.getAddr21().equals("")) {
			messages.add("住所が入力されていません。");
			result = false;
		}

		errMsg = DataCommons.chksDigits(seeker.getAddr21(), 50);
		messages.add(errMsg);

		/*
		// 最寄り駅の値が入力されているか
		if (seeker.getNearstation().equals("")) {
			messages.add("最寄り駅が入力されていません。");
			result = false;
		}
		 */

		if (seeker.getNearstation() != ("")) {
			errMsg = DataCommons.chksDigits(seeker.getNearstation(), 50);
			messages.add(errMsg);
		}

		/*
		//自宅TELが入力されているか
		errMsg = DataCommons.chksDigits(seeker.getPhone(), 20);
		messages.add(errMsg);
		*/

		/*
		//携帯番号が入力されているか
		errMsg = DataCommons.chksDigits(seeker.getMobile(), 20);
		messages.add(errMsg);
		*/

		// 配偶者の値が入力されているか
		if (seeker.getPartner().equals("")) {
			messages.add("配偶者の有無が入力されていません。");
			result = false;
		}

		// 扶養家族の値が入力されているか
		if (seeker.getHuyou() == -1) {
			messages.add("扶養家族が入力されていません。");
			result = false;
		}

		errMsg = DataCommons.chkInt(String.valueOf(seeker.getHuyou().toString()));
		messages.add(errMsg);

		errMsg = DataCommons.chkiDigits(seeker.getHuyou(), 2);
		messages.add(errMsg);

		/*
		// 学歴の値が入力されているか
		if (seeker.getEducation().equals("")) {
			messages.add("学歴が入力されていません。");
			result = false;
		}
		*/
		if (seeker.getEducation() != ("")) {
			errMsg = DataCommons.chksDigits(seeker.getEducation(), 100);
			messages.add(errMsg);
		}

		/*
		// 希望職種1の値が入力されているか
		if (seeker.getHopejob1().equals("")) {
			messages.add("希望職種1が入力されていません。");
			result = false;
		}
		*/

		/*
		// 希望職種2の値が入力されているか
		if (seeker.getHopejob2().equals("")) {
			messages.add("希望職種2が入力されていません。");
			result = false;
		}
		*/

		/*
		// 希望職種3の値が入力されているか
		if (seeker.getHopejob3().equals("")) {
			messages.add("希望職種3が入力されていません。");
			result = false;
		}
		*/

		/*
		// 希望業種の値が入力されているか
		if (seeker.getHopejobcategory().equals("")) {
			messages.add("希望業種が入力されていません。");
			result = false;
		}
		*/

		/*
		// 希望勤務地の値が入力されているか
		if (seeker.getHopeworkplace().equals("")) {
			messages.add("希望勤務地が入力されていません。");
			result = false;
		}
		*/
		/*
		if(seeker.getHopeworkplace() != ("")) {
		errMsg = DataCommons.chksDigits(seeker.getHopeworkplace(), 2);
		messages.add(errMsg);
		}
		*/
		/*
		// 雇用形態の値が入力されているか
		if (seeker.getHopekoyoukeitai() == null) {
			messages.add("雇用形態が入力されていません。");
			result = false;
		}
		*/

		/*
		// 希望勤務日時の値が入力されているか
		if (seeker.getHopeworkingDate() == -1) {
			messages.add("希望勤務日時が入力されていません。");
			result = false;
		}
		*/

		/*
		// 希望勤務時間（開始）の値が入力されているか
		if (seeker.getHopebegintime() == -1) {
			messages.add("希望勤務時間（開始）が入力されていません。");
			result = false;
		}
		*/

		/*
		// 希望勤務時間（終了）の値が入力されているか
		if (seeker.getHopeendtime() == -1) {
			messages.add("希望勤務時間（終了）が入力されていません。");
			result = false;
		}
		*/

		/*
		// 希望月給の値が入力されているか
		if (seeker.getHopesalary() == -1) {
			messages.add("希望月給が入力されていません。");
			result = false;
		}
		*/

		// 希望月給の値が正しいか
		if (seeker.getHopesalary() != null) {
			errMsg = DataCommons.chkInt(String.valueOf(seeker.getHopesalary().toString()));
			messages.add(errMsg);
		}

		/*
		// 希望時間給の値が入力されているか
		if (seeker.getHopejikyu() == -1) {
			messages.add("希望時間給が入力されていません。");
			result = false;
		}
		*/

		// 希望時間給が正しいか
		if (seeker.getHopejikyu() != null) {
			errMsg = DataCommons.chkInt(String.valueOf(seeker.getHopejikyu().toString()));
			messages.add(errMsg);
		}
		//その他希望が入力されているかどうか
		errMsg = DataCommons.chksDigits(seeker.getHopeetc(), 200);
		messages.add(errMsg);

		// 自動車免許の値が入力されているか
		if (seeker.getDriverlicense().equals("")) {
			messages.add("自動車免許が入力されていません。");
			result = false;
		}

		/*
		//その他免許が入力されているかどうか
		if(seeker.getLicenseetc() != null) {
		errMsg = DataCommons.chksDigits(seeker.getLicenseetc(), 200);
		messages.add(errMsg);
		result = false;
		}
		*/

		//パソコンスキルが入力されているかどうか
		errMsg = DataCommons.chksDigits(seeker.getPasokonskill(), 200);
		messages.add(errMsg);

		//パソコンスキルが入力されているかどうか
		errMsg = DataCommons.chksDigits(seeker.getPasokonskill(), 200);
		messages.add(errMsg);

		// 担当職業紹介者IDの値が入力されているか
		if (seeker.getTantoustaffid().equals("")) {
			messages.add("担当職業紹介者IDが入力されていません。");
			result = false;
		}
		/*
						// パスワードの値が入力されているか
						if (seeker.getPassword().equals("")) {
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
	 * 求職者詳細情報の内容をチェックする
	 * @param seeker 求職者情報
	 * @return 処理結果（true:成功、false:失敗）
	 * @author Y.Okamura & T.Uchi
	 */
	public boolean check(JobSeekerMain seeker) {
		boolean result = true; // チェック結果

		// 求職者IDの値が入力されているか
		if (seeker.getName().equals("")) {
			messages.add("名前が入力されていません。");
			result = false;
		}

		errMsg = DataCommons.chksDigits(seeker.getName(), 100);
		messages.add(errMsg);

		// ふりがなの値が入力されているか
		if (seeker.getKana().equals("")) {
			messages.add("ふりがなが入力されていません。");
			result = false;
		}

		errMsg = DataCommons.chkHiragana(seeker.getKana());
		messages.add(errMsg);

		errMsg = DataCommons.chksDigits(seeker.getKana(), 100);
		messages.add(errMsg);

		// 生年月日の値が入力されているか
		if (seeker.getBirthdt() == null) {
			messages.add("生年月日が入力されていません。");
			result = false;
		}

		// 性別の値が入力されているか
		if (seeker.getSex() == null) {
			messages.add("性別が入力されていません。");
			result = false;
		}

		// 年齢の値が入力されているか
		/*	if (seeker.getAge() == -1) {
				messages.add("年齢が入力されていません。");
				result = false;
			}
			errMsg = DataCommons.chkInt(String.valueOf(seeker.getAge().toString()));
			messages.add(errMsg);

			errMsg = DataCommons.chkiDigits(seeker.getAge(), 3);
			messages.add(errMsg);
		 */
		// 郵便番号の値が入力されているか
		if (seeker.getzip21().equals("")) {
			messages.add("郵便番号が入力されていません。");
			result = false;
		}

		// 郵便番号の値が正しいか
		errMsg = DataCommons.chkZipcode(seeker.getzip21());
		messages.add(errMsg);

		/*
		 * TODO 後ほど削除するか確認
		 * chkZipcodeを呼び出しているため、不要と思われる。18/09/29 pgjavaAT
				errMsg = DataCommons.chksDigits(seeker.getPostal(), 8);
				messages.add(errMsg);
		*/
		// 住所の値が入力されているか
		if (seeker.getAddr21().equals("")) {
			messages.add("住所が入力されていません。");
			result = false;
		}

		// 住所の桁数が正しいか
		errMsg = DataCommons.chksDigits(seeker.getAddr21(), 50);
		messages.add(errMsg);

		/*
		// 最寄り駅の値が入力されているか
		if (seeker.getNearstation().equals("")) {
			messages.add("最寄り駅が入力されていません。");
			result = false;
		}
		 */

		// 最寄り駅の値が入力されているか
		if (seeker.getNearstation() != ("")) {
			errMsg = DataCommons.chksDigits(seeker.getNearstation(), 50);
			messages.add(errMsg);
		}

		/*
		//自宅TELが入力されているか
		errMsg = DataCommons.chksDigits(seeker.getPhone(), 20);
		messages.add(errMsg);
		*/

		/*
		//携帯番号が入力されているか
		errMsg = DataCommons.chksDigits(seeker.getMobile(), 20);
		messages.add(errMsg);
		*/

		// 配偶者の値が入力されているか
		if (seeker.getPartner().equals("")) {
			messages.add("配偶者の有無が入力されていません。");
			result = false;
		}


		// 扶養家族の値が入力されているか
		if (seeker.getHuyou() == -1) {
			messages.add("扶養家族が入力されていません。");
			result = false;
		}
		if (seeker.getHuyou() != null) {
			errMsg = DataCommons.chkInt(String.valueOf(seeker.getHuyou().toString()));
			messages.add(errMsg);
		}

		errMsg = DataCommons.chkiDigits(seeker.getHuyou(), 2);
		messages.add(errMsg);

		/*
		// 学歴の値が入力されているか
		if (seeker.getEducation().equals("")) {
			messages.add("学歴が入力されていません。");
			result = false;
		}
		*/
		// 学歴の値が入力されているか
		if (seeker.getEducation() != ("")) {
			errMsg = DataCommons.chksDigits(seeker.getEducation(), 100);
			messages.add(errMsg);
		}

		/*
		// 希望職種1の値が入力されているか
		if (seeker.getHopejob1().equals("")) {
			messages.add("希望職種1が入力されていません。");
			result = false;
		}
		*/

		/*
		// 希望職種2の値が入力されているか
		if (seeker.getHopejob2().equals("")) {
			messages.add("希望職種2が入力されていません。");
			result = false;
		}
		*/

		/*
		// 希望職種3の値が入力されているか
		if (seeker.getHopejob3().equals("")) {
			messages.add("希望職種3が入力されていません。");
			result = false;
		}
		*/

		/*
		// 希望業種の値が入力されているか
		if (seeker.getHopejobcategory().equals("")) {
			messages.add("希望業種が入力されていません。");
			result = false;
		}
		*/

		/*
		// 希望勤務地の値が入力されているか
		if (seeker.getHopeworkplace().equals("")) {
			messages.add("希望勤務地が入力されていません。");
			result = false;
		}
		*/
		/*
		if(seeker.getHopeworkplace() != ("")) {
		errMsg = DataCommons.chksDigits(seeker.getHopeworkplace(), 2);
		messages.add(errMsg);
		}
		*/
		/*
		// 雇用形態の値が入力されているか
		if (seeker.getHopekoyoukeitai() == null) {
			messages.add("雇用形態が入力されていません。");
			result = false;
		}
		*/

		/*
		// 希望勤務日時の値が入力されているか
		if (seeker.getHopeworkingDate() == -1) {
			messages.add("希望勤務日時が入力されていません。");
			result = false;
		}
		*/

		// 希望勤務日時の値が入力されているか
		if (seeker.getHopeworkingdate() != -1) {
			messages.add(DataCommons.chkTime(seeker.getHopeworkingdate()));
		}

		/*
		// 希望勤務時間（開始）の値が入力されているか
		if (seeker.getHopebegintime() == -1) {
			messages.add("希望勤務時間（開始）が入力されていません。");
			result = false;
		}
		*/

		/*
		// 希望勤務時間（終了）の値が入力されているか
		if (seeker.getHopeendtime() == -1) {
			messages.add("希望勤務時間（終了）が入力されていません。");
			result = false;
		}
		*/

		/*
		// 希望月給の値が入力されているか
		if (seeker.getHopesalary() == -1) {
			messages.add("希望月給が入力されていません。");
			result = false;
		}
		*/

		// 希望月給の値が入力されているか
		errMsg = DataCommons.chkInt(String.valueOf(seeker.getHopesalary().toString()));
		messages.add(errMsg);

		/*
		// 希望時間給の値が入力されているか
		if (seeker.getHopejikyu() == -1) {
			messages.add("希望時間給が入力されていません。");
			result = false;
		}
		*/

		errMsg = DataCommons.chkInt(String.valueOf(seeker.getHopejikyu().toString()));
		messages.add(errMsg);

		//その他希望が入力されているかどうか
		errMsg = DataCommons.chksDigits(seeker.getHopeetc(), 200);
		messages.add(errMsg);

		// 自動車免許の値が入力されているか
		if (seeker.getDriverlicense().equals("")) {
			messages.add("自動車免許が入力されていません。");
			result = false;
		}

		/*
		//その他免許が入力されているかどうか
		if(seeker.getLicenseetc() != null) {
		errMsg = DataCommons.chksDigits(seeker.getLicenseetc(), 200);
		messages.add(errMsg);
		result = false;
		}
		*/

		//パソコンスキルが入力されているかどうか
		errMsg = DataCommons.chksDigits(seeker.getPasokonskill(), 200);
		messages.add(errMsg);

		//パソコンスキルが入力されているかどうか
		errMsg = DataCommons.chksDigits(seeker.getPasokonskill(), 200);
		messages.add(errMsg);

		// 担当職業紹介者IDの値が入力されているか
		if (seeker.getTantoustaffid().equals("")) {
			messages.add("担当職業紹介者IDが入力されていません。");
			result = false;
		}
		/*
				// パスワードの値が入力されているか
				if (seeker.getPassword().equals("")) {
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
	 * 求職者情報をデータベースに登録する
	 * @param seeker 求職者情報
	 * @return 処理結果（true:成功、false:失敗）
	 * @author Y.Okamura & T.Uchi & mmiyamoto
	 */
	public boolean insertJobSeeker(JobSeeker seeker) {
		boolean result = false; // 処理結果

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
			int count = dao.insertPrivateData(seeker);
			count += dao.insertZokuseiData(seeker);

			if (count > 1) {
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

		} catch (IOException e) {
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
	 * @author Y.Okamura & T.Uchi
	 */
	public boolean updateJobSeeker(JobSeekerMain seeker) {
		boolean result = false; // 処理結果

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
				messages.add("更新が完了しました。");
				result = true;
			} else {
				// エラーメッセージをセットする
				messages.add("更新に失敗しました。");
			}

			// トランザクションをコミットする
			transaction.commit();

		} catch (IOException e) {
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
