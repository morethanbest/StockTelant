package Stock.Server;

import java.sql.SQLException;
import java.sql.Statement;

public class AddUserTable {
	
	public void addTable(Statement stmt){
		String sql="create table User(id varchar(20) , password varchar (20) ,primary key(id))";
		
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		sql="create table UserChosenStock(id varchar(20), stock varchar(20), primary key(id,stock))";
		
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
