package services;

import entities.Transpoteur;


import java.util.List;

public interface itService<T> {




        boolean addt(Transpoteur tr);
        void deletet(Transpoteur tr);
        void updatet(Transpoteur tr);



    List<Transpoteur> readAll();

    T readBynum_ch(int num_ch);
}
