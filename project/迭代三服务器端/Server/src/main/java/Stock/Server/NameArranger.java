package Stock.Server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NameArranger {
	public static List<String> getStockNameList() {
		List<String> result = new ArrayList<String>();
		FileReader fr = null;
		try {
			fr = new FileReader("names.txt");
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			while ((line = br.readLine()) != null) {
				String temp = line.substring(0, 8);
				if(!temp.equals("sh000300")){
					result.add(temp);
				}
			}
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
			System.out.println("file Not Find--names.txt");
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("read names.txt line failed");
		}
		
		return result;

	}

}
