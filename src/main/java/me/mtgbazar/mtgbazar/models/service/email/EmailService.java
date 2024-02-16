package me.mtgbazar.mtgbazar.models.service.email;

import me.mtgbazar.mtgbazar.data.entities.CardForSaleEntity;
import me.mtgbazar.mtgbazar.models.DTO.EmailDTO;

public interface EmailService {
    void sendEmail(EmailDTO emailDTO, CardForSaleEntity card);// EmailDTO emailDTO



}
