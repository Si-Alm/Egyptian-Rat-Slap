package com.nerd.si.ers;


public class Card {
    private String suit, name;
    private int path;
    protected boolean isAFace;

    public Card(String suit, String name, int path) {
        this.suit = suit;
        this.name = name;
        this.path = path;

        if(name.equals("Jack") || name.equals("Queen") || name.equals("King") || name.equals("Ace"))
            isAFace = true;
        else
            isAFace = false;
    }

    public Card(String suit, String name) {
        this.suit = suit;
        this.name = name;
        this.path = 0;
        if(name.equals("Jack") || name.equals("Queen") || name.equals("King") || name.equals("Ace"))
            isAFace = true;
        else
            isAFace = false;
    }

    public Card() {
        this.suit = "";
        this.name = "";
        this.path = 0;
        this.isAFace = false;
    }


    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPath() {
        return path;
    }

    public void setPath(int path) {
        this.path = path;
    }


    public String toString() {
        return "The " + name + " of " + suit + ".";
    }


}