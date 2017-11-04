package edu.ucsb.cs56.projects.utilities.flashcards;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * Deck class for organizing FlashCards by subject
 * with methods for shuffling, drawing, and putting back the cards.
 *
 * @author Chad Simmons, Shany Yeshanov
 * @version CS56 W12
 */
public class Deck  implements Serializable {
    static private final long serialVersionUID = 0xdec;
    private ArrayList<FlashCard> cards;
    private String name;

    /**
     Default constructor to make a deck with no name
     */
    public Deck() {
        this("");
    }

    /**
     Constructor to create a new empty deck with a given name.
     @param name The name of the deck.
     */
    public Deck(String name){
        this.cards = new ArrayList<FlashCard>();
        this.name = name;
    }

    /**
     Getter for the name attribute.
     */
    public String getName() {
        return this.name;
    }

    /**
     Setter for the name attribute
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     Shuffles the deck into a random order
     */
    public void shuffle() {
        ArrayList<FlashCard> shuffledCards = new ArrayList<FlashCard>();
        int numCards = cards.size();
        for(int i=0; i < numCards; i++)
        {
            int randomIndex = (int)(Math.random()*cards.size());
            FlashCard randomCard = this.cards.remove(randomIndex);
            shuffledCards.add(randomCard);
        }

        this.cards = shuffledCards;
    }

    /**
     Draw a single card from the deck and return it
     @return The card drawn from the top of the deck
     */

    public FlashCard draw() {
        return this.cards.remove(0);
    }

    /**
     Put a card onto the bottom of the deck.
     */
    public void putBack(FlashCard card) {
        this.cards.add(card);
    }

    /**
     Get the size of the deck.
     @return The number of cards in the deck.
     */
    public int getSize() {
        return this.cards.size();
    }
}
