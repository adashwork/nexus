package jp.or.adash.nexus.entity;

public class Job {
	private String largecd;
	private String middlecd;
	private String smallcd;
	private String name;

	public Job(String largecd, String middlecd, String smallcd, String name) {
		this.largecd = largecd;
		this.middlecd = middlecd;
		this.smallcd = smallcd;
		this.name = name;
	}

	public String getLargecd() {
		return largecd;
	}

	public String getMiddlecd() {
		return middlecd;
	}

	public String getSmallcd() {
		return smallcd;
	}

	public String getName() {
		return name;
	}


}
