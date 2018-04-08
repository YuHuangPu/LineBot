package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class TestApi
 */
public class TestApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestApi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String result;
		BufferedReader br = new BufferedReader(new InputStreamReader(
				(ServletInputStream) request.getInputStream(), "UTF-8"));
		StringBuffer sb = new StringBuffer("");
		String temp;
		while ((temp = br.readLine()) != null) {
			sb.append(temp);
		}
		br.close();
		result = sb.toString();
		JSONObject json = new JSONObject(result);
		System.out.println(json);
		JSONObject results = null;
		try{
			results = new JSONObject();
			String authorization = "xzP2Zg5iCPfG0nWHvE8MPeFhXLwHbllHLeurBecEpmVbbFEA7+ICL/CyvMKFn6BMJbrowEAv3InEKTn4SOxqQZLecnGCgjZc3tYm3f08DJx9jpGCh2TeLJ79AujH3pjh411irsPhY6gxF0DmQRVZ+gdB04t89/1O/w1cDnyilFU=";
			response.addHeader("Authorization", authorization);
			response.setContentType("application/json");
			
			results.put("to", "U17b29ee60e7bf35b1e21c129cd9053ed").put("messages"
						, new JSONArray().put(new JSONObject().put("type", "text").put("text", "Hellow World !")));
		}catch(Exception e){
			System.out.println("JSON ERROR");
		}
		System.out.println(results.toString());
		response.getWriter().append(results.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
