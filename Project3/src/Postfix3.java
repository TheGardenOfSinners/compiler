import java.io.*;

class Parser3 {
	static int lookahead;

	public Parser3() throws IOException {
		lookahead = System.in.read();
	}

	void expr() throws IOException {
		term();
		rest();
	}

	void rest() throws IOException {
		while(lookahead == '+' || lookahead == '-') {
			if (lookahead == '+') {
				match('+');
				term();
				System.out.write('+');
			} else if (lookahead == '-') {
				match('-');
				term();
				System.out.write('-');
			}
		}
		
		
	}

	void term() throws IOException {
		if (Character.isDigit((char)lookahead)) {
			System.out.write((char)lookahead);
			match(lookahead);
		} else  throw new Error("syntax error");
	}

	void match(int t) throws IOException {
		if (lookahead == t)  lookahead = System.in.read();
		else  throw new Error("syntax error");
	}
}

public class Postfix3 {
	public static void main(String[] args) throws IOException {
		System.out.println("Input an infix expression and output its postfix notation:");
		long startTime=System.currentTimeMillis();
		new Parser3().expr();
		long endTime=System.currentTimeMillis();
		System.out.println("\nEnd of program.");
		float excTime=(float)(endTime-startTime);
		System.out.println("Using time:" + excTime + "msec.");
	}
}
