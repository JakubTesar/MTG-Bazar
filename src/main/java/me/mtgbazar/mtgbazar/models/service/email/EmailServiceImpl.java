package me.mtgbazar.mtgbazar.models.service.email;

import me.mtgbazar.mtgbazar.models.DTO.EmailDTO;

public class EmailServiceImpl implements EmailService{

    private Mail mailSender;
    private SimpleMailMessage templateMessage;
    @Override
    public void sendEmail(EmailDTO emailDTO) {

    }
}
