package entities;

public class Activite {
    private int id_activite;
    private String nom_activite;
    private String description;
    private String type;
    private Centre id_centre;


    public Activite() {
    }

    public Activite(int id_activite, String nom_activite, String description, String type,Centre id_centre) {
        this.id_activite = id_activite;
        this.nom_activite = nom_activite;
        this.description = description;
        this.type = type;
        this.id_centre=id_centre;
    }


    public Centre getId_centre() {
        return id_centre;
    }

    public void setId_centre(Centre id_centre) {
        this.id_centre = id_centre;
    }

    public int getId_activite() {
        return id_activite;
    }

    public void setId_activite(int id_activite) {
        this.id_activite = id_activite;
    }

    public String getNom_activite() {
        return nom_activite;
    }

    public void setNom_activite(String nom_activite) {
        this.nom_activite = nom_activite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Activite{" +
                "id_activite=" + id_activite +
                ", nom_activite='" + nom_activite + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", id_centre=" + id_centre +
                '}';
    }
}
