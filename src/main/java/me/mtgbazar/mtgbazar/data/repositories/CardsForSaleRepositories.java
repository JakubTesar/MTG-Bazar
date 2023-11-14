package me.mtgbazar.mtgbazar.data.repositories;

import me.mtgbazar.mtgbazar.data.entities.CardForSaleEntity;
import org.springframework.data.repository.CrudRepository;

public interface CardsForSaleRepositories extends CrudRepository<CardForSaleEntity, Long> {
}
