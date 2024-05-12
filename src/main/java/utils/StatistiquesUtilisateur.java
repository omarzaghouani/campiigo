package utils;

import entities.User;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public  class StatistiquesUtilisateur {

    public static Map<String, Integer> calculerStatistiquesUtilisateurs(ObservableList<User> utilisateurs) {
        Map<String, Integer> statistiques = new HashMap<>();
        for (User utilisateur : utilisateurs) {
            String role = utilisateur.getRoles();
            statistiques.put(role, statistiques.getOrDefault(role, 0) + 1);
        }
        return statistiques;
    }


    public static Map<String, Double> calculerStatistiquesUtilisateursAvecPourcentage(List<User> utilisateurs) {
        Map<String, Integer> comptageRoles = calculerStatistiquesUtilisateurs((ObservableList<User>) utilisateurs);
        Map<String, Double> pourcentagesRoles = new HashMap<>();

        int totalUtilisateurs = utilisateurs.size();
        for (Map.Entry<String, Integer> entry : comptageRoles.entrySet()) {
            double pourcentage = (double) entry.getValue() / totalUtilisateurs * 100;
            pourcentagesRoles.put(entry.getKey(), pourcentage);
        }

        return pourcentagesRoles;
    }
}

