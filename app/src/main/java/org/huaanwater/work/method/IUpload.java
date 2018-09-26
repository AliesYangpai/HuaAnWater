package org.huaanwater.work.method;

import com.yanzhenjie.nohttp.FileBinary;

import org.huaanwater.work.listener.OnDataBackListener;

/**
 * Created by Alie on 2018/1/24.
 * 类描述
 * 版本
 */

public interface IUpload {


    void doUpLoad(String url,
                  FileBinary fileBinary,
                  OnDataBackListener onDataBackListener);
}
