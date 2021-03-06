import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.*;

public class adminSearchRoomReservationsForm extends JPanel {
    JFrame jframe;
    adminSearchRoomReservationsForm currentForm;
    private final Admin admin;
    private final MainUI mainUI;
    private final Map<Integer,Reservation> reservations;
    private final Map<Integer,Room> rooms;
    private final Map<String,Authentication> users;
    private final Map<String,Customer> customers;
    private final Map<String,Provider> providers;
    private final Map<String,Admin> admins;
    private final Map<Integer,Message> messages;
    DefaultListModel<String> model;
    AtomicBoolean noRoomsFound;
    ArrayList<Integer> roomIDsInList;
    private static DecimalFormat df;

    public void setCurrentForm(adminSearchRoomReservationsForm currentForm) {
        this.currentForm = currentForm;
    }

    /**
     * Constructor of adminSearchRoomReservationsForm. Adds to displayed list all existing rooms
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
     * @param admin
     */
    public adminSearchRoomReservationsForm(JFrame jframe, Map<Integer,Reservation> reservations, Map<Integer,Room> rooms,
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
        jframe.setPreferredSize(new Dimension(675, 475));
        jframe.pack();

        roomIDsInList = new ArrayList<>();
        df = new DecimalFormat("0.00");
        model = new DefaultListModel<>();
        noRoomsFound = new AtomicBoolean(true);
        this.rooms.forEach((roomID, room) -> {
            model.addElement("Room ID: " + room.getId() + ", Name: \"" + room.getName() +
                    "\", Type: " + room.getType() + ", Capacity: " +
                    room.getCapacity().toString()+ ", Price: $" +
                    df.format(room.getPrice()));
            roomIDsInList.add(roomID);
            noRoomsFound.set(false);
        });
        if (noRoomsFound.get()) {
            model.addElement("No rooms found");
        }
        list1.setModel(model);
        if (!noRoomsFound.get()){
            list1.setEnabled(true);
            textField1.setEnabled(false);
        } else {
            list1.setEnabled(false);
            textField1.setEnabled(false);
            button2.setEnabled(false);
        }
    }

    private void textField1Click() {
        if (!noRoomsFound.get()) {
            list1.setEnabled(false);
            textField1.setEnabled(true);
        }
    }

    private void list1Click() {
        if (!noRoomsFound.get()) {
            list1.setEnabled(true);
            textField1.setEnabled(false);
        }
    }

    /**
     *  Returns to search reservations form
     */
    private void backButtonClick() {
        adminSearchReservationsForm adminSearchReservationsForm = new adminSearchReservationsForm(jframe, this.reservations,
                this.rooms, this.users, this.customers, this.providers, this.admins, this.messages,
                this.mainUI, this.admin);
        adminSearchReservationsForm.setCurrentForm(adminSearchReservationsForm);
        jframe.add(adminSearchReservationsForm);
        this.currentForm.setVisible(false);
    }

    /**
     *  Proceeds to adminReturnRoomReservationsForm and displays all reservations for the
     *  selected room
     */
    private void nextButtonClick() {
        if (!noRoomsFound.get()) {
            if (list1.isEnabled() && !textField1.isEnabled()) {
                if (!list1.isSelectionEmpty()) {
                    adminReturnRoomReservationsForm adminReturnRoomReservationsForm = new adminReturnRoomReservationsForm(
                            jframe, this.reservations, this.rooms, this.users, this.customers, this.providers, this.admins,
                            this.messages, this.mainUI, this.admin, roomIDsInList.get(list1.getSelectedIndex()));
                    adminReturnRoomReservationsForm.setCurrentForm(adminReturnRoomReservationsForm);
                    jframe.add(adminReturnRoomReservationsForm);
                    this.currentForm.setVisible(false);
                }
                else {
                    label3.setText("Please select a room");
                    label3.setForeground(Color.red);
                    label3.setVisible(true);
                }

            }
            else if (textField1.isEnabled() && !list1.isEnabled()) {
                try {
                    Integer givenID = Integer.parseInt(textField1.getText());
                    if (this.rooms.containsKey(givenID)) {
                        adminReturnRoomReservationsForm adminReturnRoomReservationsForm = new adminReturnRoomReservationsForm(jframe, this.reservations,
                                this.rooms, this.users, this.customers, this.providers, this.admins, this.messages,
                                this.mainUI, this.admin, givenID);
                        adminReturnRoomReservationsForm.setCurrentForm(adminReturnRoomReservationsForm);
                        jframe.add(adminReturnRoomReservationsForm);
                        this.currentForm.setVisible(false);
                    } else {
                        label3.setText("Room not found");
                        label3.setForeground(Color.red);
                        label3.setVisible(true);
                    }
                } catch (NumberFormatException ignored) {
                    label3.setText("Please enter a valid room ID");
                    label3.setForeground(Color.red);
                    label3.setVisible(true);
                }


            }
        }
    }

