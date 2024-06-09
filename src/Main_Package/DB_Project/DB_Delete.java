package Main_Package.DB_Project;

import java.sql.*;
public class DB_Delete {
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo","root","");
            System.out.println("Connection Successfully !!!");
            Statement stmt=con.createStatement();
            String q="Delete from emp where id=103;";
            int x=stmt.executeUpdate(q);
            if(x>0){
                System.out.println("Delete Successfully "+x+" rows affected.");
            }else{
                System.out.println("Delete fail.");
            }
            stmt.close();
            con.close();
            System.out.println("Connection close successfully !!!");
        }catch(Exception e){
            System.out.println(e);
        }
    }

}
