package org.huaanwater.work.entity;

/**
 * Created by Alie on 2018/2/9.
 * 类描述
 * 版本
 */

public class PayChannel {


//        "channel_id": "string",
//                "name": "string",
//                "description": "string",
//                "logo": "string",
//                "tips": "string",
//                "balance": 0,
//                "limit": 0,
//                "has_pay_pwd": true


    private String channel_id;
    private String name;
    private String description;

    private String logo;
    private String tips;
    private String balance;
    private String limit;

    public PayChannel() {
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "PayChannel{" +
                "channel_id='" + channel_id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", logo='" + logo + '\'' +
                ", tips='" + tips + '\'' +
                ", balance='" + balance + '\'' +
                ", limit='" + limit + '\'' +
                '}';
    }
}
