package org.huaanwater.work.http;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.huaanwater.work.entity.thirdabout.ali.AliAuthInfo;
import org.huaanwater.work.entity.thirdabout.wx.WxAuthInfo;
import org.huaanwater.work.util.StringUtil;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Administrator on 2017/11/2.
 * 类描述   实体类对象解析
 * 版本
 */

public class ParseSerilizable {


    public ParseSerilizable() {

    }


    /**
     * 解析成对象
     *
     * @param back
     * @param cls
     * @param <T>
     * @return
     */
    public <T> T getParseToObj(String back, Class<T> cls) {
        T t = null;


        try {
            t = new Gson().fromJson(back, cls);
        } catch (Exception e) {
            return t;
        }
        return t;
    }


    /**
     * 解析成集合
     *
     * @param back
     * @param cls
     * @param <T>
     * @return
     */
    public <T> List<T> getParseToList(String back, Class<T> cls) {


        JsonObject asJsonObject = null;
        JsonArray jsonArray = null;
        List<T> list = null;
        Gson gson = null;
        try {
            asJsonObject = new JsonParser().parse(back).getAsJsonObject();
            String items = "items";
            jsonArray = asJsonObject.getAsJsonArray(items);

            gson = new Gson();
            list = new ArrayList<T>();
            for (JsonElement elem : jsonArray) {
                list.add(gson.fromJson(elem, cls));
            }

        } catch (Exception e) {
            return list;
        }
        return list;
    }


    /**
     * 解析count
     * @param back
     * @return
     */
    public int getParseCount(String back) {


        int count =0;
        try {
            JSONObject jsonObject = new JSONObject(back);
            count = jsonObject.getInt("count");


        } catch (JSONException e) {
            return count;

        }

        return count;

    }


    /**
     * 解析成集合
     *
     * @param back
     * @param cls
     * @param <T>
     * @return
     */
    public <T> List<T> getParseToNoItemsList(String back, Class<T> cls) {

        JsonArray asJsonArray = null;

        Gson gson = null;
        List<T> list = null;

        try {

            asJsonArray = new JsonParser().parse(back).getAsJsonArray();
            gson = new Gson();
            list = new ArrayList<T>();

            for (JsonElement elem : asJsonArray) {
                list.add(gson.fromJson(elem, cls));
            }


        }catch (Exception e) {

            return list;

        }
        return list;

    }


    /**
     * 这个是 org的jsonobject
     *
     * @param back
     * @param key
     * @return
     */
    public String getParseString(String back, String key) {

        String json = "";
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(back);
            json = jsonObject.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }


    /**
     * 将对象转化为 json对象
     * @param o
     * @return
     */
    public JsonElement getParseObjToJson(Object o) {

        JsonElement jsonElement = null;


        try{
            jsonElement = new Gson().toJsonTree(o);
        }catch (Exception e){

            return jsonElement;
        }

        return jsonElement;
    }


    /**
     *  解析微信 用户信息（特别接口）
     * @param back
     * @return
     */

    public WxAuthInfo getSpecialParseWxAthInfo(String back) {

        String json = "";
        WxAuthInfo wxAuthInfo = null;
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(back);
            wxAuthInfo = new WxAuthInfo();
            wxAuthInfo.setNickname(jsonObject.getString("nickname"));
            wxAuthInfo.setHeadimgurl(jsonObject.getString("headimgurl"));
            wxAuthInfo.setOpenid(jsonObject.getString("openid"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return wxAuthInfo;
    }





    /**
     *  解析ali 用户信息（特别接口）
     * @param back
     * @return
     */

    public AliAuthInfo getSpecialParseAliAthInfo(String back) {

        String json = "";
        AliAuthInfo aliAuthInfo = null;
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(back);
            aliAuthInfo = new AliAuthInfo();
            aliAuthInfo.setNick_name(jsonObject.getString("nick_name"));
            aliAuthInfo.setAvatar(jsonObject.getString("avatar"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return aliAuthInfo;
    }



//    /**
//     * 解析成对象
//     * @param back
//     * @param cls
//     * @param <T>
//     * @return
//     */
//    public <T>T getParseToObj(String back,Class<T> cls) {
//        T t = new Gson().fromJson(back, cls);
//        return t;
//    }
//
//
//    /**
//     * 解析成集合
//     * @param back
//     * @param cls
//     * @param <T>
//     * @return
//     */
//    public <T>List<T> getParseToList(String back, Class<T> cls) {
//
//        JsonObject asJsonObject = new JsonParser().parse(back).getAsJsonObject();
//
//        String items = "items";
//        JsonArray jsonArray = asJsonObject.getAsJsonArray(items);
//
//        Gson gson = new Gson();
//        List<T> list = new ArrayList<T>();
//        for(JsonElement elem : jsonArray){
//            list.add(gson.fromJson(elem, cls));
//        }
//        return list;
//
//
//    }
//
//
//    /**
//     * 解析成集合
//     * @param back
//     * @param cls
//     * @param <T>
//     * @return
//     */
//    public <T>List<T> getParseToNoItemsList(String back, Class<T> cls) {
//
//        JsonArray asJsonArray = new JsonParser().parse(back).getAsJsonArray();
//
//        Gson gson = new Gson();
//        List<T> list = new ArrayList<T>();
//        for(JsonElement elem : asJsonArray){
//            list.add(gson.fromJson(elem, cls));
//        }
//        return list;
//
//    }
//
//
//    /**
//     * 这个是 org的jsonobject
//     * @param back
//     * @param key
//     * @return
//     */
//    public String getParseString(String back,String key) {
//
//        String json  = "";
//        JSONObject jsonObject = null;
//        try {
//            jsonObject = new JSONObject(back);
//            json = jsonObject.getString(key);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return json;
//    }


}
