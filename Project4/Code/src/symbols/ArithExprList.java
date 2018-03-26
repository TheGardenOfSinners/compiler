 package symbols;
/**
 * 算术表达式列表，用于最大最小值函数max和min<br>
 * 可以省略掉存储每个值，直接存储最大最小值
 */
public class ArithExprList extends NonTerminal{
    /**
     * 记录最大最小值
     */
    public double max,min;
    /**
     * 只有一个ArithExpr的构造函数
     * @param  ae 一个ArithExpr
     */
    public ArithExprList(ArithExpr ae)
    {
        this.type=Type.ArithExprList;
        max=min=ae.value;
    }
    /**
     * 一个已经有的的ArithExpr的list和一个新的ArithExpr的构造函数
     * @param  ae  新的ArithExpr
     * @param  ael 当前ArithExprList
     */
    public ArithExprList(ArithExpr ae, ArithExprList ael)
    {
        this.type=Type.ArithExprList;
        double v=ae.value;
        max=((v>ael.max)?v:ael.max);
        min=((v<ael.min)?v:ael.min);
    }
}
