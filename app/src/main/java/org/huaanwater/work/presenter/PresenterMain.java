package org.huaanwater.work.presenter;

import org.huaanwater.work.http.ParseSerilizable;
import org.huaanwater.work.logic.LogicUser;
import org.huaanwater.work.ui.iview.IMainView;
import org.huaanwater.work.verification.VertifyNotNull;
import org.w3c.dom.ProcessingInstruction;

/**
 * Created by Alie on 2018/1/17.
 * 类描述
 * 版本
 */

public class PresenterMain extends BasePresenter<IMainView> {
    private IMainView iMainView;
    private VertifyNotNull vertifyNotNull;
    private ParseSerilizable parseSerilizable;


    private LogicUser logicUser;

    public PresenterMain(IMainView iMainView) {
        this.iMainView = iMainView;
        this.vertifyNotNull = new VertifyNotNull();
        this.parseSerilizable = new ParseSerilizable();
    }





    /**
     * 权限判断
     */
    public void doPermissionCheck() {

        iMainView.doPermissionCheck();

    }


    public void doShowPermissionDialog(){

        iMainView.doShowPermissionDialog();
    }


}
