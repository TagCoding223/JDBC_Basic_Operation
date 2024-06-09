package Main_Package.DB_Project;

import java.sql.*;
public class DB_Read {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo","root","");
            System.out.println("Connection Done");

            Statement stmt=con.createStatement();
            String q="Select * from emp;";
            ResultSet rs= stmt.executeQuery(q);
            int id,age;
            String name;
            while(rs.next()){
                id=rs.getInt("id");
                name=rs.getString("name");
                age=rs.getInt("age");
                System.out.println();
                System.out.println("****************************");
                System.out.println("Id : "+id);
                System.out.println("Name : "+name);
                System.out.println("Age : "+age);
            }
            con.close();
            stmt.close();
            rs.close();
            System.out.println("Connection Close.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
