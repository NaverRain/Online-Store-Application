package com.naverrain.persistence.enteties.impl;

import com.naverrain.persistence.utils.validation.Validate;
import com.naverrain.persistence.enteties.User;

public class DefaultUser implements User {
    private static int userCounter = 0;

    private int id;

    @Validate(pattern = "[a-zA-Z]+")
    private String firstName;
    @Validate(pattern = "[a-zA-Z]+")
    private String lastName;

    private String password;

    @Validate(pattern = ".+@.+")
    private String email;

    private String roleName;
    private double money;
    private String creditCard;

    {
        id = ++userCounter;
    }

    public DefaultUser(){
    }

    public DefaultUser(String firstName, String lastName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }

    public DefaultUser(int id, String firstName, String lastName, String password, String email){
        this.id = id;
        userCounter--;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }

    public DefaultUser(String firstName, String lastName, String password, String email, String creditCard) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.creditCard = creditCard;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
    public String getRoleName() {
        return this.roleName;
    }

    @Override
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public double getMoney() {
        return money;
    }

    @Override
    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String getCreditCard() {
        return creditCard;
    }

    @Override
    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public void setPassword(String newPassword) {
        if (newPassword != null && !newPassword.isEmpty()) {
            this.password = newPassword;
        }
    }

    @Override
    public void setEmail(String newEmail) {
        if (newEmail == null) return;

        this.email = newEmail;
    }

    @Override
    public String toString() {
        return  "id=" + this.getId() +
                ", firstName='" + this.getFirstName() + '\'' +
                ", lastName='" + this.getLastName() + '\'' +
                ", email='" + this.getEmail();
    }

    void clearState(){
        userCounter = 0;
    }

    public static void setCounter(int updatedCount){
        userCounter = updatedCount;
    }
}
