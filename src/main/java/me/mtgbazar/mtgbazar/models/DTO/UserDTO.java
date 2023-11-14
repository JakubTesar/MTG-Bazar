package me.mtgbazar.mtgbazar.models.DTO;

import java.util.List;

public class UserDTO {
    private long id;
    private String username;
    private String email;
    private String password;
    private List<CardDTO> cards;
    private List<CardForSaleDTO> cardsForSale;

    public List<CardForSaleDTO> getCardsForSale() {
        return cardsForSale;
    }

    public void setCardsForSale(List<CardForSaleDTO> cardsForSale) {
        this.cardsForSale = cardsForSale;
    }

    public String getUsername() {
        return username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<CardDTO> getCards() {
        return cards;
    }

    public void setCards(List<CardDTO> cards) {
        this.cards = cards;
    }
}














