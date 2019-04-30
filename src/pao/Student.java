package pao;

import java.util.Date;

public class Student {
   private String snumber;
   private String pwd;
   private String sname;
   private String ssex;
   private String sdept;
public Student() {
	super();
	// TODO Auto-generated constructor stub
}
public Student(String snumber,String pwd,String sname,String ssex,String sdept) {
	this.snumber=snumber;
	this.pwd=pwd;
	this.sname=sname;
	this.ssex=ssex;
	this.sdept=sdept;
}


public String getSnumber() {
	return snumber;
}
public void setSnumber(String snumber) {
	this.snumber = snumber;
}
public String getPwd() {
	return pwd;
}
public void setPwd(String pwd) {
	this.pwd = pwd;
}
public String getSname() {
	return sname;
}
public void setSname(String sname) {
	this.sname = sname;
}
public String getSsex() {
	return ssex;
}
public void setSsex(String ssex) {
	this.ssex = ssex;
}
public String getSdept() {
	return sdept;
}
public void setSdept(String sdept) {
	this.sdept = sdept;
}
@Override
public String toString() {
	return "User [snumber=" + snumber + ", pwd=" + pwd + ", sname=" + sname + ", ssex=" + ssex
			+ ", sdept=" + sdept + "]";
}


	
   
}
