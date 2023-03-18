package com.chat.message.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chat.message.model.User;

public interface UserService extends IService<User> {

    User queryByPhone(String phone);

    User login(String username, String password);

    Integer register(String code, String password, String username);
}
