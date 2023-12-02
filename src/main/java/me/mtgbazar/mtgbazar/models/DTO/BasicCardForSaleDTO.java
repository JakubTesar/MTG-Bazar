package me.mtgbazar.mtgbazar.models.DTO;

public class BasicCardForSaleDTO {
    private Double cost;
    private String quality;
    private BasicUserDTO sellingUser;
    private BasicCardDTO card;

    public BasicCardDTO getCard() {
        return card;
    }

    public void setCard(BasicCardDTO card) {
        this.card = card;
    }

    public BasicUserDTO getSellingUser() {
        return sellingUser;
    }

    public void setSellingUser(BasicUserDTO sellingUser) {
        this.sellingUser = sellingUser;
    }

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
}
