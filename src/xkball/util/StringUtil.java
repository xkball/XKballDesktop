package xkball.util;

import sun.font.FontDesignMetrics;

import java.awt.*;

public class StringUtil {
    public static int getWordX(Font font,String s){
        
        FontMetrics metrics = FontDesignMetrics.getMetrics(font);
        return metrics.stringWidth(s);
    }
}
