package me.mtgbazar.mtgbazar.data.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Map;

//artist,artistIds,asciiName,attractionLights,availability,boosterTypes,borderColor,cardParts,colorIdentity,
// colorIndicator,colors,defense,duelDeck,edhrecRank,edhrecSaltiness,faceConvertedManaCost,faceFlavorName,
// faceManaValue,faceName,finishes,flavorName,flavorText,frameEffects,frameVersion,hand,hasAlternativeDeckLimit,
// hasContentWarning,hasFoil,hasNonFoil,isAlternative,isFullArt,isFunny,isOnlineOnly,isOversized,
@Entity
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long CardId;

    @Column
    private String id;
    @Column
    private String oracleId;
    @Column
    private int mtgoId;
    @Column
    private int mtgoFoilId;
    @Column
    private int tcgplayerId;
    @Column
    private int cardmarketId;
    @Column
    private String name;
    @Column
    private String lang;
    @Column
    private String releasedAt;
    @Column
    private String uri;
    @Column
    private String scryfallUri;
    @Column
    private String layout;
    @Column
    private boolean highresImage;
    private String imageStatus;
    @Column
    private String imageUri;
    @Column
    private String manaCost;
    @Column
    private double cmc;
    @Column
    private String typeLine;
    @Column
    private String oracleText;
    @Column
    private String power;
    @Column
    private String toughness;
    @Column
    private String colors;
    @Column
    private String colorIdentity;
    @Column
    private String keywords;
    @Column
    private boolean reserved;
    @Column
    private boolean foil;
    @Column
    private boolean nonfoil;
    @Column
    private boolean oversized;
    @Column
    private boolean promo;
    @Column
    private boolean reprint;
    @Column
    private boolean variation;
    @Column
    private String setId;
    @Column
    private String setS;
    @Column
    private String setName;
    @Column
    private String setType;
    @Column
    private String setUri;
    @Column
    private String setSearchUri;
    @Column
    private String scryfallSetUri;
    @Column
    private String rulingsUri;
    @Column
    private String printsSearchUri;
    @Column
    private String collectorNumber;
    @Column
    private boolean digital;
    @Column
    private String rarity;
    @Column
    private String flavorText;
    @Column
    private String cardBackId;
    @Column
    private String artist;
    @Column
    private String illustrationId;
    @Column
    private String borderColor;
    @Column
    private String frame;
    @Column
    private boolean fullArt;
    @Column
    private boolean textless;
    @Column
    private boolean booster;
    @Column
    private boolean storySpotlight;
    @Column
    private int edhrecRank;
    @Column
    private int pennyRank;


    public long getCardId() {
        return CardId;
    }

    public void setCardId(long cardId) {
        CardId = cardId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOracleId() {
        return oracleId;
    }

    public void setOracleId(String oracleId) {
        this.oracleId = oracleId;
    }

    public int getMtgoId() {
        return mtgoId;
    }

    public void setMtgoId(int mtgoId) {
        this.mtgoId = mtgoId;
    }

    public int getMtgoFoilId() {
        return mtgoFoilId;
    }

    public void setMtgoFoilId(int mtgoFoilId) {
        this.mtgoFoilId = mtgoFoilId;
    }

    public int getTcgplayerId() {
        return tcgplayerId;
    }

    public void setTcgplayerId(int tcgplayerId) {
        this.tcgplayerId = tcgplayerId;
    }

    public int getCardmarketId() {
        return cardmarketId;
    }

    public void setCardmarketId(int cardmarketId) {
        this.cardmarketId = cardmarketId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getReleasedAt() {
        return releasedAt;
    }

    public void setReleasedAt(String releasedAt) {
        this.releasedAt = releasedAt;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getScryfallUri() {
        return scryfallUri;
    }

    public void setScryfallUri(String scryfallUri) {
        this.scryfallUri = scryfallUri;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public boolean isHighresImage() {
        return highresImage;
    }

    public void setHighresImage(boolean highresImage) {
        this.highresImage = highresImage;
    }

    public String getImageStatus() {
        return imageStatus;
    }

    public void setImageStatus(String imageStatus) {
        this.imageStatus = imageStatus;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getManaCost() {
        return manaCost;
    }

    public void setManaCost(String manaCost) {
        this.manaCost = manaCost;
    }

    public double getCmc() {
        return cmc;
    }

    public void setCmc(double cmc) {
        this.cmc = cmc;
    }

    public String getTypeLine() {
        return typeLine;
    }

    public void setTypeLine(String typeLine) {
        this.typeLine = typeLine;
    }

    public String getOracleText() {
        return oracleText;
    }

    public void setOracleText(String oracleText) {
        this.oracleText = oracleText;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getToughness() {
        return toughness;
    }

    public void setToughness(String toughness) {
        this.toughness = toughness;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public String getColorIdentity() {
        return colorIdentity;
    }

    public void setColorIdentity(String colorIdentity) {
        this.colorIdentity = colorIdentity;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public boolean isFoil() {
        return foil;
    }

    public void setFoil(boolean foil) {
        this.foil = foil;
    }

    public boolean isNonfoil() {
        return nonfoil;
    }

    public void setNonfoil(boolean nonfoil) {
        this.nonfoil = nonfoil;
    }

    public boolean isOversized() {
        return oversized;
    }

    public void setOversized(boolean oversized) {
        this.oversized = oversized;
    }

    public boolean isPromo() {
        return promo;
    }

    public void setPromo(boolean promo) {
        this.promo = promo;
    }

    public boolean isReprint() {
        return reprint;
    }

    public void setReprint(boolean reprint) {
        this.reprint = reprint;
    }

    public boolean isVariation() {
        return variation;
    }

    public void setVariation(boolean variation) {
        this.variation = variation;
    }

    public String getSetId() {
        return setId;
    }

    public void setSetId(String setId) {
        this.setId = setId;
    }

    public String getSetS() {
        return setS;
    }

    public void setSetS(String setS) {
        this.setS = setS;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getSetType() {
        return setType;
    }

    public void setSetType(String setType) {
        this.setType = setType;
    }

    public String getSetUri() {
        return setUri;
    }

    public void setSetUri(String setUri) {
        this.setUri = setUri;
    }

    public String getSetSearchUri() {
        return setSearchUri;
    }

    public void setSetSearchUri(String setSearchUri) {
        this.setSearchUri = setSearchUri;
    }

    public String getScryfallSetUri() {
        return scryfallSetUri;
    }

    public void setScryfallSetUri(String scryfallSetUri) {
        this.scryfallSetUri = scryfallSetUri;
    }

    public String getRulingsUri() {
        return rulingsUri;
    }

    public void setRulingsUri(String rulingsUri) {
        this.rulingsUri = rulingsUri;
    }

    public String getPrintsSearchUri() {
        return printsSearchUri;
    }

    public void setPrintsSearchUri(String printsSearchUri) {
        this.printsSearchUri = printsSearchUri;
    }

    public String getCollectorNumber() {
        return collectorNumber;
    }

    public void setCollectorNumber(String collectorNumber) {
        this.collectorNumber = collectorNumber;
    }

    public boolean isDigital() {
        return digital;
    }

    public void setDigital(boolean digital) {
        this.digital = digital;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getFlavorText() {
        return flavorText;
    }

    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }

    public String getCardBackId() {
        return cardBackId;
    }

    public void setCardBackId(String cardBackId) {
        this.cardBackId = cardBackId;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getIllustrationId() {
        return illustrationId;
    }

    public void setIllustrationId(String illustrationId) {
        this.illustrationId = illustrationId;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public String getFrame() {
        return frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public boolean isFullArt() {
        return fullArt;
    }

    public void setFullArt(boolean fullArt) {
        this.fullArt = fullArt;
    }

    public boolean isTextless() {
        return textless;
    }

    public void setTextless(boolean textless) {
        this.textless = textless;
    }

    public boolean isBooster() {
        return booster;
    }

    public void setBooster(boolean booster) {
        this.booster = booster;
    }

    public boolean isStorySpotlight() {
        return storySpotlight;
    }

    public void setStorySpotlight(boolean storySpotlight) {
        this.storySpotlight = storySpotlight;
    }

    public int getEdhrecRank() {
        return edhrecRank;
    }

    public void setEdhrecRank(int edhrecRank) {
        this.edhrecRank = edhrecRank;
    }

    public int getPennyRank() {
        return pennyRank;
    }

    public void setPennyRank(int pennyRank) {
        this.pennyRank = pennyRank;
    }
}
