package com.linebot.beta.service;

import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONObject;

import com.linebot.beta.global.GLOBAL_STRING;
import com.util.DatesUtil;

public class MessageManager {
	JSONObject result = new JSONObject();
	public MessageManager(){
//		DEMO
		result.put("Join", "Welcome");
		DatesUtil.DateTimeFormat.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		String timeResponse = "目前時區設定為 GMT+8:00\n目前時間為[%s]";
		result.put("Time", String.format(timeResponse, DatesUtil.DateTimeFormat.format(new Date())));
		result.put("author", GLOBAL_STRING.replyAuthor);
//		
	}
	private Boolean IsEffectiveText(String str) {
		return result.has(str);
	}

	public JSONArray getEffectiveAnswer(String str) {
		JSONArray result = new JSONArray();
		if (IsEffectiveText(str)) {
			//
			// DB
			//
			// ?
			
			// ?
//			Iterator<String> keys = this.result.keys();
//			while(keys.hasNext()){
//				String key = keys.next();
			result.put(new JSONObject().put("type", "text").put("text", this.result.getString(str)));
//			}
			return result;
		} else {
			return result.put(new JSONObject().put("type", "text").put("text", GLOBAL_STRING.replyUnknown));
		}

	}
}
