package jp.or.adash.nexus.entity;

public class Career {
	private int id;
	private String career;
	private int nyusyokudt;
	private int taisyokudt;


	public Career(int id, String career, int nyusyokudt, int taisyokudt) {
		this.id = id;
		this.career = career;
		this.nyusyokudt = nyusyokudt;
		this.taisyokudt = taisyokudt;
	}


	public int getId() {
		return id;
	}


	public String getCareer() {
		return career;
	}


	public int getNyusyokudt() {
		return nyusyokudt;
	}


	public int getTaisyokudt() {
		return taisyokudt;
	}


}
