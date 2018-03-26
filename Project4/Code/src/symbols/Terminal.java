package symbols;


public class Terminal extends Expr{
    /**
     * 终结符的构造函数，根据标签确定非终结符的类型用于
     * @param  t 传入的token
     */
    public Terminal(Token t)
    {
        super(t);
        switch(t.tag)
        {
            case Tag.ADD: case Tag.MINUS:
                type=Type.addminus; break;
            case Tag.MULTIPLY: case Tag.DIVIDE:
                type=Type.muldiv; break;
            case Tag.NUM:
                type=Type.num; break;
            case Tag.TRUE: case Tag.FALSE:
                type=Type.bool; break;
            case Tag.NEG:
                type=Type.neg; break;
            case Tag.POWER:
                type=Type.power; break;
            case Tag.LP:
                type=Type.lp; break;
            case Tag.RP:
                type=Type.rp; break;
            case Tag.COMMA:
                type=Type.comma; break;
            case Tag.EQ: case Tag.NE: case Tag.LE: case Tag.LT: case Tag.GE: case Tag.GT:
                type=Type.relation; break;
            case Tag.SIN: case Tag.COS: case Tag.MAX: case Tag.MIN:
                type=Type.func; break;
            case Tag.QM:
                type=Type.qm; break;
            case Tag.COLON:
                type=Type.colon; break;
            case Tag.NOT:
                type=Type.not; break;
            case Tag.AND:
                type=Type.and; break;
            case Tag.OR:
                type=Type.or; break;
            case Tag.DOLLAR:
                type=Type.dollar; break;
        }
    }
    /**
     * 默认构造函数
     */
    public Terminal()
    {
        tag=Tag.DOLLAR;
        type=Type.dollar;
    }
}