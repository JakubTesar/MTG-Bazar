package me.mtgbazar.mtgbazar.data.repositories;

import me.mtgbazar.mtgbazar.data.entities.CardEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CardsRepositories extends CrudRepository<CardEntity, Long> {
   /* @Query("""

""")
    Optional<CardEntity> fetchCardEntity(int id);*/
}
