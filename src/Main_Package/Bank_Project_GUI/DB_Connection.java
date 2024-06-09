package Main_Package.Bank_Project_GUI;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB_Connection {
    private final String url="jdbc:mysql://localhost:3306/bankingapp_db";
    private final String user_name="root";
    private final String password="";
    Connection con;
    public Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection(url,user_name,password);
            return con;
        }catch (Exception e){
            System.out.println(e);
        }
        return con;
    }
}
