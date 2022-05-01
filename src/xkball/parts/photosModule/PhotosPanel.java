package xkball.parts.photosModule;

import xkball.interfaces.IColorSetting;
import xkball.parts.log.Log;
import xkball.parts.resourseloader.IResources;
import xkball.parts.swingParts.SelectionPanel;
import xkball.parts.resourseloader.IPath;
import xkball.util.fileUtil.FileUtil;
import xkball.util.fileUtil.ImageUtil;
import xkball.util.exceptions.ContentsNotFoundException;
import xkball.interfaces.IFlushable;
import xkball.util.MathUtil;
import xkball.util.StringUtil;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.io.*;



public class PhotosPanel extends SelectionPanel implements IFlushable, MouseListener {
    private boolean isPng;
    private int numbering;
    private File path;
    private final File words = new File(IPath.photos.getPath()+File.separator+"words.txt");
    private int x = 10;
    private int y = 10;
    private String word;
    
    public PhotosPanel(){
        
        if(!words.getParentFile().exists()){
            words.getParentFile().mkdirs();
        }
        if(!words.exists()){
            try {
                words.createNewFile();
                FileWriter fw = new FileWriter(words);
                BufferedWriter bufferedWriter= new BufferedWriter(fw);
                bufferedWriter.write("made by xkball");
                bufferedWriter.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(getPhotosCounts()==0){
            try {
                FileUtil.copy("/resource/transparent_icon.png",new File(IPath.photos.getPath()+File.separator+"1.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        
        this.initialization();
        this.flush();
        this.addMouseListener(this);
        this.setShape1(new Rectangle2D.Float[]{new Rectangle2D.Float(2, 2, this.getWidth() - 5, this.getHeight() - 5)});
        this.setBorderColor(new Color(65,65,65,165));
        this.setFill(true);
    }
    public void initialization(){
        this.setOpaque(true);
        this.setSize(308,308);
        //this.setBounds(30,32,308,308);
        this.setBackground(IColorSetting.partitionLinesColor2);
        
    }
    @Override
    public void flush() {
        flushWord();
        flushPhoto();
        ImageUtil.flushWidthHeight(path,this.getSize());
        this.repaint();
        Log.log.print("PhotoPanel:刷新了图像");
    }
    
    public void flushPhoto(){
        if (this.getPhotosCounts() >= 1) {
            numbering = MathUtil.getRandomNumberWithinARange(1, getPhotosCounts());
            File png = new File(IPath.photos.getPath() + File.separator + numbering + ".png");
            File jpg = new File(IPath.photos.getPath() + File.separator + numbering + ".jpg");
            checkImgKind();
            path = isPng ? png : jpg;
        }
        //System.out.println(path);
    }
    
    public void checkImgKind(){
        File png = new File(IPath.photos.getPath()+File.separator+numbering+".png");
        File jpg = new File(IPath.photos.getPath()+File.separator+numbering+".jpg");
        if(jpg.exists()){
            isPng = false;
        }
        else if(png.exists()){
            isPng = true;
        }
    }
    
    public void flushWord(){
        if(this.getWordsCounts()>=1){
            try {
                int times = MathUtil.getRandomNumberWithinARange(1, this.getWordsCounts());
                FileReader fr = new FileReader(words);
                BufferedReader bufferedReader = new BufferedReader(fr);
                for(int i = 0;i<times ;i++){
                    String sr = bufferedReader.readLine();
                    if(i==times-1){
                        word = sr;
                    }
                }
        
                bufferedReader.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            throw new ContentsNotFoundException("语言文件不能为空");
        }
        
    }
    
    /*
    public Image getImg(){
        this.checkImgKind();
        URL url = null;
        File png = new File(Path.photos.getPath()+File.separator+numbering+".png");
        File jpg = new File(Path.photos.getPath()+File.separator+numbering+".jpg");
        if(isPng){
            try {
                url = png.toURI().toURL();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                url = jpg.toURI().toURL();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        Image image = Toolkit.getDefaultToolkit().getImage(url);
        return image;
    }
    */
    
    public void paintWord(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        Font font = new Font("楷体",Font.PLAIN,20);
        g2.setFont(font);
        g2.setColor(IColorSetting.charsColor1);
        int ny = this.getHeight()*3/4+y/4;
        if((308-ny)<10){
            ny = this.getHeight()-10;
        }
        g2.drawString(word,this.getWidth()/2- StringUtil.getWordX(font,word)/2,ny);
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Dimension d = ImageUtil.flushWidthHeight(path,this.getSize());
        x = (int) d.getWidth();
        y = (int) d.getHeight();
        Graphics2D g2 = (Graphics2D) g;
        Image img = Toolkit.getDefaultToolkit().getImage(path.getPath());
        g2.drawImage(img,this.getWidth()/2-x/2,this.getHeight()/2-y/2,x,y,this);
        paintWord(g);
    }
    
    public static int getPhotosCounts(){
        int result = 0;
        for(int i = 1;i<100;i++){
            File png = new File(IPath.photos.getPath()+File.separator+i+".png");
            File jpg = new File(IPath.photos.getPath()+File.separator+i+".jpg");
            if(!png.exists()&&!jpg.exists()) {
                result = i-1;
                break;
            }
        }
        return result;
    }
    
    public int getWordsCounts(){
        int count = 0;
        boolean tab = true;
        try {
            FileReader fr = new FileReader(words);
            BufferedReader bufferedReader = new BufferedReader(fr);
            while (tab){
                String sr = bufferedReader.readLine();
                if(sr!=null){
                    count=count+1;
                }
                else {
                    tab = false;
                }
            }
            
            bufferedReader.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        int key = e.getButton();
        if(key == MouseEvent.BUTTON1 && e.getClickCount() == 2){
            this.flush();
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    
    }
}
