package edu.ucsb.cs56.projects.utilities.flashcards;

import javax.swing.JFrame;
import java.awt.event.*;

/**
 *
 * @author Shany Yeshanov, Chad Simmons
 * @version CS56 W12 2012.02.23
 */
public class FlashCardApplication {

    public FlashCardApplication() {
	this.outer = this;
	this.mainMenu = new MainMenuFrame();
	this.mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.mainMenu.addActionListener(new MainMenuListener());
	this.mainMenu.setVisible(true);
    }

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
	    }
	}
    }
    

    public class CreateFrameListener implements ActionListener {
	public void actionPerformed(ActionEvent ev) {
	    deck = createFrame.getDeck();
	    createFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	    outer.showDeckMenu();
	    createFrame.setVisible(false);
	}
    }

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
	}
    }

    public class ModeListener implements ActionListener { 
	public void actionPerformed(ActionEvent ev) {
	    if(outer.studyFrame != null) {
		outer.studyFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		outer.studyFrame.setVisible(false);
		outer.studyFrame.dispose();
		outer.studyFrame = null;
	    }
	    if(outer.quizFrame != null) {
		outer.quizFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		outer.quizFrame.setVisible(false);
		outer.quizFrame.dispose();
		outer.quizFrame = null;
	    }
	    outer.showDeckMenu();
	}
    }

    public void showDeckMenu() {
	deckMenu = new DeckMenuFrame();
	deckMenu.addActionListener(new DeckMenuListener());
	deckMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	deckMenu.setVisible(true);
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
