package entities;

public class Session {
    private static Session instance;
    private utilisateur user;

    private Session() {}

    public static synchronized Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public void destroy() {
        this.user = null;
    }

    public void setUser(utilisateur user) {
        this.user = user;
    }

    public utilisateur getUser() {
        return user;
    }

    public void clearUser() {
        this.user = null;
    }
}
