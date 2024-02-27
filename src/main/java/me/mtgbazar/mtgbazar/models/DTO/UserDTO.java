package me.mtgbazar.mtgbazar.models.DTO;

import jakarta.persistence.Column;

import java.util.List;

public class UserDTO {
    private long id;
    private String username;
    private String email;
    private String password;
    private List<CardDTO> cards;
    private List<BasicCardForSaleDTO> cardsForSale;
    private String verificationKey;
    private boolean isVerified;

    public String getVerificationKey() {
        return verificationKey;
    }

    public void setVerificationKey(String verificationKey) {
        this.verificationKey = verificationKey;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public List<BasicCardForSaleDTO> getCardsForSale() {
        return cardsForSale;
    }

    public void setCardsForSale(List<BasicCardForSaleDTO> cardsForSale) {
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














