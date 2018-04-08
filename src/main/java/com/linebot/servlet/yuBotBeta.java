package com.linebot.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.linebot.beta.service.MessageManager;
import com.linebot.beta.service.ReplyMessages;
import com.linebot.util.MessageInfo;
import com.util.JsonsUtil;

/**
 * Servlet implementation class yuBotBeta
 */
public class yuBotBeta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public yuBotBeta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject result = new JSONObject();
		result.put("message", "我是API, 你可以換換別的方式找我 :D");
		response.getWriter().append(result.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			execPost(request, response, JsonsUtil.getJsonObject(request));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void execPost(HttpServletRequest req, HttpServletResponse res, JSONObject reqJson){
		System.out.println(String.format("get json[%s]", reqJson.toString()));
		MessageInfo MI = new MessageInfo(reqJson);
		MessageManager MM = new MessageManager();
		JSONArray resultMessage = MM.getEffectiveAnswer(MI.getMessage().getText());
		ReplyMessages RM = new ReplyMessages(MI.getReplyToken(), resultMessage);
		RM.execute();
	}
}
