package edu.ucsb.cs56.projects.utilities.flashcards;

import java.awt.Frame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Dialog box for getting the front and back text of a card.
 * @author Chad Simmons and Shany Yeshanov
 */
public class NewCardDialog extends javax.swing.JDialog implements ActionListener {

    /**
       Constructs a NewCardDialog owned by a parent Frame.
       @param owner The owner of the NewCardDialog.
     */
    public NewCardDialog(Frame owner) {
	super(owner, "Add a Card", true);

	BorderLayout layout = new BorderLayout();
	JPanel contentPane = new JPanel();
	JPanel buttonFrame = new JPanel();
	JPanel textFrame = new JPanel();
	
	this.setContentPane(contentPane);
	contentPane.setLayout(layout);

	contentPane.add(buttonFrame, BorderLayout.SOUTH);
	contentPane.add(textFrame, BorderLayout.CENTER);

	textFrame.setLayout(new BoxLayout(textFrame, 
					  BoxLayout.Y_AXIS));

 
	this.frontTextField = new JTextField();
	textFrame.add(createTextInput("Front Text:", 
				      this.frontTextField));

	this.backTextField = new JTextField();
	textFrame.add(createTextInput("Back Text:", 
				      this.backTextField));

	this.cancelButton = new JButton("Cancel");
	buttonFrame.add(this.cancelButton);
	this.cancelButton.addActionListener(this);

	this.clearButton = new JButton("Clear");
	buttonFrame.add(this.clearButton);
	this.clearButton.addActionListener(this);

	this.addButton = new JButton("Add");
	buttonFrame.add(this.addButton);
	this.addButton.addActionListener(this);

	this.actionListeners = new ArrayList<ActionListener>();

    }

    /**
       Adds an action listener to the list of objects to notify when the 'Add' Button is pressed.
       @param listener The new listener to inform when add is pressed.
    */
    public void addActionListener(ActionListener listener) {
	this.actionListeners.add(listener);
    }

    /**
       Retrieves the text that has been typed into the TextField for the front text.
       @return The string of text in the TextField.
     */
    public String getFrontText() {
	return this.frontTextField.getText();
    }

    /**
       Retrieves the text that has been typed into the TextField for the back text.
       @return The string of text in the TextField.
     */
    public String getBackText() {
	return this.backTextField.getText();
    }

    /**
       Helper method for creating text input panels.
       Will layout a text field with a label into a panel.
       @param name The label to appear next to the text field.
       @param field The text field to lay out.
     */
    private JPanel createTextInput(String name, JTextField field) {
	JPanel newInput = new JPanel();
	JLabel label = new JLabel(name);
	field.setPreferredSize(new Dimension(200,20));
	newInput.add(label);
	newInput.add(field);
	return newInput;
    }
    
    /**
       Method for recieving action events from the buttons in the Dialog.
       This method is required for the ActionListener interface.
       @param e The event which occurred.
     */
    public void actionPerformed(ActionEvent e) {
	if(e.getSource() == this.addButton)
	    {
		for(ActionListener listener : this.actionListeners) {
		    listener.actionPerformed(new ActionEvent(this, 
							     e.getID(), 
							     "AddCard"));
		}
	    }

	if(e.getSource() == this.clearButton) {
	    this.frontTextField.setText("");
	    this.backTextField.setText("");
	}

	if(e.getSource() == this.cancelButton) {
	    this.setVisible(false);
	}
    }

    private JTextField frontTextField;
    private JTextField backTextField;

    private JButton addButton;
    private JButton cancelButton;
    private JButton clearButton;
    private ArrayList<ActionListener> actionListeners;
}
