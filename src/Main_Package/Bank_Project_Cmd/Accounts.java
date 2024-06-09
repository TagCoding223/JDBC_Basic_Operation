package Main_Package.Bank_Project_Cmd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Accounts {
    private Connection con;
    private Scanner sc_user;
    Accounts(Connection con,Scanner sc_user){
        this.con=con;
        this.sc_user=sc_user;
    }

    public long open_account(String email){
        String full_name,security_pin;
        double balance;
        if(!account_exist(email )){
            String open_acc_q="Insert into accounts(account_number,full_name,email," +
                    "balance,security_pin) values(?,?,?,?,?);";
            System.out.print("\nEnter Full Name : ");
            full_name=sc_user.next();
            full_name=full_name+" "+sc_user.nextLine();
            //sc_user.nextLine();
            System.out.print("Enter Initial Amount : ");
            balance=sc_user.nextDouble();
            System.out.print("Enter Security Pin : ");
            security_pin=sc_user.next();
            try{
                long account_number=generateAccountNumber();
                PreparedStatement pstmt=con.prepareStatement(open_acc_q);
                pstmt.setLong(1,account_number);
                pstmt.setString(2,full_name);
                pstmt.setString(3,email);
                pstmt.setDouble(4,balance);
                pstmt.setString(5,security_pin);
                int affectedRow=pstmt.executeUpdate();
                if (affectedRow>0){
                    return account_number;
                }else {
                    throw new RuntimeException("\n\t*** Account Creation failed !!! ***");
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }
        throw new RuntimeException("\n\t*** Account Already Exist !!! ***");
    }

    public long getAccountNumber(String email){
        String q="Select account_number from accounts where email=?;";
        try {
            PreparedStatement pstmt =con.prepareStatement(q);
            pstmt.setString(1,email);
            ResultSet rs=pstmt.executeQuery();
            if(rs.next()){
                return rs.getLong("account_number");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        throw new RuntimeException("\n\t*** Account Number Doesn't Exist !!! ***");
    }

    private long generateAccountNumber(){
        try {
            Statement stmt= con.createStatement();
            ResultSet rs=stmt.executeQuery("Select account_number from accounts order by " +
                    "account_number DESC LIMIT 1;");
            if (rs.next()){
                long last_acc_number=rs.getLong("account_number");
                return last_acc_number+1;
            }else{
                return 10000100;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return 10000100;
    }

    public boolean account_exist(String email){
        String q="Select account_number from accounts where email=?;";
        try{
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setString(1,email);
            ResultSet rs=pstmt.executeQuery();
            if(rs.next()){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
}
