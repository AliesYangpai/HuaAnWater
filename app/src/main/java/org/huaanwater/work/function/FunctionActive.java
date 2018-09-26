package org.huaanwater.work.function;

import org.huaanwater.work.entity.News;
import org.huaanwater.work.entity.active.Active;
import org.huaanwater.work.entity.active.ActiveParticipate;
import org.huaanwater.work.entity.thepakege.PakegeActive;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Alie on 2018/3/11.
 * 类描述  活动相关方法
 * 版本
 */

public class FunctionActive {


    public boolean isMaxActives(List<Active> actives, int allCount) {

        return actives.size() == allCount;

    }


    /**
     * 新闻转化为封装集合
     *
     * @param actives
     * @return
     */
    public List<PakegeActive> getPakegeActiveList(List<Active> actives) {

        List<PakegeActive> pakegeActives = new ArrayList<>();


        if (null != actives && actives.size() > 0) {

            for (Active active : actives) {
                PakegeActive pakegeActive = new PakegeActive();
                pakegeActive.setActive(active);
                pakegeActives.add(pakegeActive);
            }

        }

        return pakegeActives;

    }


    /**
     * 获取随机的活动整合到
     * @param actives
     * @return
     */
    public PakegeActive getRandomActiveToPakage(List<Active> actives) {

        PakegeActive pakegeActive = null;
        if (null != actives && actives.size() > 0) {
            Active active = actives.get(new Random().nextInt(actives.size()));
            pakegeActive = new PakegeActive();
            pakegeActive.setActive(active);
        }
        return pakegeActive;
    }



}
