package edu.ucsb.cs56.projects.utilities.flashcards;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class QuizTest {

	@Test
	public void getWholeDeckTest() {
		System.out.println("getWholeDeckTest");
		Deck sampleDeck = new Deck("Biology");

		FlashCard card = new FlashCard("ayyy", "forget about it");
		sampleDeck.putBack(card);

		ArrayList<FlashCard> fc = new ArrayList<FlashCard>();
		fc.add(card);

		Quiz sampleQuiz = new Quiz(sampleDeck);

		// deck has cards
		assertEquals(fc, sampleQuiz.getWholeDeck());

		sampleDeck.pop();
		fc.remove(0);

		// deck has 0 cards
		assertEquals(fc, sampleQuiz.getWholeDeck());

	}

	@Test
	public void getAnswerCountTest() {
		System.out.println("getAnswerCountTest");
		Deck sampleDeck = new Deck("Biology");

		FlashCard card = new FlashCard("ayyy", "forget about it");
		sampleDeck.putBack(card);

		Quiz sampleQuiz = new Quiz(sampleDeck);

		assertEquals(0, sampleQuiz.getAnswerCount());
	}

	@Test
	public void getCardTextTest() {
		System.out.println("getCardTextTest");
		Deck sampleDeck = new Deck("Biology");

		FlashCard card = new FlashCard("ayyy", "forget about it");
		sampleDeck.putBack(card);

		Quiz sampleQuiz = new Quiz(sampleDeck);
		assertEquals("ayyy", sampleQuiz.getCardText());

	}

	@Test
	public void getCorrectAnswerCountTest() {
		System.out.println("getCorrectAnswerCountTest");
		Deck sampleDeck = new Deck("Biology");

		FlashCard card = new FlashCard("ayyy", "forget about it");
		sampleDeck.putBack(card);

		Quiz sampleQuiz = new Quiz(sampleDeck);

		assertEquals(0, sampleQuiz.getCorrectAnswerCount());
	}

	@Test
	public void getCurrentCardNumTest() {
		System.out.println("getCurrentCardNumTest");
		Deck sampleDeck = new Deck("Biology");

		FlashCard card = new FlashCard("ayyy", "forget about it");
		sampleDeck.putBack(card);

		Quiz sampleQuiz = new Quiz(sampleDeck);

		assertEquals(1, sampleQuiz.getCurrentCardNum());
	}

	@Test
	public void getDeckSizeTest() {
		System.out.println("getDeckSizeTest");
		Deck sampleDeck = new Deck("Biology");

		FlashCard card = new FlashCard("ayyy", "forget about it");
		sampleDeck.putBack(card);

		Quiz sampleQuiz = new Quiz(sampleDeck);
		assertEquals(1, sampleQuiz.getDeckSize());

		// tests deck size 100 times
		for (int i = 0; i < 100; i++) {
			FlashCard card2 = new FlashCard("" + i, "" + i);
			sampleDeck.putBack(card2);
			sampleQuiz = new Quiz(sampleDeck);
			assertEquals(2 + i, sampleQuiz.getDeckSize());
		}

	}

	@Test
	public void answerTest() {
		System.out.println("getDeckSizeTest");
		Deck sampleDeck = new Deck("Biology");

		FlashCard card = new FlashCard("ayyy", "forget about it");
		sampleDeck.putBack(card);

		Quiz sampleQuiz = new Quiz(sampleDeck);
		// single value is correct
		assertEquals("forget about it", sampleQuiz.answer("ayyy"));
	
		// check 100 times
		for (int i = 0; i < 100; i++) {
			FlashCard card2 = new FlashCard("" + i, "" + i);
			sampleDeck.putBack(card2);
		}
		
		Quiz sampleQuiz2 = new Quiz(sampleDeck);
		
		for (int i = 0; i < 100; i++)
			assertNotNull(sampleQuiz2.answer(Integer.toString(i)));
	}

	@Test
	public void isCompleteTest() {
		System.out.println("getDeckSizeTest");
		Deck sampleDeck = new Deck("Biology");

		FlashCard card = new FlashCard("ayyy", "forget about it");
		sampleDeck.putBack(card);

		Quiz sampleQuiz = new Quiz(sampleDeck);
		assertEquals(false, sampleQuiz.isComplete());
	}
}
