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
	public String insert(String tnumber,String cnumber,String applyterm,String courseplace,String coursetime){    //���в������,
		PreparedStatement pst=null;
		int n=0;
		ResultSet rs=null;
		String sql="";
		
		
		try{//���ж������ʱ��ص��Ƿ���ȷ�������Ŀγ�ʱ��ص��ͻ��
			sql="select * from courseapply where courseplace=? and coursetime=? and applyterm=? and agree='true'";//�ж���ͬ��ʱ��ص��Ƿ��ͻ
			pst=connect.prepareStatement(sql);
			pst.setString(1, courseplace);
			pst.setString(2, coursetime);
			pst.setString(3, applyterm);
			System.out.println(sql);
			rs=pst.executeQuery();	
			if(rs.next())         //˵����ͻ�����ܲ���
				{
				System.out.println("����ʱ���ͻ");
				return "����ʱ���ͻ������������";
				}
			else {    //˵������ͻ����ʱӦ���ж��Ƿ����Լ��Ѿ�ȷ�������Ŀγ̳�ͻ
				System.out.println("�뵱ǰ���Ų���ͻ�����������ж����Լ��Ѷ��γ��Ƿ��ͻ");
				sql="select * from courseapply where tnumber=? and coursetime=? and applyterm=? and agree='true'";//�ж����Լ�ʱ���Ƿ��ͻ
//				sql="select * from courseapply where tnumber='"+tnumber+"'and coursetime='"+coursetime+"'and applyterm='"+applyterm+"'and agree='true'";
				pst=connect.prepareStatement(sql);
				pst.setString(1, tnumber);
				pst.setString(2, coursetime);
				pst.setString(3, applyterm);
				System.out.println(sql);
				rs=pst.executeQuery();	
				if(rs.next())      //��ͻ
					{					
						System.out.println("�Լ�ʱ���ͻ");
						return "�������ʱ������пγ̰��ţ�����������";
					}
				else {  //����ͻ�����ڵ��ж��ǲ��뻹���޸�
					System.out.println("�Լ�ʱ�䲻��ͻ���ж����״����뻹���޸�����");
					sql="select * from courseapply where cnumber=? and tnumber=? and applyterm=? and agree='false'";
					pst=connect.prepareStatement(sql);
					pst.setString(1, cnumber);
					pst.setString(2, tnumber);
					pst.setString(3, applyterm);
					System.out.println(sql);
					rs=pst.executeQuery();	
					if(rs.next()) {   //�ǿգ�˵��֮ǰ�Ѿ����룬��ʱ��Ҫupdate��������
						System.out.println("����������ſγ̣����ڽ���������Ϣ���и���");
						sql="update courseapply set courseplace=?,coursetime=? where cnumber=? and tnumber=? and applyterm=? and agree='false'";
						pst=connect.prepareStatement(sql);
						pst.setString(1, courseplace);
						pst.setString(2, coursetime);
						pst.setString(3, cnumber);
						pst.setString(4, tnumber);
						pst.setString(5, applyterm);
						System.out.println(sql);
						n=pst.executeUpdate();
						System.out.println("���ĳɹ�");
						}
					else {   //û�У�����в���,  
						 sql="insert into courseapply(tnumber,cnumber,applyterm,courseplace,coursetime)"+" values(?,?,?,?,?)";
						 pst=connect.prepareStatement(sql);
						 pst.setString(1, tnumber);
						 pst.setString(2, cnumber);
						 pst.setString(3, applyterm);
						 pst.setString(4, courseplace);
						 pst.setString(5, coursetime);
						 System.out.println(sql);
						 n=pst.executeUpdate();		
						 System.out.println("����ɹ�");
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
			 return "����ʧ�ܣ��������ݳ������⣬����ϵ�ͷ����Ժ�����";
		
	}
	public boolean delete(String snumber,String cnumber,int score){    //���в������,
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
			//��������
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
	//����������ѯ
	public String find(String cnumber,String snumber){
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql="select * from courseapply where cnumber='"+cnumber+"' and tnumber='"+snumber+"'";
		String agree="";
		try {
			pst=connect.prepareStatement(sql);
			rs=pst.executeQuery();
			//��������
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

		public String update(int id,int flag,String coursetime,String courseplace){   //����
			PreparedStatement pst=null;
			ResultSet rs=null;
			String sql="";
			String mess="";

			int i=0;
			try{
				if(flag==1)   //�޸�֮ǰ��Ҫ�жϽ���ʱ���Ƿ��ͻ,�Լ����Լ���ͨ���γ��Ƿ��ͻ
				{
				//���ж������ʱ��ص��Ƿ���ȷ�������Ŀγ�ʱ��ص��ͻ��
					sql="select * from courseapply where courseplace=? and coursetime=? and applyterm=? and agree='true'";//�ж���ͬ��ʱ��ص��Ƿ��ͻ
					pst=connect.prepareStatement(sql);
					pst.setString(1, courseplace);
					pst.setString(2, coursetime);
					pst.setString(3, getterm.getTerms());
					System.out.println(sql);
					rs=pst.executeQuery();	
					if(rs.next())         //˵����ͻ�����ܲ���
						{
						System.out.println("����ʱ���ͻ");
						mess="����ʱ���ͻ������������";
						sql = "update courseapply set agree=0 , descri='"+mess+"' where id='"+id+"'";
//						return false;
						}
					else {    //˵������ͻ����ʱӦ���ж��Ƿ����Լ��Ѿ�ȷ�������Ŀγ̳�ͻ
						System.out.println("�뵱ǰ���Ų���ͻ�����������ж����Լ��Ѷ��γ��Ƿ��ͻ");
						sql="select * from courseapply where coursetime=? and applyterm=? and agree='true' and tnumber in (select tnumber "
								+ "from courseapply where id=? )";//�ж����Լ�ʱ���Ƿ��ͻ
//						sql="select * from courseapply where tnumber='"+tnumber+"'and coursetime='"+coursetime+"'and applyterm='"+applyterm+"'and agree='true'";
						pst=connect.prepareStatement(sql);
						pst.setString(1, coursetime);
						pst.setString(2, getterm.getTerms());
						pst.setInt(3,id);
						System.out.println(sql);
						rs=pst.executeQuery();	
						if(rs.next())      //��ͻ
							{					
								System.out.println("�Լ�ʱ���ͻ");
								mess= "�������ʱ������пγ̰��ţ�����������";
								sql = "update courseapply set agree=0 , descri='"+mess+"' where id='"+id+"'";
//								return false;
							}
						else {  //����ͻ��update
							sql = "update courseapply set agree='true' where id='"+id+"'";
						}
					}
				}
				else if(flag==0)
				   sql = "update courseapply set agree=0 ,descri='����Ա����' where id='"+id+"'";
			
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
				return "��������ʧ��";
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
				//��������
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
		public String delete(String snumber,String cnumber,String cyear){   //ɾ��
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
