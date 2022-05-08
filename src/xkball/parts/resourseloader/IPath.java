package xkball.parts.resourseloader;

import xkball.MainFrame;

import java.io.File;
import java.net.URL;
import java.util.Objects;

public interface IPath {
    URL url = MainFrame.class.getProtectionDomain().getCodeSource().getLocation();
    File in = new File(Objects.requireNonNull(url).getFile()).getParentFile();
    
    //自启动目录
    
    File start = new File(System.getProperty("user.home") +
            "\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\");
    //总目录
    File mainFolder = new File(in.getPath()+File.separator+"xkball");
    File resource = new File(mainFolder.getPath()+File.separator+"resource");
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
    
    //说明文档
    File init = new File(IResources.getResourceURL("/resource/error.txt").getFile());
    File error = new File(IResources.getResourceURL("/resource/init.txt").getFile());
    File initOut = new File(mainFolder.getPath()+File.separator+"init.txt");
    File errorOut = new File(mainFolder.getPath()+File.separator+"error.txt");
    
    static File getSelfPath(Class aClass){
        URL url = aClass.getResource("");
        return new File(url.getFile()).getParentFile();
    }
}
