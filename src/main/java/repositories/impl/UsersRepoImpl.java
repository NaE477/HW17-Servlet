package repositories.impl;


import model.User;
import org.hibernate.SessionFactory;
import repositories.UsersRepo;

import java.util.ArrayList;
import java.util.List;

public class UsersRepoImpl extends BaseRepositoryImpl<User> implements UsersRepo {
    public UsersRepoImpl() {
        super();
    }

    public UsersRepoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public User readById(Integer id) {
        try (var session = super.getSessionFactory().openSession()) {
            try {
                return session.get(User.class, id);
            } catch (Exception e) {
                return null;
            }
        }
    }

    public User readByUsername(String username) {
        try (var session = super.getSessionFactory().openSession()) {
            try {
                return session
                        .createQuery("from User u where u.username = :username", User.class)
                        .setParameter("username", username)
                        .getSingleResult();
            } catch (Exception e) {
                return null;
            }
        }
    }

    public List<User> searchUsername(String username) {
        try (var session = super.getSessionFactory().openSession()) {
            try {
                return session
                        .createQuery("from User u where u.username like :username", User.class)
                        .setParameter("username", "%" + username + "%")
                        .list();
            } catch (Exception e) {
                return new ArrayList<>();
            }
        }
    }

    public List<User> readAll() {
        try (var session = super.getSessionFactory().openSession()) {
            try {
                return session
                        .createQuery("from User", User.class)
                        .list();
            } catch (Exception e) {
                return new ArrayList<>();
            }
        }
    }

    public void truncate() {
        try (var session = super.getSessionFactory().openSession()) {
            var transaction = session.beginTransaction();
            try {
                String truncateStmt = "truncate users cascade;";
                session.createNativeQuery(truncateStmt,User.class).executeUpdate();
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                transaction.rollback();
            }
        }
    }
}
