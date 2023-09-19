package me.mtgbazar.mtgbazar.models.service.cards;

import me.mtgbazar.mtgbazar.data.entities.CardEntity;
import me.mtgbazar.mtgbazar.data.entities.UserEntity;
import me.mtgbazar.mtgbazar.data.repositories.CardsRepositories;
import me.mtgbazar.mtgbazar.data.repositories.UsersRepositories;
import me.mtgbazar.mtgbazar.models.DTO.CardDTO;
import me.mtgbazar.mtgbazar.models.DTO.mappers.CardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardsRepositories cardsRepositories;

    @Autowired
    private UsersRepositories usersRepositories;
    @Autowired
    private CardMapper cardMapper;

    @Override
    public void createCard(CardDTO cardDTO) {
        CardEntity card = cardMapper.toEntity(cardDTO);
        cardsRepositories.save(card);
    }

    @Override
    public List<CardDTO> getAll() {
        return StreamSupport.stream(cardsRepositories.findAll().spliterator(), false)
                .map(i -> cardMapper.toDTO(i))
                .toList();
    }

    @Override
    public CardDTO getCardById(long cardId) {
        CardEntity card = cardsRepositories.findById(cardId).orElseThrow();
        return cardMapper.toDTO(card);
    }

    @Override
    public void addCardToAccount() {
        UserEntity user = usersRepositories.findById(1L).orElseThrow();
        CardEntity card = cardsRepositories.findById(1L).orElseThrow();

        user.getCards().add(card);
        usersRepositories.save(user);
    }


}
