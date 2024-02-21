package me.mtgbazar.mtgbazar.models.service.trade;

import me.mtgbazar.mtgbazar.data.entities.CardEntity;
import me.mtgbazar.mtgbazar.data.entities.CardForSaleEntity;
import me.mtgbazar.mtgbazar.data.entities.UserEntity;
import me.mtgbazar.mtgbazar.data.entities.WatchlistEntity;
import me.mtgbazar.mtgbazar.data.repositories.CardsForSaleRepositories;
import me.mtgbazar.mtgbazar.data.repositories.CardsRepositories;
import me.mtgbazar.mtgbazar.data.repositories.UsersRepositories;
import me.mtgbazar.mtgbazar.data.repositories.WatchlistRepositories;
import me.mtgbazar.mtgbazar.models.DTO.CardForSaleDTO;
import me.mtgbazar.mtgbazar.models.DTO.mappers.CardForSaleMapper;
import me.mtgbazar.mtgbazar.models.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    WatchlistRepositories watchlistRepositories;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserService userService;

    @Override
    public void forSaleCard(long cardId, CardForSaleDTO cardForSaleDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String singedUserUsername = authentication.getName();
        UserEntity user = usersRepositories.findByUsername(singedUserUsername).orElseThrow();
        CardEntity card = cardsRepositories.findById(cardId).orElseThrow();
        CardForSaleEntity cardForSale = cardForSaleMapper.toEntity(cardForSaleDTO);
        List<WatchlistEntity> listAll = (List<WatchlistEntity>) watchlistRepositories.findAll();
        List<WatchlistEntity> listFiltered = listAll.stream().filter(i -> i.getWatchedCard().getCardId() == cardId).toList();
        for(WatchlistEntity entity : listFiltered) {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("noreplybazarmtg@gmail.com");
                message.setTo(entity.getUserWatching().getEmail());
                message.setSubject("Kartička je k dostání");
                message.setText("Kartička " + card.getName() + " je k dostání na MTG - Bazar.");
                mailSender.send(message);
        }
        cardForSale.setCard(card);
        user.getCardsForSale().add(cardForSale);
        cardForSale.setSellingUser(user);
        cardsForSaleRepositories.save(cardForSale);
        card.getOwnedUsers().remove(user);
        usersRepositories.save(user);
    }
}
