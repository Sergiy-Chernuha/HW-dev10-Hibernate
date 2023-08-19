package ua.goit.repos.impl;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import ua.goit.entyties.Client;
import ua.goit.entyties.Ticket;
import ua.goit.repos.CRUDService;
import ua.goit.utils.HibernateUtil;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ClientService implements CRUDService<Client> {
    //    __________________________
    @Override
    public void save(Session session, Client entity) {
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
    }

    @Override
    public Optional<Client> findById(Session session, Long id) {
        session.close();
        try (Session session2 = HibernateUtil.getInstance().getSessionFactory().openSession()){

            return Optional.ofNullable(session2.get(Client.class, id));
        }catch (Exception e){
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

    }

    @Override
    public void deleteById(Session session, Long id) {

        System.out.println("WWWTTTT");
        Optional<Client> client = findById(session,id);
        System.out.println(client.get().getTicket());
        session.remove(client.get());
        Set<Ticket> ticket = client.get().getTicket();
        System.out.println(ticket);
        try {
//            for (Ticket ticket1 : ticket) {
//                System.out.println("!!1111!!!!!!!");
//                session.remove(ticket1);
//                System.out.println("!!122222!!!!!!!");
//            }
            session.remove(client.get());
            System.out.println("!!33333!!!!!");
        }catch (Exception e){
            System.out.println("!!!!!!!!!!!!!!!"+ e.getMessage());
        }



//        String queryString = "delete from Client where id =:delId";
//        Transaction transaction = session.beginTransaction();
//
//        try {
//            NativeQuery<Client> nativeQuery = session.createNativeQuery(queryString, Client.class);
//            nativeQuery.setParameter("delId", id);
//            int i = nativeQuery.executeUpdate();
//            transaction.commit();
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//        }
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
