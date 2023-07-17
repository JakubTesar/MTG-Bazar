package me.mtgbazar.mtgbazar.models.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CardDTO {//Jméno karty; Cena v Kč; Edice; Typ; Rarita; Počet
    private long cardId;
    @NotBlank(message = "Vyplňte název")
    @NotNull(message = "Vyplňte název")
    @Size(max = 200, message = "Název je příliš dlouhý")
    private String name;
    private double cost;
    @NotBlank(message = "Vyplňte edici")
    @NotNull(message = "Vyplňte edici")
    private String edition;
    @NotBlank(message = "Vyplňte raritu")
    @NotNull(message = "Vyplňte raritu")
    private String rarity;
    @NotBlank(message = "Vyplňte typ")
    @NotNull(message = "Vyplňte typ")
    private String type;
    private int count;

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
