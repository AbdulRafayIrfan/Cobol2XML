package parse.tokens;

import static org.junit.Assert.*;

import org.junit.Test;

public class FirstTest {

	@Test
	public void wordTest() {
		Word w = new Word();
		
		assertFalse(w.qualifies(5));
	}

	@Test
	public void symbolTest() {
		
	}
	
}
