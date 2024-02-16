package me.mtgbazar.mtgbazar.models.service.cards;

import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import me.mtgbazar.mtgbazar.data.entities.CardEntity;
import me.mtgbazar.mtgbazar.data.entities.CardForSaleEntity;
import me.mtgbazar.mtgbazar.data.entities.QCardEntity;
import me.mtgbazar.mtgbazar.data.entities.UserEntity;
import me.mtgbazar.mtgbazar.data.entities.filter.CardFilter;
import me.mtgbazar.mtgbazar.data.repositories.CardRepository;
import me.mtgbazar.mtgbazar.data.repositories.CardsForSaleRepositories;
import me.mtgbazar.mtgbazar.data.repositories.CardsRepositories;
import me.mtgbazar.mtgbazar.data.repositories.UsersRepositories;
import me.mtgbazar.mtgbazar.models.DTO.CardDTO;
import me.mtgbazar.mtgbazar.models.DTO.CardForSaleDTO;
import me.mtgbazar.mtgbazar.models.DTO.UserDTO;
import me.mtgbazar.mtgbazar.models.DTO.mappers.CardForSaleMapper;
import me.mtgbazar.mtgbazar.models.DTO.mappers.CardMapper;
import me.mtgbazar.mtgbazar.models.DTO.mappers.UserMapper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardsRepositories cardsRepositories;
    @Autowired
    private UsersRepositories usersRepositories;
    @Autowired
    private CardsForSaleRepositories cardsForSaleRepositories;
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
        else cardDTOS =  cardEntities.subList(startItem, toIndex).stream().map(c -> cardMapper.toDTO(c)).toList();
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
    @Transactional
    public void addCardToAccount(long cardId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String singedUserUsername= authentication.getName();
        UserEntity user = usersRepositories.findByUsername(singedUserUsername).orElseThrow();
        CardEntity card = cardsRepositories.findById(cardId).orElseThrow();
        card.getOwnedUsers().add(user);
        //user.getCards().add(card);
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
        else cardForSaleDTOS =  cardEntities.subList(startItem, toIndex).stream().map(c -> cardForSaleMapper.toDTO(c)).toList();
        return new PageImpl<CardForSaleDTO>(cardForSaleDTOS, PageRequest.of(currentPage, pageSize), cardEntities.size());
    }

    @Override
    public void deleteCard(long cardForSaleId) {
      //  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      //  String singedUserEmail = authentication.getName();
       // UserEntity user = usersRepositories.findByEmail(singedUserEmail).orElseThrow();
        //CardForSaleEntity card = cardsForSaleRepositories.findById(cardForSaleId).orElseThrow();
        cardsForSaleRepositories.deleteById(cardForSaleId);
        //user.getCardsForSale().remove(card);

        //cardsForSaleRepositories.findById();
       // usersRepositories.save(user);
    }
}
