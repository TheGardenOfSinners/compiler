package parser;

/**
 * 与symbols包中的Type类定义的对应，0是num到16是$。用于发现报错和设定优先级
 */
public class OppTable {
    /**
     * 记录相邻的有终结符见对应的操作或报错
     */
    public final static int[][] table= new int[][]
    {
    // num,bool,+-,*/,-,^,fucn,(,,,),Relation,!,&,|,?,:,$
        
        {-1,-1, 1, 1, 1, 1, 1,-1, 1, 1, 1,-5, 1, 1, 1, 1, 1},
        {-1,-1,-5,-5,-5,-5,-1,-1,-5, 1,-5,-1, 1, 1, 1, 1, 1},
        { 0,-5, 1, 0, 0, 0, 0, 0, 1, 1, 1,-5,-5,-5,-5, 1, 1},
        { 0,-5, 1, 1, 0, 0, 0, 0, 1, 1, 1,-5,-5,-5,-5, 1, 1},
        { 0,-5, 1, 1, 0, 1, 0, 0, 1, 1, 1,-5,-5,-5,-5, 1, 1},
        { 0,-5, 1, 1, 0, 0, 0, 0, 1, 1, 1,-5,-5,-5,-5, 1, 1},
        {-3,-3,-3,-3,-3,-3,-3, 0,-3, 1,-3,-3,-3,-3,-3, 1,-3},
        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-2},
        { 0,-5, 0, 0, 0, 0, 0, 0, 0, 0,-5,-5,-5,-5, 0, 0,-2},
        {-1,-1, 1, 1, 1, 1,-1,-1, 1, 1, 1,-1, 1, 1, 1, 1, 1},
        { 0,-5, 0, 0, 0, 0, 0, 0,-5, 1, 1, 1, 1, 1, 1, 1, 1},
        { 0, 0,-5,-5,-5,-5,-5, 0,-5, 1, 0, 0, 1, 1, 1, 1, 1},
        { 0, 0,-5,-5,-5,-5,-5, 0,-5, 1, 0, 0, 1, 1, 1, 1, 1},
        { 0, 0,-5,-5,-5,-5,-5, 0,-5, 1, 0, 0, 0, 1, 1, 1, 1},
        { 0,-7, 0, 0, 0, 0, 0, 0,-7,-6, 0,-7,-7,-7, 0, 0,-7},
        { 0,-7, 0, 0, 0, 0, 0, 0, 1, 1, 0,-7,-7,-7, 1, 1, 1},
        { 0, 0, 0, 0, 0, 0, 0, 0,-3,-3, 0, 0, 0, 0, 0,-7, 2}
    };
    /* 0 for shift
     * 1 for reduce
     * 2 for accept
     * -1 for MissingOperatorError
     * -2 for MissingRightParenthesisException
     * -3 for MissingLeftParenthesisException
     * -4 for FunctionCallException
     * -5 for TypeMismatchedException
     * -6 for MissingOperandError
     * -7 for TrinaryOperationError
     */
}