package org.huaanwater.work.logic;

import org.huaanwater.work.entity.active.ActiveParticipate;

import java.util.List;

/**
 * Created by Alie on 2018/3/15.
 * 类描述
 * 版本
 */

public class LogicActive {


    public boolean isJust1(List<ActiveParticipate> activeParticipates) {
        return activeParticipates.size() == 1;
    }

    public boolean isJust2(List<ActiveParticipate> activeParticipates) {
        return activeParticipates.size() == 2;
    }

    public boolean isInclude3AndBeyond(List<ActiveParticipate> activeParticipates) {
        return activeParticipates.size() >= 3;
    }
}
