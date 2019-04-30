package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBcon {
	public static  Connection getConn(){
		  Connection conn=null;
		  try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			 String url="jdbc:sqlserver://localhost:1433;DatabaseName=bishe";
			 String username="sa";
			 String password="123123";
			 conn=DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} return conn;
	   }
	   /**
	    * πÿ±’¡¨Ω”
	    */
	   public static void closeAll(Connection con,PreparedStatement pst,ResultSet rs){
		   if(rs!=null){
			   try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
		   if(pst!=null){
			   try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
		   if(con!=null){
			   try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
	   }
	}