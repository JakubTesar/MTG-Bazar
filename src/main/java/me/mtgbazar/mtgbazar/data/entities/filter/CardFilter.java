package me.mtgbazar.mtgbazar.data.entities.filter;

import lombok.Data;

@Data
public class CardFilter {
    private Integer power;
    private Integer toughness;
    private Boolean nonFoil = null;
    private Boolean reprint = null;
    private Boolean textLess = null;
    private Boolean R = null;
    private Boolean G = null;
    private Boolean U = null; // blue
    private Boolean B = null;
    private Boolean W = null;
    //private Boolean Colorless;
    private String cardName;
    private String artistName;
    private String frame; //year
    private String set;
    private String keywords;
    private Rarity rarity;
}
