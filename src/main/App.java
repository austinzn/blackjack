package main;

import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws Exception {
        Blackjack blackjack = new Blackjack();
        System.out.println("Welcome to blackjack!");
        System.out.println("The game is being prepared...");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        blackjack.playBlackjack();
    }
}
