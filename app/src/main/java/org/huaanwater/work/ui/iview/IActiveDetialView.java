package org.huaanwater.work.ui.iview;

import org.huaanwater.work.entity.active.Active;
import org.huaanwater.work.entity.active.ActiveParticipate;
import org.huaanwater.work.ui.IBaseView;

import java.util.List;

/**
 * Created by Alie on 2018/3/11.
 * 类描述
 * 版本
 */

public interface IActiveDetialView extends IBaseView{


    void onDataBackFail(int code, String errorMsg);


    void doSetActiveDaraToUi(Active active);

    void onDataBackSuccessForActiveDetail(Active active);


    void onDataBackSuccessForUserExist();

    void onDataBackSuccessForUserNotExist();

    void onDataBackSuccessForParticipate();


    void onDataBackSuccessForParticipatePersonList1(List<ActiveParticipate> activeParticipates);
    void onDataBackSuccessForParticipatePersonList2(List<ActiveParticipate> activeParticipates);
    void onDataBackSuccessForParticipatePersonList3(List<ActiveParticipate> activeParticipates);

    void onDataBackSuccessForParticipateToShowAvatarLayout();

    void onDataBackSuccessForParticipatePersonCount(int count);


    void onDataBackSuccessForUserNotExistFirstIn();
    void onDataBackSuccessForUserExistFirstIn();
}
