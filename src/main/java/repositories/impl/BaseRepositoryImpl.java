package repositories.impl;

import lombok.Getter;
import org.hibernate.SessionFactory;
import repositories.BaseRepository;
import repositories.SessionFactorySingleton;

@Getter
public abstract class BaseRepositoryImpl<T> implements BaseRepository<T> {
    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    public BaseRepositoryImpl() {
    }

    public BaseRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public T ins(T t) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.persist(t);
                transaction.commit();
                return t;
            } catch (Exception e) {
                e.printStackTrace();
                transaction.rollback();
                return null;
            }
        }
    }

    @Override
    public T update(T t) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.merge(t);
                transaction.commit();
                return t;
            } catch (Exception e) {
                e.printStackTrace();
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void delete(T t) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.remove(t);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                transaction.rollback();
            }
        }
    }
}
