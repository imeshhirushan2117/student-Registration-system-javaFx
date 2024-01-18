package view.tm;

public class UserTm {
     private String firstName;
     private String lastName;
     private String contact;
     private String email;
     private String userName;
     private String userPassword;
     private String role;

    public UserTm() {
    }

    public UserTm(String firstName, String lastName, String contact, String email, String userName, String userPassword, String role) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setContact(contact);
        this.setEmail(email);
        this.setUserName(userName);
        this.setUserPassword(userPassword);
        this.setRole(role);
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

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserTm{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
