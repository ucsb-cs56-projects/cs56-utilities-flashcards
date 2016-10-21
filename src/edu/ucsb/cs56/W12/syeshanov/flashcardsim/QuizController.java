package edu.ucsb.cs56.W12.syeshanov.flashcardsim;


public class QuizController { 

    public QuizController(Quiz quiz, QuizUI quizUI) {
	this.quiz = quiz;
	this.quizUI = quizUI;
	this.quizUI.registerController(this);
    }

    public void submitAnswer(String guess) {
	String correctAnswer = this.quiz.answer(guess);
	this.quizUI.questionWasAnswered(guess, correctAnswer);
    }

    public String getCardText() {
	return this.quiz.getCardText();
    }

    public int getCurrentCardNum() { 
	return this.quiz.getCurrentCardNum();
    }

    public int getScore() {
	return this.quiz.getCorrectAnswerCount();
    }

    public int getPossibleScore() {
	return this.quiz.getAnswerCount();
    }
    
    public int getQuizSize() {
	return this.quiz.getDeckSize();
    }
    
    public boolean quizIsComplete() {
	return this.quiz.isComplete();
    }

    public void restartQuiz() {
	this.quiz.restart();
	this.quizUI.quizStateChanged();
    }


    private Quiz quiz;
    private QuizUI quizUI;
}