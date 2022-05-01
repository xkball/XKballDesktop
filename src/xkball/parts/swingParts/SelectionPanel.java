package xkball.parts.swingParts;

import xkball.interfaces.IColorSetting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

public class SelectionPanel extends JPanel  {
    
    private boolean isInside = false;
    private boolean haveBorder = true;
    private boolean isFill;
    private Shape[] shape1;
    private Color borderColor;
    
    
    public SelectionPanel(){
        this(IColorSetting.partitionLinesColor6);
    }
    
    public SelectionPanel(Dimension d){
        this(IColorSetting.partitionLinesColor6,false,d);
    }
    
    public SelectionPanel(Color c){
       this(c,false);
    }
    
    public SelectionPanel(Color c,boolean b){
        this(c,b,new Dimension(50,50));
    }
    
    public SelectionPanel(Color c,boolean b,Dimension d){
       this(c,b,d,null);
    }
    
    public SelectionPanel(Color c,boolean b,Dimension d,Shape[] s){
        this.addMouseListener(new innerMouseListener(this));
        this.setBorderColor(c);
        this.setFill(b);
        this.setSize(d);
        if(s!= null){
            shape1 = s;
        }
        else {
            shape1 = new Rectangle2D.Float[]{new Rectangle2D.Float(2,2,this.getWidth()-5,this.getHeight()-5)};
        }
        
    }
    
    /**
     *绘制外层边框
     */
    public void drawOutBorder(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        if(haveBorder){
            Shape rec1 = new Rectangle2D.Float(0,0,this.getWidth()-1,this.getHeight()-1);
            g2.setColor(borderColor);
            g2.draw(rec1);
        }
    }
    
    /**
     * 绘制内层边框等东西
     * 可以覆盖显示别的东西
     * 鼠标移入才显示
     */
    public void drawInnerBorder(Graphics g){
        drawInnerBorder(g,shape1);
    }
    public void drawInnerBorder(Graphics g,Shape[] shape2){
        Graphics2D g2 = (Graphics2D) g;
        if(isInside){
            g2.setColor(borderColor);
            for(Shape s : shape2){
                if(isFill){
                    g2.fill(s);
                }
                else {
                    g2.draw(s);
                }
            }
            
        }
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        this.drawOutBorder(g);
        this.drawInnerBorder(g);
    }
    
    
    
    private void setInside(boolean b) {
        isInside = b;
    }
    
    public boolean isInside() {
        return isInside;
    }
    
    public boolean isHaveBoard() {
        return haveBorder;
    }
    
    public void setHaveBoard(boolean haveBoard) {
        this.haveBorder = haveBoard;
    }
    
    public Color getBorderColor() {
        return borderColor;
    }
    
    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }
    
    public Shape[] getShape1() {
        return shape1;
    }
    
    public void setShape1(Shape[] shape1) {
        this.shape1 = shape1;
    }
    
    public boolean isFill() {
        return isFill;
    }
    
    public void setFill(boolean fill) {
        isFill = fill;
    }
    
    private static class innerMouseListener implements MouseListener{
        private SelectionPanel s;
        
        public innerMouseListener(SelectionPanel s){
            this.s = s;
        }
    
        @Override
        public void mouseClicked(MouseEvent e) {
        
        }
    
        @Override
        public void mousePressed(MouseEvent e) {
        
        }
    
        @Override
        public void mouseReleased(MouseEvent e) {
        
        }
    
        @Override
        public void mouseEntered(MouseEvent e) {
            s.setInside(true);
            s.repaint();
        }
    
        @Override
        public void mouseExited(MouseEvent e) {
            s.setInside(false);
            s.repaint();
        }
    }
}


