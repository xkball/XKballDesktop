package xkball.util.fileUtil;

import xkball.MainFrame;
import xkball.parts.log.Log;
import xkball.parts.resourseloader.IPath;
import xkball.parts.resourseloader.IResources;
import xkball.util.MathUtil;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class FileUtil {
    //to是文件夹
    
    /**
     * 简单的复制罢了
     * @param from 文件地址
     * @param to 文件粘贴地址，文件夹
     * @return 复制结果
     */
    public static boolean copy(String from,File to) throws FileNotFoundException {
        return copy(new File(from),to, MainFrame.class.getResourceAsStream(from));
    }
    public static boolean copy(File from,File to) throws FileNotFoundException {
        return copy(from,to,new FileInputStream(from));
    }
    public static boolean copy(File from,File to,InputStream InputStream){
        //Log.log.print(from.toString());
        //if(from.exists()){
            if(!to.getParentFile().exists()){
                to.getParentFile().mkdirs();
            }
            boolean res = false;
            try {
                File outFile = getCopyFile(from,to);
                outFile.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(outFile);
                Log.log.print("将"+from);
                Log.log.print("拷贝到"+to);
                byte[] data = new byte[1024];
                int t = 0;
                while ((t = InputStream.read(data)) != -1){
                    fileOutputStream.write(data,0,t);
                }
                InputStream.close();
                fileOutputStream.close();
                res = true;
            } catch (IOException e) {
                e.printStackTrace();
                Log.log.printException(e);
            } catch (NullPointerException e){
                e.printStackTrace();
                Log.log.print("复制失败");
                Log.log.printException(e);
            }
            return res;
            
        //}
        //else {
        //    return false;
        //}
    }
    
    public static String getCopyPath(File from,File to){
        return getCopyFile(from,to).getPath();
    }
    
    public static File getCopyFile(File from,File to){
        boolean b = to.isDirectory();
        if(!b){
            return to;
        }
        else {
            Directory tod = new Directory(to);
            return getCopyFile(from,tod);
        }
    }
    
    public static File getCopyFile(File from,Directory to){
        //if(from.exists()){
            String out = from.getPath().substring(from.getPath().lastIndexOf(File.separator));
            return new File(to.getPath()+out);
        //}
//        //else {
//            try {
//                throw new FileNotFoundException();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//                Log.log.printException(e);
//            }
//            return null;
//        }
    }
    
    public static File[] getAllFiles(File file){
        return getAllFiles(new Directory(file));
    }
    
    public static File[] getAllFiles(Directory directory){
        return directory.getFile().listFiles();
    }
    
    public static File[] getTypedFiles(File file,String type){
        File[] allFiles = getAllFiles(file);
        ArrayList<File> result = new ArrayList<File>();
        for(File files : allFiles){
            if(!files.isDirectory()){
                String t;
                if('.' == type.charAt(0)){
                    t = type;
                }
                else {
                    t = "."+type;
                }
                if(FileUtil.getExpandName(files).equals(t)){
                    result.add(files);
                }
            }
        }
        File[] results = new File[result.size()];
        for (int i = 0;i<results.length;i++){
            results[i] = result.get(i);
        }
        return results;
    }
    
    public static String getExpandName(File file){
        if(file.isFile()){
            return file.getPath().substring(file.getPath().lastIndexOf("."));
        }
        else {
            return "directory";
        }
    }
    
    public static void print(String string,File file){
        try {
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fw);
            
            bufferedWriter.newLine();
            bufferedWriter.write(string);
            bufferedWriter.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.log.printException(e);
        }
    }
    public static void print(String[] strings,File file){
        try {
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fw);
            for(String s : strings){
                
                bufferedWriter.newLine();
                bufferedWriter.write(s);
            }
            bufferedWriter.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.log.printException(e);
        }
    }
    
    public static int countLines(File file){
        if(!file.exists()){
            return 0;
        }
        else {
            int i = 0;
            try {
                FileReader fr = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fr);
        
                while (true){
                    String sr = bufferedReader.readLine();
                    if(sr == null){
                        break;
                    }
                    i++;
                }
        
                bufferedReader.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
                Log.log.printException(e);
            }
            return i;
        }
        
    }
    
    public static String readALine(int i,File file){
        String sr = null;
        try {
            FileReader fr = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fr);
        
            for(int times = 0;times<i;times++){
                sr = bufferedReader.readLine();
            }
        
            bufferedReader.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.log.printException(e);
        }
        return sr;
    }
    
    public static void printAtLine(int line,File file,String s){
        String[] strings = new String[line];
        for(int i=0;i<line;i++){
            strings[i] = readALine(i,file);
        }
        strings[line-1] = s;
        try {
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fw);
            
            for(String ss : strings){
                bufferedWriter.write(ss);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.log.printException(e);
        }
    }
    
    public static void createFile(File file){
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                Log.log.printException(e);
            }
        }
    }
    
    public static void createDirectory(File file){
        if(!file.exists()){
            file.mkdirs();
        }
    }
}
