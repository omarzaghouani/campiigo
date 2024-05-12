package services;

import entities.Activite;
import entities.Centre;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActiviteService implements IService<Activite> {
    private final Connection conn;
    private PreparedStatement pst;
    private ResultSet rs;
    private Statement ste;
    public ActiviteService() {
        conn = DataSource.getInstance().getCnx();
    }
    @Override
    public void add(Activite a) {
        String query = "INSERT INTO activite (id_activite, nom_activite, description, type, id_centre) VALUES (?, ?, ?, ?, ?)";
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, a.getId_activite());
            pst.setString(2, a.getNom_activite());
            pst.setString(3, a.getDescription());
            pst.setString(4, a.getType());
            pst.setInt(5, a.getId_centre().getId_centre()); // Assumant que getId_centre() renvoie un entier
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout de l'activité", e);
        } finally {
            closeResources();
        }
    }


    @Override
    public void delete(Activite a)  {
        {
            String query = "DELETE FROM activite WHERE id_activite=?";
            try {
                pst = conn.prepareStatement(query);
                pst.setInt(1, a.getId_activite());
                pst.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException("Erreur lors de la suppression du activite", e);
            } finally {
                closeResources();
            }
        }

    }

    public void update(Activite a) {
        String query = "UPDATE activite SET nom_activite=?, description=?, type=?, id_centre=? WHERE id_activite=?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, a.getNom_activite());
            pst.setString(2, a.getDescription());
            pst.setString(3, a.getType());
            pst.setInt(4, a.getId_centre().getId_centre());
            pst.setInt(5, a.getId_activite());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la modification de l'activité", e);
        } finally {
            closeResources();
        }
    }



    @Override
    public List<Activite> readAll() {
        String requete = "SELECT * FROM activite";
        List<Activite> listA = new ArrayList<>();
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(requete);
            while (rs.next()) {
                int id_activite = rs.getInt("id_activite");
                String nom_activite = rs.getString("nom_activite");
                String description = rs.getString("description");
                String type = rs.getString("type");
                int id_centre = rs.getInt("id_centre");

                // Récupérer l'objet Centre correspondant à id_centre
                CentreService centreService = new CentreService();
                Centre centre = centreService.getCentreById(id_centre);

                // Créer l'objet Activite avec l'objet Centre obtenu
                Activite a = new Activite(id_activite, nom_activite, description, type, centre);
                listA.add(a);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listA;
    }


    @Override
    public Activite readById(int id) {
        String query = "SELECT * FROM activite WHERE id_activite=?";
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                Activite a= new Activite();
                a.setId_activite(rs.getInt("id_activite"));
                a.setNom_activite(rs.getString("nom_activite"));
                a.setDescription(rs.getString("description"));
                a.setType(rs.getString("type"));

                return a;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la lecture du activite par ID", e);
        } finally {
            closeResources();
        }
        return null;
    }

    private void closeResources() {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
