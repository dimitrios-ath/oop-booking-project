import java.awt.event.*;
import javax.swing.*;
import net.miginfocom.swing.*;

import java.util.Map;
/*
 * Created by JFormDesigner on Fri Jan 07 13:21:13 EET 2022
 */



/**
 * @author asdfasdfa
 */
public class adminForm extends JPanel {
    JFrame jframe;
    adminForm currentForm;
    private Admin admin;
    private MainUI mainUI;
    private Map<Integer,Reservation> reservations;
    private Map<Integer,Room> rooms;
    private Map<String,Authentication> users;
    private Map<String,Customer> customers;
    private Map<String,Provider> providers;
    private Map<String,Admin> admins;
    private Map<Integer,Message> messages;

    public void setCurrentForm(adminForm currentForm) {
        this.currentForm = currentForm;
    }

    public adminForm(JFrame jframe, Map<Integer,Reservation> reservations, Map<Integer,Room> rooms,
                     Map<String,Authentication> users, Map<String,Customer> customers, Map<String,Provider> providers,
                     Map<String,Admin> admins, Map<Integer,Message> messages, MainUI mainUI, Admin admin) {
        this.jframe = jframe;
        this.reservations = reservations;
        this.rooms = rooms;
        this.users = users;
        this.customers = customers;
        this.providers = providers;
        this.admins = admins;
        this.messages = messages;
        this.mainUI = mainUI;
        this.admin = admin;
        initComponents();
    }

    private void messagesButtonClick(ActionEvent e) {
        messageForm messageForm = new messageForm(
                this.jframe, this.reservations, this.rooms, this.users, this.customers, this.providers,
                this.admins, this.messages, this.mainUI, this.admin.getUsername());
        messageForm.setCurrentForm(messageForm);
        this.jframe.add(messageForm);
        this.currentForm.setVisible(false);
    }

    private void logoutButtonClick(ActionEvent e) {
        loginForm loginForm = new loginForm(jframe, this.reservations, this.rooms, this.users, this.customers,
                this.providers, this.admins, this.messages, this.mainUI);
        loginForm.setCurrentForm(loginForm);
        jframe.add(loginForm);
        this.currentForm.setVisible(false);
    }

    private void exitButtonClick(ActionEvent e) {this.mainUI.saveAndExit();}

    private void searchReservationsButtonClick(ActionEvent e) {
        searchReservationsForm searchReservationsForm = new searchReservationsForm(jframe, this.reservations,
                this.rooms, this.users, this.customers, this.providers, this.admins, this.messages,
                this.mainUI, this.admin);
        searchReservationsForm.setCurrentForm(searchReservationsForm);
        jframe.add(searchReservationsForm);
        this.currentForm.setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - asdfasdfa
        label1 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();
        button6 = new JButton();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder
        ( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border. TitledBorder. CENTER, javax. swing. border
        . TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt
        . Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void
        propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( )
        ; }} );
        setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- label1 ----
        label1.setText("Admin panel");
        add(label1, "cell 3 0");

        //---- button1 ----
        button1.setText("Search reservations");
        button1.addActionListener(e -> searchReservationsButtonClick(e));
        add(button1, "cell 3 2");

        //---- button2 ----
        button2.setText("Search users");
        add(button2, "cell 3 3");

        //---- button3 ----
        button3.setText("Approve new user");
        add(button3, "cell 3 4");

        //---- button4 ----
        button4.setText("Messages");
        button4.addActionListener(e -> messagesButtonClick(e));
        add(button4, "cell 3 5");

        //---- button5 ----
        button5.setText("Log out");
        button5.addActionListener(e -> logoutButtonClick(e));
        add(button5, "cell 3 6");

        //---- button6 ----
        button6.setText("Exit");
        button6.addActionListener(e -> exitButtonClick(e));
        add(button6, "cell 3 7");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - asdfasdfa
    private JLabel label1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}