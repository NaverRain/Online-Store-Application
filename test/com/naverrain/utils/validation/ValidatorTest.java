package com.naverrain.utils.validation;

import com.naverrain.enteties.User;
import com.naverrain.enteties.impl.DefaultUser;
import com.naverrain.utis.validation.Validator;
import com.naverrain.utis.validation.impl.DefaultValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {

    private Validator testInstance;
    private User user;

    @BeforeEach
    void setUp(){
        testInstance = new DefaultValidator();
    }

    @Test
    void shouldValidateFirstNameWithLatinLettersOnly(){
        user = new DefaultUser("Masha", "Muninn", "pass", "email@gmail.com");

        assertTrue(testInstance.isValid(user));
    }

    @Test
    void shouldFailValidateFirstNameWithNumbers(){
        user = new DefaultUser("Masha1", "Muninn", "pass", "email@gmail.com");

        assertFalse(testInstance.isValid(user));
    }

    @Test
    void shouldValidateLastNameWithLatinLettersOnly(){
        user = new DefaultUser("Masha", "Muninn", "pass", "email@gmail.com");

        assertTrue(testInstance.isValid(user));
    }

    @Test
    void shouldFailValidateLastNameWithNumbers(){
        user = new DefaultUser("Masha", "Muninn1", "pass", "email@gmail.com");

        assertFalse(testInstance.isValid(user));
    }

    @Test
    void shouldValidateEmailWithAtCharacter(){
        user = new DefaultUser("Masha", "Muninn", "pass", "email@gmail.com");

        assertTrue(testInstance.isValid(user));
    }

    @Test
    void shouldValidateEmailWithoutAtCharacter(){
        user = new DefaultUser("Masha", "Muninn", "pass", "emailgmail.com");

        assertFalse(testInstance.isValid(user));
    }


}
