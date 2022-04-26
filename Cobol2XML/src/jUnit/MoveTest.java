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

public class MoveTest {

	@Test
	public void test() {
		Tokenizer t = CobolParser.tokenizer();
		Parser p = CobolParser.start();
		t.setString("move w_number to entry_number");
		Assembly in = new TokenAssembly(t);
		Assembly out = p.bestMatch(in);
		
		// we have 4 elements here so expecting 4 with the absorbed assembly
		assertEquals(4, out.length());
		
		// check that whole assembly of tokens is absorbed
		assertNull(out.peek());
		
		// Checking if string given in tokenizer is same after parsing 
		ArrayList<Parser> visited = new ArrayList<Parser>();
		Literal lit = new Literal("move w_number to entry_number");
		String c2 = lit.unvisitedString(visited);
		assertEquals(c2, "move w_number to entry_number");
	}

}
