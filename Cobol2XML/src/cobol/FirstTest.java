package cobol;

import static org.junit.Assert.*;

import org.junit.Test;

import parse.tokens.Token;
import parse.tokens.Word;

public class FirstTest extends Word {

	@Test
	public void test() {
		
		Token token = new Token("h");
		assertTrue(qualifies(token));
	}

}
