package Stock.Server;
			
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.List;
			
public class TableUpdate {
	public void updateTable(Statement stmt){
		NameArranger namegetter=new NameArranger();
		List<String> names=namegetter.getStockNameList();
		for(int i=0;i<=names.size()-1;i++){
			String temp=names.get(i);
			StockTable table=new StockTable(temp);
			
			try {
				table.addLineItem(stmt);
			} catch (IOException | SQLException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(temp+"update stock info failed!!!");
				continue;
			}catch(Exception e){
				e.printStackTrace();
				System.out.println(temp+"update serious problem maybe the network");
				continue;
			}
			
			System.out.println(temp+"update success");
			
			
		}
		
	}
}
