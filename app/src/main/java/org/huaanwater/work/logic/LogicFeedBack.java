package org.huaanwater.work.logic;

import org.feezu.liuli.timeselector.Utils.TextUtil;
import org.huaanwater.work.constant.ConstLocalData;

/**
 * Created by Alie on 2018/2/28.
 * 类描述
 * 版本
 */

public class LogicFeedBack {


    /**
     * 是否已读
     * @param isRead
     * @return
     */
    public boolean isRead(boolean isRead) {
        return isRead;
    }




    /**
     * 没有输入信息
     * @param title
     * @return
     */
    public boolean isNoEnterTitle(String title) {

        return TextUtil.isEmpty(title);

    }

    /**
     * 没有输入信息
     * @param content
     * @return
     */
    public boolean isNoEnterContent(String content) {

        return TextUtil.isEmpty(content);

    }


    /**
     * 来自admin
     * @param reply_people
     * @return
     */
    public boolean isFromAdmin(String reply_people) {
        return reply_people.equals(ConstLocalData.REPLY_ADMIN);
    }


    /**
     * 是否回复
     * @param feedback_status
     * @return
     */
    public boolean isReply(String feedback_status) {


        return feedback_status.equals(ConstLocalData.REPLIED);

    }
}
