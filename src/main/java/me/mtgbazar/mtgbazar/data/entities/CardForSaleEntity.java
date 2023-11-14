package me.mtgbazar.mtgbazar.data.entities;

import jakarta.persistence.*;
import me.mtgbazar.mtgbazar.data.entities.CardEntity;
import me.mtgbazar.mtgbazar.data.entities.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cards_for_sale")
public class CardForSaleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cardForSaleId;
    @Column
    private double cost;

    @Column
    private String quality;

    @ManyToOne
    private UserEntity sellingUser;
    @ManyToOne
    private CardEntity card;


    public UserEntity getSellingUser() {
        return sellingUser;
    }

    public void setSellingUser(UserEntity sellingUser) {
        this.sellingUser = sellingUser;
    }

    public CardEntity getCard() {
        return card;
    }

    public void setCard(CardEntity card) {
        this.card = card;
    }

    public long getCardForSaleId() {
        return cardForSaleId;
    }

    public void setCardForSaleId(long cardForSaleId) {
        this.cardForSaleId = cardForSaleId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }
}
