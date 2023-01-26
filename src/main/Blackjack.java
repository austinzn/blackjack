package main;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Blackjack {
    ArrayList<Card> pcards = new ArrayList<>();
    ArrayList<Card> dcards = new ArrayList<>();
    Boolean playing = true;
    Scanner scanner = new Scanner(System.in);

    public void playBlackjack() {
        while (playing == true) {
            pcards = new ArrayList<>();
            dcards = new ArrayList<>();
            pcards.add(new Card());
            pcards.add(new Card());
            dcards.add(new Card());
            dcards.add(new Card());
            play();
            if (cardValue(pcards) < 21 && cardValue(dcards) < 21) {
                hitOrHold();
                if (cardValue(pcards) < 21) {
                    dealerDraw(dcards);
                }
            }
            checkResult(cardValue(pcards), cardValue(dcards));
            System.out.println("Play again? (yes or no) ");
            String temp = scanner.nextLine();
            while (!temp.equals("yes") && !temp.equals("no")) {
                System.out.println("Play again? (yes or no) ");
                temp = scanner.nextLine();
            }
            if (temp.equals("no")) {
                playing = false;
            }
        }
    }

    public void hitOrHold() {
        String x;
        Boolean cont = true;
        while (cont) {
            System.out.println("Hit or Hold?: ");
            x = scanner.nextLine();
            if (x.equals("Hit") || x.equals("hit")) {
                cardDraw();
                pcards.add(new Card());
                this.play();
                if (cardValue(pcards) >= 21) {
                    cont = false;
                }
            } else if (x.equals("Hold") || x.equals("hold")) {
                cont = false;
            }
        }
    }

    public void cardDraw() {
        System.out.println("Drawing a card...");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public Boolean isValid(int val) {
        if (val < 21) {
            return true;
        } else {
            return false;
        }
    }

    public void checkResult(int pc, int dc) {
        if (pc == dc) {
            System.out.println("Tie :/");
        } else if (pc == 21 && dc != 21) {
            System.out.println("Blackjack! You win! :D");
        } else if (dc == 21) {
            System.out.println("You lost! :(");
        } else if (pc > 21) {
            System.out.println("You lost! :(");
        } else if (pc < 21) {
            if (dc > 21) {
                System.out.println("You won! :)");
            } else if (dc < 21) {
                if (pc > dc) {
                    System.out.println("You won! :)");
                } else {
                    System.out.println("You lost! :(");
                }
            }
        }
    }

    public int cardValue(ArrayList<Card> cards) {
        int value = 0;
        for (Card card : cards) {
            if (card.getNumber() > 10) {
                if (card.getFace().equals("A")) {
                    if (value < 21) {
                        value += 11;
                    } else {
                        value += 1;
                    }
                } else {
                    value += card.getNumber() - 1;
                }
            } else {
                value += card.getNumber();
            }
        }
        return value;
    }

    public void play() {
        System.out.println("Dealer cards:");
        for (Card card : dcards) {
            if (card.getNumber() < 11) {
                System.out.print(card.getNumber() + " ");
            } else {
                System.out.print(card.getFace() + " ");
            }
        }
        System.out.println("The dealer's current score is: " + cardValue(dcards) + "\n");
        System.out.println("Player cards:");
        for (Card card : pcards) {
            if (card.getNumber() < 11) {
                System.out.print(card.getNumber() + " ");
            } else if (card.getNumber() > 10) {
                System.out.print(card.getFace() + " ");
            }
        }
        System.out.println("Your current score is: " + cardValue(pcards) + "\n");
    }

    public void dealerDraw(ArrayList<Card> cards) {
        while (cardValue(cards) < 17) {
            cardDraw();
            dcards.add(new Card());
            play();
        }
    }
}
