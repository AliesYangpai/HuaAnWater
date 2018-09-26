package org.huaanwater.work.ui.iview;

import org.huaanwater.work.ui.IBaseView;

/**
 * Created by Alie on 2018/2/7.
 * 类描述
 * 版本
 */

public interface ISettingView extends IBaseView {

    void onDataBackFail(int code, String errorMsg);

    void onDataBackSuccessForLogout();
}
