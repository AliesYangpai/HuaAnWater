package org.huaanwater.work.dao.impl;


import org.huaanwater.work.dao.IBaseDao;
import org.huaanwater.work.entity.User;
import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Alie on 2017/11/6.
 * 类描述   用户实现类
 * 版本
 */

public class IUserDaoImpl implements IBaseDao<User> {


    @Override
    public boolean save(User user) {

        return user.save();
    }

    @Override
    public int deleteAll(Class<User> userClass) {
        return DataSupport.deleteAll(userClass);
    }

    @Override
    public int deleteAllByCondition(Class<User> userClass, String... args) {
        return DataSupport.deleteAll(userClass, args);
    }


    @Override
    public List<User> findAll(Class<User> userClass) {

        return DataSupport.findAll(userClass);
    }

    @Override
    public User findFirst(Class<User> userClass) {

        return DataSupport.findFirst(userClass);
    }
}
