package pao;

public class Courseapply {
	private int id;
	private String tname;
	private String cname;
	private String courseplace;
	private String coursetime;
	public Courseapply(int id,String tnumber, String cnumber,String tnu, String cnu) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.tname = tnumber;
		this.cname = cnumber;
		this.courseplace=tnu;
		this.coursetime=cnu;
	}
	public  Courseapply() {}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCourseplace() {
		return courseplace;
	}
	public void setCourseplace(String courseplace) {
		this.courseplace = courseplace;
	}
	public String getCoursetime() {
		return coursetime;
	}
	public void setCoursetime(String coursetime) {
		this.coursetime = coursetime;
	}
	



}
