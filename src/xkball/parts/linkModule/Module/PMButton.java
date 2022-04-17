package xkball.parts.linkModule.Module;

import xkball.interfaces.IColorSetting;
import xkball.parts.SwingParts.SelectionPanel;
import xkball.parts.linkModule.EditFrame;
import xkball.ui.DarkJMenuItemUI;
import xkball.ui.DarkJMenuUI;
import xkball.util.DesktopUtil;
import xkball.util.fileUtil.Directory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.File;

public class PMButton extends ModuleButton implements MouseListener {
    
    private final JPopupMenu jPopupMenu = new JPopupMenu();
    private final JMenuItem doDelete = new JMenuItem("确认        ");
    private final JMenuItem cancelDelete = new JMenuItem("取消    ");
    private final JMenuItem edit = new JMenuItem("编辑        ");
    private final JMenu delete = new JMenu("删除");
    
    private JLabel label;
    private final PartModule partModule;
    
    public PMButton(PartModule partModule,LMMainPanel panel) {
        super(new Dimension(308,28),panel);
        this.setPreferredSize(new Dimension(308,28));
        this.partModule = partModule;
        this.setOpaque(false);
        this.setHaveBoard(false);
        this.addMouseListener(this);
        this.setLayout(null);
        
        label = new JLabel(partModule.getTitle());
        label.setBounds(0,0,this.getWidth(),this.getHeight());
        label.setFont(new Font("",Font.PLAIN,20));
        
        initLabel();
        menuInitialization();
        this.setShape1(
                new Shape[]{
                        new Line2D.Float(2,2,this.getWidth()-5,2),
                        new Line2D.Float(2,this.getHeight()-3,this.getWidth()-5,this.getHeight()-3)
                }
        );
        this.setBorderColor(new Color(80,80,80));
    }
    
    public void initLabel(){
        label.setOpaque(false);
        label.setForeground(IColorSetting.charsColor2);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(label,BorderLayout.CENTER);
    }
    public void menuInitialization(){

        edit.setUI(new DarkJMenuItemUI());
        delete.setUI(new DarkJMenuUI());
        doDelete.setUI(new DarkJMenuItemUI());
        cancelDelete.setUI(new DarkJMenuItemUI());
        
        delete.add(cancelDelete);
        delete.add(doDelete);
        
        edit.addActionListener(e -> {
            new EditFrame(partModule,PartModule.class.getName(),getPanel().getPlace());
            getPanel().setNeedReload(true);
        });
        
        doDelete.addActionListener(e -> {
            this.getPanel().deleteModule(partModule.getNumbering());
            this.getPanel().setNeedReload(true);
        });
        

        jPopupMenu.addSeparator();
        
        jPopupMenu.add(edit);
        jPopupMenu.addSeparator();
        jPopupMenu.add(delete);
        jPopupMenu.addSeparator();
        
        jPopupMenu.setBorderPainted(false);
        delete.setBorderPainted(false);
        doDelete.setBorderPainted(false);
        cancelDelete.setBorderPainted(false);
        
        jPopupMenu.setBackground(IColorSetting.partitionLinesColor2);
        
        this.add(jPopupMenu);
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        Shape line = new Line2D.Float(0,this.getHeight()-1,this.getWidth(),this.getHeight()-1);
        g2.setColor(IColorSetting.partitionLinesColor4);
        g2.draw(line);
    
//        Shape rec1 = new Rectangle2D.Float(0,0,this.getWidth()-1,this.getHeight()-1);
//        Shape rec2 = new Rectangle2D.Float(2,2,this.getWidth()-5,this.getHeight()-5);
//        g2.setColor(IColorSetting.partitionLinesColor6);
//        g2.draw(rec1);
//        if(isInside){
//            g2.draw(rec2);
//        }
        label.updateUI();
    }
    
    public int getNumbering(){
        return partModule.getNumbering();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        int i = e.getButton();
        
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
    
    public PartModule getPartModule(){
        return partModule;
    }
    
    @Override
    public void clickedE(int state) {
        super.getPanel().adjustTheOrder(partModule.getNumbering(),state);
    }
}
