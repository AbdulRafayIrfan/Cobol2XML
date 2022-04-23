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

public class ConstantValueTest {

	@Test
	public void test() {
		Tokenizer t = CobolParser.tokenizer();
		Parser p = CobolParser.start();
		
		// Testing cobol parser with a constant value line
		t.setString("88 base_2 value 2.");
		
		Assembly in = new TokenAssembly(t);
		Assembly out = p.bestMatch(in);
		
		// check if there are elements in the assembly
		assertNotNull(in.peek());
		
		// Check that number of elements in string equals tokenized number of elements,
		// Here there are 4 elements: 88, base_2, value, 2
		assertEquals(4, out.length());
		
		// Checking if string given in tokenizer is same after parsing
		ArrayList<Parser> visited = new ArrayList<Parser>();
		Literal lit = new Literal("88 base_2 value 2.");
		String s = lit.unvisitedString(visited);
		assertEquals(s, lit.toString());
	}

}
