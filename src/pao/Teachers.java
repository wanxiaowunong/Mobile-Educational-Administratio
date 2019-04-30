package pao;

public class Teachers {
	   private String tnumber;
	   private String pwd;
	   private String tname;
	   private String tsex;
	   private String tdept;
	   private int tadmin;
	public Teachers() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Teachers(String tnumber,String pwd,String tname,String tsex,String tdept,int tadmin) {
		this.tnumber=tnumber;
		this.pwd=pwd;
		this.tname=tname;
		this.tsex=tsex;
		this.tdept=tdept;
		this.tadmin=tadmin;
	}
	public String getTnumber() {
		return tnumber;
	}
	public void setTnumber(String tnumber) {
		this.tnumber = tnumber;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTsex() {
		return tsex;
	}
	public void setTsex(String tsex) {
		this.tsex = tsex;
	}
	public String getTdept() {
		return tdept;
	}
	public void setTdept(String tdept) {
		this.tdept = tdept;
	}
	public int getTadmin() {
		return tadmin;
	}
	public void setTadmin(int tadmin) {
		this.tadmin = tadmin;
	}
	@Override
	public String toString() {
		return "Teachers [tnumber=" + tnumber + ", pwd=" + pwd + ", tname=" + tname + ", tsex=" + tsex + ", tdept="
				+ tdept + ", tadmin=" + tadmin + "]";
	}
	



}
