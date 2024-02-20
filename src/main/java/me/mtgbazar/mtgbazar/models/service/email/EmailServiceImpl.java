package me.mtgbazar.mtgbazar.models.service.email;

import me.mtgbazar.mtgbazar.data.entities.CardForSaleEntity;
import me.mtgbazar.mtgbazar.models.DTO.EmailDTO;
import me.mtgbazar.mtgbazar.models.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserService userService;
    public void sendEmail(EmailDTO emailDTO, CardForSaleEntity card) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(userService.getLoggedUser().getEmail());
        message.setTo(card.getSellingUser().getEmail());
        message.setSubject("Rád bych Vaši kartičku " + userService.getLoggedUser().getUsername());
        message.setText("Rád bych Vaši kartičku " +
                card.getCard().getName() + "\n" + "Za " + card.getCost() + " Kč a kvalitou "
                + card.getQuality() + " (Tento text vygenerovala stránka).\n" + emailDTO.getMessage());
        mailSender.send(message);
    }
}