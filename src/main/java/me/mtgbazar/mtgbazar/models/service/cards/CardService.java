package me.mtgbazar.mtgbazar.models.service.cards;

import me.mtgbazar.mtgbazar.data.entities.filter.CardFilter;
import me.mtgbazar.mtgbazar.models.DTO.CardDTO;
import me.mtgbazar.mtgbazar.models.DTO.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CardService {
    void createCard(CardDTO cardDTO);
    Page<CardDTO> getAll(Pageable pageable, CardFilter filter);
    Page<CardDTO> getAllByOwnerId(Pageable pageable, UserDTO userDTO);

    CardDTO getCardById(long cardId);
    List<UserDTO> getCardOwnersByCardId(long cardId);

    void addCardToAccount(long cardId);

    List<CardDTO> getSellingCardFromUserSellingListByCardId(long cardId, List<CardDTO> cards);

}
