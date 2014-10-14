package Old;

import CommonClasses.JDBCClass;
import CommonClasses.Contact;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class JTableClass {

    JFrame frame;

    JPanel topPanel = new JPanel();
    JPanel bottomPanel = new JPanel();

    JLabel fnameLabel;
    JLabel lnameLabel;
    JLabel emailLabel;
    JLabel dobLabel;
    JButton submit;

    DefaultTableModel model;
    JTable table;

    public JTableClass(){

        frame = new JFrame("JTable, JDBC Application");
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);

        final JTextField fname = new JTextField(20);
        final JTextField lname = new JTextField(20);
        final JTextField email = new JTextField(20);
        final JTextField dob = new JTextField(10);
        fnameLabel = new JLabel("First Name");
        lnameLabel = new JLabel("Last Name");
        emailLabel = new JLabel("Email");
        dobLabel = new JLabel("Date of Birth");
        submit = new JButton("Submit");
        fname.setMaximumSize(fname.getPreferredSize());
        lname.setMaximumSize(fname.getPreferredSize());
        email.setMaximumSize(fname.getPreferredSize());
        dob.setMaximumSize(fname.getPreferredSize());

        topPanel.setPreferredSize(new Dimension(600, 230));
        bottomPanel.setPreferredSize(new Dimension(600, 770));

        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JPanel container = new JPanel();
        container.setBorder(BorderFactory.createTitledBorder("Add New CommonClasses.Contact"));
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);
        fname.setAlignmentX(Component.CENTER_ALIGNMENT);
        lname.setAlignmentX(Component.CENTER_ALIGNMENT);
        email.setAlignmentX(Component.CENTER_ALIGNMENT);
        dob.setAlignmentX(Component.CENTER_ALIGNMENT);
        fnameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        lnameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dobLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        submit.setAlignmentX(Component.CENTER_ALIGNMENT);

        container.add(fnameLabel);
        container.add(fname);
        container.add(lnameLabel);
        container.add(lname);
        container.add(emailLabel);
        container.add(email);
        container.add(dobLabel);
        container.add(dob);
        container.add(submit);

        topPanel.add(container);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Contact c = new Contact(fname.getText(), lname.getText(), email.getText(), convertDateToSQL(dob.getText()));
                addRowtoTable(table, c, 1);
                clearFields(fname, lname, email, dob);
            }
        });

        model = new DefaultTableModel();
        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("Email");
        model.addColumn("Date of Birth");

        table = new JTable(model);
        table.setFillsViewportHeight(true);

        JScrollPane scr = new JScrollPane(table);

        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(scr, BorderLayout.CENTER);

        frame.add(topPanel);
        frame.add(bottomPanel);

        displayTable();
    }

    public void addRowtoTable(JTable table, Contact c, int addRowDB){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[]{c.getFirstName(), c.getLastName(), c.getEmail(), c.getDob().toString()});

        if (addRowDB != 0){
            addRowtoDB(c);
        }
    }

    public void displayTable(){
        JDBCClass db = new JDBCClass();
        java.util.List<Contact> contacts = db.getAllContacts();

        for (Contact c : contacts){
            addRowtoTable(table, c, 0);
        }
    }

    public void clearFields(JTextField fname, JTextField lname, JTextField email, JTextField dob){
        fname.setText("");
        lname.setText("");
        email.setText("");
        dob.setText("");
    }

    public void addRowtoDB(Contact c){
        JDBCClass db = new JDBCClass();
        db.insertContact(c);
    }

    public java.sql.Date convertDateToSQL(String date){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        java.sql.Date newDOB = new java.sql.Date(new java.util.Date().getTime());
        try {
            newDOB = new java.sql.Date(formatter.parse(date).getTime());
        }catch (ParseException e){

        }
        return newDOB;
    }

}
