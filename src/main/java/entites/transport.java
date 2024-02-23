package entites;

public class transport {
    private int num_t;
    private int num_ch;
    private int dd;
    private int da;
    private int num_v;
    private int cout;


    public transport() {
    }

    public transport(int num_t, int num_ch,  int dd,int da,int num_v,int cout) {
        this.num_t= num_t;
        this.num_ch = num_ch;
        this.dd = dd;
        this.da = da;
        this.num_v = num_v;
        this.cout = cout;
    }

    /*public transport(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
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


    public int getDd() {
        return dd;
    }

    public void setDd(int dd) {
        this.dd = dd;
    }

    public int getDa() {
        return da;
    }

    public void setDa(int da) {
        this.da = da;
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
