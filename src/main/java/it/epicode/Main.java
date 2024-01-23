package it.epicode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

public class Main {
    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GestioneEventi");

    public static void main(String[] args) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        EventoDaoImpl eventoDAO = new EventoDaoImpl(entityManager);
        Evento evento = new Evento();
        evento.setTitolo("Secondo Test");
        evento.setDescrizione("It Just Works");
        evento.setDataEvento(LocalDate.now());
        evento.setTipoEvento(TipoEvento.PRIVATO);
        evento.setNumeroMassimoPartecipanti(50);
        eventoDAO.save(evento);

        Evento found = eventoDAO.findById(1L);
        if (found != null)
            System.out.println(found);
        else System.out.println("Elemento non trovato");

        eventoDAO.delete(0L);

        entityManager.persist(evento);

        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();

        TypedQuery<Evento> query = entityManager.createQuery("SELECT e FROM Evento e", Evento.class);
        List<Evento> eventi = query.getResultList();

        for (Evento ev : eventi) {
            System.out.println(ev.getTitolo());
        }

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
