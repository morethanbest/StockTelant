package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpMessage;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.api.client.json.Json;

public class ApiEntity {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		
		String url="http://121.41.106.89:8010/api/benchmark/hs300";
		
			String res=do_get(url);
			/*JSONObject array=JSONObject.fromObject(res);
			JSONObject json= new JSONObject().fromObject(array.fromObject("data"));
			System.out.println(json.getString("link"));
			System.out.println(json.getString("name"));*/
			System.out.println(res);
			
			
		
	}
	
	

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
	
//X-Auth-Code: 35b251c9f26936314e2058a2d16ab3f6
}
