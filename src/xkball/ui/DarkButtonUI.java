package xkball.ui;

import sun.swing.SwingUtilities2;
import xkball.interfaces.IColorSetting;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicFileChooserUI;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class DarkButtonUI extends BasicButtonUI {
    private static final Color buttonColor1 = new Color(80,80,80);
    private static final Color buttonColor2 = new Color(50,50,50);
    private JButton button;
    public DarkButtonUI(JButton button){
        super();
        this.button=button;
        button.setForeground(IColorSetting.charsColor1);
    }
    
    @Override
    public void paint(Graphics g, JComponent c){
        
        Graphics2D g2 = (Graphics2D) g;
        int x = c.getWidth();
        int y = c.getHeight();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint p1 = new GradientPaint(0,0,new Color(0,0,0),0,y-1,new Color(100,100,100));
        GradientPaint p2 = new GradientPaint(0,1,new Color(0,0,0,50),0,y-3,new Color(255,255,255,100));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1F));
        Rectangle2D.Float rec = new Rectangle2D.Float(0,0,x-1,y-1);
        Shape clip = g2.getClip();
        g2.clip(rec);
        GradientPaint gp = new GradientPaint(0.0F,0.0F,buttonColor1,0.0F,y,buttonColor2);
        g2.setPaint(gp);
        g2.fillRect(0,0,x,y);
        g2.setClip(clip);
        g2.setPaint(p1);
        g2.drawRect(0,0,x-1,y-1);
        g2.setPaint(p2);
        g2.drawRect(1,1,x-3,y-3);
    
        AbstractButton b = (AbstractButton) c;
        ButtonModel model = b.getModel();
        super.paint(g,c);
    }
}
