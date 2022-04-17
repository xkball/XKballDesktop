package xkball.parts.linkModule;

import xkball.interfaces.IColorSetting;
import xkball.parts.resourseloader.IResources;
import xkball.util.fileUtil.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.io.File;

public class IconPanel extends JPanel implements MouseListener {
    private File iconFile;
    private boolean isChoose = false;
    private boolean isInside = false;
    private IconChoosePanel iconChoosePanel;
    
    public IconPanel(){
        this(new File(IResources.urlIcon.getFile()));
    }
    public IconPanel(File iconFile) {
        this.iconFile = iconFile;
        this.addMouseListener(this);
        this.setSize(100,100);
        this.setBackground(IColorSetting.partitionLinesColor3);
    }
    
    @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        super.paint(g);
        Image img = Toolkit.getDefaultToolkit().getImage(iconFile.getPath());
        Dimension d = ImageUtil.flushWidthHeight(iconFile,new Dimension(80,80));
        int x = (int) d.getWidth();
        int y = (int) d.getHeight();
        g2.drawImage(img, this.getWidth()/2-x/2,this.getHeight()/2-y/2,x,y,this);
        Shape rec1 = new Rectangle2D.Float(0,0,this.getWidth()-1,this.getHeight()-1);
        Shape rec2 = new Rectangle2D.Float(2,2,this.getWidth()-5,this.getHeight()-5);
        g2.setColor(IColorSetting.partitionLinesColor6);
        g2.draw(rec1);
        if(isInside){
            g2.draw(rec2);
        }
    }
    
    public File getIconFile() {
        return iconFile;
    }
    
    public void setIconFile(File iconFile) {
        this.iconFile = iconFile;
    }
    
    public boolean isChoose() {
        return isChoose;
    }
    
    public void setChoose(boolean choose) {
        isChoose = choose;
    }
    
    public boolean isInside() {
        return isInside;
    }
    
    public void setInside(boolean inside) {
        isInside = inside;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        this.setChoose(true);
        try{
            iconChoosePanel.setChooseIconPath(this.getIconFile());
        }
        catch (NullPointerException ex){}
        
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        this.setChoose(false);
        
        try{
            iconChoosePanel.disableIconChoosePanel();
        }
        catch (NullPointerException ex){}
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        this.setInside(true);
        this.repaint();
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        this.setInside(false);
        this.repaint();
    }
    
    public IconChoosePanel getIconChoosePanel() {
        return iconChoosePanel;
    }
    
    public void setIconChoosePanel(IconChoosePanel iconChoosePanel) {
        this.iconChoosePanel = iconChoosePanel;
    }
}
