package Main_Package.Bank_Project_Cmd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class User {
    private Connection con;
    private Scanner sc_user;

    User(Connection con,Scanner sc_user){
        this.con=con;
        this.sc_user=sc_user;
    }
    public void register(){
        String full_name,email,password;
        sc_user.nextLine();
        System.out.print("\nEnter Full Name ( Format : FirstName LastName ) : ");
        full_name=sc_user.next();
        full_name=full_name+" "+sc_user.nextLine();
        //sc_user.nextLine();
        System.out.print("Enter Your Email : ");
        email=sc_user.nextLine();
        System.out.print("Enter your Password : ");
        password=sc_user.nextLine();
        if(user_exist(email)){
            System.out.println("\nUser Already Exists for this Email Address!!!");
        }
        String register_query="Insert into user(full_name,email,password) values (?,?,?);";
        try {
            PreparedStatement pstmt= con.prepareStatement(register_query);
            pstmt.setString(1,full_name);
            pstmt.setString(2,email);
            pstmt.setString(3,password);
            int affectedRows=pstmt.executeUpdate();
            if(affectedRows>0){
                System.out.println("Registration Successfully !!!");
            }else{
                System.out.println("registration Failed !!!");
            }
        }catch (Exception e){
            System.out.println("Duplicate entry '"+email+"' .");
        }
    }

    public String login(){
        String email,password;
        sc_user.nextLine();
        System.out.print("\nEmail : ");
        email=sc_user.nextLine();
        System.out.print("Password : ");
        password=sc_user.nextLine();

        String login_q="Select * from user where email=? and password=?;";
        try{
            PreparedStatement pstmt= con.prepareStatement(login_q);
            pstmt.setString(1,email);
            pstmt.setString(2,password);
            ResultSet rs=pstmt.executeQuery();
            if(rs.next()){
                return email;
            }else {
                return null;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public boolean user_exist(String email){
        String check_q="Select * from user where email=?;";
        try{
            PreparedStatement pstmt = con.prepareStatement(check_q);
            pstmt.setString(1,email);
            ResultSet rs=pstmt.executeQuery();
            if(rs.next()){
                return true;
            }
            else{
                return false;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
}
