package Main_Package.DB_Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DB_Transaction_Handling {
    public static void main(String[] args) {
        String withdrawQ="Update account set balance=balance-? where account_number=?;";
        String depositQ="Update account set balance=balance+? where account_number=?;";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_db",
                    "root","");
            System.out.println("Connection done");
            con.setAutoCommit(false);// I want all thing commit manually

            try {
                PreparedStatement pstmtWithdraw = con.prepareStatement(withdrawQ);
                pstmtWithdraw.setDouble(1, 500.50);
                pstmtWithdraw.setString(2, "100025");

                PreparedStatement pstmtDeposit = con.prepareStatement(depositQ);
                pstmtDeposit.setDouble(1, 500.50);
                pstmtDeposit.setString(2, "100025");
                //pstmtWithdraw.setString(2, "100025"); //if I am use this line at place of 23 then
                // transaction fail

                int xWith=pstmtWithdraw.executeUpdate();
                int xDep=pstmtDeposit.executeUpdate();

                // check operation(transaction) done
                if(xWith>0 && xDep>0){ // in this case all two rows are affected then transaction
                    // commit
                    // Transaction handling
                    con.commit(); // if transaction success
                    System.out.println("Transaction Successfully Done !!!");
                }else{
                    con.rollback();// if any error occur in transaction then all process rollback
                    // means nothing be happened.
                    // rollback -> you perform previous operation reverse it.
                    System.out.println("Transaction Fail !!!");
                }

                pstmtWithdraw.close();
                pstmtDeposit.close();
            }catch(Exception e){
//                con.rollback();// if any error occur in transaction then all process rollback
//                // means nothing be happened.
//                System.out.println("Transaction Fail !!!");

                System.out.println(e);
            }
            con.close();
            System.out.println("Connection close");
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
