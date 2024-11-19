package com.to404hanga.controller;

import com.to404hanga.domain.User;
import com.to404hanga.service.UserService;
import com.to404hanga.utils.SHA256Utils;
import com.to404hanga.utils.RedisPoolUtils;
import com.to404hanga.utils.ResponseWriter;
import com.to404hanga.utils.VerifyEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/toMainPage")
    public String toMainPage() {
        return "main";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/admin/login.jsp";
    }

    @RequestMapping("/login")
    public void login(User user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (user.getPassword().isEmpty() || user.getEmail().isEmpty()) {
            // 信息为空
            ResponseWriter.alertAndRelocation(response, "Fail! The message cannot be empty!", "/admin/login.jsp");
            return;
        }

        if (!VerifyEmail.isValid(user.getEmail())) {
            // 邮箱格式错误
            ResponseWriter.alertAndRelocation(response, "Fail! The email format is incorrect!", "/admin/login.jsp");
            return;
        }

        String hashedPwd = SHA256Utils.encrypt(user.getPassword());
        if (hashedPwd.isEmpty()) {
            // 加密失败，返回服务器错误
            ResponseWriter.alertAndRelocation(response, "Fail! Server Error!", "/admin/login.jsp");
            return;
        }

        // 发起登陆验证
        User dbUser = userService.login(new User(hashedPwd, user.getEmail()));
        if (dbUser == null) {
            // 登陆失败
            ResponseWriter.alertAndRelocation(response, "Fail! The email or password is incorrect!", "/admin/login.jsp");
            return;
        }
        // 登陆成功
        request.getSession().setAttribute("USER_SESSION", dbUser);
        ResponseWriter.alertAndRelocation(response, "Success! Welcome to SSM_Library! " + user.getEmail(), "/admin/main.jsp");
    }

    @RequestMapping("/register")
    public void addUser(String user_name, String user_password, String user_email, String user_role, String vc, HttpServletResponse response) throws IOException {
        Integer num = -1;

        if (user_name == null || user_password == null || user_email == null || user_role == null || vc == null
                || user_name.isEmpty() || user_password.isEmpty() || user_email.isEmpty() || user_role.isEmpty() || vc.isEmpty()) {
            // 信息为空
            ResponseWriter.alertAndRelocation(response, "Fail! The message cannot be empty!", "/admin/register.jsp");
            return;
        }

        if (!VerifyEmail.isValid(user_email)) {
            // 邮箱格式错误
            ResponseWriter.alertAndRelocation(response, "Fail! The email format is incorrect!", "/admin/register.jsp");
            return;
        }

        if (userService.checkEmail(new User(user_email)) != null) {
            // 邮箱被注册
            ResponseWriter.alertAndRelocation(response, "Fail! The email address has been registered!", "/admin/register.jsp");
            return;
        }

        String key = "vc_" + user_email;
        Jedis redis = RedisPoolUtils.getJedis();
        if (redis != null) {
            if (!redis.exists(key) || !redis.get(key).equals(vc)) {
                // 验证码不存在或验证码错误
                ResponseWriter.alertAndRelocation(response, "Fail! Verification code error!", "/admin/register.jsp");
                redis.close();
                return;
            }

            String hashedPwd = SHA256Utils.encrypt(user_password);
            if (hashedPwd.isEmpty()) {
                // 加密失败，返回服务器错误
                ResponseWriter.alertAndRelocation(response, "Fail! Server Error!", "/admin/register.jsp");
                return;
            }

            num = userService.addUser(user_name, hashedPwd, user_email, user_role);
            if (num > 0) {
                // 添加成功，删除验证码
                redis.del(key);
                ResponseWriter.alertAndRelocation(response, "Success!", "/admin/login.jsp");
            } else {
                // 添加失败，不删除验证码，有效期内可使用原验证码进行注册
                ResponseWriter.alertAndRelocation(response, "Failed!", "/admin/register.jsp");
            }
            redis.close();
        }
    }
}
