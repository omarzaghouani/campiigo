package service;

import entites.transport;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class transportService implements IService<transport> {

    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;

    public transportService() {
        conn= DataSource.getInstance().getCnx();
    }


    public void add(transport t){
        String requete="insert into transport (num_t,num_ch) values ('"+t.getNum_t()+"','"+t.getNum_ch()+"')";

        try {
            ste=conn.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void addpst(transport t) {
        String query = "INSERT INTO transport (num_t,num_ch, dd, da, num_v, cout) VALUES (12, 123, 1222024,1522024,1245,130)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, t.getNum_t());
            ps.setInt(2, t.getNum_ch());
            ps.setInt(3, t.getDd());
            ps.setInt(4, t.getDa());
            ps.setInt(5, t.getNum_v());
            ps.setInt(6, t.getCout());
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
    }


    @Override
    public void delete(transport transport) {
        String query = "DELETE FROM transport WHERE num_t = 123";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, transport.getNum_t());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No records deleted for transport with num_t: " + transport.getNum_t());
            } else {
                System.out.println("Deleted transport with num_t: " + transport.getNum_t());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update(transport transport) {
        String query = "UPDATE transport SET num_ch = ?, dd = ?, da = ?, num_v = ?, cout = ? WHERE num_t = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, transport.getNum_ch());
            ps.setInt(2, transport.getDd());
            ps.setInt(3, transport.getDa());
            ps.setInt(4, transport.getNum_v());
            ps.setInt(5, transport.getCout());
            ps.setInt(6, transport.getNum_t()); // Assuming num_t is the primary key
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No records updated for transport with num_t: " + transport.getNum_t());
            } else {
                System.out.println("Updated transport with num_t: " + transport.getNum_t());
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
            throw new RuntimeException(e); // If you prefer to throw a RuntimeException
        }
    }


    @Override
    public List<transport> readAll() {
        String requete = "SELECT * FROM transport";
        List<transport> list = new ArrayList<>();
        try {
            Statement ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(requete);
            while (rs.next()) {
                int num_t = rs.getInt("num_t"); // Assuming the column "num_t" exists in the transport table
                int num_ch = rs.getInt("num_ch"); // Assuming the column "num_ch" exists in the transport table
                int dd = rs.getInt(3); // Assuming the third column's name is unknown
                int da = rs.getInt(3);
                int num_v = rs.getInt(3);
                int cout = rs.getInt(3);
                // Assuming you have a constructor for the Transport class like (int num_t, int num_ch, int otherColumnValue)
                transport transport = new transport(num_t, num_ch, dd,da,num_v,cout);
                list.add(transport);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception properly in your application
            throw new RuntimeException(e); // If you prefer to throw a RuntimeException
        }
        return list;
    }

    @Override
    public transport readById(int id) {
        return null;
    }

}