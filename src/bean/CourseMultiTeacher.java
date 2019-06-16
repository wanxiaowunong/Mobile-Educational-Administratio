package bean;

public class CourseMultiTeacher {
	private String tname;
	private String tnumber;
	private String courseplace;
	private String coursetime;
	private String courseselected;
	public CourseMultiTeacher(String tname, String tnumber, String courseplace, String coursetime,
			String courseselected) {
		this.tname = tname;
		this.tnumber = tnumber;
		this.courseplace = courseplace;
		this.coursetime = coursetime;
		this.courseselected = courseselected;
	}
	public CourseMultiTeacher() {
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTnumber() {
		return tnumber;
	}
	public void setTnumber(String tnumber) {
		this.tnumber = tnumber;
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
	public String getCourseselected() {
		return courseselected;
	}
	public void setCourseselected(String courseselected) {
		this.courseselected = courseselected;
	}
	
	

}
