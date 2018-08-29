package com.nerd.si.ers;
import java.util.ArrayList;

public class Player {
    protected ArrayList<Card> hand;

    public Player(ArrayList<Card> cards) {
        for(int i=0; i<cards.size(); i++)
            hand.add(cards.get(i));
    }

    public Player(Card[] cards) {
        for(int i=0; i<cards.length; i++)
            hand.add(cards[i]);
    }

    public Player() {
        hand = new ArrayList<>();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public Card getCard(int pos) {
        return hand.get(pos);
    }

    public void removeCard(int pos) {
        hand.remove(pos);
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

}
