package me.mtgbazar.mtgbazar.data.repositories;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import me.mtgbazar.mtgbazar.data.entities.CardEntity;
import me.mtgbazar.mtgbazar.data.entities.QCardEntity;
import me.mtgbazar.mtgbazar.data.entities.filter.CardFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CardRepository {
    @Autowired
    private EntityManager entityManager;

    public List<CardEntity> findAll(CardFilter f) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QCardEntity c = QCardEntity.cardEntity;
        var predicate = c.name.like("%"+f.getCardName()+"%");
        if (f.getPower() != null)
            predicate = predicate.and(c.power.like("%"+f.getPower()+"%"));
        if (f.getToughness() != null)
            predicate = predicate.and(c.toughness.like("%"+f.getToughness()+"%"));
        if (f.getNonFoil() != null)
            predicate = predicate.and(c.nonfoil.eq(f.getNonFoil()));
        if (f.getReprint() != null)
            predicate = predicate.and(c.reprint.eq(f.getReprint()));
        if (f.getTextLess() != null)
            predicate = predicate.and(c.nonfoil.eq(f.getTextLess()));
        if (f.getR() != null)
            predicate = predicate.and(c.colors.like("%"+f.getR()+"%"));
        if (f.getG() != null)
            predicate = predicate.and(c.colors.like("%"+f.getG()+"%"));
        if (f.getU() != null)
            predicate = predicate.and(c.colors.like("%"+f.getU()+"%"));
        if (f.getB() != null)
            predicate = predicate.and(c.colors.like("%"+f.getB()+"%"));
        if (f.getW() != null)
            predicate = predicate.and(c.colors.like("%"+f.getW()+"%"));
        if (f.getArtistName() != null)
            predicate = predicate.and(c.artist.like("%"+f.getArtistName()+"%"));
        if (f.getFrame() != null)
            predicate = predicate.and(c.frame.eq(f.getFrame()));  //year
        if (f.getSet() != null)
            predicate = predicate.and(c.setS.eq(f.getSet()));     //edition
        if (f.getKeywords() != null)
            predicate = predicate.and(c.keywords.eq(f.getKeywords()));
        if (f.getRarity() != null)
            predicate = predicate.and(c.rarity.like("%"+f.getRarity()+"%"));

        return queryFactory.selectFrom(c)
                .where(predicate)
                .fetch();
    }
}
//c.name.eq(f.getCardName())
//                                .and(c.power.eq(String.valueOf(f.getPower())))
//                                .and(c.toughness.eq(String.valueOf(f.getToughness())))
//                                .and(c.nonfoil.eq(f.getNonFoil()))
//                                .and(c.reprint.eq(f.getNonFoil()))
//                                .and(c.textless.eq(f.getNonFoil()))
//                                .and(c.colors.eq(String.valueOf(f.getR())))
//                                .and(c.colors.eq(String.valueOf(f.getG())))
//                                .and(c.colors.eq(String.valueOf(f.getU())))
//                                .and(c.colors.eq(String.valueOf(f.getB())))
//                                .and(c.colors.eq(String.valueOf(f.getW())))
//                                .and(c.artist.eq(f.getArtistName()))
//                                .and(c.frame.eq(f.getFrame()))  //year
//                                .and(c.setS.eq(f.getSet()))     //edition
//                                .and(c.keywords.eq(f.getKeywords()))
//                                .and(c.rarity.eq(String.valueOf(f.getRarity())))