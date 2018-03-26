package symbols;

/**
 * 算术表达式
 */
public class ArithExpr extends NonTerminal{
    /**
     * 此算术表达式的值
     */
    public double value;
    /**
     * 运用一个Num类型的token来构造算术表达式，直接算出值
     */
    ArithExpr(Num n)
    {
        super(n,Type.ArithExpr);
        value=n.value;
    }
    /**
     * 运用传入值来构造算术表达式
     */
    ArithExpr(double v)
    {
        super(new Num(v),Type.ArithExpr);
        value=v;
    }
}