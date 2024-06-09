package Main_Package.DB_Project;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DB_Image_Read {
    public static void main(String[] args) {
        //String q="Select * from image_table where image_id=?;";
        String q="Select image_data from image_table where image_id=(?);";
        String folderPath="G:\\Pictures\\";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_db",
                    "root","");
            System.out.println("Connection done.");

            PreparedStatement pstmt= con.prepareStatement(q);
            pstmt.setInt(1,1);
            ResultSet rs=pstmt.executeQuery();
            if(rs.next()){
                byte [] imageData=rs.getBytes("image_data");
                String imagepath=folderPath+"Db_godimage.jpg";
                OutputStream outSt=new FileOutputStream(imagepath);
                outSt.write(imageData);
                System.out.println("Image Save successfully");
            }else{
                System.out.println("image not found");
            }
            pstmt.close();
            con.close();
            System.out.println("Connection close");
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
