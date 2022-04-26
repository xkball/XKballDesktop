package xkball.parts.resourseloader;

import xkball.parts.frame.XkballFrame;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public interface IResources {
    URL urlIcon = XkballFrame.class.getResource("/resource/icon.png");
    ImageIcon icon = new ImageIcon(urlIcon);
    
    URL urlFileIcon = XkballFrame.class.getResource("/resource/filesIcon.png");
    ImageIcon filesIcon = new ImageIcon(urlFileIcon);
    
    URL urlLargeIcon = XkballFrame.class.getResource("/resource/icon_large.png");
    ImageIcon largeIcon = new ImageIcon(urlLargeIcon);
    
    URL urlLocalLinkIcon = XkballFrame.class.getResource("/resource/local_link_icon.png");
    
    URL urlNetLinkIcon = getResourceURL("/resource/net_link_icon.png");
    
    URL urlHookIcon = getResourceURL("/resource/hook.png");
    URL urlProngIcon = getResourceURL("/resource/prong.png");
    
    URL urlArrowRight = getResourceURL("/resource/arrow_right.png");
    URL urlArrowLeft= getResourceURL("/resource/arrow_left.png");
    
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
        return XkballFrame.class.getResource("/resource/"+name+"."+kind);
    }
    
    static URL getResourceURL(String path){
        return XkballFrame.class.getResource(path);
    }
}
