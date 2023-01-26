package main;

import java.util.Random;

public class Card {
    private int number;
    private String face;
    private String suit;

    public Card(){
        this.number = randInt(2, 14);
        if(number > 10){
            if(number == 11){
                face = "J";
            } else if(number==12){
                face = "Q";
            } else if(number==13){
                face = "K";
            } else{
                face = "A";
            }
            number = 11;
        }
    }

    public String getFace() {
        return face;
    }

    public int getNumber() {
        return number;
    }

    public String getSuit() {
        return suit;
    }
    
    public int randInt(int min, int max) {
        Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
    }

}
