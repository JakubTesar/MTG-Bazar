package me.mtgbazar.mtgbazar.models.DTO;

import jakarta.persistence.OneToMany;
import me.mtgbazar.mtgbazar.data.entities.CardEntity;
import me.mtgbazar.mtgbazar.data.entities.UserEntity;

import java.util.List;

public class CardForSaleDTO {
    private Double cost;
    private String quality;

    private UserEntity sellingUser;

    private CardEntity card;

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

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
}
