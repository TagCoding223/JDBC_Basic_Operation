package Main_Package.DB_Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DB_Prepared_Insert {
    private static final String url="jdbc:mysql://localhost:3306/hotel_db";
    private static final String username="root";
    private static final String pwd="";
    public static void main(String[] args) {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(url,username,pwd);
            System.out.println("Connection Done");

            String q="Insert into reservation_info(guest_name,room_number,contact_number) values" +
                    "(?,?,?)";
            PreparedStatement pstmt=con.prepareStatement(q);

            pstmt.setString(1,"Shiv");
            pstmt.setInt(2,8765);
            pstmt.setString(3,"11111");

            int x=pstmt.executeUpdate();
            if(x>0){
                System.out.println("insert successfully");
            }else {
                System.out.println("insert fail");
            }

            pstmt.close();
            con.close();

            System.out.println("Connection Close.");
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
