package entities;
import java.util.List;
import java.awt.*;
import java.util.ArrayList;

public class Centre {
private int id_centre;
private String nom_centre;
private String ville;
private String mail;
private int num_tel;
private int nbre_activite;
//private List<Activite> activites;

public Centre() {
       //this.activites= new ArrayList<>();
        }

        public Centre(int id_centre, String nom_centre, String ville, String mail, int num_tel, int nbre_activite) {
                this.id_centre = id_centre;
                this.nom_centre = nom_centre;
                this.ville = ville;
                this.mail = mail;
                this.num_tel = num_tel;
                this.nbre_activite = nbre_activite;
         //       this.activites = activites;
        }

        public int getId_centre() {
        return id_centre;
        }

public void setId_centre(int id_centre) {
        this.id_centre = id_centre;
        }

public String getNom_centre() {
        return nom_centre;
        }

public void setNom_centre(String nom_centre) {
        this.nom_centre = nom_centre;
        }

public String getVille() {
        return ville;
        }

public void setVille(String ville) {
        this.ville = ville;
        }

        public String getMail() {
                return mail;
        }

        public void setMail(String mail) {
                this.mail = mail;
        }

        public int getNum_tel() {
        return num_tel;
        }

public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
        }

public int getNbre_activite() {
        return nbre_activite;
        }

public void setNbre_activite(int nbre_activite) {
        this.nbre_activite = nbre_activite;
        }
   /*    public List<Activite> getActivites() {
                return activites;
        }

        public void setActivites(List<Activite> activites) {
                this.activites = activites;
        }
*/
        @Override
        public String toString() {
                return "Centre{" +
                        "id_centre=" + id_centre +
                        ", nom_centre='" + nom_centre + '\'' +
                        ", ville='" + ville + '\'' +
                        ", mail=" + mail +
                        ", num_tel=" + num_tel +
                        ", nbre_activite=" + nbre_activite +

                        '}';
        }
}
