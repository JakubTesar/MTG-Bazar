package me.mtgbazar.mtgbazar.data.json;

import com.google.gson.Gson;
import me.mtgbazar.mtgbazar.models.DTO.CardDTO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CardJsonList {
    public static void main(String[] args) throws IOException {
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
        for (CardJSON c : cards) {
            if (c.getImage_uris() != null) {
                System.out.println(c.getImage_uris().getNormal());
            }
        }
    }
}
