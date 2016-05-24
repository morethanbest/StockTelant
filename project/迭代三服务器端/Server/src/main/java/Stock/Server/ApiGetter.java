package Stock.Server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class ApiGetter {
	public static String do_get(String url) throws ClientProtocolException, IOException {
        String body = "{}";
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            HttpGet httpget = new HttpGet(url);
            
            httpget.addHeader( "X-Auth-Code", "35b251c9f26936314e2058a2d16ab3f6"); 
        HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            body = EntityUtils.toString(entity);
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return body;
    }
	
	
	public static String getname(String code){
		FileReader file=null;
		String result="";
		try {
			file=new FileReader("names.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("file read failed!!!");
			e.printStackTrace();
		}
		
		BufferedReader bf=new BufferedReader(file);
		
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
		
		result=result.substring(9,result.length());
		
		return result;
		
		
		
		
		
	}
	
	
	
	
	
	

}
