package org.huaanwater.work.ui.iview;

import org.huaanwater.work.entity.Version;
import org.huaanwater.work.ui.IBaseView;

/**
 * Created by Alie on 2018/3/3.
 * 类描述
 * 版本
 */

public interface IVersionView extends IBaseView {

    void onDataBackFail(int code, String errorMsg);

    void onDataBackSuccessForVersion(Version version);
}
