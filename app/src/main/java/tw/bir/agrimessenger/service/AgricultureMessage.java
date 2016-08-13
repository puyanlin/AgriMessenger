package tw.bir.agrimessenger.service;

import org.json.JSONObject;

/**
 * Created by puyan on 8/13/16.
 */
public class AgricultureMessage {
    private JSONObject rawJSON;
    public AgricultureMessage(JSONObject json){
        rawJSON = json;
    }
}
