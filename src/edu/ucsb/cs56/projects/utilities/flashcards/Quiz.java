package edu.ucsb.cs56.projects.utilities.flashcards;


public class Quiz {
	/**
	 * Contructor for Quiz Mode
	 * @param deck The deck to be quizzed on
	 */
	public Quiz(Deck deck) {
		this.currentDeck = deck;
		this.originalDeck = deck;
		this.currentCard = this.currentDeck.draw();
		this.currentDeck.putBack(currentCard);
		this.incorrectCards = new Deck();
		this.restart("Soft Reset");
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
		if(answerCount < currentDeck.getSize())
			return this.answerCount+1;
		else
			return this.answerCount;
	}

	/**
	 * Getter for the size of the deck
	 * @return The deck's size
	 */
	public int getDeckSize() {
		return this.currentDeck.getSize();
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
				incorrectCards.putBack(currentCard);
				correctAnswer = currentCard.getBackText();
			}

			this.answerCount += 1;
			this.currentCard = this.currentDeck.draw();
			this.currentDeck.putBack(this.currentCard);

			if(this.answerCount == this.currentDeck.getSize())
				this.completeFlag = true;

			return correctAnswer;
		}
		return null;
	}

	/**
	 * Method for incrementing the correctAnswerCount when an Override action is submitted
	 */
	public void override(){
		incorrectCards.pop();
		correctAnswerCount++;
	}

	/**
	 * Method for restarting the quiz
	 *
	 * Options:
	 * 		Hard Reset - Resets to beginning of the entire quiz session
	 *		Subdeck Reset - Resets to a subdeck of incorrect cards
	 * 		Soft Reset - Resets to beginning of current subdeck
	 */
	public void restart(String option) {
		if(option != "Invalid Option") {
			this.completeFlag = false;
			this.correctAnswerCount = 0;
			this.answerCount = 0;
			if (option.equals("Hard Reset")) {
				this.currentDeck = originalDeck;
			} else if (option.equals("Subdeck Reset")) {
				this.currentDeck = incorrectCards;
			}
			//Soft Reset doesn't do anything extra
			incorrectCards.empty();
			this.currentDeck.shuffle();
			this.currentCard = this.currentDeck.draw();
			this.currentDeck.putBack(this.currentCard);
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
	private Deck currentDeck;
	private Deck originalDeck;
	private Deck incorrectCards;
	private boolean completeFlag;

	private int correctAnswerCount;
	private int answerCount;
}