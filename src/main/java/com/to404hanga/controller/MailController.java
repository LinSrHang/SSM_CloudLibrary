package com.to404hanga.controller;

import com.to404hanga.domain.User;
import com.to404hanga.service.UserService;
import com.to404hanga.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/email")
public class MailController {
    @Autowired
    UserService userService;
    @RequestMapping("/send")
    public void send(String user_email, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        if (user_email == null || user_email.isEmpty()) {
            // 邮箱不能为空
            out.println("Fail! The email cannot be empty!");
            out.close();
            return;
        }

        if (!VerifyEmail.isValid(user_email)) {
            // 邮箱格式错误
            out.println("Fail! The email format is incorrect!");
            out.close();
            return;
        }

        if (userService.checkEmail(new User(user_email)) != null) {
            // 邮箱被注册
            out.println("Fail! The email address has been registered!");
            out.close();
            return;
        }

        Set<String> set = new HashSet<>();
        set.add(user_email);
        String code = VerifyCode.genVerifyCode(6);
        String key = "vc_" + user_email;
        Jedis redis = RedisPoolUtils.getJedis();
        if (redis == null || !redis.set(key, code).equals("OK")) {
            out.println("Fail! Server error!");
            out.close();
            return;
        }

        redis.expire(key, 180);     // 有效期 3 分钟
        MailUtils.sendEmail(set, "SSM_CloudLibrary 注册验证码","【SSM_CloudLibrary 云借阅图书管理系统】 验证码 "+code+" 用于 SSM_CloudLibrary 平台用户注册，3分钟内有效，请勿泄露和转发。如非本人操作，请忽略此邮件。");
        out.println("Success! Send Successfully!");
        redis.close();
        out.close();
    }
}
