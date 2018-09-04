package com.nerd.si.ers;

import java.util.ArrayList;

public class Deck {
    protected ArrayList<Card> cards;
    int size;

    public Deck(String[] ranks, String[] suits, int[] paths) {
        cards = new ArrayList<>();

        for (int i = 0; i < ranks.length; i++) { //iterates through the ranks
            for (String suitString : suits) { //iterates through the suits
                cards.add(new Card(ranks[i], suitString)); //each combination of suit and ranks created(i.e a full deck of cards)
            }
        }
        size = cards.size();

        for(int i=0; i<paths.length; i++) //iterates through paths array
            cards.get(i).setPath(paths[i]); //each card in cards is given the appropriate imageResource int

        //shuffle();
    }

    public void shuffle() {
        for (int k = cards.size() - 1; k > 0; k--) { //iterates through half of array and swaps each card with a random index
            int howMany = k + 1;
            int start = 0;
            int randPos = (int) (Math.random() * howMany) + start;
            Card temp = cards.get(k);
            cards.set(k, cards.get(randPos));
            cards.set(randPos, temp);
        }
        size = cards.size();
    }


    //Getter for card at give position and
    public Card getCard(int pos) {
        return cards.get(pos);
    }

    //getter for size of cards arrayList
    public int getSize() {
        return cards.size();
    }

    public void removeCard(int pos) {
        cards.remove(pos);
    }

}
