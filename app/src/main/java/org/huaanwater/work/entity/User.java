package org.huaanwater.work.entity;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by Alie on 2018/1/22.
 * 类描述  用户表
 * 版本
 */

public class User extends DataSupport implements Serializable {

    private int id;//自增id
    private int user_id;//用户Id ,
    private int region_id; //区域Id ,
    private String uu_id; //uuid


    private int card_type;//卡类型 ,
    private String phone;//手机号 ,
    private String user_name; //用户名 ,
    private String avatar; //用户头像 ,
    private String full_name;//用户姓名 ,
    private String nick_name; //昵称 ,
    private String identification_card; //身份证编号 ,
    private String ic_number;//ic卡编号 ,
    private float balance; //:余额 ,
    private String create_time; //:注册时间 ,
    private String last_login_time;//:最后登录时间 ,
    private String currently_login_time; //:当前登录时间 ,
    private int user_level;//:用户等级 ,
    private int user_points;//用户积分 ,
    private String open_id; //:微信开放Id ,
    private String certification_status; //:实名认证状态 ,
    private String unique_code; //:唯一码 ,
    private int parent_user_id; //:邀请人Id ,
    private String refusals; //:认证拒绝原因 ,
    private int province_id;//:省份Id ,
    private int community_id; //:市Id ,
    private String an_xin_account; //:安信账号 ,
    private int county_id;//:区县Id ,
    private String address; //:地址 ,
    private String qq_id;//:QQ账号 ,
    private String ali_id;////阿里账号
    private String identification_card_address; //身份证图片地址 用户类型 = ['Other', 'Student', 'HousingEstate', 'Enterprise'],
    private String user_type;//用户类型type
    private String student_no;//学生证编号


    public User() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getRegion_id() {
        return region_id;
    }

    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }

    public String getUu_id() {
        return uu_id;
    }

    public void setUu_id(String uu_id) {
        this.uu_id = uu_id;
    }

    public int getCard_type() {
        return card_type;
    }

    public void setCard_type(int card_type) {
        this.card_type = card_type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getIdentification_card() {
        return identification_card;
    }

    public void setIdentification_card(String identification_card) {
        this.identification_card = identification_card;
    }

    public String getIc_number() {
        return ic_number;
    }

    public void setIc_number(String ic_number) {
        this.ic_number = ic_number;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(String last_login_time) {
        this.last_login_time = last_login_time;
    }

    public String getCurrently_login_time() {
        return currently_login_time;
    }

    public void setCurrently_login_time(String currently_login_time) {
        this.currently_login_time = currently_login_time;
    }

    public int getUser_level() {
        return user_level;
    }

    public void setUser_level(int user_level) {
        this.user_level = user_level;
    }

    public int getUser_points() {
        return user_points;
    }

    public void setUser_points(int user_points) {
        this.user_points = user_points;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public String getCertification_status() {
        return certification_status;
    }

    public void setCertification_status(String certification_status) {
        this.certification_status = certification_status;
    }

    public String getUnique_code() {
        return unique_code;
    }

    public void setUnique_code(String unique_code) {
        this.unique_code = unique_code;
    }

    public int getParent_user_id() {
        return parent_user_id;
    }

    public void setParent_user_id(int parent_user_id) {
        this.parent_user_id = parent_user_id;
    }

    public String getRefusals() {
        return refusals;
    }

    public void setRefusals(String refusals) {
        this.refusals = refusals;
    }

    public int getProvince_id() {
        return province_id;
    }

    public void setProvince_id(int province_id) {
        this.province_id = province_id;
    }

    public int getCommunity_id() {
        return community_id;
    }

    public void setCommunity_id(int community_id) {
        this.community_id = community_id;
    }

    public String getAn_xin_account() {
        return an_xin_account;
    }

    public void setAn_xin_account(String an_xin_account) {
        this.an_xin_account = an_xin_account;
    }

    public int getCounty_id() {
        return county_id;
    }

    public void setCounty_id(int county_id) {
        this.county_id = county_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQq_id() {
        return qq_id;
    }

    public void setQq_id(String qq_id) {
        this.qq_id = qq_id;
    }

    public String getAli_id() {
        return ali_id;
    }

    public void setAli_id(String ali_id) {
        this.ali_id = ali_id;
    }


    public String getIdentification_card_address() {
        return identification_card_address;
    }

    public void setIdentification_card_address(String identification_card_address) {
        this.identification_card_address = identification_card_address;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getStudent_no() {
        return student_no;
    }

    public void setStudent_no(String student_no) {
        this.student_no = student_no;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", region_id=" + region_id +
                ", uu_id='" + uu_id + '\'' +
                ", card_type=" + card_type +
                ", phone='" + phone + '\'' +
                ", user_name='" + user_name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", full_name='" + full_name + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", identification_card='" + identification_card + '\'' +
                ", ic_number='" + ic_number + '\'' +
                ", balance=" + balance +
                ", create_time='" + create_time + '\'' +
                ", last_login_time='" + last_login_time + '\'' +
                ", currently_login_time='" + currently_login_time + '\'' +
                ", user_level=" + user_level +
                ", user_points=" + user_points +
                ", open_id='" + open_id + '\'' +
                ", certification_status='" + certification_status + '\'' +
                ", unique_code='" + unique_code + '\'' +
                ", parent_user_id=" + parent_user_id +
                ", refusals='" + refusals + '\'' +
                ", province_id=" + province_id +
                ", community_id=" + community_id +
                ", an_xin_account='" + an_xin_account + '\'' +
                ", county_id=" + county_id +
                ", address='" + address + '\'' +
                ", qq_id='" + qq_id + '\'' +
                ", ali_id='" + ali_id + '\'' +
                ", identification_card_address='" + identification_card_address + '\'' +
                ", user_type='" + user_type + '\'' +
                ", student_no='" + student_no + '\'' +
                '}';
    }
}
