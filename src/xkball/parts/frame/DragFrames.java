package xkball.parts.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class DragFrames implements MouseListener, MouseMotionListener {
    
    private int ox;
    private int oy;
    private int tx;
    private int ty;
    private JFrame frame;
    
    public DragFrames(JFrame frame){
        this.frame = frame;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        //tx = (int) p.getX();
        //ty = (int) p.getY();
        ox = (int) p.getX();
        oy = (int) p.getY();
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        Point p = e.getPoint();
        Rectangle r = frame.getBounds();
        
        int wx = (int) r.getX();
        int wy = (int) r.getY();
        int x = (int) p.getX();
        int y = (int) p.getY();
        tx = x-ox;
        ty = y-oy;
        
        frame.setLocation(wx+tx,wy+ty);
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
    
    }
}
