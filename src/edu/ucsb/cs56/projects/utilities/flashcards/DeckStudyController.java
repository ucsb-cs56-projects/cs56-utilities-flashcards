package edu.ucsb.cs56.projects.utilities.flashcards;


/**
   Controller for a deck and a DeckStudy User Interface.
   @author Chad Simmons and Shany Yeshanov

 */
public class DeckStudyController { 

    /**
       Construct a new DeckStudyController to manage a given user interface and deck.
       If there are no cards in the deck a default message will appear.

       @param ui The user interface which communicates with the controller.
       @param deck The deck of cards being studied.
     */
    public DeckStudyController(DeckStudyUI ui, Deck deck) {
	this.StudyUI = ui;
	this.deck = deck;
	this.StudyUI.registerController(this);

	this.position = 0;
	this.nextCard();
    }

    /**
       Flip the current card to display the back side. 
       This will update the user interface with the text on the back of the card.      
     */
    public void flipCard() {
	if(this.isFlipped)
	    this.isFlipped = false;
	else
	    this.isFlipped = true;

	this.updateUI();
    }
    
    /**
       View the next card in the deck.
       The current card will be placed on the bottom. This method signals the UI to update its display.
     */
    public void nextCard() {
	if(this.currentCard == null 
	   && this.deck.getSize() == 0) {
	    this.updateUI();
	    return;
	}



	this.currentCard = this.deck.draw();
	this.deck.putBack(this.currentCard);

	this.isFlipped = false;
	this.position = (this.position % this.deck.getSize()) + 1;

	this.updateUI();
    }

    /**
       Shuffle the deck and draw/view the top card after shuffling. 
       This method signals the UI to update its display.
     */
    public void shuffleDeck() {
	if(this.currentCard != null){
	    this.deck.putBack(this.currentCard);
	    this.currentCard = null;
	}

	this.deck.shuffle();
	this.position = 0;
	this.nextCard(); // get the next card and update the ui.
    }


    /**
       Update the user interface with the text of the current card's state and position.
    */
    public void updateUI() {
	String cardText = "Add a new card to begin.";

	if(this.currentCard != null) {
	    cardText = this.currentCard.getFrontText();

	    if(this.isFlipped)
		cardText = this.currentCard.getBackText();
	}

	this.StudyUI.showCardText(cardText, this.isFlipped, 
				  position, this.deck.getSize());
    }

    private Deck deck;
    private DeckStudyUI StudyUI;
    private boolean isFlipped;
    private FlashCard currentCard;

    private int position;
    
}