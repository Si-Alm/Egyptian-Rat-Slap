/**
 * This is the main, four-player activity for an android-mobile version of the card game: Egyptian-Rat Slap
 * Due to the repetitive nature of the game logic, this activity will be the most heavily documented activity
 * This documentation will largely be used as reference for the two and three player activities
 * All code is written and designed by me, Silas Almgren
 **/


package com.nerd.si.ers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /*
      Widget view declarations(player slap and play buttons, button array, corner card image views and their array
      As well as the cardToast and its imageView
     */
    Button p1Play, p1Slap, p2Play, p2Slap, p3Play, p3Slap, p4Play, p4Slap;
    Button[] playButtons = new Button[4];
    ImageView card, cCard1, cCard2, cCard3, cCard4;
    ImageView[] cornerCards = new ImageView[4];
    Toast cardToast;
    ImageView imageView;



    /*
      All player objects
      This includes the four players, current player tracking object, and the player array
     */
    Player p1 = new Player();
    Player p2 = new Player();
    Player p3 = new Player();
    Player p4 = new Player();
    Player currentPlayer = new Player();
    Player[] players = {p1, p2, p3, p4};


    //Card and deck declarations
    /*
     Card and deck declarations
     This includes the deck, previous and previous-previous card, reset card, and the suit and names arrays
     */
    Deck deck;
    Card prevCard, prevPrevCard,resetCard;
    ArrayList<Card> playPile;
    final String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
    final String[] names = {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Jack", "Queen", "King", "Ace"};


    //Tracking variables used for face card logic
    int chanceCount;
    boolean isAFaceTurn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four_player);

        //Back card resource int
        final int backCard = getResources().getIdentifier("com.nerd.si.ers:drawable/back1", null, null);
        //Card image resource array
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

        //Card images are set to their imageViews and the corner cards are placed into the cornerCard array
        card = findViewById(R.id.card);
        cCard1 = findViewById(R.id.cornerCard1);
        cCard2 = findViewById(R.id.cornerCard2);
        cCard3 = findViewById(R.id.cornerCard3);
        cCard4 = findViewById(R.id.cornerCard4);
        cornerCards[0] = cCard1;
        cornerCards[1] = cCard2;
        cornerCards[2] = cCard3;
        cornerCards[3] = cCard4;


        //Creates toasts with card image
        cardToast = new Toast(getApplicationContext());
        imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(backCard);
        cardToast.setView(imageView);


        /*
          playPile and deck are initialized and the deck is shuffled
         */
        playPile = new ArrayList<>();
        deck = new Deck(suits, names, paths);
        resetCard = deck.getCard(0);
        deck.shuffle();

        //previous card trackers are initialized
        prevCard = new Card();
        prevPrevCard = new Card();


        //tracking variables are set to default values (0, false, and playerOne)
        chanceCount = 0;
        isAFaceTurn = false;
        currentPlayer = p1;



        /*
         Block for dealing the deck evenly to the four players
         */
        int ct = 0; // counting variable

        while(deck.getSize()!=0) { //while the deck is not empty
            if(ct >=4) //resets to start next round of dealing
                ct=0;
            players[ct].addCard(deck.getCard(deck.getSize()-1)); //adds card to appropriate player
            deck.removeCard(deck.getSize()-1); //removes dealt card from deck
            ct++; //counter incremented
        }


        /*
         All of the buttons are initialized to their corresponding views
         the play buttons are then placed into the button array
         */
        p1Play = findViewById(R.id.p1Play);
        p1Slap = findViewById(R.id.p1Slap);

        p2Play = findViewById(R.id.p2Play);
        p2Slap = findViewById(R.id.p2Slap);

        p3Play = findViewById(R.id.p3Play);
        p3Slap = findViewById(R.id.p3Slap);

        p4Play = findViewById(R.id.p4Play);
        p4Slap = findViewById(R.id.p4Slap);

        playButtons[0] = p1Play;
        playButtons[1] = p2Play;
        playButtons[2] = p3Play;
        playButtons[3] = p4Play;

        //play buttons text is set
        setPlaybuttons();



            /*
              Begin the play onClickListeners

              Since these listeners will include all of the same logic(for the most part) only the first one
              will be documented, for reference come back to the p1Play listener and read its documentation
             */
            p1Play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    /*
                     This first if-block is used for handling the current player variable
                     if the current player's hand is empty
                     */
                    if(currentPlayer.equals(p1) && p1.getHand().isEmpty()) {
                        if(!p2.getHand().isEmpty()) { //if next player isn't empty, they are set as the current player
                            currentPlayer = p2;
                        } else if(!p3.getHand().isEmpty()) { //if previous two players are empty, they are set as the current player
                            currentPlayer = p3;
                        } else if(!p4.getHand().isEmpty()) { //if previous three players are empty, they are set as the current player
                            currentPlayer = p4;
                        }
                    }

                    /*
                     This small block is used for setting the current player variable
                     if a previous player runs out of cards in the middle of a face turn
                     */
                    if(isAFaceTurn) {
                        /*
                          If player 3 plays a face card, but player 4 is unable to complete the required amount of plays
                          the current player will be set to player 1
                          Similarly, if player 2 plays a face card, and player 3 is unable to complete, player becomes the current player
                         */
                        if(currentPlayer.equals(p4) && p4.getHand().isEmpty())
                            currentPlayer = p1;
                        else if(currentPlayer.equals(p3) && p3.getHand().isEmpty())
                            currentPlayer = p1;
                    }

                    /*
                     First if button will only make the play button functional
                     if it is player 1's turn
                     */
                    if (currentPlayer.equals(p1)) {
                        try { //try catch, primary function to catch variations of OutOfBounds Exceptions

                            /*
                             First 2 lines remove a card from the player's hand and adds it to the play pile
                             */
                            playPile.add(p1.getCard(0));
                            p1.removeCard(0);

                            /*
                             These two if statements set the previous and previous previous cards, provided
                             that the play pile is large enough to do so with out an OutOfBounds Exception
                             */
                            if (playPile.size() >= 2) //if the play pile is greater than one, the previous card is set to size-2
                                prevCard = playPile.get(playPile.size() - 2);
                            if (playPile.size() >= 3) //if the play pile is greater than two, the previous previous card is set to size-3
                                prevPrevCard = playPile.get(playPile.size() - 3);


                        /*
                          First basic method for face card rule
                          It checks if the previous played card was a face card and if the next played card isn't a face
                          the block is ran
                         */

                            if (prevCard.getIsAFace() || isAFaceTurn) {
                                //face turn is always set to true given the previous condition
                                isAFaceTurn = true;

                                /*
                                 If the previous card was a face card(i.e. it's the beginning of a new face turn
                                 the chance count is set based on the previous card
                                 */
                                if (prevCard.getIsAFace())
                                    chanceCount = prevCard.getRequiredPlays();


                                prevCard = playPile.get(playPile.size() - 1); //the previous card is then updated


                                /*
                                 This large if statement checks if the previous card(i.e. the one(s) layed on top
                                 of the face card that initiated the face turn) was not a face card
                                 If it wasn't, the chance count is de-incremented
                                 */
                                if (!playPile.get(playPile.size() - 1).getIsAFace()) {
                                    chanceCount--;

                                    /*
                                     This inner if checks if the chance count is zero
                                     given that the previous condition makes sure that the previous card was NOT a face
                                     the play pile than can be distributed to the appropriate player
                                     */
                                    if (chanceCount == 0) {
                                        if (!p4.getHand().isEmpty()) {
                                            for (int i = 0; i < playPile.size(); i++) //adds the whole play pile to the previous player's hand
                                                p4.addCard(playPile.get(i));
                                            currentPlayer = p4;
                                        } else if (!p3.getHand().isEmpty()) {
                                            for (int i = 0; i < playPile.size(); i++) //adds the whole play pile to the previous player's hand
                                                p3.addCard(playPile.get(i));
                                            currentPlayer = p3;
                                        } else if (!p2.getHand().isEmpty()) {
                                            for (int i = 0; i < playPile.size(); i++) //adds the whole play pile to the previous player's hand
                                                p2.addCard(playPile.get(i));
                                            currentPlayer = p2;
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Player 1 is the WINNER!", Toast.LENGTH_LONG).show();
                                        }


                                         /*
                                         Since the final card laid down was not shown on the pile
                                         it is shown as a toast, so the players know which card was played
                                         */
                                        imageView.setImageResource(playPile.get(playPile.size() - 1).getPath()); //sets the image view for the card toast to the non-face card that was played
                                        cardToast.show(); //shows the card that was played as a toast

                                        /*
                                         Since the play pile was emptied the play pile is cleared and the face turn boolean is set to false
                                         */
                                        isAFaceTurn = false;
                                        playPile.clear(); //playPile is then cleared




                                    }
                                } else if (playPile.get(playPile.size() - 1).getIsAFace()) { //if the player lays down a face card
                                                                                             //within the allotted chances, the face card
                                                                                             //trackers are reset
                                    chanceCount = 0;
                                    isAFaceTurn = false;
                                } else {
                                    //with the logic, this statement should NEVER run
                                    Toast.makeText(getApplicationContext(), "WHOOPS!!", Toast.LENGTH_LONG).show();
                                }


                            }

                            /*
                             This block is used to set the current player in normal circumstances(i.e. isn't a face turn, a number card was played, etc.)
                             It checks if the current player is equal to the play button owner AND (if the play pile isn't empty OR
                                                                                                    if it is an uncompleted face turn)
                             */
                            if(currentPlayer.equals(p1) && (!playPile.isEmpty() || !prevCard.getIsAFace()) && !isAFaceTurn) {
                                if (!p2.getHand().isEmpty()) {
                                    currentPlayer = p2;
                                } else if (!p3.getHand().isEmpty()) {
                                    currentPlayer = p3;
                                } else if (!p4.getHand().isEmpty()) {
                                    currentPlayer = p4;
                                }
                            }


                        } catch (Exception e) { //catch block used primarily for out of bounds exception(which indicate that a player's hand is empty)
                            Toast.makeText(getApplicationContext(), "Player 1 is out of Cards!", Toast.LENGTH_LONG).show();
                        }



                        /*
                         This block is used for setting the card imageViews
                         If the play pile was emptied(due to face card turn being completed) the images are set to the backCard file
                         Otherwise, the main card is set to the previous card and the corner cards are set to the previous previous card
                         */
                        if (playPile.isEmpty()) {
                            card.setImageResource(backCard);
                            for (int i = 0; i < cornerCards.length; i++)
                                cornerCards[i].setImageResource(backCard);
                        } else {
                            card.setImageResource(playPile.get(playPile.size() - 1).getPath());
                            if (playPile.size() >= 2) {
                                for (int i = 0; i < cornerCards.length; i++)
                                    cornerCards[i].setImageResource(playPile.get(playPile.size() - 2).getPath());
                            }
                        }


                        setPlaybuttons(); //the text for all play buttons is set(i.e. the card counter is updated)

                        //If all other hands are empty, it is presumed this player is the winner
                        if(p2.getHand().isEmpty() && p3.getHand().isEmpty() && p4.getHand().isEmpty())
                            Toast.makeText(getApplicationContext(), "Player 1 Wins!!!!!!!", Toast.LENGTH_LONG).show();


                    } else {
                        //If player attempts to use their play button outside of their turn, this toast is shown
                        //unless the player's hand is empty, then the message stating so is shown
                        if(p1.getHand().isEmpty())
                            Toast.makeText(getApplicationContext(), "Player 1 is out of Cards!", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getApplicationContext(), "It is not your turn", Toast.LENGTH_LONG).show();
                    }
                }

            });

            p2Play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(currentPlayer.equals(p2) && p2.getHand().isEmpty()) {
                        if(!p3.getHand().isEmpty()) {
                            currentPlayer = p3;
                        } else if(!p4.getHand().isEmpty()) {
                            currentPlayer = p4;
                        } else if(!p1.getHand().isEmpty()) {
                            currentPlayer = p1;
                        }
                    }

                    if(isAFaceTurn) {
                        if(currentPlayer.equals(p1) && p1.getHand().isEmpty() )
                            currentPlayer = p2;
                        else if(currentPlayer.equals(p4) && p4.getHand().isEmpty())
                            currentPlayer = p2;
                    }

                    if (currentPlayer.equals(p2)) {
                        try {

                            playPile.add(p2.getCard(0));
                            p2.removeCard(0);
                            if (playPile.size() >= 2)
                                prevCard = playPile.get(playPile.size() - 2);
                            if (playPile.size() >= 3)
                                prevPrevCard = playPile.get(playPile.size() - 3);

                            if (prevCard.getIsAFace() || isAFaceTurn) {
                                isAFaceTurn = true;
                                if (prevCard.getIsAFace())
                                    chanceCount = prevCard.getRequiredPlays();

                                prevCard = playPile.get(playPile.size() - 1); //sets the previous card

                                if (!playPile.get(playPile.size() - 1).getIsAFace()) {
                                    chanceCount--;
                                    if (chanceCount == 0) {
                                        if (!p1.getHand().isEmpty()) {
                                            for (int i = 0; i < playPile.size(); i++) //adds the whole play pile to the previous player's hand
                                                p1.addCard(playPile.get(i));
                                            currentPlayer = p1;
                                        } else if (!p4.getHand().isEmpty()) {
                                            for (int i = 0; i < playPile.size(); i++) //adds the whole play pile to the previous player's hand
                                                p4.addCard(playPile.get(i));
                                            currentPlayer = p4;
                                        } else if (!p3.getHand().isEmpty()) {
                                            for (int i = 0; i < playPile.size(); i++) //adds the whole play pile to the previous player's hand
                                                p3.addCard(playPile.get(i));
                                            currentPlayer = p3;
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Player 2 is the WINNER!", Toast.LENGTH_LONG).show();
                                        }
                                        isAFaceTurn = false;

                                        imageView.setImageResource(playPile.get(playPile.size() - 1).getPath()); //sets the image view for the card toast to the non-face card that was played
                                        cardToast.show(); //shows the card that was played as a toast


                                        playPile.clear(); //playPile is then cleared


                                    }
                                } else if (playPile.get(playPile.size() - 1).getIsAFace()) {
                                    chanceCount = 0;
                                    isAFaceTurn = false;
                                } else {
                                    Toast.makeText(getApplicationContext(), "WHOOPS!!", Toast.LENGTH_LONG).show();
                                }

                            }

                            if(!playPile.isEmpty() && currentPlayer.equals(p2) && !isAFaceTurn) {
                                if (!p3.getHand().isEmpty()) {
                                    currentPlayer = p3;
                                } else if (!p4.getHand().isEmpty()) {
                                    currentPlayer = p4;
                                } else if (!p1.getHand().isEmpty()) {
                                    currentPlayer = p1;
                                }
                            }
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Player 2 is out of Cards!", Toast.LENGTH_LONG).show();
                        }


                        if (playPile.isEmpty()) { // if the play pile was emptied(due to face card rule) the card images are set to the back card image
                            card.setImageResource(backCard);
                            for (int i = 0; i < cornerCards.length; i++)
                                cornerCards[i].setImageResource(backCard);
                        } else { //if pile wasn't emptied, the card images are set to the played card
                            card.setImageResource(playPile.get(playPile.size() - 1).getPath());
                            if (playPile.size() >= 2) {
                                for (int i = 0; i < cornerCards.length; i++)
                                    cornerCards[i].setImageResource(playPile.get(playPile.size() - 2).getPath());
                            }
                        }

                        setPlaybuttons();


                        if(p1.getHand().isEmpty() && p3.getHand().isEmpty() && p4.getHand().isEmpty())
                            Toast.makeText(getApplicationContext(), "Player 2 Wins!!!!!!!", Toast.LENGTH_LONG).show();

                    } else {
                        if(p2.getHand().isEmpty())
                            Toast.makeText(getApplicationContext(), "Player 2 is out of Cards!", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getApplicationContext(), "It is not your turn!", Toast.LENGTH_LONG).show();
                    }
                }
            });

            p3Play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(currentPlayer.equals(p3) && p3.getHand().isEmpty()) {
                        if(!p4.getHand().isEmpty()) {
                            currentPlayer = p4;
                        } else if(!p1.getHand().isEmpty()) {
                            currentPlayer = p1;
                        } else if(!p2.getHand().isEmpty()) {
                            currentPlayer = p2;
                        }
                    }

                    if(isAFaceTurn) {
                        if(currentPlayer.equals(p2) && p2.getHand().isEmpty() )
                            currentPlayer = p3;
                        else if(currentPlayer.equals(p1) && p1.getHand().isEmpty())
                            currentPlayer = p3;
                    }

                    if (currentPlayer.equals(p3)) {
                        try {

                            playPile.add(p3.getCard(0));
                            p3.removeCard(0);
                            if (playPile.size() >= 2)
                                prevCard = playPile.get(playPile.size() - 2);
                            if (playPile.size() >= 3)
                                prevPrevCard = playPile.get(playPile.size() - 3);

                            if (prevCard.getIsAFace() || isAFaceTurn) {
                                isAFaceTurn = true;
                                if (prevCard.getIsAFace())
                                    chanceCount = prevCard.getRequiredPlays();

                                prevCard = playPile.get(playPile.size() - 1); //sets the previous card

                                if (!playPile.get(playPile.size() - 1).getIsAFace()) {
                                    chanceCount--;
                                    if (chanceCount == 0) {
                                        if (!p2.getHand().isEmpty()) {
                                            for (int i = 0; i < playPile.size(); i++) //adds the whole play pile to the previous player's hand
                                                p2.addCard(playPile.get(i));
                                            currentPlayer = p2;
                                        } else if (!p1.getHand().isEmpty()) {
                                            for (int i = 0; i < playPile.size(); i++) //adds the whole play pile to the previous player's hand
                                                p1.addCard(playPile.get(i));
                                            currentPlayer = p1;
                                        } else if (!p4.getHand().isEmpty()) {
                                            for (int i = 0; i < playPile.size(); i++) //adds the whole play pile to the previous player's hand
                                                p4.addCard(playPile.get(i));
                                            currentPlayer = p4;
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Player 3 is the WINNER!", Toast.LENGTH_LONG).show();
                                        }
                                        isAFaceTurn = false;

                                        imageView.setImageResource(playPile.get(playPile.size() - 1).getPath()); //sets the image view for the card toast to the non-face card that was played
                                        cardToast.show(); //shows the card that was played as a toast

                                        playPile.clear(); //playPile is then cleared

                                    }
                                } else if (playPile.get(playPile.size() - 1).getIsAFace()) {
                                    chanceCount = 0;
                                    isAFaceTurn = false;
                                } else {
                                    Toast.makeText(getApplicationContext(), "WHOOPS!!", Toast.LENGTH_LONG).show();
                                }


                            }

                            if(!playPile.isEmpty() && currentPlayer.equals(p3) && !isAFaceTurn) {
                                if (!p4.getHand().isEmpty()) {
                                    currentPlayer = p4;
                                } else if (!p1.getHand().isEmpty()) {
                                    currentPlayer = p1;
                                } else if (!p2.getHand().isEmpty()) {
                                    currentPlayer = p2;
                                }
                            }

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Player 3 is out of Cards!", Toast.LENGTH_LONG).show();
                        }

                        if (playPile.isEmpty()) { // if the play pile was emptied(due to face card rule) the card images are set to the back card image
                            card.setImageResource(backCard);
                            for (int i = 0; i < cornerCards.length; i++)
                                cornerCards[i].setImageResource(backCard);
                        } else { //if pile wasn't emptied, the card images are set to the played card
                            card.setImageResource(playPile.get(playPile.size() - 1).getPath());
                            if (playPile.size() >= 2) {
                                for (int i = 0; i < cornerCards.length; i++)
                                    cornerCards[i].setImageResource(playPile.get(playPile.size() - 2).getPath());
                            }
                        }

                        setPlaybuttons();

                        if(p2.getHand().isEmpty() && p1.getHand().isEmpty() && p4.getHand().isEmpty())
                            Toast.makeText(getApplicationContext(), "Player 3 Wins!!!!!!!", Toast.LENGTH_LONG).show();
                    } else {
                        if(p3.getHand().isEmpty())
                            Toast.makeText(getApplicationContext(), "Player 3 is out of Cards!", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getApplicationContext(), "It is not your turn!", Toast.LENGTH_LONG).show();
                    }
                }
            });

            p4Play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(currentPlayer.equals(p4) && p4.getHand().isEmpty()) {
                        if(!p1.getHand().isEmpty()) {
                            currentPlayer = p1;
                        } else if(!p2.getHand().isEmpty()) {
                            currentPlayer = p2;
                        } else if(!p3.getHand().isEmpty()) {
                            currentPlayer = p3;
                        }
                    }

                    if(isAFaceTurn) {
                        if(currentPlayer.equals(p3) && p3.getHand().isEmpty() )
                            currentPlayer = p4;
                        else if(currentPlayer.equals(p2) && p2.getHand().isEmpty())
                            currentPlayer = p4;
                    }

                    if (currentPlayer.equals(p4)) {
                        try {

                            playPile.add(p4.getCard(0));
                            p4.removeCard(0);
                            if (playPile.size() >= 2)
                                prevCard = playPile.get(playPile.size() - 2);
                            if (playPile.size() >= 3)
                                prevPrevCard = playPile.get(playPile.size() - 3);

                            if (prevCard.getIsAFace() || isAFaceTurn) {
                                isAFaceTurn = true;
                                if (prevCard.getIsAFace())
                                    chanceCount = prevCard.getRequiredPlays();

                                prevCard = playPile.get(playPile.size() - 1); //sets the previous card

                                if (!playPile.get(playPile.size() - 1).getIsAFace()) {
                                    chanceCount--;
                                    if (chanceCount == 0) {
                                        if (!p3.getHand().isEmpty()) {
                                            for (int i = 0; i < playPile.size(); i++) //adds the whole play pile to the previous player's hand
                                                p3.addCard(playPile.get(i));
                                            currentPlayer = p3;
                                        } else if (!p2.getHand().isEmpty()) {
                                            for (int i = 0; i < playPile.size(); i++) //adds the whole play pile to the previous player's hand
                                                p2.addCard(playPile.get(i));
                                            currentPlayer = p2;
                                        } else if (!p1.getHand().isEmpty()) {
                                            for (int i = 0; i < playPile.size(); i++) //adds the whole play pile to the previous player's hand
                                                p1.addCard(playPile.get(i));
                                            currentPlayer = p1;
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Player 4 is the WINNER!", Toast.LENGTH_LONG).show();
                                        }
                                        isAFaceTurn = false;

                                        imageView.setImageResource(playPile.get(playPile.size() - 1).getPath()); //sets the image view for the card toast to the non-face card that was played
                                        cardToast.show(); //shows the card that was played as a toast

                                        playPile.clear(); //playPile is then cleared

                                    }
                                } else if (playPile.get(playPile.size() - 1).getIsAFace()) {
                                    chanceCount = 0;
                                    isAFaceTurn = false;
                                } else {
                                    Toast.makeText(getApplicationContext(), "WHOOPS!!", Toast.LENGTH_LONG).show();
                                }


                            }

                            if(!playPile.isEmpty() && currentPlayer.equals(p4) && !isAFaceTurn) {
                                if (!p1.getHand().isEmpty()) {
                                    currentPlayer = p1;
                                } else if (!p2.getHand().isEmpty()) {
                                    currentPlayer = p2;
                                } else if (!p3.getHand().isEmpty()) {
                                    currentPlayer = p3;
                                }
                            }
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Player 4 is out of Cards!", Toast.LENGTH_LONG).show();
                        }

                        if (playPile.isEmpty()) { // if the play pile was emptied(due to face card rule) the card images are set to the back card image
                            card.setImageResource(backCard);
                            for (int i = 0; i < cornerCards.length; i++)
                                cornerCards[i].setImageResource(backCard);
                        } else { //if pile wasn't emptied, the card images are set to the played card
                            card.setImageResource(playPile.get(playPile.size() - 1).getPath());
                            if (playPile.size() >= 2) {
                                for (int i = 0; i < cornerCards.length; i++)
                                    cornerCards[i].setImageResource(playPile.get(playPile.size() - 2).getPath());
                            }
                        }

                        setPlaybuttons();


                        if(p2.getHand().isEmpty() && p3.getHand().isEmpty() && p1.getHand().isEmpty())
                            Toast.makeText(getApplicationContext(), "Player 4 Wins!!!!!!!", Toast.LENGTH_LONG).show();

                    } else {
                        if(p4.getHand().isEmpty())
                            Toast.makeText(getApplicationContext(), "Player 4 is out of Cards!", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getApplicationContext(), "It is not your turn!", Toast.LENGTH_LONG).show();
                    }
                }
            });
            /*
              End the play button listeners
             */



            /*
             Begin slap button listeners

             Similar to the play buttons, all of these listeners will follow the same logic, as such
             only the first one will be documented and will be used as reference for the rest
             */
            p1Slap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*
                     This primary if statement checks if the play pile is at least two cards tall as a slap would not be possible
                     Additionally, this is used to prevent OutOfBounds Exceptions
                     */
                    if (playPile.size() >= 2) {
                        /*
                         This second monstrosity of an if, is used to check if the slap was viable
                         If either the top two cards were the same or if the top card and the second card down were the same,
                         it was a viable slap.
                         Otherwise, it was not and the player will lose two cards
                         */
                        if ((playPile.get(playPile.size()-1).getName().equals(playPile.get(playPile.size()-2).getName())) ||
                                (playPile.size() >= 3 && playPile.get(playPile.size()-1).getName().equals(playPile.get(playPile.size()-3).getName()))) { //conditional checks for double cards and sandwhiches


                            //This loop will add the play pile to the slappers hand since it was a viable slap
                            for (int i = 0; i < playPile.size(); i++)
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

                            /*
                             The next few lines are used for resetting the field, the play pile is cleared, the card images are set,
                             the face trackers are reset(allowing for slaps on face cards), and a toast declaring that the player
                             has won the slap is shown
                             */
                            playPile.clear(); //playPile is then cleared
                            card.setImageResource(backCard); //and the imageView is set to the backCard image
                            for (int i = 0; i < cornerCards.length; i++)
                                cornerCards[i].setImageResource(backCard);

                            Toast.makeText(getApplicationContext(), "Player 1 wins the slap and the cards", Toast.LENGTH_LONG).show();

                            isAFaceTurn = false;
                            currentPlayer = p1;

                        } else { //the else that is ran if the slap was not viable

                            /*
                             This large if/else if/else block checks for how many cards to remove from the player's hand
                             If they have at least two cards, to cards are removed and added to the play pile
                             If they only have one, it is added to the play pile
                             If they have none, a toast is shown stating this fact
                             */
                            if (!p1.getHand().isEmpty()) {
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
                        //This toast is shown if the player slaps on an empty(or nearly empty) play pile
                        //This means the player will not be penalized for slapping in this instance
                        Toast.makeText(getApplicationContext(), "Why would you slap, you numbskull...", Toast.LENGTH_SHORT).show();
                    }

                    //the play buttons text is set
                    setPlaybuttons();

                }
            });

            p2Slap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (playPile.size() >= 2) {
                        /*if (prevCard.getName().equals(playPile.get(playPile.size() - 1).getName()) || ((playPile.size() >= 3) &&
                                prevPrevCard.getName().equals(playPile.get(playPile.size() - 1).getName()))) { */
                        if((playPile.get(playPile.size()-1).getName().equals(playPile.get(playPile.size()-2).getName())) ||
                                (playPile.size() >= 3 && playPile.get(playPile.size()-1).getName().equals(playPile.get(playPile.size()-3).getName()))){
                            for (int i = 0; i < playPile.size(); i++) //adds the whole play pile to end
                                p2.addCard(playPile.get(i));
                            prevCard = resetCard;
                            prevPrevCard = resetCard;
                            playPile.clear(); //playPile is then cleared
                            card.setImageResource(backCard); //and the imageView is set to the backCard image
                            for (int i = 0; i < cornerCards.length; i++)
                                cornerCards[i].setImageResource(backCard);
                            Toast.makeText(getApplicationContext(), "Player 2 wins the slap and the cards", Toast.LENGTH_SHORT).show();
                            currentPlayer = p2;
                            isAFaceTurn = false;
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

                    setPlaybuttons();

                }
            });

            p3Slap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (playPile.size() >= 2) {
                        if ((playPile.get(playPile.size()-1).getName().equals(playPile.get(playPile.size()-2).getName())) ||
                                (playPile.size() >= 3 && playPile.get(playPile.size()-1).getName().equals(playPile.get(playPile.size()-3).getName()))) {
                            for (int i = 0; i < playPile.size(); i++) //adds the whole play pile to end
                                p3.addCard(playPile.get(i));
                            prevCard = resetCard;
                            prevPrevCard = resetCard;
                            playPile.clear(); //playPile is then cleared
                            card.setImageResource(backCard); //and the imageView is set to the backCard image
                            for (int i = 0; i < cornerCards.length; i++)
                                cornerCards[i].setImageResource(backCard);
                            Toast.makeText(getApplicationContext(), "Player 3 wins the slap and the cards", Toast.LENGTH_SHORT).show();

                            currentPlayer = p3;
                            isAFaceTurn = false;
                        } else {

                            if (!p3.getHand().isEmpty()) {
                                if (p3.getHand().size() >= 2) {
                                    playPile.add(0, p3.getCard(0));
                                    playPile.add(0, p3.getCard(1));
                                    p3.getHand().remove(1);
                                    p3.getHand().remove(0);
                                    Toast.makeText(getApplicationContext(), "Not a viable slap, player 3 loses two cards", Toast.LENGTH_SHORT).show();
                                } else if (p3.getHand().size() == 1) {
                                    playPile.add(0, p3.getCard(0));
                                    p3.getHand().remove(0);
                                    Toast.makeText(getApplicationContext(), "Not a viable slap, player 3 loses their last card", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Player 3 has no more cards to lose for their carelessness", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Why would you slap, you numbskull...", Toast.LENGTH_SHORT).show();
                    }

                    setPlaybuttons();
                }
            });

            p4Slap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (playPile.size() >= 2) {
                        if ((playPile.get(playPile.size()-1).getName().equals(playPile.get(playPile.size()-2).getName())) ||
                                (playPile.size() >= 3 && playPile.get(playPile.size()-1).getName().equals(playPile.get(playPile.size()-3).getName()))) {
                            for (int i = 0; i < playPile.size(); i++) //adds the whole play pile to end
                                p4.addCard(playPile.get(i));
                            prevCard = resetCard;
                            prevPrevCard = resetCard;
                            playPile.clear(); //playPile is then cleared
                            card.setImageResource(backCard); //and the imageView is set to the backCard image
                            for (int i = 0; i < cornerCards.length; i++)
                                cornerCards[i].setImageResource(backCard);
                            Toast.makeText(getApplicationContext(), "Player 4 wins the slap and the cards", Toast.LENGTH_SHORT).show();

                            currentPlayer = p4;
                            isAFaceTurn = false;
                        } else {

                            if (!p4.getHand().isEmpty()) {
                                if (p4.getHand().size() >= 2) {
                                    playPile.add(0, p4.getCard(0));
                                    playPile.add(0, p4.getCard(1));
                                    p4.getHand().remove(1);
                                    p4.getHand().remove(0);
                                    Toast.makeText(getApplicationContext(), "Not a viable slap, player 4 loses two cards", Toast.LENGTH_SHORT).show();
                                } else if (p4.getHand().size() == 1) {
                                    playPile.add(0, p4.getCard(0));
                                    p4.getHand().remove(0);
                                    Toast.makeText(getApplicationContext(), "Not a viable slap, player 4 loses their last card", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Player 4 has no more cards to lose for their carelessness", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Why would you slap, you numbskull...", Toast.LENGTH_SHORT).show();
                    }

                    setPlaybuttons();

                }
            });

    }//end onLoad

    //since this loop is used at the end of every listener, it gets its own method
    public void setPlaybuttons() {
        //loops through the play buttons and updates the hand card counter
        for(int i=0; i<playButtons.length; i++)
            playButtons[i].setText("Play\n(" + players[i].getHand().size() + ")");
    }
} //end activity class