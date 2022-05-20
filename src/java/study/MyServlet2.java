package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MyServlet2 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Map<String, QueueInfo> map = new HashMap<String, QueueInfo>();

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// getPathInfo
		String pathInfo = req.getPathInfo();
		String type = pathInfo.split("/")[1];
		String queueName = pathInfo.split("/")[2];

		// get json format
		BufferedReader reader = req.getReader();
		StringBuffer buffer = new StringBuffer();
		String line = null;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
//		System.out.println("doGet buffer string : " + pathInfo + " : " + buffer.toString().length());

		JsonObject obj = new JsonObject();

		if (type.equals("RECEIVE")) {
			if (map.containsKey(queueName)) {
				QueueInfo info = map.get(queueName);
				if (info.q.size() > info.currentNumber) {
					String message = info.q.get(info.currentNumber).message;
					String messageId = UUID.randomUUID().toString();
					obj.addProperty("Result", "OK");
					obj.addProperty("MessageId", messageId);
					obj.addProperty("Message", message);
					info.q.get(info.currentNumber).messageId = messageId;
					info.currentNumber++;
				} else {
					obj.addProperty("Result", "No Message");
				}
			}
		}

//		res.setStatus(200);
		res.getWriter().write(obj.toString());

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		System.out.println("doPost req : " + req.getPathInfo());
//		System.out.println("doPost res : " + res);

		// getPathInfo
		String pathInfo = req.getPathInfo();
		String type = pathInfo.split("/")[1];
		String queueName = pathInfo.split("/")[2];

		// get json format
		BufferedReader reader = req.getReader();
		StringBuffer buffer = new StringBuffer();
		String line = null;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
//		System.out.println("doPost buffer string : " + pathInfo + " : " + (buffer.toString().length() > 0 ? buffer.toString() : "enmpty"));

		JsonObject obj = new JsonObject();

		if (type.equals("CREATE")) {
			if (map.containsKey(queueName)) {
//				System.out.println("Queue Exist");
				obj.addProperty("Result", "Queue Exist");
			} else {
				List<DetailInfo> q = new LinkedList<>();
				JsonElement element = JsonParser.parseString(buffer.toString());
				String size = element.getAsJsonObject().get("QueueSize").getAsString();
				map.put(queueName, new QueueInfo(q, Integer.parseInt(size), 0));
				obj.addProperty("Result", "OK");
			}
		} else if (type.equals("SEND")) {
			if (map.containsKey(queueName)) {
				QueueInfo info = map.get(queueName);
				if (info.q.size() == info.maxSize) {
					System.out.println("Queue Full : " + info.maxSize + " : " + info.q.size());
					obj.addProperty("Result", "Queue Full");
				} else {
					JsonElement element = JsonParser.parseString(buffer.toString());
					String message = element.getAsJsonObject().get("Message").getAsString();
					info.q.add(new DetailInfo(message, ""));
					obj.addProperty("Result", "OK");
				}
			}

		} else if (type.equals("ACK")) {
			if (map.containsKey(queueName)) {
				QueueInfo info = map.get(queueName);
				List<DetailInfo> qList = info.q;
				for (int i = 0; i < qList.size(); i++) {
					DetailInfo dInfo = qList.get(i);
					if(dInfo.messageId.equals(pathInfo.split("/")[3])) {
						qList.remove(i);
						obj.addProperty("Result", "OK");
						break;
					}
				}
			}
		} else if (type.equals("FAIL")) {
			if (map.containsKey(queueName)) {
				QueueInfo info = map.get(queueName);
				List<DetailInfo> qList = info.q;
				for (int i = 0; i < qList.size(); i++) {
					DetailInfo dInfo = qList.get(i);
					if(dInfo.messageId.equals(pathInfo.split("/")[3])) {
						info.currentNumber = 0;
						obj.addProperty("Result", "OK");
					}
				}
			}
		}

//		res.setStatus(200);
		res.getWriter().write(obj.toString());
	}
}
