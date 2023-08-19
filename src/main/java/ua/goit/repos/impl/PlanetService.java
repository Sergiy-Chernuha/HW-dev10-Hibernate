package ua.goit.repos.impl;

import org.hibernate.Session;
import ua.goit.entyties.Client;
import ua.goit.entyties.Planet;
import ua.goit.repos.CRUDService;

import java.util.List;
import java.util.Optional;

public class PlanetService implements CRUDService<Planet> {
    @Override
    public void save(Session session, Planet entity) {

    }

    @Override
    public Optional<Planet> findById(Session session, Long id) {
        Optional<Planet> client;
        try {
            Planet planet = session.get(Planet.class, id);
            System.out.println("_________________________");
            System.out.println(planet);
            System.out.println("_________________________");
            return null;
        }catch (Exception e){
            System.out.println("NOT A ID "+ e.toString());
        }
        return Optional.empty();
    }

    @Override
    public List<Planet> findAll(Session session) {
        List<Planet> selectDevFromClientDev=null;
try {
//    selectDevFromClientDev = session.createQuery("from Client", Client.class).list();
    selectDevFromClientDev = session.createQuery("from Planet p", Planet.class).list();
}catch (Exception e){
    System.out.println("3555555555555"+ e.getMessage());
}
        return selectDevFromClientDev;
    }

//    List<Person> persons = session.createQuery("from Person p WHERE p.id = 1", Person.class).list();

    @Override
    public void update(Session session, Planet entity) {

    }

    @Override
    public void deleteById(Session session, Long id) {

    }

    @Override
    public void delete(Session session, Planet entity) {

    }

    @Override
    public void deleteAll(Session session) {

    }
}
