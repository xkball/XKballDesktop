package xkball.parts.SwingParts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class VerticalViewPort extends JViewport implements MouseWheelListener {
    private int speed=0;
    private int nowPosition = 0;
    
    public VerticalViewPort(){
        this(8,null);
    }
    
    public VerticalViewPort(int speed){
        this(speed,null);
    }
    
    public VerticalViewPort(JComponent component){
        this(8,component);
    }
    
    public VerticalViewPort(int speed,JComponent component){
        this.setSpeed(speed);
        this.setScrollMode(BLIT_SCROLL_MODE);
        this.setView(component);
    }
    
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int max = 500;
        if(isViewSizeSet){
            max = Math.max(500,getViewSize().height);
        }
        int i = e.getWheelRotation();
    
        int inner = nowPosition+i*speed;
        int o1 = Math.max(0,inner);
        nowPosition = Math.min(o1,max);
        super.setViewPosition(new Point(0,nowPosition));
    }
    
    public int getSpeed() {
        return speed;
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
