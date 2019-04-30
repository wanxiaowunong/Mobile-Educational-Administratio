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

public class opteacher {
	private Connection connect;
	public opteacher() {
		DBcon conn=new DBcon();
		connect=conn.getConn();
	}
	//专业管理员向teacher表中插入数据
	public boolean insert(String tnumber,String pwd,String tname,String tsex,String tdept,String tadmin){    //进行插入操作
		PreparedStatement pst=null;
		int n=0;
		String sql="insert into teacher(tnumber,pwd,tname,tsex,tdepart,tadmin)"+" values(?,?,?,?,?,?)";
		try {
			pst=connect.prepareStatement(sql);
			pst.setString(1, tnumber);
			pst.setString(2, pwd);
			pst.setString(3, tname);
			pst.setString(4, tsex);
			pst.setString(5, tdept);
			pst.setString(5, tadmin);
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
	public boolean find1(String snumber,String pwd){    //登录时查询是否存在用户
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql="select pwd from student where snumber='"+snumber+"'";
		System.out.println("========="+sql);
		String pwd1 = null;
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
	//根据条件查询
	public Teachers find2(String snumber,String pwd){    //登录时返回用户信息    
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql="select * from teacher where tnumber='"+snumber+"'";
		System.out.println("========="+sql);
		String pwd1 = null;
		Teachers u=new Teachers();
		try {
			pst=connect.prepareStatement(sql);
			rs=pst.executeQuery();
			//处理结果集
			u.setTnumber(null);
			u.setTname(null);
			u.setTdept(null);
			u.setPwd(null);
			while(rs.next()){    //可能会返回一个空空对象
				u.setTnumber(rs.getString("tnumber"));
				u.setTname(rs.getString("tname"));
				u.setTdept(rs.getString("tdpart"));
				u.setPwd(rs.getString("pwd"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBcon.closeAll(connect, pst, rs);
		}
		pwd1=u.getTnumber();
		System.out.println(u.getTnumber());
		return u;
	}
	public boolean alert1(String snumber,String pwd){    //登录时查询是否存在用户
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql="update teacher set pwd='"+pwd+"' where tnumber='"+snumber+"'";
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
		else return true;
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
