package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DB.DBcon;

public class opcondition {
	private  Connection connect;
	public opcondition() {
		DBcon conn=new DBcon();
		connect=conn.getConn();
	}
	//רҵ����Ա��teacher���в�������
	
	public  boolean update(String time, String condi) {
		PreparedStatement pst=null;
		int n=0;    
			
			ResultSet rs=null;   //����֤�Ƿ���ڵ�ǰѧ����Ϣ��û�еĻ����룬�еĻ�����
			String sql="select * from selectcondition where time='"+time+"'";
			System.out.println("========="+sql);
			try {
				pst=connect.prepareStatement(sql);
				rs=pst.executeQuery();
				//��������
				if(rs.next()){   //˵���Ѵ��ڵ�ǰѧ��������Ϣ������и��²���
					sql="update selectcondition set condition='"+condi+"' where time='"+time+"'";
					pst=connect.prepareStatement(sql);
					n=pst.executeUpdate();	
					
					
				}else {    //������в���
					sql="insert into selectcondition(time,condition)"+" values(?,?)";
					pst=connect.prepareStatement(sql);
						pst.setString(1, time);
						pst.setString(2, condi);
						
						n=pst.executeUpdate();	
						
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBcon.closeAll(connect, pst, rs);
				
			}
			if(n>0)
				 return true;
				else
					return false;
			
	}
}
