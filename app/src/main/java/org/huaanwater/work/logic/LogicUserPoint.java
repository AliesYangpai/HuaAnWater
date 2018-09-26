package org.huaanwater.work.logic;

import org.huaanwater.work.constant.ConstLocalData;

/**
 * Created by Alie on 2018/2/28.
 * 类描述
 * 版本
 */

public class LogicUserPoint {


    /**
     * 是否是流入
     * @param flow_direction
     * @return
     */
    public boolean isFlowIn(String flow_direction) {
        return flow_direction.equals(ConstLocalData.INCOME);
    }
}
