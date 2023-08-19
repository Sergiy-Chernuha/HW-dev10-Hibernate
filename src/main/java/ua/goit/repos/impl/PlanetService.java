package ua.goit.repos.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.goit.entyties.Planet;
import ua.goit.repos.CRUDService;

import java.util.List;
import java.util.Optional;

public class PlanetService implements CRUDService<Planet> {
    @Override
    public void save(Session session, Planet entity) {
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
    }

    @Override
    public Optional<Planet> findById(Session session, String id) {
        try {
            return Optional.ofNullable(session.get(Planet.class, id));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public List<Planet> findAll(Session session) {
        return session.createQuery("from Planet", Planet.class).list();
    }

    @Override
    public void update(Session session, Planet entity) {
        try {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery("UPDATE Planet SET name =:name WHERE id =:planet_id", Planet.class)
                    .setParameter("name", entity.getName())
                    .setParameter("planet_id", entity.getId())
                    .executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteById(Session session, String id) {
        Optional<Planet> planet = findById(session, id);

        try {
            Transaction transaction = session.beginTransaction();
            session.remove(planet.get());
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Session session, Planet entity) {
        deleteById(session, entity.getId());
    }
}
