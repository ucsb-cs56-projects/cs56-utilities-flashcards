package edu.ucsb.cs56.projects.utilities.flashcards;


public interface QuizUI {
    public void registerController(QuizController controller);

    public void quizStateChanged();

    public void questionWasAnswered(String guess, String answer);

}