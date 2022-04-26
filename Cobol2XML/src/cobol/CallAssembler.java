package cobol;

import parse.Assembler;
import parse.Assembly;
import parse.tokens.Token;

public class CallAssembler extends Assembler {

	@Override
	public void workOn(Assembly a) {
		Cobol c = new Cobol();
		Token t = (Token) a.pop();
		
		// Checks if token is quoted and sets it appropriately
		if (t.isQuotedString()) {
			Token to = (Token) a.pop();
			c.setCallVariable(to.sval() + " " + t.sval());
		}
		// if token is a number, possibly 2 numbers at the end as value
		// so assembly is popped twice
		else if (t.isNumber()) {
			a.pop();
			a.pop();
			c.setCallValue(t.nval());
			Token to = (Token) a.pop();
			c.setCallVariable(to.sval());
		}
		
		a.pop(); 
		
		Token tok = (Token) a.pop();
		
		// set assembly target
		c.setCallIdentifier(tok.sval());
		a.setTarget(c);
	}

}
