package me.mtgbazar.mtgbazar.data.repositories;

import me.mtgbazar.mtgbazar.data.entities.CardEntity;
import org.springframework.data.repository.CrudRepository;

public interface CardsRepositories extends CrudRepository<CardEntity, Long> {

}
