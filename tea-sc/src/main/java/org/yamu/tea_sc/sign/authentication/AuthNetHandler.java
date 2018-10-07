package org.yamu.tea_sc.sign.authentication;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.yamu.tea_core.net.RestClient;
import org.yamu.tea_core.net.callback.IFailure;
import org.yamu.tea_core.net.callback.ISuccess;
import org.yamu.tea_sc.net.NetKey;

import java.util.Arrays;

/**
 * Created by mjt89 on 2018/10/3 0003
 */

public class AuthNetHandler {

    public static final void onRequestSchools(Context context, final IAuthNetCallback callback, final AuthItemTag tag) {
        Log.d("请求地址： ", NetKey.REQUEST_SCHOOLS);
        RestClient.builder()
                .url(NetKey.REQUEST_SCHOOLS)
                .loader(context)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject jsonObject = JSON.parseObject(response);
                        JSONArray datas = jsonObject.getJSONArray("data");
                        String[] schools = datas.toArray(new String[datas.size()]);
                        callback.onSuccess(Arrays.asList(schools), tag);
                    }
                }).failure(new IFailure() {
            @Override
            public void onFailure() {
                Log.d("EEEEEEEEEEEEE", "faile");
            }
        })
                .build()
                .get();
    }


    public static final void onRequestCollege(Context context, final IAuthNetCallback callback, final AuthItemTag tag, String cheakSchool) {
        Log.d("请求地址： ", NetKey.REQUEST_COLLEGE + "?schoolName=" + cheakSchool);
        RestClient.builder()
                .url(NetKey.REQUEST_COLLEGE)
                .loader(context)
                .params("schoolName", cheakSchool)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject jsonObject = JSON.parseObject(response);
                        JSONArray datas = jsonObject.getJSONArray("data");
                        Log.d("VVVVVVVVVV", datas.toString());
                        String[] schools = datas.toArray(new String[datas.size()]);
                        callback.onSuccess(Arrays.asList(schools), tag);
                    }
                }).failure(new IFailure() {
            @Override
            public void onFailure() {
                Log.d("EEEEEEEEEEEEE", "faile");
            }
        })
                .build()
                .get();
    }


}
