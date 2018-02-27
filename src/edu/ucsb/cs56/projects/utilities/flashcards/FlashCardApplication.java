package edu.ucsb.cs56.projects.utilities.flashcards;

import javax.swing.JFrame;
import java.awt.event.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Shany Yeshanov, Chad Simmons
 * @version CS56 W12 2012.02.23
 */
public class FlashCardApplication {
	/**
	 * Default constructor for the making the whole application
	 */
	public FlashCardApplication() {
		this.outer = this;
		this.mainMenu = new MainMenuFrame();
		this.mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mainMenu.addActionListener(new MainMenuListener());
		this.mainMenu.setVisible(true);
	}

	/**
	 * Listener for the MainMenu JFrame
	 */
	public class MainMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			mainMenu.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			mainMenu.setVisible(false);
			if(ev.getActionCommand().equals("CreateDeck")) {
				createFrame = new CreateDeckFrame();
				createFrame.addActionListener(new CreateFrameListener());
				createFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				createFrame.setVisible(true);
			}
			else if(ev.getActionCommand().equals("LoadDeck")) {
				deck = mainMenu.getDeck();
				outer.showDeckMenu();
			} else
				mainMenu.setVisible(true);		//this occurs when ev.getActionCommand().equals("LoadFail")
		}
	}

	/**
	 * Listener for the CreateDeck JFrame
	 */
	public class CreateFrameListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			if(ev.getActionCommand().equals("DeckCreated")) {
				deck = createFrame.getDeck();
				createFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				outer.showDeckMenu();
				createFrame.setVisible(false);
			}
			else if(ev.getActionCommand().equals("MainMenu")){
				JFrame checkMain = new JFrame("Are you sure?");
				int confirmed = JOptionPane.showConfirmDialog(null,
						"Going to main menu will not save current deck, are you sure you want to continue" +
								"?", "Main Menu Message Box",
						JOptionPane.YES_NO_OPTION);

				if (confirmed == JOptionPane.YES_OPTION) {
					createFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					createFrame.setVisible(false);
					outer.showMainMenu();
				}
			}
		}
	}

	/**
	 * Listener for the DeckMenu JFrame
	 */
	public class DeckMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			String cmd = ev.getActionCommand();
			deckMenu.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			deckMenu.setVisible(false);
			if(cmd.equals("QuizModeSelected")) {
				outer.quizFrame = new QuizFrame();
				outer.quizFrame.addActionListener(new ModeListener());
				QuizController controller = new QuizController(new Quiz(deck),
						outer.quizFrame);
				quizFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				quizFrame.setVisible(true);
			}
			else if(cmd.equals("StudyModeSelected")) {
				outer.studyFrame = new DeckStudyFrame();
				outer.studyFrame.addActionListener(new ModeListener());
				DeckStudyController controller = new DeckStudyController(outer.studyFrame,
						outer.deck);
				studyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				studyFrame.setVisible(true);
			}
			else if(cmd.equals("MainMenu")){
				outer.showMainMenu();
			}
		}
	}

	/**
	 * Listener for setting the correct Mode to show
	 */
	public class ModeListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			if(ev.getActionCommand().equals("ModeExit")) {
				if (outer.studyFrame != null) {
					outer.studyFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					outer.studyFrame.setVisible(false);
					outer.studyFrame.dispose();
					outer.studyFrame = null;
				}
				if (outer.quizFrame != null) {
					outer.quizFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					outer.quizFrame.setVisible(false);
					outer.quizFrame.dispose();
					outer.quizFrame = null;
				}
				outer.showDeckMenu();
			}
			else if(ev.getActionCommand().equals("MainMenu")) {
				if (outer.studyFrame != null) {
					outer.studyFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					outer.studyFrame.setVisible(false);
					outer.studyFrame.dispose();
					outer.studyFrame = null;
				}
				if (outer.quizFrame != null) {
					outer.quizFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					outer.quizFrame.setVisible(false);
					outer.quizFrame.dispose();
					outer.quizFrame = null;
				}
				outer.showMainMenu();
			}
		}
	}

	/**
	 * Method for showing the DeckMenu JFrame
	 */
	public void showDeckMenu() {
		deckMenu = new DeckMenuFrame();
		deckMenu.addActionListener(new DeckMenuListener());
		deckMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		deckMenu.setVisible(true);
	}

	/**
	 * Method for showing the MainMenu JFrame
	 */
	public void showMainMenu() {
		mainMenu = new MainMenuFrame();
		mainMenu.addActionListener(new MainMenuListener());
		mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainMenu.setVisible(true);
	}
	/**
	 * The main function for the application.
	 * The only argument will be the file name of the
	 * saved flashcards.
	 */
	public static void main(String[] args) {
		FlashCardApplication app = new FlashCardApplication();
	}


	private MainMenuFrame mainMenu;
	private CreateDeckFrame createFrame;
	private DeckMenuFrame deckMenu;
	private QuizFrame quizFrame;
	private DeckStudyFrame studyFrame;
	private Deck deck;
	private FlashCardApplication outer;

}
