package symbols;

/**
 * 数字Token，数字表达式
 */
public class Num extends Token{
    /**
     * 这个token的值
     */
    public final double value;
    /**
     * 传入数字值的构造函数
     * @param  v 传入的数字
     */
    public Num(double v)
    {
        super(Tag.NUM);
        value=v;
    }
    
    @Override
    public String toString()
    {
        return String.valueOf(value);
    }
}