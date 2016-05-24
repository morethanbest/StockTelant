package Stock.Server;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;



public class InitTable {
	/*public void addAllTables(Statement stmt){
		List<String> stocks=NameArranger.getStockNameList();
		for(int i=0;i<=stocks.size()-1;i++){
			String temp=stocks.get(i);
			System.out.println(temp);
			try {
				StockTable add=new StockTable(temp,stmt);
				add.addTable(stmt);
			
				add.addLineItem(stmt);
			} catch (IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(temp+"addTableOrItemFailed!!!");
			}
			
			
		}
		System.out.println("work done sk");
		
		
		
		
	}*/
	

}
