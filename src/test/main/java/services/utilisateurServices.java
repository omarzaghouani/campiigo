package services;

import entities.UserRole;
import entities.utilisateur;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class utilisateurServices implements Iservices<utilisateur> {
    protected Connection conn;

    public utilisateurServices() {
        conn = DataSource.getInstance().getCnx();
    }

    @Override
    public void ajouter(utilisateur utilisateur) throws SQLException {
        String sql = "INSERT INTO utilisateur (id, nom, prenom, Role, NumeroDeTelephone, Email) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, utilisateur.getId());
            preparedStatement.setString(2, utilisateur.getNom());
            preparedStatement.setString(3, utilisateur.getPrenom());
            preparedStatement.setObject(4, utilisateur.getRole());
            preparedStatement.setInt(5, utilisateur.getNumeroDeTelephone());
            preparedStatement.setString(6, utilisateur.getEmail());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void modifier(utilisateur utilisateur) throws SQLException {
        String sql = "UPDATE utilisateur SET " +
                "nom=?, " +
                "prenom=?, " +
                "Role=?, " +
                "NumeroDeTelephone=?, " +
                "Email=? " +
                "WHERE id=?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, utilisateur.getNom());
            preparedStatement.setString(2, utilisateur.getPrenom());
            preparedStatement.setObject(3, utilisateur.getRole());
            preparedStatement.setInt(4, utilisateur.getNumeroDeTelephone());
            preparedStatement.setString(5, utilisateur.getEmail());
            preparedStatement.setInt(6, utilisateur.getId());

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
                utilisateur user1 = new utilisateur();
                user1.setId(rs.getInt("id"));

                // Utilisation de la méthode getObject pour récupérer le rôle en tant qu'objet
                Object roleObject = rs.getObject("Role");

                if (roleObject != null && roleObject instanceof UserRole) {
                    // Conversion de l'objet en UserRole
                    UserRole role = (UserRole) roleObject;
                    user1.setRole(role);
                } else {
                    // Gestion du cas où la valeur n'est pas une instance de UserRole
                    throw new SQLException("Erreur lors de la récupération du rôle");
                }

                user1.setNom(rs.getString("nom"));
                user1.setPrenom(rs.getString("prenom"));
                user1.setNumeroDeTelephone(rs.getInt("NumeroDeTelephone"));
                user1.setEmail(rs.getString("Email"));
                user1.setMotDePasse(rs.getString("MotDePasse"));

                utilisateurs.add(user1);
            }
        }

        return utilisateurs;
    }


    public abstract int getIdFromObject(Object o);
}
