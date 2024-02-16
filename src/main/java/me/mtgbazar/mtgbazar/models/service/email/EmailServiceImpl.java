package me.mtgbazar.mtgbazar.models.service.email;

import me.mtgbazar.mtgbazar.data.entities.CardForSaleEntity;
import me.mtgbazar.mtgbazar.models.DTO.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;
    public void sendEmail(EmailDTO emailDTO, CardForSaleEntity card) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String singedUserEmail = authentication.getName();
        message.setFrom(singedUserEmail);
        message.setTo(card.getSellingUser().getEmail());
        message.setSubject("Rád bych Vaši kartičku " + card.getCard().getName() + "\n" + "Za " + card.getCost() + "Kč a kvalitou " + card.getQuality());
        message.setText(emailDTO.getMessage());
        mailSender.send(message);
    }
}