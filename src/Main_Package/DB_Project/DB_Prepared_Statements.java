package Main_Package.DB_Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DB_Prepared_Statements {
    private static final String url="jdbc:mysql://localhost:3306/hotel_db";
    private static final String username="root";
    private static final String pwd="";
    public static void main(String[] args) {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(url,username,pwd);
            System.out.println("Connection Done");
            // when just one parameter
//            String q="Select * from reservation_info where guest_name=?";
            String q="Select * from reservation_info where guest_name=? and room_number=?";//
            // multiple parameter use when same data available

            PreparedStatement pstmt=con.prepareStatement(q);
            // when just one
//            pstmt.setString(1,"Jaydeep");

            pstmt.setString(1,"Jaydeep");
//            pstmt.setInt(2,103);
            pstmt.setString(2,"103");
            ResultSet rs= pstmt.executeQuery();

            while(rs.next()){
                System.out.println("Reservation Id : "+rs.getInt("reservation_id")+"\nGuest Name " +
                        ": "+rs.getString("guest_name")+"\nRoom No. : "+rs.getInt("room_number")+
                        "\nContact No. : "+rs.getString("contact_number"));
                System.out.println();
            }
            rs.close();
            pstmt.close();
            con.close();

            System.out.println("Connection Close.");
        }catch (Exception e){
            System.out.println(e);
        }

    }

}