    /**
     *   Form generator
     */
    private void initComponents() {
        JLabel label1 = new JLabel();
        textField1 = new JTextField();
        JLabel label4 = new JLabel();
        JScrollPane scrollPane1 = new JScrollPane();
        list1 = new JList<>();
        JButton button1 = new JButton();
        button2 = new JButton();
        label3 = new JLabel();
        JLabel label5 = new JLabel();
        JLabel label6 = new JLabel();

        //======== this ========
        setBackground(new Color(51, 102, 255));
        setLayout(null);

        //---- label1 ----
        label1.setText("Type the room ID:");
        label1.setFont(new Font("Tahoma", Font.BOLD, 14));
        label1.setForeground(Color.white);
        add(label1);
        label1.setBounds(new Rectangle(new Point(205, 70), label1.getPreferredSize()));

        //---- textField1 ----
        textField1.setFont(new Font("Tahoma", Font.PLAIN, 14));

        textField1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField1Click();
            }
        });
        add(textField1);
        textField1.setBounds(345, 65, 95, textField1.getPreferredSize().height);

        //---- label4 ----
        label4.setText("Select a room from the list below:");
        label4.setFont(new Font("Tahoma", Font.BOLD, 14));
        label4.setHorizontalAlignment(SwingConstants.CENTER);
        label4.setForeground(Color.white);
        add(label4);
        label4.setBounds(200, 130, 275, 30);

        //======== scrollPane1 ========
        {
            scrollPane1.setFont(new Font("Tahoma", Font.BOLD, 14));

            //---- list1 ----
            list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list1.setEnabled(false);
            list1.setFont(new Font("Tahoma", Font.PLAIN, 14));
            list1.setModel(new AbstractListModel<>() {
                final String[] values = {
                        "Name: \"test\", type: hotel, capacity: 2, price: $40.00"
                };

                @Override
                public int getSize() {
                    return values.length;
                }

                @Override
                public String getElementAt(int i) {
                    return values[i];
                }
            });
            list1.setBackground(Color.white);
            list1.setForeground(Color.black);
            list1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    list1Click();
                }
            });
            scrollPane1.setViewportView(list1);
        }
        add(scrollPane1);
        scrollPane1.setBounds(55, 160, 565, 220);

        //---- button1 ----
        button1.setText("Back");
        button1.setFont(new Font("Tahoma", Font.BOLD, 14));
        button1.setForeground(new Color(51, 102, 255));

        button1.addActionListener(e -> backButtonClick());
        add(button1);
        button1.setBounds(140, 395, 125, 40);

        //---- button2 ----
        button2.setText("Next");
        button2.setFont(new Font("Tahoma", Font.BOLD, 14));
        button2.setForeground(new Color(51, 102, 255));

        button2.addActionListener(e -> nextButtonClick());
        add(button2);
        button2.setBounds(410, 395, 125, 40);

        //---- label3 ----
        label3.setText("Room ID not found");
        label3.setVisible(false);
        label3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(label3);
        label3.setBounds(460, 65, 195, 25);

        //---- label5 ----
        label5.setText("Reservations");
        label5.setFont(new Font("Tahoma", Font.BOLD, 22));
        label5.setForeground(Color.white);
        label5.setHorizontalAlignment(SwingConstants.CENTER);
        add(label5);
        label5.setBounds(265, 20, 155, 35);

        //---- label6 ----
        label6.setText("or");
        label6.setFont(new Font("Tahoma", Font.BOLD, 14));
        label6.setBackground(Color.white);
        label6.setForeground(Color.white);
        add(label6);
        label6.setBounds(new Rectangle(new Point(330, 105), label6.getPreferredSize()));

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
    private JTextField textField1;
    private JList<String> list1;
    private JButton button2;
    private JLabel label3;
}
