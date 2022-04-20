package repositories;

import java.util.List;

public interface BaseRepository<T>{
    T ins(T t);

    T readById(Integer id);
    List<T> readAll();

    T update(T t);

    void delete(T t);

    void truncate();
}
