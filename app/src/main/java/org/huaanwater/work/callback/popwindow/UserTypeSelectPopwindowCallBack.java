package org.huaanwater.work.callback.popwindow;

import org.huaanwater.work.entity.UserType;

/**
 * Created by Administrator on 2017/4/18.
 * 类描述 设置选择类型的回调函数
 * 版本
 */

public interface UserTypeSelectPopwindowCallBack {

     void onSelectStudentType(UserType userType);

     void onSelectHousingEstateType(UserType userType);

     void onSelectEnterpriseType(UserType userType);

     void onSelectOtherType(UserType userType);
}
