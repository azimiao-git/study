package top.zl.v1.controller;

import top.zl.annotation.AutoWired;
import top.zl.annotation.Controller;
import top.zl.annotation.RequestMapping;
import top.zl.v1.service.UserService;

import javax.servlet.http.HttpServletResponse;

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
    public String detail(String name, HttpServletResponse response, Long age) {
        return userService.query(name)+"-->"+age;
    }

}
