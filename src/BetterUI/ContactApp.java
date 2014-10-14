package BetterUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import CommonClasses.UtilClass;

public class ContactApp {

    JFrame frame;

    JPanel menu;
    JPanel body;
    JPanel viewPanel;
    JPanel addPanel;

    JButton btnAddContact;
    JButton btnViewContacts;

    DefaultTableModel model;
    JTable contactsTable;

    CardLayout cards;

    private void createUI(){
        frame = new JFrame("Contacts App");
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        menu = new JPanel();
        body = new JPanel();
        viewPanel = new JPanel();
        addPanel = new JPanel();
        addPanel.setLayout(new BorderLayout());

        menu.setPreferredSize(new Dimension(600, 100));
        menu.setBackground(Color.CYAN);

        viewPanel.setPreferredSize(new Dimension(600, 400));
        viewPanel.setBackground(Color.black);
        addPanel.setPreferredSize(new Dimension(600, 400));
        addPanel.setBackground(Color.red);

        btnAddContact = new JButton("New Contact");
        btnViewContacts = new JButton("All Contacts");

        BoxLayout layout = new BoxLayout(menu, BoxLayout.X_AXIS);
        menu.setLayout(layout);
        menu.add(btnAddContact);
        menu.add(btnViewContacts);

        cards = new CardLayout();
        body.setLayout(cards);
        body.add(addPanel, "Add Contacts");
        body.add(viewPanel, "View Contacts");

        frame.setLayout(new BorderLayout());
        frame.add(menu, BorderLayout.NORTH);
        frame.add(body, BorderLayout.CENTER);

        btnAddContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (viewPanel.isVisible()){
                    cards.show(body, "Add Contacts");
                }
            }
        });

        btnViewContacts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addPanel.isVisible()){
                    cards.show(body, "View Contacts");
                }
            }
        });

        contactsTable = UtilClass.createTable(contactsTable, model);
        UtilClass.displayTable(contactsTable, viewPanel);

        UtilClass.createForm(contactsTable, addPanel, cards, body);
    }

    public ContactApp(){
        createUI();
    }

}
