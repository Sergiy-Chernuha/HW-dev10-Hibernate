package ua.goit.repos;

import java.util.List;
import java.util.Optional;

public interface CRUDService<T>{
    void save(T entity);

    Optional<T> findById(String id);

    List<T> findAll();

    void update(T entity);

    void deleteById(String id);

    void delete(T entity);
}
