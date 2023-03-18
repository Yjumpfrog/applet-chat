package com.chat.message.web;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.chat.message.model.User;
import com.chat.message.service.UserService;
import com.chat.message.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/mobile/register")
@Slf4j
public class MobileApiController {

    @Autowired
    private UserService userService;

    @GetMapping("/verifyMember/{openId}")
    public R verifyMember(@PathVariable String openId) {
        try {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("open_id", openId);
            User user = userService.getOne(queryWrapper, false);
            if (user != null) {
                return R.ok().put("data", user);
            }
            return R.error(HttpStatus.NOT_FOUND.value(), "用户不存在！");
        } catch (Exception e) {
            log.error("Error Message:" + e.getMessage());
            e.printStackTrace();
            return R.error("注册失败");
        }
    }

    @GetMapping("/getMemberList/{openId}")
    public R getMemberList(@PathVariable String openId) {
        try {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.ne("open_id", openId);
            List<User> userList = userService.list(queryWrapper);
            if (userList != null) {
                return R.ok().put("data", userList);
            }
            return R.error(HttpStatus.NOT_FOUND.value(), "用户不存在！");
        } catch (Exception e) {
            log.error("Error Message:" + e.getMessage());
            e.printStackTrace();
            return R.error("注册失败");
        }
    }

    @PostMapping("/getSMS/{phone}")
    public R sendCode(@PathVariable Integer phone) {
        return R.ok().put("data", 200);
//        return R.error("注册失败");
    }

//    @PostMapping("/getSMS")
//    public R sendCode(@RequestParam("phone") Integer phone){
//        return R.ok().put("data", phone);
//        return R.error("注册失败");
//    }



}
