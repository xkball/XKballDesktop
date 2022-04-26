package xkball.parts.SwingParts;

import xkball.interfaces.IColorSetting;
import xkball.parts.others.PlanText;
import xkball.ui.DarkButtonUI;
import xkball.ui.DarkJTextFieldUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.Line2D;
import java.io.File;

public class PlanPanel extends JPanel {
    
    private PlanText nowText;
    private final String place;
    private int page = 0;
    
    private final JTextField title = new JTextField();
    private final JTextField text1 = new JTextField();
    private final JTextField text2 = new JTextField();
    private final JTextField text3 = new JTextField();
    private final JTextField text4 = new JTextField();
    private final JTextField text5 = new JTextField();
    private final JTextField text6 = new JTextField();
    
    private ClickPanel click1;
    private ClickPanel click2;
    private ClickPanel click3;
    private ClickPanel click4;
    private ClickPanel click5;
    private ClickPanel click6;
    
    private final JButton nextLeft = new JButton("<-");
    private final JButton nextRight = new JButton("->");
    private final JButton returnCenter = new JButton();
    
    private final JLabel showPage;
    //overallMoveDown
    int y = 5;
    
    public PlanPanel(String place){
        this.place = place;
        this.nowText = PlanText.getInstance(new File(place),page);
        
        this.setBackground(IColorSetting.partitionLinesColor2);
        this.setOpaque(true);
        this.setLayout(null);
        this.addFocusListener(new SaveModule(this));
        BackgroundPanel backgroundPanel = new BackgroundPanel(308,408){
            @Override
            public void paintBackgroundShape(Graphics g){
                Graphics2D g2 = (Graphics2D) g;
                Shape[] shapes1 = new Shape[]{
                        new Line2D.Float(0,65,308,65),
                        new Line2D.Float(20,105+y,288,105+y),
                        new Line2D.Float(20,145+y,288,145+y),
                        new Line2D.Float(20,185+y,288,185+y),
                        new Line2D.Float(20,225+y,288,225+y),
                        new Line2D.Float(20,265+y,288,265+y),
                        new Line2D.Float(20,305+y,288,305+y),
                        new Line2D.Float(0,325+y,308,325+y)
                };
                Shape[] shapes2 = new Shape[]{
                        new Line2D.Float(0,66,308,66),
                        new Line2D.Float(0,326+y,308,326+y),
                        new Line2D.Float(55,75+y,55,305+y)
                };
                g2.setColor(IColorSetting.partitionLinesColor4);
                for(Shape s : shapes1){
                    g2.draw(s);
                }
                g2.setColor(IColorSetting.partitionLinesColor3);
                for(Shape s : shapes2){
                    g2.draw(s);
                }
            }
        };
        showPage = new JLabel(String.valueOf(page));
        this.init();
        this.add(backgroundPanel);
        this.loadIn();
    }
    
