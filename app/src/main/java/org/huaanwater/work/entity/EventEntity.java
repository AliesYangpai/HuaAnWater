package org.huaanwater.work.entity;


/**
 * Created by Administrator on 2017/5/17.
 * 类描述   用于eventBus对象传递
 * 版本
 */

public class EventEntity {

    private String notifyMsg;

    private int notifyTag; //外层tag

    private int childNotifyTag; //内层tag


    public EventEntity() {
    }

    public String getNotifyMsg() {
        return notifyMsg;
    }

    public void setNotifyMsg(String notifyMsg) {
        this.notifyMsg = notifyMsg;
    }

    public int getNotifyTag() {
        return notifyTag;
    }

    public void setNotifyTag(int notifyTag) {
        this.notifyTag = notifyTag;
    }




    public int getChildNotifyTag() {
        return childNotifyTag;
    }

    public void setChildNotifyTag(int childNotifyTag) {
        this.childNotifyTag = childNotifyTag;
    }


    @Override
    public String toString() {
        return "EventEntity{" +
                "notifyMsg='" + notifyMsg + '\'' +
                ", notifyTag=" + notifyTag +
                ", childNotifyTag=" + childNotifyTag +
                '}';
    }
}
