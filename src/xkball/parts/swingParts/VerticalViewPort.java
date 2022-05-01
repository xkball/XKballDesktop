package xkball.parts.swingParts;

import xkball.util.exceptions.InitFailureException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * 一定，一定，一定
 * <p>
 * 要记得
 * TMD
 * <p>
 * 实时设置（改变） view 属性的
 * PreferredSize
 */
public class VerticalViewPort extends JViewport {
    private int speed=0;
    private int nowPosition = 0;
    
    public VerticalViewPort(){
        this(8,null,0);
    }
    
    public VerticalViewPort(int speed){
        this(speed,null,0);
    }
    
    public VerticalViewPort(JComponent component){
        this(8,component,0);
    }
    
    public VerticalViewPort(int speed,JComponent component,int x){
        this.setSpeed(speed);
        this.setScrollMode(BLIT_SCROLL_MODE);
        this.setView(component);
        this.addMouseWheelListener(new WheelView(this,x));
    }
    
    
    
    public int getSpeed() {
        return speed;
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public static class WheelView implements MouseWheelListener{
        private int max_r = 0;
        private final VerticalViewPort v;
        public WheelView(VerticalViewPort v){
            this.v = v;
        }
        public WheelView(VerticalViewPort v,int x){
            this.v = v;
            this.max_r = x;
        }
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            int max = 1000;
            if(v.isViewSizeSet){
                max = v.getView().getHeight()-v.getHeight();
            }
            else {
                throw new InitFailureException("面板未设置显示大小");
            }
            int i = e.getWheelRotation();
        
            int inner = v.nowPosition+i*v.speed;
            int o1 = Math.max(0,inner);
            v.nowPosition = Math.min(o1,max);
            v.setViewPosition(new Point(0,v.nowPosition));
        }
    }
}
