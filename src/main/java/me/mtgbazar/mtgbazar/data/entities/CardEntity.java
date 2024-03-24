package me.mtgbazar.mtgbazar.data.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.List;

import static org.hibernate.annotations.CascadeType.DELETE_ORPHAN;

//artist,artistIds,asciiName,attractionLights,availability,boosterTypes,borderColor,cardParts,colorIdentity,
// colorIndicator,colors,defense,duelDeck,edhrecRank,edhrecSaltiness,faceConvertedManaCost,faceFlavorName,
// faceManaValue,faceName,finishes,flavorName,flavorText,frameEffects,frameVersion,hand,hasAlternativeDeckLimit,
// hasContentWarning,hasFoil,hasNonFoil,isAlternative,isFullArt,isFunny,isOnlineOnly,isOversized,
@Setter
@Getter


@Entity
@Table(name = "cards")
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long CardId;
    @Column
    private String name;
    @Column
    private String id;
    @ManyToMany
    private List<UserEntity> ownedUsers;
    @OneToMany(mappedBy = "card")
    private List<CardForSaleEntity> cardForSale;
    @OneToMany(mappedBy = "watchedCard")
    private List<WatchlistEntity> watchlistEntities;
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


}
