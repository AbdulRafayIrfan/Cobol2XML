package jUnit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import parse.tokens.Symbol;

public class SymbolTest {

	@Test
	public void test() {
		char ch = 'r';
		Symbol symbol = new Symbol(ch);
		ArrayList<String> arraylist = new ArrayList<String>();
		arraylist = symbol.randomExpansion(3, 1);
		assertFalse(arraylist.isEmpty());
	}

}
