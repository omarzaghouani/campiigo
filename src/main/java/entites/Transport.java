package entites;

import java.time.LocalDate;

public class Transport {
    public String getNum_t;
    private int num_t;
    private int num_ch;
    //transpoteur transpoteur;
    private LocalDate dd;
    private LocalDate da;
    private int num_v;
   // vehicule vehicule;
    private int cout;

    public Transport() {
    }

    public  Transport(int num_ch, LocalDate dd, LocalDate da, int num_v, int cout) {
        this.num_t= num_t;
        this.num_ch = num_ch;
        this.dd = dd;
        this.da = da;
        this.num_v = num_v;
        this.cout = cout;
    }

    public Transport(int numCh, int numV, int cout, LocalDate dd, LocalDate da) {
    }





   /* public Transport(int i, int i1, LocalDate of, LocalDate of1, int i2, int i3) {
    }*/

    /*public transport(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }*/

    /*public transport(int num_ch, entites.transpoteur transpoteur, LocalDate dd, LocalDate da, int num_v, entit int cout) {
        this.num_ch = num_ch;
        //this.transpoteur = transpoteur;
        this.dd = dd;
        this.da = da;
        this.num_v = num_v;
        //this.vehicule = vehicule;
        this.cout = cout;
    }*/

    /*public entites.transpoteur getTranspoteur() {
        return transpoteur;
    }*/

    /*public void setTranspoteur(entites.transpoteur transpoteur) {
        this.transpoteur = transpoteur;
    }*/

    public LocalDate getDd() {
        return dd;
    }

    public void setDd(LocalDate dd) {
        this.dd = dd;
    }

    public LocalDate getDa() {
        return da;
    }

    public void setDa(LocalDate da) {
        this.da = da;
    }

    /*public entites.vehicule getVehicule() {
        return vehicule;
    }*/

    /*public void setVehicule(entites.vehicule vehicule) {
        this.vehicule = vehicule;
    }*/

    public int getNum_t() {
        return num_t;
    }

    public void setNum_t(int num_t) {
        this.num_t = num_t;
    }

    public int getNum_ch() {
        return num_ch;
    }

    public void setNum_ch(int num_ch) {
        this.num_ch = num_ch;
    }




    public int getNum_v() {
        return num_v;
    }

    public void setNum_v(int num_v) {
        this.num_v = num_v;
    }

    public int getCout() {
        return cout;
    }

    public void setCout(int cout) {
        this.cout = cout;
    }

    @Override
    public String toString() {
        return "transport{" +
                "num_t=" + num_t +
                ", num_ch=" + num_ch +
                ", dd=" + dd +
                ", da=" + da +
                ", num_v=" + num_v +
                ", cout=" + cout +
                '}';
    }
}
