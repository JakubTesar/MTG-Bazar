package me.mtgbazar.mtgbazar.models;

import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class RandomNumberService {
    // number 1 = min
    // number 2 = max
    public int randomize(int number1, int number2){
        Random random = new Random();
        if (number1 <= number2){
            return (random.nextInt(number2 - number1 + 1) + number1);
        } else {
            throw new RuntimeException();
        }

    }
}
