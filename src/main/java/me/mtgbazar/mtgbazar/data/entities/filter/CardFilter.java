package me.mtgbazar.mtgbazar.data.entities.filter;

import lombok.Data;

@Data
public class CardFilter {
    private Integer power;
    private Integer toughness;
    private Boolean nonFoil = true;
    private Boolean reprint = false;
    private Boolean textLess = false;
    private Boolean R;
    private Boolean G;
    private Boolean U;
    private Boolean B;
    private Boolean W;
    private Boolean Colorless;
    private String cardName = "";
    private String artistName = "";
    private String frame = "";
    private String set = "";
    private String keywords = "";
    private Enum<Rarity> rarity = Rarity.common;
}
