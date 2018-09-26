package org.huaanwater.work.presenter;

import org.huaanwater.work.dao.impl.IClearDataDao;
import org.huaanwater.work.ui.iview.ISettingView;

/**
 * Created by Alie on 2018/2/7.
 * 类描述
 * 版本
 */

public class PresenterSetting extends BasePresenter<ISettingView> {

    private ISettingView iSettingView;
    private IClearDataDao iClearDataDao;



    public PresenterSetting(ISettingView iSettingView) {
        this.iSettingView = iSettingView;
        this.iClearDataDao = new IClearDataDao();
    }


    public void doClearAll() {

        iClearDataDao.doClearAllTable();
        iClearDataDao.doClearSp();
        iSettingView.onDataBackSuccessForLogout();
    }
}
