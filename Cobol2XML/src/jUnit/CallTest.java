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

public class CallTest {

	@Test
	public void test() {
		Tokenizer t = CobolParser.tokenizer();
		Parser p = CobolParser.start();
		t.setString("call \"c$toupper\" using entry_char, value 16");
		Assembly in = new TokenAssembly(t);
		Assembly out = p.bestMatch(in);
		
		// expected length is 7 as we have 7 elements: call, quotedstring,
		// using, entry_char, ",", value, 16
		assertEquals(7, out.length()); 

		// check that whole assembly of tokens is absorbed
		assertNull(out.peek());
		
		// Checking if string given in tokenizer is same after parsing
		ArrayList<Parser> visited = new ArrayList<Parser>();
		Literal lit = new Literal("call \"c$toupper\" using entry_char, value 16");
		String c2 = lit.unvisitedString(visited);
		assertEquals(c2, "call \"c$toupper\" using entry_char, value 16");
	}

}
