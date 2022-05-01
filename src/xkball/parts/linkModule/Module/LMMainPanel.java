package xkball.parts.linkModule.Module;

import xkball.interfaces.IColorSetting;
import xkball.parts.swingParts.VerticalViewPort;
import xkball.parts.linkModule.EditFrame;
import xkball.parts.log.Log;
import xkball.ui.DarkButtonUI;
import xkball.ui.DarkJMenuItemUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class LMMainPanel extends JPanel {
    
    private final File place;
    private boolean needReload = false;
    private ArrayList<Module> loadList = new ArrayList<>();
    private ArrayList<ModuleButton> loadButton = new ArrayList<>();
    
    private final JPopupMenu jPopupMenu = new JPopupMenu();
    private final JMenuItem newPMButton = new JMenuItem("   新建分割组件");
    private final JMenuItem newLMButton = new JMenuItem("   新建链接组件");
    private final JMenuItem sort = new JMenuItem("   排序组件");
    private final JMenuItem enforceResetNumberingButton = new JMenuItem("    强制重新排序");
    private final JMenuItem refresh = new JMenuItem("   刷新");
    
    private final JButton cancel = new JButton("取消");
    private final JButton save = new JButton("保存");
    
    private final String titleS;
    private final JPanel innerPanel = new JPanel();
    private final VerticalViewPort innerScrollPane;
    
    private final JLabel title;
    
    
    public LMMainPanel(File place){
        this.place = place;
        if(!place.exists()){
            place.mkdirs();
        }
        this.titleS = place.getName();
        innerScrollPane = new VerticalViewPort(20,innerPanel,0);
        this.menuInitialization();
        title  = new JLabel(titleS){
            {
                this.add(jPopupMenu);
                this.setHorizontalAlignment(CENTER);
                this.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                
                    }
            
                    @Override
                    public void mousePressed(MouseEvent e) {
                
                    }
            
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if(e.isMetaDown()){
                            if(e.getY()<50){
                                jPopupMenu.show(e.getComponent(),e.getX(),e.getY());
                            }
                        }
                    }
            
                    @Override
                    public void mouseEntered(MouseEvent e) {
                
                    }
            
                    @Override
                    public void mouseExited(MouseEvent e) {
                
                    }
                });
    
            }
        };
        this.initShowPlace();
        this.reload();
    }
    
    //对链接按钮的处理
    
    /**
     * 从硬盘加载模块
     */
    public void loadModules(){
        loadList.clear();
        //boolean tab=true;
        //int i = 0;
        File[] files = place.listFiles();
        for (File file : files) {
            Module m = Module.deserialize(file);
            loadList.add(m);
        }
//        while (tab){
//
//            assert files != null;
//            if(files[i]!=null){
//                Module m = Module.deserialize(files[i]);
//                loadList.add(m);
//                i++;
//            }
//            else {
//                tab = false;
//            }
//        }
    }
    
    /**
     * 按照文件内顺序排序
     */
    public void sortModules(){
        ArrayList<Module> bufferA = (ArrayList<Module>) loadList.clone();
        Module[] bufferB = new Module[loadList.size()];
        loadList.clear();
        try {
            for(Module m : bufferA){
                int n = m.getNumbering();
                //if(n<bufferB.length){
                bufferB[m.getNumbering()] = m;
                //System.out.println(m);
                //}
        
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
            Log.log.printException(e);
            Log.log.print("你的模块序号出现了错误，现在已经自动重排");
            System.out.println("你的模块序号出现了错误，现在已经自动重排");
            this.enforceResetNumbering();
        }
        
        loadList.addAll(Arrays.asList(bufferB));
    }
    
    /**
     * 将模块放到窗口上
     */
    public void loadModuleButtons(){
        innerPanel.removeAll();
        loadButton.clear();
        int y = 0;
        for(Module m : loadList){
            if(m instanceof LinkModule){
                int h = 25;
                LMButton lm = new LMButton((LinkModule) m,this);
                lm.setBounds(0,y,lm.getWidth(),h);
                innerPanel.add(lm);
                y=y+h+1;
                loadButton.add(lm);
            }
            else if(m instanceof PartModule){
                
                PMButton pm = new PMButton((PartModule) m,this);
                int h = pm.getHeight();
                pm.setBounds(0,y,pm.getWidth(),h);
                innerPanel.add(pm);
                y=y+h+1;
                loadButton.add(pm);
            }
        }
        if(y>innerPanel.getHeight()){
            innerPanel.setPreferredSize(new Dimension(innerPanel.getWidth(),y));
        }
    }
    
    /**
     * 从内存重载模块，并不会保存现在模块的状态
     */
    public void reload(){
        this.loadModules();
        this.sortModules();
        this.loadModuleButtons();
        this.repaint();
        Log.log.print(this.place.getName()+":刷新了面板");
    }
    
    /**
     * 强制重新排序，于顺序出问题时使用
     * <P>
     * //目前设计是只能手动调用
     * 现在不是了
     */
    public void enforceResetNumbering(){
        loadModules();
        if (loadList.size()!=0){
            int var = 0;
            for(Module m : loadList){
                m.setNumbering(var);
                var=var+1;
            }
        }
        Log.log.print(this.place.getName()+":强制重载了模块顺序");
        this.saveModules();
        this.reload();
    }
    
    /**
     * 修改顺序后保持被修改的顺序，编辑模块是不需要的
     */
    public void saveModules(){
        for(Module m : loadList){
            m.serialize(place);
        }
        Log.log.print(this.place.getName()+":保存了模块");
    }
    public void saveModule(int i){
        loadList.get(i).serialize(place);
        Log.log.print(this.place.getName()+":保存了模块");
    }
    public void saveModule(Module m){
        m.serialize(place);
        Log.log.print(this.place.getName()+":保存了模块");
    }
    /**
     * 设置编辑模式
     * <p>
     * 编辑模式会让ModuleButtons进入编辑状态，同时禁止右键菜单
     * @param b true开启，false关闭
     */
    public void editMode(boolean b){
        for(ModuleButton sp : loadButton){
            sp.setEdit(b);
        }
        newPMButton.setEnabled(!b);
        newLMButton.setEnabled(!b);
        sort.setEnabled(!b);
        enforceResetNumberingButton.setEnabled(!b);
        refresh.setEnabled(!b);
        Log.log.print(this.place.getName()+":模块编辑模式-"+b);
    }
    
    /**
     * 改变模块顺序，会影响两个模块
     * @param numbering 发出改变动作的模块
     * @param state -1/+1 上移或者下移
     */
    public void adjustTheOrder(int numbering,int state){
        int e=numbering+state;
        if(e!=-1 && e!= loadList.size()){
            loadList.get(e).setNumbering(numbering);
            loadList.get(numbering).setNumbering(e);
        }
//        for(int i=0;i<loadButton.size();i++){
//
//            if(i==e){
//                ModuleButton moduleButton = loadButton.get(e);
//                if(moduleButton instanceof LMButton){
//                    LMButton lmButton = (LMButton) moduleButton;
//                    Module m = lmButton.getLinkModule();
//                    m.setNumbering(e-state);
//                }
//                else if(moduleButton instanceof PMButton){
//                    PMButton pmButton = (PMButton) moduleButton;
//                    Module m = pmButton.getPartModule();
//                    m.setNumbering(e-state);
//                }
//            }
//        }
        this.sortModules();
        this.loadModuleButtons();
        this.editMode(true);
        this.repaint();
    }
    
    /**
     * 删除模块
     * @param numbering 被删除的模块位置
     */
    public void deleteModule(int numbering){
        String s = null;
        for (Module m : loadList){
            if(m.getNumbering() == numbering){
                System.out.println(m.getFileName(place));
                s = String.valueOf(m.getFileName(place));
                m.getFileName(place).delete();
            }
            if(m.getNumbering() > numbering){
                m.setNumbering(m.getNumbering()-1);
                this.saveModule(m);
            }
        }
        Log.log.print(this.place.getName()+":删除了模块"+s);
        this.reload();
    }
    
    //菜单初始化
    public void initShowPlace(){
        //this.setOpaque(false);
        this.setLayout(null);
        this.setBackground(IColorSetting.partitionLinesColor2);
    
        JPanel background = new JPanel() {
            @Override
            public void paint(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                Shape line = new Line2D.Float(0, 49, 308, 49);
                g2.setColor(IColorSetting.partitionLinesColor1);
                g2.draw(line);
            }
        };
        background.setBounds(0,0,1000,1000);
        background.setOpaque(false);
        this.add(background);
    
        title.setBounds(0,0,308,50);
        title.setForeground(IColorSetting.charsColor2);
        //title.setFocusable(false);
        title.setFont(new Font("",Font.BOLD,30));
        title.setOpaque(false);
        this.add(title);
        
        //innerPanel.setFocusable(false);
//        innerPanel.setBackground(IColorSetting.partitionLinesColor3);
        
        innerScrollPane.setOpaque(false);
       
//        innerScrollPane.addMouseWheelListener(innerScrollPane);
//        innerScrollPane.setScrollMode(SIMPLE_SCROLL_MODE);
        //innerScrollPane.setFocusable(false);
        this.add(innerScrollPane);
        innerScrollPane.setBounds(0,51,308,506);
        innerScrollPane.setView(innerPanel);
        innerScrollPane.setViewSize(new Dimension(308,506));
        innerScrollPane.setViewPosition(new Point(0,0));
        innerPanel.setLayout(null);
        innerPanel.setMinimumSize(new Dimension(308,506));
        innerPanel.setOpaque(false);
        innerScrollPane.setBounds(0,51,308,506);
        //innerScrollPane.setView(innerPanel);
        //this.addMouseWheelListener(new VerticalViewPort.WheelView(innerScrollPane,506));
    }
    
    public void menuInitialization(){
//        newLMButton.setBackground(IColorSetting.partitionLinesColor2);
//        newPMButton.setBackground(IColorSetting.partitionLinesColor2);
//        sort.setBackground(IColorSetting.partitionLinesColor2);
//
//        newLMButton.setForeground(IColorSetting.charsColor1);
//        newPMButton.setForeground(IColorSetting.charsColor1);
//        sort.setForeground(IColorSetting.charsColor1);
        newLMButton.setUI(new DarkJMenuItemUI());
        newPMButton.setUI(new DarkJMenuItemUI());
        sort.setUI(new DarkJMenuItemUI());
        enforceResetNumberingButton.setUI(new DarkJMenuItemUI());
        refresh.setUI(new DarkJMenuItemUI());
        
        newLMButton.addActionListener(e -> {
            new EditFrame(LinkModule.class.getName(),place).setVisible(true);
            this.setNeedReload(true);
        });
        newPMButton.addActionListener(e -> {
            new EditFrame(PartModule.class.getName(),place).setVisible(true);
            this.setNeedReload(true);
        });
        enforceResetNumberingButton.addActionListener(e -> {
            this.enforceResetNumbering();
            //this.reload();
        });
        refresh.addActionListener(e -> {
            this.reload();
            this.repaint();
        });
        sort.addActionListener(e -> {
            this.editMode(true);
            save.setVisible(true);
            cancel.setVisible(true);
        });
        
        jPopupMenu.addSeparator();
        jPopupMenu.add(refresh);
        jPopupMenu.addSeparator();
        jPopupMenu.add(newLMButton);
        jPopupMenu.add(newPMButton);
        jPopupMenu.add(sort);
        jPopupMenu.addSeparator();
        jPopupMenu.add(enforceResetNumberingButton);
        jPopupMenu.addSeparator();
        
        jPopupMenu.setBorderPainted(false);
        
        jPopupMenu.setBackground(IColorSetting.partitionLinesColor2);
        jPopupMenu.setBorderPainted(false);
        //this.add(jPopupMenu);
        
        this.buttonInitialization();
    }
    
    
    /**
     * 重新加载模块的，并不绘制内容
     * <p>
     *邪道方法（但是有趣，在重绘时判断是不是要重新加载
     */
    @Override
    public void paint(Graphics g){
        super.paint(g);
        if(needReload){
            this.reload();
            this.setNeedReload(false);
        }
//        innerPanel.repaint();
//        for(ModuleButton m : loadButton){
//            m.repaint();
//        }
    }
    
    //按钮初始化
    public void buttonInitialization(){
        cancel.setSize(72,36);
        cancel.addActionListener(e -> {
            cancel.setVisible(false);
            save.setVisible(false);
            this.editMode(false);
            this.reload();
        });
        cancel.setBounds(20,10,72,36);
        cancel.setUI(new DarkButtonUI(cancel));
        cancel.setForeground(IColorSetting.charsColor1);
        cancel.setVisible(false);
        this.add(cancel);
    
        save.setSize(72,36);
        save.addActionListener(e -> {
            cancel.setVisible(false);
            save.setVisible(false);
            this.saveModules();
            this.editMode(false);
            this.reload();
        });
        save.setBounds(226,10,72,36);
        save.setUI(new DarkButtonUI(save));
        save.setForeground(IColorSetting.charsColor1);
        save.setVisible(false);
        this.add(save);
    }
    
    public boolean isNeedReload() {
        return needReload;
    }
    
    public void setNeedReload(boolean needReload) {
        this.needReload = needReload;
    }
    
    public File getPlace(){
        return place;
    }
}
