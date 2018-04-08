package com.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonsUtil {
	@SuppressWarnings("unchecked")
	public static <T> List<T> JSONArraytoList(JSONArray ja) throws JSONException{
		List<T> result = null;
		if(ja != null){
			if(ja != null){
				result = new ArrayList<T>();
				for(int i = 0 ; i < ja.length() ; i ++){
					result.add((T)ja.get(i));
				}
			}
		}
		return result;
	}
	public static JSONObject getJsonObject(HttpServletRequest req) throws Exception{
		String result;
		BufferedReader br = new BufferedReader(new InputStreamReader(
				(ServletInputStream) req.getInputStream(), "UTF-8"));
		StringBuffer sb = new StringBuffer("");
		String temp;
		while ((temp = br.readLine()) != null) {
			sb.append(temp);
		}
		br.close();
		result = sb.toString();
		return new JSONObject(result);
	}
	
}
