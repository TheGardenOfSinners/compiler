package symbols;

/**
 * 布尔表达式，非终结符之一。
 */
public class BoolExpr extends NonTerminal{
    /**
     * 标记这个Bool表达式的值
     */
    public boolean value;
    /**
     * 由一个Token构造Bool表达式
     * @param  t 传入的Token
     */
    public BoolExpr(Token t)
    {
        type=Type.BoolExpr;
        if(t.tag == Tag.TRUE){
            value=true;
            tag = Tag.TRUE;
        }
        else if(t.tag==Tag.FALSE) {
            value=false;
            tag = Tag.FALSE;
        }
    }
    /**
     * 由一个Boolean类型变量构造的Bool表达式
     * @param  t 传入的Boolean类型变量
     */
    public BoolExpr(boolean v)
    {
        value=v;
        type=Type.BoolExpr;
        tag=((value==true)?Tag.TRUE:Tag.FALSE);
    }
}