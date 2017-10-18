import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.*;

public class TelegramBot {
	
	private static String token = "";
	
	public static long lastUpdate = 495041178;
	
	public TelegramBot() {}
	
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
	    while ((cp = rd.read()) != -1) {
	    	sb.append((char) cp);
	    }
	    return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	    	BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	    	String jsonText = readAll(rd);
	    	JSONObject json = new JSONObject(jsonText);
	    	return json;
	    } finally {
	    	is.close();
	    }
	}
	
	public static String createBotUrl(String method) {
		String s = "https://api.telegram.org/bot";
		s += token + "/" + method;
		return s;
	}
	
	public JSONObject getUpdates() {
		JSONObject json = null;
		try {
			json = readJsonFromUrl(createBotUrl("getUpdates")+"?offset=" + lastUpdate + "&limit=5");
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	public void printUpdates(JSONObject updates) {
		JSONArray array= (JSONArray) updates.get("result");
		System.out.println(array.length());
		for (int i = 0; i < array.length(); ++i) {
			System.out.println((JSONObject) array.get(i));
			Answer ans = new Answer((JSONObject) array.get(i));
			ans.process();
		}
	}
		
	public static void main(String[] args) {
		System.out.println("Hellow Uordi");
		TelegramBot tb = new TelegramBot();
		while(true) {
			JSONObject json = tb.getUpdates();
			tb.printUpdates(json);
		}
	}
}
