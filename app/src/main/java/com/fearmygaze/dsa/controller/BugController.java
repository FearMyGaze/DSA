package com.fearmygaze.dsa.controller;

import android.content.Context;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.fearmygaze.dsa.Interface.IVolleyMessage;
import com.fearmygaze.dsa.R;
import com.fearmygaze.dsa.model.RequestSingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BugController {

    /**
     * @param context We need it to get the String from resource file strings.xml
     * @param userEmail We need the userID so the db knows in what user the file will be assigned
     * @param BugDesc We need description so the programmer knows the error tha you encountered
     * @param iVolleyMessage a quick interface to handle the Success/Warning/Error
     */
    public static void BugReport(Context context, String userEmail, String BugDesc, IVolleyMessage iVolleyMessage){
        String[] url = context.getResources().getStringArray(R.array.url);
        String jsonError = context.getResources().getString(R.string.jsonErrorDuring);
        String onErrorFilingBugReport = context.getResources().getString(R.string.onErrorFilingBugReport);
        String volleyError = context.getResources().getString(R.string.volleyError);

        StringRequest request = new StringRequest(Request.Method.POST, url[9],
                response -> {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        if (jsonResponse.getString("success").equals("1")) {
                            iVolleyMessage.onSuccess(context.getResources().getString(R.string.successFilingBugReport));
                        } else {
                            iVolleyMessage.onWaring(onErrorFilingBugReport);
                        }
                    } catch (JSONException e) {
                        iVolleyMessage.onError(jsonError + " " + e.getMessage());
                    }
                },
                error -> iVolleyMessage.onError(volleyError + error.getMessage())) {
            @NonNull
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parameters = new HashMap<>();
                parameters.put("bugEmail", userEmail);
                parameters.put("bugDesc", BugDesc);
                return parameters;
            }
        };
        RequestSingleton.getInstance(context).addToRequestQueue(request);


    }
}
