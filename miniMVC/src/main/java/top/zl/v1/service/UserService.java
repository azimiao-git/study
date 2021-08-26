package top.zl.v1.service;

import top.zl.v1.dto.UserDto;

/**
 * @author zl
 * 2021/08/23
 */
public interface UserService {


    String add(UserDto dto);

    String query(String name);
}
