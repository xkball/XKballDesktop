package xkball.parts.linkModule;

import xkball.interfaces.IColorSetting;
import xkball.parts.log.Log;
import xkball.parts.swingParts.VerticalViewPort;
import xkball.parts.resourseloader.IPath;
import xkball.parts.resourseloader.IResources;
import xkball.util.fileUtil.FileUtil;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class IconChoosePanel extends VerticalViewPort {
    
    private final File icons = IPath.icons;
    private final JPanel showPanel = new JPanel();
    private static ArrayList<File> files = new ArrayList<>();
    private final ArrayList<JComponent> components = new ArrayList<>();
    private File chooseIconPath;
    private IconPanel iconPanel;
    private JFrame frame;
    
    public IconChoosePanel(){
        //super(15);
         this.setView(showPanel);
         this.setViewSize(new Dimension(600,304));
         this.setViewPosition(new Point(0,0));
        
//        this.setViewportView(showPanel);
//        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        this.setWheelScrollingEnabled(true);
//        //this.setPreferredSize(new Dimension(600,304));
//        this.getVerticalScrollBar().setUnitIncrement(6);
//        this.getVerticalScrollBar().setUI(new BasicScrollBarUI(){
//            @Override
//            public void paint(Graphics g,JComponent j){
//
//            }
//        });
        //this.getColumnHeader().setUI();

//        this.addMouseWheelListener(e -> {
//            Adjustable adj = this.getVe
//            if (e.getScrollType() == WHEEL_UNIT_SCROLL) {
//                int totalScrollAmount =
//                        e.getUnitsToScroll() *
//                                adj.getUnitIncrement();
//                adj.setValue(adj.getValue() + totalScrollAmount);
//
//            }
//        });
        
        //showPanel.setLayout(new GridLayout(0,6,0,0));
        showPanel.setLayout(null);
        showPanel.setBackground(IColorSetting.partitionLinesColor3);
        showPanel.setMinimumSize(new Dimension(600,300));
        //showPanel.setPreferredSize(new Dimension(600,500));
        if(!icons.exists()){
            icons.mkdirs();
        }
        
        loadResourcesIcon();
        loadIcons();
//        this.addMouseWheelListener(this);
//        this.setScrollMode(SIMPLE_SCROLL_MODE);
    }
    
    public void loadResourcesIcon(){
        String[] needs = new String[]{
                "/resource/icon.png",
                "/resource/filesIcon.png",
                "/resource/local_link_icon.png",
                "/resource/net_link_icon.png",
                "/resource/icon_large.png",
                "/resource/transparent_icon.png",
                "/resource/arrow_right.png",
                "/resource/arrow_left.png",
                "/resource/prong.png",
                "/resource/hook.png"
        };
        for(String f : needs){
            
            if(!FileUtil.getCopyFile(new File(IResources.getResourceURL(f).getFile()),icons).exists()){
                try {
                    FileUtil.copy(f,icons);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Log.log.printException(e);
                }
            }
            
        }
        
    }
    
    public void enableIconChoosePanel(JComponent... jComponent){
        for(JComponent component : jComponent){
            component.setVisible(false);
            components.add(component);
        }
        if(iconPanel == null){
            iconPanel = new IconPanel(IResources.getFile(IResources.SIcon));
        }
        chooseIconPath = iconPanel.getIconFile();
        this.loadIcons();
        this.setVisible(true);
        frame.repaint();
    }
    
    public void disableIconChoosePanel(){
        this.setVisible(false);
        for(JComponent component : components){
            component.setVisible(true);
        }
        iconPanel.setIconFile(this.getChooseIconPath());
        iconPanel.repaint();
        frame.repaint();
    }
    
    public void loadIcons(){
        files.clear();
        File[] filePng = FileUtil.getTypedFiles(icons,".png");
        File[] fileJpg = FileUtil.getTypedFiles(icons,".jpg");
        files.addAll(Arrays.asList(filePng));
        files.addAll(Arrays.asList(fileJpg));
        showPanel.removeAll();
        int x = 0;
        int y = 0;
        
        for(File file : files){
            IconPanel ic = new IconPanel(file);
            ic.setIconChoosePanel(this);
            ic.setBounds(x*100,y*100,100,100);
            x = x+1;
            if(x==6){
                x=0;
                y = y+1;
            }
            if(y*100>=200){
                showPanel.setPreferredSize(new Dimension(600,y*100+100));
            }
            showPanel.add(ic);
        }
    }
    
    public File getChooseIconPath() {
        return chooseIconPath;
    }
    
    public void setChooseIconPath(File chooseIconPath) {
        this.chooseIconPath = chooseIconPath;
    }
    
    public IconPanel getIconPanel() {
        return iconPanel;
    }
    
    public void setIconPanel(IconPanel iconPanel) {
        this.iconPanel = iconPanel;
    }
    
    public JFrame getFrame() {
        return frame;
    }
    
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
    
    
}
