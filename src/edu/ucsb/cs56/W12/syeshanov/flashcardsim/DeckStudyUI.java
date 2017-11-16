package edu.ucsb.cs56.W12.syeshanov.flashcardsim;


/**
   Interface declaring methods required for all DeckStudyUI implementations.
   Used by the DeckStudyController to provide a consistent interface for any kind of user interface.

   @author Chad Simmons and Shany Yeshanov
 */
public interface DeckStudyUI {

    /**
       Register the controller for this user interface.
     */
    public void registerController(DeckStudyController controller);

    /**
       Update the user interface with a given card's text and position in the deck.
     */
    public void showCardText(String text, boolean isFlipped, int position, int numCards);
}