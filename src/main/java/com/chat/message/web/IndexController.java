package com.chat.message.web;


import com.chat.message.model.User;
import com.chat.message.service.ALSendSmsService;
import com.chat.message.service.UserService;
import com.chat.message.util.PhoneUtils;
import com.chat.message.util.R;
import com.chat.message.util.VerificationCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/index")
@Slf4j
public class IndexController {

    private static final Map registerMap;
    private static final Map loginMap;

    static {
        registerMap = new HashMap();
        loginMap = new HashMap();
    }

    @Autowired
    private ALSendSmsService alSendSmsService;

    @Autowired
    private UserService userService;


    /**
     * 注册发送验证码
     *
     * @param phone
     * @return
     */
    @GetMapping("/sendRegisterCode/{phone}")
    public R sendRegisterCode(@PathVariable String phone) {
        if (phone == null) {
            return R.error()
                    .put("message","手机号码为空");
        }
        if (!PhoneUtils.validatePhoneNumber(phone)) {
            return R.error()
                    .put("message","手机号码格式错误");
        }
        User user = userService.queryByPhone(phone);
        if (user != null) {
            return R.error()
                    .put("message","手机号已经被注册");
        }
        String code = VerificationCodeGenerator.generateVerificationCode(4);
        if (alSendSmsService.SendCode(phone, code)) {
            registerMap.put(phone, code);
            return R.ok()
                    .put("message","发送成功");
        }
        return R.error()
                .put("message","发送失败");
    }


    /**
     * 注册保存数据
     *
     * @param user
     * @return
     */
    @PostMapping("/register")
    public R register(@RequestBody User user) {
        Integer res =  userService.register(user.getCode(),user.getPassword(),user.getUsername());
        if (res == -1){
            return R.error().put("message","手机号已注册！");
        }else if (res== 0){
            return R.error().put("message","请求不完整！");
        }else if (res== 1){
            return R.ok()
                    .put("code",200)
                    .put("message","注册成功！");
        }else{
            return R.error().put("message","未知错误！");
        }

//        String code = (String) registerMap.get(user.getUsername());
//        if (code == null) {
//            return R.error().put("message","验证码错误！");
//        }
//        if (code.equals(user.getCode())) {
//            if (userService.save(user)) {
//                return R.ok().put("message","注册成功！");
//            }
//        }
    }





    /**
     * 登录获取信息
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    public R login(@RequestBody User user) {
        User login = userService.login(user.getUsername(),user.getPassword());

        if (login==null) {
            return R.error("账号或密码错误！");
        }
        login.setPassword("");

        return R.ok()
                .put("code",200)
                .put("message", "登录成功")
                .put("data", login);
    }


}
