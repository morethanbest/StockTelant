package DataWeb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;

public class DataBase {
	static Connection conn=null;
	
	public static ResultSet querySql(String sql){
		
		ResultSet result=null;
		
		Statement stmt = null;
		String url = "jdbc:mysql://"+IpConfig.ip+":3306/new_schema?"
                + "user=root&password=130014&useUnicode=true&characterEncoding=UTF8"
        		+"&useSSL=false";
 
        try {
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
            System.out.println("成功加载MySQL驱动程序");
            // 一个Connection代表一个数据库连接
            conn = DriverManager.getConnection(url);
            // Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
            stmt = conn.createStatement();
         }
         catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("MySQL操作错误");
        } 
        
        try {
			result= stmt.executeQuery(sql);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("SQL execute query failedfdfa!!!");
		}
        
        
        
        
        
        
        
        
        return result;
	}
	
	
	
	public static int insertSql(String sql){  //result等于0 为失败，result等于1 为成功
		int result=0;
		
		Statement stmt = null;
		String url = "jdbc:mysql://"+IpConfig.ip+":3306/new_schema?"
                + "user=root&password=130014&useUnicode=true&characterEncoding=UTF8"
        		+"&useSSL=false";
 
        try {
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
            System.out.println("成功加载MySQL驱动程序");
            // 一个Connection代表一个数据库连接
            conn = DriverManager.getConnection(url);
            // Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
            stmt = conn.createStatement();
         }
         catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("MySQL操作错误");
        } 
		
        
        
        
        try {
        	
			result=stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("MySQL 插入 操作错误");
			e.printStackTrace();
		}catch(Exception ee){
			System.out.println("MySQL 插入 操作错误");
			ee.printStackTrace();
		}
        
        
        
        
        try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("while insert  数据库关闭失败");
			e.printStackTrace();
		}catch(Exception ee){
			System.out.println("while insert  数据库关闭失败");
			ee.printStackTrace();
		}
        
        
        if(result!=-1){
        	result =1;
        }else{
        	result=0;
        }
        
        
        return result;
        
		
	}
	
	
	
	
	
	
	
	
	
	public static void close(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Connection Close Failed!!!");
		}
	}

}
