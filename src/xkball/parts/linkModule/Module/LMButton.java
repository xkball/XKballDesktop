package xkball.parts.linkModule.Module;

import xkball.interfaces.IColorSetting;
import xkball.parts.linkModule.EditFrame;
import xkball.parts.resourseloader.IResources;
import xkball.ui.DarkJMenuItemUI;
import xkball.ui.DarkJMenuUI;
import xkball.util.DesktopUtil;
import xkball.util.fileUtil.Directory;
import xkball.util.fileUtil.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.File;

public class LMButton extends ModuleButton implements MouseListener {
    
    
    private final JPopupMenu jPopupMenu = new JPopupMenu();
    //private final JMenuItem deleteMenu = new JMenuItem();
    private final JMenuItem doDelete = new JMenuItem("确认");
    private final JMenuItem cancelDelete = new JMenuItem("取消        ");
    private final JMenuItem openFile = new JMenuItem("打开文件位置");
    private final JMenuItem edit = new JMenuItem("编辑");
    private final JMenu delete = new JMenu("删除");
    
    private JLabel label;
    private JPanel icon;

    
    private final LinkModule lm;
   
    
    public LMButton(LinkModule lm,LMMainPanel panel){
        super(new Dimension(308,25),panel);
        //this.setSize(380,25);
        this.lm = lm;
        this.setHaveBoard(false);
        this.setOpaque(false);
        this.setLayout(null);
        icon = new JPanel(){
            {
                this.setBounds(0,0,55,25);
                this.setFocusable(false);
                this.setOpaque(false);
            }
            @Override
            public void paint(Graphics g){
                super.paint(g);
                Graphics2D g2 = (Graphics2D) g;
                Image image = Toolkit.getDefaultToolkit().getImage(lm.getIcon().getPath());
                if(image!= null){
                    Dimension size = ImageUtil.flushWidthHeight(lm.getIcon(),new Dimension(20,20));
                    g2.drawImage(image,32,3, (int) size.getWidth(), (int) size.getHeight(),this);
                }
                Image image1 =
                        lm.isFile()?
                                IResources.getImage(IResources.urlLocalLinkIcon)
                                :
                                IResources.getImage(IResources.urlNetLinkIcon);
                
                    
                    Dimension size = ImageUtil.flushWidthHeight(new File(IResources.urlLocalLinkIcon.getFile()),new Dimension(20,20));
                    g2.drawImage(image1,3,3, (int) size.getWidth(), (int) size.getHeight(),this);
                
                g2.setColor(IColorSetting.partitionLinesColor1);
                Shape rec1 = new Rectangle2D.Float(25,0,3,25);
                g2.fill(rec1);
            }
        };
        label = new JLabel(lm.getTitle());
        label.setBounds(55,0,this.getWidth()-55,25);
        label.setForeground(IColorSetting.charsColor2);
        //label.setIcon(new ImageIcon(String.valueOf(lm.getIcon())));
        label.setFont(new Font("",Font.PLAIN,17));
        menuInitialization();
        //this.setFill(true);
        this.setShape1(
                new Shape[]{
                        new Line2D.Float(2,2,this.getWidth()-5,2),
                        new Line2D.Float(2,this.getHeight()-3,this.getWidth()-5,this.getHeight()-3)
                }
        );
        this.setBorderColor(new Color(80,80,80));
        this.addMouseListener(this);
        this.add(label);
        this.add(icon);
    }
    
    @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        super.paint(g);
//        Image img = Toolkit.getDefaultToolkit().getImage(iconFile.getPath());
//        Dimension d = ImageUtil.flushWidthHeight(iconFile,new Dimension(80,80));
//        int x = (int) d.getWidth();
//        int y = (int) d.getHeight();
//        g2.drawImage(img, this.getWidth()/2-x/2,this.getHeight()/2-y/2,x,y,this);
        Shape line = new Line2D.Float(0,this.getHeight()-1,this.getWidth(),this.getHeight()-1);
        g2.setColor(IColorSetting.partitionLinesColor4);
        g2.draw(line);
        label.updateUI();
        //label.repaint();
    }
    
    public void menuInitialization(){
//        openFile.setBackground(IColorSetting.partitionLinesColor2);
//        edit.setBackground(IColorSetting.partitionLinesColor2);
//        delete.setBackground(IColorSetting.partitionLinesColor2);
//
//        openFile.setForeground(IColorSetting.charsColor1);
//        edit.setForeground(IColorSetting.charsColor1);
//        delete.setForeground(IColorSetting.charsColor1);
        
        openFile.setUI(new DarkJMenuItemUI());
        edit.setUI(new DarkJMenuItemUI());
        delete.setUI(new DarkJMenuUI());
        doDelete.setUI(new DarkJMenuItemUI());
        cancelDelete.setUI(new DarkJMenuItemUI());
        
        delete.getPopupMenu().addSeparator();
        delete.add(cancelDelete);
        delete.add(doDelete);
        delete.getPopupMenu().addSeparator();
        
        openFile.addActionListener(e -> {
            if(lm.isFile()){
                File f = new File(lm.getFilePath());
                if(f.isDirectory()){
                    lm.use();
                }
                else {
                    DesktopUtil.openFile(Directory.toDirectory(f));
                }
            }
        });
        
        edit.addActionListener(e -> {
            new EditFrame(lm,LinkModule.class.getName(),getPanel().getPlace()).setVisible(true);
            getPanel().setNeedReload(true);
        });
        
        doDelete.addActionListener(e -> {
            this.getPanel().deleteModule(lm.getNumbering());
            this.getPanel().setNeedReload(true);
        });
        
        if(!lm.isFile()){
            openFile.setEnabled(false);
        }
        
        
//        deleteMenu.add(cancelDelete);
//        deleteMenu.add(doDelete);
        jPopupMenu.addSeparator();
        jPopupMenu.add(openFile);
        jPopupMenu.add(edit);
        jPopupMenu.addSeparator();
        jPopupMenu.add(delete);
        jPopupMenu.addSeparator();
        
        jPopupMenu.setBorderPainted(false);
        delete.setBorderPainted(false);
        doDelete.setBorderPainted(false);
        cancelDelete.setBorderPainted(false);
        
        jPopupMenu.setBackground(IColorSetting.partitionLinesColor2);
        //jPopupMenu.setBorderPainted(false);
        this.add(jPopupMenu);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        int key = e.getButton();
        
        if(key == MouseEvent.BUTTON1 && e.getClickCount() == 2 && !isEdit()){
            lm.use();
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.isMetaDown() && !isEdit()){
            jPopupMenu.show(e.getComponent(),e.getX(),e.getY());
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    
    }
    
    public LinkModule getLinkModule(){
        return lm;
    }
    
    @Override
    public void clickedE(int state) {
        super.getPanel().adjustTheOrder(lm.getNumbering(),state);
    }
}
