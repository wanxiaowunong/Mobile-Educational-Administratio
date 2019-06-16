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
	//专业管理员向teacher表中插入数据
	
	public  boolean update(String time, String condi) {
		PreparedStatement pst=null;
		int n=0;    
			
			ResultSet rs=null;   //先验证是否存在当前学期信息，没有的话插入，有的话更新
			String sql="select * from selectcondition where time='"+time+"'";
			System.out.println("========="+sql);
			try {
				pst=connect.prepareStatement(sql);
				rs=pst.executeQuery();
				//处理结果集
				if(rs.next()){   //说明已存在当前学期数据信息，则进行更新操作
					sql="update selectcondition set condition='"+condi+"' where time='"+time+"'";
					pst=connect.prepareStatement(sql);
					n=pst.executeUpdate();	
					
					
				}else {    //否则进行插入
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
