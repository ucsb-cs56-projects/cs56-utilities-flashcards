package edu.ucsb.cs56.projects.utilities.flashcards;


public class Quiz {
	/**
	 * Contructor for Quiz Mode
	 * @param deck The deck to be quizzed on
	 */
	public Quiz(Deck deck) {
		this.currentdeck = deck;
		this.currentCard = this.currentdeck.draw();
		this.currentdeck.putBack(currentCard);
		this.restart();
	}

	/**
	 * Getter for the amount of correct answers so far
	 * @return The number of correct answers
	 */
	public int getCorrectAnswerCount() {
		return this.correctAnswerCount;
	}

	/**
	 * Getter for the total amount of answers so far
	 * @return The number of answered cards
	 */
	public int getAnswerCount() {
		return this.answerCount;
	}

	/**
	 * Getter for the current card being displayed
	 * @return The index of the current card
	 */
	public int getCurrentCardNum() {
		if(answerCount < currentdeck.getSize())
			return this.answerCount+1;
		else
			return this.answerCount;
	}

	/**
	 * Getter for the size of the deck
	 * @return The deck's size
	 */
	public int getDeckSize() {
		return this.currentdeck.getSize();
	}

	/**
	 * Getter for the current card's text
	 * @return the front text of the current card
	 */
	public String getCardText() {
		return this.currentCard.getFrontText();
	}

	/**
	 * Method for checking if the inputted answer is correct
	 * @param backText the inputted string
	 * @return The correct answer if incorrect, null if correct
	 */
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
			this.currentCard = this.currentdeck.draw();
			this.currentdeck.putBack(this.currentCard);

			if(this.answerCount == this.currentdeck.getSize())
				this.completeFlag = true;

			return correctAnswer;
		}
		return null;
	}

	/**
	 * Method for incrementing the correctAnswerCount when an Override action is submitted
	 */
	public void override(){correctAnswerCount++;}

	/**
	 * Method for restarting the quiz
	 */
	public void restart(String option) {
		if(option.equals("Soft Reset")) {
			this.completeFlag = false;
			this.correctAnswerCount = 0;
			this.answerCount = 0;
			this.currentdeck.shuffle();
			this.currentCard = this.currentdeck.draw();
			this.currentdeck.putBack(this.currentCard);
		}
		else if(option.equals("Hard Reset")) {

		}
		else if(option.equals("Subdeck Reset")){

		}
	}

	/**
	 * Method for checking if the quiz is complete
	 * @return True if the quiz is complete
	 */
	public boolean isComplete() {
		return completeFlag;
	}



	private FlashCard currentCard;
	private Deck currentdeck;
	private Deck originalDeck;
	private boolean completeFlag;

	private int correctAnswerCount;
	private int answerCount;
}