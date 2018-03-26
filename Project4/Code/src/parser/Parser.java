
package parser;
import exceptions.*;
import scanner.*;
import symbols.*;
import java.util.*;
import java.io.*;


public class Parser {
    MyScanner sc = new MyScanner();
    Token token;
    Terminal lookahead;
    ArrayList<Expr> stack = new ArrayList<Expr>();
    String Input;
    public Parser(String in)
    {
        Input=in;
    }
    Terminal getTopMostTerminal()
    {
        int post=stack.size()-1;
        while(true)
        {
            Expr e=stack.get(post);
            if(e instanceof Terminal) return (Terminal)e;
            else post--;
        }
    }
    double accept() throws TypeMismatchedException
    {
        Expr e=stack.get(stack.size()-1);
        if(e instanceof BoolExpr)
            throw new TypeMismatchedException();
        return ((ArithExpr)e).value;
    }
    /**
     * 实验指南上面的reduce()
     * @throws ExpressionException [description]
     */
    void reduce() throws ExpressionException
    {
        Terminal t=getTopMostTerminal();
        TerminalReduce tr = new TerminalReduce();
        tr.reduce(stack, t);
    }
    
    /**
     * [parse description]
     * @return 编译结果
     * @throws IOException              输入错误
     * @throws ExpressionException      表达式错误
     */
    public double parse() throws IOException,ExpressionException{
        if(Input.isEmpty()) throw new EmptyExpressionException();
        stack.add(new Terminal());      // put a $ in the stack
        ByteArrayInputStream bin=new ByteArrayInputStream(Input.getBytes());
        System.setIn(bin);
        token=sc.scan();
        lookahead=new Terminal(token);
        while(true)
        {
            Terminal topMostTerminal=getTopMostTerminal();
            switch(OppTable.table[topMostTerminal.type][lookahead.type])
            {
                case 2: // accept
                    System.setIn(new FileInputStream(FileDescriptor.in));
                    return accept();
                case 1: //reduce
                    reduce(); break;
                case 0: // shift
                    stack.add(lookahead); 
                    token=sc.scan();
                    lookahead=new Terminal(token);
                    break;
                case -1:
                    throw new MissingOperatorException();
                case -2:
                    throw new MissingRightParenthesisException();
                case -3:
                    throw new MissingLeftParenthesisException();
                case -4:
                    throw new FunctionCallException();
                case -5:
                    throw new TypeMismatchedException();
                case -6:
                    throw new MissingOperandException();
                case -7:
                    throw new TrinaryOperationException();
            }
        }
    }
    
}