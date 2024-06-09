package Main_Package.DB_Project;

import java.sql.*;
public class DB_Update {
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo","root","");
            System.out.println("Connection Successfully !!!");
            Statement stmt=con.createStatement();
            //UPDATE `emp` SET `id`='[value-1]',`name`='[value-2]',`age`='[value-3]' WHERE 1
            String q="Update emp set id='103',name='maya',age='23' where id='101';";
            int x=stmt.executeUpdate(q);
            if (x>0){
                System.out.println("Update successfully "+x+" rows affected.");
            }else{
                System.out.println("Update fail.");
            }
            stmt.close();
            con.close();
            System.out.println("Connection Close Successfully !!!");
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
