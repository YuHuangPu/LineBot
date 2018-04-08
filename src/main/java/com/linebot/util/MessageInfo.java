package com.linebot.util;

import org.json.JSONObject;

public class MessageInfo {
	private String replyToken ;
	private String type ;
	private Long timestamp ;
	private Source Source;
	private Message Message;
	
	public String getReplyToken() {
		return replyToken;
	}
	public void setReplyToken(String replyToken) {
		this.replyToken = replyToken;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public Source getSource() {
		return Source;
	}
	public void setSource(Source source) {
		Source = source;
	}
	public Message getMessage() {
		return Message;
	}
	public void setMessage(Message message) {
		Message = message;
	}



	public class Source{
		private String type;
		private String userId;
		public Source(){
		}
		public Source(JSONObject source){
			this.setType(source.getString("type"));
			this.setUserId(source.getString("userId"));
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		
	}
	public class Message{
		private String id ;
		private String type;
		private String text ;
		public Message(){
		}
		public Message(JSONObject message){
			this.setId(message.getString("id"));
			this.setText(message.getString("text"));
			this.setType(message.getString("type"));
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		
	}
	
	public MessageInfo(){
		
	}
	public MessageInfo(JSONObject json){
		JSONObject event = json.getJSONArray("events").getJSONObject(0);
		this.setReplyToken(event.getString("replyToken"));
		this.setTimestamp(event.getLong("timestamp"));
		this.setType(event.getString("type"));
		JSONObject source = event.getJSONObject("source");
		this.setSource(new Source(source));
		JSONObject message = event.getJSONObject("message");
		this.setMessage(new Message(message));
	}
}
