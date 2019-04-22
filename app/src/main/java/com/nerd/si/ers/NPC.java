package com.nerd.si.ers;

import java.util.ArrayList;

public class NPC extends Player{
    /*
      This int will be used to determine
      how quickly the NPC player will slap

      There will need to be an algorithm created
      to determine the amount of time this will
      be and how this int will factor into said
      algorithm.

      The current idea is that the field will be
      1 - 5(or something like that) where the lower
      number indicates the greater "difficulty". The
      number will then be multiplied by a pre-determined
      amount of time and that will be how quickly the
      NPC will slap
     */
    int difficulty;


    public NPC(ArrayList<Card> cards) {
        super(cards);
    }

    public NPC(Card[] cards) {
        super(cards);
    }

    public NPC() {
        super();
    }

    public int getDifficulty() {
        return difficulty;
    }

    public double getSlapSeconds() {
        return 0.25*difficulty;
    }


}
