package edu.ucsb.cs56.projects.utilities.flashcards;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.util.ArrayList;

/**
 * A graphical user interface for studying a deck of flash cards using swing.
 * @author Chad Simmons and Shany Yeshanov
 */
public class DeckStudyFrame extends JFrame implements ActionListener,DeckStudyUI{

	/** Creates new DeckStudyFrame */
	public DeckStudyFrame() {

		JPanel contentPanel = new JPanel();
		this.setContentPane(contentPanel);
		contentPanel.setBorder(new EmptyBorder(10,20,50,20));


		BoxLayout layout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
		this.setLayout(layout);

		this.positionLabel = new JLabel("# of #");
		this.positionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.positionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.add(this.positionLabel);


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



		this.flipCardButton = createToggleButton("Flip");
		this.flipCardButton.addActionListener(this);
		this.nextCardButton = createButton("Next Card");
		this.nextCardButton.addActionListener(this);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(this.flipCardButton);
		buttonPanel.add(this.nextCardButton);
		this.add(buttonPanel);


		this.shuffleButton = createButton("Shuffle", 150);
		this.shuffleButton.addActionListener(this);

		this.mainMenuButton = createButton("Main Menu", 150);
		this.mainMenuButton.addActionListener(this);

		this.quitButton = createButton("Quit", 150);
		this.quitButton.addActionListener(this);



		JPanel shufflePanel = new JPanel();
		shufflePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0,0));
		shufflePanel.add(this.shuffleButton);

		JPanel quitPanel = new JPanel();
		quitPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0,0));
		quitPanel.add(this.quitButton);

		JPanel mainMenuPanel = new JPanel();
		mainMenuPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		mainMenuPanel.add(this.mainMenuButton);

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,3));
		buttonPanel.add(shufflePanel);
		buttonPanel.add(mainMenuPanel);
		buttonPanel.add(quitPanel);

		buttonPanel.setBorder(new EmptyBorder(25, 0,0,0));
		this.add(buttonPanel);

		this.pack();
		this.actionListeners = new ArrayList<ActionListener>();
		this.setMinimumSize(new Dimension(this.getWidth(), this.getHeight()));


	}

	/**
	 Show the given card text.
	 Required for the DeckStudyUI interface.
	 @param text  The text on the card
	 @param isFlipped Indicates whether or not the card is 'flipped' (viewing the back side)
	 @param position The position the current card takes in the deck.
	 @param numCards The total number of cards in the deck.
	 */
	public void showCardText(String text, boolean isFlipped, int position, int numCards) {

		if(isFlipped)
			this.flipCardButton.setSelected(true);
		else
			this.flipCardButton.setSelected(false);

		this.cardTextLabel.setText(text);

		this.positionLabel.setText(String.format("%d of %d", position, numCards));
	}

	/**
	 Registers the controller for this UI.
	 Required for the DeckStudyUI interface.
	 @param controller The controller for this user interface.
	 */
	public void registerController(DeckStudyController controller) {
		this.controller = controller;
	}

	/**
	 * Method for adding an action listener to the JFrame
	 * @param listener an ActionListener
	 */
	public void addActionListener(ActionListener listener) {
		this.actionListeners.add(listener);
	}


	/**
	 Recieves events from the buttons in the UI, as well as the NewCardDialog.
	 Required for the ActionListener interface.
	 @param e The action event.
	 */
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == this.nextCardButton)
			this.controller.nextCard();

		if(e.getSource() == this.flipCardButton)
			this.controller.flipCard();

		if(e.getSource() == this.shuffleButton)
			this.controller.shuffleDeck();

		if(e.getSource() == this.quitButton) {
			e = new ActionEvent(this, 0, "ModeExit");
			for(ActionListener listener: this.actionListeners) {
				listener.actionPerformed(e);
			}
		}
		if(e.getSource() == this.mainMenuButton){
			e = new ActionEvent(this, 0, "MainMenu");
			for(ActionListener listener: this.actionListeners) {
				listener.actionPerformed(e);
			}
		}

	}


	/**
	 Helper method for constructing a button with a given label and the default width (125).
	 @param label The label for the button.
	 */
	private JButton createButton(String label) {
		return createButton(label, 125);
	}


	/**
	 Helper method to construct a button with a specified width and label.
	 @param label The label for the button.
	 @param width The width of the button.
	 */
	private JButton createButton(String label, int width) {
		JButton button = new JButton(label);
		button.setPreferredSize(new Dimension(width, 20));
		return button;
	}


	/**
	 Helper method for constructing a toggle button with a given label and the default width (125).
	 @param label The label for the button.
	 */

	private JToggleButton createToggleButton(String label) {
		return createToggleButton(label, 125);
	}

	/**
	 Helper method for constructing a toggle button with a given label and width.
	 @param label The label for the button.
	 @param width The width for the button.
	 */

	private JToggleButton createToggleButton(String label, int width) {
		JToggleButton button = new JToggleButton(label);
		button.setPreferredSize(new Dimension(width, 20));
		return button;
	}

	private JButton shuffleButton;
	private ArrayList<ActionListener> actionListeners;
	private JToggleButton flipCardButton;
	private JButton nextCardButton;
	private JLabel positionLabel;
	private JLabel cardTextLabel;
	private JPanel cardTextPanel;
	private DeckStudyController controller;
	private JButton quitButton;
	private JButton mainMenuButton;
}
