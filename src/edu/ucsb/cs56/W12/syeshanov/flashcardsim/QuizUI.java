package edu.ucsb.cs56.W12.syeshanov.flashcardsim;


public interface QuizUI {
    public void registerController(QuizController controller);

    public void quizStateChanged();

    public void questionWasAnswered(String guess, String answer);

}