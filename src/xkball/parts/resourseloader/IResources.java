package xkball.parts.resourseloader;

import xkball.parts.frame.XkballFrame;

import javax.swing.*;
import java.net.URL;

public interface IResources {
    URL urlIcon = XkballFrame.class.getResource("/resource/icon.png");
    ImageIcon icon = new ImageIcon(urlIcon);
    URL urlFileIcon = XkballFrame.class.getResource("/resource/filesIcon.png");
    ImageIcon filesIcon = new ImageIcon(urlFileIcon);
    
}
