package jp.or.adash.nexus.entity;

/**
 * 職種マスタのEntityクラス
 * @author pgjavaAT
 *
 */
public class Job {

	/**
	 * id 職種ID
	 * largecd 大分類コード
	* middlecd 中分類コード
	* smallcd 小分類コード
	* name 職種名
	 */
	private Integer id;
	private String largecd;
	private String middlecd;
	private String smallcd;
	private String name;

	public Job(Integer id, String largecd, String middlecd, String smallcd, String name) {
		this.id = id;
		this.largecd = largecd;
		this.middlecd = middlecd;
		this.smallcd = smallcd;
		this.name = name;
	}

	public Job(String largecd, String name) {
		this.largecd = largecd;
		this.name = name;
	}

	/**
	 * idを返す
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 大分類コードを返す
	 * @return largecd
	 */
	public String getLargecd() {
		return largecd;
	}

	/**
	 * 中分類コードを返す
	 * @return middlecd
	 */
	public String getMiddlecd() {
		return middlecd;
	}

	/**
	 * 小分類コードを返す
	 * @return smallcd
	 */
	public String getSmallcd() {
		return smallcd;
	}

	/**
	 * 業種名を返す
	 * @return name
	 */
	public String getName() {
		return name;
	}

}
