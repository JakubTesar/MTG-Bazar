package me.mtgbazar.mtgbazar.models.service;

import me.mtgbazar.mtgbazar.models.DTO.CardDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public interface CardService {
    void createCard(CardDTO cardDTO);
    List<CardDTO> getAll();
    CardDTO getCardById(long cardId);


}
