package xkball;


import xkball.parts.frame.XkballFrame;
import xkball.parts.linkModule.Module.Module;
import xkball.parts.linkModule.Module.PartModule;
import xkball.parts.log.Log;
import xkball.parts.resourseloader.IPath;
import xkball.parts.resourseloader.IResources;
import xkball.util.DesktopUtil;
import xkball.util.fileUtil.FileUtil;

import java.io.File;
import java.io.FilterOutputStream;
import java.net.URL;

public class MainFrame {
    
    //该项目所有要图片的地方默认只识别.png和.jpg
    //bug记录：选择图标时图标可能会乱变，原因未知   已修复
    //方法名称前init 代表initialization 初始化
    public static void main(String[] args){
//        String place = String.valueOf(MainFrame.class.getProtectionDomain().getCodeSource().getLocation());
//        System.out.println(place);
   
        try {
//            URL url = MainFrame.class.getResource("/resource/icon.png");
//            Log.log.print(url.toString());
            if(!IPath.mainFolder.exists()){
                IPath.mainFolder.mkdirs();
    
                FileUtil.copy("/resource/error.txt", IPath.mainFolder);
                FileUtil.copy("/resource/init.txt", IPath.mainFolder);
                
                DesktopUtil.openFile(IPath.start);
                DesktopUtil.openFile(IPath.initOut);
            }
            if(!IPath.resource.isHidden()){
                String string=" attrib +h "+IPath.resource.getAbsolutePath();
                Runtime.getRuntime().exec(string);
            }
            //throw new RuntimeException("哎就是玩");
            XkballFrame e = new XkballFrame("桌面小程序");
            e.setVisible(true);
            
        } catch (Exception e){
            e.printStackTrace();
            Log.log.printException(e);
            DesktopUtil.openFile(IPath.errorOut);
        }
        
        //System.out.println(MathUtil.getRandomNumberWithinARange(1,PhotosPanel.getPhotosCounts()));
        //LinkedModule lm = new LinkedModule(false,"https://space.bilibili.com/313103156");
        //new EditFrame(lm).setVisible(true);
        //Path file = new File("235.png").toPath();
        //String name = file.getName().substring(0,file.getName().lastIndexOf("."));
        //System.out.println(Files.probeContentType(file));
        
    }
}