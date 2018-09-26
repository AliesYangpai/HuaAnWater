package org.huaanwater.work.ui.iview;

import org.huaanwater.work.entity.AboutUs;
import org.huaanwater.work.entity.AnXinBindBack;
import org.huaanwater.work.ui.IBaseView;

/**
 * Created by Alie on 2018/3/8.
 * 类描述   华安电信绑定View
 * 版本
 */

public interface IAnXinBindView extends IBaseView {

    void onDataBackFail(int code, String errorMsg);

    void onVertifyErrorForNoAnXinUserName();

    void onVertifyErrorForNoPass();

    void onDataBackSuccessForAnXinBind(AnXinBindBack anXinBindBack);
}
