package edu.ucsb.cs56.projects.utilities.flashcards;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {
    File soundFile;
    AudioInputStream soundStream;
    Clip sound;

    public Sound(String fileName) {
        try {
            soundFile = new File(fileName);
            soundStream = AudioSystem.getAudioInputStream(soundFile);
            sound = AudioSystem.getClip();
            sound.open(soundStream);
        } catch (UnsupportedAudioFileException e) {
            System.out.println("No sound: Unsupported Audio File Exception");
        } catch (LineUnavailableException e) {
            System.out.println("No sound: Line Unavailable Exception");
        } catch (IOException e) {
            System.out.println("No sound: IO Exception");
        }
    }

    public void playSound() {
        sound.setMicrosecondPosition(0);
        sound.start();
    }
}
