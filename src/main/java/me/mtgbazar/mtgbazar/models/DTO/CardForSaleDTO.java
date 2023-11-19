package me.mtgbazar.mtgbazar.models.DTO;

import jakarta.persistence.OneToMany;
import me.mtgbazar.mtgbazar.data.entities.CardEntity;
import me.mtgbazar.mtgbazar.data.entities.UserEntity;

import java.util.List;

public class CardForSaleDTO {
    private Double cost;
    private String quality;

//    private UserDTO sellingUser;

//    private CardDTO card;

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

//    public UserDTO getSellingUser() {
//        return sellingUser;
//    }
//
//    public void setSellingUser(UserDTO sellingUser) {
//        this.sellingUser = sellingUser;
//    }

//    public CardDTO getCard() {
//        return card;
//    }
//
//    public void setCard(CardDTO card) {
//        this.card = card;
//    }
}
