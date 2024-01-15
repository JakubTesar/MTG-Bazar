package me.mtgbazar.mtgbazar.data.repositories;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import me.mtgbazar.mtgbazar.data.entities.*;
import me.mtgbazar.mtgbazar.data.entities.filter.CardFilter;
import me.mtgbazar.mtgbazar.models.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CardRepository {

    @Autowired
    private EntityManager entityManager;

    public List<CardEntity> findAll(CardFilter f, Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QCardEntity c = QCardEntity.cardEntity;

        var predicate = c.name.like("%%");
        if (f.getCardName() != null)
            predicate = predicate.and(c.name.like("%" + f.getCardName() + "%"));
        if (f.getPower() != null)
            predicate = predicate.and(c.power.like("%" + f.getPower() + "%"));
        if (f.getToughness() != null)
            predicate = predicate.and(c.toughness.like("%" + f.getToughness() + "%"));
        if (f.getNonFoil() != null)
            predicate = predicate.and(c.nonfoil.eq(f.getNonFoil()));
        if (f.getReprint() != null)
            predicate = predicate.and(c.reprint.eq(f.getReprint()));
        if (f.getTextLess() != null)
            predicate = predicate.and(c.nonfoil.eq(f.getTextLess()));
        if (f.getR() != null)
            predicate = predicate.and(c.colors.like("%" + f.getR() + "%"));
        if (f.getG() != null)
            predicate = predicate.and(c.colors.like("%" + f.getG() + "%"));
        if (f.getU() != null)
            predicate = predicate.and(c.colors.like("%" + f.getU() + "%"));
        if (f.getB() != null)
            predicate = predicate.and(c.colors.like("%" + f.getB() + "%"));
        if (f.getW() != null)
            predicate = predicate.and(c.colors.like("%" + f.getW() + "%"));
        if (f.getArtistName() != null)
            predicate = predicate.and(c.artist.like("%" + f.getArtistName() + "%"));
        if (f.getFrame() != null)
            predicate = predicate.and(c.frame.like("%" +f.getFrame()+"%"));        //year
        if (f.getSet() != null)
            predicate = predicate.and(c.setS.like("%" +f.getSet()+"%"));           //edition
        if (f.getKeywords() != null)
            predicate = predicate.and(c.keywords.like("%" +f.getKeywords()+"%"));
        if (f.getRarity() != null)
            predicate = predicate.and(c.rarity.like("%" + f.getRarity() + "%"));

        return queryFactory.selectFrom(c)
                .where(predicate)
//                .offset(pageable.getPageNumber() * 20L)
//                .limit(12)
                .fetch();
    }
    public List<UserEntity> findAllByOwner(CardFilter f, Pageable pageable, UserEntity user) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        QUserEntity u = QUserEntity.userEntity;
        QCardEntity c = QCardEntity.cardEntity;
        var predicate = u.id.eq(user.getId());
            predicate = predicate.and(c.name.like("%%"));
        if (f.getCardName() != null)
            predicate = predicate.and(c.name.like("%" + f.getCardName() + "%"));
        if (f.getPower() != null)
            predicate = predicate.and(c.power.like("%" + f.getPower() + "%"));
        if (f.getToughness() != null)
            predicate = predicate.and(c.toughness.like("%" + f.getToughness() + "%"));
        if (f.getR() != null)
            predicate = predicate.and(c.colors.like("%" + f.getR() + "%"));
        if (f.getG() != null)
            predicate = predicate.and(c.colors.like("%" + f.getG() + "%"));
        if (f.getU() != null)
            predicate = predicate.and(c.colors.like("%" + f.getU() + "%"));
        if (f.getB() != null)
            predicate = predicate.and(c.colors.like("%" + f.getB() + "%"));
        if (f.getW() != null)
            predicate = predicate.and(c.colors.like("%" + f.getW() + "%"));
        if (f.getArtistName() != null)
            predicate = predicate.and(c.artist.like("%" + f.getArtistName() + "%"));
        if (f.getFrame() != null)
            predicate = predicate.and(c.frame.like("%" +f.getFrame()+"%"));        //year
        if (f.getSet() != null)
            predicate = predicate.and(c.setS.like("%" +f.getSet()+"%"));           //edition
        if (f.getKeywords() != null)
            predicate = predicate.and(c.keywords.like("%" +f.getKeywords()+"%"));
        if (f.getRarity() != null)
            predicate = predicate.and(c.rarity.like("%" + f.getRarity() + "%"));

        return queryFactory
                .selectFrom(u)
                .join(u.cards, c)
                .fetchJoin()
                .where(predicate)
                .fetch();
    }
    public List<UserEntity> findAllSellingByOwner(CardFilter f, Pageable pageable, UserEntity user) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QUserEntity u = QUserEntity.userEntity;
        QCardEntity c = QCardEntity.cardEntity;
        QCardForSaleEntity cs = QCardForSaleEntity.cardForSaleEntity;
        var predicate = u.id.eq(user.getId());
        predicate = predicate.and(cs.sellingUser.eq(user));
        predicate = predicate.and(c.name.like("%%"));
        if (f.getCardName() != null)
            predicate = predicate.and(c.name.like("%" + f.getCardName() + "%"));
        if (f.getPower() != null)
            predicate = predicate.and(c.power.like("%" + f.getPower() + "%"));
        if (f.getToughness() != null)
            predicate = predicate.and(c.toughness.like("%" + f.getToughness() + "%"));
        if (f.getR() != null)
            predicate = predicate.and(c.colors.like("%" + f.getR() + "%"));
        if (f.getG() != null)
            predicate = predicate.and(c.colors.like("%" + f.getG() + "%"));
        if (f.getU() != null)
            predicate = predicate.and(c.colors.like("%" + f.getU() + "%"));
        if (f.getB() != null)
            predicate = predicate.and(c.colors.like("%" + f.getB() + "%"));
        if (f.getW() != null)
            predicate = predicate.and(c.colors.like("%" + f.getW() + "%"));
        if (f.getArtistName() != null)
            predicate = predicate.and(c.artist.like("%" + f.getArtistName() + "%"));
        if (f.getFrame() != null)
            predicate = predicate.and(c.frame.like("%" +f.getFrame()+"%"));        //year
        if (f.getSet() != null)
            predicate = predicate.and(c.setS.like("%" +f.getSet()+"%"));           //edition
        if (f.getKeywords() != null)
            predicate = predicate.and(c.keywords.like("%" +f.getKeywords()+"%"));
        if (f.getRarity() != null)
            predicate = predicate.and(c.rarity.like("%" + f.getRarity() + "%"));

        return queryFactory
                .selectFrom(u)
                .join(u.cards, c)
                .fetchJoin()
                .join(u.cardsForSale, cs)
                .fetchJoin()
                .where(predicate)
                .fetch();
    }
}


