import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by admin on October/13/14.
 */
public class JTableClass {

    JFrame frame;

    JPanel topPanel = new JPanel();
    JPanel bottomPanel = new JPanel();

    JLabel fnameLabel;
    JLabel lnameLabel;
    JLabel emailLabel;
    JLabel dobLabel;
    JButton submit;
    JLabel output;

    DefaultTableModel model;
    JTable table;

    JTableClass(){

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

        output = new JLabel();
        output.setBackground(Color.cyan);
        output.setSize(100, 25);

        bottomPanel.setBackground(Color.blue);
        topPanel.setBackground(Color.RED);

        topPanel.setPreferredSize(new Dimension(600, 500));
        bottomPanel.setPreferredSize(new Dimension(600, 500));

        topPanel.add(fnameLabel);
        topPanel.add(fname);
        topPanel.add(lnameLabel);
        topPanel.add(lname);
        topPanel.add(emailLabel);
        topPanel.add(email);
        topPanel.add(dobLabel);
        topPanel.add(dob);
        topPanel.add(submit);
        topPanel.add(output);

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
        table.setBackground(Color.CYAN);

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
