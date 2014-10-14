package CommonClasses;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UtilClass {

    static JLabel fnameLabel;
    static JLabel lnameLabel;
    static JLabel emailLabel;
    static JLabel dobLabel;
    static JButton submit;

    static JTextField fname = new JTextField(20);
    static JTextField lname = new JTextField(20);
    static JTextField email = new JTextField(20);
    static JTextField dob = new JTextField(10);

    public static void createForm(final JTable table, JPanel panel, final CardLayout cards, final JPanel body){
        fnameLabel = new JLabel("First Name");
        lnameLabel = new JLabel("Last Name");
        emailLabel = new JLabel("Email");
        dobLabel = new JLabel("Date of Birth");
        submit = new JButton("Submit");
        fname.setMaximumSize(fname.getPreferredSize());
        lname.setMaximumSize(fname.getPreferredSize());
        email.setMaximumSize(fname.getPreferredSize());
        dob.setMaximumSize(fname.getPreferredSize());

        fname.setAlignmentX(Component.CENTER_ALIGNMENT);
        lname.setAlignmentX(Component.CENTER_ALIGNMENT);
        email.setAlignmentX(Component.CENTER_ALIGNMENT);
        dob.setAlignmentX(Component.CENTER_ALIGNMENT);
        fnameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        lnameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dobLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        submit.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setBorder(BorderFactory.createTitledBorder("Add New Contact"));
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);
        container.add(fnameLabel);
        container.add(fname);
        container.add(lnameLabel);
        container.add(lname);
        container.add(emailLabel);
        container.add(email);
        container.add(dobLabel);
        container.add(dob);
        container.add(submit);

        panel.add(container, BorderLayout.CENTER);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Contact c = new Contact(fname.getText(), lname.getText(), email.getText(), convertDateToSQL(dob.getText()));
                addRowtoTable(table, c, 1);
                clearFields(fname, lname, email, dob);
                cards.show(body, "View Contacts");
            }
        });
    }


    public static void addRowtoTable(JTable table, Contact c, int addRowDB){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[]{c.getFirstName(), c.getLastName(), c.getEmail(), c.getDob().toString()});

        if (addRowDB != 0){
            addRowtoDB(c);
        }
    }

    public static JTable createTable(JTable table, DefaultTableModel model){
        model = new DefaultTableModel();
        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("Email");
        model.addColumn("Date of Birth");
        table = new JTable(model);
        table.setFillsViewportHeight(true);
        return table;
    }

    public static void displayTable(JTable table, JPanel panel){

        JScrollPane scr = new JScrollPane(table);

        panel.setLayout(new BorderLayout());
        panel.add(scr, BorderLayout.CENTER);

        JDBCClass db = new JDBCClass();
        java.util.List<Contact> contacts = db.getAllContacts();

        for (Contact c : contacts){
            addRowtoTable(table, c, 0);
        }
    }

    public static void clearFields(JTextField fname, JTextField lname, JTextField email, JTextField dob){
        fname.setText("");
        lname.setText("");
        email.setText("");
        dob.setText("");
    }

    public static void addRowtoDB(Contact c){
        JDBCClass db = new JDBCClass();
        db.insertContact(c);
    }

    public static java.sql.Date convertDateToSQL(String date){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        java.sql.Date newDOB = new java.sql.Date(new java.util.Date().getTime());
        try {
            newDOB = new java.sql.Date(formatter.parse(date).getTime());
        }catch (ParseException e){

        }
        return newDOB;
    }

    public static void showNewContact(JPanel body, CardLayout cards){

    }
}
