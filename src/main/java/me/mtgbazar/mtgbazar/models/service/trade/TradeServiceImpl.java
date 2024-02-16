package me.mtgbazar.mtgbazar.models.service.trade;

import me.mtgbazar.mtgbazar.data.entities.CardEntity;
import me.mtgbazar.mtgbazar.data.entities.CardForSaleEntity;
import me.mtgbazar.mtgbazar.data.entities.UserEntity;
import me.mtgbazar.mtgbazar.data.repositories.CardsForSaleRepositories;
import me.mtgbazar.mtgbazar.data.repositories.CardsRepositories;
import me.mtgbazar.mtgbazar.data.repositories.UsersRepositories;
import me.mtgbazar.mtgbazar.models.DTO.CardForSaleDTO;
import me.mtgbazar.mtgbazar.models.DTO.mappers.CardForSaleMapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TradeServiceImpl implements TradeService {
    @Autowired
    UsersRepositories usersRepositories;
    @Autowired
    CardsRepositories cardsRepositories;
    @Autowired
    CardForSaleMapper cardForSaleMapper;
    @Autowired
    CardsForSaleRepositories cardsForSaleRepositories;

    @Override
    public void forSaleCard(long cardId, CardForSaleDTO cardForSaleDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String singedUserUsername = authentication.getName();
        UserEntity user = usersRepositories.findByUsername(singedUserUsername).orElseThrow();
        CardEntity card = cardsRepositories.findById(cardId).orElseThrow();
        CardForSaleEntity cardForSale = cardForSaleMapper.toEntity(cardForSaleDTO);
        cardForSale.setCard(card);
        user.getCardsForSale().add(cardForSale);
        cardForSale.setSellingUser(user);
        cardsForSaleRepositories.save(cardForSale);
        card.getOwnedUsers().remove(user);
        //user.getCards().remove(card);
        usersRepositories.save(user);
       // cardsRepositories.save(card);

    }
}
