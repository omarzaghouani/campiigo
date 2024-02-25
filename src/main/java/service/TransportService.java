package service;

import entites.Transport;

import utils.DataSource;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
public class TransportService implements IService<Transport> {

    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;


    public TransportService() {
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

    @Override
    public boolean add(Transport t) {
        // Vérification de la saisie sur num_v
        if (t.getNum_v() <= 0) {
            System.out.println("Le numéro de véhicule doit être un nombre positif.");
            return false ; // Sortie de la méthode si la saisie est invalide
        }
        if (t.getCout() <= 0) {
            System.out.println("Le cout doit être un nombre positif.");
            return false ;// Sortie de la méthode si la saisie est invalide
        }
        if ( t.getDa().compareTo(t.getDd())<0 || t.getDd().compareTo(LocalDate.now())<0 ){
            System.out.println(" wrong dates ");
            return false  ;

        }

        String query = "INSERT INTO transport (num_ch, dd, da, num_v, cout) VALUES ( ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {

           // ps.setInt(1, t.getNum_t());
            ps.setInt(1, t.getNum_ch());
            ps.setDate(2, Date.valueOf(t.getDd()));
            ps.setDate(3, Date.valueOf(t.getDa()));
            ps.setInt(4, t.getNum_v());
            ps.setInt(5, t.getCout());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Failed to add transport.");
            } else {
                System.out.println("Transport added successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
            throw new RuntimeException(e); // If you prefer to throw a RuntimeException
        }
    return true ;
    }



    @Override
    public void delete(Transport t) {
        String query = "DELETE FROM transport WHERE num_ch=?";
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, t.getNum_ch());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression du transport", e);
        } finally {
            closeResources();
        }
    }



    @Override
    public void update(Transport transport) {
        String query = "UPDATE transport SET num_ch = ?, dd = ?, da = ?, num_v = ?, cout = ? WHERE num_ch= ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, transport.getNum_ch());
            ps.setDate(2, Date.valueOf(transport.getDd()));
            ps.setDate(3, Date.valueOf(transport.getDa()));
            ps.setInt(4, transport.getNum_v());
            ps.setInt(5, transport.getCout());
            ps.setInt(6, transport.getNum_ch()); // Assuming num_t is the primary key
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No records updated for transport with num_ch: " + transport.getNum_ch());
            } else {
                System.out.println("Updated transport with num_ch: " + transport.getNum_ch());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update transport: " + e.getMessage());
        }
    }

    /*public List<Transport> rechercheParnum_ch(int num_ch) {
        ResultSet resultSet = null;
        List<Transport> transports = new ArrayList<>();
        try {
            String query = "SELECT * FROM transport WHERE num_ch = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, num_ch);
            resultSet = pst.executeQuery();

            while (resultSet.next()) {


                Transport t = new Transport(num_ch,dd,da,num_v,cout);
                t.setNum_ch(resultSet.getInt("num_ch")); // Using column name instead of index
                t.setDd(resultSet.getDate("dd").toLocalDate()); // Assuming dd is of type Date in Transport
                t.setDa(resultSet.getDate("da").toLocalDate()); // Assuming da is of type Date in Transport
                t.setNum_v(resultSet.getInt("num_v")); // Assuming num_v is of type int in Transport
                t.setCout(resultSet.getInt("cout")); // Assuming cout is of type int in Transport

                transports.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return transports;
    }*/

    /*@Override
    public List<Transport> readAll() {
        List<Transport> List = new ArrayList<>();

        String query = "SELECT * FROM vehicule JOIN transport ON vehicule.num_v = transport.num_v WHERE transport.num_ch = ?";
        List<Transport> transportList = new ArrayList<>();

        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1,); // Set the value for the placeholder

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int num_t = rs.getInt("num_t");
                    int num_ch = rs.getInt("num_ch");
                    LocalDate dde = rs.getDate("dd").toLocalDate();
                    LocalDate dae = rs.getDate("da").toLocalDate();
                    int num_ve = rs.getInt("num_v");
                    int coutt = rs.getInt("cout");
                    int num_v = rs.getInt("num_v"); // Assuming "num_v" is the correct column name
                    String type = rs.getString("type");
                    int capacite = rs.getInt("capacite");
                    int prixuni = rs.getInt("prixuni");

                    // Create the corresponding vehicle object
                    Vehicule vehicule = new Vehicule(num_ve, type, capacite, prixuni);

                    // Create the Transport object
                    Transport transport = new Transport(num_t, num_ch, dde, dae, coutt, vehicule);
                    transportList.add(transport);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error executing SQL query", e);
        }

        return transportList;
    }*/
