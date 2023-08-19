package ua.goit.repos.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.goit.entyties.Client;
import ua.goit.repos.CRUDService;

import java.util.List;
import java.util.Optional;

public class ClientService implements CRUDService<Client> {
    @Override
    public void save(Session session, Client entity) {
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
    }

    @Override
    public Optional<Client> findById(Session session, String id) {
        try {
            return Optional.ofNullable(session.get(Client.class,Long.parseLong(id)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public List<Client> findAll(Session session) {
        return session.createQuery("from Client", Client.class).list();
    }

    @Override
    public void update(Session session, Client entity) {
        try {
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
    public void deleteById(Session session, String id) {
        Optional<Client> client = findById(session, id);

        try {
            Transaction transaction = session.beginTransaction();
            session.remove(client.get());
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Session session, Client entity) {
        deleteById(session, String.valueOf(entity.getId()));
    }
}
