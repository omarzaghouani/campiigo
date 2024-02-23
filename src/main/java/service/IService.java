package service;

import entites.transport;

import java.util.List;

public interface IService <T>{

    void add(T t);
    void delete(T t);

    void update(T t);

    List<transport> readAll();

    T readById(int id);
}
