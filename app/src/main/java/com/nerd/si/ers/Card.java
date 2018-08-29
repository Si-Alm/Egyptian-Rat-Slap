package com.nerd.si.ers;


public class Card {
    private String suit, name;
    private int path;

    public Card(String suit, String name, int path) {
        this.suit = suit;
        this.name = name;
        this.path = path;
    }

    public Card(String suit, String name) {
        this.suit = suit;
        this.name = name;
        this.path = 0;
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