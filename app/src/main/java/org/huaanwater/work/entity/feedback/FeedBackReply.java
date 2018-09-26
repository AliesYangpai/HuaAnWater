package org.huaanwater.work.entity.feedback;

import java.io.Serializable;

/**
 * Created by Alie on 2018/2/28.
 * 类描述
 * 版本
 */

public class FeedBackReply implements Serializable {


    //        "feedback_reply_id": 0,
//            "feedback_id": 0,
//            "reply_content": "string",
//            "reply_time": "2018-02-28T05:19:30.154Z",
//            "reply_people": "User",
//            "admin_id": 0,
//            "full_name": "string"


    private int feedback_reply_id;
    private int feedback_id;
    private String reply_content;
    private String reply_time;
    private String reply_people;
    private int admin_id;
    private String full_name;


    public FeedBackReply() {
    }

    public int getFeedback_reply_id() {
        return feedback_reply_id;
    }

    public void setFeedback_reply_id(int feedback_reply_id) {
        this.feedback_reply_id = feedback_reply_id;
    }

    public int getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(int feedback_id) {
        this.feedback_id = feedback_id;
    }

    public String getReply_content() {
        return reply_content;
    }

    public void setReply_content(String reply_content) {
        this.reply_content = reply_content;
    }

    public String getReply_time() {
        return reply_time;
    }

    public void setReply_time(String reply_time) {
        this.reply_time = reply_time;
    }

    public String getReply_people() {
        return reply_people;
    }

    public void setReply_people(String reply_people) {
        this.reply_people = reply_people;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }


    @Override
    public String toString() {
        return "FeedBackReply{" +
                "feedback_reply_id=" + feedback_reply_id +
                ", feedback_id=" + feedback_id +
                ", reply_content='" + reply_content + '\'' +
                ", reply_time='" + reply_time + '\'' +
                ", reply_people='" + reply_people + '\'' +
                ", admin_id=" + admin_id +
                ", full_name='" + full_name + '\'' +
                '}';
    }
}
