package xkball.parts.linkModule.Module;

import xkball.parts.SwingParts.SelectionPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class ModuleButton extends SelectionPanel {
    
    //private int state = 0;
    private boolean isEdit = false;
    private LMMainPanel panel;
    
    public ModuleButton(Dimension d,LMMainPanel panel){
        super(d);
        this.panel=panel;
        this.addMouseListener(new EditListener());
    }
    
    
//    public int getState() {
//        return state;
//    }
//
//    public void setState(int state) {
//        this.state = state;
//    }
    abstract public void clickedE(int state);
    
    public boolean isEdit() {
        return isEdit;
    }
    
    public void setEdit(boolean edit) {
        isEdit = edit;
    }
    
    public LMMainPanel getPanel() {
        return panel;
    }
    
    private class EditListener implements MouseListener{
    
        @Override
        public void mouseClicked(MouseEvent e) {
            int key = e.getButton();
            if(isEdit){
                switch (key){
                    case MouseEvent.BUTTON1:
                        clickedE(-1);
                        break;
                    case MouseEvent.BUTTON3:
                        clickedE(1);
                        //System.out.println(1);
                        break;
                }
            }
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
}
