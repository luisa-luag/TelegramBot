import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import org.json.*;


public class Answer {
	private JSONObject msg = null;	
	private long updateId = 0;
	private long chatId = 0;
	private long msgId = 0;
	private String text = "";

	
	public Answer(JSONObject json) {
		this.msg = (JSONObject) json.get("message");
		this.updateId = json.getLong("update_id");
		this.chatId = ((JSONObject) msg.get("chat")).getLong("id");
		this.msgId = msg.getLong("message_id");
		if (msg.has("text")) this.text = msg.getString("text");
		System.out.println("Answer diz updateid=" + updateId + "   chatid=" + chatId + "   msgid=" + msgId);
	}
	
	public long getUpdateId() {
		return this.updateId;
	}
	
	public void process() {
		if (text.startsWith("/gbariate")) sendMessage(Gbariation.gbariate(text.substring(10)), false);
		if (text.startsWith("/test")) sendMessage("testado" + text.substring(5), true);
		if (text.startsWith("/otvio")) sendMessage("Ow, vamo no mc?", false);
		if (text.startsWith("/mike")) sendMessage("Oi mike", false);
		if (text.startsWith("/pipi")) forwardMessage((long) 104548269, (long) 59);
		if (text.startsWith("/qualidade")) forwardMessage((long) 104548269, (long) 62);
		if (text.startsWith("/dislexia")) forwardMessage((long) 104548269, (long) 151);
		if (text.startsWith("/ali")) forwardMessage((long) 104548269, (long) 198);
		if (text.startsWith("/ain")) forwardMessage((long) 104548269, (long) 201);
		if (text.startsWith("/joegs")) {
			int max = 10;
			int randomNum = ThreadLocalRandom.current().nextInt(0, max + 1);
			switch (randomNum) {
				case 0:
					forwardMessage((long) 104548269, (long) 65);
					break;
				case 1:
					forwardMessage((long) 104548269, (long) 66);
					forwardMessage((long) 104548269, (long) 67);
					break;
				case 2:
					forwardMessage((long) 104548269, (long) 68);
					break;
				case 3:
					forwardMessage((long) 104548269, (long) 69);
					forwardMessage((long) 104548269, (long) 70);
					break;
				case 4:
					forwardMessage((long) 104548269, (long) 71);
					break;
				case 5:
					forwardMessage((long) 104548269, (long) 72);
					break;
				case 6:
					forwardMessage((long) 104548269, (long) 73);
					break;
				case 7:
					forwardMessage((long) 104548269, (long) 76);
					break;
				case 8:
					forwardMessage((long) 104548269, (long) 77);
					break;
				case 9:
					forwardMessage((long) 104548269, (long) 78);
					break;
				case 10:
					forwardMessage((long) 104548269, (long) 79);
					forwardMessage((long) 104548269, (long) 80);
					break;
				default:
					break;
			}
		}
		TelegramBot.lastUpdate = updateId + 1;
	}
	
	private void sendMessage(String text, boolean reply) {
		String s = "sendMessage?chat_id=" + chatId + "&text=" + text;
		if (reply) s += "&reply_to_message_id=" + msgId;
		JSONObject json = null;
		try {
			json = TelegramBot.readJsonFromUrl(TelegramBot.createBotUrl(s));
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("msg enviada:" + json);
	}
	
	private void forwardMessage(Long fromChatId, Long fromMsgId) {
		String s = "forwardMessage?chat_id=" + chatId + "&from_chat_id=" + fromChatId + "&message_id=" + fromMsgId;
		JSONObject json = null;
		try {
			json = TelegramBot.readJsonFromUrl(TelegramBot.createBotUrl(s));
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("msg enviada:" + json);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
