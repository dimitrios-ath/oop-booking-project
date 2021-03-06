import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class messageInboxForm extends JPanel {
    JFrame jframe;
    messageInboxForm currentForm;
    String currentUsername;
    private final MainUI mainUI;
    private final Map<Integer,Reservation> reservations;
    private final Map<Integer,Room> rooms;
    private final Map<String,Authentication> users;
    private final Map<String,Customer> customers;
    private final Map<String,Provider> providers;
    private final Map<String,Admin> admins;
    private final Map<Integer,Message> messages;
    DefaultListModel<String> model;
    private ArrayList<Integer> idsInList;

    public void setCurrentForm(messageInboxForm currentForm) {
        this.currentForm = currentForm;
    }

    /**
     * Constructor of messageInboxForm. Add to displayed list all messages that belong to the current user
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
     * @param currentUsername
     */
    public messageInboxForm(JFrame jframe, Map<Integer,Reservation> reservations,
                            Map<Integer,Room> rooms, Map<String,Authentication> users, Map<String,Customer> customers,
                            Map<String,Provider> providers, Map<String,Admin> admins, Map<Integer,Message> messages,
                            MainUI mainUI, String currentUsername) {
        this.jframe = jframe;
        this.reservations = reservations;
        this.rooms = rooms;
        this.users = users;
        this.customers = customers;
        this.providers = providers;
        this.admins = admins;
        this.messages = messages;
        this.mainUI=mainUI;
        this.currentUsername = currentUsername;
        jframe.setPreferredSize(new Dimension(435 , 455));
        jframe.pack();
        initComponents();

        idsInList = new ArrayList<>();
        model = new DefaultListModel<>();
        AtomicBoolean noMessages = new AtomicBoolean(true);
        this.messages.forEach((id, message) -> {
            if (Objects.equals(message.getRecipient(), currentUsername)){
                if (!message.isRead()) {
                    try {
                        model.addElement("(new) From: \"" + message.getSender() +
                                "\", Content: " + message.getContent().substring(0, 10) + "...");
                        noMessages.set(false);
                        idsInList.add(message.getID());
                    } catch (StringIndexOutOfBoundsException ignored) {
                        model.addElement("(new) From: \"" + message.getSender() +
                                "\", Content: " + message.getContent() + "...");
                        noMessages.set(false);
                        idsInList.add(message.getID());
                    }
                }
            }
        });
        this.messages.forEach((id, message) -> {
            if (Objects.equals(message.getRecipient(), currentUsername)){
                if (message.isRead()) {
                    try {
                    model.addElement("From: \"" + message.getSender() +
                            "\", Content: " + message.getContent().substring(0,10) + "...");
                    noMessages.set(false);
                    idsInList.add(message.getID());
                    } catch (StringIndexOutOfBoundsException ignored) {
                        model.addElement("From: \"" + message.getSender() +
                                "\", Content: " + message.getContent() + "...");
                        noMessages.set(false);
                        idsInList.add(message.getID());
                    }
                }
            }
        });
        if (noMessages.get()) {
            model.addElement("No messages found");
            list1.setEnabled(false);
        }
        list1.setModel(model);
    }

    /**
     *  Updates the list when a new list item is selected. If the message is not read yet,
     *  it's marked as read
     */
    private void listValueSelected() {
        label3.setText(messages.get(idsInList.get(list1.getSelectedIndex())).getSender());
        textArea1.setText(messages.get(idsInList.get(list1.getSelectedIndex())).getContent());
        messages.get(idsInList.get(list1.getSelectedIndex())).setRead(true);

        idsInList = new ArrayList<>();
        model = new DefaultListModel<>();
        AtomicBoolean noMessages = new AtomicBoolean(true);
        this.messages.forEach((id, message) -> {
            if (Objects.equals(message.getRecipient(), currentUsername)){
                if (!message.isRead()) {
                    try {
                        model.addElement("(new) From: \"" + message.getSender() +
                                "\", Content: " + message.getContent().substring(0, 10) + "...");
                        noMessages.set(false);
                        idsInList.add(message.getID());
                    } catch (StringIndexOutOfBoundsException ignored) {
                        model.addElement("(new) From: \"" + message.getSender() +
                                "\", Content: " + message.getContent() + "...");
                        noMessages.set(false);
                        idsInList.add(message.getID());
                    }
                }
            }
        });
        this.messages.forEach((id, message) -> {
            if (Objects.equals(message.getRecipient(), currentUsername)){
                if (message.isRead()) {
                    try {
                        model.addElement("From: \"" + message.getSender() +
                                "\", Content: " + message.getContent().substring(0,10) + "...");
                        noMessages.set(false);
                        idsInList.add(message.getID());
                    } catch (StringIndexOutOfBoundsException ignored) {
                        model.addElement("From: \"" + message.getSender() +
                                "\", Content: " + message.getContent() + "...");
                        noMessages.set(false);
                        idsInList.add(message.getID());
                    }
                }
            }
        });
        if (noMessages.get()) {
            model.addElement("No messages found");
            list1.setEnabled(false);
        }
        list1.setModel(model);
    }

    /**
     * Returns to the message form
     */
    private void returnButtonClicked() {
        messageForm messageForm = new messageForm(
                this.jframe, this.reservations, this.rooms, this.users, this.customers, this.providers,
                this.admins, this.messages, this.mainUI, currentUsername);
        messageForm.setCurrentForm(messageForm);
        this.jframe.add(messageForm);
        this.currentForm.setVisible(false);
    }

    /**
     *   Form generator
     */
    private void initComponents() {
        JLabel label1 = new JLabel();
        JScrollPane scrollPane1 = new JScrollPane();
        list1 = new JList<>();
        JLabel label2 = new JLabel();
        label3 = new JLabel();
        JScrollPane scrollPane2 = new JScrollPane();
        textArea1 = new JTextArea();
        JButton button1 = new JButton();

        //======== this ========
        setBackground(new Color(51, 102, 255));
        setLayout(null);

        //---- label1 ----
        label1.setText("Inbox");
        label1.setForeground(Color.white);
        label1.setFont(new Font("Tahoma", Font.BOLD, 22));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        add(label1);
        label1.setBounds(180, 20, 80, 30);

        //======== scrollPane1 ========
        {

            //---- list1 ----
            list1.setFont(new Font("Tahoma", Font.PLAIN, 14));

            list1.setForeground(Color.black);
            list1.setBackground(Color.white);
            list1.addListSelectionListener(e -> listValueSelected());
            scrollPane1.setViewportView(list1);
        }
        add(scrollPane1);
        scrollPane1.setBounds(45, 70, 345, 120);

        //---- label2 ----
        label2.setText("From:");
        label2.setFont(new Font("Tahoma", Font.BOLD, 14));
        label2.setForeground(Color.white);
        add(label2);
        label2.setBounds(140, 210, 50, 22);

        //---- label3 ----
        label3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(label3);
        label3.setBounds(200, 205, 115, 32);

        //======== scrollPane2 ========
        {

            //---- textArea1 ----
            textArea1.setRows(5);
            textArea1.setLineWrap(true);
            textArea1.setEnabled(false);
            textArea1.setFont(new Font("Tahoma", Font.PLAIN, 14));

            textArea1.setForeground(Color.black);
            textArea1.setBackground(Color.white);
            scrollPane2.setViewportView(textArea1);
        }
        add(scrollPane2);
        scrollPane2.setBounds(80, 240, 280, 110);

        //---- button1 ----
        button1.setText("Return");
        button1.setFont(new Font("Tahoma", Font.BOLD, 14));
        button1.setForeground(new Color(51, 102, 255));

        button1.addActionListener(e -> returnButtonClicked());
        add(button1);
        button1.setBounds(150, 370, 125, 40);

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
    private JList<String> list1;
    private JLabel label3;
    private JTextArea textArea1;
}
