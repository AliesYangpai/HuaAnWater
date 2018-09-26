package org.huaanwater.work.entity;

import java.io.Serializable;

/**
 * Created by Alie on 2018/3/3.
 * 类描述
 * 版本
 */

public class Consume implements Serializable {

    //    {
//        "id": 0,
//            "order_no": "string",
//            "user_id": 0,
//            "account": "string",
//            "balance": 0,
//            "device_id": 0,
//            "imei": "string",
//            "name": "string",
//            "status": "Init",
//            "card_id": 0,
//            "type": "User",
//            "amount": 0,
//            "createtime": 0,
//            "create_times": "2018-03-02T16:38:31.344Z",
//            "opentime": 0,
//            "open_time": "2018-03-02T16:38:31.344Z",
//            "closetime": 0,
//            "close_time": "2018-03-02T16:38:31.344Z",
//            "pulse": 0,
//            "use_time": 0,
//            "remark": "string",
//            "payment": 0,
//            "used_user_points": 0
//    }









    private String order_no;
    private int user_id;
    private String account;
    private int balance;
    private int device_id;
    private String imei;
    private String name;
    private String status;
    private String card_id;
    private String type;
    private float amount;
    private String createtime;
    private String create_times;
    private int opentime;
    private String open_time;
    private int closetime;
    private String close_time;
    private int pulse;
    private String use_time;
    private String remark;
    private float payment;
    private int used_user_points;


    public Consume() {
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getDevice_id() {
        return device_id;
    }

    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getCreate_times() {
        return create_times;
    }

    public void setCreate_times(String create_times) {
        this.create_times = create_times;
    }

    public int getOpentime() {
        return opentime;
    }

    public void setOpentime(int opentime) {
        this.opentime = opentime;
    }

    public String getOpen_time() {
        return open_time;
    }

    public void setOpen_time(String open_time) {
        this.open_time = open_time;
    }

    public int getClosetime() {
        return closetime;
    }

    public void setClosetime(int closetime) {
        this.closetime = closetime;
    }

    public String getClose_time() {
        return close_time;
    }

    public void setClose_time(String close_time) {
        this.close_time = close_time;
    }

    public int getPulse() {
        return pulse;
    }

    public void setPulse(int pulse) {
        this.pulse = pulse;
    }

    public String getUse_time() {
        return use_time;
    }

    public void setUse_time(String use_time) {
        this.use_time = use_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public float getPayment() {
        return payment;
    }

    public void setPayment(float payment) {
        this.payment = payment;
    }

    public int getUsed_user_points() {
        return used_user_points;
    }

    public void setUsed_user_points(int used_user_points) {
        this.used_user_points = used_user_points;
    }
}
