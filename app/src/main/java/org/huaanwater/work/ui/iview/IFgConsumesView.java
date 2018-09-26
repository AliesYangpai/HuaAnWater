package org.huaanwater.work.ui.iview;

import org.huaanwater.work.entity.Consume;
import org.huaanwater.work.ui.IBaseView;

import java.util.List;

/**
 * Created by Alie on 2018/3/2.
 * 类描述
 * 版本
 */

public interface IFgConsumesView extends IBaseView{

    void onDataBackFail(int code, String errorMsg);

    void onDataBackFailInLoadMore(int code, String errorMsg);

    void onDataBackSuccessForGetConsumes(List<Consume> consumes);

    void onDataBackSuccessForGetConsumesInLoadMore(List<Consume> consumes);



}
