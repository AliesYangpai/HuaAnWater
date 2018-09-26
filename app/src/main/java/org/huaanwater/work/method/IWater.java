package org.huaanwater.work.method;

import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.ui.IBaseView;

/**
 * Created by Alie on 2018/2/28.
 * 类描述
 * 版本
 */

public interface IWater  {


    /**
     * 出水接口
     * @param url
     * @param imei
     * @param order_no  可为空
     * @param onDataBackListener
     */
     void doOutPutWater(String url, String imei, String order_no, OnDataBackListener onDataBackListener);
}
