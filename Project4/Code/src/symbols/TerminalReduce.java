package symbols;
import exceptions.*;
import java.util.*;
/**
 * 对终结符进行reduce
 */
public class TerminalReduce {
    /**
     * [reduce description]
     * @param  stack                   输入的栈
     * @param  e                       输入的是栈的最顶终结符
     * @return                         得到一个缩减后的表达式
     * @throws ExpressionException     表达式错误
     * @throws TypeMismatchedException 类型不匹配错误
     * @throws MissingOperandException 缺少符号错误
     * @throws DividedByZeroException  0作为除数的错误
     * @throws SyntacticException 词法错误
     */
    public Expr reduce(ArrayList<Expr> stack, Terminal e) throws ExpressionException, TypeMismatchedException, 
                                                          MissingOperandException, DividedByZeroException,
                                                          SyntacticException

    {
    	if(e.type==Type.num) {
    		int post=stack.size()-1;
	        Terminal t=(Terminal)stack.get(post);
	        ArithExpr a=new ArithExpr((Num)t.token);
	        stack.remove(post);
	        stack.add(a);
	        return a;
    	} else if(e.type==Type.bool) {
    		int post=stack.size()-1;
	        Terminal t=(Terminal)stack.get(post);
	        BoolExpr b=new BoolExpr(t.token);
	        stack.remove(post);
	        stack.add(b);
	        return b;
    	} else if(e.type == Type.addminus) {
    		int post=stack.size()-1;
	        Expr a=stack.get(post-2);
	        Expr o=stack.get(post-1);
	        Expr b=stack.get(post);
	        if((b.type==Type.addminus)) throw new MissingOperandException();
	        if(a instanceof BoolExpr)
	            throw new TypeMismatchedException();
	        else if(!(a instanceof ArithExpr))
	            throw new MissingOperandException();
	        ArithExpr ae;
	        if(o.tag==Tag.ADD)
	            ae=new ArithExpr(((ArithExpr)a).value+((ArithExpr)b).value);
	        else
	            ae=new ArithExpr(((ArithExpr)a).value-((ArithExpr)b).value);
	        stack.remove(post);
	        stack.remove(post-1);
	        stack.remove(post-2);
	        stack.add(ae);
	        return ae;
    	} else if(e.type == Type.muldiv) {
    		int post=stack.size()-1;
	        Expr a =stack.get(post-2);
	        Expr o = stack.get(post-1);
	        Expr b = stack.get(post);
	        if((b.type==Type.muldiv)) throw new MissingOperandException();
	        if(!(a instanceof ArithExpr)|| !(b instanceof ArithExpr))
	            throw new TypeMismatchedException();
	        ArithExpr ae;
	        if(o.tag==Tag.MULTIPLY)
	            ae=new ArithExpr(((ArithExpr)a).value * ((ArithExpr)b).value);
	        else if(((ArithExpr)b).value==0)
	            throw new DividedByZeroException();
	        else
	            ae=new ArithExpr(((ArithExpr)a).value / ((ArithExpr)b).value);
	        stack.remove(post);
	        stack.remove(post-1);
	        stack.remove(post-2);
	        stack.add(ae);
	        return ae;
    	} else if(e.type == Type.not) {
    		int post=stack.size()-1;
	        Expr a=stack.get(post);
	        if(!(a instanceof BoolExpr)) throw new TypeMismatchedException();
	        BoolExpr be = new BoolExpr(!((BoolExpr)a).value);
	        stack.remove(post);
	        stack.remove(post-1);
	        stack.add(be);
	        return be;
    	} else if(e.type == Type.neg) {
    		int post=stack.size()-1;
	        Expr a=stack.get(post);
	        if(!(a instanceof ArithExpr)) throw new TypeMismatchedException();
	        double v =((Num)a.token).value;
	        ArithExpr ae = new ArithExpr(-v);
	        stack.remove(post);
	        stack.remove(post-1);
	        stack.add(ae);
	        return ae;
    	} else if(e.type == Type.power) {
    		int post=stack.size()-1;
	        
	        Expr a=stack.get(post-2);
	        Expr o=stack.get(post-1);
	        Expr b=stack.get(post);
	        if((b.type==Type.power)) throw new MissingOperandException();
	        if(!(a instanceof ArithExpr) || !(b instanceof ArithExpr))
	            throw new TypeMismatchedException();
	        ArithExpr ae;
	        ae=new ArithExpr(Math.pow(((ArithExpr)a).value,((ArithExpr)b).value));
	        stack.remove(post);
	        stack.remove(post-1);
	        stack.remove(post-2);
	        stack.add(ae);
	        return ae;
	    } else if(e.type == Type.or ||e.type == Type.and) {
	    	int post=stack.size()-1;
	        Expr a=stack.get(post-2);
	        Expr o=stack.get(post-1);
	        Expr b=stack.get(post);
	        if((b.type==Type.and)||(b.type==Type.or)) throw new MissingOperandException();
	        if(!(a instanceof BoolExpr) || !(b instanceof BoolExpr))
	            throw new TypeMismatchedException();
	        BoolExpr be;
	        if(o.tag==Tag.AND)
	            be=new BoolExpr(((BoolExpr)a).value && ((BoolExpr)b).value);
	        else
	            be=new BoolExpr(((BoolExpr)a).value || ((BoolExpr)b).value);
	        stack.remove(post);
	        stack.remove(post-1);
	        stack.remove(post-2);
	        stack.add(be);
	        return be;
	    } else if(e.type == Type.relation) {
	    	int post=stack.size()-1;
	        Expr a=stack.get(post-2);
	        Expr o=stack.get(post-1);
	        Expr b=stack.get(post);
	        if((b.type==Type.relation)) throw new MissingOperandException();
	        if(!(a instanceof ArithExpr) || !(b instanceof ArithExpr))
	            throw new TypeMismatchedException();
	        BoolExpr be=null;
	        switch(o.tag)
	        {
	            case Tag.EQ:
	                be=new BoolExpr(((ArithExpr)a).value==((ArithExpr)b).value); break;
	            case Tag.NE:
	                be=new BoolExpr(((ArithExpr)a).value!=((ArithExpr)b).value); break;
	            case Tag.LE:
	                be=new BoolExpr(((ArithExpr)a).value<=((ArithExpr)b).value); break;
	            case Tag.LT:
	                be=new BoolExpr(((ArithExpr)a).value<((ArithExpr)b).value); break;
	            case Tag.GE:
	                be=new BoolExpr(((ArithExpr)a).value>=((ArithExpr)b).value); break;
	            case Tag.GT:
	                be=new BoolExpr(((ArithExpr)a).value>((ArithExpr)b).value); break;
	        }
	        stack.remove(post);
	        stack.remove(post-1);
	        stack.remove(post-2);
	        stack.add(be);
	        return be;
	    } else if(e.type == Type.colon) {
	    	int post=stack.size()-1;  // be?ae:ae
	        Expr a=stack.get(post);   // ae
	        Expr b=stack.get(post-1);// :
	        Expr c=stack.get(post-2);// ae
	        Expr d=stack.get(post-3);// ?
	        Expr e1=stack.get(post-4);//be
	        
	        if(!(a instanceof ArithExpr) || !(c instanceof ArithExpr)|| !(e1 instanceof BoolExpr))
	            throw new TypeMismatchedException();
	        if(!(d.type==Type.qm))
	            throw new TrinaryOperationException();
	        if(((BoolExpr)e1).value==true)
	        {
	            stack.remove(post);
	            stack.remove(post-1);
	            stack.remove(post-3);
	            stack.remove(post-4);
	            return c;
	        }
	        else
	        {
	            stack.remove(post-1);
	            stack.remove(post-2);
	            stack.remove(post-3);
	            stack.remove(post-4);
	            return a;
	        }
	    } else if(e.type == Type.rp) {
	    	int post=stack.size()-1;
	        Expr a= stack.get(post);  // )
	        Expr o= stack.get(post-1); //bool or ae or ael //error if operand
	        Expr b= stack.get(post-2); // ( or ,
	        Expr c= stack.get(post-3);//$ or func or ArithExpr
	        
	        if(o.type==Type.lp || o.type==Type.comma)
	        {
	            throw new MissingOperandException();
	        }
	        else if(o instanceof BoolExpr) // (be) to be
	        {
	            stack.remove(post);
	            stack.remove(post-2);
	            return stack.get(post-2);
	        }
	        else if(o instanceof ArithExprList)
	        {
	            if(!(c instanceof ArithExpr)) throw new TypeMismatchedException();
	            ArithExprList ael=new ArithExprList((ArithExpr)c,(ArithExprList)o);
	            Expr d= stack.get(post-4);
	            Expr f=stack.get(post-5);
	            ArithExpr ae=null;
	            if(d.type==Type.lp && f.type==Type.func) // max(ae,ael) to ae
	            {
	                switch(f.tag)
	                {
	                    case Tag.MAX:
	                        ae=new ArithExpr(ael.max); break;
	                    case Tag.MIN:
	                        ae=new ArithExpr(ael.min); break;
	                    default:
	                        throw new FunctionCallException();
	                }
	                stack.remove(post);  //)
	                stack.remove(post-1);//ael
	                stack.remove(post-2);//,
	                stack.remove(post-3);//ae
	                stack.remove(post-4);//(
	                stack.remove(post-5);//max min
	                stack.add(ae);
	                return ae;
	            }
	            else                          // ae,ael) to ael)
	            {
	                stack.remove(post-1);
	                stack.remove(post-2);
	                stack.set(post-3, ael);
	                return ael;
	            }
	        }
	        else if(o instanceof ArithExpr)
	        {
	            if(b.type==Type.comma) // ae,ae) to ae,ael)
	            {
	                ArithExprList ael = new ArithExprList((ArithExpr)o);
	                stack.set(post-1, ael);
	                return ael;
	            }
	            else if(c.type==Type.func) //sin(ae)
	            {
	                if(!(o instanceof ArithExpr)) throw new TypeMismatchedException();
	                ArithExpr ae=null;
	                switch(c.tag)
	                {
	                    case Tag.SIN:
	                        ae=new ArithExpr(Math.sin(((ArithExpr)o).value)); break;
	                    case Tag.COS:
	                        ae=new ArithExpr(Math.cos(((ArithExpr)o).value)); break;
	                    default:
	                        throw new MissingOperandException();
	                }
	                stack.remove(post);
	                stack.remove(post-1);
	                stack.remove(post-2);
	                stack.remove(post-3);
	                stack.add(ae);
	                return ae;
	            }
	            else //(ae) to ae
	            {
	                stack.remove(post);
	                stack.remove(post-2);
	                return stack.get(post-2);
	            }
	        }
	        throw new SyntacticException();
	    } else throw new SyntacticException();
    } 
}