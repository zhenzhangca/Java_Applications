package ca.jrvs.apps.twitter.dao.helper;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ApacheHttpHelperIntTest {
    HttpHelper httpHelper = new ApacheHttpHelper() ;

    @Test
    public void httpPostUpdate() throws Exception{
        ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        postParameters.add(new BasicNameValuePair("status", "a new tweet"));
        StringEntity entity = new UrlEncodedFormEntity(postParameters, "UTF-8");
        String uri = "https://api.twitter.com/1.1/statuses/update.json";
        HttpResponse response = httpHelper.httpPost(new URI(uri), entity);
//        System.out.println(response.getEntity());
//        StringBuffer sb = new StringBuffer();
//        BufferedReader br = new BufferedReader(new InputStreamReader(
//                response.getEntity().getContent()));
//        String in = "";
//
//        while ((in = br.readLine()) != null) {
//            sb.append(in + "\n");
//        }
//        System.out.println(sb.toString());
//
//        br.close();
        assertNotNull(response);
        assertEquals(200,response.getStatusLine().getStatusCode() );
    }

    @Test
    public void httpGetRead() throws Exception{
        String uri = "https://api.twitter.com/1.1/statuses/show.json?id=1146569038157373440";
        HttpResponse response = httpHelper.httpGet(new URI(uri));
        assertNotNull(response);
        assertEquals(200,response.getStatusLine().getStatusCode() );
    }

    @Test
    public void httpPostDelete() throws Exception{
        String uri =  "https://api.twitter.com/1.1/statuses/destroy/1146805670265729024.json";
        HttpResponse response = httpHelper.httpPost(new URI(uri));
        assertNotNull(response);
        assertEquals(200, response.getStatusLine().getStatusCode());
    }


}
