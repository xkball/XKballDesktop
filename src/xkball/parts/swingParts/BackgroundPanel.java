package xkball.parts.swingParts;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    
    public BackgroundPanel(int width,int height){
        this.setBounds(0,0,width,height);
        this.setOpaque(false);
    }
    
    public void paintBackgroundShape(Graphics g){
    
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        this.paintBackgroundShape(g);
    }
}
