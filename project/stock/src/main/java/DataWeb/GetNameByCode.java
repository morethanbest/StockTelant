package DataWeb;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GetNameByCode {
	public static String getname(String code){
		String result=null;
		Connection conn=null;
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
        
        String sql="select name from names where code='"+code+"'";
        ResultSet set=null;
        try {
			set=stmt.executeQuery(sql);
			if(set.next()){
				result=set.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	
	
	public static List<String> getAllCodes(){
		List<String> result=new ArrayList<String>();
		
		Connection conn=null;
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
        
        String sql="select code from names ";
        ResultSet set=null;
        try {
			set=stmt.executeQuery(sql);
			while(set.next()){
				result.add(set.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		return result;
	}
	
	
	public static List<String> getAllCodesSH(){
		List<String> result=new ArrayList<String>();
		List<String> res=new ArrayList<String>();
		
		Connection conn=null;
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
        
        String sql="select code from names ";
        ResultSet set=null;
        try {
			set=stmt.executeQuery(sql);
			while(set.next()){
				result.add(set.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0;i<result.size();i++){
			if(result.get(i).contains("sh")&& !result.get(i).equals("sh000300")){
				res.add(result.get(i));
			}
		}
		
		
		
		return res;
	}
	
	
	public static List<String> getAllCodesSZ(){
		List<String> result=new ArrayList<String>();
		List<String> res=new ArrayList<String>();
		
		Connection conn=null;
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
        
        String sql="select code from names ";
        ResultSet set=null;
        try {
			set=stmt.executeQuery(sql);
			while(set.next()){
				result.add(set.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0;i<result.size();i++){
			if(result.get(i).contains("sz")){
				res.add(result.get(i));
			}
		}
		
		
		
		return res;
	}
	
	
}
