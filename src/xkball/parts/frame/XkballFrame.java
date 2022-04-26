package xkball.parts.frame;

import xkball.interfaces.IColorSetting;
import xkball.interfaces.IFlushable;
import xkball.interfaces.ISettings;
//import xkball.parts.linkModule.Module.disabled.OutPanel;
import xkball.parts.SwingParts.PlanPanel;
import xkball.parts.linkModule.Module.LMMainPanel;
import xkball.parts.photosModule.PhotosPanel;
import xkball.parts.resourseloader.IPath;
import xkball.parts.resourseloader.IResources;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class XkballFrame extends JFrame implements ISettings, IColorSetting, IFlushable {
    
    JLabel textUp = new JLabel("今天也是精疲力尽的一天呢",SwingConstants.CENTER);
    JLabel textDown1 = new JLabel("made by xkball");
    JLabel textDown2 = new JLabel();
    
    public PhotosPanel photosPanel = new PhotosPanel();
    
    public Container c = this.getContentPane();
    
    //DragFrames dragFrames = new DragFrames(this);
    ShowFrames showFrames = new ShowFrames(this);
    LMMainPanel left;
    LMMainPanel right;
    
    //OutPanel left = new OutPanel(1,this);
    BackgroundPanel backgroundPanel = new BackgroundPanel();
    //ClickPanel clickPanel = new ClickPanel();
    final PlanPanel planPanel = new PlanPanel(IPath.planTexts.getPath());
    
    public XkballFrame(String title)
    {
        super(title);
        if(IPath.LMLeft.listFiles()==null){
            left = new LMMainPanel(new File(IPath.LMLeft.getPath()+File.separator+"新"));
        }
        else {
            left = new LMMainPanel(IPath.LMLeft.listFiles()[0]);
        }
        if(IPath.LMRight.listFiles()==null){
            right= new LMMainPanel(new File(IPath.LMRight.getPath()+File.separator+"新"));
        }
        else {
            right = new LMMainPanel(IPath.LMRight.listFiles()[0]);
        }
        initialization();
        //this.addMouseListener(new ShowFrames(this,true));
        
    }
    
    public void addSystemTray(){
        if(SystemTray.isSupported()) {
            PopupMenu popupMenu = new PopupMenu();
            MenuItem item1 = new MenuItem("exit");
            MenuItem item2 = new MenuItem("flush");
            MenuItem item3 = new MenuItem("open");
            item1.addActionListener(e -> System.exit(0));
            item3.addActionListener(e -> {
                this.setVisible(true);
                left.setNeedReload(true);
            });
            popupMenu.add(item3);
            popupMenu.add(item2);
            popupMenu.add(item1);
            
            TrayIcon trayIcon = new TrayIcon(IResources.icon.getImage(), "xkball’s desktop", popupMenu);
            SystemTray systemTray = SystemTray.getSystemTray();
            trayIcon.addMouseListener(showFrames);
            try {
                systemTray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public void flush() {
        
        this.setSize(750, Toolkit.getDefaultToolkit().getScreenSize().height-50);
        this.setIconImage(IResources.largeIcon.getImage());
        this.setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()-750),0);
        this.setLayout(null);
        this.c.setBackground(backgroundColor);
        //this.addMouseListener(dragFrames);
        //this.addMouseMotionListener(dragFrames);
        this.addWindowFocusListener(showFrames);
        this.addKeyListener(showFrames);
    
        photosPanel.setBounds(30,32,308,308);
        this.add(photosPanel);
        
    
        this.initializationJLabel();
        this.initializationLM();
    }
    
    public void initialization(){
        this.setUndecorated(true);
        this.flush();
        this.addSystemTray();
    }
    
    public void initializationJLabel(){
        textDown1.setOpaque(true);
        textDown1.setBackground(rectanglesColor1);
        textDown1.setFont(new Font("宋体", Font.BOLD,20));
        textDown1.setForeground(charsColor1);
        textDown1.setHorizontalAlignment(SwingConstants.CENTER);
        textDown1.setBounds(0,this.getHeight()-20,375,20);
        c.add(textDown1);
        
        textUp.setOpaque(true);
        textUp.setBackground(rectanglesColor1);
        textUp.setFont(new Font("宋体", Font.BOLD,20));
        textUp.setForeground(charsColor1);
        textUp.setBounds(0,0,750,20);
        c.add(textUp);
    
        textDown2.setText("记得浇花");
        textDown2.setOpaque(true);
        textDown2.setBackground(rectanglesColor1);
        textDown2.setFont(new Font("宋体", Font.BOLD,20));
        textDown2.setForeground(charsColor1);
        textDown2.setHorizontalAlignment(SwingConstants.CENTER);
        textDown2.setBounds(375,this.getHeight()-20,375,20);
        c.add(textDown2);
    }
    
    public void initializationLM(){
        backgroundPanel.setBounds(0,0,this.getWidth(),this.getHeight());
        left.setPreferredSize(new Dimension(308,this.getHeight()-454-21));
        left.setBounds(30,454,308,this.getHeight()-454-21);
        this.add(left);
//        clickPanel.setPreferredSize(new Dimension(308,408));
//        clickPanel.setBounds(375+37,32,308,408);
//        this.add(clickPanel);
        planPanel.setPreferredSize(new Dimension(308,408));
        planPanel.setBounds(375+37,32,308,408);
        this.add(planPanel);
        right.setPreferredSize(new Dimension(308,this.getHeight()-454-21));
        right.setBounds(375+37,454,308,this.getHeight()-454-21);
        this.add(right);
        this.add(backgroundPanel);
    }
    
    
}
