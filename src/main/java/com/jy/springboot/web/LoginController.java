package com.jy.springboot.web;

import com.jy.springboot.domain.User;
import com.jy.springboot.domain.UserRepository;
import com.jy.springboot.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by limi on 2017/8/22.
 */
@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * 跳转到注册页面
     * @param model
     * @return
     */
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "register";
    }

    /**
     * 登陆页面跳转
     * @return
     */
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


    @PostMapping("/login")
    public String loginPost(@RequestParam String username,
                            @RequestParam String password,
                            HttpSession session) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            session.setAttribute("user",user);
            return "index";
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "login";
    }



    /**
     * 注册提交
     * @param userForm
     * @param result
     * @param model
     * @return
     */
    @PostMapping("/register")
    public String register(@Valid UserForm userForm, BindingResult result, Model model) {
        if (!userForm.confirmPassword()) {
            result.rejectValue("confirmPassword","confirmError","两次密码不一致");
        }
        if (result.hasErrors()) {
            return "register";
        }
        User user = userForm.convertToUser();
        userRepository.save(user);
        return "redirect:/login";
    }


    @GetMapping("/exception")
    public String testException() {
        throw new RuntimeException("测试异常处理");
    }


}
