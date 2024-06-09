package Main_Package.Bank_Project_GUI;

import javax.swing.*;

public class Exit{
    Exit(){
        JFrame frame=new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocation(690,400);
        int result=JOptionPane.showConfirmDialog(frame,"You want to exit ?","Confirm Exit",
                    JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(result==JOptionPane.YES_OPTION){
            System.exit(0);
        }else{
            frame.setVisible(false);
        }
    }
}
