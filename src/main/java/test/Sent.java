package test;

import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;
public class Sent {
	public static void main (String [] args){
		JSONObject j = new JSONObject();
		j.put("messages", new JSONArray().put(new JSONObject().put("text", "Hellow World !").put("type", "text")));
//		j.put("replyToken", "d5d5318392fa4106832ce23b8c353482");
		j.put("to", "U17b29ee60e7bf35b1e21c129cd9053ed");
		System.out.println(j.toString());
//		String result = new Sent().SendJson("https://api.line.me/v2/bot/message/reply", j.toString());
		String result = new Sent().SendJson("https://api.line.me/v2/bot/message/push", j.toString());
		System.out.println(result);
	}
	public  String SendJson(String URL,String V){
		final SSLConnectionSocketFactory sslsf;
		try {
		    sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault(),
		            NoopHostnameVerifier.INSTANCE);
		} catch (NoSuchAlgorithmException e) {
		    throw new RuntimeException(e);
		}

		final Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
		        .register("http", new PlainConnectionSocketFactory())
		        .register("https", sslsf)
		        .build();

		final PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
		cm.setMaxTotal(100);
		HttpClient httpClient = HttpClients.custom()
		        .setSSLSocketFactory(sslsf)
		        .setConnectionManager(cm)
		        .build();
		String s ="A";
		try {
			String value = V;
			HttpPost request = new HttpPost(URL);
			StringEntity params =new StringEntity(value,HTTP.UTF_8);
			String accessToken = "2iQHR7/LIHUr0lFr3FxcrtzsWkrqi00xDO3J04Gt1LX3JWH69q+ly61RCjf35wmI0w6XI0oa5TauvcHeDyug8c9MkAmDgCAkaBCKYRxlSL/4FSYveGZn1+FRuzrLp97NFAZW/7MGJdpAqXFhMVc3MgdB04t89/1O/w1cDnyilFU=";
			request.addHeader("Content-Type", "application/json");
			request.addHeader("Authorization",String.format("Bearer %s", accessToken) );
//		    request.addHeader("Accept", "application/json");
			request.setEntity(params);
			s = httpClient.execute(request,new BasicResponseHandler());
		}catch (Exception ex){
			ex.printStackTrace();
		}finally{
			httpClient = null;
		}
		return s ;
}
}
