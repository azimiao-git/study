package top.zl.v1.service.impl;

import top.zl.annotation.Service;
import top.zl.v1.dto.UserDto;
import top.zl.v1.service.UserService;

/**
 * @author zl
 * 2021/08/23
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public String add(UserDto dto) {
        return null;
    }

    @Override
    public String query(String name) {
        return "query:"+name;
    }
}
