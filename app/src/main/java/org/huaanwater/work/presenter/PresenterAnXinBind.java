package org.huaanwater.work.presenter;

import org.huaanwater.work.constant.ConstError;
import org.huaanwater.work.entity.AnXinBindBack;
import org.huaanwater.work.entity.ErrorEntity;
import org.huaanwater.work.http.ParseSerilizable;
import org.huaanwater.work.listener.OnDataBackListener;
import org.huaanwater.work.method.IThirdAuth;
import org.huaanwater.work.method.impl.IThirdAuthImpl;
import org.huaanwater.work.ui.iview.IAnXinBindView;
import org.huaanwater.work.verification.VertifyNotNull;

/**
 * Created by Alie on 2018/3/8.
 * 类描述
 * 版本
 */

public class PresenterAnXinBind extends BasePresenter<IAnXinBindView> {

    private IAnXinBindView iAnXinBindView;
    private IThirdAuth iThirdAuth;
    private ParseSerilizable parseSerilizable;
    private VertifyNotNull vertifyNotNull;

    public PresenterAnXinBind(IAnXinBindView iAnXinBindView) {
        this.iAnXinBindView = iAnXinBindView;
        this.iThirdAuth = new IThirdAuthImpl();
        this.parseSerilizable = new ParseSerilizable();
        this.vertifyNotNull = new VertifyNotNull();
    }

    public void doAnXinBind(String url, String ax_account, String ax_password) {

        iThirdAuth.doBindAnXinAccount(url, ax_account, ax_password, new OnDataBackListener() {
            @Override
            public void onStart() {
                iAnXinBindView.showLoadingDialog();
            }

            @Override
            public void onSuccess(String data) {


                AnXinBindBack anXinBindBack = parseSerilizable.getParseToObj(data, AnXinBindBack.class);
                if(vertifyNotNull.isNotNullObj(anXinBindBack)) {

                    if (anXinBindBack.getSuccess()) {

                        iAnXinBindView.onDataBackSuccessForAnXinBind(anXinBindBack);
                    }else {

                        iAnXinBindView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE,anXinBindBack.getRe_value());
                    }
                }else {

                    iAnXinBindView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }

            }

            @Override
            public void onFail(int code, String data) {

                ErrorEntity errorEntity = parseSerilizable.getParseToObj(data, ErrorEntity.class);
                if (vertifyNotNull.isNotNullObj(errorEntity)) {
                    iAnXinBindView.onDataBackFail(code, errorEntity.getError_message());
                } else {
                    iAnXinBindView.onDataBackFail(ConstError.DEFUAL_ERROR_CODE, ConstError.PARSE_ERROR_MSG);
                }
            }

            @Override
            public void onFinish() {
                iAnXinBindView.dismissLoadingDialog();
            }
        });

    }
}
