package org.huaanwater.work.ui.iview;


import org.huaanwater.work.ui.IBaseView;

/**
 * Created by Alie on 2017/11/16.
 * 类描述
 * 版本
 */

public interface IWelcomView extends IBaseView {


    void doGoToMain();

    void onDataBackFail(int code, String errorMsg);



}
