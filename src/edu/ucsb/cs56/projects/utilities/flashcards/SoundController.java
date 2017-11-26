package edu.ucsb.cs56.projects.utilities.flashcards;
import java.io.*;
import javax.sound.sampled.*;

/**
 * SoundController class for loading sound files and provides methods for playback.
 * @author Brian Lim, Christopher Alsheikh
 */
public class SoundController {

	File successFile = new File("media/success.wav");
	AudioInputStream successStream;
	Clip success;

	File failFile;
	AudioInputStream failStream;
	Clip fail;

    /**
     * Contructor that connects sound streams to files and opens them.
     */
	public SoundController() {	
		failFile = new File("media/fail.wav");
		try {
			successStream = AudioSystem.getAudioInputStream(successFile);
			success = AudioSystem.getClip();
            success.open(successStream);

			failStream = AudioSystem.getAudioInputStream(failFile);
			fail = AudioSystem.getClip();
            fail.open(failStream);

			
		} catch (UnsupportedAudioFileException e) {
			System.out.println("No sound: Unsupported Audio File Exception");
		} catch (LineUnavailableException e) {
			System.out.println("No sound: Line Unavailable Exception");
		} catch (IOException e) {
			System.out.println("No sound: IO Exception");
		}
	}

	/**
	 * Method that plays the "success" sound clip."
	 */	
	public void playSuccess() {

        	success.setMicrosecondPosition(0);
        	success.start();

	}

	/**
	 * Method that plays the "fail" sound clip."
	 */
	public void playFail(){

        	fail.setMicrosecondPosition(0);
		fail.start();
	
	}


}
