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
import util.getterm;

public class opcourseapply {
	private Connection connect;
	public opcourseapply() {
		DBcon conn=new DBcon();
		connect=conn.getConn();
	}
	public String insert(String tnumber,String cnumber,String applyterm,String courseplace,String coursetime){    //进行插入操作,
		PreparedStatement pst=null;
		int n=0;
		ResultSet rs=null;
		String sql="";
		
		
		try{//先判断申请的时间地点是否与确定下来的课程时间地点冲突，
			sql="select * from courseapply where courseplace=? and coursetime=? and applyterm=? and agree='true'";//判断相同的时间地点是否冲突
			pst=connect.prepareStatement(sql);
			pst.setString(1, courseplace);
			pst.setString(2, coursetime);
			pst.setString(3, applyterm);
			System.out.println(sql);
			rs=pst.executeQuery();	
			if(rs.next())         //说明冲突，则不能插入
				{
				System.out.println("教室时间冲突");
				return "教室时间冲突，请重新申请";
				}
			else {    //说明不冲突，此时应当判断是否与自己已经确定下来的课程冲突
				System.out.println("与当前安排不冲突，继续进行判断与自己已定课程是否冲突");
				sql="select * from courseapply where tnumber=? and coursetime=? and applyterm=? and agree='true'";//判断与自己时间是否冲突
//				sql="select * from courseapply where tnumber='"+tnumber+"'and coursetime='"+coursetime+"'and applyterm='"+applyterm+"'and agree='true'";
				pst=connect.prepareStatement(sql);
				pst.setString(1, tnumber);
				pst.setString(2, coursetime);
				pst.setString(3, applyterm);
				System.out.println(sql);
				rs=pst.executeQuery();	
				if(rs.next())      //冲突
					{					
						System.out.println("自己时间冲突");
						return "您申请的时间段已有课程安排，请重新申请";
					}
				else {  //不冲突，现在得判断是插入还是修改
					System.out.println("自己时间不冲突，判断是首次申请还是修改申请");
					sql="select * from courseapply where cnumber=? and tnumber=? and applyterm=? and agree='false'";
					pst=connect.prepareStatement(sql);
					pst.setString(1, cnumber);
					pst.setString(2, tnumber);
					pst.setString(3, applyterm);
					System.out.println(sql);
					rs=pst.executeQuery();	
					if(rs.next()) {   //非空，说明之前已经申请，此时需要update先有数据
						System.out.println("已申请过该门课程，现在将对申请信息进行更改");
						sql="update courseapply set courseplace=?,coursetime=? where cnumber=? and tnumber=? and applyterm=? and agree='false'";
						pst=connect.prepareStatement(sql);
						pst.setString(1, courseplace);
						pst.setString(2, coursetime);
						pst.setString(3, cnumber);
						pst.setString(4, tnumber);
						pst.setString(5, applyterm);
						System.out.println(sql);
						n=pst.executeUpdate();
						System.out.println("更改成功");
						}
					else {   //没有，则进行插入,  
						 sql="insert into courseapply(tnumber,cnumber,applyterm,courseplace,coursetime)"+" values(?,?,?,?,?)";
						 pst=connect.prepareStatement(sql);
						 pst.setString(1, tnumber);
						 pst.setString(2, cnumber);
						 pst.setString(3, applyterm);
						 pst.setString(4, courseplace);
						 pst.setString(5, coursetime);
						 System.out.println(sql);
						 n=pst.executeUpdate();		
						 System.out.println("插入成功");
					}
			 }
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
			 return "申请失败，插入数据出现问题，请联系客服或稍后重试";
		
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
				agree=rs.getString("agree");
				if(agree.equals("0"))
					agree=rs.getString("descri");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBcon.closeAll(connect, pst, rs);
		}

		return agree;
	}

		public String update(int id,int flag,String coursetime,String courseplace){   //更新
			PreparedStatement pst=null;
			ResultSet rs=null;
			String sql="";
			String mess="";

			int i=0;
			try{
				if(flag==1)   //修改之前需要判断教室时间是否冲突,以及和自己已通过课程是否冲突
				{
				//先判断申请的时间地点是否与确定下来的课程时间地点冲突，
					sql="select * from courseapply where courseplace=? and coursetime=? and applyterm=? and agree='true'";//判断相同的时间地点是否冲突
					pst=connect.prepareStatement(sql);
					pst.setString(1, courseplace);
					pst.setString(2, coursetime);
					pst.setString(3, getterm.getTerms());
					System.out.println(sql);
					rs=pst.executeQuery();	
					if(rs.next())         //说明冲突，则不能插入
						{
						System.out.println("教室时间冲突");
						mess="教室时间冲突，请重新申请";
						sql = "update courseapply set agree=0 , descri='"+mess+"' where id='"+id+"'";
//						return false;
						}
					else {    //说明不冲突，此时应当判断是否与自己已经确定下来的课程冲突
						System.out.println("与当前安排不冲突，继续进行判断与自己已定课程是否冲突");
						sql="select * from courseapply where coursetime=? and applyterm=? and agree='true' and tnumber in (select tnumber "
								+ "from courseapply where id=? )";//判断与自己时间是否冲突
//						sql="select * from courseapply where tnumber='"+tnumber+"'and coursetime='"+coursetime+"'and applyterm='"+applyterm+"'and agree='true'";
						pst=connect.prepareStatement(sql);
						pst.setString(1, coursetime);
						pst.setString(2, getterm.getTerms());
						pst.setInt(3,id);
						System.out.println(sql);
						rs=pst.executeQuery();	
						if(rs.next())      //冲突
							{					
								System.out.println("自己时间冲突");
								mess= "您申请的时间段已有课程安排，请重新申请";
								sql = "update courseapply set agree=0 , descri='"+mess+"' where id='"+id+"'";
//								return false;
							}
						else {  //不冲突，update
							sql = "update courseapply set agree='true' where id='"+id+"'";
						}
					}
				}
				else if(flag==0)
				   sql = "update courseapply set agree=0 ,descri='管理员驳回' where id='"+id+"'";
			
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
				return "更新数据失败";
			}
			else if(flag==0||mess.equals(""))
			return "success";
			else
				return mess;
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
		public String delete(String snumber,String cnumber,String cyear){   //删除
			PreparedStatement pst=null;
			ResultSet rs=null;
			String sql = "delete from courseapply where tnumber='"+snumber+"' and cnumber='"+cnumber+"' and applyterm='"+cyear+"'";
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
				return "fail";
			}
			else
			return "success";
		}

}
