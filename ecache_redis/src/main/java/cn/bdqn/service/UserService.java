package cn.bdqn.service;

import cn.bdqn.domain.User;

import java.util.List;

public interface UserService {
    int addUser(User user);

    List<User> findAllUser(int pageNum, int pageSize);

    User find(Integer id);
}
