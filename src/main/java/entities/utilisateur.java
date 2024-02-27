package entities;

public  class utilisateur {
    private int id;
    private String nom;
    private String prenom;
    private UserRole role;
    private int numeroDeTelephone;
    private String email;
    private String motDePasse;

    public utilisateur( int id,String nom, String prenom, String email, String motDePasse, UserRole role, int numeroDeTelephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.numeroDeTelephone = numeroDeTelephone;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    public utilisateur(String nom, String prenom, UserRole role, int numeroDeTelephone, String email, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.numeroDeTelephone = numeroDeTelephone;
        this.email = email;
        this.motDePasse = motDePasse;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {

        this.role = role;

    }

    private boolean isValidRole(UserRole role) {
        return role.equals("Camp_Owner") || role.equals("Simple_Utilisateur") || role.equals("Admin");
    }

    public int getNumeroDeTelephone() {
        return numeroDeTelephone;
    }

    public void setNumeroDeTelephone(int numeroDeTelephone) {
        this.numeroDeTelephone = numeroDeTelephone;
    }

    public String getEmail() {
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
}

