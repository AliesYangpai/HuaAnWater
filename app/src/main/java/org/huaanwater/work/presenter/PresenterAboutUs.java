package org.huaanwater.work.presenter;

import org.huaanwater.work.constant.ConstError;
import org.huaanwater.work.entity.AboutUs;
import org.huaanwater.work.entity.ErrorEntity;
import org.huaanwater.work.http.ParseSerilizable;
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.method.IAboutUs;
import org.huaanwater.work.method.impl.IAboutUsImpl;
import org.huaanwater.work.ui.iview.IAboutUsView;
import org.huaanwater.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created by Alie on 2018/3/3.
 * 类描述
 * 版本
 */

public class PresenterAboutUs extends BasePresenter<IAboutUsView> {

    private IAboutUsView iAboutUsView;
    private IAboutUs iAboutUs;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;

    public PresenterAboutUs(IAboutUsView iAboutUsView) {
        this.iAboutUsView = iAboutUsView;
        this.iAboutUs = new IAboutUsImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
    }


    public void  doGeiAboutUs(String url, int size, int index, String tag_code) {

            iAboutUs.doGetAboutUs(url, size, index, tag_code, new OnDataBackListener() {
                @Override
                public void onStart() {
                    iAboutUsView.showLoadingDialog();
                }

                @Override
                public void onSuccess(String data) {



                    List<AboutUs> list = parseSerilizable.getParseToList(data,AboutUs.class);



                    if(vertifyNotNull.isNotNullListSize(list)) {
                        iAboutUsView.onDataBackSuccessForAboutUs(list.get(0));
                    }



                }

                @Override
                public void onFail(int code, String data) {
                    ErrorEntity errorEntity =parseSerilizable.getParseToObj(data, ErrorEntity.class);
                    if(vertifyNotNull.isNotNullObj(errorEntity)) {
                        iAboutUsView.onDataBackFail(code,errorEntity.getError_message());
                    }else {
                        iAboutUsView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                    }
                }

                @Override
                public void onFinish() {
                    iAboutUsView.dismissLoadingDialog();
                }
            });

    }
}
