/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimpleLexer;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		//if (args.length < 1){
		//	System.err.println("parameter error!!");return;
		//}
		PrintStream console = System.out;
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
				"test.in"));
		PrintStream out = new PrintStream(new BufferedOutputStream(
				new FileOutputStream("result.out"),4));
		System.setOut(out);
		System.setIn(in);
		lexer lex = new lexer();
		int count=0;
		do {
			try {
				
				System.out.print(lex.scan()+"\t");
				if(++count%3==0)System.out.println();
				out.flush();
			} catch (LexError e) {
				// TODO: handle exception
				System.err.println(e);
			}
		} while (System.in.available() > 0);
		out.close();
		System.setOut(console);
	}
}

class LexError extends Exception {
	public LexError() {
		// TODO Auto-generated constructor stub
	}

	public LexError(String msg) {
		super(msg);
	}
}