    public void init(){
        this.removeAll();
        title.setBounds(104,25,100,40);
        
        //line1
        click1 = new ClickPanel(20,75+y,30,30);
        text1.setBounds(70,75+y,218,30);
        //line2
        click2 = new ClickPanel(20,115+y,30,30);
        text2.setBounds(70,115+y,218,30);
        //line3
        click3 = new ClickPanel(20,155+y,30,30);
        text3.setBounds(70,155+y,218,30);
        //line4
        click4 = new ClickPanel(20,195+y,30,30);
        text4.setBounds(70,195+y,218,30);
        //line5
        click5 = new ClickPanel(20,235+y,30,30);
        text5.setBounds(70,235+y,218,30);
        //line6
        click6 = new ClickPanel(20,275+y,30,30);
        text6.setBounds(70,275+y,218,30);
        
        showPage.setBounds(141,376+y,26,26);
        showPage.setForeground(IColorSetting.charsColor1);
        showPage.setOpaque(false);
        showPage.setHorizontalAlignment(JLabel.CENTER);
        
        nextLeft.setBounds(55,346+y,30,30);
        nextRight.setBounds(223,346+y,30,30);
        returnCenter.setBounds(139,346+y,30,30);
        
        nextLeft.setFont(new Font("",Font.PLAIN,8));
        nextRight.setFont(new Font("",Font.PLAIN,8));
        
        nextLeft.addActionListener(e -> this.loadPage(page-1));
        nextRight.addActionListener(e -> this.loadPage(page+1));
        returnCenter.addActionListener(e -> this.loadPage(0));
        
        title.setHorizontalAlignment(JTextField.CENTER);
        
        title.setUI(new DarkJTextFieldUI());
        text1.setUI(new DarkJTextFieldUI());
        text2.setUI(new DarkJTextFieldUI());
        text3.setUI(new DarkJTextFieldUI());
        text4.setUI(new DarkJTextFieldUI());
        text5.setUI(new DarkJTextFieldUI());
        text6.setUI(new DarkJTextFieldUI());
        nextLeft.setUI(new DarkButtonUI(nextLeft));
        nextRight.setUI(new DarkButtonUI(nextRight));
        returnCenter.setUI(new DarkButtonUI(returnCenter));
        
        title.addFocusListener(new SaveModule(this));
        text1.addFocusListener(new SaveModule(this));
        text2.addFocusListener(new SaveModule(this));
        text3.addFocusListener(new SaveModule(this));
        text4.addFocusListener(new SaveModule(this));
        text5.addFocusListener(new SaveModule(this));
        text6.addFocusListener(new SaveModule(this));
        click1.addFocusListener(new SaveModule(this));
        click2.addFocusListener(new SaveModule(this));
        click3.addFocusListener(new SaveModule(this));
        click4.addFocusListener(new SaveModule(this));
        click5.addFocusListener(new SaveModule(this));
        click6.addFocusListener(new SaveModule(this));
        nextLeft.addFocusListener(new SaveModule(this));
        nextRight.addFocusListener(new SaveModule(this));
        returnCenter.addFocusListener(new SaveModule(this));
        
        title.setFont(new Font("",Font.BOLD,20));
        
        this.add(title);
        this.add(text1);
        this.add(text2);
        this.add(text3);
        this.add(text4);
        this.add(text5);
        this.add(text6);
        this.add(click1);
        this.add(click2);
        this.add(click3);
        this.add(click4);
        this.add(click5);
        this.add(click6);
        this.add(nextLeft);
        this.add(nextRight);
        this.add(returnCenter);
        this.add(showPage);
    }
    
    public void loadIn(){
        click1.setState(nowText.getState(0));
        text1.setText(nowText.getText(0));
        
        click2.setState(nowText.getState(1));
        text2.setText(nowText.getText(1));
        
        click3.setState(nowText.getState(2));
        text3.setText(nowText.getText(2));
        
        click4.setState(nowText.getState(3));
        text4.setText(nowText.getText(3));
        
        click5.setState(nowText.getState(4));
        text5.setText(nowText.getText(4));
        
        click6.setState(nowText.getState(5));
        text6.setText(nowText.getText(5));
        
        title.setText(nowText.getTitle());
    }
    
    public void loadBack(){
        nowText.setTexts(
                new String[]{
                text1.getText(),
                text2.getText(),
                text3.getText(),
                text4.getText(),
                text5.getText(),
                text6.getText()}
        
        );
        nowText.setStates(
                new int[]{
                click1.getState(),
                click2.getState(),
                click3.getState(),
                click4.getState(),
                click5.getState(),
                click6.getState()}
        );
        nowText.setTitle(title.getText());
    }
    
    public void savePage(){
        this.loadBack();
        nowText.saveTexts(new File(place));
    }
    
    public void loadPage(int i){
        this.savePage();
        this.setPage(i);
        this.nowText = PlanText.getInstance(new File(place),i);
        this.loadIn();
        showPage.setText(String.valueOf(page));
        showPage.repaint();
    }
    
    private void setPage(int i) {
        this.page = i;
    }

//    @Override
//    public void paint(Graphics g){
//        super.paint(g);
//        Graphics2D g2 = (Graphics2D) g;
//        g2.drawString(String.valueOf(page),139,380+y);
//    }
    
    private static class SaveModule implements FocusListener{
    
        private final PlanPanel planPanel;
        
        public SaveModule(PlanPanel planPanel){
            this.planPanel = planPanel;
        }
        @Override
        public void focusGained(FocusEvent e) {
        
        }
    
        @Override
        public void focusLost(FocusEvent e) {
            planPanel.savePage();
        }
    }
}
