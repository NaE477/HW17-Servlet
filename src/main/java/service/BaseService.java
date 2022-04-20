package service;

import java.util.List;

public interface BaseService<T> {
    T insert(T t);

    T findById(Integer id);

    List<T> findAll();

    T update(T t);

    void delete(T t);

    void truncate();
}
