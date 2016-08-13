package tw.bir.agrimessenger.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import tw.bir.agrimessenger.service.CallbackInterface.ActivateInterface;
import tw.bir.agrimessenger.service.CallbackInterface.MessageListCallback;

public class APIManager {
    private String TAG = "APIManager";
    private static APIManager manager = null;
    public static  APIManager sharedManager(Context context){
        if(manager == null){
            manager = new APIManager(context);
        }
        return manager;
    }
    private String apiUrl = "http://agrimessenger.bir-taipei.co/api/";
    private RequestQueue mQueue;
    private String userId;
    private APIManager(Context context){
        mQueue = Volley.newRequestQueue(context);
        SharedPreferences preferences = context.getSharedPreferences("BIR",Context.MODE_PRIVATE);
        userId = preferences.getString(ServiceConfig.KEY_PREF_ACTIVATED_ID,"");
    }

    public void activate(String activateCode, String deviceToken, final ActivateInterface callback){
        JsonObject body = new JsonObject();
        body.addProperty("activation_code",activateCode);
        body.addProperty("token",deviceToken);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, apiUrl + "activate", body.toString(),new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String id = response.getString("id");
                    userId = id;
                    callback.notifyActivateResult(id,null);
                }catch (JSONException je) {
                    callback.notifyActivateResult(null,"資料錯誤");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.notifyActivateResult(null,"註冊失敗");
            }
        });
        mQueue.add(request);
    }
    public void queryMessages(final MessageListCallback callback){
        Log.e(TAG,"qurey url = " + apiUrl + "messages?fid="+userId);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, apiUrl + "messages?fid="+userId,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    ArrayList<AgricultureMessage> list = new ArrayList<>();
                    JSONArray msgJSONArray =  response.getJSONArray("results");
                    for(int i = 0;i<msgJSONArray.length();i++){
                        JSONObject msgJSON = msgJSONArray.getJSONObject(i);
                        Log.e(TAG,msgJSON.toString());
                        AgricultureMessage msg = new AgricultureMessage(msgJSON);
                        list.add(msg);
                    }
                    if (list.size() > 0 ){
                        AgricultureMessage[] messages = new AgricultureMessage[list.size()];
                        list.toArray(messages);
                        callback.notifyList(messages,null);
                        return;
                    }
                }catch (JSONException e){

                }
                callback.notifyList(null,"沒有資料");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.notifyList(null,"資料有誤");
            }
        });
        mQueue.add(request);
    }

}
