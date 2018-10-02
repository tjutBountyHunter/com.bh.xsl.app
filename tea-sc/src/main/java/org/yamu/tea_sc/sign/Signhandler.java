package org.yamu.tea_sc.sign;


import android.database.Cursor;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.yamu.tea_sc.database.DatabaseManager;
import org.yamu.tea_sc.database.UserProfile;

import java.util.List;

/**
 * Created by mjt89 on 2018/10/2 0002
 */
public class Signhandler {
    public static void onSignIn(String response) {
//        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");

        final UserProfile profile = new UserProfile(1L, "马杰涛", "男", "16600295147",
                "天津理工大学", "3", "2");
        DatabaseManager.getInstance().getUserProfileDao().insertOrReplace(profile);
        List<UserProfile> list = DatabaseManager.getInstance().getUserProfileDao().loadAll();
        for (UserProfile s : list) {
            Log.d("a", s.toString());
        }
//        DatabaseManager.getInstance().getmDao().readEntity( {
    }
}
