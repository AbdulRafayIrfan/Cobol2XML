package jUnit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import parse.Parser;
import parse.tokens.Literal;

public class LiteralTest {

	@Test
	public void test() {
		String s = "Hello";
		Literal lit = new Literal(s);
		ArrayList<Parser> visited = new ArrayList<Parser>();
		String sr = lit.unvisitedString(visited);
		assertEquals(sr, s);
	}

}
