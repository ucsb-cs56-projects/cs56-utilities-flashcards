package edu.ucsb.cs56.projects.utilities.flashcards;


public class Quiz { 

    public Quiz(Deck deck) {
	this.deck = deck;
	this.currentCard = this.deck.draw();
	this.deck.putBack(currentCard);
	this.restart();
    }

    public int getCorrectAnswerCount() {
	return this.correctAnswerCount;
    }
    
    public int getAnswerCount() {
	return this.answerCount;
    }

    public int getCurrentCardNum() {
	if(answerCount < deck.getSize())
	    return this.answerCount+1;
	else
	    return this.answerCount;
    }

    public int getDeckSize() {
	return this.deck.getSize();
    }

    public String getCardText() {
	return this.currentCard.getFrontText();
    }

    public String answer(String backText) {
	if(!this.isComplete()) {
	    String correctAnswer;
	    if(this.currentCard.getBackText().toLowerCase().equals(backText.toLowerCase())) {
		correctAnswer = backText;
		this.correctAnswerCount += 1;
	    }
	    else {
		correctAnswer = currentCard.getBackText();
	    }
	    
	    this.answerCount += 1;
	    this.currentCard = this.deck.draw();
	    this.deck.putBack(this.currentCard);
	    
	    if(this.answerCount == this.deck.getSize())
		this.completeFlag = true;

	    return correctAnswer;
	}
	return null;
    }

    public void restart() { 
	this.completeFlag = false;
	this.correctAnswerCount = 0;
	this.answerCount = 0;
	this.deck.shuffle();
	this.currentCard = this.deck.draw();
	this.deck.putBack(this.currentCard);
    }

    public boolean isComplete() {
	return completeFlag;
    }

    

    private FlashCard currentCard;
    private Deck deck;
    private boolean completeFlag;
    
    private int correctAnswerCount;
    private int answerCount;
}