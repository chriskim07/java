import java.io.FileReader;
import java.io.Reader;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ReadFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Reader reader = new FileReader("./file/input.json");
			JsonObject jsonObject = (JsonObject) JsonParser.parseReader(reader);

			String id = jsonObject.get("id").getAsString();
			System.out.println("id : " + id);
			JsonArray arr = jsonObject.get("array").getAsJsonArray();
			for (int i = 0; i < arr.size(); i++) {
				int val = arr.get(i).getAsInt();
				System.out.println(i + " val : " + val);
			}

			JsonObject dataObj = jsonObject.getAsJsonObject("data");
			String data1 = dataObj.get("data1").getAsString();
			System.out.println("data1 : " + data1);
			String data2 = dataObj.get("data2").getAsString();
			System.out.println("data2 : " + data2);
			JsonArray dataArr = dataObj.get("data3").getAsJsonArray();
			for (int i = 0; i < dataArr.size(); i++) {
				String data3Val = dataArr.get(i).getAsString();
				System.out.println(i + " data3Val : " + data3Val);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
