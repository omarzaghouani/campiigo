package entites;

import java.time.LocalDate;

public class Transpoteur {

    private int num_ch;
    private  String nom;

    private String prenom;
    private int numtel;
    private String email;
    private LocalDate daten;
    private  int num_t;


    /*public Transpoteur(int num_ch, String nom, String prenom, int numtel, String email, LocalDate daten, int num_t) {
        this.num_ch = num_ch;
        this.nom = nom;
        this.prenom = prenom;
        this.numtel = numtel;
        this.email = email;
        this.daten = daten;
        this.num_t = num_t;
    }*/

    public Transpoteur(int num_ch, String nom, String prenom, int numtel, String email, LocalDate daten, int num_t) {
        this.num_ch = num_ch;
        this.nom = nom;
        this.prenom = prenom;
        this.numtel = numtel;
        this.email = email;
        this.daten = daten;
        this.num_t = num_t;
    }

    public int getNum_ch() {
        return num_ch;
    }

    public void setNum_ch(int num_ch) {
        this.num_ch = num_ch;
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

    public int getNumtel() {
        return numtel;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

   /* public String getDaten() {
        return daten;
    }

    public void setDaten(String daten) {
        this.daten = daten;
    }*/

    public Transpoteur(LocalDate daten) {
        this.daten = daten;
    }

    public LocalDate getDaten() {
        return daten;
    }

    public void setDaten(LocalDate daten) {
        this.daten = daten;
    }

    public int getNum_t() {
        return num_t;
    }

    public void setNum_t(int num_t) {
        this.num_t = num_t;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Transpoteur{" +
                "num_ch=" + num_ch +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", numtel=" + numtel +
                ", email='" + email + '\'' +
                ", daten='" + daten + '\'' +
                ", num_t=" + num_t +
                '}';
    }
}
