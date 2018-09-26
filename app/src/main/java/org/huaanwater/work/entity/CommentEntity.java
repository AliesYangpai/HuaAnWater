package org.huaanwater.work.entity;

import java.io.Serializable;

/**
 * Created by Alie on 2018/2/26.
 * 类描述  评论的实体类
 * 版本
 */

public class CommentEntity implements Serializable {



//             "news_content_comment_id": 0,
//                     "news_content_id": 0,
//                     "comment": "string",
//                     "create_time": "2018-03-04T14:20:36.337Z",
//                     "parent_comment_id": 0,
//                     "is_anonymous": true,
//                     "user_id": 0,
//                     "user_name": "string",
//                     "tree_code": "string",
//                     "comment_count": 0





       private int news_content_comment_id;//新闻内容评论Id ,
       private int news_content_id;//新闻内容ID
       private String comment;//新闻内容
       private String create_time;//创建时间


       private int parent_comment_id;
       private boolean is_anonymous;//匿名回复
       private String user_id;//用户Id
       private String user_name;//用户名
       private String tree_code;//树形吗
       private int comment_count;


       public CommentEntity() {
       }

       public int getNews_content_comment_id() {
              return news_content_comment_id;
       }

       public void setNews_content_comment_id(int news_content_comment_id) {
              this.news_content_comment_id = news_content_comment_id;
       }


       public int getNews_content_id() {
              return news_content_id;
       }

       public void setNews_content_id(int news_content_id) {
              this.news_content_id = news_content_id;
       }

       public String getComment() {
              return comment;
       }

       public void setComment(String comment) {
              this.comment = comment;
       }

       public String getCreate_time() {
              return create_time;
       }

       public void setCreate_time(String create_time) {
              this.create_time = create_time;
       }


       public int getParent_comment_id() {
              return parent_comment_id;
       }

       public void setParent_comment_id(int parent_comment_id) {
              this.parent_comment_id = parent_comment_id;
       }

       public boolean is_anonymous() {
              return is_anonymous;
       }

       public void setIs_anonymous(boolean is_anonymous) {
              this.is_anonymous = is_anonymous;
       }

       public String getUser_id() {
              return user_id;
       }

       public void setUser_id(String user_id) {
              this.user_id = user_id;
       }

       public String getUser_name() {
              return user_name;
       }

       public void setUser_name(String user_name) {
              this.user_name = user_name;
       }

       public String getTree_code() {
              return tree_code;
       }

       public void setTree_code(String tree_code) {
              this.tree_code = tree_code;
       }

       public int getComment_count() {
              return comment_count;
       }

       public void setComment_count(int comment_count) {
              this.comment_count = comment_count;
       }


       @Override
       public String toString() {
              return "CommentEntity{" +
                      "news_content_comment_id=" + news_content_comment_id +
                      ", news_content_id=" + news_content_id +
                      ", comment='" + comment + '\'' +
                      ", create_time='" + create_time + '\'' +
                      ", parent_comment_id=" + parent_comment_id +
                      ", is_anonymous=" + is_anonymous +
                      ", user_id='" + user_id + '\'' +
                      ", user_name='" + user_name + '\'' +
                      ", tree_code='" + tree_code + '\'' +
                      ", comment_count=" + comment_count +
                      '}';
       }
}
