package com.XXXX.vas.main.service.impl;

import com.XXXX.vas.main.mapper.UserMapper;
import com.XXXX.vas.main.service.UserService;
import com.XXXX.vas.main.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.XXXX.vas.core.model.ReplyObj;

import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * User 表数据服务层接口实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    /** find user by name and age **/
    @Override
    public JsonObject findUser(JsonObject param) {
        LOGGER.debug("enter into service");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        if (param.containsKey("name")) {
            queryWrapper.lambda().like(User::getName, param.getString("name"));
        }
        if (param.containsKey("age")) {
            queryWrapper.lambda().eq(User::getAge, param.getString("age"));
        }
        List<User> list = list(queryWrapper);
        return new JsonObject(ReplyObj.build().setData(list).toString());
    }
}