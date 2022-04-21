package jUnit;

import static org.junit.Assert.*;

import org.junit.Test;

import parse.tokens.Token;

public class TokenTest {

	@Test
	public void test() {
		char ch = 'l';
		Token t = new Token(ch);
		
		// Testing to see if the character token is of what type
		assertTrue(t.isSymbol());
		assertFalse(t.isNumber());
		assertFalse(t.isWord());
		
		// Testing to see if the string token is of what type
		String s = "Hello";
		Token to = new Token(s);
		assertTrue(to.isWord());
		assertFalse(to.isSymbol());
		assertFalse(to.isNumber());
	}

}
