public class Client {
    private String lastName;
    private String firstName;
    private String passportID;

    public Client(String lastName, String firstName, String passportID) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.passportID = passportID;
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

    public String getPassportID() {
        return passportID;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    @Override
    public String toString() {
        return "Last name: " + lastName +
                ", first name: " + firstName +
                ", passportID: " + passportID;
    }
}
