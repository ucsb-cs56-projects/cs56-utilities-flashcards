package edu.ucsb.cs56.projects.utilities.flashcards;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.ArrayList;


public class DeckMenuFrame extends JFrame {
	/**
	 * Default constructor for creating a DeckMenu JFrame
	 */
	public DeckMenuFrame() {
		super("Choose Mode");
		JButton quizButton;
		JButton studyButton;
		JButton mainMenuButton;
		JButton editButton;

		this.outer = this;

		JPanel contentPanel = new JPanel();
		this.setContentPane(contentPanel);
		contentPanel.setBorder(new EmptyBorder(10,20,10,20));
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		JLabel menuLabel = new JLabel("Would you like a quiz or just to study?");
		menuLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		menuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(menuLabel);

		JPanel buttonPanel = new JPanel();
		quizButton = new JButton("Quiz");
		quizButton.addActionListener(new QuizButtonListener());
		buttonPanel.add(quizButton);

		studyButton = new JButton("Study");
		studyButton.addActionListener(new StudyButtonListener());
		buttonPanel.add(studyButton);

		editButton = new JButton("Edit");
		editButton.addActionListener(new EditButtonListener());
		buttonPanel.add(editButton);

		mainMenuButton = new JButton("Main Menu");
		mainMenuButton.addActionListener(new MainMenuButtonListener());
		buttonPanel.add(mainMenuButton);

		this.add(buttonPanel);
		this.actionListeners = new ArrayList<ActionListener>();
		this.pack();

	}

	/**
	 * Method for adding an action listener to the JFrame
	 * @param listener an ActionListener
	 */
	public void addActionListener(ActionListener listener) {
		this.actionListeners.add(listener);
	}

	/**
	 * A Button Listener for the "Quiz" Button
	 */
	public class QuizButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			ev = new ActionEvent(outer, 0, "QuizModeSelected");
			for(ActionListener listener: outer.actionListeners)
				listener.actionPerformed(ev);
		}
	}

	/**
	 * A Button Listener for the "Main Menu" Button
	 */
	public class MainMenuButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			ev = new ActionEvent(outer, 0, "MainMenu");
			for(ActionListener listener: outer.actionListeners)
				listener.actionPerformed(ev);
		}
	}
	/**
	 * A Button Listener for the "Study" Button
	 */
	public class StudyButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			ev = new ActionEvent(outer, 0, "StudyModeSelected");
			for(ActionListener listener: outer.actionListeners)
				listener.actionPerformed(ev);
		}
	}

	/**
	 * A Button Listener for the "Edit" Button
	 */
	public class EditButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			ev = new ActionEvent(outer, 0, "EditModeSelected");
			for(ActionListener listener: outer.actionListeners)
				listener.actionPerformed(ev);
		}
	}

	public static void main(String [] args) {
		DeckMenuFrame frame = new DeckMenuFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private DeckMenuFrame outer;
	private ArrayList<ActionListener> actionListeners;
}