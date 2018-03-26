package symbols;
/**
 * 用于分辨不同的终结符和非终结符。其中终结符部分用于优先表。
 */
public class Type {
      /**
       * 非终结符
       */
      public final static int
            Expr=300,
            ArithExpr=301,
            BoolExpr=302,
            UnaryFunc=303,
            VariablFunc=304,
            ArithExprList=305;
      /**
       * 终结符
       */
      public final static int
            num = 0,
            bool = 1,
            addminus=2,
            muldiv=3,
            neg=4,
            power=5,
            func=6,
            lp=7,
            comma=8,
            rp=9,
            relation=10,
            not=11,
            and=12,
            or=13,
            qm=14,
            colon=15,
            dollar=16;
}