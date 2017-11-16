package edu.ucsb.cs56.W12.syeshanov.flashcardsim;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.ArrayList;


public class DeckMenuFrame extends JFrame {
    public DeckMenuFrame() {
	super("Choose Mode");
	JButton quizButton;
	JButton studyButton;

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
	
	this.add(buttonPanel);
	this.actionListeners = new ArrayList<ActionListener>();
	this.pack();

    }

    public void addActionListener(ActionListener listener) {
	this.actionListeners.add(listener);
    }

    public class QuizButtonListener implements ActionListener {
	public void actionPerformed(ActionEvent ev) {
	    ev = new ActionEvent(outer, 0, "QuizModeSelected");
	    for(ActionListener listener: outer.actionListeners)
		listener.actionPerformed(ev);
	}
    }

    public class StudyButtonListener implements ActionListener {
	public void actionPerformed(ActionEvent ev) {
	    ev = new ActionEvent(outer, 0, "StudyModeSelected");
	    for(ActionListener listener: outer.actionListeners)
		listener.actionPerformed(ev);
	}
    }

    private DeckMenuFrame outer;
    private ArrayList<ActionListener> actionListeners;
}