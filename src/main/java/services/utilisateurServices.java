package services;

import entities.UserRole;
import entities.utilisateur;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class utilisateurServices implements Iservices<utilisateur> {
    private static Connection conn;


    public  utilisateurServices() {
        conn = DataSource.getInstance().getCnx();
    }

    public static utilisateur getUtilisateurById(int userId) {
        String req = "SELECT * FROM utilisateur WHERE id=?";
        utilisateur user = null;

        try (PreparedStatement preparedStatement = conn.prepareStatement(req)) {
            preparedStatement.setInt(1, userId);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    user = new utilisateur(
                            rs.getInt("id"),
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getInt("NumeroDeTelephone"),
                            rs.getString("Email"),
                            rs.getString("MotDePasse"),
                            UserRole.valueOf(rs.getString("Role"))
                    );
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return user;
    }

    @Override
    public void ajouter(utilisateur utilisateur) throws SQLException {
        String sql = "INSERT INTO utilisateur (nom, prenom, Role, NumeroDeTelephone, Email, MotDePasse) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, utilisateur.getNom());
            preparedStatement.setString(2, utilisateur.getPrenom());
            preparedStatement.setString(3, utilisateur.getRole().name());
            preparedStatement.setInt(4, utilisateur.getNumeroDeTelephone());
            preparedStatement.setString(5, utilisateur.getEmail());
            preparedStatement.setString(6, utilisateur.getMotDePasse());

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    utilisateur.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Échec de la récupération de l'ID après l'ajout de l'utilisateur.");
                }
            }
        }
    }

    @Override
    public void modifier(utilisateur utilisateur) throws SQLException {
        String sql = "UPDATE utilisateur SET " +
                "nom=?, " +
                "prenom=?, " +
                "Role=?, " +
                "NumeroDeTelephone=?, " +
                "Email=?, " +
                "MotDePasse=? " +
                "WHERE id=?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, utilisateur.getNom());
            preparedStatement.setString(2, utilisateur.getPrenom());
            preparedStatement.setObject(3, utilisateur.getRole());
            preparedStatement.setInt(4, utilisateur.getNumeroDeTelephone());
            preparedStatement.setString(5, utilisateur.getEmail());
            preparedStatement.setString(6, utilisateur.getMotDePasse());
            preparedStatement.setInt(7, utilisateur.getId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void supprimer(utilisateur utilisateur) throws SQLException {
        String sql = "DELETE FROM utilisateur WHERE id=?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, utilisateur.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<utilisateur> afficher() throws SQLException {
        String req = "SELECT * FROM utilisateur";
        List<utilisateur> utilisateurs = new ArrayList<>();

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(req)) {

            while (rs.next()) {
                utilisateur user = new utilisateur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getInt("NumeroDeTelephone"),
                        rs.getString("Email"),
                        rs.getString("MotDePasse"),
                        UserRole.valueOf(rs.getString("Role"))
                );

                utilisateurs.add(user);
            }
        }

        return utilisateurs;
    }

    @Override
    public int getId(utilisateur utilisateur) {
        return utilisateur.getId();
    }

    @Override
    public int getIdFromObject(Object o) {
        if (o instanceof utilisateur) {
            utilisateur user = (utilisateur) o;
            return user.getId();
        }
        return 0;
    }

    @Override
    public utilisateur authentifier(String mail, String mdp) {
        utilisateur p = new utilisateur();
        try {
            String req = "SELECT * FROM utilisateur WHERE Email = ? AND motDePasse = ?";
            try (PreparedStatement st = conn.prepareStatement(req)) {
                st.setString(1, mail);
                st.setString(2, mdp);
                ResultSet RS = st.executeQuery();

                if (RS.next()) {
                    p.setId(RS.getInt("id"));
                    p.setNom(RS.getString("Nom"));
                    p.setPrenom(RS.getString("Prenom"));

                    String roleString = RS.getString("role");

                    if (roleString != null) {
                        UserRole userRole = UserRole.valueOf(roleString);
                        p.setRole(userRole);
                    } else {
                        // Handle the case where the role is null
                        // You might set a default role or throw an exception
                    }

                    p.setNumeroDeTelephone(RS.getInt("numeroDeTelephone"));
                    p.setEmail(RS.getString("Email"));
                    p.setMotDePasse(RS.getString("MotDePasse"));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }

}
