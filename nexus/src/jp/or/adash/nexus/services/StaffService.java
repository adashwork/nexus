package jp.or.adash.nexus.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.or.adash.nexus.dao.StaffDao;
import jp.or.adash.nexus.entity.Comment;
import jp.or.adash.nexus.entity.Company;
import jp.or.adash.nexus.entity.Staff;
import jp.or.adash.nexus.utils.common.MessageCommons;
import jp.or.adash.nexus.utils.dao.Transaction;

public class StaffService {

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
	public StaffService() {
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
	 * スタッフオブジェクトを取得する
	 * @param staffテーブルのid
	 * @return Staffオブジェクト
	 *
	 */
	public Staff getStaff(String staffId) {
		Staff staff = null;

		try {
			// データベース接続を開始する
			transaction.open();

			// 求人リストを取得する
			StaffDao staffDao = new StaffDao(transaction);
			staff = staffDao.selectStaffName(staffId);

		} catch (IOException e) {
			// エラーメッセージをセットする
			messages.add(MessageCommons.ERR_DB_CONNECT);
		} finally {
			// データベース接続を終了する
			transaction.close();
		}

		return staff;
	}

	/**
	 * Keyがid、valueが空のマップを引数に渡すと
	 * 戻り値として、valueにスタッフ名が代入されたマップを返す
	 * @param staffMap <id, null>
	 * @return StaffNameMap <id, staffName>
	 */
	public Map<String, String> getStaffNameMap(Map<String, String> staffMap){
		Map<String, String> staffNameMap = new HashMap<String, String>();

		for(Map.Entry<String, String> staff : staffMap.entrySet()) {

			//スタッフ名を取得
			String staffName = getStaff(staff.getKey()).getName();

			staffNameMap.put(staff.getKey(), staffName);
		}
		return staffNameMap;
	}



	/**
	 * KeyがスタッフID、 Valueが空文字のスタッフのIDマップを返します
	 * (TantouStaffId,UpdateUserId, CreateuserId)を抽出します
	 * @param List<Company>
	 * @return StaffIdMap<id, 空文字>
	 */
	public Map<String, String> getCompanyStaffIdMap(List<Company> companyList){
		Map<String, String> staffIdMap = new HashMap<>();
		for(Company company: companyList) {
			staffIdMap.put(company.getTantouStaffId(), "");
			staffIdMap.put(company.getUpdateUserId(), "");
			staffIdMap.put(company.getCreateuserId(), "");
		}
		return staffIdMap;
	}

	/**
	 * KeyがスタッフID、 Valueが空文字のスタッフのIDマップを返します
	 * (StaffId)を抽出します
	 * @param List<Comment>
	 * @return StaffIdMap<id, 空文字>
	 */
	public Map<String, String> getCommentStaffIdMap(List<Comment> commentList){
		Map<String, String> staffIdMap = new HashMap<>();
		for(Comment comment: commentList) {
			staffIdMap.put(comment.getStaffId(), "");
		}
		return staffIdMap;
	}



}
