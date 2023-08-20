package ua.goit.repos.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.goit.entyties.Planet;
import ua.goit.repos.CRUDService;
import ua.goit.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlanetService implements CRUDService<Planet> {
    @Override
    public void save(Planet entity) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Optional<Planet> findById(String id) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(Planet.class, id));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public List<Planet> findAll() {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            return session.createQuery("from Planet", Planet.class).list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public void update(Planet entity) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
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
    public void deleteById(String id) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Optional<Planet> planet = findById(id);
            Transaction transaction = session.beginTransaction();
            session.remove(planet.get());
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Planet entity) {
             deleteById(entity.getId());
    }
}
