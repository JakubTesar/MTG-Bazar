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
import me.mtgbazar.mtgbazar.models.DTO.UserDTO;
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
    private UserMapper userMapper;

    @Override
    public void createCard(CardDTO cardDTO) {
        CardEntity card = cardMapper.toEntity(cardDTO);
        cardsRepositories.save(card);
    }
    @Override
    public Page<CardDTO> getAll(Pageable pageable, CardFilter filter) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<CardEntity> cardEntities = cardRepository.findAll(filter, pageable);
        List<CardDTO> cardDTOS;
        if (cardEntities.size() < startItem) cardDTOS = Collections.emptyList();
        else {
            cardDTOS = cardEntities.stream().map(c -> cardMapper.toDTO(c)).toList();
        }
        return new PageImpl<CardDTO>(cardDTOS, PageRequest.of(currentPage, pageSize), cardEntities.size());
    }

    @Override
    public Page<CardDTO> getAllByOwnerId(Pageable pageable, UserDTO userDTO) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<CardDTO> cardDTOS = userDTO.getCards();

        if (cardDTOS.size() < startItem) cardDTOS = Collections.emptyList();
        else {
            int toIndex = Math.min(startItem + pageSize, cardDTOS.size());
            cardDTOS = cardDTOS.subList(startItem, toIndex);
        }
        return new PageImpl<CardDTO>(cardDTOS, PageRequest.of(currentPage, pageSize), cardDTOS.size());
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
        String singedUserEmail = authentication.getName();
        UserEntity user = usersRepositories.findByEmail(singedUserEmail).orElseThrow();
        CardEntity card = cardsRepositories.findById(cardId).orElseThrow();
        card.getOwnedUsers().add(user);
        //user.getCards().add(card);
        usersRepositories.save(user);
    }

    @Override
    public List<CardDTO> getSellingCardFromUserSellingListByCardId(long cardId, List<CardDTO> cards) {
        List<CardDTO> sellingCards = new ArrayList<>();
        sellingCards = cards.stream().filter(c -> c.getCardId() == cardId).toList();


        return sellingCards;

    }


}
