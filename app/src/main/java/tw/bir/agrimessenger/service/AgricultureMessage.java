package tw.bir.agrimessenger.service;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class AgricultureMessage implements Serializable{
    private JSONObject rawJSON;

    private String id;
    private String date;
    private String sender;
    private String content;
    private String country;
    private String category;
    private String subject;

    public AgricultureMessage(JSONObject json){
        rawJSON = json;
        try {
            id = json.getString("_id");
            date = json.optString("date");
            sender = json.optString("sender");
            content = json.optString("content");
//            country = json.optString("county");
//            category = json.optString("category");
            country = json.optJSONObject("county") == null? "" : json.optJSONObject("county").optString("display_name");
            category = json.optJSONObject("category") == null? "" : json.optJSONObject("category").optString("display_name");
            subject = json.optString("subject");
        }catch (JSONException e){}
    }

    public String getId(){
        return id;
    }
    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public String getCountry() {
        return country;
    }
    public String getDate() {
        return date;
    }
    public String getCategory() {
        return category;
    }
    public String getSubject() {
        return subject;
    }

}
