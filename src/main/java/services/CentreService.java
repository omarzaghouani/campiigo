package services;

import entities.Centre;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CentreService implements IService<Centre> {

    private final Connection conn;
    private PreparedStatement pst;
    private ResultSet rs;
    private Statement ste;

    public CentreService() {
        conn = DataSource.getInstance().getCnx();
    }

    @Override
    public void add(Centre c) {
        String query = "INSERT INTO centre (id_centre, nom_centre, ville, mail, num_tel, nbre_activite) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, c.getId_centre());
            pst.setString(2, c.getNom_centre());
            pst.setString(3, c.getVille());
            pst.setString(4, c.getMail());
            pst.setInt(5, c.getNum_tel());
            pst.setInt(6, c.getNbre_activite());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout du centre", e);
        } finally {
            closeResources();
        }
    }

    @Override
    public void delete(Centre c) {
        String query = "DELETE FROM centre WHERE id_centre=?";
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, c.getId_centre());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression du centre", e);
        } finally {
            closeResources();
        }
    }

    @Override
    public void update(Centre c) {
        String query = "UPDATE centre SET nom_centre=?, ville=?, mail=?, num_tel=?, nbre_activite=? WHERE id_centre=?";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, c.getNom_centre());
            pst.setString(2, c.getVille());
            pst.setString(3, c.getMail());
            pst.setInt(4, c.getNum_tel());
            pst.setInt(5, c.getNbre_activite());
            pst.setInt(6, c.getId_centre());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la mise à jour du centre", e);
        } finally {
            closeResources();
        }
    }


    @Override
    public List<Centre> readAll()  {
        String requete = "SELECT * FROM centre";
        List<Centre> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(requete);
            while (rs.next()) {
                int id_centre = rs.getInt("id_centre");
                String nom_centre = rs.getString("nom_centre");
                String ville = rs.getString("ville");
                String mail= rs.getString("mail");
                int num_tel = rs.getInt("num_tel");
                int nbre_activite= rs.getInt("nbre_activite");
                Centre c = new Centre(id_centre, nom_centre, ville,mail,num_tel,nbre_activite);
                list.add(c);
                //list.delete(c);
                //list.update(c);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Centre readById(int id) {
        String query = "SELECT * FROM centre WHERE id_centre=?";
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                Centre c = new Centre();
                c.setId_centre(rs.getInt("id_centre"));
                c.setNom_centre(rs.getString("nom_centre"));
                c.setVille(rs.getString("ville"));
                c.setMail(rs.getString("mail"));
                c.setNum_tel(rs.getInt("num_tel"));
                c.setNbre_activite(rs.getInt("nbre_activite"));
                return c;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la lecture du centre par ID", e);
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

    public boolean checkIfCentreExists(String nomCentre) {
        List<Centre> centres = readAll();

        for (Centre centre : centres) {
            if (centre.getNom_centre().equals(nomCentre)) {
                return true; // Le centre existe déjà
            }
        }

        return false; // Le centre n'existe pas
    }

    public Centre getCentreById(int id) {
        Centre centre = null;
        String query = "SELECT * FROM centre WHERE id_centre=?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int id_centre = rs.getInt("id_centre");
                String nom_centre = rs.getString("nom_centre");
                String ville = rs.getString("ville");
                String mail = rs.getString("mail");
                int num_tel = rs.getInt("num_tel");
                int nbre_activite = rs.getInt("nbre_activite");
                // Créer l'objet Centre avec les informations récupérées
                centre = new Centre(id_centre, nom_centre, ville, mail, num_tel, nbre_activite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return centre;
    }
    public List<Centre> rechercherParNom(String nom) {
        List<Centre> centresTrouves = new ArrayList<>();

        // Parcourir la liste des centres pour trouver ceux dont le nom correspond
        for (Centre centre : centresTrouves) {
            if (centre.getNom_centre().equalsIgnoreCase(nom)) {
                centresTrouves.add(centre);
            }
        }

        return centresTrouves;
    }
}
