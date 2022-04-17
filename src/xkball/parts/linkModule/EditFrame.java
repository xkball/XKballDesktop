package xkball.parts.linkModule;

import xkball.parts.frame.DragFrames;
import xkball.interfaces.IColorSetting;
import xkball.parts.linkModule.Module.LinkModule;
import xkball.parts.linkModule.Module.Module;
import xkball.parts.linkModule.Module.PartModule;
import xkball.parts.linkModule.Module.disabled.LMButton;
import xkball.parts.log.Log;
import xkball.parts.resourseloader.IPath;
import xkball.parts.resourseloader.IResources;
import xkball.ui.DarkButtonUI;
import xkball.util.fileUtil.ImageUtil;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

public class EditFrame extends JFrame {
    
    private final Module m;
    private String type;
    private File place;
    
    JButton exitNotSave = new JButton("取消");
    JButton exitOnSave = new JButton("保存");
    JButton chooseFile = new JButton();
    JButton chooseFileIcon = new JButton();
    JButton chooseIcon = new JButton("选择");
    
    JLabel titleLabel = new JLabel("名称:");
    JLabel urlLabel = new JLabel("URL地址:");
    JLabel fileLabel = new JLabel("文件地址:");
    JLabel iconLabel = new JLabel("图标:");
    JLabel information = new JLabel();
    
    JCheckBox urlCheckBox = new JCheckBox("是否是URL",true);
    JCheckBox fileCheckBox = new JCheckBox("是否是文件/文件夹",false);
    
    JTextField titleField = new JTextField(600);
    JTextField urlField = new JTextField(600);
    JTextField fileField = new JTextField(600);
    
    JFileChooser fileChooser = new JFileChooser();
    JFileChooser iconChooser = new JFileChooser();
    
    IconPanel icon = new IconPanel();
    IconChoosePanel iconChoosePanel = new IconChoosePanel();
    
    DragFrames dragFrames = new DragFrames(this);
    
    //LMButton lmButton;
    
//    public EditFrame(LMButton lb){
//        this(lb.getLM());
//        this.lmButton = lb;
//    }
    
    public  EditFrame(String type,File place){
        this(null,type,place);
    }
    public EditFrame(Module m, String type, File place){
        this.m = m;
        this.type = type;
        this.place = place;
        this.initialization();
        this.setVisible(true);
        if(m!=null){
            String title = m.getTitle();
            String path = null;
            boolean b = false;
            if(m instanceof LinkModule){
                LinkModule lm = (LinkModule) m;
                b = lm.isFile();
                path = lm.getAPath();
            }
            titleField.setText(title);
            if(path!= null){
                if(b){
                    fileField.setText(path);
                    enableTextField(true,fileField);
                    enableTextField(false,urlField);
                }
                else {
                    urlField.setText(path);
                    enableTextField(true,urlField);
                    enableTextField(false,titleField);
                }
                urlCheckBox.setEnabled(false);
                fileCheckBox.setEnabled(false);
                urlCheckBox.setSelected(!b);
                fileCheckBox.setSelected(b);
                urlField.setEnabled(!b);
            }
        }
    }
    
    public void initialization(){
        this.setUndecorated(true);
        this.setSize(1000,404);
        int x = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2;
        int y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2;
        this.setLocation((int)(x-this.getSize().getWidth()/2),(int)(y-this.getSize().getHeight()/2));
        this.setLayout(null);
        this.getContentPane().setBackground(IColorSetting.backgroundColor);
        this.setIconImage(IResources.icon.getImage());
        this.addMouseListener(dragFrames);
        this.addMouseMotionListener(dragFrames);
        
        buttonInitialization();
        LabelInitialization();
        checkBoxInitialization();
        fieldInitialization();
        chooserInitialization();
        panelInitialization();
        IconChooserInitialization();
    }
    
    //初始化 图标选择面板
    public void IconChooserInitialization(){
        iconChoosePanel.setBounds(50,50,600,304);
        iconChoosePanel.setVisible(false);
        iconChoosePanel.setIconPanel(icon);
        iconChoosePanel.setFrame(this);
        this.add(iconChoosePanel);
    }
    
    //初始化 文件选择器
    public void chooserInitialization(){
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setCurrentDirectory(new File("C:/"));
        fileChooser.setDialogTitle("选择文件/文件夹");
    
        FileNameExtensionFilter iconFilter = new FileNameExtensionFilter("图标", "jpg", "png", "jpeg", "exe", "icon");
        iconChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        iconChooser.setCurrentDirectory(new File("C:/"));
        iconChooser.setDialogTitle("添加图标");
        iconChooser.setFileHidingEnabled(true);
        iconChooser.setApproveButtonText("选择");
        //iconChooser.setFileFilter(iconFilter);
        
    }
    
