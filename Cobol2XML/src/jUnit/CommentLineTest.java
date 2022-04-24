package jUnit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import cobol.CobolParser;
import parse.Assembly;
import parse.Parser;
import parse.tokens.Literal;
import parse.tokens.TokenAssembly;
import parse.tokens.Tokenizer;

public class CommentLineTest {

	@Test
	public void test() {
		Tokenizer t = CobolParser.tokenizer();
		Parser p = CobolParser.start();
		t.setString("***--- random comment here");
		Assembly in = new TokenAssembly(t);
		Assembly out = p.bestMatch(in);
		
		// check if absorbed assembly has same number of elements as expected (inputted)
		// Here my comment should be 9, (6 symbols + 3 words)
		assertEquals(out.length(), 9);
		
		// check if assembly has been absorbed properly
		assertNull(out.peek());
		
		// Checking if string given in tokenizer is same after parsing
		ArrayList<Parser> visitedAL = new ArrayList<Parser>();
		Literal lit = new Literal("***---  random comment here");
		String sr = lit.unvisitedString(visitedAL);
		assertEquals(sr, "***---  random comment here");
	}

}
