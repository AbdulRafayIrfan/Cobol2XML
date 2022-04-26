/*
 * @(#)CobolParser.java	 0.1.0
 *
 * Copyright (c) 2019 Julian M. Bass
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 */
package cobol;

import parse.Alternation;
import parse.Empty;
import parse.Parser;
import parse.Repetition;
import parse.Sequence;
import parse.tokens.CaselessLiteral;
import parse.tokens.Num;
import parse.tokens.QuotedString;
import parse.tokens.Symbol;
import parse.tokens.Tokenizer;
import parse.tokens.Word;

public class CobolParser {
	/**
	 * Return a parser that will recognize the selected COBOL source code constructs:
	 * 
	 * 
	 * This parser creates a <code>COBOL</code> object
	 * as an assembly's target.
	 *
	 * @return a parser that will recognize and build a 
	 *         <object>COBOL</object> from a source code file.
	 */
	public Parser cobol() {
		Alternation a = new Alternation();
		
		Symbol fullstop = new Symbol('.');
		fullstop.discard();
		
		a.add( ProgramID() );
		
		a.add( DivisionName() );
		
		a.add( SectionName() );
		
		a.add( DateWritten() );
		
		a.add( constantValue() );
		
		a.add( CommentLine() );
		
		a.add(Display());
		
		a.add(Move());
		
		a.add(Call());
		
		a.add(new Empty());
		return a;
	}
	
	/*
	 * Return a parser that will recognize the grammar:
	 * 
	 *    Program Identifier = Word
	 *
	 */
	protected Parser ProgramID() {
		Sequence s = new Sequence();
		s.add(new CaselessLiteral("program-id") );
		s.add(new Symbol('.').discard());	
		s.add(new Word().setAssembler(new Program_idAssembler()));
		return s;
	}



	/*
	 * Return a parser that will recognise the grammar:
	 * 
	 *    <divisionName> division
	 *
	 */
	protected Parser DivisionName() {
		Sequence s = new Sequence();
		s.add(new Word().setAssembler(new DivisionAssembler()));
		s.add(new CaselessLiteral("division") );
		s.add(new Symbol('.').discard());
		return s;
	}
	
	/*
	 * Return a parser that will recognize the grammar:
	 * 
	 *    Section Name = Word
	 *
	 */
	protected Parser SectionName() {
		Sequence s = new Sequence();
		s.add(new Word().setAssembler(new SectionNameAssembler()));
		s.add(new CaselessLiteral("section") );
		s.add(new Symbol('.').discard());	

		return s;
	}
	
	/*
	 * Return a parser that will recognise the grammar:
	 * 
	 *    working-storage section
	 *
	 */
	protected Parser DateWritten() {
		Sequence s = new Sequence();
		s.add(new CaselessLiteral("date-written") );
		s.add(new Symbol('.').discard());
		s.add(new Num());
		s.add(new Symbol('-').discard());

		//This next Word actually contains month and year (which are extracted in DateAssembler
		s.add(new Word());
		s.add(new Symbol('-').discard());
		s.add(new Word().discard());
		s.add(new Symbol('.').discard());
		s.setAssembler(new DateAssembler());
		return s;
	}


	/**
	 * Return the primary parser for this class -- cobol().
	 *
	 * @return the primary parser for this class -- cobol()
	 */
	public static Parser start() {
		return new CobolParser().cobol();
	}

	/**
	 * Returns a tokenizer that does not allow spaces to appear inside
	 * the "words" that identify cobol's grammar.
	 *
	 * @return a tokenizer that does not allow spaces to appear inside
	 * the "words" that identify cobol grammar.
	 */
	public static Tokenizer tokenizer() {
		Tokenizer t = new Tokenizer();
		t.wordState().setWordChars(' ', ' ', false);
		return t;
	}
	/**
	 * Return a parser that will recognize the grammar:
	 * 
	 * 	<line number> <contstant name> "value" <constant value>.
	 * 
	 */
	protected Parser constantValue() {
		//System.out.println("constantValue()");
		Sequence s = new Sequence();
		s.add(new Num() );
		s.add(new Word() );
		s.add(new CaselessLiteral("value") );
		s.add(new Num() );
		s.setAssembler(new ConstantValueAssembler());
		return s;
	}
	
	/*
	* Return a parser that will recognize the grammar:
	* 
	* ***--- comment text
	*
	*/
	protected Parser CommentLine() {
		//System.out.println("commentLine()");
		Sequence s = new Sequence();
		Repetition r = new Repetition(new Word());
		s.add(new Symbol("*"));
		s.add(new Symbol("*"));
		s.add(new Symbol("*"));
		s.add(new Symbol("-"));
		s.add(new Symbol("-"));
		s.add(new Symbol("-"));
		// Comment can include a repetition of words, hence repetition of parsers used in this case
		s.add(new Word());
		s.add(r);
		s.setAssembler(new CommentLineAssembler());
		return s;
	}
	
	/**
	 * Return a parser that will recognize the grammar:
	 * 
	 * <display> strings with quotations || words
	 */
	protected Parser Display() {
		Sequence s = new Sequence();
		Alternation a = new Alternation();
		Repetition r = new Repetition(a);
		s.add(new CaselessLiteral("display"));
		a.add(new QuotedString());
		a.add(new Word());
		s.add(r);
		s.setAssembler(new DisplayAssembler());
		return s;
	}
	
	/**
	 * Returns a parser that will recognize the grammar:
	 * 
	 * <Move> <initial position> "to" <final position>
	 * 
	 */
	protected Parser Move() {
		Sequence s = new Sequence();
		Alternation a  = new Alternation();
		s.add(new CaselessLiteral("move"));
		a.add(new Word());
		a.add(new Num());
		
		// Second sequence to parse
		Sequence se = new Sequence();
		se.add(new Symbol("("));
		se.add(new Word());
		se.add(new Symbol(")"));
		a.add(se);
		Repetition r = new Repetition(a);
		s.add(r);
		s.add(new CaselessLiteral("to"));
		Alternation al  = new Alternation();
		al.add(new Word());
		
		// Third sequence to parse
		Sequence seq = new Sequence();
		seq.add(new Symbol("("));
		seq.add(new Word());	
		seq.add(new Symbol(":"));
		seq.add(new Num());	
		seq.add(new Symbol(")"));
		al.add(seq);
		Repetition re = new Repetition(al);
		s.add(re);
		s.setAssembler(new MoveAssembler());
		return s;
	}
	
	/**
	 * Returns a parser that will recognize the grammar:
	 * 
	 * <call> <call identifier> "using" <variable> <value>
	 */
	protected Parser Call() {
		Sequence s = new Sequence();
		s.add(new CaselessLiteral("call"));
		s.add(new QuotedString());
		s.add(new CaselessLiteral("using"));
		s.add(new Word());
		
		// Second sequence
		Sequence se = new Sequence();
		se.add(new Symbol(","));
		se.add(new CaselessLiteral("value"));
		se.add(new Num());
		Alternation a = new Alternation(se);
		
		a.add(new QuotedString());
		s.add(a);
		s.setAssembler(new CallAssembler());
		return s;
		
	}
}
