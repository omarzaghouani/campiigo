package org.example;

import entites.Transport;

import entites.Transpoteur;
import entites.Vehicule;


import service.TransportService;

import service.TranspoteurService;
import service.VehiculeService;


import java.time.LocalDate;
import java.util.List;

public class Main {


    public static void main(String[] args) {

      Transport t = new Transport(78,
              LocalDate.of(2024,6,19),  LocalDate.of(2025,9,6), 9,15);

        TransportService ps = new TransportService() {

        };
        //ps.add(t);

        // ps.delete(t);
      // ps.update(t);
        /* int num_chToSearch = 78; // Example num_ch to search for
              Transport rechercherParNumCh = ps.rechercherParNumCh(num_chToSearch);*/
         ps.readAll().forEach(System.out::println);


        Transpoteur t2=new Transpoteur(58,"omar","zaghouani", 26834008, "omar.zaghouani@esprit.tn", LocalDate.of(2024,9,6)
                ,88);
TranspoteurService psr= new TranspoteurService(){
};
   //psr.addt(t2);
    //psr.deletet(t2);
     //  psr.updatet(t2);
    psr.readAll().forEach(System.out::println);

        Vehicule v=new Vehicule(122,"van familiale",7, 150, 148);
       VehiculeService psv= new VehiculeService(){

        };

//psv.add(v);
       // psv.delete(v);
    //   psv.update(v);
      //psv.readAll().forEach(System.out::println);


    }



}