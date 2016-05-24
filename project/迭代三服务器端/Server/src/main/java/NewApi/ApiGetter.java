package NewApi;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class ApiGetter {
	public static String do_get(String url) {
        String body = "{}";
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            HttpGet httpget = new HttpGet(url);
            
          //  httpget.addHeader( "X-Auth-Code", "35b251c9f26936314e2058a2d16ab3f6"); 
        
           
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
			body = EntityUtils.toString(entity);
		} catch (ParseException | IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            httpclient.getConnectionManager().shutdown();
        }
        return body;
    }
}
