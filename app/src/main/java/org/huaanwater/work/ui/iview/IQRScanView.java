package org.huaanwater.work.ui.iview;

import org.huaanwater.work.ui.IBaseView;

/**
 * Created by Alie on 2018/1/22.
 * 类描述
 * 版本
 */

public interface IQRScanView extends IBaseView {




    void onDataBackFail(int code, String errorMsg);


    void onDataBackSuccessForOutPutWater(String data);


}
