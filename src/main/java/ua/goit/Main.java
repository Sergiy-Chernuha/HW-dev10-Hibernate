package ua.goit;

import ua.goit.entyties.Client;
import ua.goit.entyties.Planet;
import ua.goit.entyties.Ticket;
import ua.goit.repos.impl.ClientService;
import ua.goit.repos.impl.PlanetService;
import ua.goit.repos.impl.TicketService;
import ua.goit.utils.FlywayUtils;

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

        Client sevedClient = new Client();
        sevedClient.setName("Ferdinant");
        clientService.save(sevedClient);

        Optional<Client> clientById = clientService.findById(String.valueOf(4L));
        System.out.println(clientById.get() + "\n");

        List<Client> clientList = clientService.findAll();
        System.out.println(clientList + "\n");

        Optional<Client> updatedClient = clientService.findById(String.valueOf(4L));
        if (updatedClient.isPresent()) {
            updatedClient.get().setName("NewYuonger");
            clientService.update(updatedClient.get());
        }

        clientService.deleteById(String.valueOf(11L));

        Client newClient = new Client();
        newClient.setName("Fuming");
        clientService.save(newClient);
        Optional<Client> deletedClient = clientService.findById(String.valueOf(12L));
        if (deletedClient.isPresent()) {
            clientService.delete(deletedClient.get());
        }

        Planet sevedPlanet = new Planet();
        sevedPlanet.setId("QWERTY");
        sevedPlanet.setName("qwerty34563");
        planetService.save(sevedPlanet);

        Optional<Planet> planetById = planetService.findById("QWERTY");
        System.out.println(planetById.get() + "\n");

        List<Planet> planetList = planetService.findAll();
        System.out.println(Arrays.asList(planetList) + "\n");

        Optional<Planet> updatedPlanet = planetService.findById("QWERTY3");
        if (updatedPlanet.isPresent()) {
            updatedPlanet.get().setName("NewYuonger");
            planetService.update(updatedPlanet.get());
        }

        planetService.deleteById("QWERTY");

        Planet newPlanet = new Planet();
        newPlanet.setId("MIMI");
        newPlanet.setName("Fuming");
        planetService.save(newPlanet);
        Optional<Planet> deletedPlanet = planetService.findById("MIMI");
        if (deletedPlanet.isPresent()) {
            planetService.delete(deletedPlanet.get());
        }


        Ticket savedTicket = new Ticket();
//            check incorrect save
        ticketService.save(savedTicket);

        Optional<Client> newSavedClient = clientService.findById(String.valueOf(4L));
        Optional<Planet> newSavedPlanet = planetService.findById("QWERTY3");
        Optional<Planet> newSavedSecondPlanet = planetService.findById("SUN23");
        savedTicket.setCreatedAt(ZonedDateTime.now());
        savedTicket.setClient(null);
//            check incorrect save
        ticketService.save(savedTicket);

        savedTicket.setClient(newSavedClient.get());
        savedTicket.setToPlanet(newSavedPlanet.get());
        savedTicket.setFromPlanet(null);
//            check incorrect save
        ticketService.save(savedTicket);

        savedTicket.setFromPlanet(newSavedSecondPlanet.get());
//            check correct save
        ticketService.save(savedTicket);

        Optional<Ticket> findTicket = ticketService.findById(String.valueOf(5L));
        System.out.println(findTicket.get() + "\n");

        List<Ticket> ticketList = ticketService.findAll();
        System.out.println(ticketList + "\n");

        Ticket updatedTicket = new Ticket();
        Optional<Client> newClientToTicket = clientService.findById(String.valueOf(2L));
        Optional<Planet> newPlanetToTicket = planetService.findById("ROCKET");
        Optional<Planet> newSecondPlanetToTicket = planetService.findById("ZOOM");
        updatedTicket.setId(1L);
        updatedTicket.setCreatedAt(ZonedDateTime.now());
        updatedTicket.setClient(newClientToTicket.get());
        updatedTicket.setToPlanet(newPlanetToTicket.get());
        updatedTicket.setFromPlanet(newSecondPlanetToTicket.get());
        ticketService.update(updatedTicket);

        ticketService.deleteById(String.valueOf(11L));

        Optional<Ticket> deletedTicket = ticketService.findById(String.valueOf(10L));
        ticketService.delete(deletedTicket.get());
    }
}
