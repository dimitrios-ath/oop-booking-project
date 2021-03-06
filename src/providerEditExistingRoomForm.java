import java.awt.*;
import java.util.Map;
import java.util.Objects;
import javax.swing.*;

public class providerEditExistingRoomForm extends JPanel {
    JFrame jframe;
    providerEditExistingRoomForm currentForm;
    private final Provider provider;
    private final MainUI mainUI;
    private final Map<Integer,Reservation> reservations;
    private final Map<Integer,Room> rooms;
    private final Map<String,Authentication> users;
    private final Map<String,Customer> customers;
    private final Map<String,Provider> providers;
    private final Map<String,Admin> admins;
    private final Map<Integer,Message> messages;
    private final Integer idToEdit;

    public void setCurrentForm(providerEditExistingRoomForm currentForm) {
        this.currentForm = currentForm;
    }

    /**
     * Constructor of providerEditExistingRoomForm. It sets the form attributes based on the selected room
     * characteristics
     *
     * @param jframe
     * @param reservations
     * @param rooms
     * @param users
     * @param customers
     * @param providers
     * @param admins
     * @param messages
     * @param mainUI
     * @param provider
     * @param id
     */
    public providerEditExistingRoomForm(JFrame jframe, Map<Integer,Reservation> reservations, Map<Integer,Room> rooms,
                                        Map<String,Authentication> users, Map<String,Customer> customers, Map<String, Provider> providers,
                                        Map<String,Admin> admins, Map<Integer,Message> messages, MainUI mainUI, Provider provider, Integer id) {
        this.jframe = jframe;
        this.reservations = reservations;
        this.rooms = rooms;
        this.users = users;
        this.customers = customers;
        this.providers = providers;
        this.admins = admins;
        this.messages = messages;
        this.mainUI = mainUI;
        this.provider = provider;
        this.idToEdit = id;
        jframe.setPreferredSize(new Dimension(435 , 630));
        jframe.pack();
        initComponents();
        
        if (Objects.equals(rooms.get(idToEdit).getType(), "hotel")) {
            comboBox1.setSelectedIndex(0);
        } else if (Objects.equals(rooms.get(idToEdit).getType(), "room")) {
            comboBox1.setSelectedIndex(1);
        } else {comboBox1.setSelectedIndex(2);}
        textField1.setText(rooms.get(idToEdit).getName());
        spinner3.setValue(rooms.get(idToEdit).getPrice());
        spinner2.setValue(rooms.get(idToEdit).getM2());
        spinner1.setValue(rooms.get(idToEdit).getCapacity());
        if (rooms.get(idToEdit).getLongTime()) {checkBox1.setSelected(true);}
        if (rooms.get(idToEdit).getWifi()) {checkBox2.setSelected(true);}
        if (rooms.get(idToEdit).getParking()) {checkBox3.setSelected(true);}
        if (rooms.get(idToEdit).getAirCondition()) {checkBox4.setSelected(true);}
        if (rooms.get(idToEdit).getBalcony()) {checkBox5.setSelected(true);}
        if (rooms.get(idToEdit).getFridge()) {checkBox6.setSelected(true);}
        if (rooms.get(idToEdit).getKitchen()) {checkBox7.setSelected(true);}
        if (rooms.get(idToEdit).getTv()) {checkBox8.setSelected(true);}
        if (rooms.get(idToEdit).getSmoking()) {checkBox9.setSelected(true);}
        if (rooms.get(idToEdit).getPets()) {checkBox10.setSelected(true);}
        
    }

    /**
     * Returns to the provider panel
     */
    private void cancelClick() {
        providerForm providerForm = new providerForm(this.jframe, this.reservations, this.rooms, this.users, this.customers,
                this.providers, this.admins, this.messages, this.mainUI, this.provider);
        providerForm.setCurrentForm(providerForm);
        this.jframe.add(providerForm);
        this.currentForm.setVisible(false);
    }

