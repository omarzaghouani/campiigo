package controller;

import entities.User;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import utils.StatistiquesUtilisateur;

import java.util.Map;

public class StatistiquesFenetre extends Application {

    public static void afficherStatistiques(ObservableList<User> utilisateurs) {
        StatistiquesFenetre fenetre = new StatistiquesFenetre(utilisateurs);
        fenetre.start(new Stage());
    }

    private ObservableList<User> utilisateurs;

    public StatistiquesFenetre(ObservableList<User> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    @Override
    public void start(Stage primaryStage) {
        PieChart pieChart = new PieChart();
        // Logique pour ajouter des données au graphique en utilisant la liste des utilisateurs
        // Assurez-vous que la méthode calculerStatistiquesUtilisateurs accepte ObservableList<utilisateur>
        Map<String, Integer> statistiques = StatistiquesUtilisateur.calculerStatistiquesUtilisateurs(utilisateurs);
        for (Map.Entry<String, Integer> entry : statistiques.entrySet()) {
            PieChart.Data data = new PieChart.Data(entry.getKey().toString(), entry.getValue());
            pieChart.getData().add(data);
        }
        Scene scene = new Scene(pieChart, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Statistiques des Utilisateurs");
        primaryStage.show();
    }
}
