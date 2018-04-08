package com.linebot.beta.service;

import java.util.Date;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.linebot.beta.global.GLOBAL_STRING;
import com.util.DatesUtil;

public class MessageManager {
	JSONObject result = new JSONObject();
	public MessageManager(){
//		DEMO
		result.put("Join", "Welcome");
		result.put("Time", DatesUtil.DateTimeFormat.format(new Date()));
		result.put("author", GLOBAL_STRING.replyAuthor);
		result.put("在幹嘛", "想妳ㄚㄚㄚㄚㄚㄚㄚㄚ");
		result.put("在幹嘛?", "想妳ㄚㄚㄚㄚㄚㄚㄚㄚ");
		result.put("在幹嘛？", "想妳ㄚㄚㄚㄚㄚㄚㄚㄚ");
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
