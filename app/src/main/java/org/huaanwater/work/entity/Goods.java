package org.huaanwater.work.entity;

import java.io.Serializable;

/**
 * Created by Alie on 2018/2/5.
 * 类描述 好货
 * 版本
 */

public class Goods implements Serializable {

//    {
//        "news_content_id": 0,
//            "title": "string",
//            "sub_title": "string",
//            "": "string",
//            "": "2018-02-05T11:02:08.968Z",
//            "": 0,
//            "": 0,
//            "": "string",
//            "": 0,
//            "": true,
//            "": "string",
//            "": "string",
//            "": "string",
//            "": "string",
//            "": 0,
//            "": 0,
//            "": 0
//    }


    private int news_content_id;//新闻内容ID ,
    private String title;//标题 ,
    private String sub_title;//短标题,
    private String content; //内容
    private String create_time;//创建时间
    private int admin_id;//发布人id
    private int content_category_id;//内容分类id
    private String category_name;//分类名称
    private String category_index;
    private String category_level;
    private int views_count;//浏览次数
    private boolean is_comment; // 是否可以评论
    private String key_word;//关键字
    private String summary; //内容摘要
    private String image_url;//图片地址
    private String link;//链接地址
    private int reprint_count; //转载次数
    private int like_count;//点赞次数
    private int reply_count;//评论次数
    private int parent_comment_id;//父级评论id

    private String tag_code;
    private String tag_code_description;

    public Goods() {
    }


    public int getParent_comment_id() {
        return parent_comment_id;
    }

    public void setParent_comment_id(int parent_comment_id) {
        this.parent_comment_id = parent_comment_id;
    }

    public int getNews_content_id() {
        return news_content_id;
    }

    public void setNews_content_id(int news_content_id) {
        this.news_content_id = news_content_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public int getContent_category_id() {
        return content_category_id;
    }

    public void setContent_category_id(int content_category_id) {
        this.content_category_id = content_category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public int getViews_count() {
        return views_count;
    }

    public void setViews_count(int views_count) {
        this.views_count = views_count;
    }

    public boolean is_comment() {
        return is_comment;
    }

    public void setIs_comment(boolean is_comment) {
        this.is_comment = is_comment;
    }

    public String getKey_word() {
        return key_word;
    }

    public void setKey_word(String key_word) {
        this.key_word = key_word;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getReprint_count() {
        return reprint_count;
    }

    public void setReprint_count(int reprint_count) {
        this.reprint_count = reprint_count;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public int getReply_count() {
        return reply_count;
    }

    public void setReply_count(int reply_count) {
        this.reply_count = reply_count;
    }


    public String getCategory_index() {
        return category_index;
    }

    public void setCategory_index(String category_index) {
        this.category_index = category_index;
    }

    public String getCategory_level() {
        return category_level;
    }

    public void setCategory_level(String category_level) {
        this.category_level = category_level;
    }

    public String getTag_code() {
        return tag_code;
    }

    public void setTag_code(String tag_code) {
        this.tag_code = tag_code;
    }

    public String getTag_code_description() {
        return tag_code_description;
    }

    public void setTag_code_description(String tag_code_description) {
        this.tag_code_description = tag_code_description;
    }

    @Override
    public String toString() {
        return "News{" +
                "news_content_id=" + news_content_id +
                ", title='" + title + '\'' +
                ", sub_title='" + sub_title + '\'' +
                ", content='" + content + '\'' +
                ", create_time='" + create_time + '\'' +
                ", admin_id=" + admin_id +
                ", content_category_id=" + content_category_id +
                ", category_name='" + category_name + '\'' +
                ", category_index='" + category_index + '\'' +
                ", category_level='" + category_level + '\'' +
                ", views_count=" + views_count +
                ", is_comment=" + is_comment +
                ", key_word='" + key_word + '\'' +
                ", summary='" + summary + '\'' +
                ", image_url='" + image_url + '\'' +
                ", link='" + link + '\'' +
                ", reprint_count=" + reprint_count +
                ", like_count=" + like_count +
                ", reply_count=" + reply_count +
                ", tag_code='" + tag_code + '\'' +
                ", tag_code_description='" + tag_code_description + '\'' +
                '}';
    }
}
