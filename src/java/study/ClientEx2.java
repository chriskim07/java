import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpMethod;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ClientEx2 {

	public static void main(String[] args) throws Exception {
		HttpClient client = new HttpClient();
		client.start();
		ContentResponse res = client.newRequest("http://127.0.0.1:8080/xxx").method(HttpMethod.GET).send();

		JsonObject jsonObject = JsonParser.parseString(res.getContentAsString()).getAsJsonObject();

		int number = Integer.valueOf(jsonObject.get("number").getAsString());
		String str = jsonObject.get("str").getAsString();

		for (int i = 0; i < number; i++) {
			String item = jsonObject.get("items").getAsJsonArray().get(i).getAsString();

			ThreadClass tc = new ThreadClass(i, str, item);
			tc.start();

      /*
			new Thread(() -> {
				try {
					while (true) {
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
			}).start();
			 */
      
		}

	}

}