    /**
     * If the form input is valid, updates the room characteristics
     */
    private void editRoomClick() {
        boolean validInput = true;

        String type;
        if (comboBox1.getSelectedItem() == "Hotel") {
            type = "hotel";
        } else if (comboBox1.getSelectedItem() == "Room") {
            type = "room";
        } else {type = "apartment";}

        String name = textField1.getText();
        if (Objects.equals(name, "")){
            textField1.setForeground(Color.red);
            validInput = false;
        } else {
            textField1.setForeground(null);
        }

        double price = 0;
        try {
            double scannedPrice = Double.parseDouble(spinner3.getValue().toString());
            spinner3.setForeground(null);
            price = scannedPrice;
        } catch (NumberFormatException ignored) {
            spinner3.setForeground(Color.red);
            validInput = false;
        }

        int size = 0;
        try {
            int scannedSize = Integer.parseInt(spinner2.getValue().toString());
            spinner2.setForeground(null);
            size = scannedSize;
        } catch (NumberFormatException ignored) {
            spinner2.setForeground(Color.red);
            validInput = false;
        }

        int capacity = 0;
        try {
            int scannedCapacity = Integer.parseInt(spinner1.getValue().toString());
            spinner1.setForeground(null);
            capacity = scannedCapacity;
        } catch (NumberFormatException ignored) {
            spinner1.setForeground(Color.red);
            validInput = false;
        }

        boolean longTime = checkBox1.isSelected();
        boolean wifi = checkBox2.isSelected();
        boolean parking = checkBox3.isSelected();
        boolean airCondition = checkBox4.isSelected();
        boolean balcony = checkBox5.isSelected();
        boolean fridge = checkBox6.isSelected();
        boolean kitchen = checkBox7.isSelected();
        boolean tv = checkBox8.isSelected();
        boolean smoking = checkBox9.isSelected();
        boolean pets = checkBox10.isSelected();

        if (validInput) {
            rooms.put(idToEdit, new Room(idToEdit, provider.getUsername(), name, type, longTime, capacity, price,
                    size, wifi, parking, airCondition, balcony, fridge, kitchen, tv, smoking, pets));
            providerForm providerForm = new providerForm(this.jframe, this.reservations, this.rooms, this.users,
                    this.customers, this.providers, this.admins, this.messages, this.mainUI, this.provider);
            providerForm.setCurrentForm(providerForm);
            this.jframe.add(providerForm);
            this.currentForm.setVisible(false);
        }
    }

