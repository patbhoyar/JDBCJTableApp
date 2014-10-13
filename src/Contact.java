import java.sql.Date;

/**
 * Created by admin on October/13/14.
 */
public class Contact {

    private String firstName;
    private String lastName;
    private String email;
    private Date dob;

    Contact(String fname, String lname, String email, Date dob){
        this.firstName = fname;
        this.lastName = lname;
        this.email = email;
        this.dob = dob;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
