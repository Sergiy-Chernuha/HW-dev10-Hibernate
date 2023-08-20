package ua.goit.repos.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.goit.entyties.Client;
import ua.goit.repos.CRUDService;
import ua.goit.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientService implements CRUDService<Client> {
    @Override
    public void save(Client entity) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Optional<Client> findById(String id) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(Client.class, Long.parseLong(id)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public List<Client> findAll() {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            return session.createQuery("from Client", Client.class).list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public void update(Client entity) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery("UPDATE Client SET name =:name WHERE id =:client_id", Client.class)
                    .setParameter("name", entity.getName())
                    .setParameter("client_id", entity.getId())
                    .executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteById(String id) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Optional<Client> client = findById(id);
            Transaction transaction = session.beginTransaction();
            session.remove(client.get());
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Client entity) {
        deleteById(String.valueOf(entity.getId()));
    }
}
