package com.zy.controller.admin;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
/**
 * 管理员登录控制
 */
@RequestMapping("/admin")
public class AdminLogin {

    @RequestMapping("/")
    public String ApplyLogin() {
        return "adminLogin";
    }

    /**
     * 管理员登录，使用Shiro进行验证
     *  验证成功后重定向到lolManage.html
     * @param username
     * @param password
     * @param model
     * @param httpSession
     * @return
     */
    @PostMapping("/login")
    public String ApplyLogin(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             Model model, HttpSession httpSession) {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            subject.login(token);
            httpSession.setAttribute("loginAdmin", username);

            return "redirect:/manageLol";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "用户名不存在");
            return "index";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码错误");
            return "index";
        }

    }

    /**
     * 登出操作
     * @return
     */
    @RequestMapping("/logout")
    public String loginOut(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/lol";
    }

}
