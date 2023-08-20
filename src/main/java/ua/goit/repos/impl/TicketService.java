package ua.goit.repos.impl;

import jakarta.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.goit.entyties.Client;
import ua.goit.entyties.Ticket;
import ua.goit.repos.CRUDService;
import ua.goit.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketService implements CRUDService<Ticket> {
    @Override
    public void save(Ticket entity) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (PersistenceException ex) {
            System.out.println("Can't save ticked with NULL field");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Optional<Ticket> findById(String id) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(Ticket.class, Long.parseLong(id)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public List<Ticket> findAll() {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            return session.createQuery("from Ticket", Ticket.class).list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public void update(Ticket entity) {
        String sql = "UPDATE Ticket SET CREATED_AT =:new_created_at, CLIENT_ID =:new_client_id" +
                ", FROM_PLANET_ID =:new_from_planet_id" +
                ", TO_PLANET_ID =:new_to_planet_id " +
                "WHERE id =:ticket_id";

        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
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
    public void deleteById(String id) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Optional<Ticket> ticket = findById(id);
            Transaction transaction = session.beginTransaction();
            session.remove(ticket.get());
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Ticket entity) {
        deleteById(String.valueOf(entity.getId()));
    }
}
