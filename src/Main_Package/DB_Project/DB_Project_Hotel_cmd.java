package Main_Package.DB_Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;



public class DB_Project_Hotel_cmd {
    private static int reservation_id;
    private static int room_no;
    private static String guestName;
    private static String contact_no;
    private static String tDate;

    private static String q;
    private static ResultSet rs;
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_db","root","");
            System.out.println("Connection Done");
            Statement stmt=con.createStatement();

            Scanner user=new Scanner(System.in);
            int choose;
            while(true){
                System.out.println();
                System.out.println("Hotel Management System");
                System.out.println("1 , Reserve a room");
                System.out.println("2 , View reservation");
                System.out.println("3 , Get room number");
                System.out.println("4 , Update reservation");
                System.out.println("5 , Delete reservation");
                System.out.println("6 , Exit");
                System.out.print("Choose an option : ");
                choose= user.nextInt();
                switch (choose){
                    case 1:
                        reserveRoom(con,user,stmt);
                        break;
                    case 2:
                        viewReservation(con,stmt);
                        break;
                    case 3:
                        getRoomNumber(con,user,stmt);
                        break;
                    case 4:
                        updateReservation(con,user,stmt);
                        break;
                    case 5:
                        deleteReservation(con,user,stmt);
                        break;
                    case 6:
                        exit(con);
                        user.close();
                        return;
                    default:
                        System.out.println("Invalid Choose . Try again !!!");
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    private static void reserveRoom(Connection con,Scanner user,Statement stmt){
        try{
            System.out.print("Enter Guest Name : ");
            guestName=user.next();
            guestName=guestName+" "+user.next();
            user.nextLine();
            System.out.print("Enter Room Number : ");
            room_no=user.nextInt();
            System.out.print("Enter Contact No. : ");
            contact_no=user.next();

            q="Insert into reservation_info(guest_name,room_number,contact_number) values('"+guestName+"','"+room_no+"','"+contact_no+"');";

            int x=stmt.executeUpdate(q);
            if(x>0){
                System.out.println("Reservation Successfully !!!");
            }else{
                System.out.println("Reservation Fail !!!");
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }

    private static void viewReservation(Connection con,Statement stmt){
        try{
            q="Select reservation_id,guest_name,room_number,contact_number," +
                    "reservation_date from reservation_info;";
            rs=stmt.executeQuery(q);
            System.out.println("Current Reservations :");
            System.out.println("*----------------*---------------*------"+
                    "-----------*----------------*------------------*");
            System.out.println("| Reservation ID | Guest Name    | Room " +
                    "Number     | Contact Number | Date & Time      *");
            System.out.println("*----------------*---------------*------"+
                    "-----------*----------------*------------------*");

            while(rs.next()){
                reservation_id=rs.getInt("reservation_id");
                guestName=rs.getString("guest_name");
                room_no=rs.getInt("room_number");
                contact_no=rs.getString("contact_number");
                tDate=rs.getTimestamp("reservation_date").toString();

                //System.out.println("| %-14d | %-15s | %-13d | %-20s | %-19s   |\n"+
                  //      (reservation_id)+(guestName)+(room_no)+(contact_no+tDate));
                System.out.println("|   "+reservation_id+"  |   "+guestName+"   |   "+room_no+"  " +
                        "   |   "+contact_no+"      |   "+tDate+"   |");

            }
            System.out.println("*----------------*---------------*------"+
                    "-----------*----------------*------------------*");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    private static void getRoomNumber(Connection con,Scanner user,Statement stmt){
        try{
            System.out.print("Enter Reservation Id : ");
            reservation_id=user.nextInt();
            System.out.print("Enter Guest Name : ");
            guestName=user.next();
            user.nextLine();
            q="Select room_number from reservation_info where reservation_id="+reservation_id+" and " +
                    "guest_name='"+guestName+"';";
            rs= stmt.executeQuery(q);
            if(rs.next()){
                room_no=rs.getInt("room_number");
                System.out.println("Room number for Reservation ID "+reservation_id+" and Guest "+guestName+" is : "+room_no);
            }else{
                System.out.println("Reservation not found for the given ID ang guest name.");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    private static void updateReservation(Connection con,Scanner user,Statement stmt){
        try{
            System.out.print("Enter Reservation Id to update : ");
            reservation_id=user.nextInt();

            if(!reservationExists(con,reservation_id,stmt)){
                System.out.println("Reservation not found for given Id .");
                return;
            }

            System.out.print("Enter new guest name : ");
            guestName=user.next();
            user.nextLine();
            System.out.print("Enter new room number : ");
            room_no=user.nextInt();
            System.out.print("Enter new contact number : ");
            contact_no=user.next();
            user.nextLine();

            q="Update reservation_info set guest_name='"+guestName+"',room_number='"+room_no+"'," +
                    "contact_number='"+contact_no+"' where reservation_id='"+reservation_id+"';";
            int x= stmt.executeUpdate(q);
            if(x>0){
                System.out.println("Reservation update successfully .");
            }else{
                System.out.println("Reservation update failed.");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private static void deleteReservation(Connection con, Scanner user,Statement stmt){
        try{
            System.out.print("Enter reservation id to delete : ");
            reservation_id=user.nextInt();

            if(!reservationExists(con,reservation_id,stmt)){
                System.out.println("Reservation not found for given Id .");
                return;
            }

            q="Delete from reservation_info where reservation_id='"+reservation_id+"';";
            int x= stmt.executeUpdate(q);
            if(x>0){
                System.out.println("Reservation delete successfully .");
            }else{
                System.out.println("Reservation delete failed.");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private static boolean reservationExists(Connection con,int id,Statement stmt){
        try{
            q="Select reservation_id from reservation_info where reservation_id='"+id+"';";
            rs= stmt.executeQuery(q);
            return rs.next();//if there's a result, the reservation exists (rs.next() return
            // boolean)
        }catch(Exception e){
            e.printStackTrace();
            return false; //handle databse errors as needed
        }
    }
    public static void exit(Connection con) throws InterruptedException{
        try{
            System.out.print("Exiting System");
            int i=5;
            while (i!=0){
                System.out.print(".");
                Thread.sleep(450);
                i--;
            }
            System.out.println();
            System.out.println("ThankYou For Using Hotel Reservation System.");
            System.out.println("Connection Close");
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
/*
        executeUpdate() ---> Insert , Update , Delete ---> return integer ---> show row affected

        executeQuery() ---> Retrieve (read) ---> return data
 */