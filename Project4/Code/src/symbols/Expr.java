package symbols;

/**
 * 表达式
 */
public class Expr extends MySymbols{
    /**
     * 这个表达式的token标签
     */
    public Token token;
    /**
     * 这个表达式的类型
     */
    public int type;
    /**
     * 默认构造函数
     */
    Expr(){token=null;}
    /**
     * 传入标签的构造函数
     */
    Expr(int t)
    {
        this.tag=t;
        this.type=Type.Expr;
        token=null;
    }
    /**
     * 传入Token的构造函数
     */
    Expr(Token t)
    {
        this.tag=t.tag;
        token=t;
        this.type=Type.Expr;
    }
}
