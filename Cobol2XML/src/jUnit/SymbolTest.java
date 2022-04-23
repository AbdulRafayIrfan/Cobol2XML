package jUnit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import parse.tokens.Symbol;

public class SymbolTest {

	@Test
	public void test() {
		char c = 'a';
		Symbol symbol = new Symbol(c);
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList = symbol.randomExpansion(2,1);
		assertFalse( arrayList.isEmpty() );
	}

}
