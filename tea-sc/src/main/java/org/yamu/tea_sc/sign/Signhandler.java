package org.yamu.tea_sc.sign;


import android.database.Cursor;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.yamu.tea_sc.database.DatabaseManager;
import org.yamu.tea_sc.database.UserProfile;

import java.util.List;

import okhttp3.internal.http1.Http1Codec;

/**
 * Created by mjt89 on 2018/10/2 0002
 */
public class Signhandler {
    public static String onSignIn(String response) {
        String msg = "";
        final JSONObject jsonObject = JSON.parseObject(response);
        final int code = jsonObject.getInteger("status");
        if (code != 200) {
            msg = jsonObject.getString("msg");
            return msg;
        }
        final JSONObject profileJson = jsonObject.getJSONObject("data");
        if (profileJson != null && !profileJson.isEmpty()) {
            UserProfile use = profileJson.toJavaObject(UserProfile.class);
            Log.d("登录成功数据", "onSignIn: " + use.toString());
            DatabaseManager.getInstance().getUserProfileDao().insertOrReplace(use);
        }
        return "登录成功";
    }
}
