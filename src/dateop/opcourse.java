package dateop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.DBcon;
import pao.Course;
import pao.Student;

public class opcourse {
	private Connection connect;
	public opcourse() {
		DBcon conn=new DBcon();
		connect=conn.getConn();
	}
	public String selectcondition(String time) {
		PreparedStatement pst=null;
		int n=0;
		String condi="";
		ResultSet rs=null;
		String sql="select condition from selectcondition where time='"+time+"'";
		try {
			pst=connect.prepareStatement(sql);
			rs=pst.executeQuery();
			//处理结果集
			while(rs.next()){
				condi=rs.getString(1);	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBcon.closeAll(connect, pst, null);
		}
		return condi;
	}
		
	//管理员向course表中插入数据
	public boolean insert(String cnumber,String cname,String cdept,String ctime,String cstart,String cend,String cyear){    //进行插入操作
		PreparedStatement pst=null;
		int n=0;
		String sql="insert into course"+" values(?,?,?,?,?,?,?)";
		try {
			pst=connect.prepareStatement(sql);
			pst.setString(1, cnumber);
			pst.setString(2, cname);
			pst.setString(3, cdept);
			pst.setString(4, ctime);
			pst.setString(5, cstart);
			pst.setString(6, cend);
			pst.setString(7, cyear);
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
	public List<Course> select(String cdept,String cyear,String snumber){    // 学生  查询某个专业学期课程
		PreparedStatement pst=null;
		ResultSet rs=null;
		 
		String sql="select * from course where cdept='"+cdept+"' and cyear='"+cyear+"'";
		System.out.println("========="+sql);
		String pwd1 = null;
		List<Course> courses=new ArrayList<Course>();
		try {
			pst=connect.prepareStatement(sql);
			rs=pst.executeQuery();
			
			//处理结果集
			while(rs.next()){
				pwd1=rs.getString(1);
//				u.setBirthday(rs.getDate("birthday"));
//				u.setId(rs.getInt("id"));
//				u.setName(rs.getString("name"));
//				u.setPassword(rs.getString("password"));
//				users.add(u);
				courses.add(new Course(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),null));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBcon.closeAll(connect, pst, rs);
		}
		System.out.println("数据库返回"+courses.size());
		return courses;
	}
//	public List<Course> select2(String cdept,String cyear,String snumber){    //查询某个专业学期课程
//		PreparedStatement pst=null;
//		ResultSet rs=null;
//		 
//		String sql="select * from course where cdept='"+cdept+"' and cyear='"+cyear+"'and cnumber not in(select cnumber from courseapply where tnumber='"+snumber+"')";
//		System.out.println("========="+sql);
//		String pwd1 = null;
//		List<Course> courses=new ArrayList<Course>();
//		try {
//			pst=connect.prepareStatement(sql);
//			rs=pst.executeQuery();
//			
//			//处理结果集
//			while(rs.next()){
//				pwd1=rs.getString(1);
////				u.setBirthday(rs.getDate("birthday"));
////				u.setId(rs.getInt("id"));
////				u.setName(rs.getString("name"));
////				u.setPassword(rs.getString("password"));
////				users.add(u);
//				courses.add(new Course(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			DBcon.closeAll(connect, pst, rs);
//		}
//		System.out.println("数据库返回"+courses.size());
//		return courses;
//	}
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
