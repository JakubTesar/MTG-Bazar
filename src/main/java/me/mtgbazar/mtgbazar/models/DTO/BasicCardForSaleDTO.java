package me.mtgbazar.mtgbazar.models.DTO;

public class BasicCardForSaleDTO {
    private Double cost;
    private String quality;
    private BasicUserDTO sellingUser;

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
