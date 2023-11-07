package me.mtgbazar.mtgbazar.models.service.cards;

import me.mtgbazar.mtgbazar.data.entities.filter.CardFilter;
import me.mtgbazar.mtgbazar.models.DTO.CardDTO;
import me.mtgbazar.mtgbazar.models.DTO.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CardService {
    void createCard(CardDTO cardDTO);
    Page<CardDTO> getAll(Pageable pageable);
    Page<CardDTO> getAllByOwnerId(Pageable pageable, UserDTO userDTO);

    CardDTO getCardById(long cardId);

    void addCardToAccount(long cardId);


}
