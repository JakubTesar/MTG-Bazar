package me.mtgbazar.mtgbazar.data.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "watch_list")
public class WatchlistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dogId;
    @ManyToOne()
    private CardEntity watchedCard;
    @ManyToOne()
    private UserEntity userWatching;

}
