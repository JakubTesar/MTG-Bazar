package me.mtgbazar.mtgbazar.data.repositories;

import me.mtgbazar.mtgbazar.data.entities.CardEntity;
import me.mtgbazar.mtgbazar.data.entities.filter.CardFilter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardsRepositories extends CrudRepository<CardEntity, Long> {

//    @Query(value = "SELECT * " +
//            "FROM MTG-Bazar.card_entity c " +
//            "JOIN card_entity_owned_users ou ON (c.card_id = ou.cards_card_id) " +
//            "WHERE ou.owned_users_id = :#{ownerId}"
//            , nativeQuery = true)
  //  List<CardEntity> findAllByOwnerId(@Param("ownerId") long ownerId);
//    @Query(value = "SELECT c FROM card_entity c WHERE" +
//            "    (:#{#filter.power} is null OR c.power >= :#{#filter.getPower()}) " +
//            " AND (:#{#filter.toughness} is null OR c.toughness = :#{#filter.getToughness()}) " +
//            " AND (:#{#filter.nonFoil} = false OR c.nonfoil = :#{#filter.getNonFoil()})  " +
//            " AND (:#{#filter.reprint} = false OR c.reprint = :#{#filter.getReprint()})  " +
//            " AND (:#{#filter.textLess} = false OR c.textless = :#{#filter.getTextLess()})  " +
//            " AND (:#{#filter.cardName} = '' OR :#{#filter.getCardName()} IN (c.name)) " +
//            " AND (:#{#filter.artistName} = '' OR :#{#filter.getArtistName()} IN (c.artist))" +
//            " AND (:#{#filter.frame} = '' OR :#{#filter.getFrame()} IN (c.frame)) " +
//            " AND (:#{#filter.set} = '' OR :#{#filter.getSet()} IN (c.set)) " +
//            " AND (:#{#filter.cardName} = '' OR :#{#filter.getCardName()} IN (c.name)) " +
//            " AND (:#{#filter.artistName} = '' OR :#{#filter.getArtistName()} IN (c.artist))")
//    List<CardEntity> getFilteredMovies(CardFilter filter, Pageable pageable);

}
