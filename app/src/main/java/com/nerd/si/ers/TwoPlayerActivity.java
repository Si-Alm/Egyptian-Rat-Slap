package com.nerd.si.ers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class TwoPlayerActivity extends AppCompatActivity {


    //View widget declarations
    Button p1Play, p1Slap, p2Play, p2Slap;
    Button[] playButtons = new Button[4];
    ImageView prevCardImage, prevCardImage2, mainCard;

    Player p1 = new Player();
    Player p2 = new Player();

    Player[] players = {p1, p2};

    ImageView[] cornerCards = new ImageView[2];
    //Card and deck declarations
    Deck deck;
    Card prevCard, prevPrevCard,resetCard; //will track the previous card in playPile
    ArrayList<Card> playPile;
    //arrays for creating a deck
    final String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
    final String[] names = {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Jack", "Queen", "King", "Ace"};

    TextView p1Count, p2Count;

    int chanceCount;
    boolean isAFaceTurn;

    Toast cardToast;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two_player);

        //Back card resource int
        final int backCard = getResources().getIdentifier("com.nerd.si.ers:drawable/back1", null, null);

        //Array of image resource ints
        int[] paths = {
                getResources().getIdentifier("com.nerd.si.ers:drawable/twospades", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/threespades", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/fourspades", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/fivespades", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/sixspades", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/sevenspades", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/eightspades", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/ninespades", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/tenspades", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/jackspades", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/queenspades", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/kingspades", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/acespades", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/twohearts", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/threehearts", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/fourhearts", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/fivehearts", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/sixhearts", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/sevenhearts", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/eighthearts", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/ninehearts", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/tenhearts", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/jackhearts", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/queenhearts", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/kinghearts", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/acehearts", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/twodiamonds", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/threediamonds", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/fourdiamonds", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/fivediamonds", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/sixdiamonds", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/sevendiamonds", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/eightdiamonds", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/ninediamonds", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/tendiamonds", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/jackdiamonds", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/queendiamonds", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/kingdiamonds", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/acediamonds", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/twoclubs", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/threeclubs", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/fourclubs", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/fiveclubs", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/sixclubs", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/sevenclubs", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/eightclubs", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/nineclubs", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/tenclubs", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/jackclubs", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/queenclubs", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/kingclubs", null, null),
                getResources().getIdentifier("com.nerd.si.ers:drawable/aceclubs", null, null),
        };

        //views initialized to there view ids
        p1Play = findViewById(R.id.a2p1Play);
        p1Slap = findViewById(R.id.a2p1Slap);
        p2Play = findViewById(R.id.a2p2Play);
        p2Slap = findViewById(R.id.a2p2Slap);
        p1Count = findViewById(R.id.p1Count);
        p2Count = findViewById(R.id.p2Count);
        mainCard = findViewById(R.id.a2MainCard);
        prevCardImage = findViewById(R.id.a2prevCard);
        prevCardImage2 = findViewById(R.id.a2prevCard2);

        cornerCards[0] = prevCardImage;
        cornerCards[1] = prevCardImage2;


        //Creates toasts with card image
        cardToast = new Toast(getApplicationContext());
        imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(backCard);
        cardToast.setView(imageView);



        //playPile and deck initialized and shuffled
        playPile = new ArrayList<>();
        deck = new Deck(suits, names, paths);
        resetCard = deck.getCard(0);
        deck.shuffle();


        //Block for dealing deck
        int ct = 0;

        //new dealing algorithm
        while(deck.getSize()!=0) {
            if(ct >=2) //resets to start next round of dealing
                ct=0;
            players[ct].addCard(deck.getCard(deck.getSize()-1)); //adds card to appropriate player
            deck.removeCard(deck.getSize()-1); //removes dealt card from deck
            ct++;
        }

        prevCard = new Card();


        playButtons[0] = p1Play;
        playButtons[1] = p2Play;

        p1Count.setText(""+ p1.getHand().size());
        p2Count.setText(""+p2.getHand().size());

        //play onclickListeners
        p1Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try { //try catch, primary function to catch variations of OutOfBounds Exceptions

                    playPile.add(p1.getCard(0)); //adds top card of player's hand to the playPile
                    p1.removeCard(0); //removes that same card from player's hand

                    if (playPile.size() >= 2) //if the play pile is greater than one, the previous card is set to size-2
                        prevCard = playPile.get(playPile.size() - 2);
                    if (playPile.size() >= 3) //if the play pile is greater than two, the previous previous card is set to size-3
                        prevPrevCard = playPile.get(playPile.size() - 3);

                        /*
                          First basic method for face card rule
                          It checks if the previous played card was a face card and if the next played card isn't a face
                          the block is ran
                          TODO: Use required play field from Card class to determine how many cards the player can use to play a face card
                          TODO: Create a parameter that wouldn't allow a player to use play button if it is not their turn(perhaps a field currentPlayer that is set to the player whose turn it is)
                                (the above field will not be applied to the slap buttons, as those should be able to be used regardless of whose turn it is)
                         */
                    if (prevCard.getIsAFace() || isAFaceTurn) {
                        isAFaceTurn = true;
                        if(prevCard.getIsAFace())
                            chanceCount = prevCard.getRequiredPlays();

                        prevCard = playPile.get(playPile.size() - 1); //sets the previous card

                        if(!playPile.get(playPile.size()-1).getIsAFace()) {
                            chanceCount --;
                            if(chanceCount == 0) {
                                if (!p2.getHand().isEmpty()) {
                                    for (int i = 0; i < playPile.size(); i++) //adds the whole play pile to the previous player's hand
                                        p2.addCard(playPile.get(i));
                                } else {
                                    Toast.makeText(getApplicationContext(), "Player 1 is the WINNNER!", Toast.LENGTH_LONG).show();
                                }
                                isAFaceTurn = false;

                                imageView.setImageResource(playPile.get(playPile.size() - 1).getPath()); //sets the image view for the card toast to the non-face card that was played
                                cardToast.show(); //shows the card that was played as a toast

                                playPile.clear(); //playPile is then cleared
                                mainCard.setImageResource(backCard); //and the imageView is set to the backCard image
                                //loo to set corner cards
                                for (int i = 0; i < cornerCards.length; i++)
                                    cornerCards[i].setImageResource(backCard);
                            }
                        } else if(playPile.get(playPile.size()-1).getIsAFace()) {
                            chanceCount = 0;
                            isAFaceTurn = false;
                        } else {
                            Toast.makeText(getApplicationContext(), "WHOOOPS!!", Toast.LENGTH_LONG).show();
                        }


                    }

                } catch (Exception e) { //catch block used primarily for out of bounds exception(which indicate that a player's hand is empty)
                    Toast.makeText(getApplicationContext(), "Player 1 is out of Cards!", Toast.LENGTH_LONG).show();
                }


                if (playPile.isEmpty()) { // if the play pile was emptied(due to face card rule) the card images are set to the back card image
                    mainCard.setImageResource(backCard);
                    for (int i = 0; i < cornerCards.length; i++)
                        cornerCards[i].setImageResource(backCard);
               }
                else { //if pile wasn't emptied, the card images are set to the played card
                    mainCard.setImageResource(playPile.get(playPile.size() - 1).getPath());
                    if(playPile.size() >=2) {
                        for (int i = 0; i < cornerCards.length; i++)
                            cornerCards[i].setImageResource(playPile.get(playPile.size() - 2).getPath());
                    }
                }

                p1Count.setText(""+p1.getHand().size());
                p2Count.setText(""+p2.getHand().size());

                if(p1.getHand().size() == 52)
                    Toast.makeText(getApplicationContext(), "Player 1 Wins!!!!!!!", Toast.LENGTH_LONG).show();
            }
        });

        p2Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    playPile.add(p2.getCard(0));
                    p2.removeCard(0);
                    if (playPile.size() >= 2)
                        prevCard = playPile.get(playPile.size() - 2);
                    if (playPile.size() >= 3)
                        prevPrevCard = playPile.get(playPile.size() - 3);

                    if (prevCard.getIsAFace() || isAFaceTurn) {
                        isAFaceTurn = true;
                        if(prevCard.getIsAFace())
                            chanceCount = prevCard.getRequiredPlays();

                        prevCard = playPile.get(playPile.size() - 1); //sets the previous card

                        if(!playPile.get(playPile.size()-1).getIsAFace()) {
                            chanceCount --;
                            if(chanceCount == 0) {
                                if (!p1.getHand().isEmpty()) {
                                    for (int i = 0; i < playPile.size(); i++) //adds the whole play pile to the previous player's hand
                                        p1.addCard(playPile.get(i));
                                } else {
                                    Toast.makeText(getApplicationContext(), "Player 2 is the WINNNER!", Toast.LENGTH_LONG).show();
                                }
                                isAFaceTurn = false;

                                imageView.setImageResource(playPile.get(playPile.size() - 1).getPath()); //sets the image view for the card toast to the non-face card that was played
                                cardToast.show(); //shows the card that was played as a toast

                                playPile.clear(); //playPile is then cleared
                                mainCard.setImageResource(backCard); //and the imageView is set to the backCard image
                                //loo to set corner cards
                                for (int i = 0; i < cornerCards.length; i++)
                                    cornerCards[i].setImageResource(backCard);
                            }
                        } else if(playPile.get(playPile.size()-1).getIsAFace()) {
                            chanceCount = 0;
                            isAFaceTurn = false;
                        } else {
                            Toast.makeText(getApplicationContext(), "WHOOOPS!!", Toast.LENGTH_LONG).show();
                        }


                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Player 2 is out of Cards!", Toast.LENGTH_LONG).show();
                }


                if (playPile.isEmpty()) { // if the play pile was emptied(due to face card rule) the card images are set to the back card image
                    mainCard.setImageResource(backCard);
                    for (int i = 0; i < cornerCards.length; i++)
                        cornerCards[i].setImageResource(backCard);
                }
                else { //if pile wasn't emptied, the card images are set to the played card
                    mainCard.setImageResource(playPile.get(playPile.size() - 1).getPath());
                    if(playPile.size() >=2) {
                        for (int i = 0; i < cornerCards.length; i++)
                            cornerCards[i].setImageResource(playPile.get(playPile.size() - 2).getPath());
                    }
                }

                p1Count.setText(""+p1.getHand().size());
                p2Count.setText(""+p2.getHand().size());

                if(p2.getHand().size() == 52)
                    Toast.makeText(getApplicationContext(), "Player 2 Wins!!!!!!!", Toast.LENGTH_LONG).show();
            }
        });


        //Slap onclickListeners
        p1Slap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!playPile.isEmpty() && playPile.size() >= 2) { //checks if the playPile is at least two cards long
                    if (prevCard.getName().equals(playPile.get(playPile.size() - 1).getName()) || ((playPile.size() >= 3) &&
                            prevPrevCard.getName().equals(playPile.get(playPile.size() - 1).getName()))) { //conditional checks for double cards and sandwhiches
                        for (int i = 0; i < playPile.size(); i++) //adds the whole play pile to end if the slap was viable
                            p1.addCard(playPile.get(i));

                           /*
                             These next two lines serve a unique function that get rids of a bug
                             the resetCard is a final object that is set to the two of spades
                             if this is not done, the previous card could potentially be a face card
                             as a result, if the first play of the next turn is not a face card
                             it will automatically be cleared and added the hand of the player before
                             the winner of the slap
                            */
                        prevCard = resetCard;
                        prevPrevCard = resetCard;


                        playPile.clear(); //playPile is then cleared
                        mainCard.setImageResource(backCard); //and the imageView is set to the backCard image
                        for (int i = 0; i < cornerCards.length; i++)
                            cornerCards[i].setImageResource(backCard);
                    } else {

                        if (!p1.getHand().isEmpty()) { //checks if the player's hand is not empty, as to avoid outOfBound exceptions
                            //if the players hand is at least two cards, the bottom two cards are added to the bottom of the play pile
                            if (p1.getHand().size() >= 2) {
                                playPile.add(0, p1.getCard(0));
                                playPile.add(0, p1.getCard(1));
                                p1.getHand().remove(1);
                                p1.getHand().remove(0);
                                Toast.makeText(getApplicationContext(), "Not a viable slap, player 1 loses two cards", Toast.LENGTH_SHORT).show();
                            } else if (p1.getHand().size() == 1) { // if the player only has one card left, it is added to the bottom of the play pile
                                playPile.add(0, p1.getCard(0));
                                p1.getHand().remove(0);
                                Toast.makeText(getApplicationContext(), "Not a viable slap, player 1 loses their last card", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Player 1 has no more cards to lose for their carelessness", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Why would you slap, you numbskull...", Toast.LENGTH_SHORT).show();
                }

                p1Count.setText(""+p1.getHand().size());
                p2Count.setText(""+p2.getHand().size());

            }
        });

        p2Slap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!playPile.isEmpty() && playPile.size() >= 2) {
                    if (prevCard.getName().equals(playPile.get(playPile.size() - 1).getName()) || ((playPile.size() >= 3) &&
                            prevPrevCard.getName().equals(playPile.get(playPile.size() - 1).getName()))) {
                        for (int i = 0; i < playPile.size(); i++) //adds the whole play pile to end
                            p2.addCard(playPile.get(i));
                        prevCard = resetCard;
                        prevPrevCard = resetCard;
                        playPile.clear(); //playPile is then cleared
                        mainCard.setImageResource(backCard); //and the imageView is set to the backCard image
                        for (int i = 0; i < cornerCards.length; i++)
                            cornerCards[i].setImageResource(backCard);
                        Toast.makeText(getApplicationContext(), "Player 2 wins the slap and the cards", Toast.LENGTH_SHORT).show();
                    } else {

                        if (!p2.getHand().isEmpty()) {
                            if (p2.getHand().size() >= 2) {
                                playPile.add(0, p2.getCard(0));
                                playPile.add(0, p2.getCard(1));
                                p2.getHand().remove(1);
                                p2.getHand().remove(0);
                                Toast.makeText(getApplicationContext(), "Not a viable slap, player 2 loses two cards", Toast.LENGTH_SHORT).show();
                            } else if (p2.getHand().size() == 1) {
                                playPile.add(0, p2.getCard(0));
                                p2.getHand().remove(0);
                                Toast.makeText(getApplicationContext(), "Not a viable slap, player 2 loses their last card", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Player 2 has no more cards to lose for their carelessness", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Why would you slap, you numbskull...", Toast.LENGTH_SHORT).show();
                }

                p1Count.setText(""+p1.getHand().size());
                p2Count.setText(""+p2.getHand().size());

            }
        });

    } //end onLoad

} //end class