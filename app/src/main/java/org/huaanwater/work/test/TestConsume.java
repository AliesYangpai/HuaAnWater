package org.huaanwater.work.test;

/**
 * Created by Alie on 2018/2/2.
 * 类描述
 * 版本
 */

public class TestConsume {

    private String time;

    private String money;

    private String state;
    private String info;


    public TestConsume() {
    }

    public TestConsume(String time, String money) {
        this.time = time;
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "TestCustume{" +
                "time='" + time + '\'' +
                ", money='" + money + '\'' +
                '}';
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
