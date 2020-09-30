package jp.or.adash.nexus.entity;

/**
 * 勤務地都道府県マスタのEntityクラス
 * @author pgjavaAT
 *
 */
public class Todouhuken {

	/**
	 * cd 勤務地都道府県コード
	* name 都道府県名
	 */
	private String cd;
	private String name;


	public Todouhuken() {

	}

	public Todouhuken(String cd, String name) {
		this.cd = cd;
		this.name = name;
	}

	/**
	 * 勤務地都道府県コードを返す
	 * @return cd
	 */
	public String getcd() {
		return cd;
	}

	/**
	 * 都道府県名を返す
	 * @return name
	 */
	public String getname() {
		return name;
	}
}

//getName > getname
//getCd > getcd


