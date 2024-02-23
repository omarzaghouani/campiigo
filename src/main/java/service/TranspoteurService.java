package service;

import entites.Transpoteur;
import utils.DataSource;
import java.time.LocalDate;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class TranspoteurService implements itService<Transpoteur> {

    private Connection conn;
    private Statement ste;
    private PreparedStatement psr;

    public TranspoteurService() {
        conn= DataSource.getInstance().getCnx();
    }


    /*public void add(transport t){
        String requete="insert into transport (num_t,num_ch,dd,da,num_v,cout) values ('"+t.getNum_t()+"','"+t.getNum_ch()+"','"+t.getDd()+"','"+t.getDa()+"','"+t.getNum_v()+"','"+t.getCout()+"')";

        try {
            ste=conn.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

    public boolean isValidNumtel(int numtel) {
        // Check if numtel has exactly 8 digits
        numtel = Math.abs(numtel); // Convert to absolute value to handle negative numbers
        return (int)(Math.log10(numtel) + 1) == 8;
    }
    public boolean addt(Transpoteur tr) {
        // Vérifier si le numéro de téléphone est valide


        String query = "INSERT INTO transpoteur (num_ch, nom, prenom, numtel, email, daten, num_t) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement psr = conn.prepareStatement(query)) {

            psr.setInt(1, tr.getNum_ch()); // Index 7 for num_ch (used in WHERE clause)
            psr.setString(2, tr.getNom()); // Index 1 for nom
            psr.setString(3, tr.getPrenom()); // Index 2 for prenom
            psr.setInt(4, tr.getNumtel()); // Index 3 for numtel
            // Check if numtel is valid
            if (!isValidNumtel(tr.getNumtel())) {
                System.out.println("Invalid numtel. It must be exactly 8 digits long.");
                return false ; // Exit the method if the validation fails
            }

            psr.setString(5, tr.getEmail()); // Index 4 for email
            psr.setDate(6, Date.valueOf(tr.getDaten())); // Index 5 for daten
            psr.setInt(7, tr.getNum_t()); // Index 6 for num_t

            int rowsAffected = psr.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Failed to add transporteur.");
            } else {
                System.out.println("Transporteur added successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
            throw new RuntimeException(e); // If you prefer to throw a RuntimeException
        }
        return true ;
    }



    @Override
    public void deletet(Transpoteur transpoteur) {
        String query = "DELETE FROM transpoteur WHERE num_ch = ?";
        try (PreparedStatement psr = conn.prepareStatement(query)) {
            psr.setInt(1, transpoteur.getNum_ch());
            int rowsAffected = psr.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No records deleted for transpoteur with num_ch: " + transpoteur.getNum_ch());
            } else {
                System.out.println("Deleted transpoteur with num_ch: " + transpoteur.getNum_ch());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Override
    public void updatet(Transpoteur tr) {
        String query = "UPDATE transpoteur SET nom = ?, prenom = ?, numtel = ?, email = ?, daten = ?, num_t = ? WHERE num_ch = ?";
        try (PreparedStatement psr = conn.prepareStatement(query)) {
            psr.setString(1, tr.getNom());      // Index 1 for nom
            psr.setString(2, tr.getPrenom());   // Index 2 for prenom
            psr.setInt(3, tr.getNumtel());      // Index 3 for numtel
            psr.setString(4, tr.getEmail());     // Index 4 for email
            psr.setDate(5, Date.valueOf(tr.getDaten()));    // Index 5 for daten
            psr.setInt(6, tr.getNum_t());        // Index 6 for num_t
            psr.setInt(7, tr.getNum_ch());       // Index 7 for num_ch

            int rowsAffected = psr.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No transporteur found with num_ch: " + tr.getNum_ch());
            } else {
                System.out.println("Transporteur with num_ch " + tr.getNum_ch() + " updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
            throw new RuntimeException(e); // If you prefer to throw a RuntimeException
        }
    }



    @Override

        public List<Transpoteur> readAll() {    String query = "SELECT * FROM transpoteur";
        List<Transpoteur> list = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int num_ch = rs.getInt("num_ch");
                String nom= rs.getString("nom"); // Assuming "num_v" is the correct column name
                String prenom = rs.getString("prenom"); // Assuming "cout" is the correct column name

                int numtel = rs.getInt("numtel");
                String email = rs.getString("email");
                LocalDate daten = rs.getDate("daten").toLocalDate();
                int num_t  = rs.getInt("num_t");
                Transpoteur transpoteur = new Transpoteur( num_ch,nom,prenom,numtel,email,daten,num_t);
                list.add(transpoteur);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
            throw new RuntimeException(e); // If you prefer to throw a RuntimeException
        }
        return list;
    }

    @Override
    public Transpoteur readBynum_ch(int num_ch) {
        return null;
    }

}