package com.linebot.beta.service;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

import com.linebot.beta.global.GLOBAL_STRING;

public class sentJson {
	private final Map<String, String> Headers ;
	private String url;
	private JSONObject json;
	public sentJson(){
		this.Headers = new HashMap<String, String>();
		this.Headers.put("Content-Type", "application/json");
		this.Headers.put("Authorization", String.format("Bearer %s", GLOBAL_STRING.API_ACCESS_TOKEN));
	}
	public sentJson(HashMap<String, String> Header){
		this.Headers = Header;
	}
	public sentJson createConn(String url, JSONObject json){
		this.url = url ;
		this.json = json ;
		return this;
	}
	public Boolean execute(){
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
		try {
			HttpPost request = new HttpPost(this.url);
			@SuppressWarnings("deprecation")
			StringEntity params =new StringEntity(json.toString(),HTTP.UTF_8);
			Iterator<String> keys = Headers.keySet().iterator();
			while(keys.hasNext()){
				String key = keys.next();
				request.addHeader(key, Headers.get(key));
			}
			request.setEntity(params);
			httpClient.execute(request,new BasicResponseHandler());
		}catch (Exception ex){
			ex.printStackTrace();
		}finally{
			httpClient = null;
		}
		return Boolean.TRUE;
	}

	public static void main (String [] args){
		JSONObject j = new JSONObject();
		j.put("messages", new JSONArray().put(new JSONObject().put("text", "\uD83D\uDEB6 LINE emoji").put("type", "text")));
//		j.put("replyToken", "7ae75a73898c4844819d8f2663ae5479");
		j.put("to", "U17b29ee60e7bf35b1e21c129cd9053ed");
//		System.out.println(j.toString());
//		Boolean result = new sentJson().createConn("https://api.line.me/v2/bot/message/reply", j).execute();
		Boolean result = new sentJson().createConn("https://api.line.me/v2/bot/message/push", j).execute();
		System.out.println(result);
	}
}
