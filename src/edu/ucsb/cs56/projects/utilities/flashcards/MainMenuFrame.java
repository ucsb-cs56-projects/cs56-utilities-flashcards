package edu.ucsb.cs56.projects.utilities.flashcards;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.ArrayList;

public class MainMenuFrame extends JFrame {
	/**
	 * Default constructor for the Main Menu JFrame that loads or creates a new
	 * deck of cards
	 */
	public MainMenuFrame() {
		super("Flash Cards!");
		this.outer = this;

		JPanel contentPanel = new JPanel();
		this.setContentPane(contentPanel);
		contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		this.welcomeLabel = new JLabel("Load an existing deck, or create a new one.");

		this.welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(welcomeLabel);

		JPanel buttonPanel = new JPanel();

		this.loadButton = new JButton("Load");
		this.loadButton.addActionListener(new LoadButtonListener());
		buttonPanel.add(loadButton);

		this.createButton = new JButton("Create");
		this.createButton.addActionListener(new CreateButtonListener());
		buttonPanel.add(createButton);

		this.add(buttonPanel);
		this.actionListeners = new ArrayList<ActionListener>();
		this.pack();
	}

	/**
	 * Method to add an action listener to the JFrame
	 * 
	 * @param listener
	 *            an ActionListener
	 */
	public void addActionListener(ActionListener listener) {
		this.actionListeners.add(listener);
	}

	/**
	 * Method for loading a file that contains flashcards
	 * 
	 * @param fileName
	 *            The name of the File
	 * @return A Deck that contains the flashcards that the saved file contained
	 */
	public Deck loadDeck(String fileName) {
		try {
			ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(fileName));
			Deck deck = (Deck) inStream.readObject();
			return deck;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error:" + e, 
					"Error loading deck.", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Listener for the "Load" Button
	 */
	public class LoadButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			try {
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showDialog(outer, "Load Deck");
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					currentFile = chooser.getSelectedFile().toString();
					outer.deck = outer.loadDeck(chooser.getSelectedFile().getCanonicalPath());
					if (deck == null)
						outer.notifyDeckFailed();	//loading deck failed
					else
						outer.notifyDeckFinished();	//loading deck passed
				}
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Listener for the "Create" Button
	 */
	public class CreateButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			e = new ActionEvent(outer, 0, "CreateDeck");
			for (ActionListener l : outer.actionListeners)
				l.actionPerformed(e);
		}
	}

	/**
	 * Getter for the deck
	 * 
	 * @return the Deck
	 */
	public Deck getDeck() {
		return this.deck;
	}

	/**
	 * Method for telling the actionListeners that Loading or Creating a deck
	 * has finished
	 */
	public void notifyDeckFinished() {
		ActionEvent e = new ActionEvent(outer, 0, "LoadDeck"); 
																
		for (ActionListener l : this.actionListeners)
			l.actionPerformed(e);

	}

	/**
	 * Method for telling the actionListeners that Loading a deck has failed
	 */
	public void notifyDeckFailed() {
		ActionEvent e = new ActionEvent(outer, 0, "LoadFailed"); 
																	
																	
		for (ActionListener l : this.actionListeners)
			l.actionPerformed(e);

	}

	/**
	 * Main function for testing the MainMenu JFrame
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MainMenuFrame frame = new MainMenuFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	ArrayList<ActionListener> actionListeners;
	MainMenuFrame outer;
	JLabel welcomeLabel;
	JButton loadButton;
	JButton createButton;
	String currentFile;
	Deck deck;
}