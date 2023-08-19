package ua.goit.repos;

import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface CRUDService<T>{
    void save(Session session,T entity);

    Optional<T> findById(Session session,Long id);

    List<T> findAll(Session session);

    void update(Session session,T entity);

    void deleteById(Session session,Long id);

    void delete(Session session,T entity);

    void deleteAll(Session session);
}
