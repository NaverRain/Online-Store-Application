package com.naverrain.enteties.impl;

import com.naverrain.enteties.User;

import java.util.Objects;

public class UserForHashTables implements User {

    private static int userCounter = 0;

    private int id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;

    {
        id = ++userCounter;
    }

    public UserForHashTables() {
    }

    public UserForHashTables(String firstName, String lastName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }

    public UserForHashTables(int id, String firstName, String lastName, String password, String email) {
        this.id = id;
        userCounter--;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "UserForHashTables{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public void setPassword(String newPassword) {
        if (newPassword == null) return;
        this.password = newPassword;
    }

    @Override
    public void setEmail(String newEmail) {
        if (newEmail == null) return;
        this.email = newEmail;
    }

    void clearState(){
        userCounter = 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, firstName, id, lastName, password);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        UserForHashTables other = (UserForHashTables) obj;
        return Objects.equals(email, other.email)
                && Objects.equals(firstName, other.firstName)
                && Objects.equals(id, other.id)
                && Objects.equals(lastName, other.lastName)
                && Objects.equals(password, other.password);
    }
}
