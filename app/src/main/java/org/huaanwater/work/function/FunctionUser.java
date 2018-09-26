package org.huaanwater.work.function;

import org.huaanwater.work.constant.ConstHz;
import org.huaanwater.work.constant.ConstLocalData;

/**
 * Created by Alie on 2018/3/7.
 * 类描述
 * 版本
 */

public class FunctionUser {

    public String getUserTypeHz(String string) {


        String target = ConstHz.OTHER;

        switch (string){

            case ConstLocalData.STUDENT://学生
                target = ConstHz.STUDENT;
                break;
            case ConstLocalData.ENTERPRISE://企业
                target = ConstHz.ENTERPRISE;
                break;
            case ConstLocalData.HOUSING_ESTATE://小区
                target = ConstHz.HOUSING_ESTATE;
                break;
            case ConstLocalData.OTHER://其他
                target = ConstHz.OTHER;
                break;
        }

        return target;
    }
}
