package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import MyConnection;
public class DataSource {

    private  Connection cnx;
    private  Connection conn;
    private  String url="jdbc:mysql://localhost:3306/campigo";
    private  String login="root";
    private  String pwd="";
    private static DataSource instance;

    private DataSource() {
        try {
            cnx= DriverManager.getConnection(url,login,pwd);
            System.out.println("succes");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DataSource getInstance(){
        if(instance==null)
            instance=new DataSource();
        return instance;
    }


    public Connection getCnx() {
        return cnx;
    }

    public Connection getConn() {
        return conn;
    }
}
