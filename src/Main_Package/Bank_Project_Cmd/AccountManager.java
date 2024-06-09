package Main_Package.Bank_Project_Cmd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountManager {
    Connection con;
    Scanner sc_user;
    AccountManager(Connection con,Scanner sc_user){
        this.con=con;
        this.sc_user=sc_user;
    }

    public void credit_money(long account_number) throws SQLException{
        sc_user.nextLine();
        System.out.print("Enter Amount : ");
        double amount=sc_user.nextDouble();
        System.out.print("Enter Security Pin : ");
        String security_pin=sc_user.next();
        try{
            con.setAutoCommit(false);
            if(account_number!=0){
                PreparedStatement pstmt= con.prepareStatement("Select * from accounts where " +
                        "account_number=? and security_pin=?;");
                pstmt.setLong(1,account_number);
                pstmt.setString(2,security_pin);
                ResultSet rs=pstmt.executeQuery();
                if (rs.next()) {
                    double current_balance = rs.getDouble("balance");
                    if (amount <= current_balance) {
                        String credit_Q = "Update accounts set balance=balance+? where " +
                                "account_number=?;";

                        PreparedStatement pstmt_Cr = con.prepareStatement(credit_Q);

                        pstmt_Cr.setDouble(1, amount);
                        pstmt_Cr.setLong(2, account_number);

                        int affectedRow = pstmt_Cr.executeUpdate();
                        if (affectedRow > 0) {
                            System.out.println("\n\t*** Transaction Successful ! ***");
                            System.out.println("Rs. " + amount + " Credited Successfully");
                            con.commit();
                            con.setAutoCommit(true);
                            return;
                        } else {
                            System.out.println("\n\t*** Transaction Failed ! ***");
                            con.rollback();
                            con.setAutoCommit(true);
                        }
                    } else {
                        System.out.println("\n\t*** Insufficient Balance ! ***");
                    }
                }else{
                    System.out.println("\n\t*** Invalid Security Pin ! ***");
                }
            }else{
                System.out.println("\n\t*** Invalid Account Number ! ***");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        con.setAutoCommit(true);
    }

    public void debit_money(long account_number) throws SQLException{
        sc_user.nextLine();
        System.out.print("Enter Amount : ");
        double amount=sc_user.nextDouble();
        System.out.print("Enter Security Pin : ");
        String security_pin=sc_user.next();
        try{
            con.setAutoCommit(false);
            if(account_number!=0){
                PreparedStatement pstmt= con.prepareStatement("Select * from accounts where " +
                        "account_number=? and security_pin=?;");
                pstmt.setLong(1,account_number);
                pstmt.setString(2,security_pin);
                ResultSet rs=pstmt.executeQuery();
                if (rs.next()){
                    double current_balance=rs.getDouble("balance");
                    if(amount<=current_balance){
                        String debit_Q="Update accounts set balance=balance-? where " +
                                "account_number=?;";

                        PreparedStatement pstmt_Dr=con.prepareStatement(debit_Q);

                        pstmt_Dr.setDouble(1,amount);
                        pstmt_Dr.setLong(2,account_number);
                        int affectedRow=pstmt_Dr.executeUpdate();

                        if (affectedRow>0){
                            System.out.println("\n\t*** Transaction Successful ! ***");
                            System.out.println("Rs. "+amount+" Debited Successfully");
                            con.commit();
                            con.setAutoCommit(true);
                            return;
                        }else {
                            System.out.println("\n\t*** Transaction Failed ! ***");
                            con.rollback();
                            con.setAutoCommit(true);
                        }
                    } else {
                        System.out.println("\n\t*** Insufficient Balance ! ***");
                    }
                }else{
                    System.out.println("\n\t*** Invalid Security Pin ! ***");
                }
            }else{
                System.out.println("\n\t*** Invalid Account Number ! ***");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        con.setAutoCommit(true);
    }

    public void transfer_money(long sender_account_number) throws SQLException {
        sc_user.nextLine();
        System.out.print("\nEnter Receiver Account Number : ");
        long receiver_account_number=sc_user.nextLong();
        System.out.print("Enter Amount : ");
        double amount=sc_user.nextDouble();
        System.out.print("Enter Security Pin : ");
        String security_pin=sc_user.next();
        try{
            con.setAutoCommit(false);
            if(sender_account_number!=0 && receiver_account_number!=0){
                PreparedStatement pstmt= con.prepareStatement("Select * from accounts where " +
                        "account_number=? and security_pin=?;");
                pstmt.setLong(1,sender_account_number);
                pstmt.setString(2,security_pin);
                ResultSet rs=pstmt.executeQuery();
                if (rs.next()) {
                    double current_balance = rs.getDouble("balance");
                    if (amount <= current_balance) {
                        //Write debit and credit queries
                        String debit_Q = "Update accounts set balance=balance-? where " +
                                "account_number=?;";
                        String credit_Q = "Update accounts set balance=balance+? where " +
                                "account_number=?;";

                        //Write debit and credit Prepared statement
                        PreparedStatement pstmt_Cr = con.prepareStatement(credit_Q);
                        PreparedStatement pstmt_Dr = con.prepareStatement(debit_Q);

                        //set values of debit and credit Prepared statement
                        pstmt_Cr.setDouble(1, amount);
                        pstmt_Cr.setLong(2, receiver_account_number);
                        pstmt_Dr.setDouble(1, amount);
                        pstmt_Dr.setLong(2, sender_account_number);
                        int affectedRow1 = pstmt_Dr.executeUpdate();
                        int affectedRow2 = pstmt_Cr.executeUpdate();
                        if (affectedRow1 > 0 && affectedRow2 > 0) {
                            System.out.println("\n\t*** Transaction Successful ! ***");
                            System.out.println("Rs. " + amount + " Transferred Successfully");
                            con.commit();
                            con.setAutoCommit(true);
                            return;
                        } else {
                            System.out.println("\n\t*** Transaction Successful ! ***");
                            con.rollback();
                            con.setAutoCommit(true);
                        }
                    } else {
                        System.out.println("\n\t*** Insufficient Balance ! ***");
                    }
                }else{
                    System.out.println("\n\t*** Invalid Security Pin ! ***");
                }
            }else{
                System.out.println("\n\t*** Invalid Account Number ! ***");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        con.setAutoCommit(true);
    }

    public void getBalance(long account_number){
        sc_user.nextLine();
        System.out.print("\nEnter Security Pin : ");
        String security_pin=sc_user.next();
        try{
            PreparedStatement pstmt=con.prepareStatement("Select balance from accounts where " +
                    "account_number=? and security_pin=?;");
            pstmt.setLong(1,account_number);
            pstmt.setString(2,security_pin);
            ResultSet rs=pstmt.executeQuery();
            if (rs.next()){
                double balance= rs.getDouble("balance");
                System.out.println("Balance : "+balance);
            }else {
                System.out.println("\n\t*** Invalid pin ***");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
