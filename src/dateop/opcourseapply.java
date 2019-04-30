package dateop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.DBcon;
import pao.Course;
import pao.CourseMultiTeacher;
import pao.Courseapply;
import pao.Grade;
import pao.Student;

public class opcourseapply {
	private Connection connect;
	public opcourseapply() {
		DBcon conn=new DBcon();
		connect=conn.getConn();
	}
	public boolean insert(String tnumber,String cnumber,String applyterm,String courseplace,String coursetime){    //进行插入操作,
		PreparedStatement pst=null;
		int n=0;
		String sql="insert into courseapply(tnumber,cnumber,applyterm,courseplace,coursetime)"+" values(?,?,?,?,?)";
		try {
			pst=connect.prepareStatement(sql);
			pst.setString(1, tnumber);
			pst.setString(2, cnumber);
			pst.setString(3, applyterm);
			pst.setString(4, courseplace);
			pst.setString(5, coursetime);
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
	public boolean delete(String snumber,String cnumber,int score){    //进行插入操作,
		PreparedStatement pst=null;
		int n=0;
		String sql="delete from grade where snumber=? and cnumber=? ";
		try {
			pst=connect.prepareStatement(sql);
			pst.setString(1, snumber);
			pst.setString(2, cnumber);
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
	public List<Courseapply> select(String snumber,String cnumber){ 
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql="select * from courseapply where agree='false'";
//		if(!snumber.equals(null)) 
//			sql+="snumber='"+snumber+"'";
//		if(!cnumber.equals(null)) 
//			sql+="cnumber='"+cnumber+"'";
			
		System.out.println("========="+sql);
		List<Courseapply> courses=new ArrayList<Courseapply>();
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
				courses.add( new Courseapply(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(5),rs.getString(6)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBcon.closeAll(connect, pst, rs);
		}
//		System.out.println(grades);
		return courses;
	}
	//根据条件查询
	public String find(String cnumber,String snumber){
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql="select * from courseapply where cnumber='"+cnumber+"' and tnumber='"+snumber+"'";
		String agree="";
		try {
			pst=connect.prepareStatement(sql);
			rs=pst.executeQuery();
			//处理结果集
			while(rs.next()){
				agree=rs.getString(4);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBcon.closeAll(connect, pst, rs);
		}

		return agree;
	}

		public boolean update(int id,int flag){   //更新
			PreparedStatement pst=null;
			ResultSet rs=null;
			String sql="";
			if(flag==1)
			sql = "update courseapply set agree='true' where id='"+id+"'";
			if(flag==0)
				sql = "update courseapply set agree=0 where id='"+id+"'";
			
			int i=0;
			try {
				pst = connect.prepareStatement(sql);
				
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
		public List<CourseMultiTeacher> findby(String cnumber){
			PreparedStatement pst=null;
			ResultSet rs=null;
			String sql="select tname,courseapply.tnumber,courseplace,coursetime from " + 
					"teacher,courseapply where courseapply.tnumber=teacher.Tnumber and agree='true' " + 
					"and courseapply.cnumber='"+cnumber+"'";
				
			System.out.println("========="+sql);
			List<CourseMultiTeacher> cteachers=new ArrayList<CourseMultiTeacher>();
			try {
				pst=connect.prepareStatement(sql);
				rs=pst.executeQuery();
				//处理结果集
				while(rs.next()){
//					u.setBirthday(rs.getDate("birthday"));
//					u.setId(rs.getInt("id"));
//					u.setName(rs.getString("name"));
//					u.setPassword(rs.getString("password"));
//					users.add(u);
					cteachers.add( new CourseMultiTeacher(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),""));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBcon.closeAll(connect, pst, rs);
			}
//			System.out.println(grades);
			return cteachers;
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
