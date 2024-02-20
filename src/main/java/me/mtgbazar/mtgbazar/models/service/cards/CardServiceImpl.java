package me.mtgbazar.mtgbazar.models.service.cards;

import jakarta.transaction.Transactional;
import me.mtgbazar.mtgbazar.data.entities.CardEntity;
import me.mtgbazar.mtgbazar.data.entities.CardForSaleEntity;
import me.mtgbazar.mtgbazar.data.entities.UserEntity;
import me.mtgbazar.mtgbazar.data.entities.WatchlistEntity;
import me.mtgbazar.mtgbazar.data.entities.filter.CardFilter;
import me.mtgbazar.mtgbazar.data.repositories.*;
import me.mtgbazar.mtgbazar.models.DTO.BasicCardForSaleDTO;
import me.mtgbazar.mtgbazar.models.DTO.CardDTO;
import me.mtgbazar.mtgbazar.models.DTO.CardForSaleDTO;
import me.mtgbazar.mtgbazar.models.DTO.UserDTO;
import me.mtgbazar.mtgbazar.models.DTO.mappers.CardForSaleMapper;
import me.mtgbazar.mtgbazar.models.DTO.mappers.CardMapper;
import me.mtgbazar.mtgbazar.models.DTO.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardsRepositories cardsRepositories;
    @Autowired
    private UsersRepositories usersRepositories;
    @Autowired
    private CardsForSaleRepositories cardsForSaleRepositories;
    @Autowired
    private WatchlistRepositories watchlistRepositories;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private CardMapper cardMapper;
    @Autowired
    private CardForSaleMapper cardForSaleMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public void createCard(List<CardEntity> cards) {
        cardsRepositories.saveAll(cards);
    }

    @Override
    public Page<CardDTO> getAll(Pageable pageable, CardFilter filter) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<CardEntity> cardEntities = cardRepository.findAll(filter, pageable);
        List<CardDTO> cardDTOS;
        int toIndex = Math.min(startItem + pageSize, cardEntities.size());
        if (cardEntities.size() < startItem) cardDTOS = Collections.emptyList();
        else cardDTOS = cardEntities.subList(startItem, toIndex).stream().map(c -> cardMapper.toDTO(c)).toList();

        return new PageImpl<CardDTO>(cardDTOS, PageRequest.of(currentPage, pageSize), cardEntities.size());
    }

    @Override
    public Page<CardDTO> getAllByOwnerId(Pageable pageable, CardFilter filter, UserDTO userDTO) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<CardEntity> cardEntities = cardRepository.findAllByOwner(filter, pageable, userMapper.toEntity(userDTO));
        List<CardDTO> cardDTOS;
        int toIndex = Math.min(startItem + pageSize, cardEntities.size());
        if (cardEntities.size() < startItem) cardDTOS = Collections.emptyList();
        else cardDTOS = cardEntities.subList(startItem, toIndex).stream().map(c -> cardMapper.toDTO(c)).toList();
        return new PageImpl<CardDTO>(cardDTOS, PageRequest.of(currentPage, pageSize), cardEntities.size());
    }

    @Override
    public CardDTO getCardById(long cardId) {
        CardEntity card = cardsRepositories.findById(cardId).orElseThrow();
        return cardMapper.toDTO(card);
    }

    @Override
    public List<UserDTO> getCardOwnersByCardId(long cardId) {
        CardEntity card = cardsRepositories.findById(cardId).orElseThrow();
        return card.getOwnedUsers().stream().filter(u -> u.getCards().contains(card)).map(i -> userMapper.toDTO(i)).toList();
    }
    @Override
    public Page<BasicCardForSaleDTO> getPagedOffers(Pageable pageable, long cardId) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<BasicCardForSaleDTO> cardForSaleDTOS = getCardById(cardId).getCardForSale();
        List<BasicCardForSaleDTO> cardsSLegit;
        int toIndex = Math.min(startItem + pageSize, cardForSaleDTOS.size());
        if (cardForSaleDTOS.size() < startItem) cardsSLegit = Collections.emptyList();
        else cardsSLegit = cardForSaleDTOS.subList(startItem, toIndex);
        return new PageImpl<BasicCardForSaleDTO>(cardsSLegit, PageRequest.of(currentPage, pageSize), cardForSaleDTOS.size());
    }
    @Override
    @Transactional
    public void addCardToAccount(long cardId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String singedUserUsername = authentication.getName();
        UserEntity user = usersRepositories.findByUsername(singedUserUsername).orElseThrow();
        CardEntity card = cardsRepositories.findById(cardId).orElseThrow();
        card.getOwnedUsers().add(user);
        usersRepositories.save(user);
    }

    @Override
    public Page<CardForSaleDTO> getAllSellingByOwnerId(Pageable pageable, CardFilter filter, UserDTO userDTO) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<CardForSaleEntity> cardEntities = cardRepository.findAllSellingByOwner(filter, pageable, userMapper.toEntity(userDTO));
        List<CardForSaleDTO> cardForSaleDTOS;
        int toIndex = Math.min(startItem + pageSize, cardEntities.size());
        if (cardEntities.size() < startItem) cardForSaleDTOS = Collections.emptyList();
        else
            cardForSaleDTOS = cardEntities.subList(startItem, toIndex).stream().map(c -> cardForSaleMapper.toDTO(c)).toList();
        return new PageImpl<CardForSaleDTO>(cardForSaleDTOS, PageRequest.of(currentPage, pageSize), cardEntities.size());
    }

    @Override
    public void deleteCard(long cardForSaleId) {
        cardsForSaleRepositories.deleteById(cardForSaleId);
    }

    @Override
    public void toggleCardWatchlist(long cardId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String singedUserUsername = authentication.getName();
        UserEntity user = usersRepositories.findByUsername(singedUserUsername).orElseThrow();
        List<WatchlistEntity> listAll = (List<WatchlistEntity>) watchlistRepositories.findAll();
        List<WatchlistEntity> listFiltered = listAll.stream().filter(i -> i.getWatchedCard().getCardId() == cardId && i.getUserWatching().getId() == user.getId()).toList();
        if (listFiltered.isEmpty()) {
            WatchlistEntity entity = new WatchlistEntity();
            entity.setWatchedCard(cardsRepositories.findById(cardId).orElseThrow());
            entity.setUserWatching(user);
            watchlistRepositories.save(entity);
        } else {
            watchlistRepositories.delete(listFiltered.get(0));
        }
    }
    @Override
    public boolean isAlreadyWatched(long cardId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String singedUserUsername = authentication.getName();
        UserEntity user = usersRepositories.findByUsername(singedUserUsername).orElseThrow();
        List<WatchlistEntity> listAll = (List<WatchlistEntity>) watchlistRepositories.findAll();
        List<WatchlistEntity> listFiltered = listAll.stream().filter(i -> i.getWatchedCard().getCardId() == cardId && i.getUserWatching().getId() == user.getId()).toList();
        return listFiltered.isEmpty();
    }
}
