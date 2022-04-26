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

public class DisplayTest {

	@Test
	public void test() {
		Tokenizer t = CobolParser.tokenizer();
		Parser p = CobolParser.start();
		t.setString("display window erase");
		Assembly in = new TokenAssembly(t);
		Assembly out = p.bestMatch(in);
		
		// display windo erase = 3 elements expected in out assembly
		assertEquals(3, out.length());
		
		// check that whole assembly of tokens is absorbed
		assertNull(out.peek());
		
		// Checking if string given in tokenizer is same after parsing
		ArrayList<Parser> visited = new ArrayList<Parser>();
		Literal lit = new Literal("display window erase");
		String c2 = lit.unvisitedString(visited);
		assertEquals(c2, "display window erase");
	}

}
