package org.huaanwater.work.entity.active;

import java.io.Serializable;

/**
 * Created by Alie on 2018/3/11.
 * 类描述  活动实体类
 * 版本
 */

public class Active implements Serializable {


//    {
//        "activity_management_id": 0,
//            "title": "string",
//            "content": "string",
//            "link_url": "string",
//            "first_pic": "string",
//            "start_time": "2018-03-10T14:29:44.260Z",
//            "end_time": "2018-03-10T14:29:44.260Z",
//            "admin_id": 0,
//            "create_time": "2018-03-10T14:29:44.260Z",
//            "max_particpation_count": 0
//    }

    private int activity_management_id;
    private String title;
    private String content;
    private String link_url;
    private String first_pic;
    private String start_time;
    private String end_time;
    private int admin_id;
    private String create_time;
    private int max_particpation_count ;

    public Active() {
    }

    public int getActivity_management_id() {
        return activity_management_id;
    }

    public void setActivity_management_id(int activity_management_id) {
        this.activity_management_id = activity_management_id;
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

    public String getLink_url() {
        return link_url;
    }

    public void setLink_url(String link_url) {
        this.link_url = link_url;
    }

    public String getFirst_pic() {
        return first_pic;
    }

    public void setFirst_pic(String first_pic) {
        this.first_pic = first_pic;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getMax_particpation_count() {
        return max_particpation_count;
    }

    public void setMax_particpation_count(int max_particpation_count) {
        this.max_particpation_count = max_particpation_count;
    }

    @Override
    public String toString() {
        return "Active{" +
                "activity_management_id=" + activity_management_id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", link_url='" + link_url + '\'' +
                ", first_pic='" + first_pic + '\'' +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", admin_id=" + admin_id +
                ", create_time='" + create_time + '\'' +
                ", max_particpation_count=" + max_particpation_count +
                '}';
    }
}