////
//c.ownedUsers.equals(user.getId());
//var predicate = u.id.eq(user.getId()).and(u.cards.any().name.like("%%"));
//        if (f.getCardName() != null)
//predicate = predicate.and(u.cards.any().name.like("%" + f.getCardName() + "%"));
//        if (f.getPower() != null)
//predicate = predicate.and(u.cards.any().power.like("%" + f.getPower() + "%"));
//        if (f.getToughness() != null)
//predicate = predicate.and(u.cards.any().toughness.like("%" + f.getToughness() + "%"));
//        if (f.getNonFoil() != null)
//predicate = predicate.and(u.cards.any().nonfoil.eq(f.getNonFoil()));
//        if (f.getReprint() != null)
//predicate = predicate.and(u.cards.any().reprint.eq(f.getReprint()));
//        if (f.getTextLess() != null)
//predicate = predicate.and(u.cards.any().nonfoil.eq(f.getTextLess()));
//        if (f.getR() != null)
//predicate = predicate.and(u.cards.any().colors.like("%" + f.getR() + "%"));
//        if (f.getG() != null)
//predicate = predicate.and(u.cards.any().colors.like("%" + f.getG() + "%"));
//        if (f.getU() != null)
//predicate = predicate.and(u.cards.any().colors.like("%" + f.getU() + "%"));
//        if (f.getB() != null)
//predicate = predicate.and(u.cards.any().colors.like("%" + f.getB() + "%"));
//        if (f.getW() != null)
//predicate = predicate.and(u.cards.any().colors.like("%" + f.getW() + "%"));
//        if (f.getArtistName() != null)
//predicate = predicate.and(u.cards.any().artist.like("%" + f.getArtistName() + "%"));
//        if (f.getFrame() != null)
//predicate = predicate.and(u.cards.any().frame.like("%" +f.getFrame()+"%"));        //year
//        if (f.getSet() != null)
//predicate = predicate.and(u.cards.any().setS.like("%" +f.getSet()+"%"));           //edition
//        if (f.getKeywords() != null)
//predicate = predicate.and(u.cards.any().keywords.like("%" +f.getKeywords()+"%"));
//        if (f.getRarity() != null)
//predicate = predicate.and(u.cards.any().rarity.like("%" + f.getRarity() + "%"));
