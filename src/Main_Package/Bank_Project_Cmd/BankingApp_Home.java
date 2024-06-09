package Main_Package.Bank_Project_Cmd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class BankingApp_Home {
    static Connection con;
    static Scanner sc_user=new Scanner(System.in);
    static String email;
    static long account_number;
    static int choice,choice2;

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306" +
                    "/bankingapp_db","root","");

            User user=new User(con,sc_user);
            Accounts accounts=new Accounts(con,sc_user);
            AccountManager accountManager=new AccountManager(con, sc_user);

            while (true){
                System.out.println("**** Welcome to Banking System ****\n");
                System.out.println("1 , Register");
                System.out.println("2 , Login");
                System.out.println("3 , Exit");
                System.out.print("Enter your choice : ");
                choice=sc_user.nextInt();

                switch (choice){
                    case 1:
                        user.register();
                        break;
                    case 2:
                        email= user.login();
                        if(email!=null){
                            System.out.println("\nUser Logged In !!!");
                            if(!accounts.account_exist(email)){
                                System.out.println("\n1 , Open a new Bank Account");
                                System.out.println("2 , Exit");
                                System.out.print("Enter your choice : ");
                                choice=sc_user.nextInt();
                                if (choice==1){
                                    account_number = accounts.open_account(email);
                                    System.out.println("\n Account Created Successfully !!!");
                                    System.out.println("Your Account number is : "+account_number);
                                }else{
                                    break;
                                }
                            }
                            account_number=accounts.getAccountNumber(email);
                            choice2=0;
                            while (choice2!=5){
                                System.out.println("\n1 , Debit Money");
                                System.out.println("2 , Credit Money");
                                System.out.println("3 , Transfer Money");
                                System.out.println("4 , Check Balance");
                                System.out.println("5 , Log Out");
                                System.out.print("Enter your choice : ");
                                choice2=sc_user.nextInt();
                                switch (choice2){
                                    case 1:
                                        accountManager.debit_money(account_number);
                                        break;
                                    case 2:
                                        accountManager.credit_money(account_number);
                                        break;
                                    case 3:
                                        accountManager.transfer_money(account_number);
                                        break;
                                    case 4:
                                        accountManager.getBalance(account_number);
                                        break;
                                    case 5:
                                        break;
                                    default:
                                        System.out.println("\n\t*** Enter Valid Choice ***");
                                        break;
                                }
                            }
                        }
                        else {
                            System.out.println("\n\t*** Incorrect Email or Password / You are not" +
                                    " Register in Bank ***");
                        }
                        break;
                    case 3:
                        System.out.println("Thank You For Using Banking System !!!");
                        System.out.print("System Exiting");
                        int i=0;
                        while(i<3){
                            Thread.sleep(400);
                            System.out.print(".");
                            i++;
                        }
                        return;
                    default:
                        System.out.println("\n\t*** Enter Valid Choice ***");
                        break;
                }
            }
            //con.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
