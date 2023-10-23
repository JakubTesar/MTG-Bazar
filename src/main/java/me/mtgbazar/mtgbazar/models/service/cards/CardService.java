package me.mtgbazar.mtgbazar.models.service.cards;

import me.mtgbazar.mtgbazar.models.DTO.CardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CardService {
    void createCard(CardDTO cardDTO);
    Page<CardDTO> getAll(Pageable pageable);

    CardDTO getCardById(long cardId);

    void addCardToAccount();


}
