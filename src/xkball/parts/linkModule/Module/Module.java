package xkball.parts.linkModule.Module;

import xkball.util.fileUtil.SerializeUtil;

import java.io.*;

public abstract class Module implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private String title;
    private int numbering;
    
    protected Module(String title, int numbering) {
        this.title = title;
        this.numbering = numbering;
    }
    
    abstract public File getFileName(File file);
    public abstract void serialize(File file);
    public static Module deserialize(File file){
        Object obj = SerializeUtil.deserialize(file);
        if(obj instanceof Module){
            return (Module) obj;
        }
        else {
            return null;
        }
    }
    
//    public void up(){
//        this.setNumbering(numbering-1);
//    }
//
//    public void down(){
//        this.setNumbering(numbering+1);
//    }
    
    public int getNumbering() {
        return numbering;
    }
    
    public void setNumbering(int numbering) {
        this.numbering = numbering;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Override
    public String toString() {
        return "标题"+this.getTitle()+" 序号"+this.getNumbering();
    }
}
