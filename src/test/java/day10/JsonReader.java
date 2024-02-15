package day10;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonReader {

	public static void main(String[] args) throws Exception {
		String sFile = "./data/Simple.json";
		FileReader oRead = new FileReader(sFile);
		JSONParser parse = new JSONParser();
		Object obj = parse.parse(oRead);
		JSONObject jObj = (JSONObject)obj;
		Long id = (Long)jObj.get("id");
		System.out.println("ID is : "+id);
		String name = (String)jObj.get("name");
		System.out.println("Name is : "+name);
		JSONArray arr = (JSONArray)jObj.get("menuitem");
		System.out.println("Menu Item Size is : "+arr.size());
		System.out.println("*********************");
		for (int i = 0; i < arr.size(); i++) {
			JSONObject jsonObj = (JSONObject)arr.get(i);
			String action = (String)jsonObj.get("onclick");
			System.out.println(action);
		}
		oRead.close();
	}

}
