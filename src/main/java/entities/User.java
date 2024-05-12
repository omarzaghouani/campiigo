package entities;

public class User {
    int id;
    String email,roles,password,nom,prenom;
    int numerodetelephone;
    String photo_d;

    public User(int id, String email, String roles, String password, String nom, String prenom, int numerodetelephone, String photo_d) {
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.numerodetelephone = numerodetelephone;
        this.photo_d = photo_d;
    }

    public User(String email, String roles, String password, String nom, String prenom, int numerodetelephone, String photo_d) {
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.numerodetelephone = numerodetelephone;
        this.photo_d = photo_d;
    }
    public User(int id) {
        this.id = id;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getNumerodetelephone() {
        return numerodetelephone;
    }

    public void setNumerodetelephone(int numerodetelephone) {
        this.numerodetelephone = numerodetelephone;
    }

    public String getPhoto_d() {
        return photo_d;
    }

    public void setPhoto_d(String photo_d) {
        this.photo_d = photo_d;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", roles='" + roles + '\'' +
                ", password='" + password + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", numerodetelephone=" + numerodetelephone +
                ", photo_d='" + photo_d + '\'' +
                '}';
    }
}
