package org.huaanwater.work.dao.impl;


import org.huaanwater.work.constant.ConstSp;
import org.huaanwater.work.entity.User;
import org.huaanwater.work.util.SpUtil;
import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/11/23.
 * 类描述   清楚数据的Dao
 * 版本
 */

public class IClearDataDao {


    /**
     * 删除单个本地表
     * @param mClass
     */
    public void doClearSingleTable(Class<?> mClass) {

        DataSupport.deleteAll(mClass);

    }


    /**
     * 删除全部的表
     */
    public void doClearAllTable() {

        DataSupport.deleteAll(User.class);



    }


    /**
     * 删除全部本地标记
     */
    public void doClearSp() {


        /**
         * 清除登录状态
         */

        SpUtil.getInstance().saveBooleanTosp(ConstSp.SP_KEY_IS_LOGIN,ConstSp.SP_VALUE.DEFAULT_BOOLEAN);
        /**
         * 清除token
         */
        SpUtil.getInstance().saveStringToSp(ConstSp.SP_KEY_TOKEN,ConstSp.SP_VALUE.DEFAULT_STRING);

    }



}
