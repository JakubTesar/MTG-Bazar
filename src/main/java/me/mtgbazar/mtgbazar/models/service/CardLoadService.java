package me.mtgbazar.mtgbazar.models.service;

import me.mtgbazar.mtgbazar.models.DTO.CardDTO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CardLoadService {
    public ArrayList<CardDTO> getAllCardsCSV() throws IOException {
        ArrayList<CardDTO> cards = new ArrayList<>();
        String fileName ="../../../../../../../resources/cards.csv";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            String[] lineArray = line.split(";");
        }
        br.close();
        return cards;
    }
}
