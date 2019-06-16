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
	
	public String insert(String snumber,String cnumber,String tnumber,String coursetime){    //���в������,
		PreparedStatement pst=null;
		int n=0;
		String sql="";
		ResultSet rs=null;
		try{//���ж����ѧ������Ŀγ�ʱ���뱾ѧ��������Ŀγ�ʱ���Ƿ��ͻ
//			sql="select * from grade,courseapply where snumber=? and courseapply.tnumber=grade.tnumber and grade.cyear=? " + 
//					"and courseapply.coursetime=?";
			
			//Todo �˴���ѯ������ 
			sql="select * from grade,courseapply where courseapply.tnumber=grade.tnumber and courseapply.agree='true' and courseapply.cnumber=grade.cnumber \r\n" + 
					"and grade.snumber='"+snumber+"' and courseapply.coursetime='"+coursetime+"' and grade.cyear='"+getterm.getTerms()+"'";
			pst=connect.prepareStatement(sql);
//			pst.setString(1, snumber);
//			pst.setString(2, getterm.getTerms());
//			pst.setString(3, coursetime);
			System.out.println(sql);
			rs=pst.executeQuery();	
			if(rs.next())         //˵����ͻ�����ܲ���
				{
				System.out.println("ʱ���ͻ");
				return "��ʱ�����ѡ�޿γ̣���ѡ�������ڿ���ʦ��������α�";
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
			return "���ݿ����ʧ�ܣ����Ժ����Ի�����ϵ����Ա";
	}
	public boolean delete(String snumber,String cnumber){    //����ɾ������,
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
	 * ����������ѯ*/
	public List<Grade> select(String snumber,String cnumber,String term){    //��ʦͨ���γ̺Ų�ѯ��ѧ��ͨ��ѧ�Ų�ѯ
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
			//��������
			while(rs.next()){
//				u.setBirthday(rs.getDate("birthday"));
//				u.setId(rs.getInt("id"));
//				u.setName(rs.getString("name"));
//				u.setPassword(rs.getString("password"));
//				users.add(u);
				grades.add( new Grade(rs.getString("snumber"),rs.getString("sname"),rs.getString("cnumber"),
						rs.getString("cname"),rs.getString("tnumber"),rs.getString("tname"),rs.getString("cyear"),rs.getInt("ggrade"),rs.getInt("score")));
			}
			System.out.println("����ѯ��"+grades.size()+"������");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBcon.closeAll(connect, pst, rs);
		}
		System.out.println(grades);
		return grades;
	}
	public List<Grade> select1(String cnumber,String tnumber,String term){    //��ʦͨ���γ̺Ų�ѯ��ѧ��ͨ��ѧ�Ų�ѯ
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql="select * from  grade where cnumber='"+cnumber+"' and tnumber='"+tnumber+"' and cyear='"+term+"'";
		System.out.println(sql);
		List<Grade> grades=new ArrayList<Grade>();
		try {
			pst=connect.prepareStatement(sql);
			rs=pst.executeQuery();
			//��������
			while(rs.next()){
//				String snumber, String sname, String cnumber, String cname, String tnumber, String tname, String term,
//				int grade,int score
				grades.add( new Grade(rs.getString("snumber"),rs.getString("snumber"),rs.getString("cnumber"),
						rs.getString("cnumber"),rs.getString("tnumber"),rs.getString("tnumber"),rs.getString("cyear"),rs.getInt("ggrade"),rs.getInt("score")));
			}
			System.out.println("����ѯ��"+grades.size()+"������");
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
			//��������
		
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
	public List<Student> count(String cnumber,String tnumber,String cyear) {   //��ʦͨ���Լ��Ŀγ̺ź�ѧ�Ų�ѯѧ��ѡ�����
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql="select * from student,grade where grade.snumber=student.Snumber and  cnumber='"+cnumber+"' and " + 
				"tnumber='"+tnumber+"' and ggrade='-1' and cyear='"+cyear+"'";
		System.out.println(sql);
		List<Student> students=new ArrayList<Student>();
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
				students.add( new Student(rs.getString("snumber"),"",rs.getString("sname"),
						rs.getString("ssex"),rs.getString("sdept")));
			}
			System.out.println("����ѯ��"+students.size()+"������");
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
