package xkball.parts.frame;

//import javafx.scene.input.KeyCode;
//import xkball.parts.config.KeyConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ShowFrames implements WindowFocusListener, KeyListener, MouseListener {
    //private boolean[] isPressed = new boolean[KeyConfig.keyConfig.WindowKeyCodes.size()];
    //private boolean a = false;
    private XkballFrame frame;
    
    //public ShowFrames(XkballFrame frame,boolean b){
    //    this(frame);
   //     a=b;
    //}
    public ShowFrames(XkballFrame frame) {
        this.frame = frame;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        /*
        String string = KeyEvent.getKeyText(e.getKeyCode());
        for (int i=0;i<KeyConfig.keyConfig.WindowKeyCodes.size();i++){
            if(string.equals(KeyConfig.keyConfig.WindowKeyCodes.get(i).getName())){
                isPressed[i] = true;
            }
        }
         */
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        /*
        String string = KeyEvent.getKeyText(e.getKeyCode());
        for (int i=0;i<KeyConfig.keyConfig.WindowKeyCodes.size();i++){
            if(string.equals(KeyConfig.keyConfig.WindowKeyCodes.get(i).getName())){
                isPressed[i] = false;
            }
        }
        
         */
    }
    
    @Override
    public void windowGainedFocus(WindowEvent e) {
    
    }
    
    @Override
    public void windowLostFocus(WindowEvent e) {
        frame.setVisible(false);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            frame.setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()-750),0);
            frame.left.setNeedReload(true);
            frame.setVisible(true);
            //frame.flush();
        }
        //if(a) {
        //    frame.repaint();
        //}
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    
    }
}
