package cobol;

import java.util.ArrayList;

import parse.Assembler;
import parse.Assembly;
import parse.tokens.Token;

public class MoveAssembler extends Assembler {

	@Override
	public void workOn(Assembly a) {
		
		Cobol c = new Cobol();
		Token t = (Token) a.pop();
		
		// saving all final location  
		ArrayList <String> finalLoc = new ArrayList<String>();
		while (!t.sval().equalsIgnoreCase("to")) {
			if (t.isNumber()) {
				finalLoc.add(Double.toString(t.nval()));
			}
			else {
				finalLoc.add(t.sval());
			}
			
			t = (Token) a.pop();
		}
		
		// creates position string
		String finalLocString = "";
		int size = finalLoc.size()-1;
		for (int i = size; i>=0; i--) {
			finalLocString += finalLoc.get(i) + " ";
		}
		
		// saving all the current position values
		ArrayList <String> currentLoc = new ArrayList<String>();
		t = (Token) a.pop();
		// ignoring if token is word 'move'
		while (!t.sval().equalsIgnoreCase("move")) {
			if (t.isNumber()) {
				currentLoc.add(Double.toString(t.nval()));
			}
			else {
				currentLoc.add(t.sval());
			}
			
			t = (Token) a.pop();
		}
		
		// creation of string through concatenation
		String currentLocString = "";
		size = currentLoc.size()-1;
		for (int i = size; i>=0; i--) {
			currentLocString += currentLoc.get(i) + " ";
		}
		
		// sets assembly target
		c.setInitialPosition(currentLocString);
		c.setFinalPosition(finalLocString);
		a.setTarget(c);
		
	}

}
