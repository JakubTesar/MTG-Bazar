package me.mtgbazar.mtgbazar.data.repositories;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import me.mtgbazar.mtgbazar.data.entities.CardEntity;
import me.mtgbazar.mtgbazar.data.entities.QCardEntity;
import me.mtgbazar.mtgbazar.data.entities.filter.CardFilter;
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
}