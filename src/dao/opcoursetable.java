package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.DBcon;
import bean.Course;
import bean.CourseInfotable;
import bean.Student;
import util.parsecoursetime;

public class opcoursetable {
	private Connection connect;
	public opcoursetable() {
		DBcon conn=new DBcon();
		connect=conn.getConn();
	}

	public List<CourseInfotable> select(String cyear,String number,String identity){    //��ѯ��ʦ��ѧ���Ŀα���Ϣ�������ؿα�
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql="";
		if(identity.equals("student")) //������ѯѧ���Ŀα�
			sql="select * from course,grade,teacher,courseapply where courseapply.tnumber=grade.tnumber and courseapply" + 
					".cnumber=grade.cnumber and courseapply.agree='true' and course.cnumber=grade.cnumber and teacher.Tnumber=grade.tnumber and grade.snumber='"+number+"'and grade.cyear='"+cyear+"'";
		else if(identity.equals("teacher"))
			sql="select * from course,courseapply,teacher where course.cnumber=courseapply.cnumber and teacher.Tnumber=courseapply.tnumber and courseapply.agree='true' and courseapply.tnumber='"+
		         number+"' and courseapply.applyterm='"+cyear+"'";
		System.out.println("========="+sql);
		List<CourseInfotable> courses=new ArrayList<CourseInfotable>();
		try {
			pst=connect.prepareStatement(sql);
			rs=pst.executeQuery();
			
			//��������
			while(rs.next()){
//				u.setBirthday(rs.getDate("birthday"));
//				u.setId(rs.getInt("id"));
//				u.setName(rs.getString("name"));
//				u.setPassword(rs.getString("password"));
//				users.add(u);
				courses.add(new CourseInfotable(rs.getString("cname"),rs.getString("courseplace"),rs.getString("Tname"),rs.getString("ctime"),2+"","8:50","9:50",parsecoursetime.getIdString(2,rs.getString("coursetime")),parsecoursetime.getIdString(3,rs.getString("coursetime"))));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBcon.closeAll(connect, pst, rs);
		}
		System.out.println("���ݿⷵ��"+courses.size());
		return courses;
	}
//	public List<Course> select2(String cdept,String cyear,String snumber){    //��ѯĳ��רҵѧ�ڿγ�
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
//			//��������
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
//		System.out.println("���ݿⷵ��"+courses.size());
//		return courses;
//	}
	//����������ѯ
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
			//��������
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
//		public User find(Integer id){   //����Ų�ѯ
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
//				//��������
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
		public boolean update(String tnumber,String pwd,String tname,String tsex,String tdept,String tadmin){   //����
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
		public boolean delete(String snumber){   //ɾ��
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

