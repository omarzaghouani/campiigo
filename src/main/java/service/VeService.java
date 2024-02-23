package service;
import entites.Vehicule;

import java.util.List;
public interface VeService<T> {

    void add(Vehicule v);
    void delete(Vehicule vehicule);
    void update(Vehicule vehicule);
    List<Vehicule> readAll();
    T readBynum_v(int num_v);
}
