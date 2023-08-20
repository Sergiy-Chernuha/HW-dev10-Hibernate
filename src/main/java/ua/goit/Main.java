package ua.goit;

import org.hibernate.Session;
import ua.goit.entyties.Client;
import ua.goit.entyties.Planet;
import ua.goit.entyties.Ticket;
import ua.goit.repos.impl.ClientService;
import ua.goit.repos.impl.PlanetService;
import ua.goit.repos.impl.TicketService;
import ua.goit.utils.FlywayUtils;
import ua.goit.utils.HibernateUtil;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        FlywayUtils.getInstance().doMigrations();
        ClientService clientService = new ClientService();
        PlanetService planetService = new PlanetService();
        TicketService ticketService = new TicketService();

        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Client sevedClient = new Client();
            sevedClient.setName("Ferdinant");
            clientService.save(session, sevedClient);

            Optional<Client> clientById = clientService.findById(session, String.valueOf(4L));
            System.out.println(clientById.get());

            List<Client> clientList = clientService.findAll(session);
            System.out.println(Arrays.asList(clientList));

            Optional<Client> updatedClient = clientService.findById(session, String.valueOf(4L));
            if (updatedClient.isPresent()) {
                updatedClient.get().setName("NewYuonger");
                clientService.update(session, updatedClient.get());
            }

            clientService.deleteById(session, String.valueOf(11L));

            Client newClient = new Client();
            newClient.setName("Fuming");
            clientService.save(session, newClient);
            Optional<Client> deletedClient = clientService.findById(session, String.valueOf(12L));
            if (deletedClient.isPresent()) {
                clientService.delete(session, deletedClient.get());
            }

            Planet sevedPlanet = new Planet();
            sevedPlanet.setId("QWERTY");
            sevedPlanet.setName("qwerty34563");
            planetService.save(session, sevedPlanet);

            Optional<Planet> planetById = planetService.findById(session, "QWERTY");
            System.out.println(planetById.get());

            List<Planet> planetList = planetService.findAll(session);
            System.out.println(Arrays.asList(planetList));

            Optional<Planet> updatedPlanet = planetService.findById(session, "QWERTY");
            if (updatedPlanet.isPresent()) {
                updatedPlanet.get().setName("NewYuonger");
                planetService.update(session, updatedPlanet.get());
            }

            planetService.deleteById(session, "QWERTY");

            Planet newPlanet = new Planet();
            newPlanet.setId("MIMI");
            newPlanet.setName("Fuming");
            planetService.save(session, newPlanet);
            Optional<Planet> deletedPlanet = planetService.findById(session, "MIMI");
            if (deletedPlanet.isPresent()) {
                planetService.delete(session, deletedPlanet.get());
            }

            Ticket savedTicket = new Ticket();
            Optional<Client> newSavedClient = clientService.findById(session, String.valueOf(4L));
            Optional<Planet> newSavedPlanet = planetService.findById(session, "QWERTY3");
            Optional<Planet> newSavedSecondPlanet = planetService.findById(session, "SUN23");
            savedTicket.setCreatedAt(ZonedDateTime.now());
            savedTicket.setClient(newSavedClient.get());
            savedTicket.setToPlanet(newSavedPlanet.get());
            savedTicket.setFromPlanet(newSavedSecondPlanet.get());
            ticketService.save(session, savedTicket);

            Optional<Ticket> findTicket = ticketService.findById(session, String.valueOf(5L));
            System.out.println(findTicket.get());

            List<Ticket> ticketList = ticketService.findAll(session);
            System.out.println(ticketList);

            Ticket updatedTicket = new Ticket();
            Optional<Client> newClientToTicket = clientService.findById(session, String.valueOf(2L));
            Optional<Planet> newPlanetToTicket = planetService.findById(session, "ROCKET");
            Optional<Planet> newSecondPlanetToTicket = planetService.findById(session, "ZOOM");
            updatedTicket.setId(1L);
            updatedTicket.setCreatedAt(ZonedDateTime.now());
            updatedTicket.setClient(newClientToTicket.get());
            updatedTicket.setToPlanet(newPlanetToTicket.get());
            updatedTicket.setFromPlanet(newSecondPlanetToTicket.get());
            ticketService.update(session, updatedTicket);

            ticketService.deleteById(session, String.valueOf(11L));

            Optional<Ticket> deletedTicket = ticketService.findById(session, String.valueOf(10L));
            ticketService.delete(session, deletedTicket.get());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
