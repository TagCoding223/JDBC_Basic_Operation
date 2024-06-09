package Main_Package.DB_Project;

import java.sql.*;
public class DB_Connection {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost/sonoo","root","");
            System.out.println("Connection Done");
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
