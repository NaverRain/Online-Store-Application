package com.naverrain.services.impl;

import com.naverrain.enteties.User;
import com.naverrain.services.ResetPasswordService;
import com.naverrain.utis.mail.MailSender;

public class DefaultResetPasswordService implements ResetPasswordService {

    private MailSender mailSender;

    {
        mailSender = DefaultMailSender.getInstance();
    }

    @Override
    public void resetPassword(User user) {
        mailSender.sendEmail(user.getEmail(), "Please use this password to login: " + user.getPassword());
    }
}
