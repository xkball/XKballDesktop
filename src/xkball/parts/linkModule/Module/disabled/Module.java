package xkball.parts.linkModule.Module.disabled;

import java.io.Serializable;

public abstract class Module implements Serializable {
    private String title;
    private int numbering;
    private boolean isLM;
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public int getNumbering() {
        return numbering;
    }
    
    public void setNumbering(int numbering) {
        this.numbering = numbering;
    }
    
    public void setLM(boolean b){
        this.isLM = b;
    }
    
    public boolean isLM(){
        return isLM;
    }
}
