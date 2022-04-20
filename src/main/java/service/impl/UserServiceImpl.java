package service.impl;

import model.User;
import repositories.UsersRepo;
import service.UserService;

import java.util.List;

public class UserServiceImpl extends BaseServiceImpl<User, UsersRepo> implements UserService {

    public UserServiceImpl(UsersRepo repository) {
        super(repository);
    }

    public User findByUsername(String username){
        return repository.readByUsername(username);
    }

    public List<User> searchUsername(String username){
        return repository.searchUsername(username);
    }

    @Override
    public void delete(User user) {
        repository.delete(user);
    }

    public void truncate() {
        repository.truncate();
    }
}
