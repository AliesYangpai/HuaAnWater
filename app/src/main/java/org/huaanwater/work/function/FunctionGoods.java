package org.huaanwater.work.function;

import org.feezu.liuli.timeselector.Utils.TextUtil;
import org.huaanwater.work.constant.ConstSign;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Alie on 2018/3/5.
 * 类描述
 * 版本
 */

public class FunctionGoods {




    public String[] getGoodsArryImgs(String url) {




        String[] arry = null;

        if (!TextUtil.isEmpty(url)) {
            if (url.contains(ConstSign.DOU_COMMA)) {
                arry = url.split(ConstSign.DOU_COMMA);
            }else {
                arry = new String[]{url};
            }
        }

        return arry;

    }

    public List<String> getGoodsImgs(String url) {



        List<String> imgs = null;

        String[] arry = null;

        if (!TextUtil.isEmpty(url)) {
            if (url.contains(ConstSign.DOU_COMMA)) {
                arry = url.split(ConstSign.DOU_COMMA);
            }else {
                arry = new String[]{url};
            }
        }
        imgs = Arrays.asList(arry);

        return imgs;

    }
}
