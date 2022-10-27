/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimpleLexer;
import java.io.*;
import java.util.*;

public class lexer {
	public static int line = 1;
	char peek = ' ';
	Hashtable words = new Hashtable();

	void reserve(Word w) {
		words.put(w.lexeme, w);
	}

	public lexer() {
		reserve(new Word("if", Tag.IF));
		reserve(new Word("else", Tag.ELSE));
		reserve(new Word("while", Tag.WHILE));
		reserve(new Word("do", Tag.DO));
		reserve(new Word("for", Tag.FOR));
		reserve(new Word("main", Tag.MAIN));
		reserve(Word.True);
		reserve(Word.False);
		reserve(Type.Int);
		reserve(Type.Char);
		reserve(Type.Bool);
		reserve(Type.Float);
	}

	void readch() throws IOException {
		peek = (char) System.in.read();
	}

	boolean readch(char c) throws IOException {
		readch();
		if (peek != c)
			return false;
		peek = ' ';
		return true;
	}

	public Token scan() throws IOException, LexError {
		for (;; readch()) {
			if (peek == ' ' || peek == '\t' || peek == '\r')
				continue;
			else if (peek == '\n')
				line++;
			else
				break;
		}
		switch (peek) {
		case '&':
			if (readch('&'))
				return Word.and;
			else
                            break;
				//return new Token('&');
		case '|':
			if (readch('|'))
				return Word.or;
			//else
				//return new Token('|');
		case '>':
			if (readch('='))
				return Word.ge;
			else
                            return new Token('>');

		case '<':
			if (readch('='))
				return Word.le;
                        else if (peek == '>')
                        { peek = ' ';
                            return Word.ne;  
                               }
                                
			else                        
				return new Token('<');
                case '=':
			if (readch('='))
				return Word.eq;
			else
				return new Token('=');
                          
		
		}
		if (Character.isDigit(peek)) {
			int v = 0;
			do {
				v = 10 * v + Character.digit(peek, 10);
				readch();
			} while (Character.isDigit(peek));
			if (Character.isLetter(peek))
				error(0);
			if (peek != '.')
				return new Num(v);
			float x = v;
			float d = 10;
			for (;;) {
				readch();
				if (!Character.isDigit(peek))
					break;
				x += Character.digit(peek, 10) / d;
				d *= 10;
			}
			if (Character.isLetter(peek))
				error(0);
			return new Real(x);
		}
		if (Character.isLetter(peek)) {
			StringBuilder b = new StringBuilder();
			do {
				b.append(peek);
				readch();
			} while (Character.isLetterOrDigit(peek));
			String s = b.toString();
			Word w = (Word) words.get(s);
			if (w != null)
				return w;
			w = new Word(s, Tag.ID);
			words.put(s, w);
			return w;
		}
		Token tok = new Token(peek);
		peek = ' ';
		return tok;
	}

	public void error(int type) throws IOException, LexError {
		while (Character.isLetterOrDigit(peek))
			readch();
		throw new LexError("Error:" + errorInfo[type] + "at line " + line);
	}

	String[] errorInfo = { "Illegal ID  ", "Illegal Symbol" };
}
