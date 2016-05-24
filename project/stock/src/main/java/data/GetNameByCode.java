package data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GetNameByCode {
	public String getname(String code){
				System.out.println("code is"+code);
				FileInputStream file=null;
				InputStreamReader isr=null;
				String result="";
				try {
					String path = this.getClass().getClassLoader().getResource("/").getPath();
		            path = path.substring(1, path.indexOf("classes"));
					file=new FileInputStream(path+"names.txt");
					isr = new InputStreamReader(file, "UTF-8");  
				} catch (Exception e) {	
					// TODO Auto-generated catch block
					System.out.println("file read failed!!!");
					e.printStackTrace();
				}
	 BufferedReader bf = new BufferedReader(isr);  
	 		List<String> names=new ArrayList<String>();
	 		String str="";
	 		try {
	 			while((str=bf.readLine())!=null){
	 				names.add(str);
	 			}
	 } catch (Exception e) {
		 System.out.println("read names.txt file failed!!!");
	 }
		for(int i=0;i<=names.size()-1;i++){
			String temp=names.get(i);
			if(temp.contains(code)){
				result=temp;
				break;
			}
}
System.out.println("result"+result);
		result=result.substring(9,result.length());
		return result;
	}
}
		
