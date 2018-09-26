package org.huaanwater.work.function;

import org.feezu.liuli.timeselector.Utils.TextUtil;
import org.huaanwater.work.constant.ConstHz;
import org.huaanwater.work.constant.ConstLocalData;
import org.huaanwater.work.constant.ConstSign;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by Alie on 2018/2/28.
 * 类描述
 * 版本
 */

public class FunctionFeedBack {





    /**
     * 是否已读
     * @param isRead
     * @return
     */
    public String getHzRead(boolean isRead) {

        String hz = ConstHz.IS_NOT_READ;
        if(isRead){

            hz = ConstHz.IS_READ;
        }
        return hz;

    }

    public String getFrontTime(String time) {

        String target = time ;

        if(!TextUtil.isEmpty(time)) {

           String[] arr = time.split(ConstSign.SPACE);


            target = arr[0];
        }

        return target;
    }


    public String getFeedBackImgFromMap(Map map) {

        String target = "";

        if(null != map && map.size() > 0) {

            Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, String> entry = it.next();

                target =target+ entry.getValue()+ConstSign.DOU_COMMA;
                // it.remove(); 删除元素
            }

            target = target.substring(0,target.length() - 1);

        }

        return target;

    }


    /**
     * 获取上传图片数组
     * @param imgs
     * @return
     */
    public String[] getFeedBackImgArry(String imgs) {

        String[] arry = null;

        if (!TextUtil.isEmpty(imgs)) {
            if (imgs.contains(ConstSign.DOU_COMMA)) {
                arry = imgs.split(ConstSign.DOU_COMMA);
            }else {
                arry = new String[]{imgs};
            }
        }

        return arry;

    }


}
