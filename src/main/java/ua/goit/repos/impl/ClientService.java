package ua.goit.repos.impl;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import ua.goit.entyties.Client;
import ua.goit.repos.CRUDService;

import java.util.List;
import java.util.Optional;

public class ClientService implements CRUDService<Client> {
    //    __________________________
    @Override
    public void save(Session session, Client entity) {
        session.persist(entity);
    }

    @Override
    public Optional<Client> findById(Session session, Long id) {
        return Optional.ofNullable(session.get(Client.class, id));
    }

    @Override
    public List<Client> findAll(Session session) {
        return session.createQuery("from Client", Client.class).list();
    }

    @Override
    public void update(Session session, Client entity) {

    }

    @Override
    public void deleteById(Session session, Long id) {
        String queryString = "delete from Client where id =:delId";
        Transaction transaction = session.beginTransaction();

        try {
            NativeQuery<Client> nativeQuery = session.createNativeQuery(queryString, Client.class);
            nativeQuery.setParameter("delId", id);
            int i = nativeQuery.executeUpdate();
            transaction.commit();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Session session, Client entity) {
        session.remove(entity);
    }

    @Override
    public void deleteAll(Session session) {
        String queryString = "delete from Client";
//        EntityManager entityManager=session.getEntityManagerFactory().createEntityManager();
//        entityManager.remove();
        Transaction transaction = session.beginTransaction();

        try {
            NativeQuery<Client> nativeQuery = session.createNativeQuery(queryString, Client.class);

            int i = nativeQuery.executeUpdate();
            transaction.commit();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
