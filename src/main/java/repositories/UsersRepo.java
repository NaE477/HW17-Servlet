package repositories;

import model.User;

import java.util.List;

public interface UsersRepo extends BaseRepository<User> {
    User readByUsername(String username);
    List<User> searchUsername(String username);
}
