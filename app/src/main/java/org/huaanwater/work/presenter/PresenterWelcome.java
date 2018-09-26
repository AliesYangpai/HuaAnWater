package org.huaanwater.work.presenter;


import org.huaanwater.work.method.IWelcome;
import org.huaanwater.work.ui.iview.IWelcomView;

/**
 * Created by Alie on 2017/11/16.
 * 类描述
 * 版本
 */

public class PresenterWelcome extends BasePresenter<IWelcomView> {

    private IWelcomView iWelcomView;
    private IWelcome iWelcome;



    public PresenterWelcome(IWelcomView iWelcomView) {
        this.iWelcomView = iWelcomView;
//        this.iWelcome = new IWelcomeImpl();

}


    public void doGoToMain() {

        iWelcomView.doGoToMain();

    }



}
