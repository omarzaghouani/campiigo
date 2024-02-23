package entites;

//import java.sql.Date;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import utils.DataSource;
//import MyConnection; // Import your MyConnection class

public class Vehicule {
    private int num_v;
    private String type;
    private int capacite;
    private int prixuni;
    private int num_ch;
    private DataSource MyConnection;

    public Vehicule(int num_v, String type, int capacite, int prixuni, int num_ch) {
        this.num_v = num_v;
        this.type = type;
        this.capacite = capacite;
        this.prixuni = prixuni;
        this.num_ch = num_ch;

    }

    public Vehicule(TextField numV, ChoiceBox<String> type, TextField capacite, TextField prixuni, TextField numCh) {
    }

    public int getNum_v() {
        return num_v;
    }

    public void setNum_v(int num_v) {
        this.num_v = num_v;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public int getPrixuni() {
        return prixuni;
    }

    public void setPrixuni(int prixuni) {
        this.prixuni = prixuni;
    }

    public int getNum_ch() {
        return num_ch;
    }

    public void setNum_ch(int num_ch) {
        this.num_ch = num_ch;
    }

    @Override
    public String toString() {
        return "Vehicule{" +
                "num_v=" + num_v +
                ", type='" + type + '\'' +
                ", capacite=" + capacite +
                ", prixuni=" + prixuni +
                ", num_ch=" + num_ch +
                '}';
    }

    }

