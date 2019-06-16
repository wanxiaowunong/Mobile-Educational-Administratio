package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.DBcon;
import bean.Course;
import bean.Grade;
import bean.Student;
import util.getterm;

public class opgrade {
	private Connection connect;
	public opgrade() {
		DBcon conn=new DBcon();
		connect=conn.getConn();
	}
	
	public String insert(String snumber,String cnumber,String tnumber,String coursetime){    //进行插入操作,
		PreparedStatement pst=null;
		int n=0;
		String sql="";
		ResultSet rs=null;
		try{//先判断这个学生申请的课程时间与本学期已申请的课程时间是否冲突
//			sql="select * from grade,courseapply where snumber=? and courseapply.tnumber=grade.tnumber and grade.cyear=? " + 
//					"and courseapply.coursetime=?";
			
			//Todo 此处查询有问题 
			sql="select * from grade,courseapply where courseapply.tnumber=grade.tnumber and courseapply.agree='true' and courseapply.cnumber=grade.cnumber \r\n" + 
					"and grade.snumber='"+snumber+"' and courseapply.coursetime='"+coursetime+"' and grade.cyear='"+getterm.getTerms()+"'";
			pst=connect.prepareStatement(sql);
//			pst.setString(1, snumber);
//			pst.setString(2, getterm.getTerms());
//			pst.setString(3, coursetime);
			System.out.println(sql);
			rs=pst.executeQuery();	
			if(rs.next())         //说明冲突，则不能插入
				{
				System.out.println("时间冲突");
				return "该时间段已选修课程，请选择其他授课老师，或调整课表";
//				return false;
				}
			else {  
				sql="insert into grade(snumber,cnumber,tnumber,cyear)"+" values(?,?,?,?)";
				pst=connect.prepareStatement(sql);
				pst.setString(1, snumber);
				pst.setString(2, cnumber);
				pst.setString(3, tnumber);
				pst.setString(4,getterm.getTerms());
//				pst.setInt(3, score);
				System.out.println(sql);
				n=pst.executeUpdate();		
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBcon.closeAll(connect, pst, null);
		}
		
		if(n>0)
		 return "success";
		else
			return "数据库插入失败，请稍后再试或者联系管理员";
	}
	public boolean delete(String snumber,String cnumber){    //进行删除操作,
		PreparedStatement pst=null;
		int n=0;
		String sql="delete from grade where snumber='"+snumber+"'and cnumber='"+cnumber+"'";
		try {
			pst=connect.prepareStatement(sql);
//			pst.setString(1, snumber);
//			pst.setString(2, cnumber);
			System.out.println(sql);
			n=pst.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBcon.closeAll(connect, pst, null);
		}
		if(n>0)
		 return true;
		else
			return false;
	}
	/*
	 * 根据条件查询*/
	public List<Grade> select(String snumber,String cnumber,String term){    //老师通过课程号查询，学生通过学号查询
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql="select * from student,teacher,courseapply,grade,course where\r\n" + 
				"student.Snumber=grade.snumber and courseapply.tnumber=grade.tnumber and courseapply.cnumber=course.cnumber and teacher.Tnumber=grade.tnumber\r\n" + 
				"and grade.cnumber=courseapply.cnumber and ";
		if(!snumber.equals("null")) 
			sql+="grade.snumber='"+snumber+"'";
		if(!cnumber.equals("null")) 
			sql+="grade.cnumber='"+cnumber+"'";
		if(!term.equals("null")) 
			sql+="and ggrade not in (-1,-2) and grade.cyear='"+term+"'";
			
		System.out.println(sql);
		List<Grade> grades=new ArrayList<Grade>();
		try {
			pst=connect.prepareStatement(sql);
			rs=pst.executeQuery();
			//处理结果集
			while(rs.next()){
//				u.setBirthday(rs.getDate("birthday"));
//				u.setId(rs.getInt("id"));
//				u.setName(rs.getString("name"));
//				u.setPassword(rs.getString("password"));
//				users.add(u);
				grades.add( new Grade(rs.getString("snumber"),rs.getString("sname"),rs.getString("cnumber"),
						rs.getString("cname"),rs.getString("tnumber"),rs.getString("tname"),rs.getString("cyear"),rs.getInt("ggrade"),rs.getInt("score")));
			}
			System.out.println("共查询到"+grades.size()+"条数据");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBcon.closeAll(connect, pst, rs);
		}
		System.out.println(grades);
		return grades;
	}
	public List<Grade> select1(String cnumber,String tnumber,String term){    //老师通过课程号查询，学生通过学号查询
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql="select * from  grade where cnumber='"+cnumber+"' and tnumber='"+tnumber+"' and cyear='"+term+"'";
		System.out.println(sql);
		List<Grade> grades=new ArrayList<Grade>();
		try {
			pst=connect.prepareStatement(sql);
			rs=pst.executeQuery();
			//处理结果集
			while(rs.next()){
//				String snumber, String sname, String cnumber, String cname, String tnumber, String tname, String term,
//				int grade,int score
				grades.add( new Grade(rs.getString("snumber"),rs.getString("snumber"),rs.getString("cnumber"),
						rs.getString("cnumber"),rs.getString("tnumber"),rs.getString("tnumber"),rs.getString("cyear"),rs.getInt("ggrade"),rs.getInt("score")));
			}
			System.out.println("共查询到"+grades.size()+"条数据");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBcon.closeAll(connect, pst, rs);
		}
		System.out.println(grades);
		return grades;
	}
	public String find(String cnumber,String snumber){
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		String sql="select * from grade where cnumber='"+cnumber+"' and snumber='"+snumber+"'";
		String grade="";
		try {
			pst=connect.prepareStatement(sql);
			rs=pst.executeQuery();
			//处理结果集
		
				while(rs.next()){
				grade=rs.getInt("ggrade")+"";
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBcon.closeAll(connect, pst, rs);
		}
		System.out.println(sql+"   "+grade);
		return grade;
		
	}
	public List<Student> count(String cnumber,String tnumber,String cyear) {   //老师通过自己的课程号和学号查询学生选课情况
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql="select * from student,grade where grade.snumber=student.Snumber and  cnumber='"+cnumber+"' and " + 
				"tnumber='"+tnumber+"' and ggrade='-1' and cyear='"+cyear+"'";
		System.out.println(sql);
		List<Student> students=new ArrayList<Student>();
		try {
			pst=connect.prepareStatement(sql);
			rs=pst.executeQuery();
			//处理结果集
			while(rs.next()){
//				u.setBirthday(rs.getDate("birthday"));
//				u.setId(rs.getInt("id"));
//				u.setName(rs.getString("name"));
//				u.setPassword(rs.getString("password"));
//				users.add(u);
				students.add( new Student(rs.getString("snumber"),"",rs.getString("sname"),
						rs.getString("ssex"),rs.getString("sdept")));
			}
			System.out.println("共查询到"+students.size()+"条数据");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBcon.closeAll(connect, pst, rs);
		}
		System.out.println(students);
		return students;
	}
	public boolean update(String snumber,String cnumber,String term,int grade,int score,int flag) {
		PreparedStatement pst=null;
		int n=0;
		String sql="";
		if(flag==0)
			sql="update grade set ggrade='-2' where snumber='"+snumber+"'and cnumber='"+cnumber+"' and cyear='"+term+"' and ggrade='-1'";
		else if(flag==1)
			sql="update grade set ggrade='"+grade+"' , score='"+score+"' where snumber='"+snumber+"'and cnumber='"+cnumber+"' and cyear='"+term+"'";
		try {
			pst=connect.prepareStatement(sql);
//			pst.setString(1, snumber);
//			pst.setString(2, cnumber);
			System.out.println(sql);
			n=pst.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBcon.closeAll(connect, pst, null);
		}
		if(n>0)
		 return true;
		else
			return false;
		
	}
	//根据条件查询
	public List<Student> find2(String snumber,String psd){
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		List<Student> users=new ArrayList<Student>();
		String sql="select * from student where 1=1";
		
		if(snumber!=null){
			sql=sql+" and snumber='"+snumber+"'";
		}
		if(psd!=null){
			sql=sql+" and pwd='"+psd+"'";
		}
		try {
			pst=connect.prepareStatement(sql);
			rs=pst.executeQuery();
			//处理结果集
			while(rs.next()){
				
				Student u=new Student();
				u.setSnumber(rs.getString("snumber"));
				u.setSname(rs.getString("sname"));
				u.setSsex(rs.getString("ssex"));
				u.setSdept(rs.getString("sdept"));
				u.setPwd(rs.getString("pwd"));
				users.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBcon.closeAll(connect, pst, rs);
		}
		return users;
	}
//		public User find(Integer id){   //按编号查询
//			PreparedStatement pst=null;
//			ResultSet rs=null;
//			String sql="select * from user1 where ";
//			if(id!=null){
//				sql=sql+"id='"+id+"'";
//			}User u=new User();
//			try {
//				pst=connect.prepareStatement(sql);
//				rs=pst.executeQuery();
//				//System.out.println("ok");
//				//处理结果集
//				while(rs.next()){
//					
//					u.setBirthday(rs.getDate("birthday"));
//					u.setId(rs.getInt("id"));
//					u.setName(rs.getString("name"));
//					u.setPassword(rs.getString("password"));
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}finally{
//				DBcon.closeAll(connect, pst, rs);
//			}
//			return u;
//		}
		public boolean update(String tnumber,String pwd,String tname,String tsex,String tdept,String tadmin){   //更新
			PreparedStatement pst=null;
			ResultSet rs=null;
			String sql = "update teacher set pwd=?,tname=?,tsex=?,tdpart=? where tnumber=?";
			
			int i=0;
			try {
				pst = connect.prepareStatement(sql);
				pst.setString(1, pwd);
				pst.setString(2, tname);
				pst.setString(3, tsex);
				pst.setString(4, tdept);
				
				System.out.println(sql);
				i = pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBcon.closeAll(connect, pst, rs);
			}
			if(i == 0) {
				return false;
			}
			else
			return true;
		}
		public boolean delete(String snumber){   //删除
			PreparedStatement pst=null;
			ResultSet rs=null;
			List<Student> users=new ArrayList<Student>();
			String sql = "delete from student where snumber="+snumber;
			int i=0;
			try {
				pst=connect.prepareStatement(sql);
				i =pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBcon.closeAll(connect, pst, rs);
			}
			if(i == 0) {
				return false;
			}
			else
			return true;
		}

}
