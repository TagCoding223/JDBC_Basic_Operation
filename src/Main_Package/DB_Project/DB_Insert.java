package Main_Package.DB_Project;

import java.sql.*;
public class DB_Insert {
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo","root","");
            System.out.println("Connection Done.");
            Statement stmt=con.createStatement();
            String q="Insert into emp(id,name,age) values(103,'Sachin',29);";
            int x=stmt.executeUpdate(q);
            if(x>0){
                System.out.println("insert successfully "+x+" row(s) affected.");
            }else{
                System.out.println("insert fail");
            }
            con.close();
            stmt.close();
            System.out.println("Connection Close.");
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
