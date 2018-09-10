package jp.or.adash.nexus.entity;

public class License {
	private int id;
	private String license;

	public License(int id, String license) {
		this.id = id;
		this.license = license;
	}

	public int getId() {
		return id;
	}

	public String getLicense() {
		return license;
	}

}
