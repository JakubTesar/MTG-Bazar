package me.mtgbazar.mtgbazar.models.service.cards;

import com.google.gson.Gson;
import me.mtgbazar.mtgbazar.data.json.CardJSON;
import me.mtgbazar.mtgbazar.models.DTO.CardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

@Service
public class CardLoadService {

    @Autowired
    CardService cardService;
    //artist,artistIds,asciiName,attractionLights,availability,boosterTypes,borderColor,cardParts,colorIdentity,
    // colorIndicator,colors,defense,duelDeck,edhrecRank,edhrecSaltiness,faceConvertedManaCost,faceFlavorName,
    // faceManaValue,faceName,finishes,flavorName,flavorText,frameEffects,frameVersion,hand,hasAlternativeDeckLimit,
    //----
    // hasContentWarning,hasFoil,hasNonFoil,isAlternative,isFullArt,isFunny,isOnlineOnly,isOversized,
    // isPromo,isRebalanced,isReprint,isReserved,isStarter,isStorySpotlight,isTextless,isTimeshifted,
    // keywords,language,layout,leadershipSkills,life,loyalty,manaCost,manaValue,name,number,originalPrintings,
    // originalReleaseDate,originalText,originalType,otherFaceIds,power,printings,promoTypes,rarity,rebalancedPrintings,
    // relatedCards,securityStamp,setCode,side,signature,subsets,subtypes,supertypes,text,toughness,type,types,uuid,variations,watermark

    public void getAllCardsCSV() throws IOException {
        String filePath = "resources/cards.json";
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }
        reader.close();
        Gson gson = new Gson();
        CardJSON[] cards = gson.fromJson(jsonBuilder.toString(), CardJSON[].class);
        // type var : array
        for (CardJSON c : cards) {
            CardDTO cE = new CardDTO();
            cE.setOracleId(c.getOracleId());
            cE.setMtgoId(c.getMtgoId());
            cE.setMtgoFoilId(c.getMtgoFoilId());
            cE.setTcgplayerId(c.getTcgplayerId());
            cE.setCardmarketId(c.getCardmarketId());
            cE.setName(c.getName());
            cE.setLang(c.getLang());
            cE.setReleasedAt(c.getReleasedAt());
            cE.setUri(c.getUri());
            cE.setScryfallUri(c.getScryfallUri());
            cE.setLayout(c.getLayout());
            cE.setHighresImage(c.isHighresImage());
            cE.setImageStatus(c.getImageStatus());
            if (c.getImage_uris() != null) cE.setImageUri(c.getImage_uris().getLarge());
            else cE.setImageUri("wasNull");
            cE.setManaCost(c.getManaCost());
            cE.setCmc(c.getCmc());
            cE.setTypeLine(c.getTypeLine());
            cE.setOracleText(c.getOracleText());
            cE.setPower(c.getPower());
            cE.setToughness(c.getToughness());
            if (c.getColors() != null) cE.setColors(String.join(",", c.getColors()));
            else cE.setColors("Colorless");
            if (c.getColorIdentity() != null) cE.setColorIdentity((String.join(",",c.getColorIdentity())));
            else cE.setColorIdentity("Colorless");
            if (c.getKeywords() != null) cE.setKeywords((String.join(",",c.getKeywords())));
            else cE.setKeywords("");
            cE.setReserved(c.isReserved());
            cE.setFoil(c.isFoil());
            cE.setNonfoil(c.isNonfoil());
            cE.setOversized(c.isOversized());
            cE.setPromo(c.isPromo());
            cE.setReprint(c.isReprint());
            cE.setVariation(c.isVariation());
            cE.setSetId(c.getSetId());
            cE.setSetS(c.getSet());
            cE.setSetName(c.getSetName());
            cE.setSetType(c.getSetType());
            cE.setSetUri(c.getSetUri());
            cE.setSetSearchUri(c.getSetSearchUri());
            cE.setScryfallSetUri(c.getScryfallSetUri());
            cE.setRulingsUri(c.getRulingsUri());
            cE.setPrintsSearchUri(c.getPrintsSearchUri());
            cE.setCollectorNumber(c.getCollectorNumber());
            cE.setDigital(c.isDigital());
            cE.setRarity(c.getRarity());
            cE.setFlavorText(c.getFlavorText());
            cE.setCardBackId(c.getCardBackId());
            cE.setArtist(c.getArtist());
            cE.setIllustrationId(c.getIllustrationId());
            cE.setBorderColor(c.getBorderColor());
            cE.setFrame(c.getFrame());
            cE.setFullArt(c.isFullArt());
            cE.setTextless(c.isTextless());
            cE.setBooster(c.isBooster());
            cE.setStorySpotlight(c.isStorySpotlight());
            cE.setEdhrecRank(c.getEdhrecRank());
            cE.setPennyRank(c.getPennyRank());

            // Add the CardDTO object to the list
            cardService.createCard(cE);
        }

    }

