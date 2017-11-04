package edu.ucsb.cs56.projects.utilities.flashcards;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Chad Simmons and Shany Yeshanov
 */
public class FlashCardTest {


    /**
     * Test of getBackText method, of class FlashCard.
     */
    @Test
    public void testGetBackText() {
        System.out.println("getBackText");
        FlashCard instance = new FlashCard("CS56","Advanced App Programming");
        String expResult = "Advanced App Programming";
        String result = instance.getBackText();
        assertEquals(expResult, result);
    }

    /**
     * Test of setBackText method, of class FlashCard.
     */
    @Test
    public void testSetBackText() {
        System.out.println("setBackText");
        String backText = "A cute fuzzy animal";
        FlashCard instance = new FlashCard("Dog","Dolphins");
        instance.setBackText(backText);
	assertEquals(instance.getBackText(), backText);
    }

    /**
     * Test of getFrontText method, of class FlashCard.
     */
    @Test
    public void testGetFrontText() {
        System.out.println("getFrontText");
        FlashCard instance = new FlashCard("Happiness", "An awesome thing");
        String expResult = "Happiness";
        String result = instance.getFrontText();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFrontText method, of class FlashCard.
     */
    @Test
    public void testSetFrontText() {
        System.out.println("setFrontText");
        String frontText = "Ice cream";
        FlashCard instance = new FlashCard("Snow","Something cold");
        instance.setFrontText(frontText);
	assertEquals(instance.getFrontText(), frontText);
    }

    /**
     * Test of toString method, of class FlashCard.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        FlashCard instance = new FlashCard("Cats","They meow");
        String expResult = String.format("Cats:%n\tThey meow");
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class FlashCard.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        FlashCard card = new FlashCard("Britney Spears","A horrible singer");
        FlashCard instance = new FlashCard("Adele","Sings much better");
        boolean expResult = false;
        boolean result = instance.equals(card);
        assertEquals(expResult, result);

	card = new FlashCard("Adele", "Sings much better");
	expResult = true;
	result = instance.equals(card);
	assertEquals(expResult, result);
    }

}