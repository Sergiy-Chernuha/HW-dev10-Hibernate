package ua.goit;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import ua.goit.entyties.Client;

import ua.goit.entyties.Planet;
import ua.goit.entyties.Ticket;
import ua.goit.repos.impl.ClientService;
import ua.goit.utils.FlywayUtils;
import ua.goit.utils.HibernateUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    //    private static final Logger LOGGER= LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
//        BasicConfigurator.configure();
        FlywayUtils.getInstance().doMigrations();



        ClientService clientService = new ClientService();
//        List<Client> allClients=null;
//        List<Planet> allPlanets=new ArrayList<>();

        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
//            EntityManager entityManager=session.getEntityManagerFactory().createEntityManager();
//            Client client=new Client();
//            client.setName("Fertdfg");
//            try {
//                entityManager.merge(client);
//                System.out.println(client);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            _____________ready_____________
//            Client client=new Client();
//            client.setName("Fertdfg");
//            clientService.save(session,client);
//            ____________ready_______________
            Optional<Client> clientById = clientService.findById(session, 4L);
            System.out.println(clientById.get());
//            ______________ready______________
//            List<Client> clientList = clientService.findAll(session);
//            System.out.println(Arrays.asList(clientList));
//            _______________________________
//            __________________________
//             clientService.deleteById(session, 9L);

//            clientService.deleteAll(session);
//           Transaction transaction = session.beginTransaction();

//           PlanetService planetService= new PlanetService();
//           session.persist(p);

//           Planet planet = session.get(Planet.class, "QWERTY3");
//           transaction.commit();
//           allClients = clientService.findAll(session);
//           allPlanets = planetService.findAll(session);

//           Optional<Client> byId = clientService.findById(session, 4l);
//            System.out.println( byId.get());
//            clientService.delete(session,byId.get());


//           System.out.println(allClients);
//           System.out.println(+ planet);
        } catch (RuntimeException e) {
//           LOGGER.error("It's wrong");
        }
    }
}
