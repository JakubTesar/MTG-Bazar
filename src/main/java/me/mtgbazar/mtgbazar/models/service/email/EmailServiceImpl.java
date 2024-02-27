package me.mtgbazar.mtgbazar.models.service.email;

import me.mtgbazar.mtgbazar.data.entities.CardEntity;
import me.mtgbazar.mtgbazar.data.entities.CardForSaleEntity;
import me.mtgbazar.mtgbazar.data.entities.UserEntity;
import me.mtgbazar.mtgbazar.data.entities.WatchlistEntity;
import me.mtgbazar.mtgbazar.models.DTO.EmailDTO;
import me.mtgbazar.mtgbazar.models.service.users.UserService;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
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
        message.setFrom("noreplybazarmtg@gmail.com");
        message.setTo(card.getSellingUser().getEmail());
        message.setSubject("Rád bych Vaši kartičku " + userService.getLoggedUser().getUsername());
        message.setText(
                sanitizeHTML("Rád bych Vaši kartičku " +
                        card.getCard().getName() + "\n" + "Za " + card.getCost() + " Kč a kvalitou "
                        + card.getQuality() + " (Tento text vygenerovala stránka).\n" + emailDTO.getMessage() + "\n" +
                        "Kontaktní údaje: " + "\n" +
                        "Email:" + emailDTO.getEmail() + "\n" +
                        "Tel. číslo:" + emailDTO.getPhoneNumber() + "\n"));
        mailSender.send(message);
    }

    @Override
    public void sendWatchdogEmail(WatchlistEntity entity, CardEntity card) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreplybazarmtg@gmail.com");
        message.setTo(entity.getUserWatching().getEmail());
        message.setSubject("Kartička je k dostání");
        message.setText(sanitizeHTML("Kartička " + card.getName() + " je k dostání na MTG - Bazar."));
        mailSender.send(message);
    }

    @Override
    public void sendVerificationEmail(UserEntity userEntity) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreplybazarmtg@gmail.com");
        message.setTo(userEntity.getEmail());
        message.setSubject("Ověření účtu");
        message.setText("Pro ověření účtu klikněte na tento odkaz:  + <a href='bazar.tessi.lol/access/verify/" + userEntity.getVerificationKey() + "'>MTG-Bazar</a> ");
        mailSender.send(message);
    }

    @Override
    public String sanitizeHTML(String untrustedHTML) {
        PolicyFactory policy = new HtmlPolicyBuilder()
                .disallowAttributes("src").onElements("img")
                .disallowAttributes("href").onElements("a")
                .allowStandardUrlProtocols()
                .disallowElements("a", "img").toFactory();
        return policy.sanitize(untrustedHTML);
    }
}