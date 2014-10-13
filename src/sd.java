import java.sql.*;

public class sd {

    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:8889/user";

    //  Database credentials
    private static final String USER = "root";
    private static final String PASS = "root";

    Connection conn = null;
    PreparedStatement pstmt = null;
    Statement stmt = null;

    sd(){
        try {
            System.out.println("ola");
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        }catch (Exception e){

        }
    }

    public void insertContact(String firstName, String lastName, String email, Date dob){

        try {
            String sql = "Insert into user(firstName, lastName, email, dob) Values(?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setDate(4, dob);
            pstmt.executeUpdate();

        }catch (Exception e){

        }
    }

    public void getAllContacts(){
        try {
            System.out.println(conn);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM user";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                //Retrieve by column name
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String dob = rs.getDate("dob").toString();

                //Display values
                System.out.print(", First: " + firstName);
                System.out.println(", Last: " + lastName);
                System.out.println(", Email: " + email);
                System.out.println(", DOB: " + dob);
            }

            rs.close();
            stmt.close();
            conn.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
