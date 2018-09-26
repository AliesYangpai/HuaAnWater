package org.huaanwater.work.entity;

import java.io.Serializable;

/**
 * Created by Alie on 2018/3/3.
 * 类描述
 * 版本
 */

public class RechargeRecord implements Serializable {

//    {
//        "payment_record_id": 0,
//            "payment_channel_id": "string",
//            "order_type": "string",
//            "reference_number": "string",
//            "amount": 0,
//            "user_id": 0,
//            "status": "string",
//            "create_time": "2018-03-03T07:13:35.903Z",
//            "success_time": "2018-03-03T07:13:35.903Z",
//            "reconciliation_time": "2018-03-03T07:13:35.903Z",
//            "is_settled": true,
//            "settle_date": "2018-03-03T07:13:35.903Z",
//            "discount": 0,
//            "check_sign_time": "2018-03-03T07:13:35.903Z",
//            "app_type": "string",
//            "os_type": "string",
//            "payer": 0
//    }






    private int payment_record_id;//    支付记录Id ,
    private String payment_channel_id;//    支付渠道编码
    private String order_type;//订单类型
    private String reference_number;//订单编号 ,
    private float amount;//支付金额 ,
    private int user_id;//用户Id ,
    private String status;//支付状态 ,
    private String create_time; //创建时间 ,
    private String success_time; //支付成功时间 ,
    private String reconciliation_time; //对账时间 ,
    private boolean is_settled;//已结算 ,
    private String settle_date;// 结算日期 ,
    private float discount;//优惠价格 ,
    private String check_sign_time;//验签时间
    private String app_type;// 设备类型
    private String os_type;//操作系统 ,
    private String payer;//付款方

    public RechargeRecord() {
    }

    public int getPayment_record_id() {
        return payment_record_id;
    }

    public void setPayment_record_id(int payment_record_id) {
        this.payment_record_id = payment_record_id;
    }

    public String getPayment_channel_id() {
        return payment_channel_id;
    }

    public void setPayment_channel_id(String payment_channel_id) {
        this.payment_channel_id = payment_channel_id;
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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getSuccess_time() {
        return success_time;
    }

    public void setSuccess_time(String success_time) {
        this.success_time = success_time;
    }

    public String getReconciliation_time() {
        return reconciliation_time;
    }

    public void setReconciliation_time(String reconciliation_time) {
        this.reconciliation_time = reconciliation_time;
    }

    public boolean is_settled() {
        return is_settled;
    }

    public void setIs_settled(boolean is_settled) {
        this.is_settled = is_settled;
    }

    public String getSettle_date() {
        return settle_date;
    }

    public void setSettle_date(String settle_date) {
        this.settle_date = settle_date;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public String getCheck_sign_time() {
        return check_sign_time;
    }

    public void setCheck_sign_time(String check_sign_time) {
        this.check_sign_time = check_sign_time;
    }

    public String getApp_type() {
        return app_type;
    }

    public void setApp_type(String app_type) {
        this.app_type = app_type;
    }

    public String getOs_type() {
        return os_type;
    }

    public void setOs_type(String os_type) {
        this.os_type = os_type;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }


    @Override
    public String toString() {
        return "RechargeRecord{" +
                "payment_record_id=" + payment_record_id +
                ", payment_channel_id='" + payment_channel_id + '\'' +
                ", order_type='" + order_type + '\'' +
                ", reference_number='" + reference_number + '\'' +
                ", amount=" + amount +
                ", user_id=" + user_id +
                ", status='" + status + '\'' +
                ", create_time='" + create_time + '\'' +
                ", success_time='" + success_time + '\'' +
                ", reconciliation_time='" + reconciliation_time + '\'' +
                ", is_settled=" + is_settled +
                ", settle_date='" + settle_date + '\'' +
                ", discount=" + discount +
                ", check_sign_time='" + check_sign_time + '\'' +
                ", app_type='" + app_type + '\'' +
                ", os_type='" + os_type + '\'' +
                ", payer='" + payer + '\'' +
                '}';
    }
}
