package org.huaanwater.work.http.requestparam;

import com.google.gson.JsonObject;

/**
 * Created by Alie on 2018/2/28.
 * 类描述
 * 版本
 */

public class WaterParam extends BaseParam {



    /**
     * 出水的参数
     * @param imei
     * @param order_no
     * @return
     */
    public String getOutPutWaterParam(String imei, String order_no) {

        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty("imei",imei);
        jsonObject.addProperty("order_no",order_no);

        return jsonObject.toString();
    }

}
