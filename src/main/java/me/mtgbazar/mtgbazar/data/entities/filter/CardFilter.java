package me.mtgbazar.mtgbazar.data.entities.filter;

import lombok.Data;

@Data
public class CardFilter {
    private Integer power;
    private Integer toughness;
    private Boolean nonFoil;
    private Boolean reprint ;
    private Boolean textLess ;
    private Boolean R;
    private Boolean G;
    private Boolean U;
    private Boolean B;
    private Boolean W;
    //private Boolean Colorless;
    private String cardName;
    private String artistName;
    private String frame; //year
    private String set;
    private String keywords;
    private Enum<Rarity> rarity;
}
