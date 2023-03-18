package com.chat.message.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chat.message.dao.UserDao;
import com.chat.message.model.User;
import com.chat.message.service.UserService;
import com.chat.message.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {



    /**
     * 根据手机号码查询用户
     * @param phone
     * @return
     */
    @Override
    public User queryByPhone(String phone) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",phone);
        return  baseMapper.selectOne(wrapper);
    }

    /**
     * 手机号和密码登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public User login(String username, String password) {
        // 参数校验失败 参数为空
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return null;
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(User::getUsername, username)
                .eq(User::getPassword, password);
        List<User> UserList = list(wrapper);
        if (UserList != null && UserList.size() > 0) {
            User user = UserList.get(0);
            return user;
        }
        return null;
    }

    @Override
    public Integer register(String code, String password, String username) {
        LambdaQueryWrapper<User> wrapper = new QueryWrapper<User>().lambda().eq(User::getUsername, username);
        List<User> UserList = list(wrapper);
        // 说明手机号已经注册过了
        if (UserList != null && UserList.size() > 0) {
            return -1;
        }
        // 校验不通过
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(code)) {
            return 0;
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        return baseMapper.insert(user);
    }
}