    /**
     *   Form generator
     */
    private void initComponents() {
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();
        comboBox1 = new JComboBox<>();
        JLabel label4 = new JLabel();
        textField1 = new JTextField();
        JLabel label5 = new JLabel();
        JLabel label6 = new JLabel();
        JLabel label16 = new JLabel();
        JLabel label1 = new JLabel();
        checkBox1 = new JCheckBox();
        JLabel label7 = new JLabel();
        checkBox2 = new JCheckBox();
        JLabel label8 = new JLabel();
        checkBox3 = new JCheckBox();
        JLabel label9 = new JLabel();
        checkBox4 = new JCheckBox();
        JLabel label10 = new JLabel();
        checkBox5 = new JCheckBox();
        JLabel label11 = new JLabel();
        checkBox6 = new JCheckBox();
        JLabel label12 = new JLabel();
        checkBox7 = new JCheckBox();
        JLabel label13 = new JLabel();
        checkBox8 = new JCheckBox();
        JLabel label14 = new JLabel();
        checkBox9 = new JCheckBox();
        JLabel label15 = new JLabel();
        checkBox10 = new JCheckBox();
        JButton button2 = new JButton();
        JButton button1 = new JButton();
        spinner1 = new JSpinner();
        spinner2 = new JSpinner();
        spinner3 = new JSpinner();

        //======== this ========
        setForeground(Color.white);
        setBackground(new Color(51, 102, 255));
        setLayout(null);

        //---- label2 ----
        label2.setText("Edit existing room");
        label2.setFont(new Font("Tahoma", Font.BOLD, 22));
        label2.setForeground(Color.white);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        add(label2);
        label2.setBounds(new Rectangle(new Point(120, 20), label2.getPreferredSize()));

        //---- label3 ----
        label3.setText("Type:");
        label3.setFont(new Font("Tahoma", Font.BOLD, 14));
        label3.setForeground(Color.white);
        label3.setHorizontalAlignment(SwingConstants.RIGHT);
        add(label3);
        label3.setBounds(35, 70, 165, 25);

        //---- comboBox1 ----
        comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
            "Hotel",
            "Room",
            "Apartment"
        }));
        comboBox1.setFont(new Font("Tahoma", Font.PLAIN, 14));

        comboBox1.setForeground(Color.black);
        add(comboBox1);
        comboBox1.setBounds(225, 70, 115, 25);

        //---- label4 ----
        label4.setText("Name:");
        label4.setFont(new Font("Tahoma", Font.BOLD, 14));
        label4.setForeground(Color.white);
        label4.setHorizontalAlignment(SwingConstants.RIGHT);
        add(label4);
        label4.setBounds(35, 100, 165, 25);

        //---- textField1 ----
        textField1.setFont(new Font("Tahoma", Font.PLAIN, 14));

        textField1.setForeground(Color.black);
        add(textField1);
        textField1.setBounds(225, 100, 115, 25);

        //---- label5 ----
        label5.setText("Price ($/night):");
        label5.setFont(new Font("Tahoma", Font.BOLD, 14));
        label5.setForeground(Color.white);
        label5.setHorizontalAlignment(SwingConstants.RIGHT);
        add(label5);
        label5.setBounds(35, 130, 165, 25);

        //---- label6 ----
        label6.setText("Size (m2):");
        label6.setFont(new Font("Tahoma", Font.BOLD, 14));
        label6.setForeground(Color.white);
        label6.setHorizontalAlignment(SwingConstants.RIGHT);
        add(label6);
        label6.setBounds(35, 160, 165, 25);

        //---- label16 ----
        label16.setText("Capacity:");
        label16.setFont(new Font("Tahoma", Font.BOLD, 14));
        label16.setForeground(Color.white);
        label16.setHorizontalAlignment(SwingConstants.RIGHT);
        add(label16);
        label16.setBounds(35, 190, 165, 25);

        //---- label1 ----
        label1.setText("Long term reservation:");
        label1.setFont(new Font("Tahoma", Font.BOLD, 14));
        label1.setForeground(Color.white);
        label1.setHorizontalAlignment(SwingConstants.RIGHT);
        add(label1);
        label1.setBounds(35, 225, 165, 25);

        //---- checkBox1 ----
        checkBox1.setBackground(new Color(51, 102, 255));
        add(checkBox1);
        checkBox1.setBounds(220, 225, 30, checkBox1.getPreferredSize().height);

        //---- label7 ----
        label7.setText("Free WiFi:");
        label7.setFont(new Font("Tahoma", Font.BOLD, 14));
        label7.setForeground(Color.white);
        label7.setHorizontalAlignment(SwingConstants.RIGHT);
        add(label7);
        label7.setBounds(35, 255, 165, 25);

        //---- checkBox2 ----
        checkBox2.setBackground(new Color(51, 102, 255));
        add(checkBox2);
        checkBox2.setBounds(220, 255, 25, checkBox2.getPreferredSize().height);

        //---- label8 ----
        label8.setText("Free Parking:");
        label8.setFont(new Font("Tahoma", Font.BOLD, 14));
        label8.setForeground(Color.white);
        label8.setHorizontalAlignment(SwingConstants.RIGHT);
        add(label8);
        label8.setBounds(35, 285, 165, 25);

        //---- checkBox3 ----
        checkBox3.setBackground(new Color(51, 102, 255));
        add(checkBox3);
        checkBox3.setBounds(220, 285, 20, checkBox3.getPreferredSize().height);

        //---- label9 ----
        label9.setText("Air condition:");
        label9.setFont(new Font("Tahoma", Font.BOLD, 14));
        label9.setForeground(Color.white);
        label9.setHorizontalAlignment(SwingConstants.RIGHT);
        add(label9);
        label9.setBounds(35, 315, 165, 25);

        //---- checkBox4 ----
        checkBox4.setBackground(new Color(51, 102, 255));
        add(checkBox4);
        checkBox4.setBounds(220, 315, 20, checkBox4.getPreferredSize().height);

        //---- label10 ----
        label10.setText("Balcony:");
        label10.setFont(new Font("Tahoma", Font.BOLD, 14));
        label10.setForeground(Color.white);
        label10.setHorizontalAlignment(SwingConstants.RIGHT);
        add(label10);
        label10.setBounds(35, 345, 165, 25);

        //---- checkBox5 ----
        checkBox5.setBackground(new Color(51, 102, 255));
        add(checkBox5);
        checkBox5.setBounds(220, 345, 20, checkBox5.getPreferredSize().height);

        //---- label11 ----
        label11.setText("Fridge:");
        label11.setFont(new Font("Tahoma", Font.BOLD, 14));
        label11.setForeground(Color.white);
        label11.setHorizontalAlignment(SwingConstants.RIGHT);
        add(label11);
        label11.setBounds(35, 375, 165, 25);

        //---- checkBox6 ----
        checkBox6.setBackground(new Color(51, 102, 255));
        add(checkBox6);
        checkBox6.setBounds(220, 375, 20, checkBox6.getPreferredSize().height);

        //---- label12 ----
        label12.setText("Kitchen:");
        label12.setFont(new Font("Tahoma", Font.BOLD, 14));
        label12.setForeground(Color.white);
        label12.setHorizontalAlignment(SwingConstants.RIGHT);
        add(label12);
        label12.setBounds(35, 405, 165, 25);

        //---- checkBox7 ----
        checkBox7.setBackground(new Color(51, 102, 255));
        add(checkBox7);
        checkBox7.setBounds(220, 405, 20, checkBox7.getPreferredSize().height);

        //---- label13 ----
        label13.setText("TV:");
        label13.setFont(new Font("Tahoma", Font.BOLD, 14));
        label13.setForeground(Color.white);
        label13.setHorizontalAlignment(SwingConstants.RIGHT);
        add(label13);
        label13.setBounds(35, 435, 165, 25);

        //---- checkBox8 ----
        checkBox8.setBackground(new Color(51, 102, 255));
        add(checkBox8);
        checkBox8.setBounds(220, 435, 20, checkBox8.getPreferredSize().height);

        //---- label14 ----
        label14.setText("Smoking allowed:");
        label14.setFont(new Font("Tahoma", Font.BOLD, 14));
        label14.setForeground(Color.white);
        label14.setHorizontalAlignment(SwingConstants.RIGHT);
        add(label14);
        label14.setBounds(35, 465, 165, 25);

        //---- checkBox9 ----
        checkBox9.setBackground(new Color(51, 102, 255));
        add(checkBox9);
        checkBox9.setBounds(220, 465, 20, checkBox9.getPreferredSize().height);

        //---- label15 ----
        label15.setText("Pets allowed:");
        label15.setFont(new Font("Tahoma", Font.BOLD, 14));
        label15.setForeground(Color.white);
        label15.setHorizontalAlignment(SwingConstants.RIGHT);
        add(label15);
        label15.setBounds(35, 495, 165, 25);

        //---- checkBox10 ----
        checkBox10.setBackground(new Color(51, 102, 255));
        add(checkBox10);
        checkBox10.setBounds(220, 495, 20, checkBox10.getPreferredSize().height);

        //---- button2 ----
        button2.setText("Cancel");
        button2.setForeground(new Color(51, 102, 255));
        button2.setFont(new Font("Tahoma", Font.BOLD, 14));

        button2.addActionListener(e -> cancelClick());
        add(button2);
        button2.setBounds(65, 540, 125, 40);

        //---- button1 ----
        button1.setText("Edit");
        button1.setForeground(new Color(51, 102, 255));
        button1.setFont(new Font("Tahoma", Font.BOLD, 14));

        button1.addActionListener(e -> editRoomClick());
        add(button1);
        button1.setBounds(245, 540, 125, 40);

        //---- spinner1 ----

        spinner1.setModel(new SpinnerNumberModel(1, 1, 99, 1));
        spinner1.setForeground(Color.black);
        add(spinner1);
        spinner1.setBounds(225, 190, 95, 25);

        //---- spinner2 ----

        spinner2.setModel(new SpinnerNumberModel(10, 1, 9999, 5));
        spinner2.setForeground(Color.black);
        add(spinner2);
        spinner2.setBounds(225, 160, 95, 25);

        //---- spinner3 ----

        spinner3.setModel(new SpinnerNumberModel(10.0, 0.01, 9999.0, 5.0));
        spinner3.setForeground(Color.black);
        spinner3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(spinner3);
        spinner3.setBounds(225, 130, 95, 25);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < getComponentCount(); i++) {
                Rectangle bounds = getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            setMinimumSize(preferredSize);
            setPreferredSize(preferredSize);
        }
    }
    private JComboBox<String> comboBox1;
    private JTextField textField1;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;
    private JCheckBox checkBox4;
    private JCheckBox checkBox5;
    private JCheckBox checkBox6;
    private JCheckBox checkBox7;
    private JCheckBox checkBox8;
    private JCheckBox checkBox9;
    private JCheckBox checkBox10;
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JSpinner spinner3;
}
