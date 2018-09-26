package org.huaanwater.work.http.requestparam;

import com.google.gson.JsonObject;

/**
 * Created by Alie on 2018/3/4.
 * 类描述
 * 版本
 */

public class CommentParam extends BaseParam{


    /**
     * 评论新闻
     * @param news_content_id
     * @param comment
     * @return
     */
    public String getCommentNewsParam(int news_content_id,String comment) {


//        {
//            "news_content_id": 0,
//                "comment": "string",
//        }

        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty("news_content_id",news_content_id);
        jsonObject.addProperty("comment",comment);

        return jsonObject.toString();
    }



    /**
     * 评论回复
     * @param news_content_id
     * @param comment
     * @return
     */
    public String getCommentCommentParam(int news_content_id,String comment,int parent_comment_id) {


//        {
//            "news_content_id": 0,
//                "comment": "string",
//                "parent_comment_id": 0
//        }

        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty("news_content_id",news_content_id);
        jsonObject.addProperty("comment",comment);
        jsonObject.addProperty("parent_comment_id",parent_comment_id);
        return jsonObject.toString();
    }
}
