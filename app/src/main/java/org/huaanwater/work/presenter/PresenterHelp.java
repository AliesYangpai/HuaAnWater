package org.huaanwater.work.presenter;

import org.huaanwater.work.ui.iview.IHelpView;

/**
 * Created by Alie on 2018/3/2.
 * 类描述
 * 版本
 */

public class PresenterHelp extends BasePresenter<IHelpView> {

    private IHelpView iHelpView;

    public PresenterHelp(IHelpView iHelpView) {
        this.iHelpView = iHelpView;
    }


    public void doSetHelpDataToUi(String data) {

        iHelpView.doSetLocalDataToUi();
    }
}
