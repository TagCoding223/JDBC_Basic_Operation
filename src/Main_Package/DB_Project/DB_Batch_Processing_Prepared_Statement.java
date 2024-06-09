package Main_Package.DB_Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class DB_Batch_Processing_Prepared_Statement {
    public static void main(String[] args) {
        String q="Insert into reservation_info(guest_name,room_number,contact_number) values(?,?," +
                "?);";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_db",
                    "root","");
            System.out.println("Connection done");
            con.setAutoCommit(false);

            PreparedStatement pstmt=con.prepareStatement(q);
            Scanner user=new Scanner(System.in);
            String name;
            int room;
            String con_no,decision;
            while (true){
                System.out.print("Enter Name : ");
                name=user.nextLine();
                System.out.print("Room No. : ");
                room= user.nextInt();
                System.out.print("Contact No. : ");
                con_no=user.next();
                pstmt.setString(1,name);
                pstmt.setInt(2,room);
                pstmt.setString(3,con_no);
                pstmt.addBatch();
                System.out.print("Add more values : Y/N : ");
                decision=user.next();
                user.nextLine();
                if(decision.toUpperCase().equals("N")){
                    break;
                }
            }

            int [] batchResult=pstmt.executeBatch();//return an integer array like [1,1,1,0,0,1]
            // 1-> query execute successful , 0 -> query execute fail
            con.commit();
            System.out.println("Batch execute successfully");

            con.close();
            System.out.println("Connection close");
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
