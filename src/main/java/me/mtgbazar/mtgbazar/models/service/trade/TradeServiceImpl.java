package me.mtgbazar.mtgbazar.models.service.trade;

import me.mtgbazar.mtgbazar.data.entities.CardEntity;
import me.mtgbazar.mtgbazar.data.entities.UserEntity;
import me.mtgbazar.mtgbazar.data.repositories.CardsRepositories;
import me.mtgbazar.mtgbazar.data.repositories.UsersRepositories;
import me.mtgbazar.mtgbazar.models.DTO.CardForSaleDTO;
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

    @Override
    public void forSaleCard(long cardId, CardForSaleDTO cardForSaleDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String singedUserEmail = authentication.getName();
        UserEntity user = usersRepositories.findByEmail(singedUserEmail).orElseThrow();
        CardEntity card = cardsRepositories.findById(cardId).orElseThrow();
        user.getCards().remove(card);
        card.setCost(cardForSaleDTO.getCost());
        card.setQuality(cardForSaleDTO.getQuality());
        card.setForSale(true);
        user.getCards().add(card);
        usersRepositories.save(user);
    }
}
