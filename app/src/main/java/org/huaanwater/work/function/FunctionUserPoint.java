package org.huaanwater.work.function;

import org.huaanwater.work.constant.ConstHz;
import org.huaanwater.work.constant.ConstLocalData;
import org.huaanwater.work.entity.ErrorEntity;
import org.huaanwater.work.http.HttpConst;

/**
 * Created by Alie on 2018/2/28.
 * 类描述
 * 版本
 */

public class FunctionUserPoint {


    /**
     * 流水方向 汉字
     * @param flow_direction
     * @return
     */
    public String getHzFlowDirection(String flow_direction) {

        String hz = ConstHz.INCOME;
        if(flow_direction.equals(ConstLocalData.INCOME)){
            hz = ConstHz.INCOME;
        }else if(flow_direction.equals(ConstLocalData.OUTLAY)) {

            hz = ConstHz.OUTLAY;
        }
        return hz;
    }


    /**
     * 操作类型汉字
     * @param handle_type
     * @return
     */
    public String getHzHandleType(String handle_type) {

        String hz = ConstHz.RECHARGE;
        if(handle_type.equals(ConstLocalData.SIGNIN)){

            hz = ConstHz.SIGNIN;
        }else if(handle_type.equals(ConstLocalData.CONSUMPTION)) {

            hz = ConstHz.CONSUMPTION;
        }else if(handle_type.equals(ConstLocalData.RECHARGE)) {

            hz = ConstHz.RECHARGE;
        }
        return hz;

    }


}
