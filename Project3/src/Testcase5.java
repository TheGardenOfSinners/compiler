import java.util.*;
import java.io.*;
import java.lang.*;

public class Testcase5 {
	private static final int TIMES = 100;
	private static final String INFIXPATH = "./../testcases/tc-005.infix";
	private static final String POSTFIXPATH = "./../testcases/tc-005.postfix";
	public static void main(String[] args) throws IOException {
		int a = TIMES;
		
		try {
			FileOutputStream fos = new FileOutputStream(INFIXPATH);
    		DataOutputStream dos = new DataOutputStream(fos);
    		dos.writeBytes("0");
			while((a--) > 0) {
				for(int i = 0;i < 10; i++) {
					if(i%2==0)
						dos.writeBytes("-");
					else
						dos.writeBytes("+");
					String s = "" + i;
					dos.writeBytes(s);
				}
			}
			dos.writeBytes("\n");
    		dos.close();
    		fos.close();
    		fos = new FileOutputStream(POSTFIXPATH);
    		dos = new DataOutputStream(fos);
    		dos.writeBytes("0");
    		a = TIMES;
    		while((a--) > 0) {
				for(int i = 0;i < 10; i++) {
					String s = "" + i;
					dos.writeBytes(s);
					if(i%2==0)
						dos.writeBytes("-");
					else
						dos.writeBytes("+");
				}
			}
			dos.writeBytes("\n");
			dos.close();
    		fos.close();
		} catch (Exception e) {
			System.out.println("write in Error");
		}

		
	}
}