package me.mtgbazar.mtgbazar.data.repositories;

import me.mtgbazar.mtgbazar.data.entities.CardEntity;
import me.mtgbazar.mtgbazar.data.entities.UserEntity;
import me.mtgbazar.mtgbazar.data.entities.WatchlistEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface WatchlistRepositories extends CrudRepository<WatchlistEntity, Long> {
    Optional<WatchlistEntity> findAllByWatchedCard(CardEntity watchedCard);
}
