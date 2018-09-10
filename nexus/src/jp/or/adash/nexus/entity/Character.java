package jp.or.adash.nexus.entity;

public class Character {
	private int id;
	private String character;

	public Character(int id, String character) {
		this.id = id;
		this.character = character;
	}

	public int getId() {
		return id;
	}

	public String getCharacter() {
		return character;
	}


}
