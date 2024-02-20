package me.mtgbazar.mtgbazar.data.entities;

import jakarta.persistence.*;
import org.checkerframework.common.aliasing.qual.Unique;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    @Unique
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @ManyToMany(mappedBy = "ownedUsers")
    private List<CardEntity> cards;
    @OneToMany(mappedBy = "userWatching")
    private List<WatchlistEntity> watchlistEntities;
    @OneToMany(mappedBy = "sellingUser")
    private List<CardForSaleEntity> cardsForSale;

    public List<CardForSaleEntity> getCardsForSale() {
        return cardsForSale;
    }

    public void setCardsForSale(List<CardForSaleEntity> cardsForSale) {
        this.cardsForSale = cardsForSale;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<CardEntity> getCards() {
        return cards;
    }

    public void setCards(List<CardEntity> cards) {
        this.cards = cards;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<WatchlistEntity> getWatchlistEntities() {
        return watchlistEntities;
    }

    public void setWatchlistEntities(List<WatchlistEntity> watchlistEntities) {
        this.watchlistEntities = watchlistEntities;
    }
}
