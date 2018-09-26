package org.huaanwater.work.function;

import org.feezu.liuli.timeselector.Utils.TextUtil;
import org.huaanwater.work.constant.ConstLocalData;
import org.huaanwater.work.constant.ConstSign;
import org.huaanwater.work.entity.News;
import org.huaanwater.work.entity.active.Active;
import org.huaanwater.work.entity.thepakege.PakegeActive;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alie on 2018/2/24.
 * 类描述
 * 版本
 */

public class FunctionNews {




    /**
     * 获取图片的个数
     *
     * @param
     * @return
     */
    public int getItemType(PakegeActive pakegeActive) {

        int count = ConstLocalData.ADATER_PIC_0;
        News news = pakegeActive.getNews();
        Active active = pakegeActive.getActive();


        if(null != news) {

            String param = news.getImage_url();
            if(TextUtil.isEmpty(param)) {


                return count;
            }
            if (param.contains(ConstSign.DOU_COMMA)) {
                String[] arry = param.split(ConstSign.DOU_COMMA);
                if(arry.length==ConstLocalData.ADATER_PIC_1
                        || arry.length == ConstLocalData.ADATER_PIC_2) {
                    count = ConstLocalData.ADATER_PIC_1;
                }else {
                    count = ConstLocalData.ADATER_PIC_3;
                }
            }else {
                count = ConstLocalData.ADATER_PIC_1;
            }

            return count;
        }


        if(null != active) {


            count =ConstLocalData.ADATER_ACTIVE;
        }




        return count;
    }







    /**
     * 获取图片的个数
     *
     * @param param
     * @return
     */
    public int getPicCount(String param) {

        int count = ConstLocalData.ADATER_PIC_0;


        if(TextUtil.isEmpty(param)) {


            return count;
        }
        if (param.contains(ConstSign.DOU_COMMA)) {
            String[] arry = param.split(ConstSign.DOU_COMMA);
            if(arry.length==ConstLocalData.ADATER_PIC_1
                    || arry.length == ConstLocalData.ADATER_PIC_2) {
                count = ConstLocalData.ADATER_PIC_1;
            }else {
                count = ConstLocalData.ADATER_PIC_3;
            }
        }else {
            count = ConstLocalData.ADATER_PIC_1;
        }
        return count;
    }







    /**
     * 获取图片数组
     *
     * @param param
     * @return
     */
    public String[] getPicArry(String param) {

        String[] arry = null;

        if (!TextUtil.isEmpty(param)) {
            if (param.contains(ConstSign.DOU_COMMA)) {
                arry = param.split(ConstSign.DOU_COMMA);
            }else {

                arry = new String[]{param};
            }
        }

        return arry;
    }


    /**
     * 新闻转化为封装集合
     * @param newses
     * @return
     */
    public List<PakegeActive> getPakegeActiveList(List<News> newses) {

        List<PakegeActive> pakegeActives = new ArrayList<>();


        if(null != newses && newses.size() > 0) {

            for (News news:newses) {
                PakegeActive pakegeActive = new PakegeActive();
                pakegeActive.setNews(news);
                pakegeActives.add(pakegeActive);
            }

        }



        return pakegeActives;

    }
}
