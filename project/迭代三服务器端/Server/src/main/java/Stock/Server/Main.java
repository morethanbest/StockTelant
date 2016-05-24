package Stock.Server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

public class Main {
	static Connection conn;
	static Statement stmt;
	public void init() throws SQLException{
        conn = null;
       
        // MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
        // 避免中文乱码要指定useUnicode和characterEncoding
        // 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
        // 下面语句之前就要先创建javademo数据库
        String url = "jdbc:mysql://localhost:3306/new_schema?"
                + "user=root&password=130014&useUnicode=true&characterEncoding=UTF8"
        		+"&useSSL=false";
 
        try {
            // 之所以要使用下面这条语句，是因为要使用MySQL的驱动，所以我们要把它驱动起来，
            // 可以通过Class.forName把它加载进去，也可以通过初始化来驱动起来，下面三种形式都可以
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
            // or:
            // com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
            // or：
            // new com.mysql.jdbc.Driver();
 
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
		
	}
	
	
	
	
	public static void main(String[] args) throws SQLException, ClientProtocolException, IOException, ParseException {
	
			Main main=new Main();
		main.init();

		
		
		update();
		
		
		

		conn.close();
	}
	
	
	
	public static void update() throws SQLException{
		Main main=new Main();
		main.init();

		TableUpdate up=new TableUpdate();
		up.updateTable(stmt);
		
		
		
		
		BenchmarkUpdate bench=new BenchmarkUpdate();
		bench.UpdateBenchmark(stmt);
		conn.close();
	}
	
	
}
