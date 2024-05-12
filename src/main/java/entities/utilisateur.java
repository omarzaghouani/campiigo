package entities;

public  class utilisateur {
    private static int id;
    private static String nom;
    private static String prenom;
    private static UserRole role;
    private int numeroDeTelephone;
    private static String email;
    private String motDePasse;
    private  String photo_d;
    public utilisateur( int id,String nom, String prenom, String email, String motDePasse, UserRole role, int numeroDeTelephone, String photo_d) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.numeroDeTelephone = numeroDeTelephone;
        this.email = email;
        this.motDePasse = motDePasse;
        this.photo_d = photo_d;
    }

    public utilisateur(String nom, String prenom, UserRole role, int numeroDeTelephone, String email, String motDePasse, String photo_d) {
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.numeroDeTelephone = numeroDeTelephone;
        this.email = email;
        this.motDePasse = motDePasse;
        this.photo_d = photo_d;
    }

    public utilisateur(int id) {
        this.id = id;
    }

    public utilisateur() {

    }

    public utilisateur(int id, String nom, String prenom, UserRole role, int numeroDeTelephone, String email, String motDePasse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.numeroDeTelephone = numeroDeTelephone;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    public utilisateur(int id, String nom, String prenom, String email, String motDePasse, UserRole role, int numeroDeTelephone) {
  this.id = id;
  this.nom = nom;
  this.prenom = prenom;
  this.email = email;
  this.motDePasse = motDePasse;
  this.role = role;
  this.numeroDeTelephone = numeroDeTelephone;
    }

    @Override
    public String toString() {
        return "utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", role='" + role + '\'' +
                ", numeroDeTelephone=" + numeroDeTelephone +
                ", email='" + email + '\'' +
                ",motDePasse'" + motDePasse + '\'' +
                '}';
    }

    public static int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public static String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public static UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {

        this.role = role;

    }

    private boolean isValidRole(UserRole role) {
        return role.equals("Camp_Owner") || role.equals("Simple_Utilisateur") || role.equals("Admin");
    }
    public void Utilisateur(String nom, UserRole role) {
        this.nom = nom;
        this.role = role;
    }


    public int getNumeroDeTelephone() {
        return numeroDeTelephone;
    }

    public void setNumeroDeTelephone(int numeroDeTelephone) {
        this.numeroDeTelephone = numeroDeTelephone;
    }

    public static String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getPhoto_d() {
        return photo_d;
    }

    public void setPhoto_d(String photo_d) {
        this.photo_d = photo_d;
    }
}

