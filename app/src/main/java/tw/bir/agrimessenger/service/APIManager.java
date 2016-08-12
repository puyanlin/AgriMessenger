package tw.bir.agrimessenger.service;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import tw.bir.agrimessenger.service.CallbackInterface.ActivateInterface;

/**
 * Created by puyan on 8/13/16.
 */
public class APIManager {
    private static APIManager manager = null;
    public static  APIManager sharedManager(Context context){
        if(manager == null){
            manager = new APIManager(context);
        }
        return manager;
    }
    private String apiUrl = "http://agrimessenger.bir-taipei.co/api/";
    private RequestQueue mQueue;
    private APIManager(Context context){
        mQueue = Volley.newRequestQueue(context);
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

}
