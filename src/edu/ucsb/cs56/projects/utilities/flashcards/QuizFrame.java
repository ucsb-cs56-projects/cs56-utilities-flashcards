package edu.ucsb.cs56.projects.utilities.flashcards;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.ArrayList;



public class QuizFrame extends JFrame implements QuizUI {
	/**
	 * Default constructor for a Quiz JFrame
	 */
	public QuizFrame() {
		super("Quiz Time!");

		JPanel contentPanel = new JPanel();
		this.setContentPane(contentPanel);
		contentPanel.setBorder(new EmptyBorder(10,20,50,20));


		BoxLayout layout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
		this.setLayout(layout);

		JPanel labelPanel = new JPanel();
		JPanel positionPanel = new JPanel();
		JPanel scorePanel = new JPanel();

		this.positionLabel = new JLabel("Card number: #/#");
		positionPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0,0));
		positionPanel.add(this.positionLabel);

		this.scoreLabel = new JLabel("Score: #/#");
		scorePanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0,0));
		scorePanel.add(this.scoreLabel);

		labelPanel.setLayout(new GridLayout(1,2));
		labelPanel.add(positionPanel);
		labelPanel.add(scorePanel);

		this.add(labelPanel);

		this.cardTextPanel = new JPanel();
		this.cardTextPanel.setBorder(new LineBorder(Color.black));
		this.cardTextPanel.setPreferredSize(new Dimension(500,300));
		this.cardTextPanel.setMinimumSize(new Dimension(500,300));
		this.cardTextPanel.setBackground(Color.white);
		this.cardTextPanel.setLayout(new GridLayout(1,1));

		this.cardTextLabel = new JLabel();
		this.cardTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.cardTextLabel.setBackground(Color.white);
		this.cardTextLabel.setFont(new Font("Courier New",
				Font.BOLD, 20));
		this.cardTextLabel.setText("Card Front Text");
		this.cardTextPanel.add(this.cardTextLabel);
		this.add(this.cardTextPanel);


		this.answerPanel = new JPanel();
		this.add(answerPanel);


		this.answerButton = createButton("Answer");
		this.answerButton.addActionListener(new AnswerButtonListener());

		this.nextCardButton = createButton("Next Card");
		this.nextCardButton.addActionListener(new NextCardButtonListener());

		this.answerTextField = new JTextField("",20);


		JPanel buttonPanel = new JPanel();
		buttonPanel.add(this.answerButton);
		buttonPanel.add(this.answerTextField);
		buttonPanel.add(this.nextCardButton);
		this.add(buttonPanel);

		this.restartButton = createButton("Restart", 150);
		this.restartButton.addActionListener(new RestartButtonListener());

		this.quitButton = createButton("Quit", 150);
		this.quitButton.addActionListener(new QuitButtonListener());

		this.mainMenuButton = createButton("Main Menu", 150);
		this.mainMenuButton.addActionListener(new MainMenuButtonListener());

		JPanel restartPanel = new JPanel();
		restartPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0,0));
		restartPanel.add(this.restartButton);

		JPanel quitPanel = new JPanel();
		quitPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0,0));
		quitPanel.add(this.quitButton);

		JPanel mainMenuPanel = new JPanel();
		mainMenuPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		mainMenuPanel.add(this.mainMenuButton);

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,3));
		buttonPanel.add(restartPanel);
		buttonPanel.add(mainMenuButton);
		buttonPanel.add(quitPanel);
		buttonPanel.setBorder(new EmptyBorder(25, 0,0,0));
		this.add(buttonPanel);

		this.nextCardButton.setEnabled(false);
		this.outer = this;
		this.pack();
		this.setMinimumSize(new Dimension(this.getWidth(), this.getHeight()));

		this.actionListeners = new ArrayList<ActionListener>();

	}

	/**
	 * Method for setting the controller
	 * @param controller a QuizController
	 */
	public void registerController(QuizController controller) {
		this.controller = controller;
		this.showQuizState();
	}

	/**
	 * Method for showing that the Quiz's state changed
	 */
	public void quizStateChanged() {
		this.showQuizState();
	}

	/**
	 * Method for checking the answer with the guess
	 * @param guess A user's guess string
	 * @param answer The answer to the card
	 */
	public void questionWasAnswered(String guess, String answer) {
		if(this.answerLabel != null) {
			this.answerPanel.remove(this.answerLabel);
			this.answerLabel = null;
		}

		if(guess.equals(answer)) {
			this.answerLabel = new JLabel("Correct!");
			this.answerLabel.setForeground(Color.green);

		}
		else {
			this.answerLabel = new JLabel("Incorrect!");
			this.answerLabel.setForeground(Color.red);
		}

		this.answerPanel.add(this.answerLabel);
		this.answerPanel.invalidate();
		this.cardTextLabel.setText(answer);
		this.answerButton.setEnabled(false);

		int score = this.controller.getScore();
		int possibleScore = this.controller.getPossibleScore();
		this.scoreLabel.setText(String.format("Score: %d/%d", score, possibleScore));

		if(!this.controller.quizIsComplete())
			this.nextCardButton.setEnabled(true);
	}

	/**
	 * Method for showing the current Quiz state
	 */
	public void showQuizState() {
		int score = this.controller.getScore();
		int possibleScore = this.controller.getPossibleScore();
		int cardNum = this.controller.getCurrentCardNum();
		int quizSize = this.controller.getQuizSize();

		String cardText = this.controller.getCardText();

		this.positionLabel.setText(String.format("Card position: %d/%d", cardNum, quizSize));
		this.scoreLabel.setText(String.format("Score: %d/%d", score, possibleScore));

		this.cardTextLabel.setText(cardText);

		if(this.answerLabel != null)
			this.answerPanel.remove(this.answerLabel);

		this.answerLabel = null;
		this.nextCardButton.setEnabled(false);
		this.answerButton.setEnabled(true);
		this.answerTextField.setText("");
		this.answerTextField.setEnabled(true);

	}

	/**
	 Helper method for constructing a button with a given label and the default width (125).
	 @param label The label for the button.
	 */
	private JButton createButton(String label) {
		return createButton(label, 125);
	}

	/**
	 Helper method for constructing a button with a given label and width.
	 @param label The label for the button.
	 @param width The width for the button.
	 */

	private JButton createButton(String label, int width) {
		JButton button = new JButton(label);
		button.setPreferredSize(new Dimension(width, 20));
		return button;
	}


	/**
	 * Main method for testing the Quiz JFrame
	 * @param args
	 */
	public static void main (String [] args) {
		QuizFrame frame = new QuizFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		Deck deck = new Deck();
		for(int i=0; i < 10; i++) {
			FlashCard f = new FlashCard("Front " + i, "Back " + i);
			deck.putBack(f);
		}

		QuizController control = new QuizController(new Quiz(deck), frame);
	}

	/**
	 * Listener for the "Answer Button"
	 */
	public class AnswerButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String guess = outer.answerTextField.getText();
			outer.controller.submitAnswer(guess);
			outer.answerTextField.setEnabled(false);
		}
	}

	/**
	 * Listener for the "Next Card" Button
	 */
	public class NextCardButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			outer.showQuizState();
		}
	}

	/**
	 * Listener for the "Restart" Button
	 */
	public class RestartButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			outer.controller.restartQuiz();
			outer.showQuizState();
		}
	}

	/**
	 * Listener for the "Quit" Button
	 */
	public class QuitButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			e = new ActionEvent(outer, 0, "ModeExit");
			for(ActionListener listener: outer.actionListeners)
				listener.actionPerformed(e);
		}
	}

	/**
	 * Listener for the "Main Menu" Button
	 */
	public class MainMenuButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			e = new ActionEvent(outer, 0, "MainMenu");
			for(ActionListener listener: outer.actionListeners)
				listener.actionPerformed(e);
		}
	}

	/**
	 * Method for adding an action listener
	 * @param listener an ActionListener
	 */
	public void addActionListener(ActionListener listener) {
		this.actionListeners.add(listener);
	}


	private QuizController controller;
	private JButton answerButton;
	private JButton nextCardButton;
	private JButton restartButton;
	private JButton quitButton;
	private JButton mainMenuButton;
	private JLabel scoreLabel;
	private JLabel positionLabel;
	private JLabel cardTextLabel;
	private JLabel answerLabel;
	private JPanel answerPanel;
	private JPanel cardTextPanel;
	private JTextField answerTextField;
	private QuizFrame outer;
	private ArrayList<ActionListener> actionListeners;
}