package Main_Package.DB_Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DB_Batch_Processing_Simple_Q {
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_db",
                    "root","");
            System.out.println("Connection done");
            con.setAutoCommit(false);

            Statement stmt =con.createStatement();
            stmt.addBatch("Insert into reservation_info(guest_name,room_number,contact_number) " +
                    "values('kumkum','67','898988');");
            stmt.addBatch("Insert into reservation_info(guest_name,room_number,contact_number) " +
                    "values('Yogita','68','18989881');");
            stmt.addBatch("Insert into reservation_info(guest_name,room_number,contact_number) " +
                    "values('karan','69','989');");

            int [] batchResult=stmt.executeBatch();//return an integer array
            con.commit();
            System.out.println("Batch execute successfully");

            con.close();
            System.out.println("Connection close");
        }catch (Exception e){
            System.out.println(e);
        }
    }
}