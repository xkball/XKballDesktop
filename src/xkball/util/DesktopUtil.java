package xkball.util;

import xkball.parts.log.Log;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DesktopUtil {
    public static void openFile(File file){
        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            if(file.exists()){
                if (desktop.isSupported(Desktop.Action.OPEN)){
                    try {
                        desktop.open(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.log.printException(e);
                    }
                }
            }
        
        }
    }
    
}
