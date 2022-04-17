package xkball.parts.linkModule.Module;

import xkball.parts.log.Log;
import xkball.util.fileUtil.Directory;
import xkball.util.fileUtil.SerializeUtil;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class LinkModule extends Module{
    
    private static final long serialVersionUID = 12345679L;
    
    private String filePath;
    private String URLPath;
    private boolean isFile;
    private File icon;
    
    public LinkModule(String title, int numbering) {
        super(title, numbering);
    }
    
    @Override
    public File getFileName(File file) {
        return new File(file.getPath()+File.separator+this.getTitle()+".lm");
    }
    
    public LinkModule(String title,int numbering,boolean isFile,String string,File icon){
        super(title,numbering);
        this.setFile(isFile);
        this.setFilePath(string);
        this.setURLPath(string);
        this.setIcon(icon);
    
    }
    
    public void use(){
        File file = new File(filePath);
        URL url = null;
        try {
            url = new URL(URLPath);
            } catch (MalformedURLException e) {
            
            }
        
        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            if(isFile && file.exists()){
                if (desktop.isSupported(Desktop.Action.OPEN)){
                    try {
                        desktop.open(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.log.printException(e);
                    }
                }
            }
            else {
                if(desktop.isSupported(Desktop.Action.BROWSE ) && url != null){
                    try {
                        desktop.browse(url.toURI());
                    } catch (IOException | URISyntaxException e) {
                        e.printStackTrace();
                        Log.log.printException(e);
                    }
                }
            }
        }
    }
    
    
    @Override
    public void serialize(File file) {
        if(!file.isDirectory()){
            file = new Directory(file).getFile();
        }
        File out = new File(file.getPath()+File.separator+this.getTitle()+".lm");
        SerializeUtil.serializeObj(out,this);
    }
    
    @Override
    public String toString(){
        String link;
        if(isFile){
            link=filePath;
        }
        else{
            if(URLPath == null){
                link = "unknown";
            }
            else {
                link = URLPath;
            }
        }
        return "\ntitle: "+this.getTitle()+"\nnumbering: "+this.getNumbering()
                +"\nicon: "+this.getIcon().getPath()+"\nlink: "+link;
    }
    
    public String getAPath(){
        if(isFile){
            return getFilePath();
        }
        else {
            return getURLPath();
        }
    }
    public File getIcon() {
        return icon;
    }
    
    public void setIcon(File icon) {
        this.icon = icon;
    }
    
    public String getFilePath() {
        return filePath;
    }
    
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    public String getURLPath() {
        return URLPath;
    }
    
    public void setURLPath(String URLPath) {
        this.URLPath = URLPath;
    }
    
    public boolean isFile() {
        return isFile;
    }
    
    public void setFile(boolean file) {
        isFile = file;
    }
    
    
}
