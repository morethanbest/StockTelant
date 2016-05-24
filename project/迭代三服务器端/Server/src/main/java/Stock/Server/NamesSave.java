package Stock.Server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

public class NamesSave {
	
	
	
	public void addTable(Statement stmt){
		String drop="drop table Names";
		try {
			stmt.executeUpdate(drop);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql="create table Names"+"(code varchar(10),name varchar(10) ,primary key(code))";
		int result=0;
		try {
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result != -1){
			System.out.println("创建数据表成功");
		}else{
			System.out.println("创建数据表失败");
		}
		
	}
	public void saveNames(Statement stmt ){
		FileReader fd=null;
		try {
			fd=new FileReader("names.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("file not exist!!!");
		}
		BufferedReader bf=new BufferedReader(fd);
		String line=null;
		try {
			while((line=bf.readLine()) != null){
				String code = line.substring(0, 8);
				String name = line.substring(9,line.length());
				System.out.println(code);
				System.out.println(name);
				
				String sql = "insert into Names(code,name) "
						+ "values('"+ code + "' , '" +name +"')";
				
				int record=0;
				try {
					record = stmt.executeUpdate(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(record ==-1 ){
					
					continue;
				}
				
				
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("read txt file line failed!!!");
		}finally{
			System.out.println(" sk work done!!!");
		}
	}
	
	
	
	

}
