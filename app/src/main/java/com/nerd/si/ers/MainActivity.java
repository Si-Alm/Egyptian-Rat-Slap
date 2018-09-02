package com.nerd.si.ers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {





    //View widget declarations
    Button p1Play, p1Slap, p2Play, p2Slap, p3Play, p3Slap, p4Play, p4Slap;
    ImageView card, cCard1, cCard2, cCard3, cCard4;
    //Player p, p2, p3, p4;
    Player p1 = new Player();
    Player p2 = new Player();
    Player p3 = new Player();
    Player p4 = new Player();
    Player[] players = {p1, p2, p3, p4};

    ImageView[] cornerCards = new ImageView[4];
    //Card and deck declarations
    Deck deck;
    Card prevCard, prevPrevCard,resetCard; //will track the previous card in playPile
    ArrayList<Card> playPile;
    //arrays for creating a deck
    final String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
    final String[] names = {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Jack", "Queen", "King", "Ace"};



    Toast cardToast;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four_player);

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
        //player objects initialized and placed into an array
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



        //playPile and deck initialized and shuffled
        playPile = new ArrayList<>();
        deck = new Deck(suits, names, paths);
        resetCard = deck.getCard(0);
        deck.shuffle();

        //Block for dealing deck
        int ct = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < (deck.getSize() - 1) / 4; j++) {
                if (ct >= deck.getSize())
                    break;
                players[i].addCard(deck.getCard(ct));
                ct++;
            }
        }
        prevCard = new Card();


        //views initialized to there view ids
        p1Play = findViewById(R.id.p1Play);
        p1Slap = findViewById(R.id.p1Slap);

        p2Play = findViewById(R.id.p2Play);
        p2Slap = findViewById(R.id.p2Slap);

        p3Play = findViewById(R.id.p3Play);
        p3Slap = findViewById(R.id.p3Slap);

        p4Play = findViewById(R.id.p4Play);
        p4Slap = findViewById(R.id.p4Slap);

        card = findViewById(R.id.card);


            //play onclickListeners
            p1Play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try { //try catch, primary function to catch variations of OutOfBounds Exceptions

                        playPile.add(p1.getCard(0)); //adds top card of player's hand to the playPile
                        p1.removeCard(0); //removes that same card from player's hand
                        if (playPile.size() >= 2)
                            prevCard = playPile.get(playPile.size() - 2);
                        if (playPile.size() >= 3)
                            prevPrevCard = playPile.get(playPile.size() - 3);

                        if (prevCard.getIsAFace() && !playPile.get(playPile.size() - 1).getIsAFace()) {
                            imageView.setImageResource(playPile.get(playPile.size() - 1).getPath());
                            cardToast.show();
                            prevCard = playPile.get(playPile.size() - 1);

                            for (int i = 0; i < playPile.size(); i++) //adds the whole play pile to end
                                p4.addCard(playPile.get(i));
                            playPile.clear(); //playPile is then cleared
                            card.setImageResource(backCard); //and the imageView is set to the backCard image
                            for (int i = 0; i < cornerCards.length; i++)
                                cornerCards[i].setImageResource(backCard);

                        }

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Player 1 is out of Cards!", Toast.LENGTH_LONG).show();
                    }


                    if (playPile.isEmpty())
                        card.setImageResource(backCard);
                    else
                        card.setImageResource(playPile.get(playPile.size() - 1).getPath());


                    if (playPile.size() <= 1) {
                        for (int i = 0; i < cornerCards.length; i++)
                            cornerCards[i].setImageResource(backCard);
                    } else {
                        for (int i = 0; i < cornerCards.length; i++)
                            cornerCards[i].setImageResource(playPile.get(playPile.size() - 2).getPath());
                    }
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

                        if (prevCard.getIsAFace() && !playPile.get(playPile.size() - 1).getIsAFace()) {
                            imageView.setImageResource(playPile.get(playPile.size() - 1).getPath());
                            cardToast.show();
                            prevCard = playPile.get(playPile.size() - 1);

                            for (int i = 0; i < playPile.size(); i++) //adds the whole play pile to end
                                p1.addCard(playPile.get(i));
                            playPile.clear(); //playPile is then cleared
                            card.setImageResource(backCard); //and the imageView is set to the backCard image
                            for (int i = 0; i < cornerCards.length; i++)
                                cornerCards[i].setImageResource(backCard);

                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Player 2 is out of Cards!", Toast.LENGTH_LONG).show();
                    }

                    //for setting main card background
                    if (playPile.isEmpty())
                        card.setImageResource(backCard);
                    else
                        card.setImageResource(playPile.get(playPile.size() - 1).getPath());

                    //just for setting backgrounds of corner cards
                    if (playPile.size() <= 1) {
                        for (int i = 0; i < cornerCards.length; i++)
                            cornerCards[i].setImageResource(backCard);
                    } else {
                        for (int i = 0; i < cornerCards.length; i++)
                            cornerCards[i].setImageResource(playPile.get(playPile.size() - 2).getPath());
                    }

                    if(p2.getHand().size() == 52)
                        Toast.makeText(getApplicationContext(), "Player 2 Wins!!!!!!!", Toast.LENGTH_LONG).show();
                }
            });

            p3Play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {
                        playPile.add(p3.getCard(0));
                        p3.removeCard(0);
                        if (playPile.size() >= 2)
                            prevCard = playPile.get(playPile.size() - 2);
                        if (playPile.size() >= 3)
                            prevPrevCard = playPile.get(playPile.size() - 3);

                        if (prevCard.getIsAFace() && !playPile.get(playPile.size() - 1).getIsAFace()) {
                            imageView.setImageResource(playPile.get(playPile.size() - 1).getPath());
                            cardToast.show();
                            prevCard = playPile.get(playPile.size() - 1);

                            for (int i = 0; i < playPile.size(); i++) //adds the whole play pile to end
                                p2.addCard(playPile.get(i));
                            playPile.clear(); //playPile is then cleared
                            card.setImageResource(backCard); //and the imageView is set to the backCard image
                            for (int i = 0; i < cornerCards.length; i++)
                                cornerCards[i].setImageResource(backCard);

                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Player 3 is out of Cards!", Toast.LENGTH_LONG).show();
                    }
                    if (playPile.isEmpty())
                        card.setImageResource(backCard);
                    else
                        card.setImageResource(playPile.get(playPile.size() - 1).getPath());

                    if (playPile.size() <= 1) {
                        for (int i = 0; i < cornerCards.length; i++)
                            cornerCards[i].setImageResource(backCard);
                    } else {
                        for (int i = 0; i < cornerCards.length; i++)
                            cornerCards[i].setImageResource(playPile.get(playPile.size() - 2).getPath());
                    }
                    if(p3.getHand().size() == 52)
                        Toast.makeText(getApplicationContext(), "Player 3 Wins!!!!!!!", Toast.LENGTH_LONG).show();
                }
            });

            p4Play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {
                        playPile.add(p4.getCard(0));
                        p4.removeCard(0);
                        if (playPile.size() >= 2)
                            prevCard = playPile.get(playPile.size() - 2);
                        if (playPile.size() >= 3)
                            prevPrevCard = playPile.get(playPile.size() - 3);

                        if (prevCard.getIsAFace() && !playPile.get(playPile.size() - 1).getIsAFace()) {
                            imageView.setImageResource(playPile.get(playPile.size() - 1).getPath());
                            cardToast.show();
                            prevCard = playPile.get(playPile.size() - 1);

                            for (int i = 0; i < playPile.size(); i++) //adds the whole play pile to end
                                p3.addCard(playPile.get(i));
                            playPile.clear(); //playPile is then cleared
                            card.setImageResource(backCard); //and the imageView is set to the backCard image
                            for (int i = 0; i < cornerCards.length; i++)
                                cornerCards[i].setImageResource(backCard);

                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Player 4 is out of Cards!", Toast.LENGTH_LONG).show();
                    }
                    if (playPile.isEmpty())
                        card.setImageResource(backCard);
                    else
                        card.setImageResource(playPile.get(playPile.size() - 1).getPath());

                    if (playPile.size() <= 1) {
                        for (int i = 0; i < cornerCards.length; i++)
                            cornerCards[i].setImageResource(backCard);
                    } else {
                        for (int i = 0; i < cornerCards.length; i++)
                            cornerCards[i].setImageResource(playPile.get(playPile.size() - 2).getPath());
                    }

                    if(p4.getHand().size() == 52)
                        Toast.makeText(getApplicationContext(), "Player 4 Wins!!!!!!!", Toast.LENGTH_LONG).show();
                }
            });


            //Slap onclickListeners
            //TODO: Make functional to game rules: if slap does not meet parameters, remove two cards from slapper's hand
            p1Slap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!playPile.isEmpty() && playPile.size() >= 2) {
                        if (prevCard.getName().equals(playPile.get(playPile.size() - 1).getName()) || ((playPile.size() >= 3) &&
                                prevPrevCard.getName().equals(playPile.get(playPile.size() - 1).getName()))) {
                            for (int i = 0; i < playPile.size(); i++) //adds the whole play pile to end
                                p1.addCard(playPile.get(i));
                            prevCard = resetCard;
                            prevPrevCard = resetCard;
                            playPile.clear(); //playPile is then cleared
                            card.setImageResource(backCard); //and the imageView is set to the backCard image
                            for (int i = 0; i < cornerCards.length; i++)
                                cornerCards[i].setImageResource(backCard);
                        } else {

                            if (!p1.getHand().isEmpty()) {
                                if (p1.getHand().size() >= 2) {
                                    playPile.add(0, p1.getCard(0));
                                    playPile.add(0, p1.getCard(1));
                                    p1.getHand().remove(1);
                                    p1.getHand().remove(0);
                                    Toast.makeText(getApplicationContext(), "Not a viable slap, player 1 loses two cards", Toast.LENGTH_SHORT).show();
                                } else if (p1.getHand().size() == 1) {
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
                    if(p1.getHand().size() == 52)
                        Toast.makeText(getApplicationContext(), "Player 1 Wins!!!!!!!", Toast.LENGTH_LONG).show();
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
                            card.setImageResource(backCard); //and the imageView is set to the backCard image
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

                }
            });

            p3Slap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!playPile.isEmpty() && playPile.size() >= 2) {
                        if (prevCard.getName().equals(playPile.get(playPile.size() - 1).getName()) || ((playPile.size() >= 3) &&
                                prevPrevCard.getName().equals(playPile.get(playPile.size() - 1).getName()))) {
                            for (int i = 0; i < playPile.size(); i++) //adds the whole play pile to end
                                p3.addCard(playPile.get(i));
                            prevCard = resetCard;
                            prevPrevCard = resetCard;
                            playPile.clear(); //playPile is then cleared
                            card.setImageResource(backCard); //and the imageView is set to the backCard image
                            for (int i = 0; i < cornerCards.length; i++)
                                cornerCards[i].setImageResource(backCard);
                            Toast.makeText(getApplicationContext(), "Player 3 wins the slap and the cards", Toast.LENGTH_SHORT).show();
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

                    if(p3.getHand().size() == 52)
                        Toast.makeText(getApplicationContext(), "Player 3 Wins!!!!!!!", Toast.LENGTH_LONG).show();
                }
            });

            p4Slap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!playPile.isEmpty() && playPile.size() >= 2 && prevPrevCard!=null) {
                        if (prevCard.getName().equals(playPile.get(playPile.size() - 1).getName()) || ((playPile.size() >= 3) &&
                                prevPrevCard.getName().equals(playPile.get(playPile.size() - 1).getName()))) {
                            for (int i = 0; i < playPile.size(); i++) //adds the whole play pile to end
                                p4.addCard(playPile.get(i));
                            prevCard = resetCard;
                            prevPrevCard = resetCard;
                            playPile.clear(); //playPile is then cleared
                            card.setImageResource(backCard); //and the imageView is set to the backCard image
                            for (int i = 0; i < cornerCards.length; i++)
                                cornerCards[i].setImageResource(backCard);
                            Toast.makeText(getApplicationContext(), "Player 4 wins the slap and the cards", Toast.LENGTH_SHORT).show();
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

                    if(p4.getHand().size() == 52)
                        Toast.makeText(getApplicationContext(), "Player 4 Wins!!!!!!!", Toast.LENGTH_LONG).show();
                }
            });

    }

}