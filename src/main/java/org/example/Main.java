package org.example;

import entites.transport;
import service.transportService;
import utils.DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {



    public static void main(String[] args) {

        transport t1=new transport();

        transportService ps=new transportService();
        //  ps.add(p1);
        // ps.addPst(p1);

        ps.readAll().forEach(System.out::println);
    }
}