import java.io.*;
import java.util.*;

class Parser {
	/**
	 * lookahead  
	 */
	static int lookahead;
	/**
	 * count the amount of error 
	 */
	private int errorNum;
	/**
	 * record the input.
	 */
	private String input;
	/**
	 * record the output.
	 */
	private String output;
	/**
	 * record the position of the error.
	 */
	private String errorPos;
	/**
	 * if in wrongState,it will not add " " to errorPos
	 */
	private boolean wrongState;
	/**
	 * record the error type.
	 */
	private ArrayList<String> errorList;

	/**
	 * initial the errorNum = 0 and start to lookahead.
	 * @return 
	 * @throws IOException   throws IOException if IO error;
	 */
	public Parser() throws IOException {
		lookahead = System.in.read();
		errorNum = 0;
		input = "";
		output = "";
		errorPos = "";
		errorList = new ArrayList<String>();
		wrongState = false;
	}

	/**
	 * deal with the input. 
	 * @throws IOException throws IOException if IO error;
	 */
	void expr() throws IOException {
		input = input+(char)lookahead;
		term();
		rest();
		printPostfix();
	}

	/**
	 * deal with the +and-.
	 * @throws IOException throws IOException if IO error;
	 */
	void rest() throws IOException {
		while(true) {
			if (lookahead == '+') {
				if(wrongState == false) 
					errorPos = errorPos+(char)' ';
				else 
					wrongState = false;
				match('+');
				term();
				if(errorNum == 0) {
					output = output + (char)'+';
				}
			} else if (lookahead == '-') {
				if(wrongState == false) 
					errorPos = errorPos+(char)' ';
				else 
					wrongState = false;
				match('-');
				term();
				if(errorNum == 0) {
					output = output + (char)'-';
				}
			} else if(lookahead == 13){
				break;
			} else {
				errorNum++;
				if(Character.isDigit((char)lookahead)) {
                    errorList.add("Miss a operator!!");
                    errorPos = errorPos + (char)'^';
                    wrongState = true;
				} else {
					errorList.add("Operator don't exist!!");
                    errorPos = errorPos + (char)'^';
                    match(lookahead);
				}
				term();
			}
		}
		
	}

	/**
	 * deal with the digit.And check the lookahead if its digit.
	 * @throws IOException
	 */
	void term() throws IOException {
		if (Character.isDigit((char)lookahead)) {
			if(errorNum == 0) {
				output = output + (char)lookahead;
			}
			if(wrongState == false) 
				errorPos = errorPos+(char)' ';
			else 
				wrongState = false;
			match(lookahead);
		} else if(lookahead == '+' || lookahead == '-') {
			errorNum++;
			errorPos = errorPos + (char)'^';
			errorList.add("Miss a digit!!");
			wrongState = true;
		} else {
			if(wrongState == false) {
				errorNum++;
				errorPos = errorPos + (char)'^';
				errorList.add("No this digit!!");
				match(lookahead);
			}
			
		}
	}

	/**
	 * deal with the digit.And check the lookahead if its digit.
	 * @throws IOException
	 */
	void match(int t) throws IOException {
		if (lookahead == t) {
			lookahead = System.in.read();
			input = input + (char)lookahead;
		} else {
			 throw new Error("syntax error");
		}
	}
	

	/**
	 * printf the output if no Syntax ERROR.
	 * But printf the error if there are errors.
	 */
	void printPostfix() {
        System.out.println(output+"\n");
		for(int i = 0; i < errorNum; i++) {
			System.out.println("\nThere is an error:\"" + errorList.get(i) + "\" at:");
			System.out.println(input);
			int n = i;
			for(int j = 0; j < errorPos.length(); j++) {
				if(errorPos.charAt(j) != '^') {
					System.out.write(errorPos.charAt(j));
				} else {
					if(n == 0) {
						System.out.write(errorPos.charAt(j));
						break;
					} else {
						System.out.write(' ');
						n--;
					}
				}
			}
			System.out.println("\n");
		}
	}
}

public class Postfix {
	public static void main(String[] args) throws IOException {
		System.out.println("Input an infix expression and output its postfix notation:");
		long startTime=System.currentTimeMillis();
		Parser a = new Parser();
		a.expr();
		long endTime=System.currentTimeMillis();
		System.out.println("End of program.");
		float excTime=(float)(endTime-startTime);
		System.out.println("Using time:" + excTime + "msec.");
	}
}
