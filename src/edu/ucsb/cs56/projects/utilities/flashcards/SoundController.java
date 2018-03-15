package edu.ucsb.cs56.projects.utilities.flashcards;
import java.io.*;
import javax.sound.sampled.*;

/**
 * SoundController class for loading sound files and provides methods for playback.
 * @author Brian Lim, Christopher Alsheikh
 */
public class SoundController {

	String successFileName = "media/success.wav";
	String failFileName = "media/fail.wav";

	Sound successSound;
	Sound failSound;

    /**
     * Contructor that connects sound streams to files and opens them.
     */
	public SoundController() {
		successSound = new Sound(successFileName);
		failSound = new Sound(failFileName);
	}

	/**
	 * Method that plays the "success" sound clip."
	 */	
	public void playSuccess() {
		successSound.playSound();
	}

	/**
	 * Method that plays the "fail" sound clip."
	 */
	public void playFail(){
		failSound.playSound();
	
	}


}
