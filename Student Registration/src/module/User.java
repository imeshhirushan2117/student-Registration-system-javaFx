package module;

public class User {
    private String firstName;
    private String lastName;
    private String contact;
    private String email;
    private String userName;
    private String password;
    private String Role;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(String firstName, String lastName, String contact, String email, String userName, String password, String role) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setContact(contact);
        this.setEmail(email);
        this.setUserName(userName);
        this.setPassword(password);
        setRole(role);
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", Role='" + Role + '\'' +
                '}';
    }
}