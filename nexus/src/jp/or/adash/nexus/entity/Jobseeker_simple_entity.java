package jp.or.adash.nexus.entity;

public class Jobseeker_simple_entity {
	private String id;
	private String js_name;
	private Integer age;
	private String sex;
	private String hopejobcategory;
	private String hopejob1;
	private String hopeworkplace;
	private String st_name;

	public Jobseeker_simple_entity(String id, String js_name,Integer age,
			String sex,String hopejobcategory,
			String hopejob1,String hopeworkplace,String st_name) {
		this.id = id;
		this.js_name = js_name;
		this.age = age;
		this.sex = sex;
		this.hopejobcategory = hopejobcategory ;
		this.hopejob1 = hopejob1;
		this.hopeworkplace = hopeworkplace;
		this.st_name = st_name;
	}

	public String getId() {
		return id;
	}

	public String getJs_name() {
		return js_name;
	}

	public Integer getAge() {
		return age;
	}

	public String getSex() {
		return sex;
	}

	public String getHopejobcategory() {
		return hopejobcategory;
	}

	public String getHopejob1() {
		return hopejob1;
	}

	public String getHopeworkplace() {
		return hopeworkplace;
	}

	public String getSt_name() {
		return st_name;
	}
}

