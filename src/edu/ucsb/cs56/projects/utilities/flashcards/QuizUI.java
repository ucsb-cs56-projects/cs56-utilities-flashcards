package edu.ucsb.cs56.projects.utilities.flashcards;


public interface QuizUI {
    /**
     * Method for registering the QuizController
     */
    public void registerController(QuizController controller);

    /**
     * Method for showing that the quiz's state changed
     */
    public void quizStateChanged();

    /**
     * Method for checking if the user's guess is correct
     */
    public void questionWasAnswered(String guess, String answer);

}