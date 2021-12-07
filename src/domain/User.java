package domain;

import java.util.Objects;

public class User extends Entity<String>{
    private String firstName;
    private String lastName;

    public User(String username, String firstName, String lastName) {
        setUsername(username);
        this.firstName = firstName;
        this.lastName = lastName;
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


    @Override
    public String toString() {

        return "Utilizator{" +
                "username=" + getUsername() +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' + " }";

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getFirstName(),
                user.getFirstName()) && Objects.equals(getLastName(),
                user.getLastName());
    }

    public int hashCode() {
        return Objects.hash(getUsername(), getFirstName(),getLastName());
    }
}
