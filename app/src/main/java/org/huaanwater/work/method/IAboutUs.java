package org.huaanwater.work.method;

import org.huaanwater.work.listener.OnDataBackListener;

/**
 * Created by Alie on 2018/1/24.
 * 类描述
 * 版本
 */

public interface IAboutUs {




    /**
     * 服务端获取版本信息
     * @param url
     * @param
     * @param size
     * @param index
     */
    void doGetAboutUs(String url,
                      int size,
                      int index,
                      String tag_code,
                      OnDataBackListener onDataBackListener);




}
