package com.naverrain.services;

import com.naverrain.enteties.User;
import com.naverrain.enteties.impl.DefaultUser;
import com.naverrain.services.impl.DefaultResetPasswordService;
import com.naverrain.utis.mail.MailSender;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class ResetPasswordServiceTest {

    @InjectMocks
    private ResetPasswordService resetPasswordService = new DefaultResetPasswordService();

    @Mock
    private MailSender mailSenderMock;
    @Captor
    private ArgumentCaptor<String> captor;

    @Test
    void shouldSendToUserEmail() {
        User user = new DefaultUser();
        String userEmail = "testemail@email.com";
        user.setEmail(userEmail);

        resetPasswordService.resetPassword(user);

        verify(mailSenderMock).sendEmail(captor.capture(), anyString());
        assertEquals(captor.getValue(), userEmail);
    }

    @Test
    void shouldSendPasswordInfo() {
        User user = new DefaultUser();
        String userPass = "pass";
        user.setPassword(userPass);

        resetPasswordService.resetPassword(user);

        verify(mailSenderMock).sendEmail(any(), captor.capture());

        String emailContent = captor.getValue();
        System.out.println("Captured email content: " + emailContent);

        assertTrue(emailContent.contains(userPass), "The email should contain the user's password.");
    }

}
