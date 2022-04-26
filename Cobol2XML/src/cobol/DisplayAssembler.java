package cobol;

import java.util.ArrayList;

import parse.Assembler;
import parse.Assembly;
import parse.tokens.Token;

public class DisplayAssembler extends Assembler {

	@Override
	public void workOn(Assembly a) {
		Cobol c = new Cobol();
		// ArrayList used to save the tokens absorbed in the assembly
		ArrayList<String> quoted = new ArrayList<String>();
		ArrayList<String> values = new ArrayList<String>();
		
		// Initialize count for the number of quoted
		// or variable number of tokens
		int valueCount = 0;
		int quotedCount = 0;
		
		// looping through assembly stack
		while (!a.stackIsEmpty()) {
			Token t = (Token) a.pop();
			String value = t.sval();
			
			// increments token count of token is quoted string or value
			if (t.isQuotedString()) {
				quoted.add(value);
				quotedCount++;
			}
			// check if the token is not the word 'display'
			else if (!value.equalsIgnoreCase("display") ) {
				values.add(value);
				valueCount++;
			}
		}
		
		// ensuring that array lists are not empty
		if(!(values.isEmpty() && quoted.isEmpty())) {
			c.setQuoted(quoted);
			// values only added if not quoted token
			if (quotedCount == 0 && valueCount > 0) {	
				String oneValue = "";
				for (String value : values) {
					oneValue += value + " ";
				}
				values.clear();
				values.add(oneValue);
			}
			
			// set assembly target
			c.setValues(values);
			a.setTarget(c);
		}
	}
}
