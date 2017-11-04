package edu.ucsb.cs56.projects.utilities.flashcards;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Chad Simmons and Shany Yeshanov
 */
public class DeckTest {

    /**
     * Test of getName method, of class Deck.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Deck instance = new Deck("Biology");
        String expResult = "Biology";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Deck.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "Chemistry";
        Deck instance = new Deck("Unicorn");
        instance.setName(name);
	assertEquals(instance.getName(), name);
    }

    /**
     * Test of shuffle method, of class Deck.
     */
    @Test
    public void testShuffle() {
        System.out.println("shuffle");
        Deck instance = new Deck();
	for(int i=0; i<100; i++){
	    FlashCard card = new FlashCard(""+i,""+i);
	    instance.putBack(card);
        }
	instance.shuffle();
	boolean foundDifference = false;
	for(int i =0; i<100; i++){
	    FlashCard drawnCard = instance.draw();
	    String text = drawnCard.getFrontText();
	    if(!text.equals(""+i)){
		foundDifference = true;
		break;
	    }
	}
	assertEquals(foundDifference, true);
    }

    /**
     * Test of draw method, of class Deck.
     */
    @Test
    public void testPutBackAndDraw() {
        System.out.println("putBack and draw");
        Deck instance = new Deck();
        FlashCard expResult = new FlashCard("CS56", "Advanced Application Programming");
	instance.putBack(expResult);
        FlashCard result = instance.draw();
        assertEquals(expResult, result);
    }


}