package Main_Package.DB_Project;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DB_Image {
    public static void main(String[] args) {
        String impath="G:\\vscode\\WebProjects\\ImagePicker\\image\\god\\g5.jpg";
        String q="Insert into image_table(image_data) values(?);";
        try{
            FileInputStream fileInSt=new FileInputStream(impath);
            byte [] imData=new byte[fileInSt.available()];
            fileInSt.read(imData);

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_db",
                    "root","");
            System.out.println("Connection done");

            PreparedStatement pstmt=con.prepareStatement(q);
            pstmt.setBytes(1,imData);
            int x= pstmt.executeUpdate();

            if(x>0){
                System.out.println("image upload successfully , "+x+" rows affected .");
            }else{
                System.out.println("image upload successfully ");
            }

            pstmt.close();
            con.close();
            System.out.println("Connection close");
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
