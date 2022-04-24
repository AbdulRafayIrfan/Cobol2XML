package cobol;

import java.util.ArrayList;

import parse.Assembler;
import parse.Assembly;
import parse.tokens.Token;

public class CommentLineAssembler extends Assembler {

	/**
	* Pop a string, and set the target DataDivision to this
	* string.
	*
	* @param Assembly the assembly to work on
	*/
	@Override
	public void workOn(Assembly a) {		
		//System.out.println("commentLineAssembler");
		Cobol c = new Cobol();
		// Creation of words that may be in a comment as an array list of string
		ArrayList<String> commentWords = new ArrayList<String>();

		// Looping through the assembly and adding any comment words (not '*' or '-') into the array list
		while (!a.stackIsEmpty()) {
			Token t = (Token) a.pop();
			String value = t.sval();
			// only words, not - or *
			if (!(value.equals("-") || value.equals("*") )) {
				commentWords.add(value);
			}
		}
		
		// Concatenating all the comment words into a single string
		int commentsize = commentWords.size() -1;
		// The final concatenated single string (commentline)
		String commentline = "";
		while ( commentsize >= 0 && commentsize < commentWords.size()) {
			// space between each word added
			commentline += commentWords.get(commentsize) + " ";
			commentsize--;
		}
		
		// Set the concatenated comment line for the target
		if (!commentline.isEmpty()) {
			c.setCommentLine(commentline);
			a.setTarget(c);
		}
		
	}
}

