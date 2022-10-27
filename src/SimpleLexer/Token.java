/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimpleLexer;
class Tag {
	public final static int MAIN = 10, WHILE = 11, FOR = 12, DO = 13, ELSE = 14, IF = 15, BOOL=16, FLOAT=17, CHAR=18, INT=19, TRUE = 20, FALSE = 21,
			ID = 22, LE = 25, GE = 26, NE = 29, EQ = 30, NUMBER = 24, REAL = 23,
			AND = 41, OR = 42;
}

public class Token {
	public final int tag;
        public  int syn;
	public Token(int t) {
		tag = t;
	}
	public String toString() {
		String temp = "";
		switch (tag) {
		case '+':
                    syn=32;
                    temp = "运算符";
                    break;
		case '-':
                    syn=33;
                    temp = "运算符";
                    break;
		case '*':
                    syn=34;
                    temp = "运算符";
                    break;
		case '/':
                    syn=35;
                    temp = "运算符";
                    break;
		case '=':
                    syn=31;
                    temp = "运算符";
                    break;
		case '<':
                    syn=27;
                    temp = "运算符";
                    break;
		case '>':
		    		syn=28;
                    temp = "运算符";
                    break;
		case '(':
                    syn=36;
                    temp = "界符";
                    break;
		case ')':
                    syn=37;
                    temp = "界符";
                    break;
		case '{':
                     syn=39;
                    temp = "界符";
                    break;
		case '}':
                     syn=40;
                    temp = "界符";
                    break;
		case ';':
		    		syn=38;
                    temp = "界符";
                    break;
		}
                
                return "<种别码" + syn+","+ temp + (char) tag + ">";
	}
}

class Num extends Token {
	public final int value;

	public Num(int v) {
		super(Tag.NUMBER);
		value = v;
	}

	public String toString() {
		syn=24;
		return "<种别码" +syn+","+"整型常数"+value + ">";
	}
}
class Real extends Token {
	public final float value;

	public Real(float v) {
		super(Tag.REAL);
		value = v;
	}

	public String toString() {
	    syn=23;
           return "<种别码" +syn+","+"实型常数"+value + ">";
	}
}

class Word extends Token {
	public String lexeme = "";

	public Word(String s, int tag) {
		super(tag);
		lexeme = s;
	}

	public String toString() {
		String temp = "";
		switch (tag) {
		case Tag.MAIN:
		case Tag.WHILE:
		case Tag.DO:                 
		case Tag.IF:                 
		case Tag.ELSE:                  
		case Tag.FOR:
                	temp = "关键字";
			break;
		case Tag.ID:
			temp = "标识符";
			break;
		case Tag.TRUE:                   
		case Tag.FALSE:
			temp = "逻辑常量";
			break;
		case Tag.LE:                  
		case Tag.GE:   
		case Tag.NE:                  
		case Tag.EQ:
			temp = "关系运算符";
			break;
                case Tag.AND:                 
                case Tag.OR:
                    temp = "逻辑运算符";
			break;
		}
		return "<种别码" +tag+ ","+ temp  + lexeme + ">";
	}

	public static final Word and = new Word("&&", Tag.AND), or = new Word("||",
			Tag.OR), eq = new Word("==", Tag.EQ), ne = new Word("<>", Tag.NE),
			le = new Word("<=", Tag.LE), ge = new Word(">=", Tag.GE);

	public static final Word True = new Word("true", Tag.TRUE),
			False = new Word("false", Tag.FALSE);
}


class Type extends Word {
	public int width = 0;

	public Type(String s, int tag, int w) {
		super(s, tag);
		width = w;
	}

	public String toString() {
		return "<种别码"+tag +","+"基本类型"+ lexeme + ">";
	}

	public static final Type Int = new Type("int", Tag.INT, 4),
			Float = new Type("float", Tag.FLOAT, 8), Char = new Type("char",
					Tag.CHAR, 1), Bool = new Type("bool", Tag.BOOL, 1);

}