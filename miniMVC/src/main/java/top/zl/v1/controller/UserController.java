package top.zl.v1.controller;

import top.zl.annotation.AutoWired;
import top.zl.annotation.Controller;
import top.zl.annotation.RequestMapping;
import top.zl.v1.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zl
 * 2021/08/24
 */
@Controller("userCon")
@RequestMapping("/user")
public class UserController {

    @AutoWired
    private UserService userService;

    @RequestMapping("detail")
    public void detail(String name, HttpServletResponse response){
        try {
            response.getWriter().write(userService.query(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
