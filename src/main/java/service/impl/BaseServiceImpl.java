package service.impl;
import repositories.BaseRepository;
import service.BaseService;

import java.util.List;

public abstract class BaseServiceImpl<T
        , R extends BaseRepository<T>>
        implements BaseService<T> {
    R repository;

    protected BaseServiceImpl(R repository) {
        this.repository = repository;
    }

    public T insert(T t) {
        return repository.ins(t);
    }

    public T findById(Integer id) {
        return repository.readById(id);
    }

    public List<T> findAll() {
        return repository.readAll();
    }

    public T update(T t) {
        return repository.update(t);
    }

    public void delete(T t) {
        repository.delete(t);
    }

    public void truncate() {
        repository.truncate();
    }
}
