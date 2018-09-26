package org.huaanwater.work.ui.iview;

import org.huaanwater.work.entity.User;
import org.huaanwater.work.ui.IBaseView;

/**
 * Created by Alie on 2018/2/6.
 * 类描述
 * 版本
 */

public interface IModifyPassView extends IBaseView{

    void onDataBackFail(int code, String errorMsg);


    void doVertifyErrorForNoPass();

    void doVertifyErrorForNoPassAgain();


    void doVertifyErrorForNotSame2Pass();

    void onDataBackSuccessForModifyPass();




}
