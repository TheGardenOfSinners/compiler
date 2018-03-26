package symbols;

/**
 * 非终结符，是表达式的一种
 */
public abstract class NonTerminal extends Expr{
    /**
     * 非终结符的默认构造函数
     */
    public NonTerminal(){type=Type.Expr;}
    /**
     * 非终结符的构造函数
     * @param  t  Token
     * @param  tp 类型的标识
     */
    public NonTerminal(Token t,int tp)
    {
        super(t);
        type=tp;
    }
}