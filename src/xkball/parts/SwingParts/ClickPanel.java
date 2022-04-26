package xkball.parts.SwingParts;

import com.sun.deploy.panel.GeneralPanel;
import javafx.fxml.JavaFXBuilderFactory;
import xkball.interfaces.IColorSetting;
import xkball.parts.resourseloader.IResources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class ClickPanel extends JPanel  {
    private int state = 0;
    JPanel background;
    
    public ClickPanel(int state){
        this();
        this.setState(state);
    }
    
    public ClickPanel(int x,int y,int width,int height){
        this();
        this.setBounds(x,y,width,height);
        this.setSize(width,height);
    }
    
    public ClickPanel(){
        this.setOpaque(false);
        this.setLayout(null);
        //this.setBackground(new Color(0,0,0,0));
        //this.setSize(35,35);
        background = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                Graphics2D g2 = (Graphics2D) g;
                //Shape out = new Rectangle2D.Float(2, 2, this.getWidth() - 4, this.getHeight() - 4);
                Shape inner = new Rectangle2D.Float(4, 4, this.getWidth() - 8, this.getHeight() - 8);
                g2.setColor(IColorSetting.partitionLinesColor1);
                g2.setStroke(new BasicStroke(3F));
                g2.draw(inner);
               
            }
        };
        background.setOpaque(false);
        this.add(background);
//        background.setFocusable(false);
        background.setBounds(0,0,this.getWidth(),this.getHeight());

        this.addMouseListener(new ChangeState(this));
    }
    
   @Override
   public void setBounds(int x,int y,int width,int height){
        super.setBounds(x,y,width,height);
        //这玩意会递归。。。。NB
        //this.setSize(width,height);
        background.setSize(width,height);
   }
   
   @Override
   public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        Image i;
        switch (state){
            case 1:
                i = Toolkit.getDefaultToolkit().getImage(IResources.urlHookIcon);
                g2.drawImage(i,0,0,this.getWidth(),this.getHeight(),this);
                break;
            case 0:
                break;
            case -1:
                i = Toolkit.getDefaultToolkit().getImage(IResources.urlProngIcon);
                g2.drawImage(i,0,0,this.getWidth(),this.getHeight(),this);
        }
   }
    
    public int getState() {
        return state;
    }
    
    public void setState(int state) {
        if(state<2 && state>-2)
        this.state = state;
        this.repaint();
    }
    
    private class ChangeState implements MouseListener{
        private ClickPanel clickPanel;
        
        public ChangeState(ClickPanel clickPanel){
            this.clickPanel = clickPanel;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            int b = e.getButton();
            if(b == MouseEvent.BUTTON1 ){
                switch (clickPanel.getState()){
                    case 1:
                        clickPanel.setState(-1);
                        break;
                    case 0:
                        clickPanel.setState(1);
                        break;
                    case -1:
                        clickPanel.setState(0);
                        break;
                }
            }
            clickPanel.repaint();
        }
    
        @Override
        public void mousePressed(MouseEvent e) {
        
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
       
    }
    
}

