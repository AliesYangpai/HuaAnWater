package org.huaanwater.work.method;

import org.huaanwater.work.listener.OnDataBackListener;

/**
 * Created by Alie on 2018/3/6.
 * 类描述 区域方法
 * 版本
 */

public interface IRegion {


    void doGetFuzzyRegions(String url, String name, OnDataBackListener onDataBackListener);
}
