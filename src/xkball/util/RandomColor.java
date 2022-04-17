package xkball.util;

import org.jetbrains.annotations.NotNull;
import xkball.interfaces.ISettings;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import static xkball.util.MathUtil.*;

public class RandomColor extends JPanel implements Runnable, ISettings {
    
    private final int[] PrintColor = new int[]{1,2,3};//相当于一个画笔？不清楚
    
    Color RestrictedColor = new Color(255,255,255);//弃用
    
    private PanelMode PanelModes = PanelMode.RandomColorMode;//默认状态为随机颜色
    
    public RandomColor(){
        Color Default_Color = randomColor();
        this.setPrintColor(Default_Color.getRed(),Default_Color.getGreen(),Default_Color.getBlue());
    }
    
    //属性的set，get部分
    public void setPrintColor(int red, int green, int blue)
    {
        Arrays.fill(PrintColor,0,1,red);
        Arrays.fill(PrintColor,1,2,green);
        Arrays.fill(PrintColor,2,3,blue);
    }
    
    public int[] getPrintColor(){
        return new int[]{PrintColor[0],PrintColor[1],PrintColor[2]};
    }
    
    public void setPanelModes(PanelMode p)
    {
        PanelModes = p;
    }
    public int getPanelModes()
    {
        return PanelModes.getMode();
    }
    
    //处理颜色的部分
    public static @NotNull Color randomColor()
    {
        int r = (int) (Math.random()*(255));
        int g = (int) (Math.random()*(255));
        int b = (int) (Math.random()*(255));
        return new Color(r,g,b);
    }
    
    public static Color randomAlphaColor()
    {
        int a = (int) (Math.random()*(255));
        return new Color(0,0,0,a);
    }
    
    public Color randomRestrictedColor()//会以PrintColor为标签变化
    {
        int nr = becomeColor(randomPlus(this.getPrintColor()[0],(int) (Math.random()*(ISettings.RandomColorRange))));
        int ng = becomeColor(randomPlus(this.getPrintColor()[1],(int) (Math.random()*(ISettings.RandomColorRange))));
        int nb = becomeColor(randomPlus(this.getPrintColor()[2],(int) (Math.random()*(ISettings.RandomColorRange))));
        //System.out.println(PrintColor[0]+"  "+PrintColor[1]+"   "+PrintColor[2]+"   "+nr);
        this.setPrintColor(nr,ng,nb);
        return new Color(nr,ng,nb);
    }
    
    //绘制屏幕的部分
    public void newRandomColorPanel(Graphics g)//使用随机颜色绘制
    {
      Graphics2D g2 = (Graphics2D) g;
      for(int i=0;i<this.getHeight();i++)
      {
          for (int o=0;o<this.getWidth();o++)
          {
              
              Color c = randomColor();
              g2.setColor(c);
              g2.drawLine(o,i,o,i);
              //System.out.println(getPrintColor()[0]+" "+getPrintColor()[1]+" "+getPrintColor()[2]);
          }
      }
    }
    public void newRandomRestrictedColorPanel(Graphics g)//使用渐变颜色绘制
    {
        Graphics2D g2 = (Graphics2D) g;
        for(int i=0;i<this.getHeight();i++)
        {
            for (int o=0;o<this.getWidth();o++)
            {
            
                Color c = this.randomRestrictedColor();
                g2.setColor(c);
                g2.drawLine(o,i,o,i);
            }
        }
    }
    
    public void newRandomRestrictedColorCrosswisePanel(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        for (int o=0;o<this.getWidth();o++)
        {
            Color c =this.randomRestrictedColor();
            g2.setColor(c);
            g2.drawLine(o,0,o,this.getHeight());
        }
    }
    
    public void newRandomAlphaColorPanel(Graphics g)//使用渐变颜色绘制
    {
        Graphics2D g2 = (Graphics2D) g;
        for(int i=0;i<this.getHeight();i++)
        {
            for (int o=0;o<this.getWidth();o++)
            {
                
                Color c = randomAlphaColor();
                g2.setColor(c);
                g2.drawLine(o,i,o,i);
            }
        }
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        switch (this.getPanelModes())
        {
            case 1:
                newRandomColorPanel(g);
                break;
            case 2:
                newRandomRestrictedColorPanel(g);
                break;
            case 3:
                newRandomRestrictedColorCrosswisePanel(g);
                break;
            case 4:
                newRandomAlphaColorPanel(g);
                break;
        }
        
        
    }
    
    @Override
    public void run() {
        while (true)
        {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.repaint();
        }
        
    }
    
    //限制属性
    public enum PanelMode
    {
        RandomColorMode(1),
        RandomRestrictedColorMode(2),
        RandomRestrictedColorCrosswiseMode(3),
        RandomAlphaColorMode(4);
        
        private int Mode = 0;
        
        PanelMode(int mode)
        {
            this.Mode=mode;
        }
        
        public int getMode()
        {
            return Mode;
        }
    }

}
