package service;

import model.User;

import java.util.List;

public interface UserService extends BaseService<User>{
    User findByUsername(String username);

    List<User> searchUsername(String username);
}
