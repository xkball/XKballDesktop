package xkball.parts.log;

import xkball.parts.resourseloader.IPath;
import xkball.util.fileUtil.FileUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Log {
    public static Log log = new Log();
    private File logPath;
    private Log(){
        logPath = new File(IPath.logFile.getPath()+File.separator+"log.txt");
        if(!logPath.getParentFile().exists()){
            logPath.getParentFile().mkdirs();
        }
        if(!logPath.exists()){
            try {
                logPath.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static String getTime(){
        Date date = new Date();
        String form = String.format("%tF",date);
        String time = String.format("%tT",date);
        return "[" + form + " " + time + "]";
    }
    
    public void print(String s){
        String out = getTime()+s;
        FileUtil.print(out,logPath);
    }
    
    public void printException(Exception e){
        StackTraceElement[] messages = e.getStackTrace();
        //print(e.getCause()+ " "+ e.getLocalizedMessage());
        printVoidLine();
        print(e.toString());
        for (StackTraceElement message : messages) {
            print("[error] " + message.toString());
        }
        printVoidLine();
        
    }
    
    public void printVoidLine(){
        FileUtil.print("",logPath);
    }
    
}
