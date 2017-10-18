import java.io.IOException;

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
		this.text = msg.getString("text");
		System.out.println("Answer diz updateid=" + updateId + "   chatid=" + chatId + "   msgid=" + msgId);
	}
	
	public long getUpdateId() {
		return this.updateId;
	}
	
	public void process() {
		if (text.startsWith("/test")) sendMessage("testado" + text.substring(5), true);
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
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
