package me.mtgbazar.mtgbazar.data.repositories;

import me.mtgbazar.mtgbazar.data.entities.CardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CardsRepositories extends CrudRepository<CardEntity, Long> {
//    Page<CardEntity> findAllBy(Pageable page);
//
//
//    @Query(value = "SELECT * FROM MTG-Bazar.card_entity LIMIT :limit", nativeQuery = true)
//    List<CardEntity> findAllBy(@Param("limit") int limit);

}
