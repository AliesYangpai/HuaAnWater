package org.huaanwater.work.presenter;

import org.huaanwater.work.constant.ConstError;
import org.huaanwater.work.entity.ErrorEntity;
import org.huaanwater.work.entity.News;
import org.huaanwater.work.entity.Version;
import org.huaanwater.work.http.ParseSerilizable;
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.method.IVersion;
import org.huaanwater.work.method.impl.IVersionImpl;
import org.huaanwater.work.ui.iview.IVersionView;
import org.huaanwater.work.verification.VertifyNotNull;

import java.util.List;

/**
 * Created by Alie on 2018/3/3.
 * 类描述
 * 版本
 */

public class PresenterVersion extends BasePresenter<IVersionView> {

    private IVersionView iVersionView;
    private IVersion iVersion;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;

    public PresenterVersion(IVersionView iVersionView) {
        this.iVersionView = iVersionView;
        this.iVersion = new IVersionImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
    }


    public void  doGetVersion(String url, int size, int index, String tag_code) {

            iVersion.doGetVersion(url, size, index, tag_code, new OnDataBackListener() {
                @Override
                public void onStart() {
                    iVersionView.showLoadingDialog();
                }

                @Override
                public void onSuccess(String data) {



                    List<Version> list = parseSerilizable.getParseToList(data,Version.class);



                    if(vertifyNotNull.isNotNullListSize(list)) {
                        iVersionView.onDataBackSuccessForVersion(list.get(0));
                    }



                }

                @Override
                public void onFail(int code, String data) {
                    ErrorEntity errorEntity =parseSerilizable.getParseToObj(data, ErrorEntity.class);
                    if(vertifyNotNull.isNotNullObj(errorEntity)) {
                        iVersionView.onDataBackFail(code,errorEntity.getError_message());
                    }else {
                        iVersionView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,ConstError.PARSE_ERROR_MSG);
                    }
                }

                @Override
                public void onFinish() {
                    iVersionView.dismissLoadingDialog();
                }
            });

    }
}