    //初始化 按钮
    public void buttonInitialization(){
        exitNotSave.setSize(72,36);
        exitNotSave.addActionListener(e -> this.dispose());
        exitNotSave.setBounds(752,318,72,36);
        exitNotSave.setUI(new DarkButtonUI(exitNotSave));
        exitNotSave.setForeground(IColorSetting.charsColor1);
        this.add(exitNotSave);
        
        exitOnSave.setSize(72,36);
        exitOnSave.addActionListener(e -> {
//            if(titleField.getText()!=null){
//                m.setTitle(titleField.getText());
//            }
            if(Objects.equals(type , LinkModule.class.getName())){
                
                LinkModule lm;
                if(m==null){
                    lm = new LinkModule(titleField.getText(), Objects.requireNonNull(place.listFiles()).length);
                }
                else {
                    lm = new LinkModule(titleField.getText(), m.getNumbering());
                }
                lm.setIcon(icon.getIconFile());
                lm.setFile(fileCheckBox.isSelected());
                if(new File(fileField.getText()).exists() || urlCheckBox.isSelected()){
                    lm.setFilePath(fileField.getText());
                    if(urlCheckBox.getText()!=null
                            && !Objects.equals(urlCheckBox.getText(), IPath.url.getPath())
                            && urlCheckBox.isSelected()){
                        //System.out.println(urlField.getText());
                        lm.setURLPath(urlField.getText());
                    }
                    lm.serialize(place);
            }
                
                this.dispose();
            }
            else if(Objects.equals(type ,PartModule.class.getName())){
            
                PartModule partModule = new PartModule(titleField.getText(), Objects.requireNonNull(place.listFiles()).length);
                partModule.serialize(place);
                this.dispose();
            }
            else {
                information.setText("错误的文件地址");
            }
            
        });
        exitOnSave.setBounds(834,318,72,36);
        exitOnSave.setUI(new DarkButtonUI(exitOnSave));
        exitOnSave.setForeground(IColorSetting.charsColor1);
        this.add(exitOnSave);
        
        chooseFile.setUI(new DarkButtonUI(chooseFile));
        chooseFile.setBounds(600,318,50,36);
        chooseFile.addActionListener(e -> {
            if(fileCheckBox.isSelected()){
                int i = fileChooser.showOpenDialog(getContentPane());
                if(i == JFileChooser.APPROVE_OPTION){
                    File file = fileChooser.getSelectedFile();
                    fileField.setText(file.getPath());
                }
            }
        });
        chooseFile.setIcon(IResources.filesIcon);
        this.add(chooseFile);
        
        chooseFileIcon.setUI(new DarkButtonUI(chooseFileIcon));
        chooseFileIcon.setBounds(802,186,50,36);
        chooseFileIcon.setIcon(IResources.filesIcon);
        chooseFileIcon.addActionListener(e -> {
            iconChooser.setCurrentDirectory(new File("E:/"));
            File f = new File(fileField.getText());
            if(f.exists()){
                iconChooser.setCurrentDirectory(f);
            }
            int i = iconChooser.showOpenDialog(getContentPane());
            if(i == JFileChooser.APPROVE_OPTION){
                File file = iconChooser.getSelectedFile();
                String type = null;
                try {
                    type = Files.probeContentType(file.toPath());
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Log.log.printException(ex);
                }
                //"application/x-msdownload".equals(type)
                if(!"image/jpeg".equals(type) && !"image/png".equals(type)){
                    //Icon imageIcon = ;
                    //Image im = ((ImageIcon)imageIcon).getImage();
                    String name = file.getName().substring(0,file.getName().lastIndexOf("."));
                    File out = new File(IPath.icons.getPath()+File.separator+name+".png");
                    ImageUtil.saveImage(out,ImageUtil.getIcon(file));
                }
            }
        });
        this.add(chooseFileIcon);
        
        chooseIcon.setUI(new DarkButtonUI(chooseIcon));
        chooseIcon.setForeground(IColorSetting.charsColor1);
        chooseIcon.setBounds(752,186,50,36);
        chooseIcon.addActionListener(e -> {
            if(!iconChoosePanel.isVisible()){
                iconChoosePanel.enableIconChoosePanel(chooseFile,fileLabel,titleLabel,urlLabel,urlCheckBox,
                        fileCheckBox,titleField,fileField,urlField);
            }
            else {
                iconChoosePanel.disableIconChoosePanel();
            }
        });
        this.add(chooseIcon);
    }
    
    //初始化 显示文字
    public void LabelInitialization(){
        titleLabel.setForeground(IColorSetting.charsColor1);
        urlLabel.setForeground(IColorSetting.charsColor1);
        fileLabel.setForeground(IColorSetting.charsColor1);
        iconLabel.setForeground(IColorSetting.charsColor1);
        information.setForeground(IColorSetting.charsColor1);
        
        Font font = new Font("宋体", Font.BOLD,20);
        titleLabel.setFont(font);
        urlLabel.setFont(font);
        fileLabel.setFont(font);
        iconLabel.setFont(font);
        information.setFont(font);
        
        //fileLabel.setOpaque(true);
        //fileLabel.setBackground(new Color(255,255,255));
        
        titleLabel.setBounds(50,50,100,36);
        urlLabel.setBounds(50,166,100,36);
        fileLabel.setBounds(50,282,100,36);
        iconLabel.setBounds(752,50,100,36);
        information.setBounds(752,282,152,36);
        
        this.add(titleLabel);
        this.add(urlLabel);
        this.add(fileLabel);
        this.add(iconLabel);
        this.add(information);
    }
    
