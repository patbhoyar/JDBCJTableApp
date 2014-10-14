package CommonClasses;

import CommonClasses.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCClass {

    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:8889/user";

    //  Database credentials
    private static final String USER = "root";
    private static final String PASS = "root";

    Connection conn = null;
    PreparedStatement pstmt = null;
    Statement stmt = null;

    public JDBCClass(){
        try {
            System.out.println("ola");
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        }catch (Exception e){

        }
    }

    public void insertContact(Contact c){

        try {
            String sql = "Insert into user(firstName, lastName, email, dob) Values(?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, c.getFirstName());
            pstmt.setString(2, c.getLastName());
            pstmt.setString(3, c.getEmail());
            pstmt.setDate(4, c.getDob());
            pstmt.executeUpdate();
        }catch (Exception e){

        }
    }

    public List<Contact> getAllContacts(){

        List<Contact> contacts = new ArrayList<Contact>();
        try {
            System.out.println(conn);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM user";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Contact c = new Contact(rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getDate("dob"));
                contacts.add(c);
            }

            rs.close();
            stmt.close();
            conn.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return contacts;
    }

}
