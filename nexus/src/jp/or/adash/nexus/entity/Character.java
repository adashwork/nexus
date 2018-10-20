package jp.or.adash.nexus.entity;

/**
 * 特性マスタのEntityクラス
 * @author pgjavaAT
 *
 */
public class Character {

	/**
	 * id 特性ID
  	* character 特性
	 */
	private Integer id;
	private String character;

	public Character(Integer id, String character) {
		this.id = id;
		this.character = character;
	}

	/**
	 * 特性IDを返す
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 特性を返す
	 * @return character
	 */
	public String getCharacter() {
		return character;
	}

}
