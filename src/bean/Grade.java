package bean;

public class Grade {
	private String snumber;
	private String sname;
	private String cnumber;
	private String cname;
	private String tnumber;
	private String tname;
	private String term;
	private int grade;
	private int score;
	
	/**
	 * @param snumber
	 * @param sname
	 * @param cnumber
	 * @param cname
	 * @param tnumber
	 * @param tname
	 * @param grade
	 * @param term
	 */

	public Grade(String snumber, String sname, String cnumber, String cname, String tnumber, String tname, String term,
			int grade,int score) {
		this.snumber = snumber;
		this.sname = sname;
		this.cnumber = cnumber;
		this.cname = cname;
		this.tnumber = tnumber;
		this.tname = tname;
		this.term = term;
		this.grade = grade;
		this.score=score;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getTnumber() {
		return tnumber;
	}
	public void setTnumber(String tnumber) {
		this.tnumber = tnumber;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
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
