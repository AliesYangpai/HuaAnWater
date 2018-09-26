package org.huaanwater.work.http.requestparam;

import com.google.gson.JsonObject;

/**
 * Created by Alie on 2018/2/28.
 * 类描述
 * 版本
 */

public class FeedBackParam extends BaseParam {


    public String getFeedParam(String title,String content,String feedback_images) {


//        {
//            "title": "string",
//                "content": "string",
//                "feedback_images": "string"
//        }

        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty("title",title);
        jsonObject.addProperty("content",content);
        jsonObject.addProperty("feedback_images",feedback_images);
        return jsonObject.toString();
    }

    public String getContentReplyParam(int feedback_id, String reply_content) {


        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty("feedback_id",feedback_id);
        jsonObject.addProperty("reply_content",reply_content);
        return jsonObject.toString();
    }
}