//        String fileName = "resources/cards_csv.csv";
//        BufferedReader br = new BufferedReader(new FileReader(fileName));
//        String line;
//        br.readLine();
//        while ((line = br.readLine()) != null) {
//            String[] l = line.split(";");
//            CardDTO cE = new CardDTO();
//            for (int i = 0; i < l.length; i++) {
//                cE.setArtist(l[0]);
//                cE.setArtistIds(l[1]);
//                cE.setAsciiName(l[2]);
//                cE.setAttractionLights(l[3]);
//                cE.setAvailability(l[4]);
//                cE.setBoosterTypes(l[5]);
//                cE.setBorderColor(l[6]);
//                cE.setCardParts(l[7]);
//                cE.setColorIdentity(l[8]);
//                cE.setColorIndicator(l[9]);
//                cE.setColors(l[10]);
//                cE.setDefense(l[11]);
//                cE.setDuelDeck(l[12]);
//                cE.setEdhrecRank(l[13]);
//                cE.setEdhrecSaltiness(l[14]);
//                cE.setFaceConvertedManaCost(l[15]);
//                cE.setFaceFlavorName(l[16]);
//                cE.setFaceManaValue(l[17]);
//                cE.setFaceName(l[18]);
//                cE.setFinishes(l[19]);
//                cE.setFlavorName(l[20]);
//                cE.setFlavorText(l[21]);
//                cE.setFrameEffects(l[22]);
//                cE.setFrameVersion(l[23]);
//                cE.setHand(l[24]);
//                cE.setHasAlternativeDeckLimit(l[25]);
//                cE.setHasContentWarning(l[26]);
//                cE.setHasFoil(l[27]);
//                cE.setHasNonFoil(l[28]);
//                cE.setIsAlternative(l[29]);
//                cE.setIsFullArt(l[30]);
//                cE.setIsFunny(l[31]);
//                cE.setIsOnlineOnly(l[32]);
//                cE.setIsOversized(l[33]);
//                cE.setIsPromo(l[34]);
//                cE.setIsRebalanced(l[35]);
//                cE.setIsReprint(l[36]);
//                cE.setIsReserved(l[37]);
//                cE.setIsStarter(l[38]);
//                cE.setIsStorySpotlight(l[39]);
//                cE.setIsTextless(l[40]);
//                cE.setIsTimeshifted(l[41]);
//                cE.setKeywords(l[42]);
//                cE.setLanguage(l[43]);
//                cE.setLayout(l[44]);
//                cE.setLeadershipSkills(l[45]);
//                cE.setLife(l[46]);
//                cE.setLoyalty(l[47]);
//                cE.setManaCost(l[48]);
//                cE.setManaValue(l[49]);
//                cE.setName(l[50]);
//                cE.setNumber(l[51]);
//                cE.setOriginalPrintings(l[52]);
//                cE.setOriginalReleaseDate(l[53]);
//                cE.setOriginalText(l[54]);
//                cE.setOriginalType(l[55]);
//                cE.setOtherFaceIds(l[56]);
//                cE.setPower(l[57]);
//                cE.setPrintings(l[58]);
//                cE.setPromoTypes(l[59]);
//                cE.setRarity(l[60]);
//                cE.setRebalancedPrintings(l[61]);
//                cE.setRelatedCards(l[62]);
//                cE.setSecurityStamp(l[63]);
//                cE.setSetCode(l[64]);
//                cE.setSide(l[65]);
//                cE.setSignature(l[66]);
//                cE.setSubsets(l[67]);
//                cE.setSubtypes(l[68]);
//                cE.setSupertypes(l[69]);
//                cE.setText(l[70]);
//                cE.setToughness(l[71]);
//                cE.setType(l[72]);
//                cE.setTypes(l[73]);
//                cE.setUuid(l[74]);
//
//            }
//            String fileNameIds = "resources/cardIdentifiers.csv";
//            BufferedReader br2 = new BufferedReader(new FileReader(fileNameIds));
//            String line2;
//            br2.readLine();
//            while ((line2 = br2.readLine()) != null) {
//                String[] l2 = line2.split(",");
//                for (int i = 0; i < l2.length; i++) {
//                    if (cE.getUuid().equals(l2[18])){
//                        cE.setScryfallId(l2[13]);
//                    }
//                }
//            }
//
//            cardService.createCard(cE);
//        }
//        br.close();
}

