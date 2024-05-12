package services;

import entities.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface générique pour les opérations de base sur une entité.
 *
 * @param <T> Type de l'entité.
 */
public interface Iservices<T> {

    /**
     * Ajoute une entité à la source de données.
     *
     * @param t L'entité à ajouter.
     * @throws SQLException Si une erreur SQL survient.
     */
    void ajouter(T t) throws SQLException;

    /**
     * Modifie une entité dans la source de données.
     *
     * @param t L'entité à modifier.
     * @throws SQLException Si une erreur SQL survient.
     */
    void modifier(T t) throws SQLException;

    /**
     * Supprime une entité de la source de données.
     *
     * @param t L'entité à supprimer.
     * @throws SQLException Si une erreur SQL survient.
     */
    void supprimer(T t) throws SQLException;

    /**
     * Récupère la liste de toutes les entités dans la source de données.
     *
     * @return Liste d'entités.
     * @throws SQLException Si une erreur SQL survient.
     */
    List<T> afficher() throws SQLException;

    /**
     * Récupère l'ID de l'entité donnée.
     *
     * @param t L'entité pour laquelle récupérer l'ID.
     * @return L'ID de l'entité.
     */
    int getId(T t);

    /**
     * Récupère l'ID à partir d'un objet générique.
     *
     * @param o L'objet pour lequel récupérer l'ID.
     * @return L'ID de l'objet, ou 0 si l'objet n'est pas de type T.
     */
    int getIdFromObject(Object o);
    User authentifier(String mail, String mdp);
}
