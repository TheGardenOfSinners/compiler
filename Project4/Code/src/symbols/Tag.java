package symbols;

/**
 * 标签类，给每个token设置一个字符标签，方便分辨token，只是记号并无实际意义，如小于等于为'['
 */
public class Tag {
    /**
     * 给每个token设置一个字符标签，方便分辨token，只是记号并无实际意义，如小于等于为'['。
     */
    public final static int
        ADD='+', AND='&',
        COS='c', COLON=':', COMMA=',',
        DIVIDE='/',DOLLAR='$',
        EQ='=',
        FALSE='F',
        GE=']', GT='>',
        LE='[', LT='<', LP='(',
        MAX='a', MIN='i', MINUS='-', MULTIPLY='*',
        NE='N', NEG='n', NOT='!', NUM='d',
        OR='|',
        POWER='^',
        QM='?',
        RP=')',
        SIN='s',
        TRUE='T';
}