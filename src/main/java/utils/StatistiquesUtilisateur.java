package utils;

import entities.UserRole;
import entities.utilisateur;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public  class StatistiquesUtilisateur {

    public static Map<UserRole, Integer> calculerStatistiquesUtilisateurs(ObservableList<utilisateur> utilisateurs) {
        Map<UserRole, Integer> statistiques = new HashMap<>();
        for (utilisateur utilisateur : utilisateurs) {
            UserRole role = utilisateur.getRole();
            statistiques.put(role, statistiques.getOrDefault(role, 0) + 1);
        }
        return statistiques;
    }


    public static Map<UserRole, Double> calculerStatistiquesUtilisateursAvecPourcentage(List<utilisateur> utilisateurs) {
        Map<UserRole, Integer> comptageRoles = calculerStatistiquesUtilisateurs((ObservableList<utilisateur>) utilisateurs);
        Map<UserRole, Double> pourcentagesRoles = new HashMap<>();

        int totalUtilisateurs = utilisateurs.size();
        for (Map.Entry<UserRole, Integer> entry : comptageRoles.entrySet()) {
            double pourcentage = (double) entry.getValue() / totalUtilisateurs * 100;
            pourcentagesRoles.put(entry.getKey(), pourcentage);
        }

        return pourcentagesRoles;
    }
}

