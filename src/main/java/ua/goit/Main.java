package ua.goit;

import org.hibernate.Session;
import ua.goit.entyties.Client;

import ua.goit.entyties.Planet;
import ua.goit.repos.impl.ClientService;
import ua.goit.utils.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class Main {
    //    private static final Logger LOGGER= LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
//        BasicConfigurator.configure();
//        FlywayUtils.getInstance().doMigrations();
        ClientService clientService = new ClientService();
//        List<Client> allClients=null;
//        List<Planet> allPlanets=new ArrayList<>();

        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
//            clientService.deleteById(session, 9L);
//            clientService.deleteAll(session);
//           Transaction transaction = session.beginTransaction();

//           PlanetService planetService= new PlanetService();
//           session.persist(p);

//           Planet planet = session.get(Planet.class, "QWERTY3");
//           transaction.commit();
//           allClients = clientService.findAll(session);
//           allPlanets = planetService.findAll(session);

           Optional<Client> byId = clientService.findById(session, 4l);
            clientService.delete(session,byId.get());

//           System.out.println( byId.get());
//           System.out.println(allClients);
//           System.out.println(+ planet);
        } catch (RuntimeException e) {
//           LOGGER.error("It's wrong");
        }
    }
}
