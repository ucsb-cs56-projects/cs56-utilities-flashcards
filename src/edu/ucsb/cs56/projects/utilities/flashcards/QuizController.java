package edu.ucsb.cs56.projects.utilities.flashcards;


public class QuizController {
    /**
     * Constructor for the QuizController
     * @param quiz A Quiz Object
     * @param quizUI an object that implements quizUI
     */
    public QuizController(Quiz quiz, QuizUI quizUI) {
        this.quiz = quiz;
        this.quizUI = quizUI;
        this.quizUI.registerController(this);
    }

    /**
     * Method for submitting the answer
     * @param guess the inputted answer
     */
    public void submitAnswer(String guess) {
        String correctAnswer = this.quiz.answer(guess);
        this.quizUI.questionWasAnswered(guess, correctAnswer);
    }

    /**
     * Method for passing on an override command to the Quiz
     */
    public void override(){quiz.override();}

    /**
     * Getter for the current card's text
     * @return The current card's front text
     */
    public String getCardText() {
        return this.quiz.getCardText();
    }

    /**
     * Getter for the current card's index
     * @return The current card's index
     */
    public int getCurrentCardNum() {
        return this.quiz.getCurrentCardNum();
    }

    /**
     * Getter for the amount of correct answers
     * @return The count of the number of correct answers
     */
    public int getScore() {
        return this.quiz.getCorrectAnswerCount();
    }

    /**
     * Getter for the total amount of answers
     * @return The count of the total number of answers
     */
    public int getPossibleScore() {
        return this.quiz.getAnswerCount();
    }

    /**
     * Getter for the deck's size
     * @return The deck's size
     */
    public int getQuizSize() {
        return this.quiz.getDeckSize();
    }

    /**
     * Checks if the quiz is complete
     * @return True if the quiz is complete
     */
    public boolean quizIsComplete() {
        return this.quiz.isComplete();
    }

    /**
     * Method for restarting the quiz
     */
    public void restartQuiz() {
        if(quizIsComplete()){

        }
        else{

        }
        this.quiz.restart();
        this.quizUI.quizStateChanged();
    }

    private final static String defaultOptions = {"Complete Deck", "Current Deck"};
    private final static String endOfQuizOptions = {"Complete Deck", "Current Deck", "Incorrect Cards"};
    private Quiz quiz;
    private QuizUI quizUI;
}