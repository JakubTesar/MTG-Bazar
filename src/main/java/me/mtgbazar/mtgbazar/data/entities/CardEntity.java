package me.mtgbazar.mtgbazar.data.entities;

import jakarta.persistence.*;

//Jméno karty; Cena v Kč; Edice; Typ; Rarita; Počet
@Entity
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long CardId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double cost;

    @Column(nullable = false)
    private String edition;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String rarity;

    @Column(nullable = false)
    private int count;

    public long getCardId() {
        return CardId;
    }

    public void setCardId(long cardId) {
        CardId = cardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
