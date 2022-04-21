package jUnit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import parse.tokens.Num;

public class NumTest {

	@Test
	public void test() {
		Num num = new Num();
		ArrayList<String> al = new ArrayList<String>();
		al = num.randomExpansion(3, 1);
		assertFalse(al.isEmpty());
	}

}
