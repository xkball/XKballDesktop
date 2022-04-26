package xkball.parts.resourseloader;

import xkball.MainFrame;

import java.io.File;
import java.net.URL;

public interface IPath {
    URL url = MainFrame.class.getResource("/");
    File in = new File(url.getFile()).getParentFile();
    
    //总目录
    File mainFolder = new File(in.getPath()+File.separator+"xkball");
    //log
    File logFile = new File(mainFolder.getPath()+File.separator+"log");
    //LinkModule
    File linkModule = new File(mainFolder.getPath()+File.separator+"linkModule");
    File LMLeft = new File(linkModule.getPath()+File.separator+"left");
    File LMRight = new File(linkModule.getPath()+File.separator+"right");
    //图片
    File photos = new File(mainFolder.getPath()+File.separator+"photos");
    //config
    File config = new File(mainFolder.getPath()+File.separator+"config");
    //图标
    File icons = new File(mainFolder.getPath()+File.separator+"icons");
    //计划单
    File planTexts = new File(mainFolder.getPath()+File.separator+"planTexts");
    
    static File getSelfPath(Class aClass){
        URL url = aClass.getResource("");
        return new File(url.getFile()).getParentFile();
    }
}
