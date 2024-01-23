package it.epicode;

import javax.persistence.*;

public class EventoDaoImpl implements EventoDao {

    private final EntityManager em;

    public EventoDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Evento evento) {
        em.persist(evento);
    }

    @Override
    public void delete(Long id) {
        Evento evento = findById(id);
        em.remove(evento);
    }

    @Override
    public Evento findById(Long id) {
        return em.find(Evento.class, id);
    }
}
