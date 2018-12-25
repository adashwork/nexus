package jp.or.adash.nexus.entity;

public class Jobseeker_simple_entity {
	private String id;
	private String js_name;
//	private Integer age;
	private String sex;
	private String HOPEJOB1;
	private String HOPEJOB2;
	private String HOPEJOB3;
	private String HOPEJOBCATEGORY;
	private String HOPEJOBCATEGORY2;
	private String HOPEJOBCATEGORY3;

	private String hopeworkplace;
	private String st_name;

	public Jobseeker_simple_entity(String id, String js_name,
			String sex, String HOPEJOB1, String HOPEJOB2, String HOPEJOB3, String HOPEJOBCATEGORY, String HOPEJOBCATEGORY2,String HOPEJOBCATEGORY3,
			String hopeworkplace,String st_name) {
		this.id = id;
		this.js_name = js_name;
//		this.age = age;
		this.sex = sex;
		this.HOPEJOB1 = HOPEJOB1;
		this.HOPEJOB2 = HOPEJOB2;
		this.HOPEJOB3 = HOPEJOB3;
		this.HOPEJOBCATEGORY = HOPEJOBCATEGORY ;
		this.HOPEJOBCATEGORY2 = HOPEJOBCATEGORY2 ;
		this.HOPEJOBCATEGORY3 = HOPEJOBCATEGORY3 ;
		this.hopeworkplace = hopeworkplace;
		this.st_name = st_name;
	}

	public String getId() {
		return id;
	}

	public String getJs_name() {
		return js_name;
	}

/*	public Integer getAge() {
		return age;
	}
*/
	public String getSex() {
		return sex;
	}


	public String getHOPEJOB1() {
		return HOPEJOB1;
	}

	public String getHOPEJOB2() {
		return HOPEJOB2;
	}
	public String getHOPEJOB3() {
		return HOPEJOB3;
	}

	public String getHOPEJOBCATEGORY() {
		return HOPEJOBCATEGORY;
	}

	public String getHOPEJOBCATEGORY2() {
		return HOPEJOBCATEGORY2;
	}

	public String getHOPEJOBCATEGORY3() {
		return HOPEJOBCATEGORY3;
	}

	public String getHopeworkplace() {
		return hopeworkplace;
	}

	public String getSt_name() {
		return st_name;
	}
}

