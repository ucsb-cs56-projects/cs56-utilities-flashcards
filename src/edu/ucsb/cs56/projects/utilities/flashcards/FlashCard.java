package edu.ucsb.cs56.projects.utilities.flashcards;

import java.io.Serializable;

/**
 * FlashCard implements a simple flashcard like object.
 * Has a front text and a back text.
 * @author Chad Simmons, Shany Yeshanov
 */


public class FlashCard implements Serializable{

    /**
       Constructs a new FlashCard with a front and back text.
       @param frontText The text for the front of the card.
       @param backText The text for the back of the card.
     */
    public FlashCard(String frontText, String backText) {
        this.frontText = frontText;
        this.backText = backText;
    }


    /** Getter for the back text. */
    public String getBackText() {
        return backText;
    }


    /** Setter for the back text 
	@param backText The text for the back of the card.
     */

    public void setBackText(String backText) {
        this.backText = backText;
    }


    /** Getter for the front text. */
    public String getFrontText() {
        return frontText;
    }

    /** Setter for the front text 
	@param frontText The text for the front of the card.
     */
    public void setFrontText(String frontText) {
        this.frontText = frontText;
    }

    /** Method for representing a flashcard as a string. */
    public String toString() {
        String result = String.format("%s:%n\t%s", this.frontText, this.backText);
        return result;
    }

    /** Method for comparing two flashcards 
	@param card The other card to be compared.
     */
    public boolean equals(FlashCard card){
        return this.frontText.equals(card.frontText) && this.backText.equals(card.backText);
    }


    static private final long serialVersionUID = 0xcad;
    private String frontText;
    private String backText;

}
