package org.huaanwater.work.function;

/**
 * Created by Alie on 2018/3/5.
 * 类描述
 * 版本
 */

public class FunctionComment  {


    public String getCommentCount(int commentCount) {

        String target = "";

        if(commentCount > 0){

            target = String.valueOf(commentCount);
        }

        return target;
    }
}
