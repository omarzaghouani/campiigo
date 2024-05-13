package services;

import entities.User;
import org.mindrot.jbcrypt.BCrypt;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements Iservices<User>{
    public static Connection conn;

    public UserService() {
        conn = DataSource.getInstance().getCnx();
        if (conn == null) {
            System.out.println("Connection is null!");
        } else {
            System.out.println("Connection is valid.");
        }
    }

    public static User getUtilisateurById(int userId) {
        Connection conn = null;
        User user = null;

        // Assuming 'req' is your SQL query, replace it with your actual query
        String req = "SELECT * FROM user WHERE id = ?";

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

    private static User mapResultSetToUtilisateur(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("email"),
                rs.getString("roles"),
                rs.getString("password"),
                rs.getString("nom"),
                rs.getString("prenom"),
                rs.getInt("numerodetelephone"),
                rs.getString("photo_d")
        );
    }

    public static User getUtilisateurByEmail(String email) {
        User user = null;

        // Préparez votre requête SQL pour rechercher un utilisateur par son e-mail
        String req = "SELECT * FROM user WHERE email = ?";

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

        String query = "SELECT id FROM user WHERE id <> ? AND email = ?";
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
    public void ajouter(User user) throws SQLException {
        String encodedPassword = PasswordEncoder.encode(user.getPassword());

        String sql = "INSERT INTO user (email, roles, password, nom, prenom, numerodetelephone, photo_d) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getRoles());
           // preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(3, encodedPassword);
            preparedStatement.setString(4, user.getNom());
            preparedStatement.setString(5, user.getPrenom());
            preparedStatement.setInt(6, user.getNumerodetelephone());
            preparedStatement.setString(7,user.getPhoto_d());

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Échec de la récupération de l'ID après l'ajout de l'utilisateur.");
                }
            }
        }
    }


    @Override
    public void modifier(User user) throws SQLException {
        String sql = "UPDATE `user` SET `email`=?,`roles`=?,`password`=?,`nom`=?,`prenom`=?,`numerodetelephone`=?,`photo_d`=? WHERE `id`=?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getRoles());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getNom());
            preparedStatement.setString(5, user.getPrenom());
            preparedStatement.setInt(6, user.getNumerodetelephone());
            preparedStatement.setString(7,user.getPhoto_d());
            preparedStatement.setInt(8, user.getId());
            preparedStatement.executeUpdate();
        }catch(SQLException err){
            System.out.println(err.getMessage());
        }
    }

    @Override
    public void supprimer(User user) throws SQLException {
        String sql = "DELETE FROM user WHERE id=?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<User> afficher() throws SQLException {
        String req = "SELECT * FROM user";
        List<User> utilisateurs = new ArrayList<>();

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(req)) {

            while (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("roles"),
                        rs.getString("password"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getInt("numerodetelephone"),
                        rs.getString("photo_d")

                );

                utilisateurs.add(user);
            }
        }

        return utilisateurs;    }

    @Override
    public int getId(User user) {
        return user.getId();
    }

    @Override
    public int getIdFromObject(Object o) {
        if (o instanceof User) {
            User user = (User) o;
            return user.getId();
        }
        return 0;    }

    // (int id, String email, String roles, String password, String nom, String prenom, int numerodetelephone, String photo_d)

    @Override
    public User authentifier(String mail, String mdp) {
        User p = new User();
        try {
            String req = "SELECT * FROM user WHERE email = ?";
            try (PreparedStatement st = conn.prepareStatement(req)) {
                st.setString(1, mail);
                ResultSet RS = st.executeQuery();

                if (RS.next()) {
                    String encodedPassword = RS.getString("password");
                    boolean passwordMatch = BCrypt.checkpw(mdp, encodedPassword);
                    if (passwordMatch) {
                        p.setId(RS.getInt("id"));
                        p.setNom(RS.getString("nom"));
                        p.setPrenom(RS.getString("prenom"));

                        String roleString = RS.getString("roles");

                        if (roleString != null) {
                            //   UserRole userRole = UserRole.valueOf(roleString);
                            p.setRoles(roleString);
                        } else {
                            // Handle the case where the role is null
                            // You might set a default role or throw an exception
                        }

                        p.setNumerodetelephone(RS.getInt("numerodetelephone"));
                        p.setEmail(RS.getString("email"));
                        p.setPassword(RS.getString("password"));
                        p.setPhoto_d(RS.getString("photo_d"));
                    } else {
                        System.out.println("wrong creds");
                         return null;
                    }


                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }


    public static List<User> rechercherUtilisateurs(String nom, String prenom, String email, String role) {
        List<User> utilisateurs = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM user WHERE 1=1");

        if (nom != null && !nom.isEmpty()) {
            query.append(" AND nom LIKE ?");
        }
        if (prenom != null && !prenom.isEmpty()) {
            query.append(" AND prenom LIKE ?");
        }
        if (email != null && !email.isEmpty()) {
            query.append(" AND email LIKE ?");
        }
        if (role != null) {
            query.append(" AND roles = ?");
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
               // preparedStatement.setString(index++, role.name());
                preparedStatement.setString(index++, "%" + role + "%");

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

    public static boolean userExistsWithEmail(String email) {
        String req = "SELECT COUNT(*) FROM user WHERE email = ?";
        try {
            if (conn == null) {
                initConnection();
            }

            try (PreparedStatement preparedStatement = conn.prepareStatement(req)) {
                preparedStatement.setString(1, email);

                try (ResultSet rs = preparedStatement.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt(1);
                        return count > 0;
                    }
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erreur lors de la vérification de l'existence de l'utilisateur par e-mail. Détails : " + ex.getMessage(), ex);
        }
        return false;
    }



}
