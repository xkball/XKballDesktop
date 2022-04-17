package xkball.parts.linkModule.Module.disabled;

import xkball.interfaces.IColorSetting;
import xkball.interfaces.IFlushable;
import xkball.parts.linkModule.Module.disabled.LinkedModule;
import xkball.parts.resourseloader.IPath;
import xkball.util.fileUtil.ImageUtil;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class LMButton extends JPanel implements IFlushable, MouseListener {
    private LinkedModule lm;
    private String title;
    private int ID;
    private File file;
    private final JPopupMenu jPopupMenu = new JPopupMenu();
    private final JMenuItem openFile = new JMenuItem("打开文件位置");
    private final JMenuItem edit = new JMenuItem("编辑");
    private final JMenuItem delete = new JMenuItem("删除");
    private int part;
    
    private LMButton(){
        this.part = 1;
        this.setLayout(null);
        //this.setSize(300,30);
        this.setOpaque(false);
        //this.setBackground(IColorSetting.partitionLinesColor2);
        this.addMouseListener(this);
        this.add(jPopupMenu);
        
    }
    public LMButton(LinkedModule lm, int part){
        this();
        this.part = part;
        this.lm=lm;
        this.ID = lm.getID();
        menuInitialization();
        file = new File(IPath.linkModule.getPath()+File.separator+lm.getID()+".lm");
    }
    public LMButton(int ID){
        this();
        this.ID = ID;
        this.lm = LinkedModule.deserialize(ID,part);
        menuInitialization();
        file = new File(IPath.linkModule.getPath()+File.separator+lm.getID()+".lm");
    }
    
    public void menuInitialization(){
        openFile.setBackground(IColorSetting.partitionLinesColor2);
        edit.setBackground(IColorSetting.partitionLinesColor2);
        delete.setBackground(IColorSetting.partitionLinesColor2);
        
        openFile.setForeground(IColorSetting.charsColor1);
        edit.setForeground(IColorSetting.charsColor1);
        delete.setForeground(IColorSetting.charsColor1);
        
        if(!lm.isFile()){
            openFile.setEnabled(false);
        }
        
        jPopupMenu.add(openFile);
        jPopupMenu.add(edit);
        jPopupMenu.add(delete);
    
        jPopupMenu.setBackground(IColorSetting.partitionLinesColor2);
        jPopupMenu.setBorderPainted(false);
        
    }
    
    
    @Override
    public void flush() {
    
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        Image image = Toolkit.getDefaultToolkit().getImage(lm.getIcon().getPath());
        Dimension d = ImageUtil.flushWidthHeight(lm.getIcon(),new Dimension(24,24));
        int x = (int) d.getWidth();
        int y = (int) d.getHeight();
        
        g2.setColor(IColorSetting.charsColor1);
        g2.setFont(new Font("",Font.PLAIN,20));
        g2.drawString(lm.getTitle(),35,20);
        g2.drawImage(image,14-x/2,14-y/2,x,y,this);
    }
    
    public void setLm(LinkedModule lm){
        this.lm = lm;
        this.repaint();
    }
    public LinkedModule getLM(){
        return lm;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        int key = e.getButton();
        //System.out.println(key);
        if(key == MouseEvent.BUTTON1 && e.getClickCount() == 2){
            lm.use();
        }
        
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        //int key = e.getButton();
        if(e.isMetaDown()){
            jPopupMenu.show(e.getComponent(),e.getX(),e.getY());
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    
    }
}
