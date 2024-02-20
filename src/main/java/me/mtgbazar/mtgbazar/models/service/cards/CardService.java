package me.mtgbazar.mtgbazar.models.service.cards;

import me.mtgbazar.mtgbazar.data.entities.CardEntity;
import me.mtgbazar.mtgbazar.data.entities.filter.CardFilter;
import me.mtgbazar.mtgbazar.models.DTO.BasicCardForSaleDTO;
import me.mtgbazar.mtgbazar.models.DTO.CardDTO;
import me.mtgbazar.mtgbazar.models.DTO.CardForSaleDTO;
import me.mtgbazar.mtgbazar.models.DTO.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CardService {
    /**
     * Retrieves a page of CardDTO objects based on
     * the provided pagination and filtering criteria.
     *
     * @param pageable The pagination information including
     *                page number, page size, and sorting criteria.
     * @param filter   The filtering criteria to apply
     *                 to the retrieved CardDTO objects.
     * @return A Page object containing a list of CardDTO objects
     * based on the provided pagination and filtering criteria.
     */
    Page<CardDTO> getAll(Pageable pageable, CardFilter filter);
    Page<CardDTO> getAllByOwnerId(Pageable pageable,CardFilter filter, UserDTO userDTO);
    Page<CardForSaleDTO> getAllSellingByOwnerId(Pageable pageable, CardFilter filter, UserDTO userDTO);
    CardDTO getCardById(long cardId);
    Page<BasicCardForSaleDTO> getPagedOffers(Pageable pageable, long cardId);
    List<UserDTO> getCardOwnersByCardId(long cardId);
    void addCardToAccount(long cardId);
    void deleteCard(long cardId);
    void createCard(List<CardEntity> cards);
    void toggleCardWatchlist(long cardId);
    boolean isAlreadyWatched(long cardId);

}
