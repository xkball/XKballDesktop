package xkball.parts.resourseloader;

import xkball.MainFrame;
import xkball.parts.frame.XkballFrame;
import xkball.parts.log.Log;
import xkball.util.fileUtil.FileUtil;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Objects;

public interface IResources {
    URL urlIcon = getResourceURL("/resource/icon.png");
    ImageIcon icon = new ImageIcon(urlIcon);
    
    URL urlFileIcon = getResourceURL("/resource/filesIcon.png");
    ImageIcon filesIcon = new ImageIcon(urlFileIcon);
    
    URL urlLargeIcon = getResourceURL("/resource/icon_large.png");
    ImageIcon largeIcon = new ImageIcon(urlLargeIcon);
    
    URL urlLocalLinkIcon = getResourceURL("/resource/local_link_icon.png");
    
    URL urlNetLinkIcon = getResourceURL("/resource/net_link_icon.png");
    
    URL urlHookIcon = getResourceURL("/resource/hook.png");
    URL urlProngIcon = getResourceURL("/resource/prong.png");
    
    URL urlArrowRight = getResourceURL("/resource/arrow_right.png");
    URL urlArrowLeft= getResourceURL("/resource/arrow_left.png");
    URL urlTransparentIcon = getResourceURL("/resource/transparent_icon.png");
    URL urlReturnIcon = getResourceURL("/resource/return_icon.png");
    
   static Image getImage(URL url){
        return getImageIcon(url).getImage();
    }
    
    static Image getImage(String name, String kind){
        return getImageIcon(name, kind).getImage();
    }
    
    static ImageIcon getImageIcon(String name, String kind){
        return new ImageIcon(getResourceURL(name,kind));
    }
    
    static ImageIcon getImageIcon(URL url){
        return new ImageIcon(url);
    }
    
    static URL getResourceURL(String name, String kind){
        return getResourceURL("/resource/"+name+"."+kind);
    }
    
    static URL getResourceURL(String path){
//       if(isStartupFromJar()){
//           if(Objects.equals('/',path.charAt(0))){
//               String s = path.substring(1);
//               return MainFrame.class.getClassLoader().getResource(s);
//           }
//           else {
//               return MainFrame.class.getResource(path);
//           }
//       }
//       else {
           return MainFrame.class.getResource(path);
       //}
    }
    static boolean isStartupFromJar() {
        String protocol = MainFrame.class.getResource("").getProtocol();
        return Objects.equals(protocol, "jar");
    }
}
