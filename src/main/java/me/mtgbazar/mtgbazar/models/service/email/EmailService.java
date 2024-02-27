package me.mtgbazar.mtgbazar.models.service.email;

import me.mtgbazar.mtgbazar.data.entities.CardEntity;
import me.mtgbazar.mtgbazar.data.entities.CardForSaleEntity;
import me.mtgbazar.mtgbazar.data.entities.UserEntity;
import me.mtgbazar.mtgbazar.data.entities.WatchlistEntity;
import me.mtgbazar.mtgbazar.models.DTO.EmailDTO;
import me.mtgbazar.mtgbazar.models.DTO.UserDTO;

public interface EmailService {
    void sendEmail(EmailDTO emailDTO, CardForSaleEntity card);// EmailDTO emailDTO
    void sendWatchdogEmail(WatchlistEntity watchlistEntity, CardEntity card);
    void sendVerificationEmail(UserEntity userEntity);

    String sanitizeHTML(String untrustedHTML);

}
