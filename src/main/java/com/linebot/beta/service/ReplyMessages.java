package com.linebot.beta.service;

import org.json.JSONArray;
import org.json.JSONObject;

import com.linebot.beta.global.GLOBAL_STRING;

public class ReplyMessages {
	private final String replyToken ;
	private final JSONArray replyMessages;
	public ReplyMessages(String replyT, JSONArray replyM){
		this.replyToken = replyT;
		this.replyMessages = replyM;
	}
	public void execute(){
		JSONObject sentJson = new JSONObject().put("messages", replyMessages).put("replyToken", replyToken);
		Boolean result = new sentJson().createConn(GLOBAL_STRING.API_REPLY_URL, sentJson).execute();
		System.out.println(String.format("SentJson[%s]\nStatus[%s]", sentJson.toString(), result));
	}
}
