package org.huaanwater.work.function;

import org.huaanwater.work.constant.ConstHz;
import org.huaanwater.work.constant.ConstLocalData;

/**
 * Created by Alie on 2018/3/3.
 * 类描述
 * 版本
 */

public class FunctionOrder {


    /**
     * 后去订单状态文字
     * @param status
     * @return
     */
    public String getHzOrderStatus(String status) {
        String target = "";

        switch (status) {

            case ConstLocalData.ORDER_STATUE_INIT://初始状态
                target = ConstHz.ORDER_STATUE_INIT;
                break;
            case ConstLocalData.ORDER_STATUE_WAITING://等待放水
                target = ConstHz.ORDER_STATUE_WAITING;
                break;
            case ConstLocalData.ORDER_STATUE_WATERING://放水中
                target = ConstHz.ORDER_STATUE_WATERING;
                break;
            case ConstLocalData.ORDER_STATUE_CLOSE://已结束
                target = ConstHz.ORDER_STATUE_CLOSE;
                break;
            case ConstLocalData.ORDER_STATUE_WATERFAILED: //放水失败
                target = ConstHz.ORDER_STATUE_WATERFAILED;
                break;
            case ConstLocalData.ORDER_STATUE_ERRORCLOSED://异常结束
                target = ConstHz.ORDER_STATUE_ERRORCLOSED;
                break;
        }

        return target;
    }




    /**
     * 后去订单类型文字
     * @param type
     * @return
     */
    public String getHzOrderType(String type) {
        String target = "";

        switch (type) {

            case ConstLocalData.ORDER_TYPE_USER://用户下单
                target = ConstHz.ORDER_TYPE_USER;
                break;
            case ConstLocalData.ORDER_TYPE_CARD://刷卡消费
                target = ConstHz.ORDER_TYPE_CARD;
                break;

        }

        return target;
    }



}
