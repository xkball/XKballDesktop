package xkball.util.fileUtil;

import sun.awt.shell.ShellFolder;
import xkball.parts.log.Log;
import xkball.parts.resourseloader.IResources;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ImageUtil {
    
    public static Dimension getImgDimension(File path){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int tx = image.getWidth();
        int ty = image.getHeight();
        return new Dimension(tx,ty);
    }
    
    public static Dimension getImgDimension(String path){
        File file = new File(path);
        return getImgDimension(file);
    }
    
    public static Dimension flushWidthHeight(File path, Dimension size) {
        int x;
        int y;
        Dimension oldSize = ImageUtil.getImgDimension(path);
        int tx = (int) oldSize.getWidth();
        int ty = (int) oldSize.getHeight();
        //System.out.println(tx+" "+ty);
        //System.out.println(this.getWidth()+" "+this.getHeight());
        float ratio = (float) ty / (float) tx;
        float ratioN = 1 / ratio;
        //System.out.println(ratio+" "+ratioN);
        if (tx <= ty) {
            y = (int) size.getHeight();
            x = (int) (y * ratioN);
        } else {
            x = (int) size.getWidth();
            y = (int) (x * ratio);
        }
        return new Dimension(x, y);
    }
    
    public static Image getIcon(File file){
        if(file != null && file.exists()){
            
            try {
                ShellFolder shellFolder = ShellFolder.getShellFolder(file);
                return shellFolder.getIcon(true);
//                FileSystemView fsv = FileSystemView.getFileSystemView();
//                return fsv.getSystemIcon(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.log.printException(e);
            }
            
            //FileSystemView fileSystemView = FileSystemView.getFileSystemView();
            //return fileSystemView.getSystemIcon(file);
        }
        return IResources.icon.getImage();
    }
    
    public static void saveImage(File to,Image image){
    
        BufferedImage bufferedImage = (BufferedImage) image;
//        try {
//            if(to.exists()){
//                to.delete();
//            }
//            ImageIO.write(bufferedImage,"png",to);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Graphics2D g2d = bufferedImage.createGraphics();
        //  content.printAll(g2d);
        try {
            ImageIO.write(bufferedImage, "png", to);
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2d.dispose();
    }
}
