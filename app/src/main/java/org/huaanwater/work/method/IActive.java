package org.huaanwater.work.method;

import org.huaanwater.work.listener.OnDataBackListener;

/**
 * Created by Alie on 2018/1/11.
 * 类描述  活动相关
 * 版本
 */

public interface IActive {


    /**
     * 获取活动列表
     * @param url
     * @param ascending
     * @param size
     * @param index
     * @param onDataBackListener
     */
    void doGetActives(String url,boolean ascending, int size, int index, OnDataBackListener onDataBackListener);


    /**
     * 获取活动详情
     * @param url
     * @param activity_management_id
     */
    void doGetActiveDetial(String url,long activity_management_id,OnDataBackListener onDataBackListener);



    /**
     * 参加活动
     * @param url
     * @param
     * @param onDataBackListener
     */
    void doActiveParticipate(String url,OnDataBackListener onDataBackListener);

    /**
     * 检查用户是否参与活动
     * @param url
     * @param activity_management_id
     */
    void doCheckActiveUserExist(String url,long activity_management_id,OnDataBackListener onDataBackListener);
}
