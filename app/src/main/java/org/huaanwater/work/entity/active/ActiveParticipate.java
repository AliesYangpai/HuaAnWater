package org.huaanwater.work.entity.active;

import java.io.Serializable;

/**
 * Created by Alie on 2018/3/12.
 * 类描述
 * 版本
 */

public class ActiveParticipate implements Serializable {

//    {
//        "activity_participation_record_id": 0,
//            "activity_management_id": 0,
//            "user_id": 0,
//            "remark": "string",
//            "create_time": "2018-03-11T14:36:00.345Z",
//            "phone": "string",
//            "full_name": "string",
//            "avatar": "string"
//    }


    private int activity_participation_record_id;
    private int activity_management_id;
    private int user_id;
    private String remark;
    private String create_time;
    private String phone;
    private String full_name;
    private String avatar;


    public ActiveParticipate() {
    }

    public int getActivity_participation_record_id() {
        return activity_participation_record_id;
    }

    public void setActivity_participation_record_id(int activity_participation_record_id) {
        this.activity_participation_record_id = activity_participation_record_id;
    }

    public int getActivity_management_id() {
        return activity_management_id;
    }

    public void setActivity_management_id(int activity_management_id) {
        this.activity_management_id = activity_management_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "ActiveParticipate{" +
                "activity_participation_record_id=" + activity_participation_record_id +
                ", activity_management_id=" + activity_management_id +
                ", user_id=" + user_id +
                ", remark='" + remark + '\'' +
                ", create_time='" + create_time + '\'' +
                ", phone='" + phone + '\'' +
                ", full_name='" + full_name + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
