package data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GetNameByCode {
	public static String getname(String code){
		System.out.println("code is"+code);
		FileInputStream file=null;
		InputStreamReader isr=null;
		String result="";
		try {
			file=new FileInputStream("names.txt");
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("read names.txt file failed!!!");
			e.printStackTrace();
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