/*
    @Override
    public List<Transport> readAll() {
        List<Transport> List = new ArrayList<>();

        String query = "SELECT * FROM vehicule JOIN transport ON vehicule.num_v = transport.num_v WHERE transport.num_ch = ?";

        try (PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                int num_t = rs.getInt("num_t");
                int num_ch = rs.getInt("num_ch");
                LocalDate dde= rs.getDate("dd ").toLocalDate();
                LocalDate dae = rs.getDate("da").toLocalDate();
                int num_ve= rs.getInt("num_v");
                int coutt= rs.getInt("cout");
                // Read voiture data excluding id_v
                int num_v = rs.getInt("num_v");
                String type = rs.getString("type");
                int capacite= rs.getInt("capacite"); // Assuming "num_v" is the correct column name
                int prixuni = rs.getInt("prixuni"); // Assuming "cout" is the correct column name
                // Assuming you have columns named "dd" and "da", replace them with the correct column names
                // int num_ch = rs.getInt("num_ch");

                // Create the corresponding voiture object
                Vehicule vehicule = new Vehicule(num_ch, dde, dae, num_ve, coutt);

                // Create the Reservation_v object
                Transport transport = new Transport(num_v,type, capacite,prixuni, vehicule);
                List.add(transport);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return List;
    }*/

    @Override
    public List<Transport> readAll() {
        String query = "SELECT * FROM transport";
        List<Transport> list = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Transport transport = new Transport(
                        rs.getInt("num_ch"),
                        rs.getDate("dd").toLocalDate(),
                        rs.getDate("da").toLocalDate(),
                        rs.getInt("num_v"),
                        rs.getInt("cout")
                );
                // Assuming num_t is the primary key
                transport.setNum_t(rs.getInt("num_t"));
                list.add(transport);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
            throw new RuntimeException(e); // If you prefer to throw a RuntimeException
        }
        return list;

    }

    public List<Transport> rechercheParnum_ch(int num_ch) {
        ResultSet resultSet = null;
        List<Transport> transports = new ArrayList<>();
        try {
            String query = "SELECT * FROM transport WHERE num_ch = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, num_ch);
            resultSet = pst.executeQuery();

            while (resultSet.next()) {
                Transport t = new Transport();
                t.setNum_ch(resultSet.getInt("num_ch")); // Using column name instead of index
                t.setDd(resultSet.getDate("dd").toLocalDate()); // Assuming dd is of type Date in Transport
                t.setDa(resultSet.getDate("da").toLocalDate()); // Assuming da is of type Date in Transport
                t.setNum_v(resultSet.getInt("num_v")); // Assuming num_v is of type int in Transport
                t.setCout(resultSet.getInt("cout")); // Assuming cout is of type int in Transport

                transports.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return transports;
    }

    @Override
    public Transport readBynum_ch(int num_ch) {
        String query = "SELECT * FROM transport WHERE num_ch=?";
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, num_ch);
            rs = pst.executeQuery();
            if (rs.next()) {
                Transport t= new Transport();
                t.setNum_t(rs.getInt("num_t"));
                t.setNum_ch(rs.getInt("num_ch"));
                t.setDd(rs.getDate("Dd").toLocalDate());
                t.setDa(rs.getDate("da").toLocalDate());
                t.setNum_v(rs.getInt("num_v"));
                t.setCout(rs.getInt("cout"));

                return t;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la lecture du transport par num_ch", e);
        } finally {
            closeResources();
        }
        return null;
    }




    @Override
   public void selectvandtranspoteur(int num_ch) {
    String query = "SELECT * FROM vehicule JOIN transport ON vehicule.num_v = transport.num_v WHERE transport.num_ch = ?";
    try {
        // Prepare the statement with the SQL query
        PreparedStatement pst = conn.prepareStatement(query);

        // Set the value of num_ch in the WHERE clause
        pst.setInt(1, num_ch);

        // Execute the query and obtain the ResultSet
   ResultSet rs = pst.executeQuery();

        // Process the ResultSet
        while (rs.next()) {
            // Retrieve data from the ResultSet and utilize it as needed
            int vehiculeId = rs.getInt("num_v");
            // Retrieve other columns as needed

            // Process the data or store it in appropriate data structures
        }

        // Close the ResultSet and PreparedStatement
        rs.close();
        pst.close();
    } catch (SQLException e) {
        // Handle any SQL exceptions that may occur
        e.printStackTrace(); // Or handle it according to your application's requirements
    }
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