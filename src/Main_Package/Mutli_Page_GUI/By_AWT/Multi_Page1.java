package Main_Package.Mutli_Page_GUI.By_AWT;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Multi_Page1 extends Frame implements ActionListener {
    Multi_Page1(){
        setSize(400,400);
        setLocationRelativeTo(null);
        setTitle("Page 1");
        setLayout(new FlowLayout());
        setVisible(true);
        setBackground(Color.GREEN);
        Label l=new Label("Page 1",Label.CENTER);
        add(l);

        Button b=new Button("Next Page");
        add(b);
        b.addActionListener(this);

        System.out.println("Page 1");

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);// this is use because window will be visible false but program
                // still running in background check when you comment 9it and try to close this
                // program at this page
            }
        });
    }
    public void actionPerformed(ActionEvent e){
        setVisible(false);
        new Multi_Page2().setVisible(true);
    }
    public static void main(String[] args) {
        new Multi_Page1();
    }
}
