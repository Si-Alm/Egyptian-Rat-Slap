package com.nerd.si.ers;


public class Card {
    private String suit, name;
    private int path, requiredPlays;
    private boolean isAFace;


    public Card(String suit, String name, int path) {
        this.suit = suit;
        this.name = name;
        this.path = path;

        if(name.equals("Jack") || name.equals("Queen") || name.equals("King") || name.equals("Ace"))
            isAFace = true;
        else
            isAFace = false;

        if(name.equals("Jack"))
            this.requiredPlays = 1;
        else if(name.equals("Queen"))
            this.requiredPlays = 2;
        else if(name.equals("King"))
            this.requiredPlays = 3;
        else if(name.equals("Ace"))
            this.requiredPlays = 4;
        else
            this.requiredPlays = 0;
    }

    public Card(String suit, String name) {
        this.suit = suit;
        this.name = name;
        this.path = 0;
        if(name.equals("Jack") || name.equals("Queen") || name.equals("King") || name.equals("Ace"))
            isAFace = true;
        else
            isAFace = false;

        if(name.equals("Jack"))
            this.requiredPlays = 1;
        else if(name.equals("Queen"))
            this.requiredPlays = 2;
        else if(name.equals("King"))
            this.requiredPlays = 3;
        else if(name.equals("Ace"))
            this.requiredPlays = 4;
        else
            this.requiredPlays = 0;

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

    public boolean getIsAFace() {
        return isAFace;
    }

    public int getRequiredPlays() {
        return requiredPlays;
    }

}