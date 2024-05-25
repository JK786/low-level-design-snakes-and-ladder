package org.jibran.services;

import lombok.Data;

import java.util.Random;

@Data
public class Dice {

    //Assuming a 6 faced dice
    public Integer roll() {
        return new Random().nextInt(6) + 1;
    }
}
