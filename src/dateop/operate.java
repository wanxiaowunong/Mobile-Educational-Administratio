package dateop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.DBcon;
import pao.Student;
import pao.Teachers;


public class operate {
	private Connection connect;
	public operate() {
		DBcon conn=new DBcon();
		connect=conn.getConn();
	}
	public boolean insert(String snumber,String pwd,String sname,String ssex,String sdept){    //���в������
		PreparedStatement pst=null;
		int n=0;
		System.out.println(sname);
		String sql="insert into student(snumber,pwd,sname,ssex,sdept)"+" values(?,?,?,?,?)";
		try {
			pst=connect.prepareStatement(sql);
			pst.setString(1, snumber);
			pst.setString(2, pwd);
			pst.setString(3, sname);
			pst.setString(4, ssex);
			pst.setString(5, sdept);
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
	public boolean find1(String snumber,String pwd){    //��¼ʱ��ѯ�Ƿ�����û�
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql="select pwd from student where snumber='"+snumber+"'";
		System.out.println("========="+sql);
		String pwd1 = null;
		try {
			pst=connect.prepareStatement(sql);
			rs=pst.executeQuery();
			//��������
			while(rs.next()){
				pwd1=rs.getString(1);
//				u.setBirthday(rs.getDate("birthday"));
//				u.setId(rs.getInt("id"));
//				u.setName(rs.getString("name"));
//				u.setPassword(rs.getString("password"));
//				users.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBcon.closeAll(connect, pst, rs);
		}
		System.out.println(pwd1);
		if(pwd1==null)
			return false;
		if(pwd1.equals(pwd))
		{
			System.out.println("yes"+pwd1);
		return true;
		}
		else return false;
	}
	
	public boolean alert1(String snumber,String pwd){    //�޸�����
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql="update student set pwd='"+pwd+"' where snumber='"+snumber+"'";
		int i=0;
		System.out.println("========="+sql);
		try {
			pst=connect.prepareStatement(sql);
			i = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBcon.closeAll(connect, pst, rs);
		}
		if(i==0)
			return false;
		else
			return true;
	}
	public Student find2(String snumber,String pwd){    //��¼ʱ�����û���Ϣ    
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql="select * from student where snumber='"+snumber+"'";
		System.out.println("========="+sql);
		String pwd1 = null;
		Student u=new Student();
		try {
			pst=connect.prepareStatement(sql);
			rs=pst.executeQuery();
			//��������
			u.setSnumber(null);
			u.setSname(null);
			u.setSsex(null);
			u.setSdept(null);
			u.setPwd(null);
			while(rs.next()){    //���ܻ᷵��һ���տն���
				u.setSnumber(rs.getString(1));
				u.setSname(rs.getString(2));
				u.setSsex(rs.getString(3));
				u.setSdept(rs.getString(4));
				u.setPwd(rs.getString(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBcon.closeAll(connect, pst, rs);
		}
		pwd1=u.getSnumber();
		System.out.println(u.getSnumber());
		return u;
	}
	
	/*
	 * ����������ѯ��ʦ*/
	
	public Teachers find5(String tnumber,String pwd){    //��¼ʱ��ѯ�Ƿ���ڹ����û�   �˴���Ҫ�ж��Ƿ�Ϊ����ѧԺ������Ա
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql="select * from teacher where tnumber='"+tnumber+"'";
		System.out.println("========="+sql);
		String pwd1 = null;
		String admin="";
		Teachers tea=new Teachers();
		try {
			pst=connect.prepareStatement(sql);
			rs=pst.executeQuery();
			//��������
			while(rs.next()){
				pwd1=rs.getString("pwd");
				System.out.println(pwd1);
//				admin=rs.getString(2).trim();
				tea.setTname(rs.getString("Tname"));   //�����ݴ�ŵ���ʦ����
				tea.setPwd(rs.getString("pwd"));
				tea.setTadmin(rs.getInt("tadmin"));
				tea.setTdept(rs.getString("tdpart"));
				tea.setTnumber(tnumber);
				tea.setTsex(rs.getString("Tsex"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBcon.closeAll(null, pst, rs);
		}
		System.out.println(tea.getTname());
		
//		return tea;
		if(tea.getPwd()==null)
			
			return null;
		if(tea.getPwd().equals(pwd)&&tea.getTadmin()==1)
		{
			System.out.println("yes"+pwd1);
		return tea;
		}
		else return null;
		
	}
	
	
	
	//����������ѯ
	public List<Student> find(String snumber,String psd){
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
		public boolean update(String snumber,String pwd,String sname,String ssex,String sdept){   //����
			PreparedStatement pst=null;
			ResultSet rs=null;
			String sql = "update student set pwd=?,sname=?,ssex=?,sdept=? where snumber=?";
			
			int i=0;
			try {
				pst = connect.prepareStatement(sql);
				pst.setString(1, pwd);
				pst.setString(2, sname);
				pst.setString(3, ssex);
				pst.setString(4, sdept);
				
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
