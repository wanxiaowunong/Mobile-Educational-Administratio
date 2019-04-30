package pao;

public class Grade {
	private String snumber;
	private String cnumber;
	private int grade;
	
	public Grade(String snumber,String cnumber,int grade) {
		this.snumber=snumber;
		this.cnumber=cnumber;
		this.grade=grade;
	}
	public String getSnumber() {
		return snumber;
	}
	public void setSnumber(String snumber) {
		this.snumber = snumber;
	}
	public String getCnumber() {
		return cnumber;
	}
	public void setCnumber(String cnumber) {
		this.cnumber = cnumber;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	

}
