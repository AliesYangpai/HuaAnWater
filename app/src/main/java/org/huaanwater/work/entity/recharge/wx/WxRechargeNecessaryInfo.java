package org.huaanwater.work.entity.recharge.wx;

import java.io.Serializable;

/**
 * Created by Alie on 2018/2/9.
 * 类描述
 * 版本
 */

public class WxRechargeNecessaryInfo implements Serializable{



//    {
//        "payment_record_id": 0,
//            "order_type": "Order",
//            "reference_number": "string",
//            "channel_id": "string",
//            "original_amount": 0,
//            "discount": 0,
//            "paid_amount": 0,
//            "result": "Pending",
//            "action_arguments": "string",
//            "parameters": {}
//    }






    private int payment_record_id;
    private String order_type;
    private String reference_number;
    private String channel_id;
    private String original_amount;
    private String discount;
    private String paid_amount;
    private String result;
    private String action_arguments;


    public WxRechargeNecessaryInfo() {
    }


    public int getPayment_record_id() {
        return payment_record_id;
    }

    public void setPayment_record_id(int payment_record_id) {
        this.payment_record_id = payment_record_id;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getReference_number() {
        return reference_number;
    }

    public void setReference_number(String reference_number) {
        this.reference_number = reference_number;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public String getOriginal_amount() {
        return original_amount;
    }

    public void setOriginal_amount(String original_amount) {
        this.original_amount = original_amount;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPaid_amount() {
        return paid_amount;
    }

    public void setPaid_amount(String paid_amount) {
        this.paid_amount = paid_amount;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getAction_arguments() {
        return action_arguments;
    }

    public void setAction_arguments(String action_arguments) {
        this.action_arguments = action_arguments;
    }

    @Override
    public String toString() {
        return "AliRechargeNecessaryInfo{" +
                "payment_record_id=" + payment_record_id +
                ", order_type='" + order_type + '\'' +
                ", reference_number='" + reference_number + '\'' +
                ", channel_id='" + channel_id + '\'' +
                ", original_amount='" + original_amount + '\'' +
                ", discount='" + discount + '\'' +
                ", paid_amount='" + paid_amount + '\'' +
                ", result='" + result + '\'' +
                ", action_arguments='" + action_arguments + '\'' +
                '}';
    }
}
