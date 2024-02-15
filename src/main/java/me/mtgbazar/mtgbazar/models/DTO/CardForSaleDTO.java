package me.mtgbazar.mtgbazar.models.DTO;

public class CardForSaleDTO {
    private long cardForSaleId;
    private Double cost;
    private String quality;
    private BasicUserDTO sellingUser;
    private CardDTO card;

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

    public BasicUserDTO getSellingUser() {
        return sellingUser;
    }

    public void setSellingUser(BasicUserDTO sellingUser) {
        this.sellingUser = sellingUser;
    }

    public CardDTO getCard() {
        return card;
    }

    public void setCard(CardDTO card) {
        this.card = card;
    }

    public long getCardForSaleId() {
        return cardForSaleId;
    }

    public void setCardForSaleId(long cardForSaleId) {
        this.cardForSaleId = cardForSaleId;
    }
}
