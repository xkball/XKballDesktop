package xkball.util.fileUtil;

import java.io.File;

public class Directory {
    private File file;
    public Directory(String s){
        this(new File(s));
    }
    public Directory(File file){
        if(file.isDirectory()){
            this.file = file;
        }
        else {
            this.file = new File(file.getPath().substring(0, file.getPath().lastIndexOf(File.separator)));
        }
    }
    
    public File getFile() {
        return file;
    }
    
    public String getPath(){
        return file.getPath();
    }
    
    public static File toDirectory(File file){
        return new Directory(file).getFile();
    }
    
    @Override
    public String toString(){
        return getPath();
    }
    
}
