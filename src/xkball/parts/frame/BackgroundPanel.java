package xkball.parts.frame;

import xkball.interfaces.IColorSetting;
import xkball.parts.linkModule.IconPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class BackgroundPanel extends JPanel implements IColorSetting {
    
    public BackgroundPanel(){
        this.setBackground(backgroundColor);
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        paintModuleArea(g);
        paintBasicShape(g);
    }
    
    public void paintModuleArea(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        
        //分割线
        Shape recUp1 = new Rectangle2D.Float(0,350,375,2);
        Shape recUp2 = new Rectangle2D.Float(0,450,750,2);
        g2.setColor(partitionLinesColor4);
        g2.fill(recUp1);
        g2.fill(recUp2);
        
        Shape recDown1 = new Rectangle2D.Float(0,352,375,2);
        Shape recDown2 = new Rectangle2D.Float(0,452,750,2);
        g2.setColor(partitionLinesColor3);
        g2.fill(recDown1);
        g2.fill(recDown2);
        
        //组件的框
        Shape rec1 = new Rectangle2D.Float(30,32,308,308);
        Shape rec2 = new Rectangle2D.Float(30,364,308,76);
        Shape rec3 = new Rectangle2D.Float(375+37,32,308,408);
        Shape rec4 = new Rectangle2D.Float(30,454,308,this.getHeight()-454-21);
        Shape rec5 = new Rectangle2D.Float(375+37,454,308,this.getHeight()-454-21);
        g2.setColor(partitionLinesColor2);
        //g2.fill(rec1);
        g2.fill(rec2);
        //g2.fill(rec3);
        //g2.fill(rec4);
        //g2.fill(rec5);
        
        //组件边缘
        Shape line1 = new Line2D.Float(30,31,338,31);
        Shape line2 = new Line2D.Float(338,32,338,340);
        Shape line3 = new Line2D.Float(30,363,338,363);
        Shape line4 = new Line2D.Float(338,364,338,440);
        Shape line5 = new Line2D.Float(375+37,31,375+37+308,31);
        Shape line6 = new Line2D.Float(375+37+308,31,375+37+308,439);
        Shape line7 = new Line2D.Float(338,454,338,this.getHeight()-22);
        Shape line8 = new Line2D.Float(375+37+308,454,375+37+308,this.getHeight()-22);
        g2.setColor(partitionLinesColor5);
        g2.draw(line1);
        g2.draw(line2);
        g2.draw(line3);
        g2.draw(line4);
        g2.draw(line5);
        g2.draw(line6);
        g2.draw(line7);
        g2.draw(line8);
    }
    
    public void paintBasicShape(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        
        //中间矩形
        Shape rec1 = new Rectangle2D.Float(375-7,20,14,this.getHeight()-40);
        g2.setColor(rectanglesColor2);
        g2.fill(rec1);
        
        //中间虚线
        Shape line1 = new Line2D.Float(375,20,375,this.getHeight()-20);
        BasicStroke basicStroke1 = new BasicStroke(1,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,
                1.0F,new float[]{4F,4F},0);
        g2.setStroke(basicStroke1);
        g2.setColor(partitionLinesColor1);
        g2.draw(line1);
        g2.setStroke(new BasicStroke());
        
        //中间线条
        Shape line2 = new Line2D.Float(375-7,20,375-7,this.getHeight()-20);
        Shape line3 = new Line2D.Float(375+7,20,375+7,this.getHeight()-20);
        g2.setColor(partitionLinesColor1);
        g2.draw(line2);
        g2.draw(line3);
        
        //上下矩形
        /*
        Shape rec2 = new Rectangle2D.Float(350,0,50,20);
        Shape rec3 = new Rectangle2D.Float(350,this.getHeight()-20,50,20);
        g2.setColor(rectanglesColor1);
        g2.fill(rec2);
        g2.fill(rec3);
         */
        
        //上下线条
        Shape line4 = new Line2D.Float(0,20,750,20);
        Shape line5 = new Line2D.Float(0,this.getHeight()-20,750,this.getHeight()-20);
        g2.setColor(partitionLinesColor2);
        g2.draw(line4);
        g2.draw(line5);
        Shape line6 = new Line2D.Float(0,21,750,21);
        Shape line7 = new Line2D.Float(0,this.getHeight()-21,750,this.getHeight()-21);
        g2.setColor(partitionLinesColor3);
        g2.draw(line6);
        g2.draw(line7);
        
        //边框线条
        Shape line8 = new Line2D.Float(0,0,0,this.getHeight());
        Shape line9 = new Line2D.Float(749,0,749,this.getHeight());
        Shape line10 = new Line2D.Float(0,0,750,0);
        Shape line11 = new Line2D.Float(0,this.getHeight()-1,0,this.getHeight()-1);
        g2.setColor(rectanglesColor1);
        g2.draw(line8);
        g2.draw(line9);
        g2.draw(line10);
        g2.draw(line11);
    }
    
}
