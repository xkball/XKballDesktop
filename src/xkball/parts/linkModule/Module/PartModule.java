package xkball.parts.linkModule.Module;

import xkball.util.fileUtil.Directory;
import xkball.util.fileUtil.SerializeUtil;

import java.awt.*;
import java.io.File;

public class PartModule extends Module{
    private static final long serialVersionUID = 10086L;
    
    public PartModule(String title, int numbering) {
        super(title, numbering);
    }
    
    @Override
    public File getFileName(File file) {
        return new File(file.getPath()+File.separator+this.getTitle()+".pm");
    }
    
    @Override
    public void serialize(File file) {
        if(!file.isDirectory()){
            file = new Directory(file).getFile();
        }
        File out = new File(file.getPath()+File.separator+this.getTitle()+".pm");
        SerializeUtil.serializeObj(out,this);
    }
    
    @Override
    public String toString(){
        return "\ntitle: "+this.getTitle()+"\nnumbering: "+this.getNumbering();
    }
    
}
