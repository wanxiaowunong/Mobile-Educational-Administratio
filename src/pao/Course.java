package pao;

public class Course {

		   private String cnumber;
		   private String cname;
		   private String ctime;
		   private String cdept;
		   private String cstart;
		   private String cend;
		   private String cyear;
		   private String flag;  //标志位，判断该门课程是否已经申请
		public Course() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Course(String cnumber,String cname,String cdept,String ctime,String cstart,String cend,String cyear,String flag) {
			this.cnumber=cnumber;
			this.cname=cname;
			this.ctime=ctime;
			this.cdept=cdept;
			this.cstart=cstart;
			this.cend=cend;
			this.cyear=cyear;
			this.flag=flag;
		}
		public String getFlag() {
			return flag;
		}
		public void setFlag(String flag) {
			this.flag = flag;
		}
		public String getCnumber() {
			return cnumber;
		}
		public void setCnumber(String cnumber) {
			this.cnumber = cnumber;
		}
		public String getCname() {
			return cname;
		}
		public void setCname(String cname) {
			this.cname = cname;
		}
		public String getCtime() {
			return ctime;
		}
		public void setCtime(String ctime) {
			this.ctime = ctime;
		}
		public String getCdept() {
			return cdept;
		}
		public void setCdept(String cdept) {
			this.cdept = cdept;
		}
		public String getCstart() {
			return cstart;
		}
		public void setCstart(String cstart) {
			this.cstart = cstart;
		}
		public String getCend() {
			return cend;
		}
		public void setCend(String cend) {
			this.cend = cend;
		}
		public String getCyear() {
			return cyear;
		}
		public void setCyear(String cyear) {
			this.cyear = cyear;
		}
		
	


}
