package org.huaanwater.work.logic;

import org.feezu.liuli.timeselector.Utils.TextUtil;
import org.huaanwater.work.constant.ConstLocalData;
import org.huaanwater.work.constant.ConstSp;
import org.huaanwater.work.util.SpUtil;

/**
 * Created by Alie on 2018/1/17.
 * 类描述
 * 版本
 */

public class LogicUser {

    public boolean isLogin() {

        return SpUtil.getInstance().getBoolenValue(ConstSp.SP_KEY_IS_LOGIN,ConstSp.SP_VALUE.DEFAULT_BOOLEAN);
    }

    public boolean isSame2Pass(String pass,String passAgain) {

        return pass.equals(passAgain);
    }


    /**
     *已经输入了 用于输入地址的时候
     * @param str
     * @return
     */
    public boolean isHasEnter(String str) {

        return !TextUtil.isEmpty(str);
    }

    /**
     * 展示布局
     * @param str
     * @return
     */
    public boolean isShowStudentNoLayout(String str) {

        return ConstLocalData.STUDENT.equals(str);
    }



    /**
     * 是学生模式
     * @param str
     * @return
     */
    public boolean isStudtentType(String str) {

        return ConstLocalData.STUDENT.equals(str);
    }
}
