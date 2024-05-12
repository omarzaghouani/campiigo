package services;

import entities.UserRole;
import entities.utilisateur;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class utilisateurServices implements Iservices<utilisateur> {
    public static Connection conn;


    public utilisateurServices() {
        conn = DataSource.getInstance().getCnx();
        if (conn == null) {
            System.out.println("Connection is null!");
        } else {
            System.out.println("Connection is valid.");
        }
    }
    public static utilisateur getUtilisateurById(int userId) {
        Connection conn = null;
        utilisateur user = null;

        // Assuming 'req' is your SQL query, replace it with your actual query
        String req = "SELECT * FROM utilisateur WHERE id = ?";

        try {
            // Establish a database connection (replace the connection details accordingly)
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/campigo", "root", "");

            // Check if the connection is valid and not closed
            if (conn != null && !conn.isClosed()) {
                try (PreparedStatement preparedStatement = conn.prepareStatement(req)) {
                    preparedStatement.setInt(1, userId);

                    try (ResultSet rs = preparedStatement.executeQuery()) {
                        if (rs.next()) {
                            user = mapResultSetToUtilisateur(rs);
                        }
                    }
                }
            } else {
                // Throw a more detailed exception message if the connection is not established
                throw new SQLException("La connexion à la base de données n'est pas établie. Vérifiez la configuration de la connexion.");
            }
        } catch (SQLException ex) {
            // Catch SQL exceptions and throw a more informative runtime exception
            throw new RuntimeException("Erreur lors de la récupération de l'utilisateur par ID. Détails : " + ex.getMessage(), ex);
        } finally {
            // Close the connection in a finally block to ensure it's always closed
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                // Handle any exception that might occur while closing the connection
                ex.printStackTrace(); // You can replace this with your logging mechanism
            }
        }

        return user;

    }


    private static utilisateur mapResultSetToUtilisateur(ResultSet rs) throws SQLException {
        return new utilisateur(
                rs.getInt("id"),
                rs.getString("nom"),
                rs.getString("prenom"),
                rs.getString("Email"),
                rs.getString("MotDePasse"),
                UserRole.valueOf(rs.getString("Role")),
                rs.getInt("NumeroDeTelephone"),
                rs.getString("photo_d")
        );
    }

    public static utilisateur getUtilisateurByEmail(String email) {
        utilisateur user = null;

        // Préparez votre requête SQL pour rechercher un utilisateur par son e-mail
        String req = "SELECT * FROM utilisateur WHERE Email = ?";

        try {
            // Assurez-vous que la connexion est valide et non fermée
            if (conn != null && !conn.isClosed()) {
                try (PreparedStatement preparedStatement = conn.prepareStatement(req)) {
                    preparedStatement.setString(1, email);

                    try (ResultSet rs = preparedStatement.executeQuery()) {
                        if (rs.next()) {
                            user = mapResultSetToUtilisateur(rs);
                        }
                    }
                }
            } else {
                // Lancez une exception si la connexion n'est pas établie
                throw new SQLException("La connexion à la base de données n'est pas établie. Vérifiez la configuration de la connexion.");
            }
        } catch (SQLException ex) {
            // Gérez les exceptions SQL et lancez une exception d'exécution plus informative
            throw new RuntimeException("Erreur lors de la récupération de l'utilisateur par e-mail. Détails : " + ex.getMessage(), ex);
        }

        return user;
    }
    public static boolean estUtilisateurUnique(int id, String nouveauEmail) {
        conn = DataSource.getInstance().getCnx();

        if (conn == null) {
            throw new IllegalStateException("Connection is null. Please initialize it before using this method.");
        }

        String query = "SELECT id FROM utilisateur WHERE id <> ? AND Email = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setString(2, nouveauEmail);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void initialize() {
    }


    @Override
    public void ajouter(utilisateur utilisateur) throws SQLException {
        String sql = "INSERT INTO utilisateur (nom, prenom, Role, NumeroDeTelephone, Email, MotDePasse, photo_d) " +
                "VALUES (?, ?, ?, ?, ?, ?,?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, utilisateur.getNom());
            preparedStatement.setString(2, utilisateur.getPrenom());
            preparedStatement.setString(3, utilisateur.getRole().name());
            preparedStatement.setInt(4, utilisateur.getNumeroDeTelephone());
            preparedStatement.setString(5, utilisateur.getEmail());
            preparedStatement.setString(6, utilisateur.getMotDePasse());
            preparedStatement.setString(7,utilisateur.getPhoto_d());

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
        String sql = "UPDATE `utilisateur` SET `Nom`=?,`Prenom`=?,`role`=?,`NumeroDeTelephone`=?,`Email`=?,`MotDePasse`=? WHERE `id`=?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, utilisateur.getNom());
            preparedStatement.setString(2, utilisateur.getPrenom());
            preparedStatement.setString(3, utilisateur.getRole().toString());
            preparedStatement.setInt(4, utilisateur.getNumeroDeTelephone());
            preparedStatement.setString(5, utilisateur.getEmail());
            preparedStatement.setString(6, utilisateur.getMotDePasse());
            preparedStatement.setInt(7, utilisateur.getId());
            preparedStatement.executeUpdate();
        }catch(SQLException err){
            System.out.println(err.getMessage());
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
                        rs.getString("Email"),
                        rs.getString("MotDePasse"),
                        UserRole.valueOf(rs.getString("Role")),
                        rs.getInt("NumeroDeTelephone")

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
                    p.setPhoto_d(RS.getString("photo_d"));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }
    public static List<utilisateur> rechercherUtilisateurs(String nom, String prenom, String email, UserRole role) {
        List<utilisateur> utilisateurs = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM utilisateur WHERE 1=1");

        if (nom != null && !nom.isEmpty()) {
            query.append(" AND Nom LIKE ?");
        }
        if (prenom != null && !prenom.isEmpty()) {
            query.append(" AND Prenom LIKE ?");
        }
        if (email != null && !email.isEmpty()) {
            query.append(" AND Email LIKE ?");
        }
        if (role != null) {
            query.append(" AND role = ?");
        }

        // Assurez-vous que la connexion est initialisée
        if (conn == null) {
            initConnection();
        }

        try (PreparedStatement preparedStatement = conn.prepareStatement(query.toString())) {
            int index = 1;
            if (nom != null && !nom.isEmpty()) {
                preparedStatement.setString(index++, "%" + nom + "%");
            }
            if (prenom != null && !prenom.isEmpty()) {
                preparedStatement.setString(index++, "%" + prenom + "%");
            }
            if (email != null && !email.isEmpty()) {
                preparedStatement.setString(index++, "%" + email + "%");
            }
            if (role != null) {
                preparedStatement.setString(index++, role.name());
            }

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    utilisateurs.add(mapResultSetToUtilisateur(rs));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erreur lors de la recherche des utilisateurs. Détails : " + ex.getMessage(), ex);
        }

        return utilisateurs;
    }


    private static void initConnection() {
        try {
            // Remplacez ces valeurs par vos propres informations de connexion
            String url = "jdbc:mysql://localhost:3306/campigo";
            String user = "root";
            String password = "";

            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();

            // Gérer l'exception, par exemple en affichant un message d'erreur
        }
    }
}
