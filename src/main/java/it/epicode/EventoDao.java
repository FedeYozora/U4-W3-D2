package it.epicode;

public interface EventoDao {

    void save(Evento evento);

    void delete(Long id);

    Evento findById(Long id);
}
