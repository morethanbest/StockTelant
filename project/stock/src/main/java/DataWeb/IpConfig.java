package DataWeb;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class IpConfig {
	public static final String ip="172.25.169.102";
	public void getIp(){
		FileReader file = null;
		try {
			file = new FileReader("ip.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader br=new BufferedReader(file);
		String line=null;
		try {
			line=br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ip=line;
		
		
		
	}

}
