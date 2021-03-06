package org.huaanwater.work.entity.feedback;

import java.io.Serializable;

/**
 * Created by Alie on 2018/2/28.
 * 类描述
 * 版本
 */

public class FeedBack implements Serializable{



//    {
//        "feedback_id": 0,
//            "title": "string",
//            "content": "string",
//            "feedback_images": "string",
//            "user_id": 0,
//            "phone": "string",
//            "full_name": "string",
//            "feedback_status": "New",
//            "create_time": "2018-03-03T10:44:28.550Z",
//            "rec_read": true
//    }








    private int feedback_id; //反馈Id ,
    private String title;
    private String content;//反馈内容
    private String feedback_images;
    private int user_id;//反馈用户Id
    private String phone; //手机号
    private String full_name; //用户姓名
    private String feedback_status; //反馈状态
    private String create_time;//创建时间
    private boolean rec_read; //已读


    public FeedBack() {
    }

    public int getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(int feedback_id) {
        this.feedback_id = feedback_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFeedback_images() {
        return feedback_images;
    }

    public void setFeedback_images(String feedback_images) {
        this.feedback_images = feedback_images;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getFeedback_status() {
        return feedback_status;
    }

    public void setFeedback_status(String feedback_status) {
        this.feedback_status = feedback_status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public boolean isRec_read() {
        return rec_read;
    }

    public void setRec_read(boolean rec_read) {
        this.rec_read = rec_read;
    }

    @Override
    public String toString() {
        return "FeedBack{" +
                "feedback_id=" + feedback_id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", feedback_images='" + feedback_images + '\'' +
                ", user_id=" + user_id +
                ", phone='" + phone + '\'' +
                ", full_name='" + full_name + '\'' +
                ", feedback_status='" + feedback_status + '\'' +
                ", create_time='" + create_time + '\'' +
                ", rec_read=" + rec_read +
                '}';
    }
}
