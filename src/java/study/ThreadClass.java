import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpMethod;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ThreadClass extends Thread {
	private int number;
	private String str;
	private String item;

	ThreadClass(int number, String str, String item) {
		this.number = number;
		this.str = str;
		this.item = item;
	}

	public void run() {
		try {
			while (true) {
				System.out.println("number : " + number);

				HttpClient iClient = new HttpClient();
				iClient.start();
				ContentResponse iRes = iClient.newRequest(str).method(HttpMethod.GET).send();

				JsonObject jObj = JsonParser.parseString(iRes.getContentAsString()).getAsJsonObject();
				String status = jObj.get("status").getAsString();

				JsonObject obj = new JsonObject();
				obj.addProperty("result", "OK");

				if (status != null) {
					ContentResponse oRes = iClient.newRequest(item).method(HttpMethod.POST)
							.header(HttpHeader.CONTENT_TYPE, "application/json")
							.content(new StringContentProvider(obj.toString())).send();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
