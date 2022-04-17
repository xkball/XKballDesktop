package xkball.parts.linkModule.Module.disabled;

import xkball.parts.log.Log;
import xkball.parts.resourseloader.IPath;
import xkball.parts.resourseloader.IResources;

import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

//import static xkball.parts.linkModule.Module.disabled.LMPanel.countAllModules;

public class LinkedModule extends Module implements Serializable {
    private int ID;
    private URL url = IPath.url;                                 //原始值仅仅是为了防错
    private File file = IPath.getSelfPath(this.getClass());      //不能使用
    private boolean isFile;
    private File icon = new File(IResources.urlIcon.getFile());
    private int part;
    
    public LinkedModule(int part){
        this.setLM(true);
        this.setTitle("default");
        this.part = part;
    }
    public LinkedModule(boolean isFile, String string){
        this(isFile,string,"default",1);
    }
    
    public LinkedModule(boolean isFile,String string,String title,int part){
        this.setLM(true);
        File f = new File(IPath.linkModule.getPath() + File.separator + part);
        if(!f.exists()){
            f.mkdirs();
        }
        this.part = part;
        this.ID = getLinkModuleCounts();
        //this.setNumbering(countAllModules(part));
        this.isFile = isFile;
        if(isFile){
            this.file = new File(string);
        }
        else {
            try {
                this.url = new URL(string);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.log.printException(e);
            }
        }
        this.setTitle(title);
    }
    
    public void use(){
        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            if(isFile){
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
                if(desktop.isSupported(Desktop.Action.BROWSE)){
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
    
    
    //序列化对象的数量+1
    public int getLinkModuleCounts(){
        /*
        int result = 0;
        while (true){
            File file = new File(IPath.linkModule.getPath()+File.separator+i+File.separator+result+".lm");
            if(!file.exists()) {
                break;
            }
            result = result+1;
        }
        return result;
        
         */
        return Objects.requireNonNull(new File(IPath.linkModule.getPath() + File.separator + part).listFiles()).length-1;
    }
    
    //序列化对象
    public void serializeObj() {
        File file = new File(IPath.linkModule.getPath()+File.separator+this.part+File.separator+this.getID()+".lm");
        if(file.exists()){
            file.delete();
        }
        if(!file.exists()){
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                Log.log.printException(e);
            }
        }
        
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(this);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.log.printException(e);
        }
    }
    
    
    public static LinkedModule deserialize(int ID,int part) {
        File file = new File(IPath.linkModule.getPath()+File.separator+part+File.separator+ID+".lm");
        LinkedModule linkedModule = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            Object obj = ois.readObject();
            LinkedModule lm = (LinkedModule) obj;
            ois.close();
            linkedModule = lm;
            linkedModule.setLM(true);
    
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            Log.log.printException(e);
        }
        return linkedModule;
    }
    
    public int getID(){
        return ID;
    }
    
    public void setID(int ID){
        this.ID = ID;
    }
    
    public URL getUrl() {
        return url;
    }
    
    public void setUrl(URL url) {
        this.url = url;
    }
    
    public File getFile() {
        return file;
    }
    
    public void setFile(File file) {
        this.file = file;
    }
    
    public File getIcon() {
        return icon;
    }
    
    public void setIcon(File icon) {
        this.icon = icon;
    }
    
    public void setIsFile(boolean b){
        isFile = b;
    }
    
    public boolean isFile(){
        return isFile;
    }
}
