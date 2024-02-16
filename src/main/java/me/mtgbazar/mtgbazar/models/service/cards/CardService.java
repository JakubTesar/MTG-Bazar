package me.mtgbazar.mtgbazar.models.service.cards;

import me.mtgbazar.mtgbazar.data.entities.CardEntity;
import me.mtgbazar.mtgbazar.data.entities.CardForSaleEntity;
import me.mtgbazar.mtgbazar.data.entities.filter.CardFilter;
import me.mtgbazar.mtgbazar.models.DTO.CardDTO;
import me.mtgbazar.mtgbazar.models.DTO.CardForSaleDTO;
import me.mtgbazar.mtgbazar.models.DTO.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CardService {
    void createCard(List<CardEntity> cards);
    Page<CardDTO> getAll(Pageable pageable, CardFilter filter);
    Page<CardDTO> getAllByOwnerId(Pageable pageable,CardFilter filter, UserDTO userDTO);
    Page<CardForSaleDTO> getAllSellingByOwnerId(Pageable pageable, CardFilter filter, UserDTO userDTO);
    CardDTO getCardById(long cardId);
    List<UserDTO> getCardOwnersByCardId(long cardId);
    void addCardToAccount(long cardId);
    void deleteCard(long cardId);


}
