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

public class DateWrittenTest {

	@Test
	public void test() {
		
		Tokenizer t = CobolParser.tokenizer();
		Parser p = CobolParser.start();
		t.setString("date-written.  08-mar-1998 - mb.");
		Assembly in = new TokenAssembly(t);
		Assembly out = p.bestMatch(in);
		
		// check if assembly with elements consumed is not empty 
		assertFalse(out.stackIsEmpty());
		
		// check that string in tokenizer is same after parsing process
		ArrayList<Parser> visitedal = new ArrayList<Parser>();
		Literal lit = new Literal("date-written.  08-mar-1998 - mb.");
		String sr = lit.unvisitedString(visitedal);
		assertEquals(sr, "date-written.  08-mar-1998 - mb.");
		
	}

}
