package dateop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.DBcon;
import pao.Course;
import pao.Grade;
import pao.Student;
import util.getterm;

public class opgrade {
	private Connection connect;
	public opgrade() {
		DBcon conn=new DBcon();
		connect=conn.getConn();
	}
	
	public boolean insert(String snumber,String cnumber,String tnumber,String coursetime){    //���в������,
		PreparedStatement pst=null;
		int n=0;
//		���ж����ʱ�����û��ѡ��
		String sql="insert into grade(snumber,cnumber,tnumber,cyear)"+" values(?,?,?,?)";
		try {
			pst=connect.prepareStatement(sql);
			pst.setString(1, snumber);
			pst.setString(2, cnumber);
			pst.setString(3, tnumber);
			pst.setString(4,getterm.getTerms());
//			pst.setInt(3, score);
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
	public boolean delete(String snumber,String cnumber){    //����ɾ������,
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
	 * ����������ѯ*/
	public List<Grade> select(String snumber,String cnumber){    //��ʦͨ���γ̺Ų�ѯ��ѧ��ͨ��ѧ�Ų�ѯ
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql="select * from grade where ";
		if(!snumber.equals("null")) 
			sql+="snumber='"+snumber+"'";
		if(!cnumber.equals("null")) 
			sql+="cnumber='"+cnumber+"'";
			
		System.out.println("========="+sql);
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
				grades.add( new Grade(rs.getString(1),rs.getString(2),rs.getInt(3)));
			}
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
				grade=rs.getString(3);
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
	public int count(String cnumber,String tnumber) {
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql="select count(*) from grade where cnumber='"+cnumber+"' and tnumber='"+tnumber+"'";
		int cut=0;
		try {
			pst=connect.prepareStatement(sql);
			rs=pst.executeQuery();
			//��������
				while(rs.next()){
				cut=Integer.parseInt(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBcon.closeAll(connect, pst, rs);
		}
		return cut;
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
