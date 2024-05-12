package services;

import entities.Transport;


import java.util.List;

public interface IoService <T>{

    boolean add(Transport  t);
    void delete(Transport  t);

    void update(Transport  t);

    List<Transport> readAll();

    T readBynum_ch(int num_ch);
   // Transport rechercherParNumCh(int num_ch);

    void selectVehiclesForTransport(int num_ch);


}
