package scanner;
import symbols.*;
import java.io.*;
import java.util.*;
import exceptions.*;


public class MyScanner {
    /**
     * 一个预期字符，初始为空格，因为token之间可以加任意空格，若是空格就跳过<br>
     * 但是遇到连续的单词，如max，true等等，则预期字符从空格变为其预期字符
     */
    char peek=' ';
    /**
     * 上一个token的tag
     */
    int lastTokenTag=0;
    /**
     * 储存函数名的哈希表
     */
    Hashtable<String,Integer> wordTable = new Hashtable<String,Integer>();
    /**
     * 构造函数，加入所有英文单词
     */
    public MyScanner()
    {
        wordTable.put("sin",new Integer(Tag.SIN));
        wordTable.put("cos",new Integer(Tag.COS));
        wordTable.put("max",new Integer(Tag.MAX));
        wordTable.put("min",new Integer(Tag.MIN));
        wordTable.put("true",new Integer(Tag.TRUE));
        wordTable.put("false",new Integer(Tag.FALSE));
    }
    /**
     * 读取下一个字母，并把大写转化成小写
     * @throws IOException
     */
    void readch() throws IOException
    {
        peek = (Character.toLowerCase((char)System.in.read()));
        
    }
    /**
     * 预读取下一个字母，看看是否与输入的c匹配。
     * @param  c           [description]
     * @return             [description]
     * @throws IOException 
     */
    boolean readch(char c) throws IOException
    {
        readch();
        if(peek != c) return false;
        peek = ' ';
        return true;
    }
    /**
     * 扫描输入串，输出token
     * @return 扫描出的token
     * @throws IOException
     * @throws LexicalException
     */
    public Token scan() throws IOException,LexicalException
    {
        while(peek==' '){
            readch();
        }
        switch(peek)
        {
            case '+':
                lastTokenTag=Tag.ADD; readch();
                return new Token(Tag.ADD);
            case '*':
                lastTokenTag=Tag.MULTIPLY; readch();
                return new Token(Tag.MULTIPLY);
            case '/':
                lastTokenTag=Tag.DIVIDE; readch();
                return new Token(Tag.DIVIDE);
            case '^':
                lastTokenTag=Tag.POWER; readch();
                return new Token(Tag.POWER);
            case '(':
                lastTokenTag=Tag.LP; readch();
                return new Token(Tag.LP);
            case ',':
                lastTokenTag=Tag.COMMA; readch();
                return new Token(Tag.COMMA);
            case ')':
                lastTokenTag=Tag.RP; readch();
                return new Token(Tag.RP);
            case '!':
                lastTokenTag=Tag.NOT; readch();
                return new Token(Tag.NOT);
            case '&':
                lastTokenTag=Tag.AND; readch();
                return new Token(Tag.AND);
            case '|':
                lastTokenTag=Tag.OR; readch();
                return new Token(Tag.OR);
            case '?':
                lastTokenTag=Tag.QM; readch();
                return new Token(Tag.QM);
            case ':':
                lastTokenTag=Tag.COLON; readch();
                return new Token(Tag.COLON);
            case '=':
                lastTokenTag=Tag.EQ; readch();
                return new Token(Tag.EQ);
            case '<':
                if(readch('=')) {lastTokenTag=Tag.LE;return new Token(Tag.LE); }
                else if(peek=='>'){lastTokenTag=Tag.NE; return new Token(Tag.NE);}
                else{lastTokenTag=Tag.LT; return new Token(Tag.LT);}
            case '>':
                if(readch('=')){lastTokenTag=Tag.GE; return new Token(Tag.GE);}
                else{lastTokenTag=Tag.GT; return new Token(Tag.GT);}
            case '-':
                readch();
                if(lastTokenTag==Tag.NUM || lastTokenTag==Tag.RP)
                {
                    lastTokenTag=Tag.MINUS;return new Token(Tag.MINUS);
                }
                else{lastTokenTag=Tag.NEG; return new Token(Tag.NEG);}
            case ((char)-1):
                lastTokenTag=Tag.DOLLAR;
                return new Token(Tag.DOLLAR);
        }
        if(Character.isDigit(peek))
        {
            int value = 0; double dv=0, d=10;
            do{
                value = 10*value+Character.digit(peek, 10);
                readch();
            }while(Character.isDigit(peek));
            dv=value;
            if(peek=='.')
            {
                while(true)
                {
                    readch();
                    if(!Character.isDigit(peek)) break;
                    dv=dv+Character.digit(peek, 10)/d;
                    d*=10;
                }
                if(d==10) throw new IllegalDecimalException(); //'.'followed by 'e'
            }
            if(peek=='E' || peek=='e')
            {
                int power=0;
                boolean minus=false;
                readch();
                if(peek=='-') {minus=true; readch();}
                else if(peek=='+'){readch();}
                if(!Character.isDigit(peek)) throw new IllegalDecimalException();
                do{
                    power = 10*power+Character.digit(peek, 10);
                    readch();
                }while(Character.isDigit(peek));
                if(minus)
                    for(int i=0;i<power;i++) dv/=10;
                else
                    for(int i=0;i<power;i++) dv*=10;
                if(peek == '.')
                    throw new IllegalDecimalException();
            }
            lastTokenTag=Tag.NUM;
            return new Num(dv);
        }
        if(Character.isLetter(peek))
        {
            StringBuilder sb= new StringBuilder();
            do{
                sb.append(peek);
                readch();
            }while(Character.isLetter(peek));
            Integer w = wordTable.get(sb.toString());
            if(w!=null){lastTokenTag=w.intValue(); return new Token(w.intValue());}
            else throw new IllegalIdentifierException();
        }
        throw new IllegalSymbolException();
    }
}