    //初始化 选项框
    public void checkBoxInitialization(){
        urlCheckBox.setBounds(150,166,150,36);
        fileCheckBox.setBounds(150,282,150,36);
    
        urlCheckBox.setOpaque(false);
        fileCheckBox.setOpaque(false);
        
        urlCheckBox.setForeground(IColorSetting.charsColor1);
        fileCheckBox.setForeground(IColorSetting.charsColor1);
        
        urlCheckBox.setFocusPainted(false);
        fileCheckBox.setFocusPainted(false);
        
        urlCheckBox.addActionListener(e ->{
            if(!fileCheckBox.isSelected()){
                urlCheckBox.setSelected(true);
            }
            urlField.setEnabled(true);
            urlField.setBackground(IColorSetting.partitionLinesColor3);
            fileCheckBox.setSelected(false);
            fileField.setEnabled(false);
            fileField.setBackground(IColorSetting.rectanglesColor1);
            fileChooser.setEnabled(false);
        });
        fileCheckBox.addActionListener(e -> {
            if(!urlCheckBox.isSelected()){
                fileCheckBox.setSelected(true);
            }
            fileField.setEnabled(true);
            fileField.setBackground(IColorSetting.partitionLinesColor3);
            urlCheckBox.setSelected(false);
            urlField.setEnabled(false);
            urlField.setBackground(IColorSetting.rectanglesColor1);
            fileChooser.setEnabled(true);
        });
        this.add(urlCheckBox);
        this.add(fileCheckBox);
        
//        if(m==null){
//            fileCheckBox.setEnabled(false);
//            urlCheckBox.setEnabled(false);
//        }
        if(PartModule.class.getName().equals(type)){
            fileCheckBox.setEnabled(false);
            urlCheckBox.setEnabled(false);
            urlCheckBox.setSelected(false);
        }
    }
    
    //初始化 文本框
    public void fieldInitialization(){
        String urlText = "";
        String fileText = "";
        String titleText = "";
        if(!urlText.equals(String.valueOf(IPath.url))){
            urlField.setText(urlText);
        }
        if(!fileText.equals(String.valueOf(IPath.getSelfPath(this.getClass())))){
            fileField.setText(fileText);
        }
        titleField.setText(titleText);
        
        urlField.setForeground(IColorSetting.charsColor1);
        fileField.setForeground(IColorSetting.charsColor1);
        titleField.setForeground(IColorSetting.charsColor1);
    
        Font font = new Font("楷体", Font.BOLD,15);
        urlField.setFont(font);
        fileField.setFont(font);
        titleField.setFont(font);
        
        urlField.setBounds(50,202,600,36);
        fileField.setBounds(50,318,550,36);
        titleField.setBounds(50,86,600,36);
        
        urlField.setBackground(IColorSetting.partitionLinesColor3);
        fileField.setBackground(IColorSetting.partitionLinesColor3);
        titleField.setBackground(IColorSetting.partitionLinesColor3);
        
        urlField.setCaretColor(IColorSetting.charsColor1);
        fileField.setCaretColor(IColorSetting.charsColor1);
        titleField.setCaretColor(IColorSetting.charsColor1);
        
        urlField.setDisabledTextColor(IColorSetting.rectanglesColor2);
        fileField.setDisabledTextColor(IColorSetting.rectanglesColor2);
        
        this.add(urlField);
        this.add(fileField);
        this.add(titleField);
    
        fileField.setEnabled(false);
        fileField.setBackground(IColorSetting.rectanglesColor1);
        
//        if(m==null){
//            fileField.setEnabled(false);
//            urlField.setEnabled(false);
//        }
        if(PartModule.class.getName().equals(type)){
            fileField.setEnabled(false);
            urlField.setEnabled(false);
            urlField.setBackground(IColorSetting.rectanglesColor1);
        }
    }
    
    //初始化 图标
    public void panelInitialization(){
        icon.setBounds(752,86,100,100);
        icon.setOpaque(false);
        this.add(icon);
    }
    
    
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        Shape line1 = new Line2D.Float(700,50,700,364);
        Shape line2 = new Line2D.Float(701,50,701,364);
        Shape line3 = new Line2D.Float(702,50,702,364);
        g2.setColor(IColorSetting.partitionLinesColor6);
        g2.draw(line1);
        g2.draw(line3);
        g2.setColor(IColorSetting.partitionLinesColor7);
        g2.draw(line2);
    }
    
    public void enableTextField(boolean b,JTextField t){
        enableTextField(b,t,IColorSetting.partitionLinesColor3,IColorSetting.rectanglesColor1);
    }
    public void enableTextField(boolean b,JTextField t,Color enableColor,Color disableColor){
        t.setEnabled(b);
        if(b){
            t.setBackground(enableColor);
        }
        else {
            t.setBackground(disableColor);
        }
    }
}
