package me.mtgbazar.mtgbazar.models.service.trade;

import me.mtgbazar.mtgbazar.models.DTO.CardForSaleDTO;

public interface TradeService {
    void forSaleCard(long cardId, CardForSaleDTO cardForSaleDTO);
}
