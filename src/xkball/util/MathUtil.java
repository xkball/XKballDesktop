package xkball.util;

import java.util.Random;

public class MathUtil {
    
    public static int randomPlus(int a, int b)//随机的加减
    {
        Random r = new Random();
        if(r.nextBoolean())
        {
            return a+b;
        }
        else {return a-b;}
    }
    
    public static int becomeColor(int a)//（暂时不能）将任意数字变成颜色用的数值
    {
        
        if(a<0)
        {return (255+a);}
        else if(a>255)
        {return (a-255);}
        else
        {return a;}
    }
    
    public static int getRandomNumberWithinARange(int from,int to){
        if(to>=from){
            int range = to-from;
            return from+((int)(Math.random()*(range+1)));
        }
        else {
            throw new ArithmeticException("范围有误");
        }
    }
    
}
