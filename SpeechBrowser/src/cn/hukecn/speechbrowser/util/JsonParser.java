package cn.hukecn.speechbrowser.util;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Jsonç»æè§£æç±?
 */
public class JsonParser {

	public static List<String> parseIatResult(String json) {
		//StringBuffer ret = new StringBuffer();
		List<String> list = new ArrayList<String>();
		try {
			JSONTokener tokener = new JSONTokener(json);
			JSONObject joResult = new JSONObject(tokener);

			JSONArray words = joResult.getJSONArray("ws");
			for (int i = 0; i < words.length(); i++) {
				JSONArray items = words.getJSONObject(i).getJSONArray("cw");
				JSONObject obj = items.getJSONObject(0);
				list.add(obj.getString("w"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}
	
	public static String parseGrammarResult(String json) {
		StringBuffer ret = new StringBuffer();
		try {
			JSONTokener tokener = new JSONTokener(json);
			JSONObject joResult = new JSONObject(tokener);

			JSONArray words = joResult.getJSONArray("ws");
			for (int i = 0; i < words.length(); i++) {
				JSONArray items = words.getJSONObject(i).getJSONArray("cw");
				for(int j = 0; j < items.length(); j++)
				{
					JSONObject obj = items.getJSONObject(j);
					if(obj.getString("w").contains("nomatch"))
					{
						ret.append("æ²¡æå¹éç»æ.");
						return ret.toString();
					}
					ret.append("ãç»æã??" + obj.getString("w"));
					ret.append("ãç½®ä¿¡åº¦ã?" + obj.getInt("sc"));
					ret.append("\n");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ret.append("æ²¡æå¹éç»æ.");
		} 
		return ret.toString();
	}
	
	public static String parseLocalGrammarResult(String json) {
		StringBuffer ret = new StringBuffer();
		try {
			JSONTokener tokener = new JSONTokener(json);
			JSONObject joResult = new JSONObject(tokener);

			JSONArray words = joResult.getJSONArray("ws");
			for (int i = 0; i < words.length(); i++) {
				JSONArray items = words.getJSONObject(i).getJSONArray("cw");
				for(int j = 0; j < items.length(); j++)
				{
					JSONObject obj = items.getJSONObject(j);
					if(obj.getString("w").contains("nomatch"))
					{
						ret.append("æ²¡æå¹éç»æ.");
						return ret.toString();
					}
					ret.append("ãç»æã??" + obj.getString("w"));
					ret.append("\n");
				}
			}
			ret.append("ãç½®ä¿¡åº¦ã?" + joResult.optInt("sc"));

		} catch (Exception e) {
			e.printStackTrace();
			ret.append("æ²¡æå¹éç»æ.");
		} 
		return ret.toString();
	}
}
