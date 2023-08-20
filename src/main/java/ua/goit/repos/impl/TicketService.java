package ua.goit.repos.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.goit.entyties.Client;
import ua.goit.entyties.Ticket;
import ua.goit.repos.CRUDService;

import java.util.List;
import java.util.Optional;

public class TicketService implements CRUDService<Ticket> {
    @Override
    public void save(Session session, Ticket entity) {
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
    }

    @Override
    public Optional<Ticket> findById(Session session, String id) {
        try {
            return Optional.ofNullable(session.get(Ticket.class, Long.parseLong(id)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public List<Ticket> findAll(Session session) {
        return session.createQuery("from Ticket", Ticket.class).list();
    }

    @Override
    public void update(Session session, Ticket entity) {
        String sql="UPDATE Ticket SET CREATED_AT =:new_created_at, CLIENT_ID =:new_client_id" +
                ", FROM_PLANET_ID =:new_from_planet_id" +
                ", TO_PLANET_ID =:new_to_planet_id " +
                "WHERE id =:ticket_id";

        try {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery(sql, Client.class)
                    .setParameter("new_created_at", entity.getCreatedAt())
                    .setParameter("new_client_id", entity.getClient().getId())
                    .setParameter("new_from_planet_id", entity.getFromPlanet().getId())
                    .setParameter("new_to_planet_id", entity.getToPlanet().getId())
                    .setParameter("ticket_id", entity.getId())
                    .executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteById(Session session, String id) {
        Optional<Ticket> ticket = findById(session, id);

        try {
            Transaction transaction = session.beginTransaction();
            session.remove(ticket.get());
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Session session, Ticket entity) {
        deleteById(session, String.valueOf(entity.getId()));
    }
}
